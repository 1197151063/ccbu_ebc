package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 初始数据表-市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-02 16:38:35
 * @since JDK1.8
 */
@Data
public class ParInitialDataMarketCustomerAccountVO {
    @ApiModelProperty("市场营销数据(客户账户)ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("客户账户营业数据")
    private String customerMarketBusinessData;

    @ApiModelProperty("账户数据类型[A=账户数量(单位：个)，V=单位账户平均价值(单位：千元)]")
    private String accountType;

    @ApiModelProperty("<35岁")
    private Integer lessAge;

    @ApiModelProperty("35~55岁")
    private Integer betweenAge;

    @ApiModelProperty(">55岁")
    private Integer greaterAge;

    @ApiModelProperty("小型企业")
    private Integer smallEnterprise;

    @ApiModelProperty("中型企业")
    private Integer mediumEnterprise;

    @ApiModelProperty("大型企业")
    private Integer majorEnterprise;

    @ApiModelProperty("公共机构")
    private Integer publicInstitution;

    @ApiModelProperty("账户合计")
    private Integer accountSum;



}
