package net.lab1024.smartadmin.module.business.basics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
//import jdk.nashorn.internal.ir.annotations.Ignore;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import java.util.Date;
import lombok.Data;

/**
 * [ 阶段表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 15:55:16
 * @since JDK1.8
 */
@Data
@TableName("sys_stage")
public class SysStageEntity{

    /**
     * 阶段id
     */
    @TableId
    private Long stageId;

    /**
     * 阶段名称
     */
    private String stageName;

    /**
     * 阶段英文名称
     */
    private String stageNameEng;

}
