package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 11:24:21
 * @since JDK1.8
 */
@Data
public class RepPersonnelLogisticsDataAutomationExcelVO {
//    @Excel(name = "自动化投资")
//    private Long id;
//
//    @Excel(name = "当前项目ID")
//    private Long currentProjectId;
//
//    @Excel(name = "当前团队ID")
//    private Long currentTeamId;
//
//    @Excel(name = "当前阶段ID")
//    private Long currentStageId;

    @Excel(name = "团队名称")
    private String name;

    @Excel(name = "项目")
    private String project;

    @Excel(name = "本阶段")
    private Integer presentStage;

    @Excel(name = "累计投资")
    private Integer cumulativeInvestment;

    @Excel(name = "指数")
    private Double exponent;



}
