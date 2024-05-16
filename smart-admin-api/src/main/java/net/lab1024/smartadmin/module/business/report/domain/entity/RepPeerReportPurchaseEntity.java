package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

import java.util.Date;

/**
 * [ 同业报告购买记录表 ]
 *
 * @author WANGXF
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 12:46:28
 * @since JDK1.8
 */
@Data
@TableName("rep_peer_report_purchase")
public class RepPeerReportPurchaseEntity extends BaseEntity{
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 团队ID
     */
    private Long teamId;
    /**
     * 阶段ID
     */
    private Long stageId;

    /**
     * 购买人ID
     */
    private Long userId;

    /**
     * 项目
     */
    private String project;
    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 购买人姓名
     */
    private String userName;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("购买时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private String toSeeUrl;

}
