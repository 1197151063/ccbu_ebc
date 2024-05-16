package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 公式 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-17 17:01:27
 * @since JDK1.8
 */
@Data
public class ParFormulaTotalExcelVO {
    @Excel(name = "公式ID")
    private Long id;

    @Excel(name = "业务")
    private String business;

    @Excel(name = "项目")
    private String project;

    @Excel(name = "公式")
    private String formula;



}
