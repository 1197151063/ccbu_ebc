package net.lab1024.smartadmin.module.business.report.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:23:18
 * @since JDK1.8
 */
@Data
@TableName("rep_investbus_operat_data_stock")
public class RepInvestbusOperatDataStockEntity extends BaseEntity{


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
     * 项目
     */
    private String stockProject;

    /**
     * 股票指数
     */
    private Double stockIndex;

    /**
     * 股票类型(长期或短期)
     */
    private String stockType;

    /**
     * 股票数量(手)
     */
    private Integer stockNumber;

    /**
     * 股票价值
     */
    private Integer stockCost;

    /**
     * 股息
     */
    private Integer stockDividend;

    /**
     * 账面损益
     */
    private Integer stockAccount;

}
