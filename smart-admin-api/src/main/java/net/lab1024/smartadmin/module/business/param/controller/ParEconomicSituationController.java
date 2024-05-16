package net.lab1024.smartadmin.module.business.param.controller;

import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationAddDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationUpdateDTO;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParEconomicSituationVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParEconomicSituationExcelVO;
import net.lab1024.smartadmin.module.business.param.service.ParEconomicSituationService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [ 经济形势分析报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 13:18:35
 * @since JDK1.8
 */
@RestController
@RequestMapping("/service")
@Api(tags = {"经济形势分析报告"})
public class ParEconomicSituationController extends BaseController {

    @Autowired
    private ParEconomicSituationService parEconomicSituationService;

    @ApiOperation(value = "分页查询经济形势分析报告",notes = "@author SMS")
    @PostMapping("/parEconomicSituation/page/query")
    public ResponseDTO<PageResultDTO<ParEconomicSituationVO>> queryByPage(@RequestBody ParEconomicSituationQueryDTO queryDTO) {
        return parEconomicSituationService.queryByPage(queryDTO);
    }

    /**
     * 查询经济形势分析报告
     * @param currentStageId
     * @return
     */
    @ApiOperation(value = "查询经济形势分析报告", notes = "返回值说明{" +
            "\n" +
            "国内经济：domesticEconomy,\n" +
            "\n" +
            "金融政策：financialPolicy;\n" +
            "\n" +
            "全球经济：globalEconomy;" +
            "}")
    @GetMapping("/parEconomicSituation/queryCurrentStageEconomy")
    public ResponseDTO<ParEconomicSituationVO> queryCurrentStageEconomy(@Param("currentStageId")Long currentStageId){
        return parEconomicSituationService.queryCurrentStageEconomy(currentStageId);
    }


    @ApiOperation(value = "添加经济形势分析报告",notes = "@author SMS")
    @PostMapping("/parEconomicSituation/add")
    public ResponseDTO<String> add(@RequestBody @Validated ParEconomicSituationAddDTO addTO){
        return parEconomicSituationService.add(addTO);
    }

    @ApiOperation(value="修改经济形势分析报告",notes = "@author SMS")
    @PostMapping("/parEconomicSituation/update")
    public ResponseDTO<String> update(@RequestBody @Validated ParEconomicSituationUpdateDTO updateDTO){
        return parEconomicSituationService.update(updateDTO);
    }

    @ApiOperation(value="批量删除经济形势分析报告",notes = "@author SMS")
    @PostMapping("/parEconomicSituation/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
      return parEconomicSituationService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author SMS")
    @PostMapping("/parEconomicSituation/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ParEconomicSituationExcelVO> parEconomicSituationList = parEconomicSituationService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("经济形势分析报告", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParEconomicSituationExcelVO.class, parEconomicSituationList);
        downloadExcel("经济形势分析报告", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SMS")
    @PostMapping("/parEconomicSituation/export/all")
    public void exportAll(@RequestBody @Validated ParEconomicSituationQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ParEconomicSituationExcelVO> parEconomicSituationList = parEconomicSituationService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("经济形势分析报告", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ParEconomicSituationExcelVO.class, parEconomicSituationList);
        downloadExcel("经济形势分析报告", workbook, response);
    }

}
