package net.lab1024.smartadmin.module.business.teacher.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 场景管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2022-01-11 09:05:57
 * @since JDK1.8
 */
@Data
public class TSceneManagementExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "标题")
    private String title;

    @Excel(name = "内容")
    private String content;

    @Excel(name = "发布范围")
    private String team;

    @Excel(name = "创建人，即发布人")
    private Integer createrUser;

    @Excel(name = "发布类型")
    private Integer releaseType;

    @Excel(name = "发布方式")
    private String releaseMode;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "发布时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    @Excel(name = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "上次更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;



}
