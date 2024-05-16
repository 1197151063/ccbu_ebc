package net.lab1024.smartadmin.module.system.employee.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

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
public class UserPhotoExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "user_id")
    private Long userId;

    @Excel(name = "user_photo")
    private String userPhoto;



}
