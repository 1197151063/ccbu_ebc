package net.lab1024.smartadmin.module.business.basics.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessExcelVO;
import net.lab1024.smartadmin.module.business.basics.service.SysStageBusinessService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 阶段业务名称 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 09:36:32
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentSystem.MANAGER_STAGE_BUSINESS})
//@Api(tags = {"阶段业务名称"})
public class SysStageBusinessController extends BaseController {

    @Autowired
    private SysStageBusinessService sysStageBusinessService;

    @ApiOperation(value = "分页查询阶段业务名称",notes = "@author SMS")
    @PostMapping("/sysStageBusiness/page/query")
    public ResponseDTO<PageResultDTO<SysStageBusinessVO>> queryByPage(@RequestBody SysStageBusinessQueryDTO queryDTO) {
        return sysStageBusinessService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "查询当前阶段业务名称",notes = "@author SMS")
    @GetMapping("/sysStageBusiness/selectBusiness")
    public ResponseDTO<SysStageBusinessVO>selectBusiness(@RequestParam("stageId") Long stageId) {
        return sysStageBusinessService.selectBusiness(stageId);
    }


    @ApiOperation(value = "添加阶段业务名称",notes = "@author SMS")
    @PostMapping("/sysStageBusiness/add")
    public ResponseDTO<String> add(@RequestBody @Validated SysStageBusinessAddDTO addTO){
        return sysStageBusinessService.add(addTO);
    }

    @ApiOperation(value="修改阶段业务名称",notes = "@author SMS")
    @PostMapping("/sysStageBusiness/update")
    public ResponseDTO<String> update(@RequestBody @Validated SysStageBusinessUpdateDTO updateDTO){
        return sysStageBusinessService.update(updateDTO);
    }

    @ApiOperation(value="批量删除阶段业务名称",notes = "@author SMS")
    @PostMapping("/sysStageBusiness/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return sysStageBusinessService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/sysStageBusiness/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<SysStageBusinessExcelVO> sysStageBusinessList = sysStageBusinessService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("阶段业务名称", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysStageBusinessExcelVO.class, sysStageBusinessList);
        downloadExcel("阶段业务名称", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/sysStageBusiness/export/all")
    public void exportAll(@RequestBody @Validated SysStageBusinessQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<SysStageBusinessExcelVO> sysStageBusinessList = sysStageBusinessService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("阶段业务名称", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SysStageBusinessExcelVO.class, sysStageBusinessList);
        downloadExcel("阶段业务名称", workbook, response);
    }

}
