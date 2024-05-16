package net.lab1024.smartadmin.module.business.basics.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 项目表 ]
 *
 * @author SMS
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c) 1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date  2021-11-21 09:08:37
 * @since JDK1.8
 */
@Data
public class SysProjectExcelVO {
    @Excel(name = "项目id")
    private Long proId;

    @Excel(name = "项目名称")
    private String proName;

    @Excel(name = "create_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "update_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;



}
