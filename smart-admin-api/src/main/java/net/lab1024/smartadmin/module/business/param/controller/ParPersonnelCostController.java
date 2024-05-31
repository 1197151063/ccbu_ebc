package net.lab1024.smartadmin.module.business.param.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPersonnelCostUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParPersonnelCostEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPersonnelCostExcelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPersonnelCostVO;
import net.lab1024.smartadmin.module.business.param.service.ParPersonnelCostService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 14:36:42
 * @since JDK1.8
 */
@RestController
@RequestMapping("service")
@Api(tags = {"人员成本表"})
public class ParPersonnelCostController extends BaseController {

    @Autowired
    private ParPersonnelCostService parPersonnelCostService;

    @ApiOperation(value = "分页查询人员成本表",notes = "@author SMS")
    @PostMapping("/parPersonnelCost/page/query")
    public ResponseDTO<PageResultDTO<ParPersonnelCostVO>> queryByPage(@RequestBody ParPersonnelCostQueryDTO queryDTO) {
        return parPersonnelCostService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加人员成本表",notes = "@author SMS")
    @PostMapping("/parPersonnelCost/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParPersonnelCostAddDTO addTO){
        return parPersonnelCostService.add(addTO);
    }

    @ApiOperation(value="修改人员成本表",notes = "@author SMS")
    @PostMapping("/parPersonnelCost/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParPersonnelCostUpdateDTO updateDTO){
        return parPersonnelCostService.update(updateDTO);
    }

    @ApiOperation(value="批量删除人员成本表",notes = "@author SMS")
    @PostMapping("/parPersonnelCost/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parPersonnelCostService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parPersonnelCost/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParPersonnelCostExcelVO> parPersonnelCostList = parPersonnelCostService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("人员成本表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParPersonnelCostExcelVO.class, parPersonnelCostList);
        downloadExcel("人员成本表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parPersonnelCost/export/all")
    public void exportAll(@RequestBody @Validated ParPersonnelCostQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParPersonnelCostExcelVO> parPersonnelCostList = parPersonnelCostService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("人员成本表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParPersonnelCostExcelVO.class, parPersonnelCostList);
        downloadExcel("人员成本表", workbook, response);
    }

    @ApiOperation(value = "查询所有人员成本表",notes = "")
    @GetMapping("/parPersonnelCost/queryAll")
    public ResponseDTO<List<ParPersonnelCostEntity>> queryAll(){
        return parPersonnelCostService.queryAll();
    }

//    @ApiOperation(value = "查询投资业务的附加人员成本",notes = "")
//    @GetMapping("/parPersonnelCo  st/queryAdditional")
//    public ParPersonnelCostVO queryAdditional(@RequestBody ParPersonnelCostEntity parPersonnelCostEntity){
//        return parPersonnelCostService.queryAdditional(parPersonnelCostEntity);
//    }


}
