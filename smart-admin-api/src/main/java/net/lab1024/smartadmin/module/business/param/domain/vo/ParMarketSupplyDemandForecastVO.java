package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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
public class ParMarketSupplyDemandForecastVO {
    @ApiModelProperty("市场供求预测ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("项目名")
    private String projectName;

    @ApiModelProperty("预期市场供求(%)")
    private String marketProspect;



}
