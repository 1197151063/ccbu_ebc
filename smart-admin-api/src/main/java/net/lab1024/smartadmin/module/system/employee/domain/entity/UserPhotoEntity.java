package net.lab1024.smartadmin.module.system.employee.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.lab1024.smartadmin.common.domain.BaseEntity;
import lombok.Data;

/**
 * [  ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-14 14:49:16
 * @since JDK1.8
 */
@Data
@TableName("user_photo")
public class UserPhotoEntity extends BaseEntity{


    /**
     * user_id
     */
    private Long userId;

    /**
     * user_photo
     */
    private String userPhoto;

}
