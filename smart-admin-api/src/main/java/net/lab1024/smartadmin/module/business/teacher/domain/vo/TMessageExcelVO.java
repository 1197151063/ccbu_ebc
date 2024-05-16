package net.lab1024.smartadmin.module.business.teacher.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 消息管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2022-01-06 09:46:05
 * @since JDK1.8
 */
@Data
public class TMessageExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "消息名称")
    private String messageName;

    @Excel(name = "消息类型")
    private Integer messageType;

    @Excel(name = "消息状态")
    private Integer status;

    @Excel(name = "创建人，即发布人")
    private Integer createrUser;

    @Excel(name = "上次更新时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}
