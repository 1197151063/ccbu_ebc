package net.lab1024.smartadmin.module.business.basics.domain.entity;

import lombok.Data;
import lombok.experimental.Tolerate;
import net.lab1024.smartadmin.common.domain.BaseEntity;

/**
 * Created by ${} on ${}.
 */
@Data
public class SysDepartmentEmployeeEntity extends BaseEntity {

    /**
     * 团队(部门)名称
     */
    private String name;

    /**
     * 用户学员名称
     */
    private String actualName;

//    @Tolerate
//    public SysTeamEmployeeEntity(){
//        teamName = "";
//        employeeName = "";
//    }


}
