package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 市场总额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 18:27:21
 * @since JDK1.8
 */
@Data
@TableName("par_total_market")
public class ParTotalMarketEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 分类类型
     */
    private String classificationType;

    /**
     * 市场总额值
     */
    private Integer totalMarketValue;

}
