package net.lab1024.smartadmin.module.business.report.constant;

import net.lab1024.smartadmin.common.constant.ResponseCodeConst;

/**
 * 购买同业报告常量类
 * 15001-15999
 *
 * @author lidoudou
 * @date 2021年12月23日下午19:04:52
 */
public class PeerReportPurchaseRecordResponseCodeConst extends ResponseCodeConst {

    /**
     * 购买的同业报告当前团队的阶段不能为空
     */
    public static final PeerReportPurchaseRecordResponseCodeConst STAGE_NOT_EXISTS = new PeerReportPurchaseRecordResponseCodeConst(15001, "购买的同业报告当前团队的阶段不能为空！");

    public PeerReportPurchaseRecordResponseCodeConst(int code, String msg) {
        super(code, msg);
    }
}
