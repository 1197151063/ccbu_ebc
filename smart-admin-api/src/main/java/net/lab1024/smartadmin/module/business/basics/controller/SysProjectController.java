package net.lab1024.smartadmin.module.business.basics.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysBeginStageDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectService;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectStageService;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectTeamStageService;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositExcelVO;
import net.lab1024.smartadmin.module.business.decision.service.DecLoanDepositService;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParMarketShareService;
import net.lab1024.smartadmin.module.business.report.domain.vo.*;
import net.lab1024.smartadmin.module.business.report.service.*;
import net.lab1024.smartadmin.module.system.department.DepartmentService;
import net.lab1024.smartadmin.module.system.department.domain.dto.DepartmentVO;
import net.lab1024.smartadmin.module.system.employee.EmployeeService;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeAddDTO;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import net.lab1024.smartadmin.module.system.employee.domain.vo.EmployeeVO;
import net.lab1024.smartadmin.module.system.login.domain.RequestTokenBO;
import net.lab1024.smartadmin.util.SmartRequestTokenUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [ 项目表 ]
 *
 * @author SMS
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c)  1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date 2021-11-21 09:08:37
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentSystem.MANAGER_PROJECT})
//@Api(tags = {"项目表"})
public class SysProjectController extends BaseController {

    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private SysProjectStageService sysProjectStageService;
    @Autowired
    private SysProjectTeamStageService sysProjectTeamStageService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RepBalanceSheetService repBalanceSheetService;
    @Autowired
    private RepProfitService repProfitService;
    @Autowired
    private ParMarketShareService parMarketShareService;
    @Autowired
    private DecLoanDepositService decLoanDepositService;
    @Autowired
    private RepLiquidityCashService repLiquidityCashService;
    @Autowired
    private RepLiquidityTotalityService repLiquidityTotalityService;
    @Autowired
    private RepCapitalAdequacyService repCapitalAdequacyService;
    @Autowired
    private RepInvestbusOperatDataStockService repInvestbusOperatDataStockService;
    @Autowired
    private RepInvestbusOperatDataBondService repInvestbusOperatDataBondService;
    @Autowired
    private RepPeopleLogisticsService repPeopleLogisticsService;
    @Autowired
    private RepPersonnelLogisticsDataAutomationService repPersonnelLogisticsDataAutomationService;
    @Autowired
    private RepMarketDataCustomerBazaarService repMarketDataCustomerBazaarService;
    @Autowired
    private RepMarketDataCustomerAccountService repMarketDataCustomerAccountService;


