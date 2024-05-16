package net.lab1024.smartadmin.module.business.report.service;

import net.lab1024.smartadmin.module.business.decision.dao.*;
import net.lab1024.smartadmin.module.business.decision.domain.vo.*;
import net.lab1024.smartadmin.module.business.param.dao.*;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketShareEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.*;
import net.lab1024.smartadmin.module.business.report.dao.*;
import net.lab1024.smartadmin.module.business.report.domain.entity.*;
import net.lab1024.smartadmin.module.business.report.domain.vo.*;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 预计算 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 14:33:59
 * @since JDK1.8
 */
@Service
public class PreOperationService {

    @Autowired
    private RepBalanceSheetDao repBalanceSheetDao;
    @Autowired
    private DecLoanDepositDao decLoanDepositDao;
    @Autowired
    private DecInvestmentShortDao decInvestmentShortDao;
    @Autowired
    private DecInvestmentLongDao decInvestmentLongDao;
    @Autowired
    private DecMiddleCustodyDao decMiddleCustodyDao;
    @Autowired
    private DecPersonnelDao decPersonnelDao;
    @Autowired
    private DecMarketStrategyDao decMarketStrategyDao;
    @Autowired
    private DecFinancialManagementDao decFinancialManagementDao;
    @Autowired
    private RepProfitDao repProfitDao;
    @Autowired
    private RepLiquidityCashDao repLiquidityCashDao;
    @Autowired
    private RepLiquidityTotalityDao repLiquidityTotalityDao;
    @Autowired
    private RepCapitalAdequacyDao repCapitalAdequacyDao;
    @Autowired
    private RepInvestbusOperatDataBondDao repInvestbusOperatDataBondDao;
    @Autowired
    private RepInvestbusOperatDataStockDao repInvestbusOperatDataStockDao;
    @Autowired
    private RepMarketDataCustomerAccountDao repMarketDataCustomerAccountDao;
    @Autowired
    private RepMarketDataCustomerBazaarDao repMarketDataCustomerBazaarDao;
    @Autowired
    private RepPersonnelLogisticsDataAutomationDao repPersonnelLogisticsDataAutomationDao;
    @Autowired
    private RepPeopleLogisticsDao repPeopleLogisticsDao;
    @Autowired
    private RepAgencyBondDao repAgencyBondDao;
    @Autowired
    private ParMarketSupplyDemandForecastDao parMarketSupplyDemandForecastDao;
    @Autowired
    private ParTotalMarketDao parTotalMarketDao;
    @Autowired
    private ParProjectRateDao parProjectRateDao;
    @Autowired
    private ParInitialDataPeopleLogisticsDao parInitialDataPeopleLogisticsDao;
    @Autowired
    private ParPersonnelCostDao parPersonnelCostDao;
    @Autowired
    private ParCapitalAdequacyDao parCapitalAdequacyDao;
    @Autowired
    private ParInitialDataInvestbusStockDao parInitialDataInvestbusStockDao;
    @Autowired
    private ParInitialDataInvestbusBondDao parInitialDataInvestbusBondDao;
    @Autowired
    private ParInitialDataAutomationDao parInitialDataAutomationDao;
    @Autowired
    private ParBondParameterDao parBondParameterDao;
    @Autowired
    private ParFormulaDao parFormulaDao;
    @Autowired
    private ParAgencyBondIssueDao parAgencyBondIssueDao;
    @Autowired
    private ParWastageRateDao parWastageRateDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ParGencyBondDiscountDao parGencyBondDiscountDao;
    @Autowired
    private ParIndexNumberDao parIndexNumberDao;
    @Autowired
    private ParMarketShareDao parMarketShareDao;
    @Autowired
    private ParInitialDataMarketCustomerAccountDao parInitialDataMarketCustomerAccountDao;
    @Autowired
    private RepPrecomputingNumDao repPrecomputingNumDao;

    //所有业务进行数据预计算
    public Map<String, Object> preOperation(Long currentProjectId, Long currentTeamId, Long currentStageId) {

        //查询预计算次数表
        RepPrecomputingNumEntity repPrecomputingNumEntity = new RepPrecomputingNumEntity();
        repPrecomputingNumEntity.setCurrentProjectId(currentProjectId);
        repPrecomputingNumEntity.setCurrentTeamId(currentTeamId);
        repPrecomputingNumEntity.setCurrentStageId(currentStageId);
        Integer preNum = repPrecomputingNumDao.queryPreNum(currentProjectId, currentTeamId, currentStageId);
        if (preNum == null) {
            repPrecomputingNumEntity.setPreNum(1);
            repPrecomputingNumDao.insert(repPrecomputingNumEntity);
        } else {
            repPrecomputingNumEntity.setPreNum(preNum + 1);
            repPrecomputingNumDao.updateNum(repPrecomputingNumEntity);
        }
        Map<String, Object> preMap = new HashMap<>();

//        List<Long> currentTeamIds = departmentDao.queryProject(currentProjectId);
//        for (Long currentTeamId : currentTeamIds) {
        //查询决策数据
        //存贷款
        DecLoanDepositVO decLoanDepositVO = decLoanDepositDao.query(currentProjectId, currentTeamId, currentStageId);
        if (decLoanDepositVO == null) {
            decLoanDepositVO = decLoanDepositDao.query(currentProjectId, currentTeamId, currentStageId - 1);
        }
        //投资业务
        DecInvestmentShortVO decInvestmentShortVO = decInvestmentShortDao.query(currentProjectId, currentTeamId, currentStageId);
        if (decInvestmentShortVO == null) {
            decInvestmentShortVO = decInvestmentShortDao.query(currentProjectId, currentTeamId, currentStageId - 1);
        }
        DecInvestmentLongVO decInvestmentLongSharesVO = decInvestmentLongDao.query(currentProjectId, currentTeamId, currentStageId, "G");
        if (decInvestmentLongSharesVO == null) {
            decInvestmentLongSharesVO = decInvestmentLongDao.query(currentProjectId, currentTeamId, currentStageId - 1, "G");
        }
        //债券
        List<DecInvestmentLongVO> decInvestmentLongBondVO = decInvestmentLongDao.querybond(currentProjectId, currentTeamId, currentStageId);
        if (decInvestmentLongBondVO.size()==0) {
            decInvestmentLongBondVO = decInvestmentLongDao.querybond(currentProjectId, currentTeamId, currentStageId - 1);
        }
        //中间业务
        DecMiddleCustodyVO decMiddleCustodyVO = decMiddleCustodyDao.query(currentProjectId, currentTeamId, currentStageId);
        if (decMiddleCustodyVO == null) {
            decMiddleCustodyVO = decMiddleCustodyDao.query(currentProjectId, currentTeamId, currentStageId - 1);
        }
        //人事后勤
        DecPersonnelVO decPersonnelVO = decPersonnelDao.query(currentProjectId, currentTeamId, currentStageId);
        if (decPersonnelVO == null) {
            decPersonnelVO = decPersonnelDao.query(currentProjectId, currentTeamId, currentStageId - 1);
        }
        //市场营销
        DecMarketStrategyVO decMarketStrategyVO = decMarketStrategyDao.query(currentProjectId, currentTeamId, currentStageId);
        if (decMarketStrategyVO == null) {
            decMarketStrategyVO = decMarketStrategyDao.query(currentProjectId, currentTeamId, currentStageId - 1);
        }
        //财务管理
        DecFinancialManagementVO decFinancialManagementVO = decFinancialManagementDao.query(currentProjectId, currentTeamId, currentStageId);
        if (decFinancialManagementVO == null) {
            decFinancialManagementVO = decFinancialManagementDao.query(currentProjectId, currentTeamId, currentStageId - 1);
        }
        //查询参数表数据
        //风险加权系数%查询
        ParCapitalAdequacyVO parCapitalAdequacyVO = parCapitalAdequacyDao.query();
        //市场供求预测
        List<ParMarketSupplyDemandForecastVO> query = parMarketSupplyDemandForecastDao.query(currentStageId);

        //所有团队
        Double m = 0.00;
        Double h = 0.00;
        Double g = 0.00;
        Double l = 0.00;
        Double t = 0.00;
        Double duancun = 0.00;
        Double duanchucun = 0.00;
        Double tongcunkuan = 0.00;
        Double changcun = 0.00;
        Double changchucun = 0.00;
        Double zcm = 0.00;
        Double zch = 0.00;
        Double zcg = 0.00;
        Double zcl = 0.00;
        //存贷款
        List<DecLoanDepositVO> decLoanDepositVOAlls = decLoanDepositDao.queryAllTeam(currentProjectId, currentStageId);
        if (decLoanDepositVOAlls.size() == 0) {
            decLoanDepositVOAlls = decLoanDepositDao.queryAllTeam(currentProjectId, currentStageId - 1);
        }

        for (DecLoanDepositVO decLoanDepositVO1 : decLoanDepositVOAlls) {
            m = m + decLoanDepositVO1.getShortMortgageLoanRise() * 0.01;
            h = h + decLoanDepositVO1.getShortHypothecatedLoanRise() * 0.01;
            g = g + decLoanDepositVO1.getShortGuaranteeLoanRise() * 0.01;
            l = l + decLoanDepositVO1.getShortLoanCreditRise() * 0.01;
            t = t + decLoanDepositVO1.getDiscountRise() * 0.01;
            duancun = duancun + decLoanDepositVO1.getShortDepositRise() * 0.01;
            duanchucun = duanchucun + decLoanDepositVO1.getShortSavingsDepositRise() * 0.01;
            tongcunkuan = tongcunkuan + decLoanDepositVO1.getInterbankdepositnnnRise() * 0.01;
            changcun = changcun + decLoanDepositVO1.getLongDePositRise() * 0.01;
            changchucun = changchucun + decLoanDepositVO1.getLongSavingsDepositRise() * 0.01;
            zcm = zcm + decLoanDepositVO1.getMlongMortgageLoanRise() * 0.01;
            zch = zch + decLoanDepositVO1.getMlongHypothecatedLoanRise() * 0.01;
            zcg = zcg + decLoanDepositVO1.getMlongGuaranteeLoanRise() * 0.01;
            zcl = zcl + decLoanDepositVO1.getMlongLoanCreditRise() * 0.01;
        }
        //短期贷款-利率
        Double shortMortgageLoanRise = decLoanDepositVO.getShortMortgageLoanRise() * 0.01;
        Double shortHypothecatedLoanRise = decLoanDepositVO.getShortHypothecatedLoanRise() * 0.01;
        Double shortGuaranteeLoanRise = decLoanDepositVO.getShortGuaranteeLoanRise() * 0.01;
        Double shortLoanCreditRise = decLoanDepositVO.getShortLoanCreditRise() * 0.01;
        //查询市场份额公式表
        //团队的家数
        List<Long> currentTeamIds = departmentDao.queryProject(currentProjectId);
        int TeamSize = currentTeamIds.size();
        //抵押贷款-市场份额
        Integer dd = parFormulaDao.queryParameter("短期贷款-抵押贷款");
//            Double d = ((double) 1 / TeamSize) + (1 / shortMortgageLoanRise + 1 / m / TeamSize) / dd;
        Double d = ((double) 1 / TeamSize) + (shortMortgageLoanRise - m / TeamSize) / dd;
        Integer dz = parFormulaDao.queryParameter("短期贷款-质押贷款");
//            Double z = ((double) 1 / TeamSize) + (1 / shortHypothecatedLoanRise + 1 / h / TeamSize) / dz;
        Double z = ((double) 1 / TeamSize) + (shortHypothecatedLoanRise - h / TeamSize) / dz;
        Integer db = parFormulaDao.queryParameter("短期贷款-保证贷款");
//            Double b = ((double) 1 / TeamSize) + (1 / shortGuaranteeLoanRise + 1 / g / TeamSize) / db;
        Double b = ((double) 1 / TeamSize) + (shortGuaranteeLoanRise - g / TeamSize) / db;
        Integer dx = parFormulaDao.queryParameter("短期贷款-信用贷款");
//            Double x = ((double) 1 / TeamSize) + (1 / shortLoanCreditRise + 1 / l / TeamSize) / dx;
        Double x = ((double) 1 / TeamSize) + (shortLoanCreditRise - l / TeamSize) / dx;
        Double sum = (d + z + b + x) / 4;
        //贴现-利率
        Double discountRise = decLoanDepositVO.getDiscountRise() * 0.01;
        Integer tx = parFormulaDao.queryParameter("贴现");
//            Double discountShare = ((double) 1 / TeamSize) + (1 / discountRise + 1 / t / TeamSize) / tx;
        Double discountShare = ((double) 1 / TeamSize) + (discountRise - t / TeamSize) / tx;
        //中长期贷款-利率
        Double mlongMortgageLoanRise = decLoanDepositVO.getMlongMortgageLoanRise() * 0.01;
        Double mlongHypothecatedLoanRise = decLoanDepositVO.getMlongHypothecatedLoanRise() * 0.01;
        Double mlongGuaranteeLoanRise = decLoanDepositVO.getMlongGuaranteeLoanRise() * 0.01;
        Double mlongLoanCreditRise = decLoanDepositVO.getMlongLoanCreditRise() * 0.01;

        Integer cd = parFormulaDao.queryParameter("长期贷款-抵押贷款");
//            Double zcd = ((double) 1 / TeamSize) + (1 / mlongMortgageLoanRise + 1 / zcm / TeamSize) / cd;
        Double zcd = ((double) 1 / TeamSize) + ((double) mlongMortgageLoanRise - zcm / TeamSize) / cd;
        Integer cz = parFormulaDao.queryParameter("长期贷款-质押贷款");
//            Double zcz = ((double) 1 / TeamSize) + (1 / mlongHypothecatedLoanRise + 1 / zch / TeamSize) / cz;
        Double zcz = ((double) 1 / TeamSize) + ((double) mlongHypothecatedLoanRise - zch / TeamSize) / cz;
        Integer cb = parFormulaDao.queryParameter("长期贷款-保证贷款");
//            Double zcb = ((double) 1 / TeamSize) + (1 / mlongGuaranteeLoanRise + 1 / zcg / TeamSize) / cb;
        Double zcb = ((double) 1 / TeamSize) + ((double) mlongGuaranteeLoanRise - zcg / TeamSize) / cb;
        Integer cx = parFormulaDao.queryParameter("长期贷款-信用贷款");
//            Double zcx = ((double) 1 / TeamSize) + (1 / mlongLoanCreditRise + 1 / zcl / TeamSize) / cx;
        Double zcx = ((double) 1 / TeamSize) + ((double) mlongLoanCreditRise - zcl / TeamSize) / cx;
        Double zcSum = (zcd + zcz + zcb + zcx) / 4;
        //短期存款-市场份额
        Double shortDepositRise = decLoanDepositVO.getShortDepositRise() * 0.01;
        Integer dqck = parFormulaDao.queryParameter("短期存款");
        Double shortDepositRiseShare = ((double) 1 / TeamSize) + ((double) shortDepositRise - duancun / TeamSize) / dqck;
        //短期储蓄存款-市场份额
        Double shortSavingsDepositRise = decLoanDepositVO.getShortSavingsDepositRise() * 0.01;
        Integer dqcxck = parFormulaDao.queryParameter("短期储蓄存款");
        Double shortSavingsDepositRiseShare = ((double) 1 / TeamSize) + ((double) shortSavingsDepositRise - duanchucun / TeamSize) / dqcxck;
        //同业存放款项-市场份额
        Double interbankdepositnnnRise = decLoanDepositVO.getInterbankdepositnnnRise() * 0.01;
        Integer tycfkx = parFormulaDao.queryParameter("同业存放款项");
        Double interbankdepositnnnRiseShare = ((double) 1 / TeamSize) + ((double) interbankdepositnnnRise - tongcunkuan / TeamSize) / tycfkx;
        //长期存款-市场份额
        Double longDePositRise = decLoanDepositVO.getLongDePositRise() * 0.01;
        Integer cqck = parFormulaDao.queryParameter("长期存款");
        Double longDePositRiseShare = ((double) 1 / TeamSize) + ((double) longDePositRise - changcun / TeamSize) / cqck;
        //长期储蓄存款-市场份额
        Double longDepositRise = decLoanDepositVO.getLongSavingsDepositRise() * 0.01;
        Integer cqcxck = parFormulaDao.queryParameter("长期储蓄存款");
        Double longDepositRiseShare = ((double) 1 / TeamSize) + ((double) longDepositRise - changchucun / TeamSize) / cqcxck;

        //中间业务
        Double tgyw = 0.00;
        Double dbyw = 0.00;
        List<DecMiddleCustodyVO> decMiddleCustodyVOS1 = decMiddleCustodyDao.queryData(currentProjectId, currentStageId);
        if (decMiddleCustodyVOS1.size() == 0) {
            decMiddleCustodyVOS1 = decMiddleCustodyDao.queryData(currentProjectId, currentStageId - 1);
        }
        for (DecMiddleCustodyVO decMiddleCustodyVO1 : decMiddleCustodyVOS1) {

            if (decMiddleCustodyVO1 == null) {
                decMiddleCustodyVO1.setCustodyBusinessProcedure(0.0);
                decMiddleCustodyVO1.setGuaranteeBusinessProcedure(0.0);
            } else {
                if (decMiddleCustodyVO1.getCustodyBusinessProcedure() == null) {
                    decMiddleCustodyVO1.setCustodyBusinessProcedure(0.0);
                }
                if (decMiddleCustodyVO1.getGuaranteeBusinessProcedure() == null) {
                    decMiddleCustodyVO1.setGuaranteeBusinessProcedure(0.0);
                }
            }
            tgyw = tgyw + decMiddleCustodyVO1.getCustodyBusinessProcedure() * 0.01;
            dbyw = dbyw + decMiddleCustodyVO1.getGuaranteeBusinessProcedure() * 0.01;
        }
        //委托资产托管业务-市场份额
        Double custodyBusinessProcedureRiseShare = 0.0;
        Double guaranteeBusinessProcedureRiseShare = 0.0;
        if (decMiddleCustodyVO != null) {
            if (decMiddleCustodyVO.getCustodyBusinessProcedure() == null) {
                decMiddleCustodyVO.setCustodyBusinessProcedure(0.0);
            }
            Double custodyBusinessProcedureRise = decMiddleCustodyVO.getCustodyBusinessProcedure() * 0.01;
            Integer wtzctgyw = parFormulaDao.queryParameter("委托资产托管业务");
            custodyBusinessProcedureRiseShare = ((double) 1 / TeamSize) + (custodyBusinessProcedureRise - tgyw / TeamSize) / wtzctgyw;
            //担保业务-市场份额
            if (decMiddleCustodyVO.getGuaranteeBusinessProcedure() == null) {
                decMiddleCustodyVO.setGuaranteeBusinessProcedure(0.0);
            }
            Double guaranteeBusinessProcedureRise = decMiddleCustodyVO.getGuaranteeBusinessProcedure() * 0.01;
            Integer dbyw1 = parFormulaDao.queryParameter("担保业务");
            guaranteeBusinessProcedureRiseShare = ((double) 1 / TeamSize) + (guaranteeBusinessProcedureRise - dbyw / TeamSize) / dbyw1;
        } else {
            //1,2,3阶段先按0.01和0.005 市场份额先按0.1667
            Double custodyBusinessProcedureRise = 0.01 * 0.01;
            Integer wtzctgyw = parFormulaDao.queryParameter("委托资产托管业务");
            custodyBusinessProcedureRiseShare = ((double) 1 / TeamSize) + (custodyBusinessProcedureRise - 0.1667 / TeamSize) / wtzctgyw;
            //担保业务-市场份额
            Double guaranteeBusinessProcedureRise = 0.005 * 0.01;
            Integer dbyw1 = parFormulaDao.queryParameter("担保业务");
            guaranteeBusinessProcedureRiseShare = ((double) 1 / TeamSize) + (guaranteeBusinessProcedureRise - 0.1667 / TeamSize) / dbyw1;
        }
        Map<String, Double> map = new HashMap<>();
        map.put("短期贷款", sum);
        map.put("短期贷款-抵押贷款", d);
        map.put("短期贷款-质押贷款", z);
        map.put("短期贷款-保证贷款", b);
        map.put("短期贷款-信用贷款", x);
        map.put("贴现", discountShare);
        map.put("中长期贷款", zcSum);
        map.put("长期贷款-抵押贷款", zcd);
        map.put("长期贷款-质押贷款", zcz);
        map.put("长期贷款-保证贷款", zcb);
        map.put("长期贷款-信用贷款", zcx);
        map.put("短期存款", shortDepositRiseShare);
        map.put("短期储蓄存款", shortSavingsDepositRiseShare);
        map.put("同业存放款项", interbankdepositnnnRiseShare);
        map.put("长期存款", longDePositRiseShare);
        map.put("长期储蓄存款", longDepositRiseShare);
        map.put("委托资产托管业务", custodyBusinessProcedureRiseShare);
        map.put("担保业务", guaranteeBusinessProcedureRiseShare);

        for (String key : map.keySet()) {
            ParMarketShareEntity parMarketShareEntity = new ParMarketShareEntity();
            parMarketShareEntity.setCurrentProjectId(currentProjectId);
            parMarketShareEntity.setCurrentTeamId(currentTeamId);
            parMarketShareEntity.setCurrentStageId(currentStageId);
            parMarketShareEntity.setBusiness(key);
            parMarketShareEntity.setMarketShare(map.get(key));
//                parMarketShareDao.insert(parMarketShareEntity);
            //市场份额表
            preMap.put("marketShare", parMarketShareEntity);
        }

        //上期填入数
        RepBalanceSheetVO repBalanceSheetVO = null;
        RepProfitVO repProfitVO = null;
        List<RepPeopleLogisticsVO> repPeopleLogisticsVOs = null;
        RepPersonnelLogisticsDataAutomationVO repPersonnelLogisticsDataAutomationVO = null;
//        List<RepInvestbusOperatDataStockVO> repInvestbusOperatDataStockVOs =null;

        if (currentStageId <= 2) {
            repBalanceSheetVO = repBalanceSheetDao.selectBalanceResultPar(currentStageId - 1);
            repProfitVO = repProfitDao.selectProfitStageIdPar(currentStageId - 1);
//            repInvestbusOperatDataStockVOs = repInvestbusOperatDataStockDao.selectInvestbusOperatDataStockPar(currentStageId - 1);
        } else {
            repBalanceSheetVO = repBalanceSheetDao.selectBalanceResultRep(currentTeamId, currentStageId - 1);
            repProfitVO = repProfitDao.selectProfitStageIdRep(currentTeamId, currentStageId - 1);
//            repInvestbusOperatDataStockVOs = repInvestbusOperatDataStockDao.selectInvestbusOperatDataStockRep(currentTeamId, currentStageId - 1);
        }
        if (currentStageId <= 3) {
            repPeopleLogisticsVOs = repPeopleLogisticsDao.selectPeoplePar(1L);
            repPersonnelLogisticsDataAutomationVO = repPersonnelLogisticsDataAutomationDao.selectPerLogDataAutomationPar(1L);
        } else {
            repPeopleLogisticsVOs = repPeopleLogisticsDao.selectPeopleRep(currentTeamId, currentStageId - 1);
            repPersonnelLogisticsDataAutomationVO = repPersonnelLogisticsDataAutomationDao.selectPerLogDataAutomationRep(currentTeamId, currentStageId - 1);
        }


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //投资业务营业数据表 2阶段开启 但是一直存在
        //股票数量（手）= 股票价值*1000（百万）/股票指数
        Double shareIndexBefore = parProjectRateDao.queryInterestRate(currentStageId - 1, "股票指数");
        Double shareIndex = parProjectRateDao.queryInterestRate(currentStageId, "股票指数");

        //股票期初持有短期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntity = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntity.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntity.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntity.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntity.setStockProject("期初持有");
        repInvestbusOperatDataStockEntity.setStockIndex(shareIndexBefore);
        repInvestbusOperatDataStockEntity.setStockType("短期");
        if (currentStageId == 2) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(currentStageId - 1, "期末持有", "短期");
            repInvestbusOperatDataStockEntity.setStockNumber(parInitialDataInvestbusStockVO.getStockNumber());
            repInvestbusOperatDataStockEntity.setStockCost(parInitialDataInvestbusStockVO.getStockCost());
            repInvestbusOperatDataStockEntity.setStockDividend(parInitialDataInvestbusStockVO.getStockDividend());
            repInvestbusOperatDataStockEntity.setStockAccount(parInitialDataInvestbusStockVO.getStockAccount());
        } else {
            RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO = repInvestbusOperatDataStockDao.selectInvestbusOperatData(currentTeamId, currentStageId - 1, "期末持有", "短期");
            repInvestbusOperatDataStockEntity.setStockNumber(repInvestbusOperatDataStockVO.getStockNumber());
            repInvestbusOperatDataStockEntity.setStockCost(repInvestbusOperatDataStockVO.getStockCost());
            repInvestbusOperatDataStockEntity.setStockDividend(0);
            repInvestbusOperatDataStockEntity.setStockAccount(0);
        }
        Integer stockNumber = repInvestbusOperatDataStockEntity.getStockNumber();
        Integer stockCost = repInvestbusOperatDataStockEntity.getStockCost();
        Integer stockAccount = repInvestbusOperatDataStockEntity.getStockAccount();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntity);

        //投资业务营业数据表-短期股票-期初持有
        preMap.put("investmentsShortBegin", repInvestbusOperatDataStockEntity);

        //股票期初持有长期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntityLong = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntityLong.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntityLong.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntityLong.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntityLong.setStockProject("期初持有");
        repInvestbusOperatDataStockEntityLong.setStockIndex(shareIndexBefore);
        repInvestbusOperatDataStockEntityLong.setStockType("长期");
        if (currentStageId == 2) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(currentStageId - 1, "期末持有", "长期");
            repInvestbusOperatDataStockEntityLong.setStockNumber(parInitialDataInvestbusStockVO.getStockNumber());
            repInvestbusOperatDataStockEntityLong.setStockCost(parInitialDataInvestbusStockVO.getStockCost());
            repInvestbusOperatDataStockEntityLong.setStockDividend(parInitialDataInvestbusStockVO.getStockDividend());
            repInvestbusOperatDataStockEntityLong.setStockAccount(parInitialDataInvestbusStockVO.getStockAccount());
        } else {
            RepInvestbusOperatDataStockVO repInvestbusOperatDataStockVO = repInvestbusOperatDataStockDao.selectInvestbusOperatData(currentTeamId, currentStageId - 1, "期末持有", "长期");
            repInvestbusOperatDataStockEntityLong.setStockNumber(repInvestbusOperatDataStockVO.getStockNumber());
            repInvestbusOperatDataStockEntityLong.setStockCost(repInvestbusOperatDataStockVO.getStockCost());
            repInvestbusOperatDataStockEntityLong.setStockDividend(0);
            repInvestbusOperatDataStockEntityLong.setStockAccount(0);
        }
        Integer stockNumberLong = repInvestbusOperatDataStockEntityLong.getStockNumber();
        Integer stockCostLong = repInvestbusOperatDataStockEntityLong.getStockCost();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntityLong);

        //投资业务营业数据表-长期股票-期初持有
        preMap.put("investmentsLongBegin", repInvestbusOperatDataStockEntityLong);

        //股票最新持有短期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntity1 = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntity1.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntity1.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntity1.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntity1.setStockProject("最新持有");
        repInvestbusOperatDataStockEntity1.setStockIndex(shareIndex);
        repInvestbusOperatDataStockEntity1.setStockType("短期");
        repInvestbusOperatDataStockEntity1.setStockNumber(stockNumber);
        repInvestbusOperatDataStockEntity1.setStockCost((int) Math.round((shareIndex * stockNumber * 0.000001)));
        //股息
        repInvestbusOperatDataStockEntity1.setStockDividend(0);
        repInvestbusOperatDataStockEntity1.setStockAccount((int) Math.round((shareIndex * stockNumber * 0.000001 - stockCost)));
        Integer stockAccount1 = repInvestbusOperatDataStockEntity1.getStockAccount();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntity1);

        //投资业务营业数据表-短期股票-最新持有
        preMap.put("investmentsShortNewest", repInvestbusOperatDataStockEntity1);

        //股票最新持有长期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntityLong1 = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntityLong1.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntityLong1.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntityLong1.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntityLong1.setStockProject("最新持有");
        repInvestbusOperatDataStockEntityLong1.setStockIndex(shareIndex);
        repInvestbusOperatDataStockEntityLong1.setStockType("长期");
        repInvestbusOperatDataStockEntityLong1.setStockNumber(stockNumberLong);
        Integer stockNumber1 = repInvestbusOperatDataStockEntityLong1.getStockNumber();
        repInvestbusOperatDataStockEntityLong1.setStockCost((int) Math.round((shareIndex * stockNumberLong * 0.000001)));
        Integer stockCost1 = repInvestbusOperatDataStockEntityLong1.getStockCost();
        //股息
        repInvestbusOperatDataStockEntityLong1.setStockDividend(0);
        repInvestbusOperatDataStockEntityLong1.setStockAccount(stockCost1 - stockCostLong);
        Integer stockAccountLong1 = repInvestbusOperatDataStockEntityLong1.getStockAccount();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntityLong1);

        //投资业务营业数据表-长期股票-最新持有
        preMap.put("investmentsLongNewest", repInvestbusOperatDataStockEntityLong1);

        //股票净买入/卖出短期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntity2 = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntity2.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntity2.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntity2.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntity2.setStockProject("净买入/卖出");
        repInvestbusOperatDataStockEntity2.setStockIndex(shareIndex);
        repInvestbusOperatDataStockEntity2.setStockType("短期");
        if (decInvestmentShortVO == null) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(1L, "期末持有", "短期");
            repInvestbusOperatDataStockEntity2.setStockNumber((int) Math.round(((long) parInitialDataInvestbusStockVO.getStockCost() * 1000000000 / shareIndex * 0.001)));
        } else {
            if (decInvestmentShortVO.getInvestmentStock() != null) {
                repInvestbusOperatDataStockEntity2.setStockNumber((int) Math.round(((long) decInvestmentShortVO.getInvestmentStock() * 1000000000 / shareIndex * 0.001)));
            } else {
                repInvestbusOperatDataStockEntity2.setStockNumber(0);
            }
        }
        Integer stockNumber2 = repInvestbusOperatDataStockEntity2.getStockNumber();
        if (decInvestmentShortVO == null) {
            repInvestbusOperatDataStockEntity2.setStockCost(0);
        } else {
            if (decInvestmentShortVO.getInvestmentStock() == null) {
                repInvestbusOperatDataStockEntity2.setStockCost(0);
            } else {
                repInvestbusOperatDataStockEntity2.setStockCost(decInvestmentShortVO.getInvestmentStock());
            }
        }
        Integer stockCost2 = repInvestbusOperatDataStockEntity2.getStockCost();
        //股息
        repInvestbusOperatDataStockEntity2.setStockDividend(0);
        repInvestbusOperatDataStockEntity2.setStockAccount(0);
        Integer stockAccount2 = repInvestbusOperatDataStockEntity2.getStockAccount();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntity2);

        //投资业务营业数据表-短期股票-净买入/卖出
        preMap.put("investmentsShortBuy", repInvestbusOperatDataStockEntity2);

        //股票净买入/卖出长期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntityLong2 = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntityLong2.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntityLong2.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntityLong2.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntityLong2.setStockProject("净买入/卖出");
        repInvestbusOperatDataStockEntityLong2.setStockIndex(shareIndex);
        repInvestbusOperatDataStockEntityLong2.setStockType("长期");
        repInvestbusOperatDataStockEntityLong2.setStockNumber(stockNumberLong);
        Integer stockNumberLong2 = repInvestbusOperatDataStockEntityLong2.getStockNumber();
        DecInvestmentLongVO decInvestmentLongVO = decInvestmentLongDao.query(currentProjectId, currentTeamId, currentStageId, "G");
        if (decInvestmentLongVO == null) {
            ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, "A", "短期");
            repInvestbusOperatDataStockEntityLong2.setStockCost(parInitialDataInvestbusBondVO.getNominalValue());
        } else {
            repInvestbusOperatDataStockEntityLong2.setStockCost(decInvestmentLongVO.getBuySell());
        }
        Integer stockCostLong2 = repInvestbusOperatDataStockEntityLong2.getStockCost();
        //股息
        repInvestbusOperatDataStockEntityLong2.setStockDividend(0);
        repInvestbusOperatDataStockEntityLong2.setStockAccount(0);
        Integer stockAccountLong2 = repInvestbusOperatDataStockEntityLong2.getStockAccount();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntityLong2);

        //投资业务营业数据表-长期股票-净买入/卖出
        preMap.put("investmentsLongBuy", repInvestbusOperatDataStockEntityLong2);

        //股票期末持有短期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntity3 = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntity3.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntity3.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntity3.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntity3.setStockProject("期末持有");
        repInvestbusOperatDataStockEntity3.setStockIndex(shareIndex);
        repInvestbusOperatDataStockEntity3.setStockType("短期");
        repInvestbusOperatDataStockEntity3.setStockNumber(stockNumber2);
        repInvestbusOperatDataStockEntity3.setStockCost(stockCost2);
        //股息
        repInvestbusOperatDataStockEntity3.setStockDividend((int) Math.round(stockNumber2 * 0.00001));
        //最新持有-期初持有
        repInvestbusOperatDataStockEntity3.setStockAccount(stockAccount1 + stockAccount2);
        Integer stockAccount3 = repInvestbusOperatDataStockEntity3.getStockAccount();
