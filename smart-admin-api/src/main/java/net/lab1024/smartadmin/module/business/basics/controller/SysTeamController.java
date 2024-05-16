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
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysTeamUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysDepartmentEmployeeEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysTeamExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysTeamVO;
import net.lab1024.smartadmin.module.business.basics.service.SysTeamService;
import net.lab1024.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 10:13:24
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentSystem.MANAGER_TEAM})
//@Api(tags = {"团队表"})
public class SysTeamController extends BaseController {

    @Autowired
    private SysTeamService sysTeamService;

    @ApiOperation(value = "分页查询团队表",notes = "@author SMS")
    @PostMapping("/sysTeam/page/query")
    public ResponseDTO<PageResultDTO<SysTeamVO>> queryByPage(@RequestBody SysTeamQueryDTO queryDTO) {
        return sysTeamService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加团队表",notes = "@author SMS")
    @PostMapping("/sysTeam/add")
    public ResponseDTO<String> add(@RequestBody @Validated SysTeamAddDTO addTO){
        return sysTeamService.add(addTO);
    }

    @ApiOperation(value="修改团队表",notes = "@author SMS")
    @PostMapping("/sysTeam/update")
    public ResponseDTO<String> update(@RequestBody @Validated SysTeamUpdateDTO updateDTO){
        return sysTeamService.update(updateDTO);
    }

    @ApiOperation(value="批量删除团队表",notes = "@author SMS")
    @PostMapping("/sysTeam/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return sysTeamService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/sysTeam/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<SysTeamExcelVO> sysTeamList = sysTeamService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("团队表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysTeamExcelVO.class, sysTeamList);
        downloadExcel("团队表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/sysTeam/export/all")
    public void exportAll(@RequestBody @Validated SysTeamQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<SysTeamExcelVO> sysTeamList = sysTeamService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("团队表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysTeamExcelVO.class, sysTeamList);
        downloadExcel("团队表", workbook, response);
    }

    /**
     * 查询首页团队信息和用户成员名称
     */
    @ApiOperation(value = "查询首页团队信息和用户成员名称", notes = "@author SMS")
    @GetMapping("/sysTeam/selectDepartmentEmployee")
    public ResponseDTO<List<SysDepartmentEmployeeEntity>> selectDepartmentEmployee(@RequestParam("id")Long id){
        return sysTeamService.selectDepartmentEmployee(id);


    }

    /**
     * 根据成员id查询该成员所属的团队ID
     */



}
