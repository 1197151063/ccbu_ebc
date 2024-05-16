package net.lab1024.smartadmin.module.business.basics.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 阶段业务名称 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-30 09:36:32
 * @since JDK1.8
 */
@Data
public class SysStageBusinessExcelVO {
    @Excel(name = "阶段业务名称ID")
    private Long id;

    @Excel(name = "阶段id")
    private Long stageId;

    @Excel(name = "阶段名称")
    private String stageName;

    @Excel(name = "业务名称")
    private String businessName;



}
