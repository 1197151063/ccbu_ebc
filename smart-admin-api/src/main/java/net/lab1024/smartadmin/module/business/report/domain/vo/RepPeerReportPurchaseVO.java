package net.lab1024.smartadmin.module.business.report.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.xiaoymin.knife4j.annotations.Ignore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *  [ 代理债券发行业务数据表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-23 12:46:28
 * @since JDK1.8
 */
@Data
public class RepPeerReportPurchaseVO {
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("团队ID")
    private Long teamId;

    @ApiModelProperty("阶段ID")
    private Long stageId;

    @ApiModelProperty("购买人ID")
    private Long userId;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("团队名称")
    private String teamName;

    @ApiModelProperty("阶段")
    private String stage;

    @ApiModelProperty("购买人姓名")
    private String userName;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("购买时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String toSeeUrl;

}
