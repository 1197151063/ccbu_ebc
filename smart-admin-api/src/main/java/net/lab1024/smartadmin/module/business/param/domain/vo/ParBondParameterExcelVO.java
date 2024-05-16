package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 债券参数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:42:33
 * @since JDK1.8
 */
@Data
public class ParBondParameterExcelVO {
    @Excel(name = "ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "债券种类(各阶段解锁一个，初始值只能加不能减)")
    private String bondParameter;

    @Excel(name = "债券价格%")
    private Double bondPrice;



}
