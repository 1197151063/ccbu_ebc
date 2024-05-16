package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 培训水平 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 14:52:40
 * @since JDK1.8
 */
@Data
@TableName("par_training_level")
public class ParTrainingLevelEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 人员流失率
     */
    private String trainingLevel;

}
