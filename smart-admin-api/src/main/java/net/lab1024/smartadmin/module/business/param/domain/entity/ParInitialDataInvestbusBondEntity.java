package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 初始数据表-投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:03:28
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_investbus_bond")
public class ParInitialDataInvestbusBondEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 债券种类(来自各个阶段)
     */
    private String bondKind;

    /**
     * 价格(%)
     */
    private Double bondPrice;

    /**
     * 债券投资类型(长期或短期)
     */
    private String bondType;

    /**
     * 实际价值
     */
    private Integer realValue;

    /**
     * 名义价值
     */
    private Integer nominalValue;

    /**
     * 利率(%)
     */
    private Double rateInterest;

    /**
     * 利息
     */
    private Integer interest;

    /**
     * 账面损益
     */
    private Integer accountProfitLoss;

}