//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntity3);

        //投资业务营业数据表-短期股票-期末持有
        preMap.put("investmentsShortEnd", repInvestbusOperatDataStockEntity3);

        //股票期末持有长期
        RepInvestbusOperatDataStockEntity repInvestbusOperatDataStockEntityLong3 = new RepInvestbusOperatDataStockEntity();
        repInvestbusOperatDataStockEntityLong3.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataStockEntityLong3.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataStockEntityLong3.setCurrentStageId(currentStageId);
        repInvestbusOperatDataStockEntityLong3.setStockProject("期末持有");
        repInvestbusOperatDataStockEntityLong3.setStockIndex(shareIndex);
        repInvestbusOperatDataStockEntityLong3.setStockType("长期");
        repInvestbusOperatDataStockEntityLong3.setStockNumber(stockNumber1 + stockNumberLong2);
        Integer stockNumber3 = repInvestbusOperatDataStockEntityLong3.getStockNumber();
        repInvestbusOperatDataStockEntityLong3.setStockCost(stockCost1 + stockCostLong2);
        //股息
        repInvestbusOperatDataStockEntityLong3.setStockDividend((int) Math.round(stockNumber3 * 0.00001));
        repInvestbusOperatDataStockEntityLong3.setStockAccount(stockAccountLong1 + stockAccountLong2);
        Integer stockAccount4 = repInvestbusOperatDataStockEntityLong3.getStockAccount();

