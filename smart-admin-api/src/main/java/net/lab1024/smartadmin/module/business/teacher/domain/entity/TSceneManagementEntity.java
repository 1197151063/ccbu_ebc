package net.lab1024.smartadmin.module.business.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import java.util.Date;
import lombok.Data;

/**
 * [ 场景管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-11 09:05:57
 * @since JDK1.8
 */
@Data
@TableName("t_scene_management")
public class TSceneManagementEntity extends BaseEntity{


    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布范围
     */
    private String team;

    /**
     * 创建人，即发布人
     */
    private Integer createrUser;

    /**
     * 发布类型
     */
    private Integer releaseType;

    /**
     * 发布方式
     */
    private String releaseMode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 结束时间
     */
    private Date endTime;



}
