package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 存贷款计算公式表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-07 14:42:04
 * @since JDK1.8
 */
@Data
public class ParFormulaExcelVO {
    @Excel(name = "存贷款计算公式ID")
    private Long id;

    @Excel(name = "业务名称")
    private String businessName;

    @Excel(name = "参数")
    private Double parameter;



}
