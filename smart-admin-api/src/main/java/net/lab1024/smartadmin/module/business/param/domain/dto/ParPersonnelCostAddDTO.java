package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-02 14:36:42
 * @since JDK1.8
 */
@Data
public class  ParPersonnelCostAddDTO {
    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("贷款业务")
    private Integer loanBusiness;

    @ApiModelProperty("存款业务")
    private Integer depositBusiness;

    @ApiModelProperty("投资业务")
    private Integer investmentBusiness;

    @ApiModelProperty("中间业务")
    private Long middleBusiness;

    @ApiModelProperty("其他")
    private Integer otherBusiness;


}
