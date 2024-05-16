package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 市场份额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-13 20:06:33
 * @since JDK1.8
 */
@Data
public class ParMarketShareExcelVO {
//    @Excel(name = "市场份额ID")
//    private Long id;
//
//    @Excel(name = "当前项目ID")
//    private Long currentProjectId;
//
//    @Excel(name = "当前团队ID")
//    private Long currentTeamId;
//
//    @Excel(name = "当前阶段ID")
//    private Long currentStageId;

    @Excel(name = "团队名称")
    private String name;

    @Excel(name = "业务")
    private String business;

    @Excel(name = "市场份额")
    private Double marketShare;



}
