package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 市场供求预测表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:49:31
 * @since JDK1.8
 */
@Data
public class ParMarketSupplyDemandForecastExcelVO {
    @Excel(name = "市场供求预测ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "项目名")
    private String projectName;

    @Excel(name = "预期市场供求(%)")
    private String marketProspect;



}
