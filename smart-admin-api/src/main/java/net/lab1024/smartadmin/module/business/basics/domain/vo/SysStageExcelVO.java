package net.lab1024.smartadmin.module.business.basics.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 阶段表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-22 15:55:16
 * @since JDK1.8
 */
@Data
public class SysStageExcelVO {
    @Excel(name = "阶段id")
    private Long stageId;

    @Excel(name = "阶段名称")
    private String stageName;

    @Excel(name = "create_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "delete_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    @Excel(name = "sort")
    private String sort;

    @Excel(name = "阶段英文名称")
    private String stageNameEng;



}
