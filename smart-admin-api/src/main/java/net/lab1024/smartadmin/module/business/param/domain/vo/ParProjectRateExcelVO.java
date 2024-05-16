package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 项目利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:52:11
 * @since JDK1.8
 */
@Data
public class ParProjectRateExcelVO {
    @Excel(name = "项目利率ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "项目名")
    private String projectName;

    @Excel(name = "利率 利率/股指")
    private Double interestRate;



}
