package net.lab1024.smartadmin.module.business.teacher.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * [ 场景管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-11 09:05:57
 * @since JDK1.8
 */
@Data
public class TSceneManagementQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("消息类型")
    private Integer releaseType;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
