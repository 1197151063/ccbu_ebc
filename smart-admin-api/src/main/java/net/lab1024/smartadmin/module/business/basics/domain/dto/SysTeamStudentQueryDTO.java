package net.lab1024.smartadmin.module.business.basics.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.PageParamDTO;


/**
 * [ 项目表 ]
 *
 * @author SMS
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c)  1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date 2021-11-21 09:08:37
 * @since JDK1.8
 */
@Data
public class SysTeamStudentQueryDTO extends PageParamDTO {

    @ApiModelProperty("团队id")
    private Long deptId;

    @ApiModelProperty("学员名称")
    private String actualName;

}
