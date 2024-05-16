package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 市场总额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-30 18:27:21
 * @since JDK1.8
 */
@Data
public class ParTotalMarketVO {
    @ApiModelProperty("市场总额ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("分类类型")
    private String classificationType;

    @ApiModelProperty("市场总额值")
    private Integer totalMarketValue;



}
