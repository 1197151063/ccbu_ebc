package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 财务管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:45:59
 * @since JDK1.8
 */
@Data
public class DecFinancialManagementExcelVO {
    @Excel(name = "财务管理ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "分红（%）")
    private Double shareBonus;

    @Excel(name = "增效（百万元）")
    private Integer synergia;



}
