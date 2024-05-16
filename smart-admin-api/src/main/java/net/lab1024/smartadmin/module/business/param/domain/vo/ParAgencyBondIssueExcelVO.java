package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 代理债券发行业务信息表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 13:37:59
 * @since JDK1.8
 */
@Data
public class ParAgencyBondIssueExcelVO {
    @Excel(name = "代理债券发行业务信息ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "债券种类(甲/乙/丙)")
    private String typesBonds;

    @Excel(name = "票面利率(%)")
    private Double couponRate;

    @Excel(name = "期限")
    private Integer deadline;

    @Excel(name = "信用评级")
    private String creditRating;

    @Excel(name = "发行金额(百万元)")
    private Integer issuance;

    @Excel(name = "最低承销价格(%)")
    private Double minPrice;

    @Excel(name =  "认购额度")
    private Double subscriptionLimit;

}
