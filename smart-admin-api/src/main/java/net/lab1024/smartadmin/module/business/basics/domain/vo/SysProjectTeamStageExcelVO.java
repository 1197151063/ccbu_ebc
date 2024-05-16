package net.lab1024.smartadmin.module.business.basics.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 项目团队阶段业务关联表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-22 11:10:47
 * @since JDK1.8
 */
@Data
public class SysProjectTeamStageExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "项目id")
    private Long proId;

    @Excel(name = "团队id")
    private Long teamId;

    @Excel(name = "阶段id")
    private Long stageId;

    @Excel(name = "业务名称")
    private String businessName;

    @Excel(name = "业务提交状态")
    private Integer status;



}
