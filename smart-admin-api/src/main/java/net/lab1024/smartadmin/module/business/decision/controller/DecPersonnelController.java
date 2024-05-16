package net.lab1024.smartadmin.module.business.decision.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.constant.SwaggerTagConst;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelAddDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecPersonnelUpdateDTO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecPersonnelExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecPersonnelVO;
import net.lab1024.smartadmin.module.business.decision.service.DecPersonnelService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:27:46
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {SwaggerTagConst.StudentDec.STUDENT_DECISION_PERSONNEL})
//@Api(tags = {"人事后勤信息录入"})
public class DecPersonnelController extends BaseController {

    @Autowired
    private DecPersonnelService decPersonnelService;

    @ApiOperation(value = "分页查询人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/decPersonnel/page/query")
    public ResponseDTO<PageResultDTO<DecPersonnelVO>> queryByPage(@RequestBody DecPersonnelQueryDTO queryDTO) {
        return decPersonnelService.queryByPage(queryDTO);
    }



    @ApiOperation(value = "查询人事后勤决策表",notes = "@author SMS")
    @GetMapping("/decPersonnel/queryPeople")
    public ResponseDTO<DecPersonnelVO> queryPeople(@RequestParam("currentProjectId") Long currentProjectId,@RequestParam("currentTeamId") Long currentTeamId, @RequestParam("currentStageId")Long currentStageId) {
        return decPersonnelService.queryPeople(currentProjectId,currentTeamId,currentStageId);
    }


    @ApiOperation(value = "添加人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/decPersonnel/add")
    public ResponseDTO<String> add(@RequestBody @Validated DecPersonnelAddDTO addTO, HttpServletRequest request){
        return decPersonnelService.add(addTO,request);
    }

//    @ApiOperation(value = "人事后勤预计算",notes = "@author SMS")
//    @PostMapping("/decPersonnel/preOperation")
//    public ResponseDTO<Map<String,Object>> preOperation(@RequestBody @Validated DecPersonnelAddDTO addTO){
//        return decPersonnelService.preOperation(addTO);
//    }

    @ApiOperation(value="修改人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/decPersonnel/update")
    public ResponseDTO<String> update(@RequestBody @Validated DecPersonnelUpdateDTO updateDTO){
        return decPersonnelService.update(updateDTO);
    }

    @ApiOperation(value="批量删除人事后勤信息录入",notes = "@author SMS")
    @PostMapping("/decPersonnel/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return decPersonnelService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/decPersonnel/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<DecPersonnelExcelVO> decPersonnelList = decPersonnelService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecPersonnelExcelVO.class, decPersonnelList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/decPersonnel/export/all")
    public void exportAll(@RequestBody @Validated DecPersonnelQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<DecPersonnelExcelVO> decPersonnelList = decPersonnelService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("人事后勤信息录入", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, DecPersonnelExcelVO.class, decPersonnelList);
        downloadExcel("人事后勤信息录入", workbook, response);
    }

}
