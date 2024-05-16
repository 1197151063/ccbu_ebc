package net.lab1024.smartadmin.module.business.report.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 11:26:24
 * @since JDK1.8
 */
@Data
public class RepMarketDataCustomerBazaarExcelVO {
//    @Excel(name = "市场营销数据(客户市场)ID")
//    private Long id;
//
//    @Excel(name = "当前项目ID")
//    private Long currentProjectId;
//
//    @Excel(name = "当前团队ID")
//    private Long currentTeamId;
//
//    @Excel(name = "当前阶段ID")
//    private Long currentStageId;

    @Excel(name = "团队名称")
    private String name;

    @Excel(name = "客户市场营业数据")
    private String customerMarketBusinessData;

    @Excel(name = "<35岁")
    private Integer lessAge;

    @Excel(name = "35~55岁")
    private Integer betweenAge;

    @Excel(name = ">55岁")
    private Integer greaterAge;

    @Excel(name = "小型企业")
    private Integer smallEnterprise;

    @Excel(name = "中型企业")
    private Integer mediumEnterprise;

    @Excel(name = "大型企业")
    private Integer majorEnterprise;

    @Excel(name = "公共机构")
    private Integer publicInstitution;

    @Excel(name = "金额合计")
    private Integer amountSum;



}
