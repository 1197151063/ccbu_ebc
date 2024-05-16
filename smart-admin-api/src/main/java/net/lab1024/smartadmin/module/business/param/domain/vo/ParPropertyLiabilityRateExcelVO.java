package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 资产负债利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:52:53
 * @since JDK1.8
 */
@Data
public class ParPropertyLiabilityRateExcelVO {
    @Excel(name = "资产负债利率ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "类型(资产，负债)")
    private String interestRateType;

    @Excel(name = "最低")
    private Double interestRateMin;

    @Excel(name = "平均")
    private Double interestRateAverage;

    @Excel(name = "最高")
    private Double interestRateMax;



}
