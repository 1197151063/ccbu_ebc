package net.lab1024.smartadmin.module.system.employee.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) 2018 SMSInc. All rights reserved.
 * @date  2021-12-14 14:49:16
 * @since JDK1.8
 */
@Data
public class UserPhotoAddDTO {
    @ApiModelProperty("user_id")
    private Long userId;

    @ApiModelProperty("user_photo")
    private String userPhoto;


}
