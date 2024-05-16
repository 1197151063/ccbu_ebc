package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 11:26:24
 * @since JDK1.8
 */
@Data
public class RepMarketDataCustomerBazaarVO {
    @ApiModelProperty("市场营销数据(客户市场)ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

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
