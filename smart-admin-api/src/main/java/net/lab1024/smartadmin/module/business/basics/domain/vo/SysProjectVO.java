package net.lab1024.smartadmin.module.business.basics.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
public class SysProjectVO {
    @ApiModelProperty("项目id")
    private Long proId;

    @ApiModelProperty("项目名称")
    private String proName;

    @ApiModelProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String remarks;

}
