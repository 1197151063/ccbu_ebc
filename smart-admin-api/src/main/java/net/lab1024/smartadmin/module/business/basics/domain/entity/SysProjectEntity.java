package net.lab1024.smartadmin.module.business.basics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

import java.util.Date;

/**
 * [ 项目表 ]
 *
 * @author SMS
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c)  1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date 2021-11-21 09:08:37
 * @since JDK1.8
 */
@Data
@TableName("sys_project")
public class SysProjectEntity{

    /**
     * 项目id
     */
    @TableId
    private Long proId;

    /**
     * 项目名称
     */
    private String proName;


    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
