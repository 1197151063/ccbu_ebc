package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

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
public class ParTrainingLevelExcelVO {
    @Excel(name = "人员流失率ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "业务类型")
    private String businessType;

    @Excel(name = "人员流失率")
    private String trainingLevel;



}
