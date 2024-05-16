package net.lab1024.smartadmin.module.business.basics.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 项目阶段关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-22 16:13:42
 * @since JDK1.8
 */
@Data
public class SysProjectStageExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "项目id")
    private Long proId;

    @Excel(name = "阶段id")
    private Long stageId;

    @Excel(name = "阶段是否开放（0：未开放1：已开放）")
    private Integer status;



}
