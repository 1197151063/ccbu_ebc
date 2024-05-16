package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 初始数据表-投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:09
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_investbus_stock")
public class ParInitialDataInvestbusStockEntity extends BaseEntity{


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
    private Long stockDividend;

    /**
     * 账面损益
     */
    private Integer stockAccount;

}
