package net.lab1024.smartadmin.module.business.echarts;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectService;
import net.lab1024.smartadmin.module.business.decision.dao.DecMarketStrategyDao;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecMarketStrategyVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.*;
import net.lab1024.smartadmin.module.business.report.service.*;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import net.lab1024.smartadmin.module.system.department.DepartmentService;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentVO;
import net.lab1024.smartadmin.module.system.employee.EmployeeDao;
import net.lab1024.smartadmin.module.system.employee.domain.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 学员、教师业务工具类
 */
@RestController
@Api(tags = {"图表数据获取公共类"})
@RequestMapping("/service")
public class EchartsDataController {
    @Autowired
    private EchartsDatalService echartsDataService;
    @Autowired
    private RepBalanceSheetService repBalanceSheetService;
    @Autowired
    private RepProfitService repProfitService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RepPeopleLogisticsService repPeopleLogisticsService;
    @Autowired
    private DecMarketStrategyDao decMarketStrategyDao;
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private RepStockPriceService repStockPriceService;
    @Autowired
    private RankingService rankingService;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 查询当前项目所有团队的不同业务市场份额
     * @param proId
     * @param stageId
     * @param type
     * @return
     */
    @ApiOperation(value = "学员首页市场份额", notes = "学员首页市场份额")
    @GetMapping("/query/marketPercentData")
    public ResponseDTO<List<ParMarketShareVO>> listDepartmentEmployee(@RequestParam Long proId, @RequestParam Long stageId, @RequestParam String type) {
        return  echartsDataService.queryMSByPidAndSid(proId, stageId, type);
    }

    /**
     * 当前项目某阶段的同业报告数据
     * @param currentProjectId
     * @param currentStageId
     */
    @ApiOperation(value = "学员首页同业报告", notes = "学员首页同业报告")
    @GetMapping("/query/teamPeerReport")
    public ResponseDTO<Map<String,PeerReport>> queryTeamPeerReport(@RequestParam Long currentProjectId,@RequestParam Long currentStageId){
        //数据封装
        Map<String,PeerReport> result = new TreeMap<>();
        //查询项目下所有团队
        ResponseDTO<List<DepartmentVO>>  listResponseDTO = departmentService.queryDeptListByProId(currentProjectId);
        if(listResponseDTO!=null){
            List<DepartmentVO> departmentVOList = listResponseDTO.getData();
            for(DepartmentVO departmentVO : departmentVOList){
                PeerReport peerReport = new PeerReport();
                Long teamId = departmentVO.getId();
                //先查询资产负债表输数据
                ResponseDTO<RepBalanceSheetVO> responseDTO = repBalanceSheetService.queryBalanceByPidAndTidAndSid(currentProjectId, teamId, currentStageId);
               if(responseDTO.getData()!=null){
                   peerReport.setTotalAssets(responseDTO.getData().getTotalAssets());
                   //查询利润表数据
                   ResponseDTO<RepProfitVO> responseDTO1 = repProfitService.queryProfitByTidAndSid(teamId, currentStageId);
                   peerReport.setRetainedProfits(responseDTO1.getData().getRetainedProfits());
                   //查询市场份额数据(短期贷款份额、中长期贷款份额、短期存款份额、长期存款份额）
                   ParMarketShareVO parMarketShareVO = new ParMarketShareVO();
                   parMarketShareVO.setCurrentTeamId(teamId);
                   parMarketShareVO.setCurrentProjectId(currentStageId);
                   parMarketShareVO.setCurrentProjectId(currentProjectId);
                   ResponseDTO<Map<String,ParMarketShareVO>> mapResponseDTO = echartsDataService.queryMarketShareByTeam(parMarketShareVO);
                   // 短期贷款份额
                   if(mapResponseDTO.getData().get("短期贷款")==null){
                       peerReport.setShortLoanShare(0.0);
                   }else{
                       peerReport.setShortLoanShare(mapResponseDTO.getData().get("短期贷款").getMarketShare());
                   }
                   // 中长期贷款份额
                   if(mapResponseDTO.getData().get("中长期贷款")==null){
                       peerReport.setMiddleLoanShare(0.0);
                   }else{
                       peerReport.setMiddleLoanShare(mapResponseDTO.getData().get("中长期贷款").getMarketShare());
                   }
                   // 短期存款份额
                   if(mapResponseDTO.getData().get("短期存款")==null){
                       peerReport.setShortDepositShare(0.0);
                   }else{
                       peerReport.setShortDepositShare(mapResponseDTO.getData().get("短期存款").getMarketShare());
                   }
                   // 长期存款份额
                   if(mapResponseDTO.getData().get("长期存款")==null){
                       peerReport.setLongDepositShare(0.0);
                   }else{
                       peerReport.setLongDepositShare(mapResponseDTO.getData().get("长期存款").getMarketShare());
                   }
                   //查询股价信息
                   peerReport.setLongDepositShare(3.4);
                   result.put(departmentVO.getName(),peerReport);
               }

            }
        }
        return ResponseDTO.succData(result);
    }

