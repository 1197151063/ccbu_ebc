package net.lab1024.smartadmin.module.business.basics.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 项目阶段关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-22 16:13:42
 * @since JDK1.8
 */
@Data
public class SysProjectStageAddDTO {
    @ApiModelProperty("项目id")
    private Long proId;

    @ApiModelProperty("阶段id")
    private Long stageId;

    @ApiModelProperty("阶段是否开放（0：未开放1：已开放）")
    private Integer status;


}
