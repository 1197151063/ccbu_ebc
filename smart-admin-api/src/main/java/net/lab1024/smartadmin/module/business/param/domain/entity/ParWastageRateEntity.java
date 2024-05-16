package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 16:05:49
 * @since JDK1.8
 */
@Data
@TableName("par_wastage_rate")
public class ParWastageRateEntity extends BaseEntity{


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
    private Double wastageRate;

}
