package net.lab1024.smartadmin.module.business.report.service;

import net.lab1024.smartadmin.module.business.decision.dao.DecFinancialManagementDao;
import net.lab1024.smartadmin.module.business.decision.dao.DecLoanDepositDao;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositVO;
import net.lab1024.smartadmin.module.business.param.dao.ParComprehensiveScoreDao;
import net.lab1024.smartadmin.module.business.report.dao.*;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepComprehensiveRankingScoreEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.*;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**排名计算接口
 * Created by ${} on ${}.
 *
 * 1， 向结果表，决策表中取数
 * 2. 计算完成后在综合评分指标中进行区间查询
 * 3. 分数计算完成后根据团队id 相加，算出各团队总分数
 * 4. 存入表中
 *
 * 2. 根据各团队排名计算分数 ---
 *
 */

@Service
public class RankingService {

    @Autowired
    private RepBalanceSheetDao repBalanceSheetDao;
    @Autowired
    private RepCapitalAdequacyDao repCapitalAdequacyDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ParComprehensiveScoreDao parComprehensiveScoreDao;
    @Autowired
    private RepLiquidityTotalityDao repLiquidityTotalityDao;
    @Autowired
    private RepProfitDao repProfitDao;
    @Autowired
    private DecFinancialManagementDao decFinancialManagementDao;
    @Autowired
    private DecLoanDepositDao decLoanDepositDao;
    @Autowired
    private RepComprehensiveRankingScoreDao repComprehensiveRankingScoreDao;
    /**
     * 计算各个团队的评分
     * @param currentProjectId
     * @param currentStageId
     */

