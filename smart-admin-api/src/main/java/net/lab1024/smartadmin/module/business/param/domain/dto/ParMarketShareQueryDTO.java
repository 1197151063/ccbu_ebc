package net.lab1024.smartadmin.module.business.param.domain.dto;

import net.lab1024.smartadmin.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * [ 市场份额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 20:06:33
 * @since JDK1.8
 */
@Data
public class ParMarketShareQueryDTO extends PageParamDTO {

    @ApiModelProperty("市场份额ID")
    private Long id;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
