package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 中间业务提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:48:49
 * @since JDK1.8
 */
@Data
public class DecMiddleCustodyExcelVO {
    @Excel(name = "中间业务()提交表ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "托管业务-增长率%")
    private Double custodyBusinessAdd;

    @Excel(name = "担保业务-增长率%")
    private Double guaranteeBusinessAdd;

    @Excel(name = "托管业务-手续费率%")
    private Double custodyBusinessProcedure;

    @Excel(name = "担保业务-手续费率%")
    private Double guaranteeBusinessProcedure;

    @Excel(name = "甲债券承销价(%)")
    private Double firstUnderwritPriceBond;

    @Excel(name = "乙债券承销价(%)")
    private Double secondUnderwritPriceBond;

    @Excel(name = "丙债券承销价(%)")
    private Double thirdUnderwritPriceBond;



}
