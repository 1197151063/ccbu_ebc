package net.lab1024.smartadmin.module.business.teacher.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageAddDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageUpdateDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TMessageEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TMessageExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TMessageVO;
import net.lab1024.smartadmin.module.business.teacher.service.TMessageService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 消息管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-06 09:46:05
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {"消息管理表"})
public class TMessageController extends BaseController {

    @Autowired
    private TMessageService tMessageService;

    @ApiOperation(value = "分页查询消息管理表",notes = "@author SMS")
    @PostMapping("/tMessage/page/query")
    public ResponseDTO<PageResultDTO<TMessageVO>> queryByPage(@RequestBody TMessageQueryDTO queryDTO) {
        return tMessageService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加消息管理表",notes = "@author SMS")
    @PostMapping("/tMessage/add")
    public ResponseDTO<String> add(@RequestBody TMessageAddDTO addTO){
        return tMessageService.add(addTO);
    }

    @ApiOperation(value = "查询消息管理表",notes = "@author SMS")
    @GetMapping("/tMessage/queryData")
    public ResponseDTO<TMessageEntity> queryData(@RequestParam("id")Long id) {
        return tMessageService.queryData(id);
    }

    @ApiOperation(value = "查询首页消息管理表",notes = "@author SMS")
    @GetMapping("/tMessage/queryMessageData")
    public ResponseDTO<List<TMessageEntity>> queryMessageData() {
        return tMessageService.queryMessageData();
    }

    @ApiOperation(value="修改消息管理表",notes = "@author SMS")
    @PostMapping("/tMessage/update")
    public ResponseDTO<String> update(@RequestBody @Validated TMessageUpdateDTO updateDTO){
        return tMessageService.update(updateDTO);
    }

//    @ApiOperation(value="删除消息管理表",notes = "@author SMS")
//    @GetMapping("/tMessage/delete")
//    public ResponseDTO<String> deleteId(@RequestParam("id") Long id) {
//        return tMessageService.deleteId(id);
//    }

    @ApiOperation(value="批量删除消息管理表",notes = "@author SMS")
    @PostMapping("/tMessage/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return tMessageService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/tMessage/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<TMessageExcelVO> tMessageList = tMessageService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("消息管理表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, TMessageExcelVO.class, tMessageList);
        downloadExcel("消息管理表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/tMessage/export/all")
    public void exportAll(@RequestBody @Validated TMessageQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<TMessageExcelVO> tMessageList = tMessageService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("消息管理表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, TMessageExcelVO.class, tMessageList);
        downloadExcel("消息管理表", workbook, response);
    }

}
