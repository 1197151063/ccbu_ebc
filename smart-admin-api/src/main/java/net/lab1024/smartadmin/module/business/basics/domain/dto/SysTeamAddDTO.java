package net.lab1024.smartadmin.module.business.basics.domain.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 团队表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-11-21 10:13:24
 * @since JDK1.8
 */
@Data
public class SysTeamAddDTO {
    @ApiModelProperty("团队id")
    private Long teamId;

    @ApiModelProperty("团队名称")
    private String teamName;

    @ApiModelProperty("项目id")
    private Long proId;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deleteTime;

    @ApiModelProperty("排序")
    private String sort;


}
