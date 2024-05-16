package net.lab1024.smartadmin.module.business.param.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastExcelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastVO;
import net.lab1024.smartadmin.module.business.param.service.ParMarketSupplyDemandForecastService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * [ 市场供求预测表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:49:31
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {"市场供求预测表"})
public class ParMarketSupplyDemandForecastController extends BaseController {

    @Autowired
    private ParMarketSupplyDemandForecastService parMarketSupplyDemandForecastService;

    @ApiOperation(value = "分页查询市场供求预测表",notes = "@author SMS")
    @PostMapping("/parMarketSupplyDemandForecast/page/query")
    public ResponseDTO<PageResultDTO<ParMarketSupplyDemandForecastVO>> queryByPage(@RequestBody ParMarketSupplyDemandForecastQueryDTO queryDTO) {
        return parMarketSupplyDemandForecastService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "查询项目利率表",notes = "@author SMS")
    @GetMapping("/parMarketSupplyDemandForecast/selectParMarketSupply")
    public ResponseDTO <Map<String, ParMarketSupplyDemandForecastVO>> selectParMarketSupply(@RequestParam("stageId") Long stageId) {
        return parMarketSupplyDemandForecastService.selectParMarketSupply(stageId);
    }

    @ApiOperation(value = "添加市场供求预测表",notes = "@author SMS")
    @PostMapping("/parMarketSupplyDemandForecast/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParMarketSupplyDemandForecastAddDTO addTO){
        return parMarketSupplyDemandForecastService.add(addTO);
    }

    @ApiOperation(value="修改市场供求预测表",notes = "@author SMS")
    @PostMapping("/parMarketSupplyDemandForecast/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParMarketSupplyDemandForecastUpdateDTO updateDTO){
        return parMarketSupplyDemandForecastService.update(updateDTO);
    }

    @ApiOperation(value="批量删除市场供求预测表",notes = "@author SMS")
    @PostMapping("/parMarketSupplyDemandForecast/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return parMarketSupplyDemandForecastService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parMarketSupplyDemandForecast/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParMarketSupplyDemandForecastExcelVO> parMarketSupplyDemandForecastList = parMarketSupplyDemandForecastService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("市场供求预测表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParMarketSupplyDemandForecastExcelVO.class, parMarketSupplyDemandForecastList);
        downloadExcel("市场供求预测表", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parMarketSupplyDemandForecast/export/all")
    public void exportAll(@RequestBody @Validated ParMarketSupplyDemandForecastQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParMarketSupplyDemandForecastExcelVO> parMarketSupplyDemandForecastList = parMarketSupplyDemandForecastService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("市场供求预测表", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParMarketSupplyDemandForecastExcelVO.class, parMarketSupplyDemandForecastList);
        downloadExcel("市场供求预测表", workbook, response);
    }

}
