package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 经济形势分析报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 13:18:35
 * @since JDK1.8
 */
@Data
@TableName("par_economic_situation")
public class ParEconomicSituationEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 国内经济
     */
    private String domesticEconomy;

    /**
     * 金融政策
     */
    private String financialPolicy;

    /**
     * 全球经济
     */
    private String globalEconomy;

}
