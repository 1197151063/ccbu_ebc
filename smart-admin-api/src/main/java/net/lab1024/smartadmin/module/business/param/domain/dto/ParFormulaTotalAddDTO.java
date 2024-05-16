package net.lab1024.smartadmin.module.business.param.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 公式 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-17 17:01:27
 * @since JDK1.8
 */
@Data
public class ParFormulaTotalAddDTO {
    @ApiModelProperty("业务")
    private String business;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("公式")
    private String formula;


}
