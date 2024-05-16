package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 初始数据表-人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-06 14:22:45
 * @since JDK1.8
 */
@Data
public class ParInitialDataAutomationExcelVO {
    @Excel(name = "自动化投资")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "项目")
    private String project;

    @Excel(name = "本阶段")
    private Double presentStage;

    @Excel(name = "累计投资")
    private Double cumulativeInvestment;

    @Excel(name = "指数")
    private Double exponent;



}
