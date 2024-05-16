package net.lab1024.smartadmin.module.business.teacher.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 新建 [ 消息管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2022-01-06 09:46:05
 * @since JDK1.8
 */
@Data
public class TMessageAddDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("消息名称")
    private String messageName;

    @ApiModelProperty("消息类型")
    private Integer messageType;

    @ApiModelProperty("消息状态")
    private Integer status;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("发布团队")
    private String team;

    @ApiModelProperty("发布方式")
    private String releaseMode;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("创建人，即发布人")
    private Integer createrUser;

    @ApiModelProperty("上次更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
