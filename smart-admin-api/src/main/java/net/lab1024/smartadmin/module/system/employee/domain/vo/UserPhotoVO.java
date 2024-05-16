package net.lab1024.smartadmin.module.system.employee.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [  ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-12-14 14:49:16
 * @since JDK1.8
 */
@Data
public class UserPhotoVO {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("user_id")
    private Long userId;

    @ApiModelProperty("user_photo")
    private String userPhoto;



}