    @ApiOperation(value = "分页查询项目表",notes = "@author SMS")
    @PostMapping("/sysProject/page/query")
    public ResponseDTO<PageResultDTO<SysProjectVO>> queryByPage(@RequestBody SysProjectQueryDTO queryDTO) {
        return sysProjectService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加项目表",notes = "@author SMS")
    @PostMapping("/sysProject/add")
    public ResponseDTO<String> add(@RequestBody SysProjectAddDTO addTO){
        return sysProjectService.add(addTO);
    }

    @ApiOperation(value="修改项目表",notes = "@author SMS")
    @PostMapping("/sysProject/update")
    public ResponseDTO<String> update(@RequestBody @Validated SysProjectUpdateDTO updateDTO){
        return sysProjectService.update(updateDTO);
    }

    @ApiOperation(value="查询上一阶段开启状态",notes = "@author SMS")
    @PostMapping("/sysProject/queryBeforeStage")
    public ResponseDTO<List<SysProjectTeamStageVO>> queryBeforeStage(@RequestBody SysBeginStageDTO sysBeginStageDTO){
        return sysProjectTeamStageService.queryBeforeStage(sysBeginStageDTO);
    }

    @ApiOperation(value="开启结束项目阶段",notes = "@author SMS")
    @PostMapping("/sysProject/beginStage")
    public ResponseDTO<String> beginStage(@RequestBody SysBeginStageDTO sysBeginStageDTO){
        return sysProjectStageService.beginStage(sysBeginStageDTO);
    }

    @ApiOperation(value="批量删除项目表",notes = "@author SMS")
    @PostMapping("/sysProject/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return sysProjectService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/sysProject/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<SysProjectExcelVO> sysProjectList = sysProjectService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("项目表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysProjectExcelVO.class, sysProjectList);
        downloadExcel("项目表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/sysProject/export/all")
    public void exportAll(@RequestBody @Validated SysProjectQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<SysProjectExcelVO> sysProjectList = sysProjectService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("项目表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysProjectExcelVO.class, sysProjectList);
        downloadExcel("项目表", workbook, response);
    }

    @ApiOperation(value="查询项目已结束阶段",notes = "@author SMS")
    @GetMapping("/sysProject/queryProjectEndStage")
    public ResponseDTO<List<SysProjectStageVO>> queryProjectEndStage(@RequestParam Long proId){
        SysProjectStageVO sysProjectVO  = new SysProjectStageVO();
        sysProjectVO.setProId(proId);
        sysProjectVO.setStatus(2);
        return sysProjectStageService.querySysProjectStage(sysProjectVO);
    }

    @ApiOperation(value="根据项目查询当前阶段",notes = "@author SMS")
    @GetMapping("/sysProject/selectByStageId")
    public ResponseDTO<Integer> selectByStageId(@RequestParam("proId") Long proId){
        return sysProjectService.selectByStageId(proId);
    }

    @ApiOperation(value = "通过团队id学员信息", notes = "@author yandanyang")
    @GetMapping("/employee/teamToStudent")
    public ResponseDTO<List<EmployeeVO>> teamToStudent(@RequestParam("deptId") Long deptId,@RequestParam("actualName") String actualName,@RequestParam("remark") String remark) {
        return employeeService.getEmployeeActualNameByDeptId(deptId,actualName,remark);
    }

    @ApiOperation(value="批量删除员工信息表",notes = "@author SMS")
    @PostMapping("/employee/deleteByIds")
    public ResponseDTO<String> deleteByIds(@RequestBody @Validated ValidateList<Long> idList) {
        return employeeService.deleteByIds(idList);
    }

    @ApiOperation(value = "修改团队成员",notes = "@author SMS")
    @GetMapping("/employee/queryData")
    public ResponseDTO<EmployeeDTO> queryData(@RequestParam("id")Long id) {
        return employeeService.queryData(id);
    }

    @ApiOperation(value = "根据项目id获取团队信息", notes = "获取团队信息")
    @GetMapping("/team/query")
    public ResponseDTO<List<DepartmentVO>> getDepartment(@RequestParam("proId")Long proId) {
        return departmentService.queryDeptListByProId(proId);
    }
    @ApiOperation(value = "教师添加学员", notes = "@author yandanyang")
    @PostMapping("/student/add")
    public ResponseDTO<String> addStudent(@Valid @RequestBody EmployeeAddDTO emp) {
        RequestTokenBO requestToken = SmartRequestTokenUtil.getRequestUser();
        return employeeService.addEmployee(emp, requestToken);
    }

    @ApiOperation(value = "导出报告（流下载）")
    @GetMapping("/sysProject/export")
    public void downLoadById(@RequestParam("proId")Long proId,@RequestParam("stageId")Long stageId, HttpServletResponse response) {
//    public ResponseEntity<byte[]> downLoadById(Long id, HttpServletRequest request) {

        Workbook workBook = null;
        try {
            //资产负债表
            // 创建参数对象（用来设定excel得sheet得内容等信息）
            ExportParams balanceSheetExportParams = new ExportParams();
            // 设置sheet得名称
            balanceSheetExportParams.setSheetName("资产负债表数据（百万元）");
            // 创建sheet1使用得map
            Map<String, Object> balanceSheetExportMap = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            balanceSheetExportMap.put("title", balanceSheetExportParams);
            // 模版导出对应得实体类型
            balanceSheetExportMap.put("entity", RepBalanceSheetExcelVO.class);
            List<RepBalanceSheetExcelVO> repBalanceSheetList = repBalanceSheetService.queryExportData(proId,stageId);
//            System.err.println(JSONArray.toJSONString(repBalanceSheetList));
            // sheet中要填充得数据
            balanceSheetExportMap.put("data", repBalanceSheetList);

            //利润表
            ExportParams profitExportParams = new ExportParams();
            profitExportParams.setSheetName("利润表数据（百万元）");
            // 创建sheet2使用得map
            Map<String, Object> profitExportMap = new HashMap<>();
            profitExportMap.put("title", profitExportParams);
            profitExportMap.put("entity", RepProfitExcelVO.class);
            List<RepProfitExcelVO> repProfitExcelVOS = repProfitService.queryExportData(proId, stageId);
            profitExportMap.put("data", repProfitExcelVOS);

            //市场份额表
            ExportParams marketShareExportParams = new ExportParams();
            marketShareExportParams.setSheetName("市场份额（%）");
            // 创建sheet2使用得map
            Map<String, Object> marketShareExportMap = new HashMap<>();
            marketShareExportMap.put("title", marketShareExportParams);
            marketShareExportMap.put("entity", ParMarketShareExcelVO.class);
            List<ParMarketShareExcelVO> repMarketShareExcelVOS = parMarketShareService.queryExportData(proId, stageId);
            marketShareExportMap.put("data", repMarketShareExcelVOS);

            //利率费率情况
            ExportParams loanDepositExportParams = new ExportParams();
            loanDepositExportParams.setSheetName("利率费率情况（%）");
            // 创建sheet2使用得map
            Map<String, Object> loanDepositExportMap = new HashMap<>();
            loanDepositExportMap.put("title", loanDepositExportParams);
            loanDepositExportMap.put("entity", DecLoanDepositExcelVO.class);
            List<DecLoanDepositExcelVO> decLoanDepositExcelVOS = decLoanDepositService.queryExportData(proId, stageId);
            loanDepositExportMap.put("data", decLoanDepositExcelVOS);

            //现金流动性
            ExportParams liquidityCashExportParams = new ExportParams();
            liquidityCashExportParams.setSheetName("现金流动性");
            // 创建sheet2使用得map
            Map<String, Object> liquidityCashExportMap = new HashMap<>();
            liquidityCashExportMap.put("title", liquidityCashExportParams);
            liquidityCashExportMap.put("entity", RepLiquidityCashExcelVO.class);
            List<RepLiquidityCashExcelVO> repLiquidityCashExcelVOS = repLiquidityCashService.queryExportData(proId, stageId);
            liquidityCashExportMap.put("data", repLiquidityCashExcelVOS);

            //总体流动性
            ExportParams liquidityTotalityExportParams = new ExportParams();
            liquidityTotalityExportParams.setSheetName("总体流动性");
            // 创建sheet2使用得map
            Map<String, Object> liquidityTotalityExportMap = new HashMap<>();
            liquidityTotalityExportMap.put("title", liquidityTotalityExportParams);
            liquidityTotalityExportMap.put("entity", RepLiquidityTotalityExcelVO.class);
            List<RepLiquidityTotalityExcelVO> repLiquidityTotalityExcelVOS = repLiquidityTotalityService.queryExportData(proId, stageId);
            liquidityTotalityExportMap.put("data", repLiquidityTotalityExcelVOS);

            //资本充足率报告
            ExportParams capitalAdequacyExportParams = new ExportParams();
            capitalAdequacyExportParams.setSheetName("资本充足率报告");
            // 创建sheet2使用得map
            Map<String, Object> capitalAdequacyExportMap = new HashMap<>();
            capitalAdequacyExportMap.put("title", capitalAdequacyExportParams);
            capitalAdequacyExportMap.put("entity", RepCapitalAdequacyExcelVO.class);
            List<RepCapitalAdequacyExcelVO> repCapitalAdequacyExcelVOS = repCapitalAdequacyService.queryExportData(proId, stageId);
            capitalAdequacyExportMap.put("data", repCapitalAdequacyExcelVOS);

            //股票投资
            ExportParams investbusOperatDataStockExportParams = new ExportParams();
            investbusOperatDataStockExportParams.setSheetName("股票投资");
            // 创建sheet2使用得map
            Map<String, Object> investbusOperatDataStockExportMap = new HashMap<>();
            investbusOperatDataStockExportMap.put("title", investbusOperatDataStockExportParams);
            investbusOperatDataStockExportMap.put("entity", RepInvestbusOperatDataStockExcelVO.class);
            List<RepInvestbusOperatDataStockExcelVO> repInvestbusOperatDataStockExcelVOS = repInvestbusOperatDataStockService.queryExportData(proId, stageId);
            investbusOperatDataStockExportMap.put("data", repInvestbusOperatDataStockExcelVOS);

            //债券投资
            ExportParams investbusOperatDataBondExportParams = new ExportParams();
            investbusOperatDataBondExportParams.setSheetName("债券投资");
            // 创建sheet2使用得map
            Map<String, Object> investbusOperatDataBondExportMap = new HashMap<>();
            investbusOperatDataBondExportMap.put("title", investbusOperatDataBondExportParams);
            investbusOperatDataBondExportMap.put("entity", RepInvestbusOperatDataBondExcelVO.class);
            List<RepInvestbusOperatDataBondExcelVO> repInvestbusOperatDataBondExcelVOS = repInvestbusOperatDataBondService.queryExportData(proId, stageId);
            investbusOperatDataBondExportMap.put("data", repInvestbusOperatDataBondExcelVOS);

            //人事后勤数据
            ExportParams peopleLogisticsExportParams = new ExportParams();
            peopleLogisticsExportParams.setSheetName("人事后勤数据");
            // 创建sheet2使用得map
            Map<String, Object> peopleLogisticsExportMap = new HashMap<>();
            peopleLogisticsExportMap.put("title", peopleLogisticsExportParams);
            peopleLogisticsExportMap.put("entity", RepPeopleLogisticsExcelVO.class);
            List<RepPeopleLogisticsExcelVO> repPeopleLogisticsExcelVOS = repPeopleLogisticsService.queryExportData(proId, stageId);
            peopleLogisticsExportMap.put("data", repPeopleLogisticsExcelVOS);

            //自动化投资
            ExportParams personnelLogisticsDataAutomationExportParams = new ExportParams();
            personnelLogisticsDataAutomationExportParams.setSheetName("自动化投资");
            // 创建sheet2使用得map
            Map<String, Object> personnelLogisticsDataAutomationExportMap = new HashMap<>();
            personnelLogisticsDataAutomationExportMap.put("title", personnelLogisticsDataAutomationExportParams);
            personnelLogisticsDataAutomationExportMap.put("entity", RepPersonnelLogisticsDataAutomationExcelVO.class);
            List<RepPersonnelLogisticsDataAutomationExcelVO> repPersonnelLogisticsDataAutomationExcelVOS = repPersonnelLogisticsDataAutomationService.queryExportData(proId, stageId);
            personnelLogisticsDataAutomationExportMap.put("data", repPersonnelLogisticsDataAutomationExcelVOS);

            //客户市场营业数据
            ExportParams marketDataCustomerBazaarExportParams = new ExportParams();
            marketDataCustomerBazaarExportParams.setSheetName("客户市场营业数据");
            // 创建sheet2使用得map
            Map<String, Object> marketDataCustomerBazaarExportMap = new HashMap<>();
            marketDataCustomerBazaarExportMap.put("title", marketDataCustomerBazaarExportParams);
            marketDataCustomerBazaarExportMap.put("entity", RepMarketDataCustomerBazaarExcelVO.class);
            List<RepMarketDataCustomerBazaarExcelVO> repMarketDataCustomerBazaarExcelVOS = repMarketDataCustomerBazaarService.queryExportData(proId, stageId);
            marketDataCustomerBazaarExportMap.put("data", repMarketDataCustomerBazaarExcelVOS);

            //客户账户营业数据
            ExportParams marketDataCustomerAccountExportParams = new ExportParams();
            marketDataCustomerAccountExportParams.setSheetName("客户账户营业数据");
            // 创建sheet2使用得map
            Map<String, Object> marketDataCustomerAccountExportMap = new HashMap<>();
            marketDataCustomerAccountExportMap.put("title", marketDataCustomerAccountExportParams);
            marketDataCustomerAccountExportMap.put("entity", RepMarketDataCustomerAccountExcelVO.class);
            List<RepMarketDataCustomerAccountExcelVO> repMarketDataCustomerAccountExcelVOS = repMarketDataCustomerAccountService.queryExportData(proId, stageId);
            marketDataCustomerAccountExportMap.put("data", repMarketDataCustomerAccountExcelVOS);


            // 将sheet1、sheet2、sheet3使用得map进行包装
            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(balanceSheetExportMap);
            sheetsList.add(profitExportMap);
            sheetsList.add(marketShareExportMap);
            sheetsList.add(loanDepositExportMap);
            sheetsList.add(liquidityCashExportMap);
            sheetsList.add(liquidityTotalityExportMap);
            sheetsList.add(capitalAdequacyExportMap);
            sheetsList.add(investbusOperatDataStockExportMap);
            sheetsList.add(investbusOperatDataBondExportMap);
            sheetsList.add(peopleLogisticsExportMap);
            sheetsList.add(personnelLogisticsDataAutomationExportMap);
            sheetsList.add(marketDataCustomerBazaarExportMap);
            sheetsList.add(marketDataCustomerAccountExportMap);

            // 执行方法
            workBook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
//            String fileName = URLEncoder.encode("行业信息报告", "UTF-8");

            SysProjectVO sysProjectVO = sysProjectService.queryData(proId);
            downloadExcel(sysProjectVO.getProName()+"-第"+(stageId-1)+"阶段-行业信息报告", workBook, response);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            workBook.write(outputStream);
//            outputStream.flush();
//            byte[] byteArray = outputStream.toByteArray();
//            ByteArrayInputStream excelStream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
//            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //查询数据
//        List<RepBalanceSheetExcelVO> repBalanceSheetList = repBalanceSheetService.queryExportData(proId,stageId);
//        List<RepProfitExcelVO> repProfitExcelVOS = repProfitService.queryExportData(proId, stageId);
//        //导出操作
//        ExportParams ex = new ExportParams("资产负债表", "资产负债表");
//        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepBalanceSheetExcelVO.class, repBalanceSheetList);
//        downloadExcel("行业信息报告", workbook, response);

//        ExportParams ex = new ExportParams("利润表", "利润表");
//        Workbook workbook = ExcelExportUtil.exportExcel(ex, RepProfitExcelVO.class, repProfitExcelVOS);
//        downloadExcel("行业信息报告", workbook, response);
        /////////////////////////////////////////////////////////////////////////////
//        FileEntity entity = fileDao.selectById(id);
//        if (null == entity) {
//        }
//        // 根据文件服务类 获取对应文件服务 查询 url
//        FileServiceTypeEnum serviceTypeEnum = SmartBaseEnumUtil.getEnumByValue(entity.getFileLocationType(), FileServiceTypeEnum.class);
//        IFileService fileService = this.getFileService(serviceTypeEnum);
//        ResponseEntity<byte[]> stream = fileService.fileDownload(entity.getFilePath(), entity.getFileName(), request);
//        return stream;
    }
}
