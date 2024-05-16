package net.lab1024.smartadmin.module.business.report.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * [ 投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:22:46
 * @since JDK1.8
 */
@Data
public class RepInvestbusOperatDataBondQueryDTO extends PageParamDTO {

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
