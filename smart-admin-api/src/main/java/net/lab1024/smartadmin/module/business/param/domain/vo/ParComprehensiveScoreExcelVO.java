package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 综合评分指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-16 10:39:05
 * @since JDK1.8
 */
@Data
public class ParComprehensiveScoreExcelVO {
    @Excel(name = "综合评价指标（算尾不算头）ID")
    private Long id;

    @Excel(name = "指标")
    private String project;

    @Excel(name = "计分标准-最低")
    private Double scoringCriteriaMin;

    @Excel(name = "计分标准-最高")
    private Double scoringCriteriaMax;

    @Excel(name = "分数")
    private Double fraction;

    @Excel(name = "计算公式")
    private String calculationFormula;



}
