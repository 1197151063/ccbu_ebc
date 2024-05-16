package net.lab1024.smartadmin.module.business.teacher.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2022-01-09 16:01:51
 * @since JDK1.8
 */
@Data
public class TDecisionSummaryExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "项目")
    private String project;

    @Excel(name = "阶段")
    private String stage;

    @Excel(name = "业务")
    private String business;

    @Excel(name = "决策团队")
    private String team;

    @Excel(name = "决策人")
    private String name;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "上次更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "总结状态")
    private Integer status;



}
