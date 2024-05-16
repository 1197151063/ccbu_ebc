package net.lab1024.smartadmin.module.business.report.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-07 17:25:20
 * @since JDK1.8
 */
@Data
public class RepPeopleLogisticsVO {
    @ApiModelProperty("人事后勤信息录入ID")
    private Long id;

    @ApiModelProperty("当前项目ID")
    private Long currentProjectId;

    @ApiModelProperty("当前团队ID")
    private Long currentTeamId;

    @ApiModelProperty("当前阶段ID")
    private Long currentStageId;

    @ApiModelProperty("业务类型")
    private String businessType;

    @ApiModelProperty("期初员工数(人)")
    private Integer startEmployeeQuantity;

    @ApiModelProperty("本阶段雇佣/解雇(人)")
    private Integer hareFire;

    @ApiModelProperty("本阶段流失人数(人)")
    private Integer drain;

    @ApiModelProperty("期末员工数(人)")
    private Integer endEmployeeQuantity;

    @ApiModelProperty("下阶段人员流失率(%)")
    private Double lossRate;

    @ApiModelProperty("下阶段流失人员数(人)")
    private Integer lostPersonnel;

    @ApiModelProperty("有效营业时间(千小时)")
    private Double effectiveTime;

    @ApiModelProperty("人员调配(千小时)-培训时间")
    private Double trainingTime;

    @ApiModelProperty("人员调配(千小时)-市场拓展")
    private Double marketDevelopment;

    @ApiModelProperty("人员调配(千小时)-客户维护")
    private Double customerMaintenance;

    @ApiModelProperty("可用营业时间(千小时)")
    private Double availableTime;

    @ApiModelProperty("需要营业时间(千小时)")
    private Double needTime;

    @ApiModelProperty("加班(千小时)")
    private Double workOvertime;

    @ApiModelProperty("临时工(千小时)")
    private Double temporaryWorker;

    @ApiModelProperty("培训水平")
    private String trainingLevel;

    @ApiModelProperty("工作满意度")
    private String jobSatisfaction;



}