    /**
     * 查询当前团队某阶段的员工满意度
     * @param teamId
     * @param stageId
     * @return
     */
    @ApiOperation(value = "学员首页员工满意程度", notes = "学员首页员工满意程度")
    @GetMapping("/query/employeeDegreeOfSatisfied")
    public ResponseDTO<Map<String,String[]>> employeeDegreeOfSatisfied(@RequestParam Long teamId,@RequestParam Long stageId){
        ResponseDTO<List<RepPeopleLogisticsVO>> responseDTO = repPeopleLogisticsService.selectPeopleLogisticsData(teamId, stageId);
        Map<String,String[]> mapResponseDTO = new HashMap<>();
        if(responseDTO!=null){
            List<RepPeopleLogisticsVO> logisticsVOList = responseDTO.getData();
            if(logisticsVOList!=null && logisticsVOList.size()>0){
                for(RepPeopleLogisticsVO repPeopleLogisticsVO : logisticsVOList){
                    if(("贷款业务").equals(repPeopleLogisticsVO.getBusinessType())){
                        mapResponseDTO.put("贷款业务",returnChartDataBySatisfied(repPeopleLogisticsVO.getJobSatisfaction()));
                    }
                    if(("投资业务").equals(repPeopleLogisticsVO.getBusinessType())){
                        mapResponseDTO.put("投资业务",returnChartDataBySatisfied(repPeopleLogisticsVO.getJobSatisfaction()));
                    }
                    if(("中间业务").equals(repPeopleLogisticsVO.getBusinessType())){
                        mapResponseDTO.put("中间业务",returnChartDataBySatisfied(repPeopleLogisticsVO.getJobSatisfaction()));
                    }
                    if(("存款业务").equals(repPeopleLogisticsVO.getBusinessType())){
                        mapResponseDTO.put("存款业务",returnChartDataBySatisfied(repPeopleLogisticsVO.getJobSatisfaction()));
                    }
                }
            }
        }
        return ResponseDTO.succData(mapResponseDTO);
    }
    /**
     * 查询当前团队某阶段的市场营销
     * @param teamId
     * @param stageId
     * @return
     */
    @ApiOperation(value = "学员首页市场营销", notes = "学员首页市场营销")
    @GetMapping("/query/marketingManagement")
    public ResponseDTO<Map<String,Integer[]>> marketingManagement(@RequestParam Long teamId,@RequestParam Long stageId){
        DecMarketStrategyVO decMarketStrategyVO = decMarketStrategyDao.queryMarket(teamId, stageId);
        Map<String,Integer[]> map = new HashMap<>();
        if(decMarketStrategyVO!=null) {
            Integer[] shortLoan = new Integer[]{decMarketStrategyVO.getLoansBusineDevelopLess(),
                    decMarketStrategyVO.getLoansBusineDevelopBetween(),
                    decMarketStrategyVO.getLoansBusineDevelopGreater(),
                    decMarketStrategyVO.getLoansBusineDevelopSmall(),
                    decMarketStrategyVO.getLoansBusineDevelopMedium(),
                    decMarketStrategyVO.getLoansBusineDevelopMajor(),
                    decMarketStrategyVO.getLoansBusineDevelopPublic()};
            map.put("短期贷款", shortLoan);
            map.put("中长期贷款", shortLoan);

            Integer[] LongLoan = new Integer[]{decMarketStrategyVO.getDepositBusineDevelopLess(),
                    decMarketStrategyVO.getDepositBusineDevelopBetween(),
                    decMarketStrategyVO.getDepositBusineDevelopGreater(),
                    decMarketStrategyVO.getDepositBusineDevelopSmall(),
                    decMarketStrategyVO.getDepositBusineDevelopMedium(),
                    decMarketStrategyVO.getDepositBusineDevelopMajor(),
                    decMarketStrategyVO.getDepositBusineDevelopPublic()};
            map.put("存款", LongLoan);

            Integer[] middle = new Integer[]{decMarketStrategyVO.getMiddleBusineDevelopLess(),
                    decMarketStrategyVO.getMiddleBusineDevelopBetween(),
                    decMarketStrategyVO.getMiddleBusineDevelopGreater(),
                    decMarketStrategyVO.getMiddleBusineDevelopSmall(),
                    decMarketStrategyVO.getMiddleBusineDevelopMedium(),
                    decMarketStrategyVO.getMiddleBusineDevelopMajor(),
                    decMarketStrategyVO.getMiddleBusineDevelopPublic()};
            map.put("托管业务", middle);
            map.put("担保业务", middle);
        }
        return ResponseDTO.succData(map);

    }
    /**
     *
     * @param teamId
     * @param stageId
     * @return
     */
    @ApiOperation(value = "学员首页人力资源分配", notes = "学员首页人力资源分配")
    @GetMapping("/query/personnelAllot")
    public ResponseDTO<List<RepPeopleLogisticsVO>> queryPersonnelAllot(@RequestParam Long teamId,@RequestParam Long stageId){
        ResponseDTO<List<RepPeopleLogisticsVO>> responseDTO = repPeopleLogisticsService.selectPeopleLogisticsData(teamId, stageId);
        return responseDTO;
    }

