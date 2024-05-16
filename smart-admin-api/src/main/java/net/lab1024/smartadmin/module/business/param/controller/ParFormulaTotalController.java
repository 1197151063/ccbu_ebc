package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParFormulaTotalQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParFormulaTotalEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaTotalVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParFormulaTotalExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParFormulaTotalService;
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
 * [ 公式 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 17:01:27
 * @since JDK1.8
 */
@RestController
@Api(tags = {"公式"})
@RequestMapping("/service")
public class ParFormulaTotalController extends BaseController {

    @Autowired
    private ParFormulaTotalService parFormulaTotalService;

    @ApiOperation(value = "分页查询公式",notes = "@author SMS")
    @PostMapping("/parFormulaTotal/page/query")
    public ResponseDTO<PageResultDTO<ParFormulaTotalVO>> queryByPage(@RequestBody ParFormulaTotalQueryDTO queryDTO) {
        return parFormulaTotalService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加公式",notes = "@author SMS")
    @PostMapping("/parFormulaTotal/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParFormulaTotalAddDTO addTO){
        return parFormulaTotalService.add(addTO);
    }

    @ApiOperation(value="修改公式",notes = "@author SMS")
    @PostMapping("/parFormulaTotal/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParFormulaTotalUpdateDTO updateDTO){
        return parFormulaTotalService.update(updateDTO);
    }

    @ApiOperation(value="批量删除公式",notes = "@author SMS")
    @PostMapping("/parFormulaTotal/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parFormulaTotalService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parFormulaTotal/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParFormulaTotalExcelVO> parFormulaTotalList = parFormulaTotalService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("公式", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParFormulaTotalExcelVO.class, parFormulaTotalList);
        downloadExcel("公式", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parFormulaTotal/export/all")
    public void exportAll(@RequestBody @Validated ParFormulaTotalQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParFormulaTotalExcelVO> parFormulaTotalList = parFormulaTotalService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("公式", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParFormulaTotalExcelVO.class, parFormulaTotalList);
        downloadExcel("公式", workbook, response);
    }

    @ApiOperation(value = "查询公式",notes = "@author SMS")
    @PostMapping("/parFormulaTotal/queryAll")
    public List<ParFormulaTotalVO> queryAll(@RequestBody ParFormulaTotalEntity allFormulaEntity) {
        return parFormulaTotalService.queryAll(allFormulaEntity);
    }



}
