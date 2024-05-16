package net.lab1024.smartadmin.module.business.basics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import java.util.Date;
import lombok.Data;

/**
 * [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 10:13:24
 * @since JDK1.8
 */
@Data
@TableName("sys_team")
public class SysTeamEntity extends BaseEntity{

    /**
     * 团队id
     */
    private Long teamId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 项目id
     */
    private Long proId;


    /**
     * 结束时间
     */
    private Date deleteTime;

    /**
     * 排序
     */
    private String sort;

}