    /**
     * 某项目所有阶段银行的资产统计柱状图
     * @param proId
     */
    @ApiOperation(value = "学员首页资产总额柱状图", notes = "学员首页资产总额柱状图")
    @GetMapping("/query/assestTotal")
    public ResponseDTO<List<String[]>> queryAssestTotalByStageid(@RequestParam Long proId,@RequestParam Long currentStageId){
        //最终的数据格式
        List<String[]> result = new ArrayList<>();
        List<RepBalanceSheetVO> list =  echartsDataService.queryAssestTotalByStageid(proId,null,null);
        //查询当前项目下的所有团队
        ResponseDTO<List<DepartmentVO>> listResponseDTO = departmentService.queryDeptListByProId(proId);
        List<DepartmentVO> departmentVOList = listResponseDTO.getData();
        //生成图标的product信息
        String[] products = new String[departmentVOList.size()+1];
        products[0] = "product";
        for(int i=0;i<departmentVOList.size();i++){
            products[i+1] = departmentVOList.get(i).getName();
        }
        result.add(products);
        //查询项目是否已经结束
        SysProjectVO sysProjectEntity = sysProjectService.queryData(proId);
        //若项目没结束，只显示当前阶段之前数据，结束后才显示所有
        if(!("1").equals(sysProjectEntity.getStatus())){
            currentStageId = currentStageId -1;
        }
        //根据当前阶段生成每阶段要渲染的数据
        for(int i = 2;i<=currentStageId;i++){
            String[] strs = new String[products.length];
            strs[0] = "第"+(i-1)+"阶段";
            //获取各银行对应的数据
            for(int j = 1;j<products.length;j++){
                String teamName = products[j];
                for(RepBalanceSheetVO repBalanceSheetVO : list){
                    if(teamName.equals(repBalanceSheetVO.getName()) && i == repBalanceSheetVO.getCurrentStageId()){
                        strs[j] = repBalanceSheetVO.getTotalAssets()+"";
                    }
                }
            }
            result.add(strs);
        }
        return ResponseDTO.succData(result);
    }
    /**
     * 某项目所有阶段银行的利润统计柱状图
     * @param proId
     */
    @ApiOperation(value = "学员首页利润柱状图", notes = "学员首页利润柱状图")
    @GetMapping("/query/profitData")
    public ResponseDTO<List<String[]>> queryProfitByStageid(@RequestParam Long proId,@RequestParam Long currentStageId){
        //最终的数据格式
        List<String[]> result = new ArrayList<>();
        List<RepProfitVO> repProfitVOList  = echartsDataService.queryProfitByProId(proId);
//        List<RepBalanceSheetVO> list =  echartsDataService.queryAssestTotalByStageid(proId,null,null);
        //查询当前项目下的所有团队
        ResponseDTO<List<DepartmentVO>> listResponseDTO = departmentService.queryDeptListByProId(proId);
        List<DepartmentVO> departmentVOList = listResponseDTO.getData();
        //生成图标的product信息
        String[] products = new String[departmentVOList.size()+1];
        String[] productIds = new String[departmentVOList.size()+1];
        products[0] = "product";
        productIds[0] = "ids";
        for(int i=0;i<departmentVOList.size();i++){
            products[i+1] = departmentVOList.get(i).getName();
            productIds[i+1] = departmentVOList.get(i).getId()+"";
        }
        result.add(products);
        //查询项目是否已经结束
        SysProjectVO sysProjectEntity = sysProjectService.queryData(proId);
        //若项目没结束，只显示当前阶段之前数据，结束后才显示所有
        if(!("1").equals(sysProjectEntity.getStatus())){
            currentStageId = currentStageId -1;
        }
        //根据当前阶段生成每阶段要渲染的数据
        for(int i = 2;i<=currentStageId;i++){
            String[] strs = new String[products.length];
            strs[0] = "第"+(i-1)+"阶段";
            //获取各银行对应的数据
            for(int j = 1;j<products.length;j++){
                String teamId = productIds[j];
                for(RepProfitVO repProfitVO : repProfitVOList){
                    if(teamId.equals(repProfitVO.getCurrentTeamId()+"") && i == repProfitVO.getCurrentStageId()){
                        strs[j] = repProfitVO.getRetainedProfits()+"";
                    }
                }
            }
            result.add(strs);
        }
        return ResponseDTO.succData(result);
    }

