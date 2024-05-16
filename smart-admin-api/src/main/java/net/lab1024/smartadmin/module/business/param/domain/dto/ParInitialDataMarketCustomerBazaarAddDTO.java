package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 初始数据表-市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-02 16:39:55
 * @since JDK1.8
 */
@Data
public class ParInitialDataMarketCustomerBazaarAddDTO {
    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("客户市场营业数据")
    private String customerMarketBusinessData;

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

    @ApiModelProperty("金额合计")
    private Integer amountSum;


}
