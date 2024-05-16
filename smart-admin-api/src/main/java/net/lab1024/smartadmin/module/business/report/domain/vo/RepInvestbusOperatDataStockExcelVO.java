package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 18:23:18
 * @since JDK1.8
 */
@Data
public class RepInvestbusOperatDataStockExcelVO {
//    @Excel(name = "投资股票ID")
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
    private String stockProject;

    @Excel(name = "股票指数")
    private Double stockIndex;

    @Excel(name = "股票类型(长期或短期)")
    private String stockType;

    @Excel(name = "股票数量(手)")
    private Integer stockNumber;

    @Excel(name = "股票价值")
    private Integer stockCost;

    @Excel(name = "股息")
    private Long stockDividend;

    @Excel(name = "账面损益")
    private Integer stockAccount;



}
