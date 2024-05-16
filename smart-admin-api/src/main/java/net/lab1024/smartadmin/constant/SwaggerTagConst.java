package net.lab1024.smartadmin.constant;

/**
 * []
 *
 * @author yandanyang
 * @version 1.0
 * @since JDK1.8
 */
public class SwaggerTagConst {


    public static class Admin {
        public static final String MANAGER_SYSTEM_CONFIG = "管理端-系统配置";

        public static final String MANAGER_USER = "管理端-用户";

        public static final String MANAGER_USER_LOGIN = "管理端-用户登录";

        public static final String MANAGER_DEPARTMENT = "管理端-部门";

        public static final String MANAGER_ROLE = "管理端-角色";

        public static final String MANAGER_ROLE_USER = "管理端-角色用户";

        public static final String MANAGER_ROLE_PRIVILEGE = "管理端-角色权限";

        public static final String MANAGER_SMART_RELOAD = "管理端-smart reload";

        public static final String MANAGER_ORDER_OPERATE_LOG = "管理端-单据操作日志";

        public static final String MANAGER_TASK_SCHEDULER = "管理端-任务调度";

        public static final String MANAGER_USER_LOGIN_LOG = "管理端-用户登录日志";

        public static final String MANAGER_USER_OPERATE_LOG = "管理端-用户操作日志";

        public static final String MANAGER_DATA_SCOPE = "管理端-数据范围";

        public static final String MANAGER_JOB = "管理端-岗位";

        public static final String MANAGER_NOTICE = "管理端-系统通知";

        public static final String MANAGER_FILE = "通用-文件服务";

        public static final String MANAGER_PRIVILEGE = "通用-权限";

        public static final String MANAGER_EMAIL = "通用-邮件发送";

        public static final String MANAGER_HEART_BEAT = "通用-心跳服务";

    }

    /**
     *  sys
     *  创建项目，团队，阶段
     */
    public static class StudentSystem {
        public static final String MANAGER_PROJECT = "项目表";

        public static final String MANAGER_PROJECT_TEAM_STAGE = "项目团队阶段业务关联表";

        public static final String MANAGER_STAGE_BUSINESS = "阶段业务名称";

        public static final String MANAGER_TEAM = "团队表";

    }

    /**
     * 学生端-决策数据接口
     */
    public static class StudentDec {

        public static final String STUDENT_DECISION_FINANACE = "学生端-填入数据-财务管理";

        public static final String STUDENT_DECISION_INVESTMENT = "学生端-填入数据-投资业务(短期投资)提交表";

        public static final String STUDENT_DECISION_DECLOANDEPOSIT = "学生端-填入数据-存贷款";

        public static final String STUDENT_DECISION_MARKET = "学生端-填入数据-市场营销策略";

        public static final String STUDENT_DECISION_MIDDLE = "学生端-填入数据-中间业务提交表";

        public static final String STUDENT_DECISION_PERSONNEL = "学生端-填入数据-人事后勤信息录入";

    }


    /**
     * 学生端-报表
     */
    public static class StudentRep {


        public static final String STUDENT_REPORT_BALANCE = "学生端-结果表-资产负债表";

        public static final String STUDENT_REPORT_CAPITAL = "学生端-结果表-资本充足率报告";

        public static final String STUDENT_REPORT_INVESTMENT_BOND = "学生端-结果表-投资业务营业数据表(债券)";

        public static final String STUDENT_REPORT_INVESTMENT_STOCK = "学生端-结果表-投资业务营业数据表(股票)";

        public static final String STUDENT_REPORT_LIQUIDITY_CASH = "学生端-结果表-流动性报表(现金)";

        public static final String STUDENT_REPORT_LIQUIDITY_TOTALITY = "学生端-结果表-流动性报表(总体)";

        public static final String STUDENT_REPORT_MARKET_ACCOUNT = "学生端-结果表-市场营销数据(客户账户)";

        public static final String STUDENT_REPORT_MARKET_BAZAAR = "学生端-结果表-市场营销数据(客户市场)";

        public static final String STUDENT_REPORT_AUTOMATION = "学生端-结果表-人事后勤数据表(自动化投资)";

        public static final String STUDENT_REPORT_PERSONNE = "学生端-结果表-人事后勤数据表";

        public static final String STUDENT_REPORT_PROFIT = "学生端-结果表-利润表";
    }

    /**
     * 学生端-参数表
     */
    public static class StudentPar {
        public static final String STUDENT_PARAM_PROPERTYLIABILITYRATE = "学生端-参数表-资产负债利率表";
        public static final String STUDENT_PARAM_PROJECTRATE = "学生端-参数表-项目利率表";
    }
    }