    /**未完成
     *不良贷款率---NonPerformingLoan
     * 不良贷款拨备覆盖率--NPLProvisionCoverage
     * EVA---EconomicValueAdded
     *
     */
    @Async
    public void calculateScore(Long currentProjectId,Long currentStageId){

        List<Long> currentTeamIds = departmentDao.queryProject(currentProjectId);

        //排序
        List<RepBalanceSheetVO> repBalanceSheetVOList = repBalanceSheetDao.queryList(currentProjectId,currentStageId);

        repBalanceSheetVOList.sort(new Comparator<RepBalanceSheetVO>(){
            @Override
            public int compare(RepBalanceSheetVO o1, RepBalanceSheetVO o2) {
                return o1.getTotalAssets()-o2.getTotalAssets();
            }
        });
        //
        //第一名初始分数  资产规模
        double c=8;
        //计算排名所用
        double i1= 0;

        //第一名初始分数  净利润（净亏损）
        double nc=10;
        //参数
        double i2= 0;

        RepComprehensiveRankingScoreEntity repRankEntity =new RepComprehensiveRankingScoreEntity();

        //for循环遍历所有团队
        for ( Long currentTeamId : currentTeamIds) {
            //综合排名分数表
             //1. 资本充足率
             //公式：（股东权益＋贷款损失准备＋次级债券）／风险加权资产总额

            //查询资产负债结果表
            RepBalanceSheetVO repBalanceSheetVO = repBalanceSheetDao.query(currentProjectId,currentTeamId,currentStageId);
            //查询资本充足率结果表
            RepCapitalAdequacyVO repCapitalAdequacyVO = repCapitalAdequacyDao.query(currentProjectId,currentTeamId,currentStageId);

            //股东权益
            Integer totalShareholdersEquity = repBalanceSheetVO.getTotalShareholdersEquity();
            //贷款损失准备
            Integer lossReserves = repBalanceSheetVO.getLossReserves();
            //次级债券
            Integer paymentSubBond = repBalanceSheetVO.getPaymentSubBond();
            //加权资产总额
            Integer total = repCapitalAdequacyVO.getTotal();

            //计算公式--得出评分
            Double capitalAdequacy = Double.valueOf((totalShareholdersEquity+lossReserves+paymentSubBond)/total);

            //获取----资本充足率---的评分
            Double capitalAdequacyScore = parComprehensiveScoreDao.queryCapitalAdequacy(capitalAdequacy);
            //System.out.println(capitalAdequacyScore);
            repRankEntity.setCapitalAdequacyRatio(capitalAdequacyScore);

            //2. 流动性比率
            //公式：（易变现资产总计+清偿力）／流动性负债总计 = 总体清偿力/流动性负债总计

            //查询流动性报表
            RepLiquidityTotalityVO repLiquidityTotalityVO = repLiquidityTotalityDao.query(currentProjectId,currentTeamId,currentStageId);

            //获取总体清偿力
            Integer totalitySolvency = repLiquidityTotalityVO.getTotalitySolvency();
            //获取流动性负债总计
            Integer totalCurrentLiabilities = repLiquidityTotalityVO.getTotalCurrentLiabilities();

            //计算公式--得出评分
             Double liquidityRatio = Double.valueOf(totalitySolvency/totalCurrentLiabilities);

            //获取----流动性比率---的评分
            Double liquidityRatioScore = parComprehensiveScoreDao.queryLiquidityRatioScore(liquidityRatio);
            //System.out.println(liquidityRatioScore);
            repRankEntity.setLiquidityRatio(liquidityRatioScore);

            //3. 存贷比
            //公式：（短期贷款＋贴现＋中长期贷款）／（短期存款＋短期储蓄存款＋存入短期保证金＋长期存款＋长期储蓄存款＋存入长期保证金）
            //查询资产负债表
            //获取短期贷款
            Integer shortLoan = repBalanceSheetVO.getShortLoan();
            //获取贴现
            Integer discount = repBalanceSheetVO.getDiscount();
            //获取中长期贷款
            Integer mlongLoan = repBalanceSheetVO.getMlongLoan();
            //获取短期存款
            Integer shortDeposit = repBalanceSheetVO.getShortDeposit();
            //获取短期储蓄存款
            Integer shortSavingsDeposit = repBalanceSheetVO.getShortSavingsDeposit();
            //获取存入短期保证金
            Integer depositShortMargin = repBalanceSheetVO.getDepositShortMargin();
            //获取长期存款
            Integer longDeposit = repBalanceSheetVO.getLongDeposit();
            //获取长期储蓄存款
            Integer longSavingsDeposit = repBalanceSheetVO.getLongSavingsDeposit();
            //获取存入长期保证金
            Integer depositLongMargin = repBalanceSheetVO.getDepositLongMargin();

            //计算公式--得出评分
            Double depositLoanRatio = Double.valueOf((shortLoan+discount+mlongLoan)/(shortDeposit+shortSavingsDeposit+
                    depositShortMargin+longDeposit+
                    longSavingsDeposit+depositLongMargin));


            //获取----存贷比---的评分
            Double depositLoanRatioScore = parComprehensiveScoreDao.queryDepositLoanRatioScore(depositLoanRatio);
            repRankEntity.setDepositLoanRatio(depositLoanRatioScore);



            //4. 资产收益率
            //公式：净利润／［（年初资产总计＋年末资产总计）／2］＊100％
            //获取利润表
            RepProfitVO repProfitVO = repProfitDao.query(currentProjectId,currentTeamId,currentStageId);
            //获取上一阶段资产负债表
            Long beforeCurrentStageId = null;
            RepBalanceSheetVO repBalanceSheetVBegin = null;
            beforeCurrentStageId = currentStageId - 1;
            if (beforeCurrentStageId < 2){
                repBalanceSheetVBegin = repBalanceSheetDao.queryPar(beforeCurrentStageId);
            }else{
                repBalanceSheetVBegin = repBalanceSheetDao.query(currentProjectId,currentTeamId,beforeCurrentStageId);
            }


            //获取净利润
            Integer retainedProfits = repProfitVO.getRetainedProfits();
            //获取年初资产总计（上一阶段资产负债表资产总计）
            Integer totalAssetsBegin = repBalanceSheetVBegin.getTotalAssets();
            //获取年末资产总计（本阶段资产负债表资产总计）
            Integer totalAssets = repBalanceSheetVO.getTotalAssets();

            //计算公式--得出评分
            Double ReturnOnAssets = Double.valueOf(retainedProfits/((totalAssetsBegin+totalAssets)/2));

            //获取----资产收益率---的评分
            Double ReturnOnAssetsScore = parComprehensiveScoreDao.queryReturnOnAssetsScore(ReturnOnAssets);
            repRankEntity.setReturnOnAssets(ReturnOnAssetsScore);

            //5. 搭桥贷款
            //有搭桥贷款为0分，无搭桥贷款为5分
            //当资产大于负债时，将生成搭桥贷款，向中央银行贷款>=0;资产小于负债时，向中央银行贷款<0

            //获取资产负债表中的想中央银行贷款
            Integer borrowingsCentralBank = repBalanceSheetVO.getBorrowingsCentralBank();
            //搭桥贷款评分
            Integer bridgeLoan = null;
            if (borrowingsCentralBank <= 0){
                //获取----搭桥贷款---的评分
                bridgeLoan = 5;
            }else {
                bridgeLoan = 0;
            }

            //获取搭桥贷款评分
            Double bridgeLoan1 = Double.valueOf(bridgeLoan);
            repRankEntity.setBridgeLoan(bridgeLoan1);

            //6计划质量---planQuality
            //（计划资产—实际资产）/实际资产*100%
            //查询资本充足率报告  上阶段---repCapitalAdequacyVO
            RepCapitalAdequacyVO repCapitalAdequacyVOBegin = null;
            if (beforeCurrentStageId < 2){
                repCapitalAdequacyVOBegin = repCapitalAdequacyDao.queryCapitalPar(beforeCurrentStageId);
            }else{
                repCapitalAdequacyVOBegin = repCapitalAdequacyDao.query(currentProjectId,currentTeamId,currentStageId);
            }
            //获取9个对应的贷款金额
            Integer shortMortgageLoanBegin = repCapitalAdequacyVOBegin.getShortMortgageLoan();
            Integer shortHypothecatedLoanBegin = repCapitalAdequacyVOBegin.getShortHypothecatedLoan();
            Integer shortGuaranteeLoanBegin = repCapitalAdequacyVOBegin.getShortGuaranteeLoan();
            Integer shortCreditLoanBegin = repCapitalAdequacyVOBegin.getShortCreditLoan();
            Integer discountRepCapitalBegin = repCapitalAdequacyVOBegin.getDiscount();
            Integer mlongMortgageLoanBegin = repCapitalAdequacyVOBegin.getMlongMortgageLoan();
            Integer mlongHypothecatedLoanBegin = repCapitalAdequacyVOBegin.getMlongHypothecatedLoan();
            Integer mlongGuaranteeLoanBegin = repCapitalAdequacyVOBegin.getMlongGuaranteeLoan();
            Integer mlongCreditLoanBegin = repCapitalAdequacyVOBegin.getMlongCreditLoan();

            //查询决策表
            DecLoanDepositVO decLoanDepositVO = decLoanDepositDao.query(currentProjectId,currentTeamId,currentStageId);
            //获取本阶段填写的增长率 9个
            Double shortMortgageLoanAdd = decLoanDepositVO.getShortMortgageLoanAdd();
            Double shortHypothecatedLoanAdd = decLoanDepositVO.getShortHypothecatedLoanAdd();
            Double shortGuaranteeLoanAdd = decLoanDepositVO.getShortGuaranteeLoanAdd();
            Double shortLoanCreditRiseAdd = decLoanDepositVO.getShortLoanCreditRiseAdd();
            Double discountAdd = decLoanDepositVO.getDiscountAdd();
            Double mlongMortgageLoanAdd = decLoanDepositVO.getMlongMortgageLoanAdd();
            Double mlongHypothecatedLoanAdd = decLoanDepositVO.getMlongHypothecatedLoanAdd();
            Double mlongGuaranteeLoanAdd = decLoanDepositVO.getMlongGuaranteeLoanAdd();
            Double mlongLoanCreditAdd = decLoanDepositVO.getMlongLoanCreditAdd();


            //计算各个业务计划资产plannedAssets
            //四个短期贷款
            Double sml = shortMortgageLoanAdd * shortMortgageLoanBegin * 0.01;
            Double shl = shortHypothecatedLoanAdd * shortHypothecatedLoanBegin * 0.01;
            Double sgl = shortGuaranteeLoanAdd * shortGuaranteeLoanBegin *0.01;
            Double slcr =shortLoanCreditRiseAdd * shortCreditLoanBegin *0.01;
            //贴现
            Double da =discountRepCapitalBegin * discountAdd * 0.01;
            //中长期贷款
            Double mml =mlongMortgageLoanAdd * mlongMortgageLoanBegin * 0.01;
            Double mhl =mlongHypothecatedLoanAdd * mlongHypothecatedLoanBegin * 0.01;
            Double mgl =mlongGuaranteeLoanAdd * mlongGuaranteeLoanBegin * 0.01;
            Double mlc =mlongLoanCreditAdd * mlongCreditLoanBegin * 0.01;
            //计划资产
            Double plannedAssets = sml+shl+sgl+slcr+da+mml+mhl+mgl+mlc;
            //获取本阶段三个实际资产（相加）
            //短期 shortLoan
            //贴现 discount
            //中长期 mlongLoan
            //当前资产
            Integer CurrentAssets = shortLoan + discount + mlongLoan;
            //实际资产
            Double ActualAssets = Double.valueOf(CurrentAssets);
            //计算公式 （计划资产—实际资产）/实际资产*100% ---评分
            Double AssetScope = (plannedAssets - ActualAssets)/ActualAssets;

            //获取----计划质量---的评分
            Double AssetScopeScore = parComprehensiveScoreDao.queryAssetScopeScore(AssetScope);
            repRankEntity.setPlanQuality(AssetScopeScore);

            //7. 净资产收益率
            //净利润／［（年初股东权益＋年末股东权益）／2］＊100％
            //获取利润表
            //获取上一阶段资产负债表
            //获取净利润   在4，中获取到了
            //retainedProfits;

            //获取年初股东权益（上一阶段资产负债）
            Integer totalShareholdersEquityBegin = repBalanceSheetVBegin.getTotalShareholdersEquity();

            //获取年末股东权益（本阶段资产负债表）  在1中取到了
            //totalShareholdersEquity;

            //计算公式--得出评分
            Double ReturnOnNetAssets = Double.valueOf(retainedProfits/((totalShareholdersEquityBegin + totalShareholdersEquity)/2));

            //获取----净资产收益率---的评分
            Double ReturnOnNetAssetsScore = parComprehensiveScoreDao.queryReturnOnNetAssetsScore(ReturnOnNetAssets);
            repRankEntity.setReturnOnNetAssets(ReturnOnNetAssetsScore);


            //8. 成本收入比
            //营业费用／营业收入

            //获取利润表

            //营业费用 --totalOperatingExpenses
            Integer totalOperatingExpenses = repProfitVO.getTotalOperatingExpenses();
            //营业收入 --totalRevenues
            Integer totalRevenues = repProfitVO.getTotalRevenues();
            //计算公式--得出评分
            Double CostIncomeRatio = Double.valueOf(totalOperatingExpenses/totalRevenues);
            // 获取----净资产收益率---的评分
            Double CostIncomeRatioScore = parComprehensiveScoreDao.queryRCostIncomeRatioScore(CostIncomeRatio);

            repRankEntity.setCostIncomeRatio(CostIncomeRatioScore);


            //9. 分红
            // 比例为学员输入的分红比例
            //净利润的20%-40%3分，10%-20%和40%-50%2分，0-10%和50%-60%1分，0以下和60%以上0分（含0%不含60%）

            //查询财务管理决策表
             DecFinancialManagementVO decFinancialManagementVO = decFinancialManagementDao.query(currentProjectId,currentTeamId,currentStageId);
            //获取输入的分红比例
            Double DividendProportion = 0.0;
            if(decFinancialManagementVO!=null){
                DividendProportion  = decFinancialManagementVO.getShareBonus() * 0.01;
            }

            // 获取----分红---的评分
            Double DividendProportionScore = parComprehensiveScoreDao.queryDividendProportionScore(DividendProportion);

            repRankEntity.setShareOutBonus(DividendProportionScore);

            //10. 资产规模  assetScale
            repRankEntity.setAssetScale(c);
            Double assetScale = repRankEntity.getAssetScale();
            i1 = i1 + 8 / (repBalanceSheetVOList.size() - 1);
            c=c-i1;

            //11. 净利润（净亏损）
            repRankEntity.setNetProfitLoss(nc);

            Double netProfitLoss = repRankEntity.getNetProfitLoss();
            i2 = i2 + 10 / (repBalanceSheetVOList.size() - 1);
            nc=nc-i2;


            //12. 不良贷款率 ---Non performing loan ratio
            Double nonPerformLoanRatioScore = 1.5;
            repRankEntity.setNonPerformingLoanRatio(nonPerformLoanRatioScore);

            //13. 不良贷款拨备覆盖率- ---NPL Provision coverage
            Double nplProvisionCoverageScore = 1.5;
            repRankEntity.setNplProvisionCoverage(nplProvisionCoverageScore);

            //14. EVA----Economic value added
            Double economicValueAddedScore = 1.5;
            repRankEntity.setEconomicValueAdded(economicValueAddedScore);

            //获取当前项目，团队，阶段id
            repRankEntity.setCurrentProjectId(currentProjectId);
            repRankEntity.setCurrentTeamId(currentTeamId);
            repRankEntity.setCurrentStageId(currentStageId);

            //总值
            repRankEntity.setTotalScore(capitalAdequacyScore+liquidityRatioScore+depositLoanRatioScore
                    +ReturnOnAssetsScore+bridgeLoan1+AssetScopeScore+ReturnOnNetAssetsScore+CostIncomeRatioScore
                    +DividendProportionScore+assetScale+netProfitLoss+nonPerformLoanRatioScore+nplProvisionCoverageScore+economicValueAddedScore);
            //总值
            repComprehensiveRankingScoreDao.insert(repRankEntity);


        }
    }

    public List<RepComprehensiveRankingScoreVO> queryTeamRankingByStageId(Long proId,Long stageId){
        List<RepComprehensiveRankingScoreVO> repComprehensiveRankingScoreVOList = repComprehensiveRankingScoreDao.queryTeamRankingByStageId(proId, stageId);
        return repComprehensiveRankingScoreVOList;
    }
}
