package net.lab1024.smartadmin.module.business.param.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [ 人员成本表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 14:36:42
 * @since JDK1.8
 */
@Data
@TableName("par_personnel_cost")
public class ParPersonnelCostEntity extends BaseEntity{


    /**
     * 项目
     */
    private Long sysProjectId;

    private String project;


//    private Integer stage;

    /**
     * 贷款业务
     */
    private Integer loanBusiness;

    /**
     * 存款业务
     */
    private Integer depositBusiness;

    /**
     * 投资业务
     */
    private Integer investmentBusiness;

    /**
     * 中间业务
     */
    private Long middleBusiness;

    /**
     * 其他
     */
    private Integer otherBusiness;

}
