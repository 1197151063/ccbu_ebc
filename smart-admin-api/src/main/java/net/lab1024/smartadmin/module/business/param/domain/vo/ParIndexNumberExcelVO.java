package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 自动化投资指数 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-09 18:42:39
 * @since JDK1.8
 */
@Data
public class ParIndexNumberExcelVO {
    @Excel(name = "自动化投资指数ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "指数")
    private Double indexNumber;

    @Excel(name = "所需工作小时变动率")
    private Double rateOfChange;

    @Excel(name = "累计投资")
    private Integer cumulativeInvestment;



}
