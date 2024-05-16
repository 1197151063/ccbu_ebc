package net.lab1024.smartadmin.module.business.report.service;

import net.lab1024.smartadmin.module.business.decision.dao.DecFinancialManagementDao;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementVO;
import net.lab1024.smartadmin.module.business.param.dao.ParStockCalculationIndexDao;
import net.lab1024.smartadmin.module.business.report.dao.*;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepStockPriceEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitVO;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${} on ${}.
 */
@Service
public class RankingStockService {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private RepBalanceSheetDao repBalanceSheetDao;
    @Autowired
    private RepCapitalAdequacyDao repCapitalAdequacyDao;
    @Autowired
    private ParStockCalculationIndexDao parStockCalculationIndexDao;
    @Autowired
    private RepLiquidityTotalityDao repLiquidityTotalityDao;
    @Autowired
    private RepProfitDao repProfitDao;
    @Autowired
    private DecFinancialManagementDao decFinancialManagementDao;
    @Autowired
    private RepStockPriceDao repStockPriceDao;

    @Async
    public void calculateStockPrice(Long currentProjectId,Long currentStageId){

        //for循环遍历所有团队
        List<Long> currentTeamIds = departmentDao.queryProject(currentProjectId);
        for ( Long currentTeamId : currentTeamIds) {
            //股价表
            RepStockPriceEntity repStockPriceEntity = new RepStockPriceEntity();

            //1. 资本充足率
            //查询资产负债表和资本充足率
            RepBalanceSheetVO repBalanceSheetVO = repBalanceSheetDao.query(currentProjectId,currentTeamId,currentStageId);
            RepCapitalAdequacyVO repCapitalAdequacyVO = repCapitalAdequacyDao.query(currentProjectId,currentTeamId,currentStageId);

            //股东权益，贷款损失准备，次级债券，加权资产总额
            Integer totalShareholdersEquity = repBalanceSheetVO.getTotalShareholdersEquity();
            Integer lossReserves = repBalanceSheetVO.getLossReserves();
            Integer paymentSubBond = repBalanceSheetVO.getPaymentSubBond();
            Integer total = repCapitalAdequacyVO.getTotal();

            //计算公式--得出评分
            Double capitalAdequacy = Double.valueOf((totalShareholdersEquity+lossReserves+paymentSubBond)/total);

            //获取----资本充足率---的分值
            Double capitalAdequacyScore = parStockCalculationIndexDao.queryCapitalAdequacy(capitalAdequacy);
            //计算--资本充足率--价格
            Double carp = (capitalAdequacyScore * 6)/100;

            repStockPriceEntity.setCapitalAdequacyRatio(carp);

            //2.资产规模
            //计算--资产规模--价格
            //Double asp = ( * 15)/100;
            Double asp = 0.2;
            repStockPriceEntity.setAssetScale(asp);


            //3.不良贷款率
            //计算--不良贷款率--价格
            //Double nplrp = ( * 6)/100;
            Double nplrp = 0.06;
            repStockPriceEntity.setNonPerformingLoanRatio(nplrp);



            //4. 流动性比率
            //查询流动性报表
            RepLiquidityTotalityVO repLiquidityTotalityVO = repLiquidityTotalityDao.query(currentProjectId,currentTeamId,currentStageId);

            //获取总体清偿力，流动性负债总计
            Integer totalitySolvency = repLiquidityTotalityVO.getTotalitySolvency();
            Integer totalCurrentLiabilities = repLiquidityTotalityVO.getTotalCurrentLiabilities();

            Double liquidityRatio = Double.valueOf(totalitySolvency/totalCurrentLiabilities);

            Double liquidityRatioScore = parStockCalculationIndexDao.queryLiquidityRatioScore(liquidityRatio);
            System.out.println(liquidityRatioScore);

            //计算--流动性比率--价格
            Double lrp = liquidityRatioScore * 15 / 100;

            repStockPriceEntity.setLiquidityRatio(lrp);




                    //5. 净资产收益率
            RepProfitVO repProfitVO = repProfitDao.query(currentProjectId,currentTeamId,currentStageId);
            //获取净利润
            Integer retainedProfits = repProfitVO.getRetainedProfits();
            //获取上一阶段资产负债表
            Long beforeCurrentStageId = null;
            RepBalanceSheetVO repBalanceSheetVBegin = null;
            beforeCurrentStageId = currentStageId - 1;
            if (beforeCurrentStageId < 2){
                repBalanceSheetVBegin = repBalanceSheetDao.queryPar(beforeCurrentStageId);
            }else{
                repBalanceSheetVBegin = repBalanceSheetDao.query(currentProjectId,currentTeamId,beforeCurrentStageId);
            }
            //获取年初股东权益（上一阶段资产负债）
            Integer totalShareholdersEquityBegin = repBalanceSheetVBegin.getTotalShareholdersEquity();
            //获取年末股东权益（本阶段资产负债表）  在1中取到了
            //totalShareholdersEquity;

            //净利润／［（年初股东权益＋年末股东权益）／2］＊100％
            //计算公式--得出评分
            Double ReturnOnNetAssets = Double.valueOf(retainedProfits/((totalShareholdersEquityBegin + totalShareholdersEquity)/2));

            //获取----净资产收益率---的评分
            Double ReturnOnNetAssetsScore = parStockCalculationIndexDao.queryReturnOnNetAssetsScore(ReturnOnNetAssets);
            //计算--净资产收益率--价格
            Double ronap = ( ReturnOnNetAssetsScore* 15)/100;

            repStockPriceEntity.setReturnOnNetAssets(ronap);



            //6. 每股收益
            //计算--每股收益--价格
            //Double pspp = ( * 25)/100;
            Double pspp = 0.3;
            repStockPriceEntity.setPerShareProfit(pspp);



            //7. 每股净资产
            //计算--资产规模--价格
            //Double psnap = ( * 15)/100;
            Double psnap = 0.1;
            repStockPriceEntity.setPerShareNetAssets(psnap);



             //8分红
            DecFinancialManagementVO decFinancialManagementVO = decFinancialManagementDao.query(currentProjectId,currentTeamId,currentStageId);
            //获取输入的分红比例
            Double DividendProportion = 0.0;
            if(decFinancialManagementVO != null){
                DividendProportion=  decFinancialManagementVO.getShareBonus() * 0.01;
            }
            // 获取----分红---的评分
            Double DividendProportionScore = parStockCalculationIndexDao.queryDividendProportionScore(DividendProportion);
            //计算--资产规模--价格
            Double sobp = ( DividendProportionScore* 10)/100;
            repStockPriceEntity.setShareOutBonus(sobp);

            //获取当前项目，团队，阶段id
            repStockPriceEntity.setCurrentProjectId(currentProjectId);
            repStockPriceEntity.setCurrentTeamId(currentTeamId);
            repStockPriceEntity.setCurrentStageId(currentStageId);

            //总数
            repStockPriceEntity.setStockPrice(carp+asp+nplrp+lrp+ronap+pspp+psnap+sobp);

            //总值
            repStockPriceDao.insert(repStockPriceEntity);

        }
    }
}
