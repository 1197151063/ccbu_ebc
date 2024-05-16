package net.lab1024.smartadmin.module.business.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

import java.util.Date;

/**
 * [ 消息管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-06 09:46:05
 * @since JDK1.8
 */
@Data
@TableName("t_message")
public class TMessageEntity extends BaseEntity{


    /**
     * 消息名称
     */
    private String messageName;

    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 消息状态
     */
    private Integer status;

    /**
     * 创建人，即发布人
     */
    private Integer createrUser;


//    @ApiModelProperty("消息内容")
    private String content;

//    @ApiModelProperty("发布团队")
    private String team;

//    @ApiModelProperty("发布方式")
    private String releaseMode;

//    @ApiModelProperty("发布时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

//    @ApiModelProperty("结束时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
