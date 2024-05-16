package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

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
public class ParStockCalculationIndexExcelVO {
    @Excel(name = "股价计划指标ID")
    private Long id;

    @Excel(name = "指标")
    private String index;

    @Excel(name = "权重")
    private Integer weight;

    @Excel(name = "最小值")
    private Double minScore;

    @Excel(name = "最大值")
    private Double maxScore;

    @Excel(name = "分值")
    private Double score;



}
