package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 投资业务(长期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 15:46:37
 * @since JDK1.8
 */
@Data
@TableName("dec_investment_long")
public class DecInvestmentLongEntity extends BaseEntity{
    /**
     * 当前项目ID
     */
    private Long currentProjectId;

    /**
     * 当前团队ID
     */
    private Long currentTeamId;

    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 股票/债券类型(P0~5)
     */
    private String bondType;

    /**
     * 买入或卖出(债券第一次只能买)
     */
    private Integer buySell;

}
