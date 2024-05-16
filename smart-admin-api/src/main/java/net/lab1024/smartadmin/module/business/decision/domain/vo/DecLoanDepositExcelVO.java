package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 存贷款 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 12:19:11
 * @since JDK1.8
 */
@Data
public class DecLoanDepositExcelVO {
//    @Excel(name = "存贷款ID")
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

//    @Excel(name = "资产总量")
//    private Integer totalAssets;

//    @Excel(name = "存放中央银行款项(百万元)")
//    private Integer dueCentralBank;
//
//    @Excel(name = "存放同业款项(百万元)")
//    private Integer interbankDeposit;
//
//    @Excel(name = "拆放同业(百万元)")
//    private Integer loanTrade;

//    @Excel(name = "短期-抵押贷款-增长率%")
//    private Double shortMortgageLoanAdd;

//    @Excel(name = "短期-质押贷款-增长率%")
//    private Double shortHypothecatedLoanAdd;
//
//    @Excel(name = "短期-保证贷款-增长率%")
//    private Double shortGuaranteeLoanAdd;
//
//    @Excel(name = "短期-信用贷款-增长率%")
//    private Double shortLoanCreditRiseAdd;

    @Excel(name = "团队名称")
    private String name;

    @Excel(name = "短期-抵押贷款-利率%")
    private Double shortMortgageLoanRise;

    @Excel(name = "短期-质押贷款-利率%")
    private Double shortHypothecatedLoanRise;

    @Excel(name = "短期-保证贷款-利率%")
    private Double shortGuaranteeLoanRise;

    @Excel(name = "短期-信用贷款-利率%")
    private Double shortLoanCreditRise;

//    @Excel(name = "贴现-增长率%")
//    private Double discountAdd;

    @Excel(name = "贴现-利率%")
    private Double discountRise;

//    @Excel(name = "中长期-抵押贷款-增长率%")
//    private Double mlongMortgageLoanAdd;
//
//    @Excel(name = "中长期-质押贷款-增长率%")
//    private Double mlongHypothecatedLoanAdd;
//
//    @Excel(name = "中长期-保证贷款-增长率%")
//    private Double mlongGuaranteeLoanAdd;
//
//    @Excel(name = "中长期-信用贷款-增长率%")
//    private Double mlongLoanCreditAdd;

    @Excel(name = "中长期-抵押贷款-利率%")
    private Double mlongMortgageLoanRise;

    @Excel(name = "中长期-质押贷款-利率%")
    private Double mlongHypothecatedLoanRise;

    @Excel(name = "中长期-保证贷款-利率%")
    private Double mlongGuaranteeLoanRise;

    @Excel(name = "中长期-信用贷款-利率%")
    private Double mlongLoanCreditRise;

    @Excel(name = "同业拆入")
    private Integer interBank;

    @Excel(name = "发行长期债券")
    private Integer issueLongBonds;

    @Excel(name = "发行次级债券")
    private Integer issueSecondaryBonds;

//    @Excel(name = "短期存款-增长率%")
//    private Double shortDepositAdd;
//
//    @Excel(name = "短期储蓄存款-增长率%")
//    private Double shortSavingsDepositAdd;
//
//    @Excel(name = "同业存放款项-增长率%")
//    private Double interbankdepositnnnAdd;
//
//    @Excel(name = "长期存款-增长率%")
//    private Double longDePositAdd;
//
//    @Excel(name = "长期储蓄存款-增长率%")
//    private Double longDepositAdd1;

    @Excel(name = "短期存款-利率%")
    private Double shortDepositRise;

    @Excel(name = "短期储蓄存款-利率%")
    private Double shortSavingsDepositRise;

    @Excel(name = "同业存放款项-利率%")
    private Double interbankdepositnnnRise;

    @Excel(name = "长期存款-利率%")
    private Double longDePositRise;

    @Excel(name = "长期储蓄存款-利率%")
    private Double longSavingsDepositRise;



}