    /**
     * 某项目所有阶段银行的资产增长率统计
     * @param proId
     */
    @ApiOperation(value = "学员首页资产增长率", notes = "学员首页资产增长率")
    @GetMapping("/query/assetGrouthRate")
    public ResponseDTO<Map<String,String[]>> queryAssetGrouthRate(@RequestParam Long proId,@RequestParam Long currentStageId){
        //最终的数据格式
        Map<String,String[]> teamMap = new HashMap<>();
        //1阶段之后数据
        List<RepBalanceSheetVO> list =  echartsDataService.queryAssestTotalByStageid(proId,null,null);
        //0阶段数据
        ResponseDTO<RepBalanceSheetVO> responseDTO = repBalanceSheetService.queryBalanceByPidAndTidAndSid(null, null, 1l);
        RepBalanceSheetVO repBalanceSheetVO_0 = responseDTO.getData();
        //查询当前项目下的所有团队
        ResponseDTO<List<DepartmentVO>> listResponseDTO = departmentService.queryDeptListByProId(proId);
        List<DepartmentVO> departmentVOList = listResponseDTO.getData();
        //查询项目是否已经结束
        SysProjectVO sysProjectEntity = sysProjectService.queryData(proId);
        //若项目没结束，只显示当前阶段之前数据，结束后才显示所有
        if(!("1").equals(sysProjectEntity.getStatus())){
            currentStageId = currentStageId -1;
        }
        //X轴数据
        String[] stages = new String[currentStageId.intValue()-1];
        DecimalFormat df = new DecimalFormat("0.00000");
        float total0 = repBalanceSheetVO_0.getTotalAssets().floatValue();//初始阶段数据
        teamMap.put("stages",stages);
        //根据当前阶段生成每阶段要渲染的数据
        for(int j = 0;j<departmentVOList.size();j++){
            String teamName = departmentVOList.get(j).getName();
            //封装不同银行的增长率
            String[] strs = new String[stages.length];//封装各银行不同阶段数据
            //获取各银行对应的数据
            for(int i = 2;i<=currentStageId;i++){
                stages[i-2] = "第"+(i-1)+"阶段";
                for(int h = 0;h<list.size();h++){
                    if(teamName.equals(list.get(h).getName()) && i == list.get(h).getCurrentStageId()){
                        //计算增长值
                        float grouth = list.get(h).getTotalAssets().floatValue() - total0;
//                        System.out.println(grouth.divide(total0,BigDecimal.ROUND_HALF_DOWN));
                        //跟0阶段比
//                        strs[i-2] = grouth.divide(total0,BigDecimal.ROUND_UNNECESSARY).toString();
                        strs[i-2] = df.format(grouth/total0);
                    }
                }
            }
            teamMap.put(teamName,strs);
        }
        return ResponseDTO.succData(teamMap);
    }
    /**
     * 某项目所有阶段银行的股价图
     * @param proId
     */
    @ApiOperation(value = "学员首页股价图", notes = "学员首页股价图")
    @GetMapping("/query/stockPrice")
    public ResponseDTO<Map<String,String[]>> queryStockPrice(@RequestParam Long proId,@RequestParam Long currentStageId){
        //最终的数据格式
        Map<String,String[]> teamMap = new HashMap<>();
        //1阶段之后数据
        List<RepStockPriceVO> list = repStockPriceService.queryStockPriceByProid(proId);
        //0阶段数据
        //查询当前项目下的所有团队
        ResponseDTO<List<DepartmentVO>> listResponseDTO = departmentService.queryDeptListByProId(proId);
        List<DepartmentVO> departmentVOList = listResponseDTO.getData();
        //查询项目是否已经结束
        SysProjectVO sysProjectEntity = sysProjectService.queryData(proId);
        //若项目没结束，只显示当前阶段之前数据，结束后才显示所有
        if(!("1").equals(sysProjectEntity.getStatus())){
            currentStageId = currentStageId -1;
        }
        //X轴数据
        String[] stages = new String[currentStageId.intValue()-1];
        DecimalFormat df = new DecimalFormat("0.00000");
        teamMap.put("stages",stages);
        //根据当前阶段生成每阶段要渲染的数据
        for(int j = 0;j<departmentVOList.size();j++){
            Long teamId = departmentVOList.get(j).getId();
            String teamName = departmentVOList.get(j).getName();
            //封装不同银行的增长率
            String[] strs = new String[stages.length];//封装各银行不同阶段数据
            //获取各银行对应的数据
            for(int i = 2;i<=currentStageId;i++){
                stages[i-2] = "第"+(i-1)+"阶段";
                for(int h = 0;h<list.size();h++){
                    if((teamId == list.get(h).getCurrentTeamId()) && i == list.get(h).getCurrentStageId()){
                        //计算增长值
                        strs[i-2] = (list.get(h).getStockPrice()!=null)?list.get(h).getStockPrice()+"":"0.0";
                    }
                }
            }
            teamMap.put(teamName,strs);
        }
        return ResponseDTO.succData(teamMap);
    }
    /**
     * 某项目排名
     * @param proId
     */
    @ApiOperation(value = "银行排名查看", notes = "银行排名查看")
    @GetMapping("/query/projectRankingList")
    public ResponseDTO<List<RepComprehensiveRankingScoreVO>> queryProRankingList(@RequestParam Long proId, @RequestParam Long currentStageId) {
        //查询项目是否已经结束
        SysProjectVO sysProjectEntity = sysProjectService.queryData(proId);
        //若项目没结束，只显示当前阶段之前数据，结束后才显示所有
        if(!("1").equals(sysProjectEntity.getStatus())){
            currentStageId = currentStageId -1;
        }
        return ResponseDTO.succData(rankingService.queryTeamRankingByStageId(proId,currentStageId));
    }
    //根据满意程度返回图表所需数据
    private String[] returnChartDataBySatisfied(String satisfied){
        //默认满意
        if(satisfied == null || satisfied.equals("")){
            satisfied = "满意";
        }
        String[] strs = new String[4];
        if(("激励").equals(satisfied)){
            strs = new String[]{"9","2","2","2"};
        }
        if(("满意").equals(satisfied)){
            strs = new String[]{"2","9","2","2"};
        }
        if(("不满意").equals(satisfied)){
            strs = new String[]{"2","2","9","2"};
        }
        if(("痛苦").equals(satisfied)){
            strs = new String[]{"2","2","2","9"};
        }
        return strs;
    }

