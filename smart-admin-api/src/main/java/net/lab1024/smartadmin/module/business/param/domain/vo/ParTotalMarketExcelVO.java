package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

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
public class ParTotalMarketExcelVO {
    @Excel(name = "市场总额ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "分类类型")
    private String classificationType;

    @Excel(name = "市场总额值")
    private Integer totalMarketValue;



}
