package net.lab1024.smartadmin.module.business.param.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [ 各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c) SMSInc. All rights reserved.
 * @date  2021-11-25 14:04:54
 * @since JDK1.8
 */
@Data
public class ParMarketsharesTestExcelVO {
    @Excel(name = "市场份额ID")
    private Long id;

    @Excel(name = "当前团队ID")
    private Long currentTeamId;

    @Excel(name = "当前阶段ID")
    private Long currentStageId;

    @Excel(name = "业务类型")
    private String businessType;

    @Excel(name = "市场份额")
    private Double marketShares;



}
