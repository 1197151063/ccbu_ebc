package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

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
public class ParEconomicSituationExcelVO {
    @Excel(name = "经济形势ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "国内经济")
    private String domesticEconomy;

    @Excel(name = "金融政策")
    private String financialPolicy;

    @Excel(name = "全球经济")
    private String globalEconomy;



}
