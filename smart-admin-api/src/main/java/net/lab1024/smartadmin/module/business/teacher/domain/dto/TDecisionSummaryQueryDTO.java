package net.lab1024.smartadmin.module.business.teacher.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.Date;

/**
 * [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-09 16:01:51
 * @since JDK1.8
 */
@Data
public class TDecisionSummaryQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("阶段")
    private String stage;

    @ApiModelProperty("业务")
    private String business;

    @ApiModelProperty("决策团队")
    private String team;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
