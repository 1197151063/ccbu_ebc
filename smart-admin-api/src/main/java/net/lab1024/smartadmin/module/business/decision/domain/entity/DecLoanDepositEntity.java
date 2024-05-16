package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

/**
 * [ 存贷款 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 12:19:11
 * @since JDK1.8
 */
@Data
@TableName("dec_loan_deposit")
public class DecLoanDepositEntity extends BaseEntity{


    /**
     * 当前项目ID
     */
    private Long currentProjectId;

    /**
     * 当前团队ID
     */
    private Long currentTeamId;

    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 资产总量
     */
    private Integer totalAssets;

    /**
     * 存放中央银行款项(百万元)
     */
    private Integer dueCentralBank;

    /**
     * 存放同业款项(百万元)
     */
    private Integer interbankDeposit;

    /**
     * 拆放同业(百万元)
     */
    private Integer loanTrade;

    /**
     * 短期-抵押贷款-增长率%
     */
    private Double shortMortgageLoanAdd;

    /**
     * 短期-质押贷款-增长率%
     */
    private Double shortHypothecatedLoanAdd;

    /**
     * 短期-保证贷款-增长率%
     */
    private Double shortGuaranteeLoanAdd;

    /**
     * 短期-信用贷款-增长率%
     */
    private Double shortLoanCreditRiseAdd;

    /**
     * 短期-抵押贷款-利率%
     */
    private Double shortMortgageLoanRise;

    /**
     * 短期-质押贷款-利率%
     */
    private Double shortHypothecatedLoanRise;

    /**
     * 短期-保证贷款-利率%
     */
    private Double shortGuaranteeLoanRise;

    /**
     * 短期-信用贷款-利率%
     */
    private Double shortLoanCreditRise;

    /**
     * 贴现-增长率%
     */
    private Double discountAdd;

    /**
     * 贴现-利率%
     */
    private Double discountRise;

    /**
     * 中长期-抵押贷款-增长率%
     */
    private Double mlongMortgageLoanAdd;

    /**
     * 中长期-质押贷款-增长率%
     */
    private Double mlongHypothecatedLoanAdd;

    /**
     * 中长期-保证贷款-增长率%
     */
    private Double mlongGuaranteeLoanAdd;

    /**
     * 中长期-信用贷款-增长率%
     */
    private Double mlongLoanCreditAdd;

    /**
     * 中长期-抵押贷款-利率%
     */
    private Double mlongMortgageLoanRise;

    /**
     * 中长期-质押贷款-利率%
     */
    private Double mlongHypothecatedLoanRise;

    /**
     * 中长期-保证贷款-利率%
     */
    private Double mlongGuaranteeLoanRise;

    /**
     * 中长期-信用贷款-利率%
     */
    private Double mlongLoanCreditRise;

    /**
     * 同业拆入
     */
    private Integer interBank;

    /**
     * 发行长期债券
     */
    private Integer issueLongBonds;

    /**
     * 发行次级债券
     */
    private Integer issueSecondaryBonds;

    /**
     * 短期存款-增长率%
     */
    private Double shortDepositAdd;

    /**
     * 短期储蓄存款-增长率%
     */
    private Double shortSavingsDepositAdd;

    /**
     * 同业存放款项-增长率%
     */
    private Double interbankdepositnnnAdd;

    /**
     * 长期存款-增长率%
     */
    private Double longDePositAdd;

    /**
     * 长期储蓄存款-增长率%
     */
    private Double longDepositAdd1;

    /**
     * 短期存款-利率%
     */
    private Double shortDepositRise;

    /**
     * 短期储蓄存款-利率%
     */
    private Double shortSavingsDepositRise;

    /**
     * 同业存放款项-利率%
     */
    private Double interbankdepositnnnRise;

    /**
     * 长期存款-利率%
     */
    private Double longDePositRise;

    /**
     * 长期储蓄存款-利率%
     */
    private Double longSavingsDepositRise;


}
