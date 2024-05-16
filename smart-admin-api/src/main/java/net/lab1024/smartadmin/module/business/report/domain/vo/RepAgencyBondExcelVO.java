package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-08 12:46:28
 * @since JDK1.8
 */
@Data
public class RepAgencyBondExcelVO {
    @Excel(name = "代理债券发行业务数据表ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "债券类型")
    private String bondType;

    @Excel(name = "票面利率(%)")
    private Double couponRate;

    @Excel(name = "期限")
    private Integer deadline;

    @Excel(name = "信用评级")
    private String creditRating;

    @Excel(name = "发行金额")
    private Integer issueAmount;

    @Excel(name = "承销发行银行")
    private String underwritingBank;

    @Excel(name = "承销金额（百万元）")
    private Integer underwritingMount;

    @Excel(name = "认购额度（%）")
    private Double subscriptionLimit;

    @Excel(name = "承销价格（%）")
    private Double underwritingPrice;

    @Excel(name = "折扣（%）")
    private Integer discount;



}
