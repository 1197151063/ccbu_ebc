package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 初始数据表-投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 14:03:28
 * @since JDK1.8
 */
@Data
public class ParInitialDataInvestbusBondExcelVO {
    @Excel(name = "投资债券ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "债券种类(来自各个阶段)")
    private String bondKind;

    @Excel(name = "价格(%)")
    private Double bondPrice;

    @Excel(name = "债券投资类型(长期或短期)")
    private String bondType;

    @Excel(name = "实际价值")
    private Integer realValue;

    @Excel(name = "名义价值")
    private Integer nominalValue;

    @Excel(name = "利率(%)")
    private Double rateInterest;

    @Excel(name = "利息")
    private Integer interest;

    @Excel(name = "账面损益")
    private Integer accountProfitLoss;



}
