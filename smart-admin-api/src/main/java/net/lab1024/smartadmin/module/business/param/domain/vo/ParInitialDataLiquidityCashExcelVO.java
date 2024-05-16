package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 初始数据表-流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:44:40
 * @since JDK1.8
 */
@Data
public class ParInitialDataLiquidityCashExcelVO {
    @Excel(name = "初始内容表-流动性报表-现金ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "同业存放款项")
    private Integer interbankDeposit;

    @Excel(name = "同业拆入")
    private Integer interBank;

    @Excel(name = "短期存款")
    private Integer shortDeposit;

    @Excel(name = "长期存款")
    private Integer longDeposit;

    @Excel(name = "短期储蓄存款")
    private Integer shortSavingsDeposit;

    @Excel(name = "长期储蓄存款")
    private Integer longSavingsDeposit;

    @Excel(name = "短期债务总计")
    private Integer totalShortDebt;

    @Excel(name = "现金流动性要求")
    private Long cashLiquidityRequirements;

    @Excel(name = "清偿力")
    private Integer solvency;

    @Excel(name = "现金清偿力")
    private Integer cashSolvency;



}
