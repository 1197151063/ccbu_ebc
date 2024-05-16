package net.lab1024.smartadmin.module.business.basics.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.Date;

/**
 * [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 10:13:24
 * @since JDK1.8
 */
@Data
public class SysTeamQueryDTO extends PageParamDTO {

    @ApiModelProperty("团队id")
    private Long teamId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("团队名称")
    private String teamName;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
