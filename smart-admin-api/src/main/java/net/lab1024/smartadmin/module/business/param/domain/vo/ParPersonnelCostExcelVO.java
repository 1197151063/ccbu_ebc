package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-02 14:36:42
 * @since JDK1.8
 */
@Data
public class ParPersonnelCostExcelVO {
    @Excel(name = "人员成本表ID")
    private Long id;

    @Excel(name = "项目")
    private String project;

    @Excel(name = "贷款业务")
    private Integer loanBusiness;

    @Excel(name = "存款业务")
    private Integer depositBusiness;

    @Excel(name = "投资业务")
    private Integer investmentBusiness;

    @Excel(name = "中间业务")
    private Long middleBusiness;

    @Excel(name = "其他")
    private Integer otherBusiness;



}
