package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 综合评分指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-16 10:39:05
 * @since JDK1.8
 */
@Data
public class ParComprehensiveScoreAddDTO {
    @ApiModelProperty("指标")
    private String project;

    @ApiModelProperty("计分标准-最低")
    private Double scoringCriteriaMin;

    @ApiModelProperty("计分标准-最高")
    private Double scoringCriteriaMax;

    @ApiModelProperty("分数")
    private Double fraction;

    @ApiModelProperty("计算公式")
    private String calculationFormula;


}
