package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-07 16:27:47
 * @since JDK1.8
 */
@Data
public class ParInitialDataPeopleLogisticsExcelVO {
    @Excel(name = "人事后勤信息录入ID")
    private Long id;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "业务类型")
    private String businessType;

    @Excel(name = "期初员工数(人)")
    private Integer startEmployeeQuantity;

    @Excel(name = "本阶段雇佣/解雇(人)")
    private Integer hareFire;

    @Excel(name = "本阶段流失人数(人)")
    private Integer drain;

    @Excel(name = "期末员工数(人)")
    private Integer endEmployeeQuantity;

    @Excel(name = "下阶段人员流失率(%)")
    private Double lossRate;

    @Excel(name = "下阶段流失人员数(人)")
    private Integer lostPersonnel;

    @Excel(name = "有效营业时间(千小时)")
    private Double effectiveTime;

    @Excel(name = "人员调配(千小时)-培训时间")
    private Double trainingTime;

    @Excel(name = "人员调配(千小时)-市场拓展")
    private Double marketDevelopment;

    @Excel(name = "人员调配(千小时)-客户维护")
    private Double customerMaintenance;

    @Excel(name = "可用营业时间(千小时)")
    private Double availableTime;

    @Excel(name = "需要营业时间(千小时)")
    private Double needTime;

    @Excel(name = "加班(千小时)")
    private Double workOvertime;

    @Excel(name = "临时工(千小时)")
    private Double temporaryWorker;

    @Excel(name = "培训水平")
    private String trainingLevel;

    @Excel(name = "工作满意度")
    private String jobSatisfaction;



}
