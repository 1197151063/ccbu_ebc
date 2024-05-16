package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 综合评分指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-16 10:39:05
 * @since JDK1.8
 */
@Data
@TableName("par_comprehensive_score")
public class ParComprehensiveScoreEntity extends BaseEntity{


    /**
     * 指标
     */
    private String project;

    /**
     * 计分标准-最低
     */
    private Double scoringCriteriaMin;

    /**
     * 计分标准-最高
     */
    private Double scoringCriteriaMax;

    /**
     * 分数
     */
    private Double fraction;

    /**
     * 计算公式
     */
    private String calculationFormula;

}
