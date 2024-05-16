package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:54
 * @since JDK1.8
 */
@Data
@TableName("par_marketshares_test")
public class ParMarketsharesTestEntity extends BaseEntity{


    /**
     * 当前团队ID
     */
    private Long currentTeamId;

    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 市场份额
     */
    private Double marketShares;

}
