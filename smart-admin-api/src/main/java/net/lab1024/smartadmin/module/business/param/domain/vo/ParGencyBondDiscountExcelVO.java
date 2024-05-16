package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 代理债券折扣 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-09 14:44:56
 * @since JDK1.8
 */
@Data
public class ParGencyBondDiscountExcelVO {
    @Excel(name = "代理债券折扣ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "债券类型")
    private String bondType;

    @Excel(name = "折扣")
    private Integer discount;



}