//            repInvestbusOperatDataStockDao.insert(repInvestbusOperatDataStockEntityLong3);

        //投资业务营业数据表-长期股票-期末持有
        preMap.put("investmentsLongEnd", repInvestbusOperatDataStockEntityLong3);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //投资业务营业数据表-短期债券
        RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity = new RepInvestbusOperatDataBondEntity();
        repInvestbusOperatDataBondEntity.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataBondEntity.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataBondEntity.setCurrentStageId(currentStageId);

        //查询参数表价格%

        List<ParBondParameterVO> parBondParameterVOS = parBondParameterDao.queryPriceData(currentStageId);
        Integer nominalValueSum = 0;
        Integer realValueSum = 0;
        Integer interestSum = 0;
        Integer accountProfitLossSum = 0;
        for (ParBondParameterVO parBondParameterVO : parBondParameterVOS) {
            //债券种类(来自各个阶段)
            repInvestbusOperatDataBondEntity.setBondKind(parBondParameterVO.getBondParameter());
            String bondKind = repInvestbusOperatDataBondEntity.getBondKind();
            //价格(%)
            repInvestbusOperatDataBondEntity.setBondPrice(parBondParameterVO.getBondPrice());
            Double bondPrice = repInvestbusOperatDataBondEntity.getBondPrice();

            //债券投资类型(长期或短期)
            repInvestbusOperatDataBondEntity.setBondType("短期");
            String bondType = repInvestbusOperatDataBondEntity.getBondType();

            //名义价值
            //判断是否为集合中最后一个对象
            if (parBondParameterVOS.size() - 1 == parBondParameterVOS.indexOf(parBondParameterVO)) {
                if (decInvestmentShortVO == null) {
                    repInvestbusOperatDataBondEntity.setNominalValue(0);
                } else {
                    if (decInvestmentShortVO.getInvestmentStock() != null) {
                        repInvestbusOperatDataBondEntity.setNominalValue(decInvestmentShortVO.getNvestmentBond());
                    } else {
                        repInvestbusOperatDataBondEntity.setNominalValue(0);
                    }
                }
            } else {
                repInvestbusOperatDataBondEntity.setNominalValue(0);

            }
            Integer nominalValue = repInvestbusOperatDataBondEntity.getNominalValue();

            //总计的名义价值循环
            nominalValueSum = nominalValueSum + nominalValue;

            if ("A".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(1L, "短期债券投资");
                repInvestbusOperatDataBondEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("B".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(2L, "短期债券投资");
                repInvestbusOperatDataBondEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("C".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(3L, "短期债券投资");
                repInvestbusOperatDataBondEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("D".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(4L, "短期债券投资");
                repInvestbusOperatDataBondEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("E".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(5L, "短期债券投资");
                repInvestbusOperatDataBondEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("F".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(6L, "短期债券投资");
                repInvestbusOperatDataBondEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }

            Double rateInterest = repInvestbusOperatDataBondEntity.getRateInterest();

            //实际价值
            repInvestbusOperatDataBondEntity.setRealValue((int) Math.round(nominalValue * bondPrice * 0.01));
            Integer realValue = repInvestbusOperatDataBondEntity.getRealValue();
            realValueSum = realValueSum + realValue;

            //利息=实际价值*利率
            repInvestbusOperatDataBondEntity.setInterest((int) Math.round(nominalValue * rateInterest * 0.01));
            Integer interest = repInvestbusOperatDataBondEntity.getInterest();
            interestSum = interestSum + interest;
            //查询上阶段价格和名义价值
            RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondPriceValueRep(currentTeamId, currentStageId - 1, bondKind, bondType);

            //账面损益=当前阶段的这种债券的价格-上阶段的这种债券的价格*上阶段的名义价值
            if (repInvestbusOperatDataBondVO == null) {
                //查询零阶段表,有误
                ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, bondKind, "短期");
                if (parInitialDataInvestbusBondVO == null) {
                    repInvestbusOperatDataBondEntity.setAccountProfitLoss(0);
                } else {
                    repInvestbusOperatDataBondEntity.setAccountProfitLoss((int) Math.round(((double) bondPrice * 0.01 - parInitialDataInvestbusBondVO.getBondPrice() * 0.01) * parInitialDataInvestbusBondVO.getNominalValue()));
                }
//                    //名义价值为0，故账面损益为0
//                    repInvestbusOperatDataBondEntity.setAccountProfitLoss(0);
            } else {
                repInvestbusOperatDataBondEntity.setAccountProfitLoss((int) Math.round(((double) bondPrice * 0.01 - repInvestbusOperatDataBondVO.getBondPrice() * 0.01) * repInvestbusOperatDataBondVO.getNominalValue()));
            }
            Integer accountProfitLoss = repInvestbusOperatDataBondEntity.getAccountProfitLoss();
            accountProfitLossSum = accountProfitLossSum + accountProfitLoss;
//                repInvestbusOperatDataBondDao.insert(repInvestbusOperatDataBondEntity);
            //投资业务营业数据表-短期债券
            if ("A".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity0 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondEntity, repInvestbusOperatDataBondEntity0);
                preMap.put("investmentsShortBondA", repInvestbusOperatDataBondEntity0);
            }
            if ("B".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity1 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondEntity, repInvestbusOperatDataBondEntity1);
                preMap.put("investmentsShortBondB", repInvestbusOperatDataBondEntity1);
            }
            if ("C".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity2 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondEntity, repInvestbusOperatDataBondEntity2);
                preMap.put("investmentsShortBondC", repInvestbusOperatDataBondEntity2);
            }
            if ("D".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity3 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondEntity, repInvestbusOperatDataBondEntity3);
                preMap.put("investmentsShortBondD", repInvestbusOperatDataBondEntity3);
            }
            if ("E".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity4 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondEntity, repInvestbusOperatDataBondEntity4);
                preMap.put("investmentsShortBondE", repInvestbusOperatDataBondEntity4);
            }
            if ("F".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity5 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondEntity, repInvestbusOperatDataBondEntity5);
                preMap.put("investmentsShortBondF", repInvestbusOperatDataBondEntity5);
            }
        }
        RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntity6 = new RepInvestbusOperatDataBondEntity();
        repInvestbusOperatDataBondEntity6.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataBondEntity6.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataBondEntity6.setCurrentStageId(currentStageId);
        repInvestbusOperatDataBondEntity6.setBondKind("");
        repInvestbusOperatDataBondEntity6.setBondPrice(0.0D);
        repInvestbusOperatDataBondEntity6.setBondType("");
        repInvestbusOperatDataBondEntity6.setRealValue(0);
        repInvestbusOperatDataBondEntity6.setNominalValue(0);
        repInvestbusOperatDataBondEntity6.setRateInterest(0.0D);
        repInvestbusOperatDataBondEntity6.setInterest(0);
        repInvestbusOperatDataBondEntity6.setAccountProfitLoss(0);
        if (preMap.get("investmentsShortBondA") == null) {
            preMap.put("investmentsShortBondA", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsShortBondB") == null) {
            preMap.put("investmentsShortBondB", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsShortBondC") == null) {
            preMap.put("investmentsShortBondC", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsShortBondD") == null) {
            preMap.put("investmentsShortBondD", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsShortBondE") == null) {
            preMap.put("investmentsShortBondE", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsShortBondF") == null) {
            preMap.put("investmentsShortBondF", repInvestbusOperatDataBondEntity6);
        }

        //短期债券投资总和
        RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondEntityNum = new RepInvestbusOperatDataBondEntity();
        List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondVOS = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondNum(currentProjectId, currentTeamId, currentStageId, "短期");
        if (repInvestbusOperatDataBondVOS.size() == 0) {
            repInvestbusOperatDataBondVOS = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondNum(currentProjectId, currentTeamId, currentStageId - 1, "短期");
        }

//        for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO : repInvestbusOperatDataBondVOS) {
//            realValue = repInvestbusOperatDataBondVO.getRealValue() + realValue;
//            nominalValue = repInvestbusOperatDataBondVO.getNominalValue() + nominalValue;
//            interest = repInvestbusOperatDataBondVO.getInterest() + interest;
//            accountProfitLoss = repInvestbusOperatDataBondVO.getAccountProfitLoss() + accountProfitLoss;
//        }
        repInvestbusOperatDataBondEntityNum.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataBondEntityNum.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataBondEntityNum.setCurrentStageId(currentStageId);
        repInvestbusOperatDataBondEntityNum.setBondKind("总计");
        repInvestbusOperatDataBondEntityNum.setBondPrice(null);
        repInvestbusOperatDataBondEntityNum.setBondType("短期");
        repInvestbusOperatDataBondEntityNum.setRealValue(realValueSum);
        repInvestbusOperatDataBondEntityNum.setNominalValue(nominalValueSum);
        repInvestbusOperatDataBondEntityNum.setRateInterest(null);
        repInvestbusOperatDataBondEntityNum.setInterest(interestSum);
        repInvestbusOperatDataBondEntityNum.setAccountProfitLoss(accountProfitLossSum);
        Integer accountProfitLoss1 = repInvestbusOperatDataBondEntityNum.getAccountProfitLoss();
//            repInvestbusOperatDataBondDao.insert(repInvestbusOperatDataBondEntityNum);

        //投资业务营业数据表-短期债券-总计
        preMap.put("investmentsShortBondTotal", repInvestbusOperatDataBondEntityNum);

        //投资业务营业数据表-长期债券
        RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity = new RepInvestbusOperatDataBondEntity();
        repInvestbusOperatDataBondLongEntity.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataBondLongEntity.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataBondLongEntity.setCurrentStageId(currentStageId);

        Integer nominalLongValueSum = 0;
        Integer realValueLongSum = 0;
        Integer interestLongSum = 0;
        Integer accountProfitLossLongSum = 0;
        for (ParBondParameterVO parBondParameterVO : parBondParameterVOS) {
            //债券种类(来自各个阶段)
            repInvestbusOperatDataBondLongEntity.setBondKind(parBondParameterVO.getBondParameter());
            String bondKind = repInvestbusOperatDataBondLongEntity.getBondKind();
            //价格(%)
            repInvestbusOperatDataBondLongEntity.setBondPrice(parBondParameterVO.getBondPrice());
            Double bondPrice = repInvestbusOperatDataBondLongEntity.getBondPrice();

            //债券投资类型(长期或短期)
            repInvestbusOperatDataBondLongEntity.setBondType("长期");
            String bondType = repInvestbusOperatDataBondLongEntity.getBondType();

            //名义价值
//            if (currentStageId - 1 != parBondParameterVOS.size()) {
//                repInvestbusOperatDataBondLongEntity.setNominalValue(0);
//            } else {
//                repInvestbusOperatDataBondLongEntity.setNominalValue(decInvestmentShortVO.getNvestmentBond());
//            }
            if (decInvestmentLongBondVO.size() != 0) {
                for (int i = 0; i < decInvestmentLongBondVO.size(); i++) {
                    if (parBondParameterVO.getBondParameter().equals(decInvestmentLongBondVO.get(i).getBondType())) {
                        repInvestbusOperatDataBondLongEntity.setNominalValue(decInvestmentLongBondVO.get(i).getBuySell());
                    }
                }
            } else {
                repInvestbusOperatDataBondLongEntity.setNominalValue(0);
            }
            Integer nominalLongValue = repInvestbusOperatDataBondLongEntity.getNominalValue();

            if (nominalLongValue != null) {
                nominalLongValueSum = nominalLongValueSum + nominalLongValue;
            } else {
                nominalLongValue = 0;
                nominalLongValueSum = nominalLongValueSum + 0;
            }

            //利率
            if ("A".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(1L, "长期债券投资");
                repInvestbusOperatDataBondLongEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("B".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(2L, "长期债券投资");
                repInvestbusOperatDataBondLongEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("C".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(3L, "长期债券投资");
                repInvestbusOperatDataBondLongEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("D".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(4L, "长期债券投资");
                repInvestbusOperatDataBondLongEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("E".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(5L, "长期债券投资");
                repInvestbusOperatDataBondLongEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            if ("F".equals(parBondParameterVO.getBondParameter())) {
                ParProjectRateVO parProjectRateVO = parProjectRateDao.queryInterestStageRate(6L, "长期债券投资");
                repInvestbusOperatDataBondLongEntity.setRateInterest(parProjectRateVO.getInterestRate());
            }
            Double rateInterest = repInvestbusOperatDataBondLongEntity.getRateInterest();

            //实际价值
            repInvestbusOperatDataBondLongEntity.setRealValue((int) Math.round(nominalLongValue * bondPrice * 0.01));
            Integer realValue = repInvestbusOperatDataBondLongEntity.getRealValue();
            realValueLongSum = realValueLongSum + realValue;
            //利息=实际价值*利率
            repInvestbusOperatDataBondLongEntity.setInterest((int) Math.round(nominalLongValue * rateInterest * 0.01));
            Integer interest = repInvestbusOperatDataBondLongEntity.getInterest();
            interestLongSum = interestLongSum + interest;
            //查询上阶段价格和名义价值
            RepInvestbusOperatDataBondVO repInvestbusOperatDataBondVO = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondPriceValueRep(currentTeamId, currentStageId - 1, bondKind, bondType);
            //账面损益=当前阶段的这种债券的价格-上阶段的这种债券的价格*上阶段的名义价值
            if (repInvestbusOperatDataBondVO == null) {
                ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, bondKind, "长期");
                if (parInitialDataInvestbusBondVO == null) {
                    repInvestbusOperatDataBondLongEntity.setAccountProfitLoss(0);
                } else {
                    repInvestbusOperatDataBondLongEntity.setAccountProfitLoss((int) Math.round(((double) bondPrice * 0.01 - parInitialDataInvestbusBondVO.getBondPrice() * 0.01) * parInitialDataInvestbusBondVO.getNominalValue()));
                }
            } else {
                repInvestbusOperatDataBondLongEntity.setAccountProfitLoss((int) Math.round(((double) bondPrice * 0.01 - repInvestbusOperatDataBondVO.getBondPrice() * 0.01) * repInvestbusOperatDataBondVO.getNominalValue()));
            }
            Integer accountProfitLoss = repInvestbusOperatDataBondLongEntity.getAccountProfitLoss();
            accountProfitLossLongSum = accountProfitLossLongSum + accountProfitLoss;
//                repInvestbusOperatDataBondDao.insert(repInvestbusOperatDataBondLongEntity);
            //投资业务营业数据表-长期债券
            if ("A".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity0 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondLongEntity, repInvestbusOperatDataBondLongEntity0);
                preMap.put("investmentsLongBondA", repInvestbusOperatDataBondLongEntity0);
            }
            if ("B".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity1 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondLongEntity, repInvestbusOperatDataBondLongEntity1);
                preMap.put("investmentsLongBondB", repInvestbusOperatDataBondLongEntity1);
            }
            if ("C".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity2 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondLongEntity, repInvestbusOperatDataBondLongEntity2);
                preMap.put("investmentsLongBondC", repInvestbusOperatDataBondLongEntity2);
            }
            if ("D".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity3 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondLongEntity, repInvestbusOperatDataBondLongEntity3);
                preMap.put("investmentsLongBondD", repInvestbusOperatDataBondLongEntity3);
            }
            if ("E".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity4 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondLongEntity, repInvestbusOperatDataBondLongEntity4);
                preMap.put("investmentsLongBondE", repInvestbusOperatDataBondLongEntity4);
            }
            if ("F".equals(parBondParameterVO.getBondParameter())) {
                RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntity5 = new RepInvestbusOperatDataBondEntity();
                BeanUtils.copyProperties(repInvestbusOperatDataBondLongEntity, repInvestbusOperatDataBondLongEntity5);
                preMap.put("investmentsLongBondF", repInvestbusOperatDataBondLongEntity5);
            }
        }
        if (preMap.get("investmentsLongBondA") == null) {
            preMap.put("investmentsLongBondA", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsLongBondB") == null) {
            preMap.put("investmentsLongBondB", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsLongBondC") == null) {
            preMap.put("investmentsLongBondC", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsLongBondD") == null) {
            preMap.put("investmentsLongBondD", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsLongBondE") == null) {
            preMap.put("investmentsLongBondE", repInvestbusOperatDataBondEntity6);
        }
        if (preMap.get("investmentsLongBondF") == null) {
            preMap.put("investmentsLongBondF", repInvestbusOperatDataBondEntity6);
        }

        //长期债券投资总和
        RepInvestbusOperatDataBondEntity repInvestbusOperatDataBondLongEntityNum = new RepInvestbusOperatDataBondEntity();
        List<RepInvestbusOperatDataBondVO> repInvestbusOperatDataBondLongVOS = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondNum(currentProjectId, currentTeamId, currentStageId, "长期");
        if (repInvestbusOperatDataBondLongVOS.size() == 0) {
            repInvestbusOperatDataBondLongVOS = repInvestbusOperatDataBondDao.selectInvestbusOperatDataBondNum(currentProjectId, currentTeamId, currentStageId - 1, "长期");
        }

//        for (RepInvestbusOperatDataBondVO repInvestbusOperatDataBondLongVO : repInvestbusOperatDataBondLongVOS) {
//            realValue = repInvestbusOperatDataBondLongVO.getRealValue() + realValue;
//            nominalValue = repInvestbusOperatDataBondLongVO.getNominalValue() + nominalValue;
//            interest = repInvestbusOperatDataBondLongVO.getInterest() + interest;
//            accountProfitLoss = repInvestbusOperatDataBondLongVO.getAccountProfitLoss() + accountProfitLoss;
//        }
        repInvestbusOperatDataBondLongEntityNum.setCurrentProjectId(currentProjectId);
        repInvestbusOperatDataBondLongEntityNum.setCurrentTeamId(currentTeamId);
        repInvestbusOperatDataBondLongEntityNum.setCurrentStageId(currentStageId);
        repInvestbusOperatDataBondLongEntityNum.setBondKind("总计");
        repInvestbusOperatDataBondLongEntityNum.setBondPrice(null);
        repInvestbusOperatDataBondLongEntityNum.setBondType("长期");
        repInvestbusOperatDataBondLongEntityNum.setRealValue(realValueLongSum);
        repInvestbusOperatDataBondLongEntityNum.setNominalValue(nominalLongValueSum);
        repInvestbusOperatDataBondLongEntityNum.setRateInterest(null);
        repInvestbusOperatDataBondLongEntityNum.setInterest(interestLongSum);
        repInvestbusOperatDataBondLongEntityNum.setAccountProfitLoss(accountProfitLossLongSum);
        Integer accountProfitLoss2 = repInvestbusOperatDataBondLongEntityNum.getAccountProfitLoss();
//            repInvestbusOperatDataBondDao.insert(repInvestbusOperatDataBondLongEntityNum);

        //投资业务营业数据表-长期债券-总计
        preMap.put("investmentsLongBondTotal", repInvestbusOperatDataBondLongEntityNum);

        //业务计算后插入数据库
        //资产负债表
        RepBalanceSheetEntity repBalanceSheetEntity = new RepBalanceSheetEntity();
        repBalanceSheetEntity.setCurrentProjectId(currentProjectId);
        repBalanceSheetEntity.setCurrentTeamId(currentTeamId);
        repBalanceSheetEntity.setCurrentStageId(currentStageId);

        //存放中央银行款项
        repBalanceSheetEntity.setDueCentralBank(decLoanDepositVO.getDueCentralBank());
        Integer dueCentralBank = repBalanceSheetEntity.getDueCentralBank();

        //存放同业款项
        repBalanceSheetEntity.setDepositInterbank(decLoanDepositVO.getInterbankDeposit());
        Integer depositInterbank = repBalanceSheetEntity.getDepositInterbank();

        //拆放同业
        repBalanceSheetEntity.setLoanTrade(decLoanDepositVO.getLoanTrade());
        Integer loanTrade = repBalanceSheetEntity.getLoanTrade();

        //市场总额查询
        Integer shortTermLoan = parTotalMarketDao.queryAmount(currentStageId, "短期贷款");
        Integer shortMortgage = parTotalMarketDao.queryAmount(currentStageId, "短期贷款-抵押贷款");
        Integer shortPledgeLoans = parTotalMarketDao.queryAmount(currentStageId, "短期贷款-质押贷款");
        Integer shortGuaranteedLoan = parTotalMarketDao.queryAmount(currentStageId, "短期贷款-保证贷款");
        Integer shortCreditLoan = parTotalMarketDao.queryAmount(currentStageId, "短期贷款-信用贷款");

        Integer amount = parTotalMarketDao.queryAmount(currentStageId, "贴现");
        Integer mlongTermLoan = parTotalMarketDao.queryAmount(currentStageId, "中长期贷款");
        Integer mlongMortgage = parTotalMarketDao.queryAmount(currentStageId, "中长期贷款-抵押贷款");
        Integer mlongPledgeLoans = parTotalMarketDao.queryAmount(currentStageId, "中长期贷款-质押贷款");
        Integer mlongGuaranteedLoan = parTotalMarketDao.queryAmount(currentStageId, "中长期贷款-保证贷款");
        Integer mlongCreditLoan = parTotalMarketDao.queryAmount(currentStageId, "中长期贷款-信用贷款");

        Integer custodyBusinessProcedureLoan = parTotalMarketDao.queryAmount(currentStageId, "委托资产托管业务");
        Integer guaranteeBusinessProcedureLoan = parTotalMarketDao.queryAmount(currentStageId, "担保业务");

        //短期贷款
        repBalanceSheetEntity.setShortLoan((int) Math.round((shortTermLoan * sum)));
        Integer shortLoan = repBalanceSheetEntity.getShortLoan();

        //贴现
        repBalanceSheetEntity.setDiscount((int) Math.round((amount * discountShare)));
        Integer discount = repBalanceSheetEntity.getDiscount();

        //中长期贷款
        repBalanceSheetEntity.setMlongLoan((int) Math.round((mlongTermLoan * zcSum)));
        Integer mlongLoan = repBalanceSheetEntity.getMlongLoan();

        //利息收入
        Integer interestIncome = (int) Math.round((shortTermLoan * d * decLoanDepositVO.getShortMortgageLoanRise() * 0.01 +
                shortTermLoan * z * decLoanDepositVO.getShortHypothecatedLoanRise() * 0.01 +
                shortTermLoan * b * decLoanDepositVO.getShortGuaranteeLoanRise() * 0.01 +
                shortTermLoan * x * decLoanDepositVO.getShortLoanCreditRise() * 0.01 + discount * decLoanDepositVO.getDiscountRise() * 0.01 +
                mlongTermLoan * zcd * mlongMortgageLoanRise * 0.01 +
                mlongTermLoan * zcz * decLoanDepositVO.getMlongHypothecatedLoanRise() * 0.01 +
                mlongTermLoan * zcb * decLoanDepositVO.getMlongGuaranteeLoanRise() * 0.01 +
                mlongTermLoan * zcx * decLoanDepositVO.getMlongLoanCreditRise() * 0.01));

        //应收利息
        repBalanceSheetEntity.setInterestReceivable((int) Math.round((interestIncome * 0.0265)));
        Integer interestReceivable = repBalanceSheetEntity.getInterestReceivable();

        //其他应收款
        repBalanceSheetEntity.setOtherReceivables(null);
        Integer otherReceivables = repBalanceSheetEntity.getOtherReceivables();

        //短期投资 为空时应查询0阶段表

        if (decInvestmentShortVO == null) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(1L, "期末持有", "短期");
            ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, "A", "短期");
            repBalanceSheetEntity.setShortInvestment(parInitialDataInvestbusStockVO.getStockCost() + parInitialDataInvestbusBondVO.getNominalValue());
        } else if (decInvestmentShortVO.getInvestmentStock() == null && decInvestmentShortVO.getNvestmentBond() == null) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(1L, "期末持有", "短期");
            ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, "A", "短期");
            repBalanceSheetEntity.setShortInvestment(parInitialDataInvestbusStockVO.getStockCost() + parInitialDataInvestbusBondVO.getNominalValue());
        } else {
            repBalanceSheetEntity.setShortInvestment(decInvestmentShortVO.getInvestmentStock() + decInvestmentShortVO.getNvestmentBond());
        }
        Integer shortInvestment = repBalanceSheetEntity.getShortInvestment();

        //其他流动资产
        repBalanceSheetEntity.setOtherProperty(null);
        Integer otherProperty = repBalanceSheetEntity.getOtherProperty();

        //减：贷款损失准备
        repBalanceSheetEntity.setLossReserves((int) Math.round((repBalanceSheetVO.getLossReserves() + (shortLoan + discount + mlongLoan) * 0.00606)));
        Integer lossReserves = repBalanceSheetEntity.getLossReserves();

        //长期债权投资，如为空，去零阶段表取
        if (decInvestmentLongBondVO.size() == 0) {
            ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, "总计", "长期");
            repBalanceSheetEntity.setLongDebtInvestment(parInitialDataInvestbusBondVO.getRealValue());
        } else {
            repBalanceSheetEntity.setLongDebtInvestment(repInvestbusOperatDataBondLongEntityNum.getRealValue());
        }
        Integer longDebtInvestment = repBalanceSheetEntity.getLongDebtInvestment();

        //长期股权投资
        if (decInvestmentLongSharesVO == null) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(1L, "期末持有", "长期");
            repBalanceSheetEntity.setLongEquityInvestment(repBalanceSheetVO.getLongEquityInvestment() + parInitialDataInvestbusStockVO.getStockCost());
        } else {
            repBalanceSheetEntity.setLongEquityInvestment(repBalanceSheetVO.getLongEquityInvestment() + decInvestmentLongSharesVO.getBuySell());
        }
        Integer longEquityInvestment = repBalanceSheetEntity.getLongEquityInvestment();

        //固定资产
        if (decPersonnelVO == null) {
            ParInitialDataAutomationVO parInitialDataAutomationVO = parInitialDataAutomationDao.selectInitialDataAutomationData(1L, "自动化投资");
            repBalanceSheetEntity.setFixedAssets((int) Math.round((repBalanceSheetVO.getFixedAssets() * 0.9 + parInitialDataAutomationVO.getPresentStage())));
        } else if (decPersonnelVO.getAutomationInvestment() == null) {
            ParInitialDataAutomationVO parInitialDataAutomationVO = parInitialDataAutomationDao.selectInitialDataAutomationData(1L, "自动化投资");
            repBalanceSheetEntity.setFixedAssets((int) Math.round((repBalanceSheetVO.getFixedAssets() * 0.9 + parInitialDataAutomationVO.getPresentStage())));
        } else {
            repBalanceSheetEntity.setFixedAssets((int) Math.round((repBalanceSheetVO.getFixedAssets() * 0.9 + decPersonnelVO.getAutomationInvestment())));
        }
        Integer fixedAssets = repBalanceSheetEntity.getFixedAssets();

        //在建工程
        repBalanceSheetEntity.setConstructionProcess(null);
        Integer constructionProcess = repBalanceSheetEntity.getConstructionProcess();

        //长期资产合计
        repBalanceSheetEntity.setLongTotalAsset(mlongLoan + lossReserves + longDebtInvestment + longEquityInvestment + fixedAssets);
        Integer longTotalAsset1 = repBalanceSheetEntity.getLongTotalAsset();

        //无形资产
        repBalanceSheetEntity.setIntangibleAssets(450);
        Integer intangibleAssets = repBalanceSheetEntity.getIntangibleAssets();
        //其他资产
        repBalanceSheetEntity.setOtherAssets(450);
        Integer otherAssets = repBalanceSheetEntity.getOtherAssets();
        //无形资产及其他资产合计
        repBalanceSheetEntity.setIntangibleAotherAssets(900);
        Integer intangibleAotherAssets1 = repBalanceSheetEntity.getIntangibleAotherAssets();

        //短期存款
        Integer shortTermLoanTotal = parTotalMarketDao.queryAmount(currentStageId, "短期存款");
        repBalanceSheetEntity.setShortDeposit((int) Math.round((shortTermLoanTotal * shortDepositRiseShare)));
        Integer shortDeposit = repBalanceSheetEntity.getShortDeposit();

        //短期储蓄存款
        Integer shortSavingsDepositTotal = parTotalMarketDao.queryAmount(currentStageId, "短期储蓄存款");
        repBalanceSheetEntity.setShortSavingsDeposit((int) Math.round((shortSavingsDepositTotal * shortSavingsDepositRiseShare)));
        Integer shortSavingsDeposit = repBalanceSheetEntity.getShortSavingsDeposit();

        //同业存放款项
        Integer interbankDepositTotal = parTotalMarketDao.queryAmount(currentStageId, "同业存放款项");
        repBalanceSheetEntity.setInterbankDeposit((int) Math.round((interbankDepositTotal * interbankdepositnnnRiseShare)));
        Integer interbankDeposit = repBalanceSheetEntity.getInterbankDeposit();

        //同业拆入
        repBalanceSheetEntity.setInterBank(decLoanDepositVO.getInterBank());
        Integer interBank = repBalanceSheetEntity.getInterBank();

        //存入短期保证金
        repBalanceSheetEntity.setDepositShortMargin((int) Math.round((repBalanceSheetVO.getDepositShortMargin() * (1 + 0.185))));
        Integer depositShortMargin = repBalanceSheetEntity.getDepositShortMargin();

        //长期存款
        Integer longDepositTotal = parTotalMarketDao.queryAmount(currentStageId, "长期存款");
        repBalanceSheetEntity.setLongDeposit((int) Math.round((longDepositTotal * longDePositRiseShare)));
        Integer longDeposit = repBalanceSheetEntity.getLongDeposit();

        //长期储蓄存款
        Integer longSavingsDepositTotal = parTotalMarketDao.queryAmount(currentStageId, "长期储蓄存款");
        repBalanceSheetEntity.setLongSavingsDeposit((int) Math.round((longSavingsDepositTotal * longDepositRiseShare)));
        Integer longSavingsDeposit = repBalanceSheetEntity.getLongSavingsDeposit();

        //存入长期保证金
        repBalanceSheetEntity.setDepositLongMargin((int) Math.round((repBalanceSheetVO.getDepositLongMargin() * (1 + 0.15))));
        Integer depositLongMargin = repBalanceSheetEntity.getDepositLongMargin();

        //应付利息
        repBalanceSheetEntity.setPaymentInterest((int) Math.round(((shortDeposit + shortSavingsDeposit + depositShortMargin + longDeposit +
                longSavingsDeposit + depositLongMargin) * 0.012 * 0.65)));
        Integer paymentInterest = repBalanceSheetEntity.getPaymentInterest();

        //应付长期债券
        repBalanceSheetEntity.setPaymentLongBond(repBalanceSheetVO.getPaymentLongBond() - 50 + decLoanDepositVO.getIssueLongBonds());
        Integer paymentLongBond = repBalanceSheetEntity.getPaymentLongBond();

        //应付次级债券
        repBalanceSheetEntity.setPaymentSubBond(repBalanceSheetVO.getPaymentSubBond() + decLoanDepositVO.getIssueSecondaryBonds());
        Integer paymentSubBond = repBalanceSheetEntity.getPaymentSubBond();

        //长期负债合计
        repBalanceSheetEntity.setTotalLongLiabilities(longDeposit + longSavingsDeposit + depositLongMargin + paymentLongBond + paymentSubBond);
        Integer totalLongLiabilities = repBalanceSheetEntity.getTotalLongLiabilities();

        //股本
        repBalanceSheetEntity.setCapital(800);
        Integer capital = repBalanceSheetEntity.getCapital();

        //资本公积
        repBalanceSheetEntity.setCapitalReserve(750);
        Integer capitalReserve = repBalanceSheetEntity.getCapitalReserve();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //人事后勤数据 2阶段开启
        int otherPeople = 0;
        if (decPersonnelVO != null) {
            if (currentStageId >= 3) {
                ParIndexNumberVO parIndexNumberVO = parIndexNumberDao.queryData(currentStageId);
                double yewuzengliang = ((double) (shortDeposit + shortSavingsDeposit + interbankDeposit + longDeposit + longSavingsDeposit + shortLoan + discount + mlongLoan) / 8) * 0.01;

                RepPeopleLogisticsEntity repPeopleLogisticsEntity = new RepPeopleLogisticsEntity();
                repPeopleLogisticsEntity.setCurrentProjectId(currentProjectId);
                repPeopleLogisticsEntity.setCurrentTeamId(currentTeamId);
                repPeopleLogisticsEntity.setCurrentStageId(currentStageId);

                //培训时间总和
                double trainingTimeSum = 0.0;
                //加班小时总和
                double linSum = 0.0;
                //临时工小时总和
                double temporaryWorkerSum = 0.0;

                //查询参数表下阶段人员流失率
                List<ParWastageRateVO> parWastageRateVOS = parWastageRateDao.queryData(currentStageId);
                for (ParWastageRateVO parWastageRateVO : parWastageRateVOS) {
                    repPeopleLogisticsEntity.setBusinessType(parWastageRateVO.getBusinessType());
                    RepPeopleLogisticsVO repPeopleLogisticsVO = repPeopleLogisticsDao.queryBeforeData(currentProjectId, currentTeamId, currentStageId - 1, parWastageRateVO.getBusinessType());
                    if (repPeopleLogisticsVO == null) {
                        ParInitialDataPeopleLogisticsVO parInitialDataPeopleLogisticsVO = parInitialDataPeopleLogisticsDao.queryData(parWastageRateVO.getBusinessType());
                        //期初员工数(人)
                        repPeopleLogisticsEntity.setStartEmployeeQuantity(parInitialDataPeopleLogisticsVO.getEndEmployeeQuantity());
                        //本阶段流失人数(人)=上阶段期末人数*流失率
                        repPeopleLogisticsEntity.setDrain(parInitialDataPeopleLogisticsVO.getLostPersonnel());
                    } else {
                        //期初员工数(人)
                        repPeopleLogisticsEntity.setStartEmployeeQuantity(repPeopleLogisticsVO.getEndEmployeeQuantity());
                        //本阶段流失人数(人)=上阶段期末人数*流失率
                        repPeopleLogisticsEntity.setDrain(repPeopleLogisticsVO.getLostPersonnel());
                    }
                    Integer startEmployeeQuantity = repPeopleLogisticsEntity.getStartEmployeeQuantity();
                    Integer drain = repPeopleLogisticsEntity.getDrain();

                    //本阶段雇佣/解雇(人)
                    repPeopleLogisticsEntity.setHareFire(decPersonnelVO.getHireFireLoans());
                    Integer hareFire = repPeopleLogisticsEntity.getHareFire();

                    //期末员工数(人)
                    if (hareFire == null) {
                        hareFire = 0;
                    }
                    repPeopleLogisticsEntity.setEndEmployeeQuantity(startEmployeeQuantity + hareFire - drain);
                    Integer endEmployeeQuantity = repPeopleLogisticsEntity.getEndEmployeeQuantity();

                    //下阶段人员流失率(%)
                    repPeopleLogisticsEntity.setLossRate(parWastageRateVO.getWastageRate());
                    Double lossRate = repPeopleLogisticsEntity.getLossRate();

                    //下阶段流失人员数(人)
                    repPeopleLogisticsEntity.setLostPersonnel((int) Math.round(endEmployeeQuantity * lossRate * 0.01));

                    //有效营业时间(千小时)
                    repPeopleLogisticsEntity.setEffectiveTime(endEmployeeQuantity * 2000 * 0.001);
                    Double effectiveTime = repPeopleLogisticsEntity.getEffectiveTime();

                    //人员调配(千小时)-培训时间
                    if (decPersonnelVO==null||decPersonnelVO.getTrainLoans() == null) {
                        decPersonnelVO.setTrainLoans(0);
                    }
                    repPeopleLogisticsEntity.setTrainingTime(Double.valueOf(String.format("%.1f", decPersonnelVO.getTrainLoans() * 8 * endEmployeeQuantity * 0.001)));
                    Double trainingTime = repPeopleLogisticsEntity.getTrainingTime();
                    trainingTimeSum = (double) (trainingTime + trainingTime) / 0.001;
                    //人员调配(千小时)-市场拓展
                    if (decPersonnelVO==null||decPersonnelVO.getBusinessDevelopmentLoans() == null) {
                        decPersonnelVO.setBusinessDevelopmentLoans(0.0);
                    }
                    repPeopleLogisticsEntity.setMarketDevelopment(Double.valueOf(String.format("%.1f", decPersonnelVO.getBusinessDevelopmentLoans() * effectiveTime * 0.01)));
                    Double marketDevelopment = repPeopleLogisticsEntity.getMarketDevelopment();

                    //人员调配(千小时)-客户维护
                    if (decPersonnelVO==null||decPersonnelVO.getCustomerCareLoans() == null) {
                        decPersonnelVO.setCustomerCareLoans(0.0);
                    }
                    repPeopleLogisticsEntity.setCustomerMaintenance(Double.valueOf(String.format("%.1f", decPersonnelVO.getCustomerCareLoans() * effectiveTime * 0.01)));
                    Double customerMaintenance = repPeopleLogisticsEntity.getCustomerMaintenance();

                    //可用营业时间(千小时)=有效营业时间(千小时)-培训时间-市场拓展-客户维护
                    repPeopleLogisticsEntity.setAvailableTime(Double.valueOf(String.format("%.1f", effectiveTime - trainingTime + marketDevelopment + customerMaintenance)));
                    Double availableTime = repPeopleLogisticsEntity.getAvailableTime();

                    //需要营业时间(千小时)//算不出来
                    for (RepPeopleLogisticsVO repPeopleLogisticsVO1 : repPeopleLogisticsVOs) {
                        if (repPeopleLogisticsVO1.getBusinessType().equals(parWastageRateVO.getBusinessType())) {
                            repPeopleLogisticsEntity.setNeedTime(Double.valueOf(String.format("%.1f", repPeopleLogisticsVO1.getNeedTime() + (1 + yewuzengliang) + (1 + parIndexNumberVO.getRateOfChange()))));
                        }
                    }

                    Double needTime = repPeopleLogisticsEntity.getNeedTime();

                    Double value = needTime - availableTime;
                    Double lin = availableTime * 0.1;
                    //如果需要营业时间(千小时)-可用营业时间(千小时)>可用营业时间(千小时)的10%
                    if (value < 0) {
                        //加班(千小时)
                        repPeopleLogisticsEntity.setWorkOvertime(0.0D);
                        //临时工(千小时)
                        repPeopleLogisticsEntity.setTemporaryWorker(0.0D);
                    } else {
                        if (value > lin) {
                            //加班(千小时)
                            repPeopleLogisticsEntity.setWorkOvertime(Double.valueOf(String.format("%.1f", lin)));
                            linSum = (double) (lin + lin) / 0.001;
                            //临时工(千小时)
                            repPeopleLogisticsEntity.setTemporaryWorker(Double.valueOf(String.format("%.1f", value - lin)));
                            Double temporaryWorker = repPeopleLogisticsEntity.getTemporaryWorker();
                            temporaryWorkerSum = (double) (temporaryWorker + temporaryWorker) / 0.001;
                        } else {
                            //加班(千小时)
                            repPeopleLogisticsEntity.setWorkOvertime(Double.valueOf(String.format("%.1f", value)));
                            //临时工(千小时)
                            repPeopleLogisticsEntity.setTemporaryWorker(0.0D);
                        }
                    }
                    //培训水平 10天以上为优，5—9天良好，3—4天及格，0—2天差
                    if (decPersonnelVO.getTrainLoans() != null) {


                        if (decPersonnelVO.getTrainLoans() >= 0 && decPersonnelVO.getTrainLoans() <= 2) {
                            repPeopleLogisticsEntity.setTrainingLevel("差");
                        }
                        if (decPersonnelVO.getTrainLoans() >= 3 && decPersonnelVO.getTrainLoans() <= 4) {
                            repPeopleLogisticsEntity.setTrainingLevel("及格");
                        }
                        if (decPersonnelVO.getTrainLoans() >= 5 && decPersonnelVO.getTrainLoans() <= 9) {
                            repPeopleLogisticsEntity.setTrainingLevel("良好");
                        }
                        if (decPersonnelVO.getTrainLoans() >= 10) {
                            repPeopleLogisticsEntity.setTrainingLevel("优");
                        } else {
                            repPeopleLogisticsEntity.setTrainingLevel("");
                        }
                    }
                    //工作满意度
                    //考虑附加人员开支和加班时间
                    //两项得分相加：1—4分为痛苦，5—8分为不满意，9—12分为满意，13—15分为激励
                    //附加人员开支：12%—14%得1分，15%—16%得3分，17%—19%得5分，20%以上得4分
                    //加班时间：没有加班时间得10分，加班时间<可用营业时间10%的0.5倍得7分，加班时间在可用营业时间10%的0.5倍到1倍之间得4分，需要雇佣临时工的得0分。
                    Integer flag = 0;
                    if (decPersonnelVO.getAdditionalPersonnelCost() != null) {


                        if (decPersonnelVO.getAdditionalPersonnelCost() >= 12 && decPersonnelVO.getAdditionalPersonnelCost() <= 14) {
                            flag = flag + 1;
                        }
                        if (decPersonnelVO.getAdditionalPersonnelCost() >= 15 && decPersonnelVO.getAdditionalPersonnelCost() <= 16) {
                            flag = flag + 3;
                        }
                        if (decPersonnelVO.getAdditionalPersonnelCost() >= 17 && decPersonnelVO.getAdditionalPersonnelCost() <= 19) {
                            flag = flag + 5;
                        }
                        if (decPersonnelVO.getAdditionalPersonnelCost() >= 12 && decPersonnelVO.getAdditionalPersonnelCost() <= 14) {
                            flag = flag + 4;
                        }
                    }
                    if (repPeopleLogisticsEntity.getTemporaryWorker() > 0) {
                        flag = flag + 0;
                    } else {
                        if (repPeopleLogisticsEntity.getWorkOvertime() == 0.0D) {
                            flag = flag + 10;
                        }
                        if (repPeopleLogisticsEntity.getWorkOvertime() < availableTime * 0.1 * 0.5) {
                            flag = flag + 7;
                        }
                        if (repPeopleLogisticsEntity.getWorkOvertime() < availableTime * 0.1 * 0.5 && repPeopleLogisticsEntity.getWorkOvertime() < availableTime * 0.1 * 1) {
                            flag = flag + 4;
                        }
                    }
                    if (flag <= 4) {
                        repPeopleLogisticsEntity.setJobSatisfaction("痛苦");
                    }
                    if (flag >= 5 && flag <= 8) {
                        repPeopleLogisticsEntity.setJobSatisfaction("不满意");
                    }
                    if (flag >= 9 && flag <= 12) {
                        repPeopleLogisticsEntity.setJobSatisfaction("满意");
                    }
                    if (flag >= 13) {
                        repPeopleLogisticsEntity.setJobSatisfaction("激励");
                    }
//                    repPeopleLogisticsDao.insert(repPeopleLogisticsEntity);
                    //人事后勤数据-业务表
                    if ("贷款业务".equals(repPeopleLogisticsEntity.getBusinessType())) {
                        RepPeopleLogisticsEntity repPeopleLogisticsEntity0 = new RepPeopleLogisticsEntity();
                        BeanUtils.copyProperties(repPeopleLogisticsEntity, repPeopleLogisticsEntity0);
                        preMap.put("personnelLoanBusiness", repPeopleLogisticsEntity0);
                    }
                    if ("存款业务".equals(repPeopleLogisticsEntity.getBusinessType())) {
                        RepPeopleLogisticsEntity repPeopleLogisticsEntity1 = new RepPeopleLogisticsEntity();
                        BeanUtils.copyProperties(repPeopleLogisticsEntity, repPeopleLogisticsEntity1);
                        preMap.put("personnelDepositBusiness", repPeopleLogisticsEntity1);
                    }
                    if ("投资业务".equals(repPeopleLogisticsEntity.getBusinessType())) {
                        RepPeopleLogisticsEntity repPeopleLogisticsEntity2 = new RepPeopleLogisticsEntity();
                        BeanUtils.copyProperties(repPeopleLogisticsEntity, repPeopleLogisticsEntity2);
                        preMap.put("personnelInvestmentBusiness", repPeopleLogisticsEntity2);
                    }
                    if ("中间业务".equals(repPeopleLogisticsEntity.getBusinessType())) {
                        RepPeopleLogisticsEntity repPeopleLogisticsEntity3 = new RepPeopleLogisticsEntity();
                        BeanUtils.copyProperties(repPeopleLogisticsEntity, repPeopleLogisticsEntity3);
                        preMap.put("personnelMiddleBusiness", repPeopleLogisticsEntity3);
                    }
                }
                //已换算成小时
                int trainingTimeMoney = (int) Math.round(((double) trainingTimeSum / 8) * 500 * 0.000001);
                int linMoney = (int) Math.round(linSum * 39 * 0.000001);
                int temporaryWorkerMoney = (int) Math.round(temporaryWorkerSum * 48 * 0.000001);
                //其他人员开支
                otherPeople = trainingTimeMoney + linMoney + temporaryWorkerMoney;

                RepPeopleLogisticsEntity repPeopleLogisticsOtherEntity = new RepPeopleLogisticsEntity();
                repPeopleLogisticsOtherEntity.setCurrentProjectId(currentProjectId);
                repPeopleLogisticsOtherEntity.setCurrentTeamId(currentTeamId);
                repPeopleLogisticsOtherEntity.setCurrentStageId(currentStageId);
                repPeopleLogisticsOtherEntity.setBusinessType("其他");

                //期初员工数(人)
                repPeopleLogisticsOtherEntity.setStartEmployeeQuantity(1200);

                //本阶段雇佣/解雇(人)
                repPeopleLogisticsOtherEntity.setHareFire(null);

                //本阶段流失人数(人)=上阶段期末人数*流失率
                repPeopleLogisticsOtherEntity.setDrain(null);

                //期末员工数(人)
                repPeopleLogisticsOtherEntity.setEndEmployeeQuantity(1200);

                //下阶段人员流失率(%)
                repPeopleLogisticsOtherEntity.setLossRate(null);

                //下阶段流失人员数(人)
                repPeopleLogisticsOtherEntity.setLostPersonnel(null);

                //有效营业时间(千小时)
                repPeopleLogisticsOtherEntity.setEffectiveTime(2400.0);

                //人员调配(千小时)-培训时间
                repPeopleLogisticsOtherEntity.setTrainingTime(null);

                //人员调配(千小时)-市场拓展
                repPeopleLogisticsOtherEntity.setMarketDevelopment(null);

                //人员调配(千小时)-客户维护
                repPeopleLogisticsOtherEntity.setCustomerMaintenance(null);

                //可用营业时间(千小时)=有效营业时间(千小时)-培训时间-市场拓展-客户维护
                repPeopleLogisticsOtherEntity.setAvailableTime(2400.0);
                Double availableTimeOther = repPeopleLogisticsOtherEntity.getAvailableTime();

                //需要营业时间(千小时)//算不出来
                RepPeopleLogisticsVO repPeopleLogisticsVO = repPeopleLogisticsDao.selectPeopleByPar(1L, "其他");
                repPeopleLogisticsOtherEntity.setNeedTime(Double.valueOf(String.format("%.1f", repPeopleLogisticsVO.getNeedTime() + (1 + yewuzengliang) + (1 + parIndexNumberVO.getRateOfChange()))));
                Double needTimeOther = repPeopleLogisticsOtherEntity.getNeedTime();

                Double valueOther = needTimeOther - availableTimeOther;
                Double linOther = availableTimeOther * 0.1;
                //如果需要营业时间(千小时)>可用营业时间可用营业时间(千小时)的10%
                if (valueOther < 0) {
                    //加班(千小时)
                    repPeopleLogisticsOtherEntity.setWorkOvertime(0.0D);
                    //临时工(千小时)
                    repPeopleLogisticsOtherEntity.setTemporaryWorker(0.0D);
                } else {
                    if (valueOther > linOther) {
                        //加班(千小时)
                        repPeopleLogisticsOtherEntity.setWorkOvertime(Double.valueOf(String.format("%.1f", linOther)));
                        //临时工(千小时)
                        repPeopleLogisticsOtherEntity.setTemporaryWorker(Double.valueOf(String.format("%.1f", valueOther - linOther)));
                    } else {
                        //加班(千小时)
                        repPeopleLogisticsOtherEntity.setWorkOvertime(Double.valueOf(String.format("%.1f", valueOther)));
                        //临时工(千小时)
                        repPeopleLogisticsOtherEntity.setTemporaryWorker(0.0D);
                    }
                }
                //培训水平 10天以上为优，5—9天良好，3—4天及格，0—2天差
                repPeopleLogisticsOtherEntity.setTrainingLevel(null);

                //工作满意度
                //考虑附加人员开支和加班时间
                //两项得分相加：1—4分为痛苦，5—8分为不满意，9—12分为满意，13—15分为激励
                //附加人员开支：12%—14%得1分，15%—16%得3分，17%—19%得5分，20%以上得4分
                //加班时间：没有加班时间得10分，加班时间<可用营业时间10%的0.5倍得7分，加班时间在可用营业时间10%的0.5倍到1倍之间得4分，需要雇佣临时工的得0分。
                repPeopleLogisticsOtherEntity.setJobSatisfaction(null);

//                repPeopleLogisticsDao.insert(repPeopleLogisticsOtherEntity);

                //人事后勤数据-其他
                preMap.put("PersonnelOther", repPeopleLogisticsOtherEntity);

                RepPersonnelLogisticsDataAutomationEntity repPersonnelLogisticsDataAutomationEntity = new RepPersonnelLogisticsDataAutomationEntity();
                repPersonnelLogisticsDataAutomationEntity.setCurrentProjectId(currentProjectId);
                repPersonnelLogisticsDataAutomationEntity.setCurrentTeamId(currentTeamId);
                repPersonnelLogisticsDataAutomationEntity.setCurrentStageId(currentStageId);
                repPersonnelLogisticsDataAutomationEntity.setProject("自动化投资（百万元）");
                if (decPersonnelVO==null||decPersonnelVO.getAutomationInvestment() == null) {
                    decPersonnelVO.setAutomationInvestment(0);
                }
                repPersonnelLogisticsDataAutomationEntity.setPresentStage(decPersonnelVO.getAutomationInvestment());
                Integer presentStage = repPersonnelLogisticsDataAutomationEntity.getPresentStage();
                //累计投资=本阶段+上阶段累计投资的90%
                repPersonnelLogisticsDataAutomationEntity.setCumulativeInvestment((int) Math.round(presentStage + repPersonnelLogisticsDataAutomationVO.getCumulativeInvestment() * 0.9));
                //未知
                repPersonnelLogisticsDataAutomationEntity.setExponent(0.0D);
//                repPersonnelLogisticsDataAutomationDao.insert(repPersonnelLogisticsDataAutomationEntity);

                //人事后勤数据-自动化投资
                preMap.put("personnelAutomation", repPersonnelLogisticsDataAutomationEntity);
            }
        }
        RepPeopleLogisticsEntity repPeopleLogisticsEntity = new RepPeopleLogisticsEntity();
        repPeopleLogisticsEntity.setCurrentProjectId(currentProjectId);
        repPeopleLogisticsEntity.setCurrentTeamId(currentTeamId);
        repPeopleLogisticsEntity.setCurrentStageId(currentStageId);

        repPeopleLogisticsEntity.setStartEmployeeQuantity(0);
        repPeopleLogisticsEntity.setHareFire(0);
        repPeopleLogisticsEntity.setDrain(0);
        repPeopleLogisticsEntity.setEndEmployeeQuantity(0);
        repPeopleLogisticsEntity.setLossRate(0.0D);
        repPeopleLogisticsEntity.setLostPersonnel(0);
        repPeopleLogisticsEntity.setEffectiveTime(0.0D);
        repPeopleLogisticsEntity.setTrainingTime(0.0D);
        repPeopleLogisticsEntity.setMarketDevelopment(0.0D);
        repPeopleLogisticsEntity.setCustomerMaintenance(0.0D);
        repPeopleLogisticsEntity.setAvailableTime(0.0D);
        repPeopleLogisticsEntity.setNeedTime(0.0D);
        repPeopleLogisticsEntity.setWorkOvertime(0.0D);
        repPeopleLogisticsEntity.setTemporaryWorker(0.0D);
        repPeopleLogisticsEntity.setTrainingLevel("");
        repPeopleLogisticsEntity.setJobSatisfaction("");

        if (preMap.get("personnelLoanBusiness") == null) {
            repPeopleLogisticsEntity.setBusinessType("贷款业务");
            preMap.put("personnelLoanBusiness", repPeopleLogisticsEntity);
        }
        if (preMap.get("personnelDepositBusiness") == null) {
            repPeopleLogisticsEntity.setBusinessType("存款业务");
            preMap.put("personnelDepositBusiness", repPeopleLogisticsEntity);
        }
        if (preMap.get("personnelInvestmentBusiness") == null) {
            repPeopleLogisticsEntity.setBusinessType("投资业务");
            preMap.put("personnelInvestmentBusiness", repPeopleLogisticsEntity);
        }
        if (preMap.get("personnelMiddleBusiness") == null) {
            repPeopleLogisticsEntity.setBusinessType("中间业务");
            preMap.put("personnelMiddleBusiness", repPeopleLogisticsEntity);
        }
        if (preMap.get("PersonnelOther") == null) {
            repPeopleLogisticsEntity.setBusinessType("其他");
            preMap.put("PersonnelOther", repPeopleLogisticsEntity);
        }
        RepPersonnelLogisticsDataAutomationEntity repPersonnelLogisticsDataAutomationEntity = new RepPersonnelLogisticsDataAutomationEntity();
        repPersonnelLogisticsDataAutomationEntity.setCurrentProjectId(currentProjectId);
        repPersonnelLogisticsDataAutomationEntity.setCurrentTeamId(currentTeamId);
        repPersonnelLogisticsDataAutomationEntity.setCurrentStageId(currentStageId);
        repPersonnelLogisticsDataAutomationEntity.setProject("自动化投资（百万元）");
        repPersonnelLogisticsDataAutomationEntity.setPresentStage(0);
        repPersonnelLogisticsDataAutomationEntity.setCumulativeInvestment(0);
        repPersonnelLogisticsDataAutomationEntity.setExponent(0.0D);


        if (preMap.get("personnelAutomation") == null) {
            preMap.put("personnelAutomation", repPersonnelLogisticsDataAutomationEntity);
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //利润表
        //Profit(currentProjectId, currentTeamId, currentStageId);
        RepProfitEntity repProfitEntity = new RepProfitEntity();
        repProfitEntity.setCurrentProjectId(currentProjectId);
        repProfitEntity.setCurrentTeamId(currentTeamId);
        repProfitEntity.setCurrentStageId(currentStageId);

        //利息收入
        repProfitEntity.setInterestIncome(interestIncome);
        Integer interestIncomes = repProfitEntity.getInterestIncome();

        Double centralBank = parProjectRateDao.queryInterestRate(currentStageId, "存放中央银行款项");
        Double Interbank = parProjectRateDao.queryInterestRate(currentStageId, "存放同业款项");
        Double InterbankLending = parProjectRateDao.queryInterestRate(currentStageId, "拆放同业");
        Double borrowingsBank = parProjectRateDao.queryInterestRate(currentStageId, "向中央银行借款");

        //金融机构往来收入
        repProfitEntity.setFinancialOrganizationIncome((int) Math.round((dueCentralBank * centralBank + depositInterbank * Interbank + loanTrade * InterbankLending)));
        Integer financialOrganizationIncome = repProfitEntity.getFinancialOrganizationIncome();

        //手续费收入//不做中间业务
        if (decMiddleCustodyVO == null) {
            repProfitEntity.setServiceChargeIncome((int) Math.round((28088 * 0.01 + 2321 * 0.005)));
        } else {
            repProfitEntity.setServiceChargeIncome((int) Math.round(custodyBusinessProcedureRiseShare * custodyBusinessProcedureLoan * decMiddleCustodyVO.getCustodyBusinessProcedure() + guaranteeBusinessProcedureRiseShare * guaranteeBusinessProcedureLoan * decMiddleCustodyVO.getGuaranteeBusinessProcedure()));
        }

        Integer serviceChargeIncome = repProfitEntity.getServiceChargeIncome();

        //营业收入合计
        repProfitEntity.setTotalRevenues(interestIncomes + financialOrganizationIncome + serviceChargeIncome);
        Integer totalRevenues = repProfitEntity.getTotalRevenues();

        //利息支出
        Double longBond = parProjectRateDao.queryInterestRate(currentStageId, "发行长期债券");
        Double secondaryBond = parProjectRateDao.queryInterestRate(currentStageId, "次级债券");
        repProfitEntity.setInterestExpense((int) Math.round((shortDeposit * decLoanDepositVO.getShortDepositRise() * 0.01 +
                shortSavingsDeposit * decLoanDepositVO.getShortSavingsDepositRise() * 0.01 + interbankDeposit * decLoanDepositVO.getInterbankdepositnnnRise() * 0.01
                + longDeposit * decLoanDepositVO.getLongDePositRise() * 0.01 + longSavingsDeposit * decLoanDepositVO.getLongSavingsDepositRise() * 0.01
                + paymentLongBond * longBond * 0.01 + paymentSubBond * secondaryBond * 0.01)));
        Integer interestExpense = repProfitEntity.getInterestExpense();

        //金融机构往来支出、、本阶段搭桥贷款利率未知
        Double interBankRate = parProjectRateDao.queryInterestRate(currentStageId, "同业拆入");
        repProfitEntity.setFinancialOrganizationExpense((int) Math.round((repBalanceSheetVO.getBorrowingsCentralBank() * borrowingsBank * 0.01 + interbankDeposit * 0.01 * decLoanDepositVO.getInterbankdepositnnnRise() + interBank * interBankRate * 0.01)));
        Integer financialOrganizationExpense = repProfitEntity.getFinancialOrganizationExpense();

        //手续费支出

        if (decInvestmentShortVO == null) {
            repProfitEntity.setServiceChargeExpense((int) Math.round(repProfitVO.getServiceChargeIncome() * (1 + 0.2)));
        } else {
            repProfitEntity.setServiceChargeExpense((int) Math.round(stockCost2 + stockCostLong2 + nominalValueSum + nominalLongValueSum * 0.025 * (1 + 0.2)));
        }
        Integer serviceChargeExpense = repProfitEntity.getServiceChargeExpense();

        //营业支出合计
        repProfitEntity.setExpenseRevenues(interestExpense + financialOrganizationExpense + serviceChargeExpense);
        Integer expenseRevenues = repProfitEntity.getExpenseRevenues();

        //人员开支=期末员工人数*工资标准（元/人年）
        //查询工资表
        ParPersonnelCostVO parPersonnelCostVO = parPersonnelCostDao.queryWages("工资标准（元/人年）");
        int personnelExpenses = 0;
        if (currentStageId == 2) {
            List<ParInitialDataPeopleLogisticsVO> parInitialDataPeopleLogisticsVOS = parInitialDataPeopleLogisticsDao.query();
            for (ParInitialDataPeopleLogisticsVO parInitialDataPeopleLogisticsVO : parInitialDataPeopleLogisticsVOS) {
                Integer loan = 0;
                if ("贷款业务".equals(parInitialDataPeopleLogisticsVO.getBusinessType())) {
                    loan = parInitialDataPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getLoanBusiness();
                }
                Integer deposit = 0;
                if ("存款业务".equals(parInitialDataPeopleLogisticsVO.getBusinessType())) {
                    deposit = parInitialDataPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getDepositBusiness();
                }
                Integer invest = 0;
                if ("投资业务".equals(parInitialDataPeopleLogisticsVO.getBusinessType())) {
                    invest = parInitialDataPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getInvestmentBusiness();
                }
                Integer middle = 0;
                if ("中间业务".equals(parInitialDataPeopleLogisticsVO.getBusinessType())) {
                    middle = parInitialDataPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getMiddleBusiness();
                }
                Integer other = 0;
                if ("其他".equals(parInitialDataPeopleLogisticsVO.getBusinessType())) {
                    other = parInitialDataPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getOtherBusiness();
                }
                personnelExpenses = (int) Math.round(Math.round(((loan + deposit + invest + middle + other) * 0.000001)));
            }
        } else {
            List<RepPeopleLogisticsVO> repPeopleLogisticsVOS = repPeopleLogisticsDao.queryData(currentProjectId, currentTeamId, currentStageId);
            if (repPeopleLogisticsVOS.size() == 0) {
                repPeopleLogisticsVOS = repPeopleLogisticsDao.queryData(currentProjectId, currentTeamId, currentStageId - 1);
            }
            for (RepPeopleLogisticsVO repPeopleLogisticsVO : repPeopleLogisticsVOS) {
                Integer loan = 0;
                if ("贷款业务".equals(repPeopleLogisticsVO.getBusinessType())) {
                    loan = repPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getLoanBusiness();
                }
                Integer deposit = 0;
                if ("存款业务".equals(repPeopleLogisticsVO.getBusinessType())) {
                    deposit = repPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getDepositBusiness();
                }
                Integer invest = 0;
                if ("投资业务".equals(repPeopleLogisticsVO.getBusinessType())) {
                    invest = repPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getInvestmentBusiness();
                }
                Integer middle = 0;
                if ("中间业务".equals(repPeopleLogisticsVO.getBusinessType())) {
                    middle = repPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getMiddleBusiness();
                }
                Integer other = 0;
                if ("其他".equals(repPeopleLogisticsVO.getBusinessType())) {
                    other = repPeopleLogisticsVO.getEndEmployeeQuantity() * parPersonnelCostVO.getOtherBusiness();
                }
                personnelExpenses = (int) Math.round(((loan + deposit + invest + middle + other) * 0.000001));
            }
        }
        repProfitEntity.setPeopleCosts(personnelExpenses);
        Integer peopleCosts = repProfitEntity.getPeopleCosts();

        //附加人员成本
        if (decPersonnelVO==null||decPersonnelVO.getAdditionalPersonnelCost() == null) {
            ParPersonnelCostVO parPersonnelCostVO1 = parPersonnelCostDao.queryWages("附加人员成本（%）");
            repProfitEntity.setAdditionPeopleCosts((int) Math.round((peopleCosts * parPersonnelCostVO1.getLoanBusiness() * 0.01)));
        } else {
            repProfitEntity.setAdditionPeopleCosts((int) Math.round((peopleCosts * decPersonnelVO.getAdditionalPersonnelCost() * 0.01)));
        }
        Integer additionPeopleCosts = repProfitEntity.getAdditionPeopleCosts();

        //其他人员开支
        repProfitEntity.setOtherPeopleCosts(otherPeople);
        Integer otherPeopleCosts = repProfitEntity.getOtherPeopleCosts();

        //固定资产折旧
        repProfitEntity.setFixedAssetsDepreciation((int) Math.round((repBalanceSheetVO.getFixedAssets() * 0.1)));
        Integer fixedAssetsDepreciation = repProfitEntity.getFixedAssetsDepreciation();

        Integer marketingTotal = 0;
        if (decMarketStrategyVO != null) {
            Integer customerMarketingLessAge = decMarketStrategyVO.getCustomerMarketingLessAge();
            Integer customerMarketingBetweenAge = decMarketStrategyVO.getCustomerMarketingBetweenAge();
            Integer customerMarketingGreaterAge = decMarketStrategyVO.getCustomerMarketingGreaterAge();
            Integer customerMarketingSmallEnterprise = decMarketStrategyVO.getCustomerMarketingSmallEnterprise();
            Integer customerMarketingMediumEnterprise = decMarketStrategyVO.getCustomerMarketingMediumEnterprise();
            Integer customerMarketingMajorEnterprise = decMarketStrategyVO.getCustomerMarketingMajorEnterprise();
            Integer customerMarketingPublicInstitution = decMarketStrategyVO.getCustomerMarketingPublicInstitution();
            Integer businessMarketingLoanTransaction = decMarketStrategyVO.getBusinessMarketingLoanTransaction();
            Integer businessMarketingDepositBloanTransaction = decMarketStrategyVO.getBusinessMarketingDepositBloanTransaction();
            Integer businessMarketingMiddleTransaction = decMarketStrategyVO.getBusinessMarketingMiddleTransaction();
            marketingTotal = customerMarketingLessAge + customerMarketingBetweenAge + customerMarketingSmallEnterprise + customerMarketingMediumEnterprise + customerMarketingMajorEnterprise + customerMarketingPublicInstitution +
                    customerMarketingGreaterAge + businessMarketingLoanTransaction + businessMarketingDepositBloanTransaction + businessMarketingMiddleTransaction;
        }
        //其它营业费用
        if (decMarketStrategyVO == null) {
            repProfitEntity.setOtherBusinessCosts((int) (200 + custodyBusinessProcedureRiseShare * custodyBusinessProcedureLoan * 0.001 +
                    guaranteeBusinessProcedureRiseShare * guaranteeBusinessProcedureLoan * 0.0005 + 2));
        } else {
            repProfitEntity.setOtherBusinessCosts((int) Math.round(marketingTotal + custodyBusinessProcedureRiseShare * custodyBusinessProcedureLoan * 0.001 +
                    guaranteeBusinessProcedureRiseShare * guaranteeBusinessProcedureLoan * 0.0005 + 2));
        }
        Integer otherBusinessCosts = repProfitEntity.getOtherBusinessCosts();

        //营业费用合计
        repProfitEntity.setTotalOperatingExpenses(peopleCosts + additionPeopleCosts + otherPeopleCosts + fixedAssetsDepreciation + otherBusinessCosts);
        Integer totalOperatingExpenses = repProfitEntity.getTotalOperatingExpenses();

        //投资收益
        if (stockAccount3 == null) {
            stockAccount3 = 0;
        }
        if (stockAccount4 == null) {
            stockAccount4 = 0;
        }
        if (accountProfitLoss1 == null) {
            accountProfitLoss1 = 0;
        }
        if (accountProfitLoss2 == null) {
            accountProfitLoss2 = 0;
        }
        repProfitEntity.setIncomeInvestment(stockAccount3 + stockAccount4 + accountProfitLoss1 + accountProfitLoss2);
        Integer incomeInvestment = repProfitEntity.getIncomeInvestment();

        //营业利润
        repProfitEntity.setOperatingProfit(totalRevenues - expenseRevenues - totalOperatingExpenses + incomeInvestment);
        Integer operatingProfit = repProfitEntity.getOperatingProfit();

        //减：营业税金及附加
        repProfitEntity.setSubtractBusTaxSurch((int) Math.round((totalRevenues * 0.05)));
        Integer subtractBusTaxSurch = repProfitEntity.getSubtractBusTaxSurch();

        //加：营业外收入
        repProfitEntity.setAddNonbusIncome(0);
        Integer addNonbusIncome = repProfitEntity.getAddNonbusIncome();

        //减：营业外支出
        repProfitEntity.setSubtractNonbusExpend(0);
        Integer subtractNonbusExpend = repProfitEntity.getSubtractNonbusExpend();

        //扣除资产减值前利润总额
        repProfitEntity.setDeductAssetImpairmentBeforeTotalProfit(operatingProfit - subtractBusTaxSurch - addNonbusIncome - subtractNonbusExpend);
        Integer deductAssetImpairmentBeforeTotalProfit = repProfitEntity.getDeductAssetImpairmentBeforeTotalProfit();

        //减：资产准备支出
        repProfitEntity.setSubtractAssetReserveExpense((int) Math.round(((shortLoan + discount + mlongLoan) * 0.00606)));
        Integer subtractAssetReserveExpense = repProfitEntity.getSubtractAssetReserveExpense();

        //扣除资产减值后利润总额
        repProfitEntity.setDeductAssetImpairmentAfterTotalProfit(deductAssetImpairmentBeforeTotalProfit - subtractAssetReserveExpense);
        Integer deductAssetImpairmentAfterTotalProfit = repProfitEntity.getDeductAssetImpairmentAfterTotalProfit();

        //减：所得税
        repProfitEntity.setSubtractIncomeTax((int) Math.round((deductAssetImpairmentAfterTotalProfit * 0.25)));
        Integer subtractIncomeTax = repProfitEntity.getSubtractIncomeTax();

        //净利润
        repProfitEntity.setRetainedProfits(deductAssetImpairmentAfterTotalProfit - subtractIncomeTax);
        Integer retainedProfits = repProfitEntity.getRetainedProfits();

        //盈余公积
        if (retainedProfits > 0) {
            repBalanceSheetEntity.setSurplusReserve((int) Math.round(repBalanceSheetVO.getSurplusReserve() + (retainedProfits * 0.15)));
        }
        if (retainedProfits == 0) {
            repBalanceSheetEntity.setSurplusReserve(repBalanceSheetVO.getSurplusReserve());
        }
        //
        if (retainedProfits < 0) {
            repBalanceSheetEntity.setSurplusReserve((int) Math.round(repBalanceSheetVO.getSurplusReserve() + (retainedProfits * 0.15)));
        }
        Integer surplusReserve = repBalanceSheetEntity.getSurplusReserve();

        //错误点记录decFinancialManagementVO=null
        if (decFinancialManagementVO == null) {
            //未分配利润
            if (retainedProfits > 0) {
                repBalanceSheetEntity.setUndistributedProfit((int) Math.round((repBalanceSheetVO.getUndistributedProfit() - repBalanceSheetVO.getCapital() * 0 + (retainedProfits * 0.85))));
            }
            if (retainedProfits <= 0) {
                repBalanceSheetEntity.setUndistributedProfit((int) Math.round((repBalanceSheetVO.getUndistributedProfit() - repBalanceSheetVO.getCapital() * 0 + retainedProfits)));
            }
        } else {
            //未分配利润
            if (decFinancialManagementVO==null||decFinancialManagementVO.getShareBonus() == null){
                decFinancialManagementVO.setShareBonus(0.0);
            }
            if (retainedProfits > 0) {
                repBalanceSheetEntity.setUndistributedProfit((int) Math.round((repBalanceSheetVO.getUndistributedProfit() - repBalanceSheetVO.getCapital() * decFinancialManagementVO.getShareBonus() * 0.01 + (retainedProfits * 0.85))));
            }
            if (retainedProfits <= 0) {
                repBalanceSheetEntity.setUndistributedProfit((int) Math.round((repBalanceSheetVO.getUndistributedProfit() - repBalanceSheetVO.getCapital() * decFinancialManagementVO.getShareBonus() * 0.01 + retainedProfits)));
            }
        }

        Integer undistributedProfit = repBalanceSheetEntity.getUndistributedProfit();

        //股东权益合计
        repBalanceSheetEntity.setTotalShareholdersEquity(capital + capitalReserve + surplusReserve + undistributedProfit);
        Integer totalShareholdersEquity = repBalanceSheetEntity.getTotalShareholdersEquity();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //代理发行债券业务 4阶段开启
        if (currentStageId >= 5) {


            //查询当前阶段所有填入数
            List<DecMiddleCustodyVO> decMiddleCustodyVOS = decMiddleCustodyDao.queryData(currentProjectId, currentStageId);
            if (decMiddleCustodyVOS.size() != 0) {
                for (DecMiddleCustodyVO decMiddleCustodyVO1 : decMiddleCustodyVOS) {
                    if (decMiddleCustodyVO1==null||decMiddleCustodyVO1.getFirstUnderwritPriceBond() == null) {
                        decMiddleCustodyVO1.setFirstUnderwritPriceBond(0.0);
                    }
                    if (decMiddleCustodyVO1==null||decMiddleCustodyVO1.getSecondUnderwritPriceBond() == null) {
                        decMiddleCustodyVO1.setSecondUnderwritPriceBond(0.0);
                    }
                    if (decMiddleCustodyVO1==null||decMiddleCustodyVO1.getThirdUnderwritPriceBond() == null) {
                        decMiddleCustodyVO1.setThirdUnderwritPriceBond(0.0);
                    }
                }
                DecMiddleCustodyVO decMiddleCustodyVO1 = decMiddleCustodyVOS.stream().max(Comparator.comparing(DecMiddleCustodyVO::getFirstUnderwritPriceBond)).get();
                DecMiddleCustodyVO decMiddleCustodyVO2 = decMiddleCustodyVOS.stream().max(Comparator.comparing(DecMiddleCustodyVO::getSecondUnderwritPriceBond)).get();
                DecMiddleCustodyVO decMiddleCustodyVO3 = decMiddleCustodyVOS.stream().max(Comparator.comparing(DecMiddleCustodyVO::getThirdUnderwritPriceBond)).get();

                List<ParAgencyBondIssueVO> parAgencyBondIssueVOS = parAgencyBondIssueDao.queryAgency(currentStageId);


                for (ParAgencyBondIssueVO parAgencyBondIssueVO : parAgencyBondIssueVOS) {
                    if ("甲债券".equals(parAgencyBondIssueVO.getTypesBonds())) {
                        RepAgencyBondEntity repAgencyBondEntity = new RepAgencyBondEntity();
                        repAgencyBondEntity.setCurrentProjectId(currentProjectId);
                        repAgencyBondEntity.setCurrentStageId(currentStageId);
                        repAgencyBondEntity.setCurrentTeamId(decMiddleCustodyVO1.getCurrentTeamId());
                        repAgencyBondEntity.setBondType(parAgencyBondIssueVO.getTypesBonds());
                        String bondType = repAgencyBondEntity.getBondType();
                        repAgencyBondEntity.setCouponRate(parAgencyBondIssueVO.getCouponRate());
                        repAgencyBondEntity.setDeadline(parAgencyBondIssueVO.getDeadline());
                        repAgencyBondEntity.setCreditRating(parAgencyBondIssueVO.getCreditRating());
                        repAgencyBondEntity.setIssueAmount(parAgencyBondIssueVO.getIssuance());
                        //承销金额
                        repAgencyBondEntity.setUnderwritingMount(parAgencyBondIssueVO.getIssuance());
                        String teamNameFirst = departmentDao.queryName(decMiddleCustodyVO1.getCurrentTeamId());

                        repAgencyBondEntity.setUnderwritingBank(teamNameFirst);
                        //认购额度
                        repAgencyBondEntity.setSubscriptionLimit(parAgencyBondIssueVO.getSubscriptionLimit());
                        repAgencyBondEntity.setUnderwritingPrice(decMiddleCustodyVO1.getFirstUnderwritPriceBond());
                        Integer Discount = parGencyBondDiscountDao.queryDiscount(currentStageId, bondType);
                        repAgencyBondEntity.setDiscount(Discount);
                        //承销金额*1%
                        //underwritingMount*0.01;
                        //获得承销资格,需支付固定成本1千万
                        repProfitEntity.setSubtractNonbusExpend(subtractNonbusExpend - 10);

//                            repAgencyBondDao.insert(repAgencyBondEntity);

                        //代理债券发行业务数据表-甲债券
                        preMap.put("agencyBondsJia", repAgencyBondEntity);
                    }
                    if ("乙债券".equals(parAgencyBondIssueVO.getTypesBonds())) {
                        RepAgencyBondEntity repAgencyBondEntity1 = new RepAgencyBondEntity();
                        repAgencyBondEntity1.setCurrentProjectId(currentProjectId);
                        repAgencyBondEntity1.setCurrentStageId(currentStageId);
                        repAgencyBondEntity1.setCurrentTeamId(decMiddleCustodyVO2.getCurrentTeamId());
                        repAgencyBondEntity1.setBondType(parAgencyBondIssueVO.getTypesBonds());
                        String bondType = repAgencyBondEntity1.getBondType();
                        repAgencyBondEntity1.setCouponRate(parAgencyBondIssueVO.getCouponRate());
                        repAgencyBondEntity1.setDeadline(parAgencyBondIssueVO.getDeadline());
                        repAgencyBondEntity1.setCreditRating(parAgencyBondIssueVO.getCreditRating());
                        repAgencyBondEntity1.setIssueAmount(parAgencyBondIssueVO.getIssuance());
                        //承销金额
                        repAgencyBondEntity1.setUnderwritingMount(parAgencyBondIssueVO.getIssuance());
                        String teamNameFirst = departmentDao.queryName(decMiddleCustodyVO2.getCurrentTeamId());

                        repAgencyBondEntity1.setUnderwritingBank(teamNameFirst);
                        //认购额度？？？
                        repAgencyBondEntity1.setSubscriptionLimit(parAgencyBondIssueVO.getSubscriptionLimit());
                        repAgencyBondEntity1.setUnderwritingPrice(decMiddleCustodyVO2.getSecondUnderwritPriceBond());
                        Integer Discount = parGencyBondDiscountDao.queryDiscount(currentStageId, bondType);
                        repAgencyBondEntity1.setDiscount(Discount);
                        //承销金额*1%
                        //underwritingMount*0.01;
                        //获得承销资格,需支付固定成本1千万
                        repProfitEntity.setSubtractNonbusExpend(subtractNonbusExpend - 10);

//                            repAgencyBondDao.insert(repAgencyBondEntity1);

                        //代理债券发行业务数据表-乙债券
                        preMap.put("agencyBondsYi", repAgencyBondEntity1);
                    }
                    if ("丙债券".equals(parAgencyBondIssueVO.getTypesBonds())) {
                        RepAgencyBondEntity repAgencyBondEntity2 = new RepAgencyBondEntity();
                        repAgencyBondEntity2.setCurrentProjectId(currentProjectId);
                        repAgencyBondEntity2.setCurrentStageId(currentStageId);
                        repAgencyBondEntity2.setCurrentTeamId(decMiddleCustodyVO3.getCurrentTeamId());
                        repAgencyBondEntity2.setBondType(parAgencyBondIssueVO.getTypesBonds());
                        String bondType = repAgencyBondEntity2.getBondType();
                        repAgencyBondEntity2.setCouponRate(parAgencyBondIssueVO.getCouponRate());
                        repAgencyBondEntity2.setDeadline(parAgencyBondIssueVO.getDeadline());
                        repAgencyBondEntity2.setCreditRating(parAgencyBondIssueVO.getCreditRating());
                        repAgencyBondEntity2.setIssueAmount(parAgencyBondIssueVO.getIssuance());
                        //承销金额
                        repAgencyBondEntity2.setUnderwritingMount(parAgencyBondIssueVO.getIssuance());
                        String teamNameFirst = departmentDao.queryName(decMiddleCustodyVO3.getCurrentTeamId());

                        repAgencyBondEntity2.setUnderwritingBank(teamNameFirst);
                        //认购额度？？？
                        repAgencyBondEntity2.setSubscriptionLimit(parAgencyBondIssueVO.getSubscriptionLimit());
                        repAgencyBondEntity2.setUnderwritingPrice(decMiddleCustodyVO3.getThirdUnderwritPriceBond());
                        Integer Discount = parGencyBondDiscountDao.queryDiscount(currentStageId, bondType);
                        repAgencyBondEntity2.setDiscount(Discount);
                        //承销金额*1%
                        //underwritingMount*0.01;
                        //获得承销资格,需支付固定成本1千万
                        repProfitEntity.setSubtractNonbusExpend(subtractNonbusExpend - 10);

//                            repAgencyBondDao.insert(repAgencyBondEntity2);
                        //代理债券发行业务数据表-丙债券
                        preMap.put("agencyBondsBing", repAgencyBondEntity2);
                    }
                }

            }


        }
        RepAgencyBondEntity repAgencyBondEntity3 = new RepAgencyBondEntity();
        repAgencyBondEntity3.setCurrentProjectId(currentProjectId);
        repAgencyBondEntity3.setCurrentTeamId(currentTeamId);
        repAgencyBondEntity3.setCurrentStageId(currentStageId);

        repAgencyBondEntity3.setCouponRate(0.0D);
        repAgencyBondEntity3.setDeadline(0);
        repAgencyBondEntity3.setCreditRating("");
        repAgencyBondEntity3.setIssueAmount(0);
        repAgencyBondEntity3.setUnderwritingBank("");
        repAgencyBondEntity3.setUnderwritingMount(0);
        repAgencyBondEntity3.setSubscriptionLimit(0.0D);
        repAgencyBondEntity3.setUnderwritingPrice(0.0D);
        repAgencyBondEntity3.setDiscount(0);
        if (preMap.get("agencyBondsJia") == null) {
            repAgencyBondEntity3.setBondType("甲债券");
            preMap.put("agencyBondsJia", repAgencyBondEntity3);
        }
        if (preMap.get("agencyBondsYi") == null) {
            repAgencyBondEntity3.setBondType("乙债券");
            preMap.put("agencyBondsYi", repAgencyBondEntity3);
        }
        if (preMap.get("agencyBondsBing") == null) {
            repAgencyBondEntity3.setBondType("丙债券");
            preMap.put("agencyBondsBing", repAgencyBondEntity3);
        }
//            repProfitDao.insert(repProfitEntity);
        //利润表
        preMap.put("profit", repProfitEntity);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //流动性报表
        RepLiquidityCashEntity repLiquidityCashEntity = new RepLiquidityCashEntity();
        repLiquidityCashEntity.setCurrentProjectId(currentProjectId);
        repLiquidityCashEntity.setCurrentTeamId(currentTeamId);
        repLiquidityCashEntity.setCurrentStageId(currentStageId);
        //同业存放款项
        repLiquidityCashEntity.setInterbankDeposit(interbankDeposit);
        //同业拆入
        repLiquidityCashEntity.setInterBank(interBank);
        //短期存款
        repLiquidityCashEntity.setShortDeposit(shortDeposit);
        //长期存款
        repLiquidityCashEntity.setLongDeposit(longDeposit);
        //短期储蓄存款的50%
        repLiquidityCashEntity.setShortSavingsDeposit((int) Math.round((shortSavingsDeposit * 0.5)));
        //长期储蓄存款
        repLiquidityCashEntity.setLongSavingsDeposit(longSavingsDeposit);
        //短期债务总计
        repLiquidityCashEntity.setTotalShortDebt(interbankDeposit + interBank + shortDeposit + longDeposit + shortSavingsDeposit + longSavingsDeposit);
        //现金流动性要求
        repLiquidityCashEntity.setCashLiquidityRequirements((int) Math.round(((interbankDeposit + interBank + shortDeposit + longDeposit + shortSavingsDeposit + longSavingsDeposit) * 0.025)));
        Integer cashLiquidityRequirements = repLiquidityCashEntity.getCashLiquidityRequirements();

        //资产除现金
        Integer assets = dueCentralBank + depositInterbank + loanTrade + shortLoan + interestReceivable + discount + shortInvestment + longTotalAsset1 + intangibleAotherAssets1;
        //负债除向中央与银行借款
        Integer nliabilities = shortDeposit + shortSavingsDeposit + interbankDeposit + interBank + depositShortMargin + paymentInterest + totalLongLiabilities + totalShareholdersEquity;

        Integer cash = 0;
        Integer borrowingsCentralBanka = 0;
        if (assets > nliabilities) {
            repLiquidityCashEntity.setSolvency(0);
            //现金等于现金流动性要求
            cash = cashLiquidityRequirements;
            borrowingsCentralBanka = assets - nliabilities + cashLiquidityRequirements;
        } else {
            //现金等于负债减资产
            cash = nliabilities - assets;
            repLiquidityCashEntity.setSolvency(cash);
            borrowingsCentralBanka = 0;
        }
        //现金
        repBalanceSheetEntity.setCash(cash);
        Integer casha = repBalanceSheetEntity.getCash();
        //流动资产合计
        repBalanceSheetEntity.setPropertySum(casha + dueCentralBank +
                depositInterbank +
                loanTrade +
                shortLoan +
                interestReceivable +
                discount +
                shortInvestment);
        //资产总计
        Integer propertySum = repBalanceSheetEntity.getPropertySum();
        Integer longTotalAsset = repBalanceSheetEntity.getLongTotalAsset();
        Integer intangibleAotherAssets = repBalanceSheetEntity.getIntangibleAotherAssets();
        repBalanceSheetEntity.setTotalAssets(propertySum + longTotalAsset + intangibleAotherAssets);
        Integer totalAssets = repBalanceSheetEntity.getTotalAssets();

        //向中央银行借款
        repBalanceSheetEntity.setBorrowingsCentralBank(borrowingsCentralBanka);
        Integer borrowingsCentralBank = repBalanceSheetEntity.getBorrowingsCentralBank();
        //流动负债合计
        repBalanceSheetEntity.setTotalCurrentLiability(shortDeposit + shortSavingsDeposit + borrowingsCentralBank + interbankDeposit + interBank + depositShortMargin + paymentInterest);
        Integer totalCurrentLiability = repBalanceSheetEntity.getTotalCurrentLiability();
        //负债合计
        repBalanceSheetEntity.setTotalLiabilities(totalCurrentLiability + totalLongLiabilities);
        Integer totalLiabilities = repBalanceSheetEntity.getTotalLiabilities();
        //负债和股东权益总计
        repBalanceSheetEntity.setTotalLiabilitiesEquityIndebted(totalLiabilities + totalShareholdersEquity);
        ;

//            repBalanceSheetDao.insert(repBalanceSheetEntity);
        //资产负债表
        preMap.put("asset", repBalanceSheetEntity);
        //清偿力
        Integer solvency = repLiquidityCashEntity.getSolvency();
        //现金清偿力
        repLiquidityCashEntity.setCashSolvency(solvency - cashLiquidityRequirements);

//            repLiquidityCashDao.insert(repLiquidityCashEntity);
        //流动性报表-现金流动性
        preMap.put("liquiditycash", repLiquidityCashEntity);

        //流动性报表-总体
        RepLiquidityTotalityEntity repLiquidityTotalityEntity = new RepLiquidityTotalityEntity();
        repLiquidityTotalityEntity.setCurrentProjectId(currentProjectId);
        repLiquidityTotalityEntity.setCurrentTeamId(currentTeamId);
        repLiquidityTotalityEntity.setCurrentStageId(currentStageId);
        //存放中央银行款项
        repLiquidityTotalityEntity.setDueCentralBank((int) Math.round(dueCentralBank - (shortDeposit + shortSavingsDeposit + depositShortMargin + longDeposit + longSavingsDeposit + depositLongMargin) * 0.048));
        Integer dueCentralBank1 = repLiquidityTotalityEntity.getDueCentralBank();
        //存放同业款项
        repLiquidityTotalityEntity.setDepositInterbank((int) Math.round((depositInterbank * 0.5)));
        Integer depositInterbank1 = repLiquidityTotalityEntity.getDepositInterbank();
        //拆放同业
        repLiquidityTotalityEntity.setLoanTrade((int) Math.round((loanTrade * 0.7)));
        Integer loanTrade1 = repLiquidityTotalityEntity.getLoanTrade();
        //贴现
        repLiquidityTotalityEntity.setDiscount((int) Math.round((discount * 0.5)));
        Integer discount1 = repLiquidityTotalityEntity.getDiscount();
        //短期投资
        repLiquidityTotalityEntity.setShortInvestment((int) Math.round((shortInvestment * 0.5)));
        Integer shortInvestment1 = repLiquidityTotalityEntity.getShortInvestment();
        //长期债权投资
        repLiquidityTotalityEntity.setLongDebtInvestment((int) Math.round((longDebtInvestment * 0.3)));
        Integer longDebtInvestment1 = repLiquidityTotalityEntity.getLongDebtInvestment();
        //长期股权投资
        repLiquidityTotalityEntity.setLongEquityInvestment((int) Math.round((longEquityInvestment * 0.2)));
        Integer longEquityInvestment1 = repLiquidityTotalityEntity.getLongEquityInvestment();
        //易变现资产总计
        repLiquidityTotalityEntity.setTotalRealizedAssets(dueCentralBank1 + depositInterbank1 + loanTrade1 + discount1 + shortInvestment1 + longDebtInvestment1 + longEquityInvestment1);
        Integer totalRealizedAssets = repLiquidityTotalityEntity.getTotalRealizedAssets();

        //同业存放款项
        repLiquidityTotalityEntity.setInterbankDeposit(interbankDeposit);
        Integer interbankDeposit1 = repLiquidityTotalityEntity.getInterbankDeposit();
        //同业拆入
        repLiquidityTotalityEntity.setInterBank((int) Math.round((interBank * 0.75)));
        Integer interBank1 = repLiquidityTotalityEntity.getInterBank();
        //短期存款
        repLiquidityTotalityEntity.setShortDeposit((int) Math.round((shortDeposit * 0.5)));
        Integer shortDeposit1 = repLiquidityTotalityEntity.getShortDeposit();
        //长期存款
        repLiquidityTotalityEntity.setLongDeposit((int) Math.round((longDeposit * 0.15)));
        Integer longDeposit1 = repLiquidityTotalityEntity.getLongDeposit();
        //短期储蓄存款
        repLiquidityTotalityEntity.setShortSavingsDeposit((int) Math.round((shortSavingsDeposit * 0.3)));
        Integer shortSavingsDeposit1 = repLiquidityTotalityEntity.getShortSavingsDeposit();
        //长期储蓄存款
        repLiquidityTotalityEntity.setLongSavingsDeposit((int) Math.round((longSavingsDeposit * 0.15)));
        Integer longSavingsDeposit1 = repLiquidityTotalityEntity.getLongSavingsDeposit();
        //存入短期及长期保证金
        repLiquidityTotalityEntity.setDepositShortLongMargin((int) Math.round(((depositShortMargin + depositLongMargin) * 0.1)));
        Integer depositShortLongMargin = repLiquidityTotalityEntity.getDepositShortLongMargin();
        //向中央银行借款
        repLiquidityTotalityEntity.setBorrowingsCentralBank(borrowingsCentralBank);
        Integer borrowingsCentralBank1 = repLiquidityTotalityEntity.getBorrowingsCentralBank();
        //流动性负债总计
        repLiquidityTotalityEntity.setTotalCurrentLiabilities(interbankDeposit1 + interBank1 + shortDeposit1 + longDeposit1 + shortSavingsDeposit1 + longSavingsDeposit1 + depositShortLongMargin + borrowingsCentralBank1);
        Integer totalCurrentLiabilities = repLiquidityTotalityEntity.getTotalCurrentLiabilities();

        //总体流动性要求
        repLiquidityTotalityEntity.setOverallLiquidityRequirements((int) Math.round((totalCurrentLiabilities * 0.3)));
        Integer overallLiquidityRequirements = repLiquidityTotalityEntity.getOverallLiquidityRequirements();

        //清偿力
        repLiquidityTotalityEntity.setSolvency(solvency);
        Integer solvency1 = repLiquidityTotalityEntity.getSolvency();
        //总体清偿力
        repLiquidityTotalityEntity.setTotalitySolvency(totalRealizedAssets + solvency1);
        Integer totalitySolvency = repLiquidityTotalityEntity.getTotalitySolvency();

        //总体流动性盈余/不足
        repLiquidityTotalityEntity.setOverallLiquiditySurplusShortage(totalitySolvency - overallLiquidityRequirements);
//            repLiquidityTotalityDao.insert(repLiquidityTotalityEntity);
        //流动性报表-总体流动性
        preMap.put("liquidityPopulation", repLiquidityTotalityEntity);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //资本充足率报告
        RepCapitalAdequacyEntity repCapitalAdequacyEntity = new RepCapitalAdequacyEntity();
        repCapitalAdequacyEntity.setCurrentProjectId(currentProjectId);
        repCapitalAdequacyEntity.setCurrentTeamId(currentTeamId);
        repCapitalAdequacyEntity.setCurrentStageId(currentStageId);
        repCapitalAdequacyEntity.setPropertyRisk("账面价值");
        repCapitalAdequacyEntity.setCash(cash);
        repCapitalAdequacyEntity.setDueCentralBank(dueCentralBank);
        repCapitalAdequacyEntity.setDepositInterbank(depositInterbank);
        repCapitalAdequacyEntity.setLoanTrade(loanTrade);

        repCapitalAdequacyEntity.setShortMortgageLoan((int) Math.round((shortMortgage * d)));
        Integer shortMortgageLoan = repCapitalAdequacyEntity.getShortMortgageLoan();

        repCapitalAdequacyEntity.setShortHypothecatedLoan((int) Math.round((shortPledgeLoans * z)));
        Integer shortHypothecatedLoan = repCapitalAdequacyEntity.getShortHypothecatedLoan();

        repCapitalAdequacyEntity.setShortGuaranteeLoan((int) Math.round((shortGuaranteedLoan * b)));
        Integer shortGuaranteeLoan = repCapitalAdequacyEntity.getShortGuaranteeLoan();

        repCapitalAdequacyEntity.setShortCreditLoan((int) Math.round((shortCreditLoan * x)));
        Integer shortCreditLoan1 = repCapitalAdequacyEntity.getShortCreditLoan();

        repCapitalAdequacyEntity.setDiscount(discount);

        repCapitalAdequacyEntity.setMlongMortgageLoan((int) Math.round((mlongMortgage * zcd)));
        Integer mlongMortgageLoan = repCapitalAdequacyEntity.getMlongMortgageLoan();

        repCapitalAdequacyEntity.setMlongHypothecatedLoan((int) Math.round((mlongPledgeLoans * zcz)));
        Integer mlongHypothecatedLoan = repCapitalAdequacyEntity.getMlongHypothecatedLoan();

        repCapitalAdequacyEntity.setMlongGuaranteeLoan((int) Math.round((mlongGuaranteedLoan * zcb)));
        Integer mlongGuaranteeLoan = repCapitalAdequacyEntity.getMlongGuaranteeLoan();

        repCapitalAdequacyEntity.setMlongCreditLoan((int) Math.round((mlongCreditLoan * zcx)));
        Integer mlongCreditLoan1 = repCapitalAdequacyEntity.getMlongCreditLoan();

        //如为空 ，从0阶段结果表取
        if (decInvestmentLongBondVO.size() == 0) {
            ParInitialDataInvestbusBondVO parInitialDataInvestbusBondVO = parInitialDataInvestbusBondDao.selectInvestbusOperatBondData(1L, "总计", "短期");
            repCapitalAdequacyEntity.setCurrentInvestmentBond(parInitialDataInvestbusBondVO.getRealValue());
        } else {
            repCapitalAdequacyEntity.setCurrentInvestmentBond(repInvestbusOperatDataBondEntity.getRealValue());
        }
        Integer currentInvestmentBond = repCapitalAdequacyEntity.getCurrentInvestmentBond();
        if (decInvestmentLongSharesVO == null) {
            ParInitialDataInvestbusStockVO parInitialDataInvestbusStockVO = parInitialDataInvestbusStockDao.selectInvestbusOperatData(1L, "期末持有", "短期");
            repCapitalAdequacyEntity.setCurrentInvestmentStock(parInitialDataInvestbusStockVO.getStockCost());
        } else {
            repCapitalAdequacyEntity.setCurrentInvestmentStock(decInvestmentLongSharesVO.getBuySell());
        }
        Integer currentInvestmentStock = repCapitalAdequacyEntity.getCurrentInvestmentStock();

        repCapitalAdequacyEntity.setLongDebtInvestment(longDebtInvestment);
        repCapitalAdequacyEntity.setLongEquityInvestment(longEquityInvestment);
        repCapitalAdequacyEntity.setFixedAssets(fixedAssets);
        repCapitalAdequacyEntity.setIntangibleAssets(intangibleAssets);
        repCapitalAdequacyEntity.setOtherAssets(otherAssets);
        //...记录点，是否取零阶段
        if (decInvestmentLongBondVO.size() == 0 ||  decInvestmentLongSharesVO == null||decInvestmentLongSharesVO.getBuySell() == null) {
            repCapitalAdequacyEntity.setTotal(cash + dueCentralBank + depositInterbank + loanTrade + shortLoan + discount + mlongLoan + 0 + longDebtInvestment + longEquityInvestment + fixedAssets + intangibleAssets + otherAssets);
        } else {
            repCapitalAdequacyEntity.setTotal(cash + dueCentralBank + depositInterbank + loanTrade + shortLoan + discount + mlongLoan + decInvestmentLongSharesVO.getBuySell() + repInvestbusOperatDataBondEntity.getRealValue() + longDebtInvestment + longEquityInvestment + fixedAssets + intangibleAssets + otherAssets);
        }
        repCapitalAdequacyEntity.setCapitalAdequacyRequirements(null);
        repCapitalAdequacyEntity.setCapitalSourceCoreStock(null);
        repCapitalAdequacyEntity.setCapitalSourceCoreCapitalReserve(null);
        repCapitalAdequacyEntity.setCapitalSourceCoreSurplusReserve(null);
        repCapitalAdequacyEntity.setCapitalSourceCoreUnProfit(null);
        repCapitalAdequacyEntity.setCapitalSourceLossReserves(null);
        repCapitalAdequacyEntity.setCapitalSourceSubordBond(null);
        repCapitalAdequacyEntity.setCapitalSourceTotal(null);
        repCapitalAdequacyEntity.setCapitalSurplusShortage(null);
//            repCapitalAdequacyDao.insert(repCapitalAdequacyEntity);
        //资本充足率报告-账面价值
        preMap.put("capitalBook", repCapitalAdequacyEntity);

        RepCapitalAdequacyEntity repCapitalAdequacyEntity1 = new RepCapitalAdequacyEntity();
        repCapitalAdequacyEntity1.setCurrentProjectId(currentProjectId);
        repCapitalAdequacyEntity1.setCurrentTeamId(currentTeamId);
        repCapitalAdequacyEntity1.setCurrentStageId(currentStageId);
        repCapitalAdequacyEntity1.setPropertyRisk("风险加权系数%");
        repCapitalAdequacyEntity1.setCash(parCapitalAdequacyVO.getCash());
        repCapitalAdequacyEntity1.setDueCentralBank(parCapitalAdequacyVO.getDueCentralBank());
        repCapitalAdequacyEntity1.setDepositInterbank(parCapitalAdequacyVO.getDepositInterbank());
        repCapitalAdequacyEntity1.setLoanTrade(parCapitalAdequacyVO.getLoanTrade());
        repCapitalAdequacyEntity1.setShortMortgageLoan(parCapitalAdequacyVO.getShortMortgageLoan());
        repCapitalAdequacyEntity1.setShortHypothecatedLoan(parCapitalAdequacyVO.getShortHypothecatedLoan());
        repCapitalAdequacyEntity1.setShortGuaranteeLoan(parCapitalAdequacyVO.getShortGuaranteeLoan());
        repCapitalAdequacyEntity1.setShortCreditLoan(parCapitalAdequacyVO.getShortCreditLoan());
        repCapitalAdequacyEntity1.setDiscount(parCapitalAdequacyVO.getDiscount());
        repCapitalAdequacyEntity1.setMlongMortgageLoan(parCapitalAdequacyVO.getMlongMortgageLoan());
        repCapitalAdequacyEntity1.setMlongHypothecatedLoan(parCapitalAdequacyVO.getMlongHypothecatedLoan());
        repCapitalAdequacyEntity1.setMlongGuaranteeLoan(parCapitalAdequacyVO.getMlongGuaranteeLoan());
        repCapitalAdequacyEntity1.setMlongCreditLoan(parCapitalAdequacyVO.getMlongCreditLoan());
        repCapitalAdequacyEntity1.setCurrentInvestmentBond(parCapitalAdequacyVO.getCurrentInvestmentBond());
        repCapitalAdequacyEntity1.setCurrentInvestmentStock(parCapitalAdequacyVO.getCurrentInvestmentStock());
        repCapitalAdequacyEntity1.setLongDebtInvestment(parCapitalAdequacyVO.getLongDebtInvestment());
        repCapitalAdequacyEntity1.setLongEquityInvestment(parCapitalAdequacyVO.getLongEquityInvestment());
        repCapitalAdequacyEntity1.setFixedAssets(parCapitalAdequacyVO.getFixedAssets());
        repCapitalAdequacyEntity1.setIntangibleAssets(parCapitalAdequacyVO.getIntangibleAssets());
        repCapitalAdequacyEntity1.setOtherAssets(parCapitalAdequacyVO.getOtherAssets());
        repCapitalAdequacyEntity1.setTotal(parCapitalAdequacyVO.getTotal());
        repCapitalAdequacyEntity1.setCapitalAdequacyRequirements(null);
        repCapitalAdequacyEntity1.setCapitalSourceCoreStock(null);
        repCapitalAdequacyEntity1.setCapitalSourceCoreCapitalReserve(null);
        repCapitalAdequacyEntity1.setCapitalSourceCoreSurplusReserve(null);
        repCapitalAdequacyEntity1.setCapitalSourceCoreUnProfit(null);
        repCapitalAdequacyEntity1.setCapitalSourceLossReserves(null);
        repCapitalAdequacyEntity1.setCapitalSourceSubordBond(null);
        repCapitalAdequacyEntity1.setCapitalSourceTotal(null);
        repCapitalAdequacyEntity1.setCapitalSurplusShortage(null);
//            repCapitalAdequacyDao.insert(repCapitalAdequacyEntity1);
        //资本充足率报告-风险加权系数%
        preMap.put("capitalRisk", repCapitalAdequacyEntity1);

        RepCapitalAdequacyEntity repCapitalAdequacyEntity2 = new RepCapitalAdequacyEntity();
        repCapitalAdequacyEntity2.setCurrentProjectId(currentProjectId);
        repCapitalAdequacyEntity2.setCurrentTeamId(currentTeamId);
        repCapitalAdequacyEntity2.setCurrentStageId(currentStageId);
        repCapitalAdequacyEntity2.setPropertyRisk("风险加权资产");
        repCapitalAdequacyEntity2.setCash(cash * parCapitalAdequacyVO.getCash());
        Integer cash2 = repCapitalAdequacyEntity2.getCash();
        repCapitalAdequacyEntity2.setDueCentralBank(dueCentralBank * parCapitalAdequacyVO.getDueCentralBank());
        Integer dueCentralBank2 = repCapitalAdequacyEntity2.getDueCentralBank();
        repCapitalAdequacyEntity2.setDepositInterbank(depositInterbank * parCapitalAdequacyVO.getDepositInterbank());
        Integer depositInterbank2 = repCapitalAdequacyEntity2.getDepositInterbank();
        repCapitalAdequacyEntity2.setLoanTrade(loanTrade * parCapitalAdequacyVO.getLoanTrade());
        Integer loanTrade2 = repCapitalAdequacyEntity2.getLoanTrade();
        repCapitalAdequacyEntity2.setShortMortgageLoan((int) Math.round((shortMortgage * d) * parCapitalAdequacyVO.getShortMortgageLoan()));
        Integer shortMortgageLoan2 = repCapitalAdequacyEntity2.getShortMortgageLoan();
        repCapitalAdequacyEntity2.setShortHypothecatedLoan((int) Math.round((shortPledgeLoans * z) * parCapitalAdequacyVO.getShortHypothecatedLoan()));
        Integer shortHypothecatedLoan2 = repCapitalAdequacyEntity2.getShortHypothecatedLoan();
        repCapitalAdequacyEntity2.setShortGuaranteeLoan((int) Math.round((shortGuaranteedLoan * b) * parCapitalAdequacyVO.getShortGuaranteeLoan()));
        Integer shortGuaranteeLoan2 = repCapitalAdequacyEntity2.getShortGuaranteeLoan();
        repCapitalAdequacyEntity2.setShortCreditLoan((int) Math.round((shortCreditLoan * x) * parCapitalAdequacyVO.getShortCreditLoan()));
        Integer shortCreditLoan2 = repCapitalAdequacyEntity2.getShortCreditLoan();
        repCapitalAdequacyEntity2.setDiscount(discount * parCapitalAdequacyVO.getDiscount());
        Integer discount2 = repCapitalAdequacyEntity2.getDiscount();
        repCapitalAdequacyEntity2.setMlongMortgageLoan((int) Math.round((mlongMortgage * zcd) * parCapitalAdequacyVO.getMlongMortgageLoan()));
        Integer mlongMortgageLoan2 = repCapitalAdequacyEntity2.getMlongMortgageLoan();
        repCapitalAdequacyEntity2.setMlongHypothecatedLoan((int) Math.round((mlongPledgeLoans * zcz) * parCapitalAdequacyVO.getMlongHypothecatedLoan()));
        Integer mlongHypothecatedLoan2 = repCapitalAdequacyEntity2.getMlongHypothecatedLoan();
        repCapitalAdequacyEntity2.setMlongGuaranteeLoan((int) Math.round((mlongGuaranteedLoan * zcb) * parCapitalAdequacyVO.getMlongGuaranteeLoan()));
        Integer mlongGuaranteeLoan2 = repCapitalAdequacyEntity2.getMlongGuaranteeLoan();
        repCapitalAdequacyEntity2.setMlongCreditLoan((int) Math.round((mlongCreditLoan * zcx) * parCapitalAdequacyVO.getMlongCreditLoan()));
        Integer mlongCreditLoan2 = repCapitalAdequacyEntity2.getMlongCreditLoan();
        repCapitalAdequacyEntity2.setCurrentInvestmentBond(currentInvestmentBond * parCapitalAdequacyVO.getCurrentInvestmentBond());
        Integer currentInvestmentBond2 = repCapitalAdequacyEntity2.getCurrentInvestmentBond();
        repCapitalAdequacyEntity2.setCurrentInvestmentStock(currentInvestmentStock * parCapitalAdequacyVO.getCurrentInvestmentStock());
        Integer currentInvestmentStock2 = repCapitalAdequacyEntity2.getCurrentInvestmentStock();
        repCapitalAdequacyEntity2.setLongDebtInvestment(longDebtInvestment * parCapitalAdequacyVO.getLongDebtInvestment());
        Integer longDebtInvestment2 = repCapitalAdequacyEntity2.getLongDebtInvestment();
        repCapitalAdequacyEntity2.setLongEquityInvestment(longEquityInvestment * parCapitalAdequacyVO.getLongEquityInvestment());
        Integer longEquityInvestment2 = repCapitalAdequacyEntity2.getLongEquityInvestment();
        repCapitalAdequacyEntity2.setFixedAssets(fixedAssets * parCapitalAdequacyVO.getFixedAssets());
        Integer fixedAssets2 = repCapitalAdequacyEntity2.getFixedAssets();
        repCapitalAdequacyEntity2.setIntangibleAssets(intangibleAssets * parCapitalAdequacyVO.getIntangibleAssets());
        Integer intangibleAssets2 = repCapitalAdequacyEntity2.getIntangibleAssets();
        repCapitalAdequacyEntity2.setOtherAssets(otherAssets * parCapitalAdequacyVO.getOtherAssets());
        Integer otherAssets2 = repCapitalAdequacyEntity2.getOtherAssets();
        repCapitalAdequacyEntity2.setTotal(cash2 + dueCentralBank2 + depositInterbank2 + loanTrade2 + shortMortgageLoan2 + shortHypothecatedLoan2 + shortGuaranteeLoan2 + shortCreditLoan2 + discount2 + mlongMortgageLoan2 + mlongHypothecatedLoan2 + mlongGuaranteeLoan2 + mlongCreditLoan2 + currentInvestmentBond2 + currentInvestmentStock2 + longDebtInvestment2 + longEquityInvestment2 + fixedAssets2 + intangibleAssets2 + otherAssets2);
        Integer total2 = repCapitalAdequacyEntity2.getTotal();
        //为总计的8%
        repCapitalAdequacyEntity2.setCapitalAdequacyRequirements((int) Math.round((total2 * 0.08)));
        repCapitalAdequacyEntity2.setCapitalSourceCoreStock(capital);
        repCapitalAdequacyEntity2.setCapitalSourceCoreCapitalReserve(capitalReserve);
        repCapitalAdequacyEntity2.setCapitalSourceCoreSurplusReserve(surplusReserve);
        repCapitalAdequacyEntity2.setCapitalSourceCoreUnProfit(undistributedProfit);
        repCapitalAdequacyEntity2.setCapitalSourceLossReserves(lossReserves);
        repCapitalAdequacyEntity2.setCapitalSourceSubordBond(paymentSubBond);
        repCapitalAdequacyEntity2.setCapitalSourceTotal(capital + capitalReserve + surplusReserve + undistributedProfit + lossReserves + paymentSubBond);
        repCapitalAdequacyEntity2.setCapitalSurplusShortage((int) Math.round((capital + capitalReserve + surplusReserve + undistributedProfit + lossReserves + paymentSubBond - (total2) * 0.08)));
//            repCapitalAdequacyDao.insert(repCapitalAdequacyEntity2);
        //资本充足率报告-风险加权资产
        preMap.put("capitalAssets", repCapitalAdequacyEntity2);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //市场营销数据 3阶段开启
//        if (currentStageId >= 4) {
//
//            Integer loansBusineDevelopLess = decMarketStrategyVO.getLoansBusineDevelopLess();
//            Integer loansBusineDevelopBetween = decMarketStrategyVO.getLoansBusineDevelopBetween();
//            Integer loansBusineDevelopGreater = decMarketStrategyVO.getLoansBusineDevelopGreater();
//            Integer loansBusineDevelopSmall = decMarketStrategyVO.getLoansBusineDevelopSmall();
//            Integer loansBusineDevelopMedium = decMarketStrategyVO.getLoansBusineDevelopMedium();
//            Integer loansBusineDevelopMajor = decMarketStrategyVO.getLoansBusineDevelopMajor();
//            Integer loansBusineDevelopPublic = decMarketStrategyVO.getLoansBusineDevelopPublic();
//            //权重总和
//            int weightSum = loansBusineDevelopLess + loansBusineDevelopBetween + loansBusineDevelopGreater + loansBusineDevelopSmall
//                    + loansBusineDevelopMedium + loansBusineDevelopMajor + loansBusineDevelopPublic;
//
//            //短期贷款金额合计
//            int shortTermLoanSum = shortMortgageLoan + shortHypothecatedLoan + shortGuaranteeLoan + shortCreditLoan1;
//
//            Map<String, Integer> mapTo = new HashMap<>();
//            mapTo.put("短期贷款-抵押贷款", shortMortgageLoan);
//            mapTo.put("短期贷款-质押贷款", shortHypothecatedLoan);
//            mapTo.put("短期贷款-保证贷款", shortGuaranteeLoan);
//            mapTo.put("短期贷款-信用贷款", shortCreditLoan1);
//            mapTo.put("短期贷款-小计", shortTermLoanSum);
//
//            //中长期贷款金额合计
//            int mLongLoanSum = mlongMortgageLoan + mlongHypothecatedLoan + mlongGuaranteeLoan + mlongCreditLoan1;
//
//            mapTo.put("中长期贷款-抵押贷款", mlongMortgageLoan);
//            mapTo.put("中长期贷款-质押贷款", mlongHypothecatedLoan);
//            mapTo.put("中长期贷款-保证贷款", mlongGuaranteeLoan);
//            mapTo.put("中长期贷款-信用贷款", mlongCreditLoan1);
//            mapTo.put("中长期贷款-小计", mLongLoanSum);
//
//            mapTo.put("客户存款-短期存款", shortDeposit);
//            mapTo.put("客户存款-长期存款", longDeposit);
//            //客户存款金额合计
//            int mLongShortLoanSum = shortDeposit + longDeposit;
//            mapTo.put("客户存款-小计", mLongShortLoanSum);
//
//
//            mapTo.put("储蓄存款-短期储蓄", shortSavingsDeposit);
//            mapTo.put("储蓄存款-长期储蓄", longSavingsDeposit);
//            //储蓄存款金额合计
//            int mLongShortSavingsLoanSum = shortSavingsDeposit + longSavingsDeposit;
//            mapTo.put("储蓄存款-小计", mLongShortSavingsLoanSum);
//
//            //资产托管业务金额
//            int assetCustodyBusiness = (int) Math.round(custodyBusinessProcedureRiseShare * custodyBusinessProcedureLoan);
//            //担保业务金额
//            int guaranteeBusiness = (int) Math.round(guaranteeBusinessProcedureRiseShare * guaranteeBusinessProcedureLoan);
//
//            mapTo.put("委托资产托管业务", assetCustodyBusiness);
//            mapTo.put("担保业务", guaranteeBusiness);
//
//            //客户存款权重总和
//            int weightCunSum = loansBusineDevelopSmall + loansBusineDevelopMedium + loansBusineDevelopMajor + loansBusineDevelopPublic;
//
//            //储蓄存款权重总和
//            int ageWeightSum = loansBusineDevelopLess + loansBusineDevelopBetween + loansBusineDevelopGreater;
//
//            //客户市场营业数据
//            for (String key : mapTo.keySet()) {
//                RepMarketDataCustomerBazaarEntity repMarketDataCustomerBazaarEntity = new RepMarketDataCustomerBazaarEntity();
//                repMarketDataCustomerBazaarEntity.setCurrentProjectId(currentProjectId);
//                repMarketDataCustomerBazaarEntity.setCurrentTeamId(currentTeamId);
//                repMarketDataCustomerBazaarEntity.setCurrentStageId(currentStageId);
//                repMarketDataCustomerBazaarEntity.setCustomerMarketBusinessData(key);
//
//                if ("客户存款-短期存款".equals(key) || "客户存款-长期存款".equals(key) || "客户存款-小计".equals(key)) {
//                    //小型企业
//                    repMarketDataCustomerBazaarEntity.setSmallEnterprise((int) Math.round(((double) loansBusineDevelopSmall / weightCunSum) * mapTo.get(key)));
//                    //中型企业
//                    repMarketDataCustomerBazaarEntity.setMediumEnterprise((int) Math.round(((double) loansBusineDevelopMedium / weightCunSum) * mapTo.get(key)));
//                    //大型企业
//                    repMarketDataCustomerBazaarEntity.setMajorEnterprise((int) Math.round(((double) loansBusineDevelopMajor / weightCunSum) * mapTo.get(key)));
//                    //公共机构
//                    repMarketDataCustomerBazaarEntity.setPublicInstitution((int) Math.round(((double) loansBusineDevelopPublic / weightCunSum) * mapTo.get(key)));
//                    //金额合计
//                    repMarketDataCustomerBazaarEntity.setAmountSum((int) Math.round(mapTo.get(key)));
//                } else if ("储蓄存款-短期储蓄".equals(key) || "储蓄存款-长期储蓄".equals(key) || "储蓄存款-小计".equals(key)) {
//                    //<35岁
//                    repMarketDataCustomerBazaarEntity.setLessAge((int) Math.round(((double) loansBusineDevelopLess / ageWeightSum) * mapTo.get(key)));
//                    //35~55岁
//                    repMarketDataCustomerBazaarEntity.setBetweenAge((int) Math.round(((double) loansBusineDevelopBetween / ageWeightSum) * mapTo.get(key)));
//                    //>55岁
//                    repMarketDataCustomerBazaarEntity.setGreaterAge((int) Math.round(((double) loansBusineDevelopGreater / ageWeightSum) * mapTo.get(key)));
//                    //金额合计
//                    repMarketDataCustomerBazaarEntity.setAmountSum((int) Math.round(mapTo.get(key)));
//                } else {
//                    //<35岁
//                    repMarketDataCustomerBazaarEntity.setLessAge((int) Math.round(((double) loansBusineDevelopLess / weightSum) * mapTo.get(key)));
//                    //35~55岁
//                    repMarketDataCustomerBazaarEntity.setBetweenAge((int) Math.round(((double) loansBusineDevelopBetween / weightSum) * mapTo.get(key)));
//                    //>55岁
//                    repMarketDataCustomerBazaarEntity.setGreaterAge((int) Math.round(((double) loansBusineDevelopGreater / weightSum) * mapTo.get(key)));
//                    //小型企业
//                    repMarketDataCustomerBazaarEntity.setSmallEnterprise((int) Math.round(((double) loansBusineDevelopSmall / weightSum) * mapTo.get(key)));
//                    //中型企业
//                    repMarketDataCustomerBazaarEntity.setMediumEnterprise((int) Math.round(((double) loansBusineDevelopMedium / weightSum) * mapTo.get(key)));
//                    //大型企业
//                    repMarketDataCustomerBazaarEntity.setMajorEnterprise((int) Math.round(((double) loansBusineDevelopMajor / weightSum) * mapTo.get(key)));
//                    //公共机构
//                    repMarketDataCustomerBazaarEntity.setPublicInstitution((int) Math.round(((double) loansBusineDevelopPublic / weightSum) * mapTo.get(key)));
//                    //金额合计
//                    repMarketDataCustomerBazaarEntity.setAmountSum((int) Math.round(mapTo.get(key)));
//                }
////                    repMarketDataCustomerBazaarDao.insert(repMarketDataCustomerBazaarEntity);
//                //市场营销数据-客户市场营业数据
//                preMap.put("marketingCustomer", repMarketDataCustomerBazaarEntity);
//            }
//
//            //客户账户平均价值
//            RepMarketDataCustomerAccountEntity repMarketDataCustomerAccountEntity = new RepMarketDataCustomerAccountEntity();
//
//            Map<String, ParInitialDataMarketCustomerAccountVO> map1 = new HashMap<>();
//            List<RepMarketDataCustomerBazaarVO> repMarketDataCustomerBazaarVOS = repMarketDataCustomerBazaarDao.selectMarDataCusBazRep(currentTeamId, currentStageId);
//            if (repMarketDataCustomerBazaarVOS.size() == 0) {
//                repMarketDataCustomerBazaarVOS = repMarketDataCustomerBazaarDao.selectMarDataCusBazRep(currentTeamId, currentStageId - 1);
//            }
//            for (RepMarketDataCustomerBazaarVO repMarketDataCustomerBazaarVO : repMarketDataCustomerBazaarVOS) {
//
//                repMarketDataCustomerAccountEntity.setCurrentProjectId(currentProjectId);
//                repMarketDataCustomerAccountEntity.setCurrentTeamId(currentTeamId);
//                repMarketDataCustomerAccountEntity.setCurrentStageId(currentStageId);
//                repMarketDataCustomerAccountEntity.setCustomerMarketBusinessData(repMarketDataCustomerBazaarVO.getCustomerMarketBusinessData());
//                String customerMarketBusinessData = repMarketDataCustomerAccountEntity.getCustomerMarketBusinessData();
//
//                ParInitialDataMarketCustomerAccountVO parInitialDataMarketCustomerAccountVOS = parInitialDataMarketCustomerAccountDao.selectMarDataCusBazPar(currentStageId, customerMarketBusinessData);
//                map1.put(customerMarketBusinessData, parInitialDataMarketCustomerAccountVOS);
//
//                if (!(customerMarketBusinessData).contains("小计")) {
//                    Integer lessAge = null;
//                    Integer betweenAge = null;
//                    Integer greaterAge = null;
//                    Integer smallEnterprise = null;
//                    Integer mediumEnterprise = null;
//                    Integer majorEnterprise = null;
//                    Integer publicInstitution = null;
//                    if (repMarketDataCustomerBazaarVO != null && map1.get(customerMarketBusinessData) != null) {
//                        if (repMarketDataCustomerBazaarVO.getLessAge() != null && map1.get(customerMarketBusinessData).getLessAge() != null) {
//                            lessAge = (int) Math.round((double) repMarketDataCustomerBazaarVO.getLessAge() / map1.get(customerMarketBusinessData).getLessAge() / 0.001);
//                        }
//                        if (repMarketDataCustomerBazaarVO.getBetweenAge() != null && map1.get(customerMarketBusinessData).getBetweenAge() != null) {
//                            betweenAge = (int) Math.round((double) repMarketDataCustomerBazaarVO.getBetweenAge() / map1.get(customerMarketBusinessData).getBetweenAge() / 0.001);
//                        }
//                        if (repMarketDataCustomerBazaarVO.getGreaterAge() != null && map1.get(customerMarketBusinessData).getGreaterAge() != null) {
//                            greaterAge = (int) Math.round((double) repMarketDataCustomerBazaarVO.getGreaterAge() / map1.get(customerMarketBusinessData).getGreaterAge() / 0.001);
//                        }
//                        if (repMarketDataCustomerBazaarVO.getSmallEnterprise() != null && map1.get(customerMarketBusinessData).getSmallEnterprise() != null) {
//                            smallEnterprise = (int) Math.round((double) repMarketDataCustomerBazaarVO.getSmallEnterprise() / map1.get(customerMarketBusinessData).getSmallEnterprise() / 0.001);
//                        }
//                        if (repMarketDataCustomerBazaarVO.getMediumEnterprise() != null && map1.get(customerMarketBusinessData).getMediumEnterprise() != null) {
//                            mediumEnterprise = (int) Math.round((double) repMarketDataCustomerBazaarVO.getMediumEnterprise() / map1.get(customerMarketBusinessData).getMediumEnterprise() / 0.001);
//                        }
//                        if (repMarketDataCustomerBazaarVO.getMajorEnterprise() != null && map1.get(customerMarketBusinessData).getMajorEnterprise() != null) {
//                            majorEnterprise = (int) Math.round((double) repMarketDataCustomerBazaarVO.getMajorEnterprise() / map1.get(customerMarketBusinessData).getMajorEnterprise() / 0.001);
//                        }
//                        if (repMarketDataCustomerBazaarVO.getPublicInstitution() != null && map1.get(customerMarketBusinessData).getPublicInstitution() != null) {
//                            publicInstitution = (int) Math.round((double) repMarketDataCustomerBazaarVO.getPublicInstitution() / map1.get(customerMarketBusinessData).getPublicInstitution() / 0.001);
//                        }
//                    }
//                    repMarketDataCustomerAccountEntity.setAccountType("");
//                    repMarketDataCustomerAccountEntity.setLessAge(lessAge);
//                    Integer lessAge1 = repMarketDataCustomerAccountEntity.getLessAge();
//                    repMarketDataCustomerAccountEntity.setBetweenAge(betweenAge);
//                    Integer betweenAge1 = repMarketDataCustomerAccountEntity.getBetweenAge();
//                    repMarketDataCustomerAccountEntity.setGreaterAge(greaterAge);
//                    Integer greaterAge1 = repMarketDataCustomerAccountEntity.getGreaterAge();
//                    repMarketDataCustomerAccountEntity.setSmallEnterprise(smallEnterprise);
//                    Integer smallEnterprise1 = repMarketDataCustomerAccountEntity.getSmallEnterprise();
//                    repMarketDataCustomerAccountEntity.setMediumEnterprise(mediumEnterprise);
//                    Integer mediumEnterprise1 = repMarketDataCustomerAccountEntity.getMediumEnterprise();
//                    repMarketDataCustomerAccountEntity.setMajorEnterprise(majorEnterprise);
//                    Integer majorEnterprise1 = repMarketDataCustomerAccountEntity.getMajorEnterprise();
//                    repMarketDataCustomerAccountEntity.setPublicInstitution(publicInstitution);
//                    Integer publicInstitution1 = repMarketDataCustomerAccountEntity.getPublicInstitution();
//                    if (lessAge1 == null) {
//                        lessAge1 = 0;
//                    }
//                    if (betweenAge1 == null) {
//                        betweenAge1 = 0;
//                    }
//                    if (greaterAge1 == null) {
//                        greaterAge1 = 0;
//                    }
//                    if (smallEnterprise1 == null) {
//                        smallEnterprise1 = 0;
//                    }
//                    if (mediumEnterprise1 == null) {
//                        mediumEnterprise1 = 0;
//                    }
//                    if (majorEnterprise1 == null) {
//                        majorEnterprise1 = 0;
//                    }
//                    if (publicInstitution1 == null) {
//                        publicInstitution1 = 0;
//                    }
//                    int AccountSum = lessAge1 + betweenAge1 + greaterAge1 + smallEnterprise1 + mediumEnterprise1 + majorEnterprise1 + publicInstitution1;
//
//                    repMarketDataCustomerAccountEntity.setAccountSum(AccountSum);
////                        repMarketDataCustomerAccountDao.insert(repMarketDataCustomerAccountEntity);
//                    //市场营销数据-客户账户平均价值
//                    preMap.put("marketingAccount", repMarketDataCustomerAccountEntity);
//                }
//            }
//        }
////        }
        return preMap;
    }
}
