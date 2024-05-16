package net.lab1024.smartadmin.module.business.decision.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建 [ 存贷款 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date 2021-11-24 12:19:11
 * @since JDK1.8
 */
@Data
public class DecLoanDepositAddDTO {
    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("资产总量")
    private Integer totalAssets;

    @ApiModelProperty("存放中央银行款项(百万元)")
    private Integer dueCentralBank;

    @ApiModelProperty("存放同业款项(百万元)")
    private Integer interbankDeposit;

    @ApiModelProperty("拆放同业(百万元)")
    private Integer loanTrade;

    @ApiModelProperty("短期-抵押贷款-增长率%")
    private Double shortMortgageLoanAdd;

    @ApiModelProperty("短期-质押贷款-增长率%")
    private Double shortHypothecatedLoanAdd;

    @ApiModelProperty("短期-保证贷款-增长率%")
    private Double shortGuaranteeLoanAdd;

    @ApiModelProperty("短期-信用贷款-增长率%")
    private Double shortLoanCreditRiseAdd;

    @ApiModelProperty("短期-抵押贷款-利率%")
    private Double shortMortgageLoanRise;

    @ApiModelProperty("短期-质押贷款-利率%")
    private Double shortHypothecatedLoanRise;

    @ApiModelProperty("短期-保证贷款-利率%")
    private Double shortGuaranteeLoanRise;

    @ApiModelProperty("短期-信用贷款-利率%")
    private Double shortLoanCreditRise;

    @ApiModelProperty("贴现-增长率%")
    private Double discountAdd;

    @ApiModelProperty("贴现-利率%")
    private Double discountRise;

    @ApiModelProperty("中长期-抵押贷款-增长率%")
    private Double mlongMortgageLoanAdd;

    @ApiModelProperty("中长期-质押贷款-增长率%")
    private Double mlongHypothecatedLoanAdd;

    @ApiModelProperty("中长期-保证贷款-增长率%")
    private Double mlongGuaranteeLoanAdd;

    @ApiModelProperty("中长期-信用贷款-增长率%")
    private Double mlongLoanCreditAdd;

    @ApiModelProperty("中长期-抵押贷款-利率%")
    private Double mlongMortgageLoanRise;

    @ApiModelProperty("中长期-质押贷款-利率%")
    private Double mlongHypothecatedLoanRise;

    @ApiModelProperty("中长期-保证贷款-利率%")
    private Double mlongGuaranteeLoanRise;

    @ApiModelProperty("中长期-信用贷款-利率%")
    private Double mlongLoanCreditRise;

    @ApiModelProperty("同业拆入")
    private Integer interBank;

    @ApiModelProperty("发行长期债券")
    private Integer issueLongBonds;

    @ApiModelProperty("发行次级债券")
    private Integer issueSecondaryBonds;

    @ApiModelProperty("短期存款-增长率%")
    private Double shortDepositAdd;

    @ApiModelProperty("短期储蓄存款-增长率%")
    private Double shortSavingsDepositAdd;

    @ApiModelProperty("同业存放款项-增长率%")
    private Double interbankdepositnnnAdd;

    @ApiModelProperty("长期存款-增长率%")
    private Double longDePositAdd;

    @ApiModelProperty("长期储蓄存款-增长率%")
    private Double longDepositAdd1;

    @ApiModelProperty("短期存款-利率%")
    private Double shortDepositRise;

    @ApiModelProperty("短期储蓄存款-利率%")
    private Double shortSavingsDepositRise;

    @ApiModelProperty("同业存放款项-利率%")
    private Double interbankdepositnnnRise;

    @ApiModelProperty("长期存款-利率%")
    private Double longDePositRise;

    @ApiModelProperty("长期储蓄存款-利率%")
    private Double longSavingsDepositRise;

    @ApiModelProperty("保存:0,提交:1")
    private Integer state;


}
