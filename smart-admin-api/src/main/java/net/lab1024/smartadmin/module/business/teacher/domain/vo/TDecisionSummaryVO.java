package net.lab1024.smartadmin.module.business.teacher.domain.vo;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2022-01-09 16:01:51
 * @since JDK1.8
 */
@Data
public class TDecisionSummaryVO {
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

    @ApiModelProperty("决策人")
    private String name;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("上次更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("总结状态")
    private Integer status;



}
