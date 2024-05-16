package net.lab1024.smartadmin.module.business.decision.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 投资业务(短期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 15:47:18
 * @since JDK1.8
 */
@Data
@TableName("dec_investment_short")
public class DecInvestmentShortEntity extends BaseEntity{


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
     * 资产总量
     */
    private Integer totalAssets;

    /**
     * 股票
     */
    private Integer investmentStock;

    /**
     * 债券
     */
    private Integer nvestmentBond;

}
