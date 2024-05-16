package net.lab1024.smartadmin.module.business.basics.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-21 10:13:24
 * @since JDK1.8
 */
@Data
public class SysTeamExcelVO {
    @Excel(name = "团队id")
    private Long teamId;

    @Excel(name = "团队名称")
    private String teamName;

    @Excel(name = "项目id")
    private Long proId;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    @Excel(name = "排序")
    private String sort;



}
