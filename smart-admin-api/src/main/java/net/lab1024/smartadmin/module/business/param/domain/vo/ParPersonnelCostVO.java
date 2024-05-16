package net.lab1024.smartadmin.module.business.param.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-02 14:36:42
 * @since JDK1.8
 */
@Data
public class ParPersonnelCostVO {
    @ApiModelProperty("人员成本表ID")
    private Long id;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("贷款业务")
    private Integer loanBusiness;

    @ApiModelProperty("存款业务")
    private Integer depositBusiness;

    @ApiModelProperty("投资业务")
    private Integer investmentBusiness;

    @ApiModelProperty("中间业务")
    private Integer middleBusiness;

    @ApiModelProperty("其他")
    private Integer otherBusiness;



}
