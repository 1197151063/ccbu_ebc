package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-07 16:27:47
 * @since JDK1.8
 */
@Data
@TableName("par_initial_data_people_logistics")
public class ParInitialDataPeopleLogisticsEntity extends BaseEntity{


    /**
     * 当前阶段ID
     */
    private Long currentStageId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 期初员工数(人)
     */
    private Integer startEmployeeQuantity;

    /**
     * 本阶段雇佣/解雇(人)
     */
    private Integer hareFire;

    /**
     * 本阶段流失人数(人)
     */
    private Integer drain;

    /**
     * 期末员工数(人)
     */
    private Integer endEmployeeQuantity;

    /**
     * 下阶段人员流失率(%)
     */
    private Double lossRate;

    /**
     * 下阶段流失人员数(人)
     */
    private Integer lostPersonnel;

    /**
     * 有效营业时间(千小时)
     */
    private Double effectiveTime;

    /**
     * 人员调配(千小时)-培训时间
     */
    private Double trainingTime;

    /**
     * 人员调配(千小时)-市场拓展
     */
    private Double marketDevelopment;

    /**
     * 人员调配(千小时)-客户维护
     */
    private Double customerMaintenance;

    /**
     * 可用营业时间(千小时)
     */
    private Double availableTime;

    /**
     * 需要营业时间(千小时)
     */
    private Double needTime;

    /**
     * 加班(千小时)
     */
    private Double workOvertime;

    /**
     * 临时工(千小时)
     */
    private Double temporaryWorker;

    /**
     * 培训水平
     */
    private String trainingLevel;

    /**
     * 工作满意度
     */
    private String jobSatisfaction;

}
