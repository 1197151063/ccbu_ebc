package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 经济形势分析报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-13 13:18:35
 * @since JDK1.8
 */
@Data
public class ParEconomicSituationVO {
    @ApiModelProperty("经济形势ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("国内经济")
    private String domesticEconomy;

    @ApiModelProperty("金融政策")
    private String financialPolicy;

    @ApiModelProperty("全球经济")
    private String globalEconomy;



}
