package net.lab1024.smartadmin.module.business.basics.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectTeamStageUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectTeamStageVO;
import net.lab1024.smartadmin.module.business.basics.service.SysProjectTeamStageService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 项目团队阶段业务关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 10:22:10
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentSystem.MANAGER_PROJECT_TEAM_STAGE})
//@Api(tags = {"项目团队阶段业务关联表"})
public class SysProjectTeamStageController extends BaseController {

    @Autowired
    private SysProjectTeamStageService sysProjectTeamStageService;

    @ApiOperation(value = "分页查询项目团队阶段业务关联表",notes = "@author SMS")
    @PostMapping("/sysProjectTeamStage/page/query")
    public ResponseDTO<PageResultDTO<SysProjectTeamStageVO>> queryByPage(@RequestBody SysProjectTeamStageQueryDTO queryDTO) {
        return sysProjectTeamStageService.queryByPage(queryDTO);
    }

    /**
     * 根据项目id查开启阶段和阶段对应的业务
     * @param proId
     * @return
     */
    @ApiOperation(value = "查询项目团队阶段业务关联表",notes = "@author SMS")
    @GetMapping("/sysProjectTeamStage/selectStageBusiness")
    public ResponseDTO <SysProjectTeamStageVO> selectStageBusiness(@RequestParam("proId")Long proId) {
            return sysProjectTeamStageService.selectCurrentStageBusiness(proId);
    }


    @ApiOperation(value = "查询阶段完成状态",notes = "@author SMS")
    @GetMapping("/sysProjectTeamStage/page/queryStatus")
    public ResponseDTO<Integer> queryStatus(@RequestParam("proId")Long proId,@RequestParam("teamId") Long teamId,@RequestParam("stageId") Long stageId, @RequestParam("businessName")String businessName) {
        return sysProjectTeamStageService.queryStatus(proId,teamId,stageId,businessName);
    }

    @ApiOperation(value = "添加项目团队阶段业务关联表",notes = "@author SMS")
    @PostMapping("/sysProjectTeamStage/add")
    public ResponseDTO<String> add(@RequestBody @Validated SysProjectTeamStageAddDTO addTO){
        return sysProjectTeamStageService.add(addTO);
    }

    @ApiOperation(value="修改项目团队阶段业务关联表",notes = "@author SMS")
    @PostMapping("/sysProjectTeamStage/update")
    public ResponseDTO<String> update(@RequestBody @Validated SysProjectTeamStageUpdateDTO updateDTO){
        return sysProjectTeamStageService.update(updateDTO);
    }

    @ApiOperation(value = "查询阶段完成状态",notes = "@author SMS")
    @GetMapping("/sysProjectStage/all/byId")
    public ResponseDTO<List<SysProjectTeamStageVO>> queryStatus(@RequestParam("proId")Long proId) {
        return sysProjectTeamStageService.selectStageBusiness(proId);
    }

    @ApiOperation(value="批量删除项目团队阶段业务关联表",notes = "@author SMS")
    @PostMapping("/sysProjectTeamStage/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return sysProjectTeamStageService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/sysProjectTeamStage/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<SysProjectTeamStageExcelVO> sysProjectTeamStageList = sysProjectTeamStageService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("项目团队阶段业务关联表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysProjectTeamStageExcelVO.class, sysProjectTeamStageList);
        downloadExcel("项目团队阶段业务关联表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/sysProjectTeamStage/export/all")
    public void exportAll(@RequestBody @Validated SysProjectTeamStageQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<SysProjectTeamStageExcelVO> sysProjectTeamStageList = sysProjectTeamStageService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("项目团队阶段业务关联表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysProjectTeamStageExcelVO.class, sysProjectTeamStageList);
        downloadExcel("项目团队阶段业务关联表", workbook, response);
    }

}
