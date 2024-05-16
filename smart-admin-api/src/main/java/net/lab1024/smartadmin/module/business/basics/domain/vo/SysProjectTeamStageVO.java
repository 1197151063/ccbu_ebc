package net.lab1024.smartadmin.module.business.basics.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  [ 项目团队阶段业务关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-22 11:10:47
 * @since JDK1.8
 */
@Data
public class SysProjectTeamStageVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("项目id")
    private Long proId;

    @ApiModelProperty("团队id")
    private Long teamId;

    @ApiModelProperty("团队名称")
    private String name;

    @ApiModelProperty("阶段id")
    private Long stageId;

    @ApiModelProperty("业务名称")
    private String businessName;

    @ApiModelProperty("业务提交状态")
    private Integer status;



}
