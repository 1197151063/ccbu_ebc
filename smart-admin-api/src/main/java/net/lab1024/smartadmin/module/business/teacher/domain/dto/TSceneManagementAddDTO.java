package net.lab1024.smartadmin.module.business.teacher.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 新建 [ 场景管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2022-01-11 09:05:57
 * @since JDK1.8
 */
@Data
public class TSceneManagementAddDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("发布范围")
    private String team;

    @ApiModelProperty("创建人，即发布人")
    private Integer createrUser;

    @ApiModelProperty("发布类型")
    private Integer releaseType;

    @ApiModelProperty("发布方式")
    private String releaseMode;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("上次更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
