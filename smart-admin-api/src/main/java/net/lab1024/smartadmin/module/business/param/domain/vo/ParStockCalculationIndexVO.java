package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 股价计划指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-17 19:09:54
 * @since JDK1.8
 */
@Data
public class ParStockCalculationIndexVO {
    @ApiModelProperty("股价计划指标ID")
    private Long id;

    @ApiModelProperty("指标")
    private String index;

    @ApiModelProperty("权重")
    private Integer weight;

    @ApiModelProperty("最小值")
    private Double minScore;

    @ApiModelProperty("最大值")
    private Double maxScore;

    @ApiModelProperty("分值")
    private Double score;



}
