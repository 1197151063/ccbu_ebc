package net.lab1024.smartadmin.module.business.basics.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:49:37
 * @since JDK1.8
 */
@Data
public class SysBeginStageDTO {

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;





}
