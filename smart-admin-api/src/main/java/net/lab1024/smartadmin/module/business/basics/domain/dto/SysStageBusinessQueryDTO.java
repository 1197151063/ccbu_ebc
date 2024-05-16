package net.lab1024.smartadmin.module.business.basics.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * [ 阶段业务名称 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 09:36:32
 * @since JDK1.8
 */
@Data
public class SysStageBusinessQueryDTO extends PageParamDTO {

    @ApiModelProperty("阶段id")
    private Long stageId;

    @ApiModelProperty("阶段业务名称ID")
    private Long id;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
