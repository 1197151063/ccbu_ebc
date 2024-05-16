package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 自动化投资指数 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-09 18:42:39
 * @since JDK1.8
 */
@Data
@TableName("par_index_number")
public class ParIndexNumberEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 指数
     */
    private Double indexNumber;

    /**
     * 所需工作小时变动率
     */
    private Double rateOfChange;

    /**
     * 累计投资
     */
    private Integer cumulativeInvestment;

}
