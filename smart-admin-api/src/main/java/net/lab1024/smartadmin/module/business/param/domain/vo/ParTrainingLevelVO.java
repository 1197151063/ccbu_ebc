package net.lab1024.smartadmin.module.business.param.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 培训水平 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-09 14:52:40
 * @since JDK1.8
 */
@Data
public class ParTrainingLevelVO {
    @ApiModelProperty("人员流失率ID")
    private Long id;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("业务类型")
    private String businessType;

    @ApiModelProperty("人员流失率")
    private String trainingLevel;



}
