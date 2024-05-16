package net.lab1024.smartadmin.module.business.basics.domain.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 阶段表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-22 15:55:16
 * @since JDK1.8
 */
@Data
public class SysStageAddDTO {
    @ApiModelProperty("阶段id")
    private Long stageId;

    @ApiModelProperty("阶段名称")
    private String stageName;

    @ApiModelProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("delete_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deleteTime;

    @ApiModelProperty("sort")
    private String sort;

    @ApiModelProperty("阶段英文名称")
    private String stageNameEng;


}