    @ApiOperation(value = "获取团队学员信息", notes = "获取团队学员信息")
    @GetMapping("/queryTeam")
    public ResponseDTO<Map<String, List<EmployeeVO>>> getDepartment(@RequestParam("currentProjectId") Long currentProjectId) {
        //数据封装
        Map<String,List<EmployeeVO>> result = new TreeMap<>();
        //查询项目下所有团队
        ResponseDTO<List<DepartmentVO>>  listResponseDTO = departmentService.queryDeptListByProId(currentProjectId);
        if(listResponseDTO!=null){
            List<DepartmentVO> departmentVOList = listResponseDTO.getData();
            for(DepartmentVO departmentVO : departmentVOList){
                List<EmployeeVO> employees = employeeDao.getEmployeeIdByDeptId(departmentVO.getId());
                result.put(departmentVO.getName(),employees);
            }
        }
        return ResponseDTO.succData(result);
    }
    @ApiOperation(value = "获取团队信息", notes = "获取团队信息")
    @GetMapping("/queryTeamData")
    public ResponseDTO<Map<String, DepartmentVO>> queryTeamData(@RequestParam("currentProjectId") Long currentProjectId) {
        //数据封装
        Map<String,DepartmentVO> map = new TreeMap<>();
        List<DepartmentVO> departmentVOS = departmentDao.queryDeptListByProId(currentProjectId);
        for (DepartmentVO departmentVO:departmentVOS) {
            map.put(departmentVO.getName(),departmentVO);
        }
        return ResponseDTO.succData(map);
//        //数据封装
//        Map<String,List<DepartmentVO>> result = new TreeMap<>();
//        //查询项目下所有团队
//        ResponseDTO<List<DepartmentVO>>  listResponseDTO = departmentService.queryDeptListByProId(currentProjectId);
//        if(listResponseDTO!=null){
//            List<DepartmentVO> departmentVOList = listResponseDTO.getData();
//            for(DepartmentVO departmentVO : departmentVOList){
//                result.put(departmentVO.getName(),departmentVOList);
//            }
//        }
//        return ResponseDTO.succData(result);
    }
}
