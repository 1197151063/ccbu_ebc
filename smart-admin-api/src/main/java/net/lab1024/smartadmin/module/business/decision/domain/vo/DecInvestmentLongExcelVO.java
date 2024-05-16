package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 投资业务(长期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:46:37
 * @since JDK1.8
 */
@Data
public class DecInvestmentLongExcelVO {
    @Excel(name = "投资业务(长期投资)ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "股票/债券类型(P0~5)")
    private String bondType;

    @Excel(name = "买入或卖出(债券第一次只能买)")
    private Integer buySell;



}
