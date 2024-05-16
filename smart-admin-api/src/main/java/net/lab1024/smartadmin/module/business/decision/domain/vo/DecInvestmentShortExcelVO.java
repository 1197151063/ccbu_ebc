package net.lab1024.smartadmin.module.business.decision.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 投资业务(短期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-24 15:47:18
 * @since JDK1.8
 */
@Data
public class DecInvestmentShortExcelVO {
    @Excel(name = "投资业务(短期投资)ID")
    private Long id;

    @Excel(name = "当前项目ID")
    private Long currentProjectId;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "资产总量")
    private Integer totalAssets;

    @Excel(name = "股票")
    private Integer investmentStock;

    @Excel(name = "债券")
    private Integer nvestmentBond;



}
