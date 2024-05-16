package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 初始数据表-市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 16:39:55
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_market_customer_bazaar")
public class ParInitialDataMarketCustomerBazaarEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 客户市场营业数据
     */
    private String customerMarketBusinessData;

    /**
     * <35岁
     */
    private Integer lessAge;

    /**
     * 35~55岁
     */
    private Integer betweenAge;

    /**
     * >55岁
     */
    private Integer greaterAge;

    /**
     * 小型企业
     */
    private Integer smallEnterprise;

    /**
     * 中型企业
     */
    private Integer mediumEnterprise;

    /**
     * 大型企业
     */
    private Integer majorEnterprise;

    /**
     * 公共机构
     */
    private Integer publicInstitution;

    /**
     * 金额合计
     */
    private Integer amountSum;

}
