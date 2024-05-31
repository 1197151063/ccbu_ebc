create table act_evt_log
(
    LOG_NR_       bigint auto_increment
        primary key,
    TYPE_         varchar(64)                               null,
    PROC_DEF_ID_  varchar(64)                               null,
    PROC_INST_ID_ varchar(64)                               null,
    EXECUTION_ID_ varchar(64)                               null,
    TASK_ID_      varchar(64)                               null,
    TIME_STAMP_   timestamp(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3),
    USER_ID_      varchar(255)                              null,
    DATA_         longblob                                  null,
    LOCK_OWNER_   varchar(255)                              null,
    LOCK_TIME_    timestamp(3)                              null,
    IS_PROCESSED_ tinyint      default 0                    null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_ge_property
(
    NAME_  varchar(64)  not null
        primary key,
    VALUE_ varchar(300) null,
    REV_   int          null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_hi_actinst
(
    ID_                varchar(64)             not null
        primary key,
    PROC_DEF_ID_       varchar(64)             not null,
    PROC_INST_ID_      varchar(64)             not null,
    EXECUTION_ID_      varchar(64)             not null,
    ACT_ID_            varchar(255)            not null,
    TASK_ID_           varchar(64)             null,
    CALL_PROC_INST_ID_ varchar(64)             null,
    ACT_NAME_          varchar(255)            null,
    ACT_TYPE_          varchar(255)            not null,
    ASSIGNEE_          varchar(255)            null,
    START_TIME_        datetime(3)             not null,
    END_TIME_          datetime(3)             null,
    DURATION_          bigint                  null,
    TENANT_ID_         varchar(255) default '' null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_HI_ACT_INST_END
    on act_hi_actinst (END_TIME_);

create index ACT_IDX_HI_ACT_INST_EXEC
    on act_hi_actinst (EXECUTION_ID_, ACT_ID_);

create index ACT_IDX_HI_ACT_INST_PROCINST
    on act_hi_actinst (PROC_INST_ID_, ACT_ID_);

create index ACT_IDX_HI_ACT_INST_START
    on act_hi_actinst (START_TIME_);

create table act_hi_attachment
(
    ID_           varchar(64)   not null
        primary key,
    REV_          int           null,
    USER_ID_      varchar(255)  null,
    NAME_         varchar(255)  null,
    DESCRIPTION_  varchar(4000) null,
    TYPE_         varchar(255)  null,
    TASK_ID_      varchar(64)   null,
    PROC_INST_ID_ varchar(64)   null,
    URL_          varchar(4000) null,
    CONTENT_ID_   varchar(64)   null,
    TIME_         datetime(3)   null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_hi_comment
(
    ID_           varchar(64)   not null
        primary key,
    TYPE_         varchar(255)  null,
    TIME_         datetime(3)   not null,
    USER_ID_      varchar(255)  null,
    TASK_ID_      varchar(64)   null,
    PROC_INST_ID_ varchar(64)   null,
    ACTION_       varchar(255)  null,
    MESSAGE_      varchar(4000) null,
    FULL_MSG_     longblob      null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_hi_detail
(
    ID_           varchar(64)   not null
        primary key,
    TYPE_         varchar(255)  not null,
    PROC_INST_ID_ varchar(64)   null,
    EXECUTION_ID_ varchar(64)   null,
    TASK_ID_      varchar(64)   null,
    ACT_INST_ID_  varchar(64)   null,
    NAME_         varchar(255)  not null,
    VAR_TYPE_     varchar(255)  null,
    REV_          int           null,
    TIME_         datetime(3)   not null,
    BYTEARRAY_ID_ varchar(64)   null,
    DOUBLE_       double        null,
    LONG_         bigint        null,
    TEXT_         varchar(4000) null,
    TEXT2_        varchar(4000) null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_HI_DETAIL_ACT_INST
    on act_hi_detail (ACT_INST_ID_);

create index ACT_IDX_HI_DETAIL_NAME
    on act_hi_detail (NAME_);

create index ACT_IDX_HI_DETAIL_PROC_INST
    on act_hi_detail (PROC_INST_ID_);

create index ACT_IDX_HI_DETAIL_TASK_ID
    on act_hi_detail (TASK_ID_);

create index ACT_IDX_HI_DETAIL_TIME
    on act_hi_detail (TIME_);

create table act_hi_identitylink
(
    ID_           varchar(64)  not null
        primary key,
    GROUP_ID_     varchar(255) null,
    TYPE_         varchar(255) null,
    USER_ID_      varchar(255) null,
    TASK_ID_      varchar(64)  null,
    PROC_INST_ID_ varchar(64)  null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_HI_IDENT_LNK_PROCINST
    on act_hi_identitylink (PROC_INST_ID_);

create index ACT_IDX_HI_IDENT_LNK_TASK
    on act_hi_identitylink (TASK_ID_);

create index ACT_IDX_HI_IDENT_LNK_USER
    on act_hi_identitylink (USER_ID_);

create table act_hi_procinst
(
    ID_                        varchar(64)             not null
        primary key,
    PROC_INST_ID_              varchar(64)             not null,
    BUSINESS_KEY_              varchar(255)            null,
    PROC_DEF_ID_               varchar(64)             not null,
    START_TIME_                datetime(3)             not null,
    END_TIME_                  datetime(3)             null,
    DURATION_                  bigint                  null,
    START_USER_ID_             varchar(255)            null,
    START_ACT_ID_              varchar(255)            null,
    END_ACT_ID_                varchar(255)            null,
    SUPER_PROCESS_INSTANCE_ID_ varchar(64)             null,
    DELETE_REASON_             varchar(4000)           null,
    TENANT_ID_                 varchar(255) default '' null,
    NAME_                      varchar(255)            null,
    constraint PROC_INST_ID_
        unique (PROC_INST_ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_HI_PRO_INST_END
    on act_hi_procinst (END_TIME_);

create index ACT_IDX_HI_PRO_I_BUSKEY
    on act_hi_procinst (BUSINESS_KEY_);

create table act_hi_taskinst
(
    ID_             varchar(64)             not null
        primary key,
    PROC_DEF_ID_    varchar(64)             null,
    TASK_DEF_KEY_   varchar(255)            null,
    PROC_INST_ID_   varchar(64)             null,
    EXECUTION_ID_   varchar(64)             null,
    NAME_           varchar(255)            null,
    PARENT_TASK_ID_ varchar(64)             null,
    DESCRIPTION_    varchar(4000)           null,
    OWNER_          varchar(255)            null,
    ASSIGNEE_       varchar(255)            null,
    START_TIME_     datetime(3)             not null,
    CLAIM_TIME_     datetime(3)             null,
    END_TIME_       datetime(3)             null,
    DURATION_       bigint                  null,
    DELETE_REASON_  varchar(4000)           null,
    PRIORITY_       int                     null,
    DUE_DATE_       datetime(3)             null,
    FORM_KEY_       varchar(255)            null,
    CATEGORY_       varchar(255)            null,
    TENANT_ID_      varchar(255) default '' null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_HI_TASK_INST_PROCINST
    on act_hi_taskinst (PROC_INST_ID_);

create table act_hi_varinst
(
    ID_                varchar(64)   not null
        primary key,
    PROC_INST_ID_      varchar(64)   null,
    EXECUTION_ID_      varchar(64)   null,
    TASK_ID_           varchar(64)   null,
    NAME_              varchar(255)  not null,
    VAR_TYPE_          varchar(100)  null,
    REV_               int           null,
    BYTEARRAY_ID_      varchar(64)   null,
    DOUBLE_            double        null,
    LONG_              bigint        null,
    TEXT_              varchar(4000) null,
    TEXT2_             varchar(4000) null,
    CREATE_TIME_       datetime(3)   null,
    LAST_UPDATED_TIME_ datetime(3)   null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_HI_PROCVAR_NAME_TYPE
    on act_hi_varinst (NAME_, VAR_TYPE_);

create index ACT_IDX_HI_PROCVAR_PROC_INST
    on act_hi_varinst (PROC_INST_ID_);

create index ACT_IDX_HI_PROCVAR_TASK_ID
    on act_hi_varinst (TASK_ID_);

create table act_id_group
(
    ID_   varchar(64)  not null
        primary key,
    REV_  int          null,
    NAME_ varchar(255) null,
    TYPE_ varchar(255) null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_id_info
(
    ID_        varchar(64)  not null
        primary key,
    REV_       int          null,
    USER_ID_   varchar(64)  null,
    TYPE_      varchar(64)  null,
    KEY_       varchar(255) null,
    VALUE_     varchar(255) null,
    PASSWORD_  longblob     null,
    PARENT_ID_ varchar(255) null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_id_user
(
    ID_         varchar(64)  not null
        primary key,
    REV_        int          null,
    FIRST_      varchar(255) null,
    LAST_       varchar(255) null,
    EMAIL_      varchar(255) null,
    PWD_        varchar(255) null,
    PICTURE_ID_ varchar(64)  null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_id_membership
(
    USER_ID_  varchar(64) not null,
    GROUP_ID_ varchar(64) not null,
    primary key (USER_ID_, GROUP_ID_),
    constraint ACT_FK_MEMB_GROUP
        foreign key (GROUP_ID_) references act_id_group (ID_),
    constraint ACT_FK_MEMB_USER
        foreign key (USER_ID_) references act_id_user (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_re_deployment
(
    ID_          varchar(64)             not null
        primary key,
    NAME_        varchar(255)            null,
    CATEGORY_    varchar(255)            null,
    TENANT_ID_   varchar(255) default '' null,
    DEPLOY_TIME_ timestamp(3)            null
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_ge_bytearray
(
    ID_            varchar(64)  not null
        primary key,
    REV_           int          null,
    NAME_          varchar(255) null,
    DEPLOYMENT_ID_ varchar(64)  null,
    BYTES_         longblob     null,
    GENERATED_     tinyint      null,
    constraint ACT_FK_BYTEARR_DEPL
        foreign key (DEPLOYMENT_ID_) references act_re_deployment (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_re_model
(
    ID_                           varchar(64)             not null
        primary key,
    REV_                          int                     null,
    NAME_                         varchar(255)            null,
    KEY_                          varchar(255)            null,
    CATEGORY_                     varchar(255)            null,
    CREATE_TIME_                  timestamp(3)            null,
    LAST_UPDATE_TIME_             timestamp(3)            null,
    VERSION_                      int                     null,
    META_INFO_                    varchar(4000)           null,
    DEPLOYMENT_ID_                varchar(64)             null,
    EDITOR_SOURCE_VALUE_ID_       varchar(64)             null,
    EDITOR_SOURCE_EXTRA_VALUE_ID_ varchar(64)             null,
    TENANT_ID_                    varchar(255) default '' null,
    constraint ACT_FK_MODEL_DEPLOYMENT
        foreign key (DEPLOYMENT_ID_) references act_re_deployment (ID_),
    constraint ACT_FK_MODEL_SOURCE
        foreign key (EDITOR_SOURCE_VALUE_ID_) references act_ge_bytearray (ID_),
    constraint ACT_FK_MODEL_SOURCE_EXTRA
        foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_) references act_ge_bytearray (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_re_procdef
(
    ID_                     varchar(64)             not null
        primary key,
    REV_                    int                     null,
    CATEGORY_               varchar(255)            null,
    NAME_                   varchar(255)            null,
    KEY_                    varchar(255)            not null,
    VERSION_                int                     not null,
    DEPLOYMENT_ID_          varchar(64)             null,
    RESOURCE_NAME_          varchar(4000)           null,
    DGRM_RESOURCE_NAME_     varchar(4000)           null,
    DESCRIPTION_            varchar(4000)           null,
    HAS_START_FORM_KEY_     tinyint                 null,
    HAS_GRAPHICAL_NOTATION_ tinyint                 null,
    SUSPENSION_STATE_       int                     null,
    TENANT_ID_              varchar(255) default '' null,
    constraint ACT_UNIQ_PROCDEF
        unique (KEY_, VERSION_, TENANT_ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_procdef_info
(
    ID_           varchar(64) not null
        primary key,
    PROC_DEF_ID_  varchar(64) not null,
    REV_          int         null,
    INFO_JSON_ID_ varchar(64) null,
    constraint ACT_UNIQ_INFO_PROCDEF
        unique (PROC_DEF_ID_),
    constraint ACT_FK_INFO_JSON_BA
        foreign key (INFO_JSON_ID_) references act_ge_bytearray (ID_),
    constraint ACT_FK_INFO_PROCDEF
        foreign key (PROC_DEF_ID_) references act_re_procdef (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_INFO_PROCDEF
    on act_procdef_info (PROC_DEF_ID_);

create table act_ru_execution
(
    ID_               varchar(64)             not null
        primary key,
    REV_              int                     null,
    PROC_INST_ID_     varchar(64)             null,
    BUSINESS_KEY_     varchar(255)            null,
    PARENT_ID_        varchar(64)             null,
    PROC_DEF_ID_      varchar(64)             null,
    SUPER_EXEC_       varchar(64)             null,
    ACT_ID_           varchar(255)            null,
    IS_ACTIVE_        tinyint                 null,
    IS_CONCURRENT_    tinyint                 null,
    IS_SCOPE_         tinyint                 null,
    IS_EVENT_SCOPE_   tinyint                 null,
    SUSPENSION_STATE_ int                     null,
    CACHED_ENT_STATE_ int                     null,
    TENANT_ID_        varchar(255) default '' null,
    NAME_             varchar(255)            null,
    LOCK_TIME_        timestamp(3)            null,
    constraint ACT_FK_EXE_PARENT
        foreign key (PARENT_ID_) references act_ru_execution (ID_),
    constraint ACT_FK_EXE_PROCDEF
        foreign key (PROC_DEF_ID_) references act_re_procdef (ID_),
    constraint ACT_FK_EXE_PROCINST
        foreign key (PROC_INST_ID_) references act_ru_execution (ID_)
            on update cascade on delete cascade,
    constraint ACT_FK_EXE_SUPER
        foreign key (SUPER_EXEC_) references act_ru_execution (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_ru_event_subscr
(
    ID_            varchar(64)                               not null
        primary key,
    REV_           int                                       null,
    EVENT_TYPE_    varchar(255)                              not null,
    EVENT_NAME_    varchar(255)                              null,
    EXECUTION_ID_  varchar(64)                               null,
    PROC_INST_ID_  varchar(64)                               null,
    ACTIVITY_ID_   varchar(64)                               null,
    CONFIGURATION_ varchar(255)                              null,
    CREATED_       timestamp(3) default CURRENT_TIMESTAMP(3) not null,
    PROC_DEF_ID_   varchar(64)                               null,
    TENANT_ID_     varchar(255) default ''                   null,
    constraint ACT_FK_EVENT_EXEC
        foreign key (EXECUTION_ID_) references act_ru_execution (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_EVENT_SUBSCR_CONFIG_
    on act_ru_event_subscr (CONFIGURATION_);

create index ACT_IDX_EXEC_BUSKEY
    on act_ru_execution (BUSINESS_KEY_);

create table act_ru_job
(
    ID_                  varchar(64)             not null
        primary key,
    REV_                 int                     null,
    TYPE_                varchar(255)            not null,
    LOCK_EXP_TIME_       timestamp(3)            null,
    LOCK_OWNER_          varchar(255)            null,
    EXCLUSIVE_           tinyint(1)              null,
    EXECUTION_ID_        varchar(64)             null,
    PROCESS_INSTANCE_ID_ varchar(64)             null,
    PROC_DEF_ID_         varchar(64)             null,
    RETRIES_             int                     null,
    EXCEPTION_STACK_ID_  varchar(64)             null,
    EXCEPTION_MSG_       varchar(4000)           null,
    DUEDATE_             timestamp(3)            null,
    REPEAT_              varchar(255)            null,
    HANDLER_TYPE_        varchar(255)            null,
    HANDLER_CFG_         varchar(4000)           null,
    TENANT_ID_           varchar(255) default '' null,
    constraint ACT_FK_JOB_EXCEPTION
        foreign key (EXCEPTION_STACK_ID_) references act_ge_bytearray (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_ru_task
(
    ID_               varchar(64)             not null
        primary key,
    REV_              int                     null,
    EXECUTION_ID_     varchar(64)             null,
    PROC_INST_ID_     varchar(64)             null,
    PROC_DEF_ID_      varchar(64)             null,
    NAME_             varchar(255)            null,
    PARENT_TASK_ID_   varchar(64)             null,
    DESCRIPTION_      varchar(4000)           null,
    TASK_DEF_KEY_     varchar(255)            null,
    OWNER_            varchar(255)            null,
    ASSIGNEE_         varchar(255)            null,
    DELEGATION_       varchar(64)             null,
    PRIORITY_         int                     null,
    CREATE_TIME_      timestamp(3)            null,
    DUE_DATE_         datetime(3)             null,
    CATEGORY_         varchar(255)            null,
    SUSPENSION_STATE_ int                     null,
    TENANT_ID_        varchar(255) default '' null,
    FORM_KEY_         varchar(255)            null,
    constraint ACT_FK_TASK_EXE
        foreign key (EXECUTION_ID_) references act_ru_execution (ID_),
    constraint ACT_FK_TASK_PROCDEF
        foreign key (PROC_DEF_ID_) references act_re_procdef (ID_),
    constraint ACT_FK_TASK_PROCINST
        foreign key (PROC_INST_ID_) references act_ru_execution (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create table act_ru_identitylink
(
    ID_           varchar(64)  not null
        primary key,
    REV_          int          null,
    GROUP_ID_     varchar(255) null,
    TYPE_         varchar(255) null,
    USER_ID_      varchar(255) null,
    TASK_ID_      varchar(64)  null,
    PROC_INST_ID_ varchar(64)  null,
    PROC_DEF_ID_  varchar(64)  null,
    constraint ACT_FK_ATHRZ_PROCEDEF
        foreign key (PROC_DEF_ID_) references act_re_procdef (ID_),
    constraint ACT_FK_IDL_PROCINST
        foreign key (PROC_INST_ID_) references act_ru_execution (ID_),
    constraint ACT_FK_TSKASS_TASK
        foreign key (TASK_ID_) references act_ru_task (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_ATHRZ_PROCEDEF
    on act_ru_identitylink (PROC_DEF_ID_);

create index ACT_IDX_IDENT_LNK_GROUP
    on act_ru_identitylink (GROUP_ID_);

create index ACT_IDX_IDENT_LNK_USER
    on act_ru_identitylink (USER_ID_);

create index ACT_IDX_TASK_CREATE
    on act_ru_task (CREATE_TIME_);

create table act_ru_variable
(
    ID_           varchar(64)   not null
        primary key,
    REV_          int           null,
    TYPE_         varchar(255)  not null,
    NAME_         varchar(255)  not null,
    EXECUTION_ID_ varchar(64)   null,
    PROC_INST_ID_ varchar(64)   null,
    TASK_ID_      varchar(64)   null,
    BYTEARRAY_ID_ varchar(64)   null,
    DOUBLE_       double        null,
    LONG_         bigint        null,
    TEXT_         varchar(4000) null,
    TEXT2_        varchar(4000) null,
    constraint ACT_FK_VAR_BYTEARRAY
        foreign key (BYTEARRAY_ID_) references act_ge_bytearray (ID_),
    constraint ACT_FK_VAR_EXE
        foreign key (EXECUTION_ID_) references act_ru_execution (ID_),
    constraint ACT_FK_VAR_PROCINST
        foreign key (PROC_INST_ID_) references act_ru_execution (ID_)
)
    collate = utf8_bin
    row_format = DYNAMIC;

create index ACT_IDX_VARIABLE_TASK_ID
    on act_ru_variable (TASK_ID_);

create table data_parameter
(
    id              int auto_increment comment '主键ID'
        primary key,
    template_id     varchar(255) null comment '模板id',
    parameter_name  varchar(255) null comment '参数名称',
    parameter_value varchar(255) null comment '参数值',
    file_path       varchar(255) null comment '文件路径',
    file_name       varchar(255) null comment '文件名称'
)
    comment '参数表' charset = utf8mb4
                     row_format = DYNAMIC;

create table dec_financial_management
(
    id                 int unsigned auto_increment comment '财务管理ID'
        primary key,
    current_project_id int(10)       not null comment '当前项目ID',
    current_team_id    int(10)       not null comment '当前团队ID',
    current_stage_id   int(3)        not null comment '当前阶段ID',
    share_bonus        double(10, 2) null comment '分红（%）',
    synergia           int(10)       null comment '增效（百万元）'
)
    comment '财务管理表' charset = utf8mb4
                         row_format = DYNAMIC;

create table dec_investment_long
(
    id                 int unsigned auto_increment comment '投资业务(长期投资)ID'
        primary key,
    current_project_id int(10)     not null comment '当前项目ID',
    current_team_id    int(10)     not null comment '当前团队ID',
    current_stage_id   int(3)      not null comment '当前阶段ID',
    bond_type          varchar(10) null comment '股票/债券类型(P0~5)',
    buy_sell           int(10)     null comment '买入或卖出(债券第一次只能买)'
)
    comment '投资业务(长期投资)提交表' charset = utf8mb4
                                       row_format = DYNAMIC;

create table dec_investment_short
(
    id                 int unsigned auto_increment comment '投资业务(短期投资)ID'
        primary key,
    current_project_id int(10) not null comment '当前项目ID',
    current_team_id    int(10) not null comment '当前团队ID',
    current_stage_id   int(3)  not null comment '当前阶段ID',
    total_assets       int(10) null comment '资产总量',
    investment_stock   int(10) null comment '股票',
    nvestment_bond     int(10) null comment '债券'
)
    comment '投资业务(短期投资)提交表' charset = utf8mb4
                                       row_format = DYNAMIC;

create table dec_loan_deposit
(
    id                           int unsigned auto_increment comment '存贷款ID'
        primary key,
    current_project_id           int(10)       not null comment '当前项目ID',
    current_team_id              int(10)       not null comment '当前团队ID',
    current_stage_id             int(3)        not null comment '当前阶段ID',
    total_assets                 int(10)       null comment '资产总量',
    due_central_bank             int(10)       null comment '存放中央银行款项(百万元)',
    interbank_deposit            int(10)       null comment '存放同业款项(百万元)',
    loan_trade                   int(10)       null comment '拆放同业(百万元)',
    short_mortgage_loan_add      double(10, 2) null comment '短期-抵押贷款-增长率%',
    short_hypothecated_loan_add  double(10, 2) null comment '短期-质押贷款-增长率%',
    short_guarantee_loan_add     double(10, 2) null comment '短期-保证贷款-增长率%',
    short_loan_credit_rise_add   double(10, 2) null comment '短期-信用贷款-增长率%',
    short_mortgage_loan_rise     double(10, 2) null comment '短期-抵押贷款-利率%',
    short_hypothecated_loan_rise double(10, 2) null comment '短期-质押贷款-利率%',
    short_guarantee_loan_rise    double(10, 2) null comment '短期-保证贷款-利率%',
    short_loan_credit_rise       double(10, 2) null comment '短期-信用贷款-利率%',
    discount_add                 double(10, 2) null comment '贴现-增长率%',
    discount_rise                double(10, 2) null comment '贴现-利率%',
    mlong_mortgage_loan_add      double(10, 2) null comment '中长期-抵押贷款-增长率%',
    mlong_hypothecated_loan_add  double(10, 2) null comment '中长期-质押贷款-增长率%',
    mlong_guarantee_loan_add     double(10, 2) null comment '中长期-保证贷款-增长率%',
    mlong_loan_credit_add        double(10, 2) null comment '中长期-信用贷款-增长率%',
    mlong_mortgage_loan_rise     double(10, 2) null comment '中长期-抵押贷款-利率%',
    mlong_hypothecated_loan_rise double(10, 2) null comment '中长期-质押贷款-利率%',
    mlong_guarantee_loan_rise    double(10, 2) null comment '中长期-保证贷款-利率%',
    mlong_loan_credit_rise       double(10, 2) null comment '中长期-信用贷款-利率%',
    inter_bank                   int(10)       null comment '同业拆入',
    issue_long_bonds             int(10)       null comment '发行长期债券',
    issue_secondary_bonds        int(10)       null comment '发行次级债券',
    short_deposit_add            double(10, 2) null comment '短期存款-增长率%',
    short_savings_deposit_add    double(10, 2) null comment '短期储蓄存款-增长率%',
    interbankdepositnnn_add      double(10, 2) null comment '同业存放款项-增长率%',
    long_de_posit_add            double(10, 2) null comment '长期存款-增长率%',
    long_deposit_add1            double(10, 2) null comment '长期储蓄存款-增长率%',
    short_deposit_rise           double(10, 2) null comment '短期存款-利率%',
    short_savings_deposit_rise   double(10, 2) null comment '短期储蓄存款-利率%',
    interbankdepositnnn_rise     double(10, 2) null comment '同业存放款项-利率%',
    long_de_posit_rise           double(10, 2) null comment '长期存款-利率%',
    long_savings_deposit_rise    double(10, 2) null
)
    comment '存贷款' charset = utf8mb4
                     row_format = DYNAMIC;

create table dec_market_strategy
(
    id                                           int unsigned auto_increment comment '市场营销策略ID'
        primary key,
    current_project_id                           int(10) not null comment '当前项目ID',
    current_team_id                              int(10) not null comment '当前团队ID',
    current_stage_id                             int(3)  not null comment '当前阶段ID',
    headcount                                    int(10) null comment '职员总数',
    loans_busine_develop_less                    int(10) null comment '贷款业务-市场拓展<35岁',
    loans_customer_main_less                     int(10) null comment '贷款业务-客户维护<35岁',
    deposit_busine_develop_less                  int(10) null comment '存款业务-市场拓展<35岁',
    deposit_customer_main_less                   int(10) null comment '存款业务-客户维护<35岁',
    middle_busine_develop_less                   int(10) null comment '中间业务-市场拓展<35岁',
    middle_customer_main_less                    int(10) null comment '中间业务-客户维护<35岁',
    loans_busine_develop_between                 int(10) null comment '贷款业务-市场拓展35~55岁',
    loans_customer_main_between                  int(10) null comment '贷款业务-客户维护35~55岁',
    deposit_busine_develop_between               int(10) null comment '存款业务-市场拓展35~55岁',
    deposit_customer_main_between                int(10) null comment '存款业务-客户维护35~55岁',
    middle_busine_develop_between                int(10) null comment '中间业务-市场拓展35~55岁',
    middle_customer_main_between                 int(10) null comment '中间业务-客户维护35~55岁',
    loans_busine_develop_greater                 int(10) null comment '贷款业务-市场拓展>55岁',
    loans_customer_main_greater                  int(10) null comment '贷款业务-客户维护>55岁',
    deposit_busine_develop_greater               int(10) null comment '存款业务-市场拓展>55岁',
    deposit_customer_main_greater                int(10) null comment '存款业务-客户维护>55岁',
    middle_busine_develop_greater                int(10) null comment '中间业务-市场拓展>55岁',
    middle_customer_main_greater                 int(10) null comment '中间业务-客户维护>55岁',
    loans_busine_develop_small                   int(10) null comment '贷款业务-市场拓展-小型企业',
    loans_customer_main_small                    int(10) null comment '贷款业务-客户维护-小型企业',
    deposit_busine_develop_small                 int(10) null comment '存款业务-市场拓展-小型企业',
    deposit_customer_main_small                  int(10) null comment '存款业务-客户维护-小型企业',
    middle_busine_develop_small                  int(10) null comment '中间业务-市场拓展-小型企业',
    middle_customer_main_small                   int(10) null comment '中间业务-客户维护-小型企业',
    loans_busine_develop_medium                  int(10) null comment '贷款业务-市场拓展-中型企业',
    loans_customer_main_medium                   int(10) null comment '贷款业务-客户维护-中型企业',
    deposit_busine_develop_medium                int(10) null comment '存款业务-市场拓展-中型企业',
    deposit_customer_main_medium                 int(10) null comment '存款业务-客户维护-中型企业',
    middle_busine_develop_medium                 int(10) null comment '中间业务-市场拓展-中型企业',
    middle_customer_main_medium                  int(10) null comment '中间业务-客户维护-中型企业',
    loans_busine_develop_major                   int(10) null comment '贷款业务-市场拓展-大型企业',
    loans_customer_main_major                    int(10) null comment '贷款业务-客户维护-大型企业',
    deposit_busine_develop_major                 int(10) null comment '存款业务-市场拓展-大型企业',
    deposit_customer_main_major                  int(10) null comment '存款业务-客户维护-大型企业',
    middle_busine_develop_major                  int(10) null comment '中间业务-市场拓展-大型企业',
    middle_customer_main_major                   int(10) null comment '中间业务-客户维护-大型企业',
    loans_busine_develop_public                  int(10) null comment '贷款业务-市场拓展-公共机构',
    loans_customer_main_public                   int(10) null comment '贷款业务-客户维护-公共机构',
    deposit_busine_develop_public                int(10) null comment '存款业务-市场拓展-公共机构',
    deposit_customer_main_public                 int(10) null comment '存款业务-客户维护-公共机构',
    middle_busine_develop_public                 int(10) null comment '中间业务-市场拓展-公共机构',
    middle_customer_main_public                  int(10) null comment '中间业务-客户维护-公共机构',
    customer_marketing_total_cost_available      int(10) null comment '客户营销-可用费用总量',
    customer_marketing_less_age                  int(10) null comment '客户营销-<35岁',
    customer_marketing_between_age               int(10) null comment '客户营销-35~55岁',
    customer_marketing_greater_age               int(10) null comment '客户营销->55岁',
    customer_marketing_small_enterprise          int(10) null comment '客户营销-小型企业',
    customer_marketing_medium_enterprise         int(10) null comment '客户营销-中型企业',
    customer_marketing_major_enterprise          int(10) null comment '客户营销-大型企业',
    customer_marketing_public_institution        int(10) null comment '客户营销-公共机构',
    business_marketing_total_cost_available      int(10) null comment '业务营销-可用费用总量',
    business_marketing_loan_transaction          int(10) null comment '业务营销-贷款业务(单位：百万元)',
    business_marketing_deposit_bloan_transaction int(10) null comment '业务营销-存款业务(单位：百万元)',
    business_marketing_middle_transaction        int(10) null comment '业务营销-中间业务(单位：百万元)'
)
    comment '市场营销策略' charset = utf8mb4
                           row_format = DYNAMIC;

create table dec_middle_custody
(
    id                           int unsigned auto_increment comment '中间业务()提交表ID'
        primary key,
    current_project_id           int(10)       not null comment '当前项目ID',
    current_team_id              int(10)       not null comment '当前团队ID',
    current_stage_id             int(3)        not null comment '当前阶段ID',
    custody_business_add         double(10, 2) null comment '托管业务-增长率%',
    guarantee_business_add       double(10, 2) null comment '担保业务-增长率%',
    custody_business_procedure   double(10, 2) null comment '托管业务-手续费率%',
    guarantee_business_procedure double(10, 2) null comment '担保业务-手续费率%',
    first_underwrit_price_bond   double(10, 2) null comment '甲债券承销价(%)',
    second_underwrit_price_bond  double(10, 2) null comment '乙债券承销价(%)',
    third_underwrit_price_bond   double(10, 2) null comment '丙债券承销价(%)'
)
    comment '中间业务提交表' charset = utf8mb4
                             row_format = DYNAMIC;

create table dec_personnel
(
    id                           int unsigned auto_increment comment '人事后勤信息录入ID'
        primary key,
    current_project_id           int(10)       not null comment '当前项目ID',
    current_team_id              int(10)       not null comment '当前团队ID',
    current_stage_id             int(3)        not null comment '当前阶段ID',
    hire_fire_loans              int(10)       null comment '雇佣/解聘(雇员数量)-贷款业务',
    train_loans                  int(10)       null comment '培训(天/人)-贷款业务',
    business_development_loans   double(10, 2) null comment '市场拓展(%)-贷款业务',
    customer_care_loans          double(10, 2) null comment '客户维护(%)-贷款业务',
    hire_fire_deposit            int(10)       null comment '雇佣/解聘(雇员数量)-存款业务',
    train_deposit                int(10)       null comment '培训(天/人)-存款业务',
    business_development_deposit double(10, 2) null comment '市场拓展(%)-存款业务',
    customer_care_deposit        double(10, 2) null comment '客户维护(%)-存款业务',
    hire_fire_invest             int(10)       null comment '雇佣/解聘(雇员数量)-投资业务',
    train_invest                 int(10)       null comment '培训(天/人)-投资业务',
    business_development_invest  double(10, 2) null comment '市场拓展(%)-投资业务',
    customer_care_invest         double(10, 2) null comment '客户维护(%)-投资业务',
    hire_fire_middle             int(10)       null comment '雇佣/解聘(雇员数量)-中间业务',
    train_middle                 int(10)       null comment '培训(天/人)-中间业务',
    business_development_middle  double(10, 2) null comment '市场拓展(%)-中间业务',
    customer_care_middle         double(10, 2) null comment '客户维护(%)-中间业务',
    additional_personnel_cost    double(10, 2) null comment '附加人员成本(%)',
    automation_investment        int(10)       null comment '自动化投资(百万元)'
)
    comment '人事后勤信息录入' charset = utf8mb4
                               row_format = DYNAMIC;

create table par_agency_bond_issue
(
    id                 int unsigned auto_increment comment '代理债券发行业务信息ID'
        primary key,
    current_stage_id   int(3)        not null comment '当前阶段ID',
    types_bonds        varchar(50)   not null comment '债券种类(甲/乙/丙)',
    coupon_rate        double(10, 2) not null comment '票面利率(%)',
    deadline           int(10)       not null comment '期限',
    credit_rating      varchar(50)   not null comment '信用评级',
    issuance           int(10)       not null comment '发行金额(百万元)',
    min_price          double(10, 2) not null comment '最低承销价格(%)',
    subscription_limit double(10, 2) null comment '认购额度'
)
    comment '代理债券发行业务信息表' charset = utf8mb4
                                     row_format = DYNAMIC;

create table par_bond_parameter
(
    id               int unsigned auto_increment comment 'ID'
        primary key,
    current_stage_id int(3)        not null comment '当前阶段ID',
    bond_parameter   varchar(50)   not null comment '债券种类(各阶段解锁一个，初始值只能加不能减)',
    bond_price       double(10, 1) not null comment '债券价格%'
)
    comment '债券参数表' charset = utf8mb4
                         row_format = DYNAMIC;

create table par_capital_adequacy
(
    id                                  int unsigned auto_increment comment '资本充足率-风险加权系数%ID'
        primary key,
    cash                                int(10) null comment '现金',
    due_central_bank                    int(10) null comment '存放中央银行款项',
    deposit_interbank                   int(10) null comment '存放同业款项',
    loan_trade                          int(10) null comment '拆放同业',
    short_mortgage_loan                 int(10) null comment '短期_抵押贷款',
    short_hypothecated_loan             int(10) null comment '短期_质押贷款',
    short_guarantee_loan                int(10) null comment '短期_保证贷款',
    short_credit_loan                   int(10) null comment '短期_信用贷款',
    discount                            int(10) null comment '贴现',
    mlong_mortgage_loan                 int(10) null comment '中长期_抵押贷款',
    mlong_hypothecated_loan             int(10) null comment '中长期_质押贷款',
    mlong_guarantee_loan                int(10) null comment '中长期_保证贷款',
    mlong_credit_loan                   int(10) null comment '中长期_信用贷款',
    current_investment_bond             int(10) null comment '短期投资-债券',
    current_investment_stock            int(10) null comment '短期投资-股票',
    long_debt_investment                int(10) null comment '长期债权投资',
    long_equity_investment              int(10) null comment '长期股权投资',
    fixed_assets                        int(10) null comment '固定资产',
    intangible_assets                   int(10) null comment '无形资产',
    other_assets                        int(10) null comment '其他资产',
    total                               int(10) null comment '总计',
    capital_adequacy_requirements       int(10) null comment '资本充足率要求',
    capital_source_core_stock           int(10) null comment '资本来源-核心资本-股本',
    capital_source_core_capital_reserve int(10) null comment '资本来源-核心资本-资本公积',
    capital_source_core_surplus_reserve int(10) null comment '资本来源-核心资本-盈余公积',
    capital_source_core_un_profit       int(10) null comment '资本来源-核心资本-未分配利润',
    capital_source_loss_reserves        int(10) null comment '资本来源-附属资本-贷款损失准备',
    capital_source_subord_bond          int(10) null comment '资本来源-附属资本-次级债券',
    capital_source_total                int(10) null comment '资本来源总额',
    capital_surplus_shortage            int(10) null comment '资本盈余/不足'
)
    comment '资本充足率-风险加权系数%' charset = utf8mb4
                                       row_format = DYNAMIC;

create table par_comprehensive_score
(
    id                   int unsigned auto_increment comment '综合评价指标（算尾不算头）ID'
        primary key,
    project              varchar(50)   null comment '指标',
    scoring_criteria_min double(10, 3) null comment '计分标准-最低',
    scoring_criteria_max double(10, 3) null comment '计分标准-最高',
    fraction             double(10, 1) null comment '分数',
    calculation_formula  varchar(255)  null comment '计算公式'
)
    comment '人员成本表' charset = utf8mb4
                         row_format = DYNAMIC;

create table par_economic_situation
(
    id               int unsigned auto_increment comment '经济形势ID'
        primary key,
    current_stage_id int(3)       not null comment '当前阶段ID',
    domestic_economy varchar(255) null comment '国内经济',
    financial_policy varchar(255) null comment '金融政策',
    global_economy   varchar(255) null comment '全球经济'
)
    comment '经济形势分析报告' charset = utf8mb4
                               row_format = DYNAMIC;

create table par_formula
(
    id            int unsigned auto_increment comment '存贷款计算公式ID'
        primary key,
    business_name varchar(50)   null comment '业务名称',
    parameter     double(10, 2) null comment '参数'
)
    comment '存贷款计算公式表' charset = utf8mb4
                               row_format = DYNAMIC;

create table par_formula_total
(
    id       int unsigned auto_increment comment '公式ID'
        primary key,
    business varchar(50)  null comment '业务',
    project  varchar(50)  null comment '项目',
    formula  varchar(255) null comment '公式'
)
    comment '公式' charset = utf8mb4
                   row_format = DYNAMIC;

create table par_gency_bond_discount
(
    id               int unsigned auto_increment comment '代理债券折扣ID'
        primary key,
    current_stage_id int(3)      not null comment '当前阶段ID',
    bond_type        varchar(50) null comment '债券类型',
    discount         int(10)     null comment '折扣'
)
    comment '代理债券折扣' charset = utf8mb4
                           row_format = DYNAMIC;

create table par_index_number
(
    id                    int unsigned auto_increment comment '自动化投资指数ID'
        primary key,
    current_stage_id      int(3)        not null comment '当前阶段ID',
    index_number          double(10, 1) null comment '指数',
    rate_of_change        double(10, 2) null comment '所需工作小时变动率',
    cumulative_investment int(10)       null comment '累计投资'
)
    comment '自动化投资指数' charset = utf8mb4
                             row_format = DYNAMIC;

create table par_initial_data_agency_bond
(
    id                 int unsigned auto_increment comment '代理债券参数表ID'
        primary key,
    current_stage_id   int(3)        not null comment '当前阶段ID',
    bond_type          varchar(50)   null comment '债券类型',
    coupon_rate        double(10, 2) null comment '票面利率(%)',
    deadline           int(10)       null comment '期限',
    credit_rating      varchar(50)   null comment '信用评级',
    issue_amount       int(10)       null comment '发行金额',
    underwriting_bank  varchar(50)   null comment '承销发行银行',
    underwriting_mount int(10)       null comment '承销金额（百万元）',
    subscription_limit double(10, 2) null comment '认购额度（%）',
    underwriting_price double(10, 2) null comment '承销价格（%）',
    discount           int(10)       null comment '折扣（%）'
)
    comment '代理债券参数表' charset = utf8mb4
                             row_format = DYNAMIC;

create table par_initial_data_automation
(
    id                    int unsigned auto_increment comment '自动化投资'
        primary key,
    current_stage_id      int(3)        not null comment '当前阶段ID',
    project               varchar(50)   null comment '项目',
    present_stage         double(10, 1) null comment '本阶段',
    cumulative_investment double(10, 1) null comment '累计投资',
    exponent              double(10, 1) null comment '指数'
)
    comment '初始数据表-人事后勤数据表(自动化投资)' charset = utf8mb4
                                                    row_format = DYNAMIC;

create table par_initial_data_capital_adequacy
(
    id                                  int unsigned auto_increment comment '资本充足率报告ID'
        primary key,
    current_stage_id                    int(3)        not null comment '当前阶段ID',
    property_risk                       varchar(50)   null comment '资产风险(账面价值(百万元) 风险加权系数% 风险加权资产(百万元) 经济资本分配系数%)',
    cash                                double(10, 2) null comment '现金',
    due_central_bank                    double(10, 2) null comment '存放中央银行款项',
    deposit_interbank                   double(10, 2) null comment '存放同业款项',
    loan_trade                          double(10, 2) null comment '拆放同业',
    short_mortgage_loan                 double(10, 2) null comment '短期_抵押贷款',
    short_hypothecated_loan             double(10, 2) null comment '短期_质押贷款',
    short_guarantee_loan                double(10, 2) null comment '短期_保证贷款',
    short_credit_loan                   double(10, 2) null comment '短期_信用贷款',
    discount                            double(10, 2) null comment '贴现',
    mlong_mortgage_loan                 double(10, 2) null comment '中长期_抵押贷款',
    mlong_hypothecated_loan             double(10, 2) null comment '中长期_质押贷款',
    mlong_guarantee_loan                double(10, 2) null comment '中长期_保证贷款',
    mlong_credit_loan                   double(10, 2) null comment '中长期_信用贷款',
    current_investment_bond             double(10, 2) null comment '短期投资-债券',
    current_investment_stock            double(10, 2) null comment '短期投资-股票',
    long_debt_investment                double(10, 2) null comment '长期债权投资',
    long_equity_investment              double(10, 2) null comment '长期股权投资',
    fixed_assets                        double(10, 2) null comment '固定资产',
    intangible_assets                   double(10, 2) null comment '无形资产',
    other_assets                        double(10, 2) null comment '其他资产',
    total                               double(10, 2) null comment '总计',
    capital_adequacy_requirements       double(10, 2) null comment '资本充足率要求',
    capital_source_core_stock           double(10, 2) null comment '资本来源-核心资本-股本',
    capital_source_core_capital_reserve double(10, 2) null comment '资本来源-核心资本-资本公积',
    capital_source_core_surplus_reserve double(10, 2) null comment '资本来源-核心资本-盈余公积',
    capital_source_core_un_profit       double(10, 2) null comment '资本来源-核心资本-未分配利润',
    capital_source_loss_reserves        double(10, 2) null comment '资本来源-附属资本-贷款损失准备',
    capital_source_subord_bond          double(10, 2) null comment '资本来源-附属资本-次级债券',
    capital_source_total                double(10, 2) null comment '资本来源总额',
    capital_surplus_shortage            double(10, 2) null comment '资本盈余/不足'
)
    comment '初始数据表-资本充足率报告' charset = utf8mb4
                                        row_format = DYNAMIC;

create table par_initial_data_investbus_bond
(
    id                  int unsigned auto_increment comment '投资债券ID'
        primary key,
    current_stage_id    int(3)        not null comment '当前阶段ID',
    bond_kind           varchar(50)   null comment '债券种类(来自各个阶段)',
    bond_price          double(10, 1) null comment '价格(%)',
    bond_type           varchar(50)   null comment '债券投资类型(长期或短期)',
    real_value          int(10)       null comment '实际价值',
    nominal_value       int(10)       null comment '名义价值',
    rate_interest       double(10, 2) null comment '利率(%)',
    interest            int(10)       null comment '利息',
    account_profit_loss int(10)       null comment '账面损益'
)
    comment '初始数据表-投资业务营业数据表(债券)' charset = utf8mb4
                                                  row_format = DYNAMIC;

create table par_initial_data_investbus_stock
(
    id               int unsigned auto_increment comment '投资股票ID'
        primary key,
    current_stage_id int(3)        not null comment '当前阶段ID',
    stock_project    varchar(50)   null comment '项目',
    stock_index      double(10, 1) null comment '股票指数',
    stock_type       varchar(50)   null comment '股票类型(长期或短期)',
    stock_number     int(10)       null comment '股票数量(手)',
    stock_cost       int(10)       null comment '股票价值',
    stock_dividend   int(10)       null comment '股息',
    stock_account    int(10)       null comment '账面损益'
)
    comment '初始数据表-投资业务营业数据表(股票)' charset = utf8mb4
                                                  row_format = DYNAMIC;

create table par_initial_data_liquidity_cash
(
    id                          int unsigned auto_increment comment '初始内容表-流动性报表-现金ID'
        primary key,
    current_stage_id            int(3)  not null comment '当前阶段ID',
    interbank_deposit           int(10) null comment '同业存放款项',
    inter_bank                  int(10) null comment '同业拆入',
    short_deposit               int(10) null comment '短期存款',
    long_deposit                int(10) null comment '长期存款',
    short_savings_deposit       int(10) null comment '短期储蓄存款',
    long_savings_deposit        int(10) null comment '长期储蓄存款',
    total_short_debt            int(10) null comment '短期债务总计',
    cash_liquidity_requirements int(10) null comment '现金流动性要求',
    solvency                    int(10) null comment '清偿力',
    cash_solvency               int(10) null comment '现金清偿力'
)
    comment '初始数据表-流动性报表-现金' charset = utf8mb4
                                         row_format = DYNAMIC;

create table par_initial_data_liquidity_totality
(
    id                                 int unsigned auto_increment comment '初始内容表-流动性报表-总体ID'
        primary key,
    current_stage_id                   int(3)  not null comment '当前阶段ID',
    due_central_bank                   int(10) null comment '存放中央银行款项',
    deposit_interbank                  int(10) null comment '存放同业款项',
    loan_trade                         int(10) null comment '拆放同业',
    discount                           int(10) null comment '贴现',
    short_investment                   int(10) null comment '短期投资',
    long_debt_investment               int(10) null comment '长期债权投资',
    long_equity_investment             int(10) null comment '长期股权投资',
    total_realized_assets              int(10) null comment '易变现资产总计',
    interbank_deposit                  int(10) null comment '同业存放款项',
    inter_bank                         int(10) null comment '同业拆入',
    short_deposit                      int(10) null comment '短期存款',
    long_deposit                       int(10) null comment '长期存款',
    short_savings_deposit              int(10) null comment '短期储蓄存款',
    long_savings_deposit               int(10) null comment '长期储蓄存款',
    deposit_short_long_margin          int(10) null comment '存入短期及长期保证金',
    borrowings_central_bank            int(10) null comment '向中央银行借款',
    total_current_liabilities          int(10) null comment '流动性负债总计',
    overall_liquidity_requirements     int(10) null comment '总体流动性要求',
    solvency                           int(10) null comment '清偿力',
    totality_solvency                  int(10) null comment '总体清偿力',
    overall_liquidity_surplus_shortage int(10) null comment '总体流动性盈余/不足'
)
    comment '初始数据表-流动性报表-总体' charset = utf8mb4
                                         row_format = DYNAMIC;

create table par_initial_data_loan_deposit
(
    id                                int unsigned auto_increment comment '初始内容表-资产负债ID'
        primary key,
    current_stage_id                  int(3)  not null comment '初始阶段ID',
    cash                              int(10) null comment '现金',
    due_central_bank                  int(10) null comment '存放中央银行款项',
    deposit_interbank                 int(10) null comment '存放同业款项',
    loan_trade                        int(10) null comment '拆放同业',
    short_loan                        int(10) null comment '短期贷款',
    interest_receivable               int(10) null comment '应收利息',
    other_receivables                 int(10) null comment '其他应收款',
    discount                          int(10) null comment '贴现',
    short_investment                  int(10) null comment '短期投资',
    other_property                    int(10) null comment '其他流动资产',
    property_sum                      int(10) null comment '流动资产合计',
    mlong_loan                        int(10) null comment '中长期贷款',
    loss_reserves                     int(10) null comment '减：贷款损失准备',
    long_debt_investment              int(10) null comment '长期债权投资',
    long_equity_investment            int(10) null comment '长期股权投资',
    fixed_assets                      int(10) null comment '固定资产',
    construction_process              int(10) null comment '在建工程',
    long_total_asset                  int(10) null comment '长期资产合计',
    intangible_assets                 int(10) null comment '无形资产',
    other_assets                      int(10) null comment '其他资产',
    intangible_aother_assets          int(10) null comment '无形资产及其他资产合计',
    total_assets                      int(10) null comment '资产总计',
    short_deposit                     int(10) null comment '短期存款',
    short_savings_deposit             int(10) null comment '短期储蓄存款',
    borrowings_central_bank           int(10) null comment '向中央银行借款	',
    interbank_deposit                 int(10) null comment '同业存放款项',
    inter_bank                        int(10) null comment '同业拆入',
    deposit_short_margin              int(10) null comment '存入短期保证金',
    payment_interest                  int(10) null comment '应付利息',
    total_current_liability           int(10) null comment '流动负债合计',
    long_deposit                      int(10) null comment '长期存款',
    long_savings_deposit              int(10) null comment '长期储蓄存款',
    deposit_long_margin               int(10) null comment '存入长期保证金',
    payment_long_bond                 int(10) null comment '应付长期债券',
    payment_sub_bond                  int(10) null comment '应付次级债券',
    total_long_liabilities            int(10) null comment '长期负债rep_合计',
    total_liabilities                 int(10) null comment '负债合计',
    capital                           int(10) null comment '股本',
    capital_reserve                   int(10) null comment '资本公积',
    surplus_reserve                   int(10) null comment '盈余公积',
    undistributed_profit              int(10) null comment '未分配利润',
    total_shareholders_equity         int(10) null comment '股东权益合计',
    total_liabilities_equity_indebted int(10) null comment '负债和股东权益总计'
)
    comment '初始内容表-资产负债' charset = utf8mb4
                                  row_format = DYNAMIC;

create table par_initial_data_market_customer_account
(
    id                            int unsigned auto_increment comment '市场营销数据(客户账户)ID'
        primary key,
    current_stage_id              int(3)      not null comment '当前阶段ID',
    customer_market_business_data varchar(50) null comment '客户账户营业数据',
    account_type                  varchar(10) null comment '账户数据类型[A=账户数量(单位：个)，V=单位账户平均价值(单位：千元)]',
    less_age                      int(10)     null comment '<35岁',
    between_age                   int(10)     null comment '35~55岁',
    greater_age                   int(10)     null comment '>55岁',
    small_enterprise              int(10)     null comment '小型企业',
    medium_enterprise             int(10)     null comment '中型企业',
    major_enterprise              int(10)     null comment '大型企业',
    public_institution            int(10)     null comment '公共机构',
    account_sum                   int(10)     null comment '账户合计'
)
    comment '初始数据表-市场营销数据(客户账户)' charset = utf8mb4
                                                row_format = DYNAMIC;

create table par_initial_data_market_customer_bazaar
(
    id                            int unsigned auto_increment comment '市场营销数据(客户市场)ID'
        primary key,
    current_stage_id              int(3)      not null comment '当前阶段ID',
    customer_market_business_data varchar(50) null comment '客户市场营业数据',
    less_age                      int(10)     null comment '<35岁',
    between_age                   int(10)     null comment '35~55岁',
    greater_age                   int(10)     null comment '>55岁',
    small_enterprise              int(10)     null comment '小型企业',
    medium_enterprise             int(10)     null comment '中型企业',
    major_enterprise              int(10)     null comment '大型企业',
    public_institution            int(10)     null comment '公共机构',
    amount_sum                    int(10)     null comment '金额合计'
)
    comment '初始数据表-市场营销数据(客户市场)单位：百万元' charset = utf8mb4
                                                           row_format = DYNAMIC;

create table par_initial_data_people_logistics
(
    id                      int unsigned auto_increment comment '人事后勤信息录入ID'
        primary key,
    current_stage_id        int(3)        not null comment '当前阶段ID',
    business_type           varchar(50)   null comment '业务类型',
    start_employee_quantity int(10)       null comment '期初员工数(人)',
    hare_fire               int(10)       null comment '本阶段雇佣/解雇(人)',
    drain                   int(10)       null comment '本阶段流失人数(人)',
    end_employee_quantity   int(10)       null comment '期末员工数(人)',
    loss_rate               double(10, 1) null comment '下阶段人员流失率(%)',
    lost_personnel          int(10)       null comment '下阶段流失人员数(人)',
    effective_time          double(10, 1) null comment '有效营业时间(千小时)',
    training_time           double(10, 1) null comment '人员调配(千小时)-培训时间',
    market_development      double(10, 1) null comment '人员调配(千小时)-市场拓展',
    customer_maintenance    double(10, 1) null comment '人员调配(千小时)-客户维护',
    available_time          double(10, 1) null comment '可用营业时间(千小时)',
    need_time               double(10, 1) null comment '需要营业时间(千小时)',
    work_overtime           double(10, 1) null comment '加班(千小时)',
    temporary_worker        double(10, 1) null comment '临时工(千小时)',
    training_level          varchar(50)   null comment '培训水平',
    job_satisfaction        varchar(50)   null comment '工作满意度'
)
    comment '人事后勤信息录入' charset = utf8mb4
                               row_format = DYNAMIC;

create table par_initial_data_personnel_logistics
(
    id                  int unsigned auto_increment comment '人事后勤数据表ID'
        primary key,
    current_stage_id    int(3)      not null comment '当前阶段ID',
    project             varchar(50) null comment '项目',
    loan_transaction    varchar(50) null comment '贷款业务',
    deposit_transaction varchar(50) null comment '存款业务',
    invest_transaction  varchar(50) null comment '投资业务',
    middle_transaction  varchar(50) null comment '中间业务',
    other               varchar(50) null comment '其他'
)
    comment '初始数据表-人事后勤数据表' charset = utf8mb4
                                        row_format = DYNAMIC;

create table par_initial_data_profit
(
    id                                          int unsigned auto_increment comment '初始内容表-利润ID'
        primary key,
    current_stage_id                            int(3)  not null comment '初始阶段ID',
    interest_income                             int(10) null comment '利息收入',
    financial_organization_income               int(10) null comment '金融机构往来收入',
    service_charge_income                       int(10) null comment '手续费收入',
    total_revenues                              int(10) null comment '营业收入合计',
    interest_expense                            int(10) null comment '利息支出',
    financial_organization_expense              int(10) null comment '金融机构往来支出',
    service_charge_expense                      int(10) null comment '手续费支出',
    expense_revenues                            int(10) null comment '营业支出合计',
    people_costs                                int(10) null comment '人员开支',
    addition_people_costs                       int(10) null comment '附加人员成本',
    other_people_costs                          int(10) null comment '其他人员开支',
    fixed_assets_depreciation                   int(10) null comment '固定资产折旧',
    other_business_costs                        int(10) null comment '其它营业费用',
    total_operating_expenses                    int(10) null comment '营业费用合计',
    income_investment                           int(10) null comment '投资收益',
    operating_profit                            int(10) null comment '营业利润',
    subtract_bus_tax_surch                      int(10) null comment '减：营业税金及附加',
    add_nonbus_income                           int(10) null comment '加：营业外收入',
    subtract_nonbus_expend                      int(10) null comment '减：营业外支出',
    deduct_asset_impairment_before_total_profit int(10) null comment '扣除资产减值前利润总额',
    subtract_asset_reserve_expense              int(10) null comment '减：资产准备支出',
    deduct_asset_impairment_after_total_profit  int(10) null comment '扣除资产减值后利润总额',
    subtract_income_tax                         int(10) null comment '减：所得税',
    retained_profits                            int(10) null comment '净利润'
)
    comment '初始数据表-利润' charset = utf8mb4
                              row_format = DYNAMIC;

create table par_market_share
(
    id                 int unsigned auto_increment comment '市场份额ID'
        primary key,
    current_project_id int(10)       not null comment '当前项目ID',
    current_team_id    int(10)       not null comment '当前团队ID',
    current_stage_id   int(3)        not null comment '当前阶段ID',
    business           varchar(50)   null comment '业务',
    market_share       double(10, 2) null comment '市场份额'
)
    comment '市场份额' charset = utf8mb4
                       row_format = DYNAMIC;

create table par_market_supply_demand_forecast
(
    id               int unsigned auto_increment comment '市场供求预测ID'
        primary key,
    current_stage_id int(3)      not null comment '当前阶段ID',
    project_name     varchar(50) not null comment '项目名',
    market_prospect  varchar(50) not null comment '预期市场供求(%)'
)
    comment '市场供求预测表' charset = utf8mb4
                             row_format = DYNAMIC;

create table par_marketshares_test
(
    id               int unsigned auto_increment comment '市场份额ID'
        primary key,
    current_team_id  int(2)        not null comment '当前团队ID',
    current_stage_id int(2)        not null comment '当前阶段ID',
    business_type    varchar(50)   null comment '业务类型',
    market_shares    double(10, 2) null comment '市场份额'
)
    comment '各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算)' charset = utf8mb4
                                                                                    row_format = DYNAMIC;

create table par_personnel_cost
(
    id                  int unsigned auto_increment comment '人员成本表ID'
        primary key,
    project             varchar(50) null comment '项目',
    loan_business       int(10)     null comment '贷款业务',
    deposit_business    int(10)     null comment '存款业务',
    investment_business int(10)     null comment '投资业务',
    middle_business     int(10)     null comment '中间业务',
    other_business      int(10)     null comment '其他',
    sys_project_id      mediumtext  null comment '项目id'
)
    comment '人员成本表' charset = utf8mb4
                         row_format = DYNAMIC;

create table par_project_rate
(
    id               int unsigned auto_increment comment '项目利率ID'
        primary key,
    current_stage_id int(3)        not null comment '当前阶段ID',
    project_name     varchar(50)   not null comment '项目名',
    interest_rate    double(10, 2) not null comment '利率 利率/股指'
)
    comment '项目利率表' charset = utf8mb4
                         row_format = DYNAMIC;

create table par_property_liability_rate
(
    id                    int unsigned auto_increment comment '资产负债利率ID'
        primary key,
    current_stage_id      int(3)        not null comment '当前阶段ID',
    interest_rate_type    varchar(50)   not null comment '类型(资产，负债)',
    interest_rate_min     double(10, 2) not null comment '最低',
    interest_rate_average double(10, 2) not null comment '平均',
    interest_rate_max     double(10, 2) not null comment '最高'
)
    comment '资产负债利率表' charset = utf8mb4
                             row_format = DYNAMIC;

create table par_stock_calculation_index
(
    id          int unsigned auto_increment comment '股价计划指标ID'
        primary key,
    stock_index varchar(50)   null comment '指标',
    weight      int(10)       null comment '权重',
    min_score   double(10, 4) null comment '最小值',
    max_score   double(10, 4) null comment '最大值',
    score       double(10, 2) null comment '分值'
)
    comment '股价计划指标' charset = utf8mb4
                           row_format = DYNAMIC;

create table par_total_market
(
    id                  int unsigned auto_increment comment '市场总额ID'
        primary key,
    current_stage_id    int(3)      not null comment '当前阶段ID',
    classification_type varchar(50) null comment '分类类型',
    total_market_value  int(10)     null comment '市场总额值'
)
    comment '市场总额' charset = utf8mb4
                       row_format = DYNAMIC;

create table par_wastage_rate
(
    id               int unsigned auto_increment comment '人员流失率ID'
        primary key,
    current_stage_id int(3)        not null comment '当前阶段ID',
    business_type    varchar(50)   null comment '业务类型',
    wastage_rate     double(10, 1) null comment '人员流失率'
)
    comment '人员流失率' charset = utf8mb4
                         row_format = DYNAMIC;

create table poi_templet
(
    id                          int auto_increment comment '主键ID'
        primary key,
    template_id                 varchar(255)                       not null comment '模板ID',
    template_path               varchar(255)                       null comment '模板路径',
    template_name               varchar(255)                       null comment '模板名称',
    comments_id                 varchar(255)                       null comment '批注Id',
    comments_author             varchar(255)                       null comment '批注作者',
    comments_date               datetime default CURRENT_TIMESTAMP null comment '批注日期',
    comments_content            varchar(255)                       null comment '批注内容',
    comments_text               varchar(255)                       null comment '批注引用正文',
    comments_regular_expression varchar(255)                       null comment '批注正则表达式',
    comments_page               varchar(255)                       null comment '批注页数'
)
    comment '模板表' charset = utf8mb4
                     row_format = DYNAMIC;

create table qrtz_calendars
(
    SCHED_NAME    varchar(120) not null,
    CALENDAR_NAME varchar(190) not null,
    CALENDAR      blob         not null,
    primary key (SCHED_NAME, CALENDAR_NAME)
)
    row_format = DYNAMIC;

create table qrtz_fired_triggers
(
    SCHED_NAME        varchar(120) not null,
    ENTRY_ID          varchar(95)  not null,
    TRIGGER_NAME      varchar(190) not null,
    TRIGGER_GROUP     varchar(190) not null,
    INSTANCE_NAME     varchar(190) not null,
    FIRED_TIME        bigint       not null,
    SCHED_TIME        bigint       not null,
    PRIORITY          int          not null,
    STATE             varchar(16)  not null,
    JOB_NAME          varchar(190) null,
    JOB_GROUP         varchar(190) null,
    IS_NONCONCURRENT  varchar(1)   null,
    REQUESTS_RECOVERY varchar(1)   null,
    primary key (SCHED_NAME, ENTRY_ID)
)
    row_format = DYNAMIC;

create index IDX_QRTZ_FT_INST_JOB_REQ_RCVRY
    on qrtz_fired_triggers (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);

create index IDX_QRTZ_FT_JG
    on qrtz_fired_triggers (SCHED_NAME, JOB_GROUP);

create index IDX_QRTZ_FT_J_G
    on qrtz_fired_triggers (SCHED_NAME, JOB_NAME, JOB_GROUP);

create index IDX_QRTZ_FT_TG
    on qrtz_fired_triggers (SCHED_NAME, TRIGGER_GROUP);

create index IDX_QRTZ_FT_TRIG_INST_NAME
    on qrtz_fired_triggers (SCHED_NAME, INSTANCE_NAME);

create index IDX_QRTZ_FT_T_G
    on qrtz_fired_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

create table qrtz_job_details
(
    SCHED_NAME        varchar(120) not null,
    JOB_NAME          varchar(190) not null,
    JOB_GROUP         varchar(190) not null,
    DESCRIPTION       varchar(250) null,
    JOB_CLASS_NAME    varchar(250) not null,
    IS_DURABLE        varchar(1)   not null,
    IS_NONCONCURRENT  varchar(1)   not null,
    IS_UPDATE_DATA    varchar(1)   not null,
    REQUESTS_RECOVERY varchar(1)   not null,
    JOB_DATA          blob         null,
    primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
    row_format = DYNAMIC;

create index IDX_QRTZ_J_GRP
    on qrtz_job_details (SCHED_NAME, JOB_GROUP);

create index IDX_QRTZ_J_REQ_RECOVERY
    on qrtz_job_details (SCHED_NAME, REQUESTS_RECOVERY);

create table qrtz_job_details_copy1
(
    SCHED_NAME        varchar(120) not null,
    JOB_NAME          varchar(190) not null,
    JOB_GROUP         varchar(190) not null,
    DESCRIPTION       varchar(250) null,
    JOB_CLASS_NAME    varchar(250) not null,
    IS_DURABLE        varchar(1)   not null,
    IS_NONCONCURRENT  varchar(1)   not null,
    IS_UPDATE_DATA    varchar(1)   not null,
    REQUESTS_RECOVERY varchar(1)   not null,
    JOB_DATA          blob         null,
    primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
    row_format = DYNAMIC;

create index IDX_QRTZ_J_GRP
    on qrtz_job_details_copy1 (SCHED_NAME, JOB_GROUP);

create index IDX_QRTZ_J_REQ_RECOVERY
    on qrtz_job_details_copy1 (SCHED_NAME, REQUESTS_RECOVERY);

create table qrtz_locks
(
    SCHED_NAME varchar(120) not null,
    LOCK_NAME  varchar(40)  not null,
    primary key (SCHED_NAME, LOCK_NAME)
)
    row_format = DYNAMIC;

create table qrtz_paused_trigger_grps
(
    SCHED_NAME    varchar(120) not null,
    TRIGGER_GROUP varchar(190) not null,
    primary key (SCHED_NAME, TRIGGER_GROUP)
)
    row_format = DYNAMIC;

create table qrtz_scheduler_state
(
    SCHED_NAME        varchar(120) not null,
    INSTANCE_NAME     varchar(190) not null,
    LAST_CHECKIN_TIME bigint(13)   not null,
    CHECKIN_INTERVAL  bigint(13)   not null,
    primary key (SCHED_NAME, INSTANCE_NAME)
)
    row_format = DYNAMIC;

create table qrtz_triggers
(
    SCHED_NAME     varchar(120) not null,
    TRIGGER_NAME   varchar(190) not null,
    TRIGGER_GROUP  varchar(190) not null,
    JOB_NAME       varchar(190) not null,
    JOB_GROUP      varchar(190) not null,
    DESCRIPTION    varchar(250) null,
    NEXT_FIRE_TIME bigint(13)   null,
    PREV_FIRE_TIME bigint(13)   null,
    PRIORITY       int          null,
    TRIGGER_STATE  varchar(16)  not null,
    TRIGGER_TYPE   varchar(8)   not null,
    START_TIME     bigint(13)   not null,
    END_TIME       bigint(13)   null,
    CALENDAR_NAME  varchar(190) null,
    MISFIRE_INSTR  smallint(2)  null,
    JOB_DATA       blob         null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_triggers_ibfk_1
        foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP) references qrtz_job_details (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
    row_format = DYNAMIC;

create table qrtz_blob_triggers
(
    SCHED_NAME    varchar(120) not null,
    TRIGGER_NAME  varchar(190) not null,
    TRIGGER_GROUP varchar(190) not null,
    BLOB_DATA     blob         null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_blob_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    row_format = DYNAMIC;

create index SCHED_NAME
    on qrtz_blob_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

create table qrtz_blob_triggers_copy1
(
    SCHED_NAME    varchar(120) not null,
    TRIGGER_NAME  varchar(190) not null,
    TRIGGER_GROUP varchar(190) not null,
    BLOB_DATA     blob         null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_blob_triggers_copy1_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    row_format = DYNAMIC;

create index SCHED_NAME
    on qrtz_blob_triggers_copy1 (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

create table qrtz_cron_triggers
(
    SCHED_NAME      varchar(120) not null,
    TRIGGER_NAME    varchar(190) not null,
    TRIGGER_GROUP   varchar(190) not null,
    CRON_EXPRESSION varchar(120) not null,
    TIME_ZONE_ID    varchar(80)  null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_cron_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    row_format = DYNAMIC;

create table qrtz_simple_triggers
(
    SCHED_NAME      varchar(120) not null,
    TRIGGER_NAME    varchar(190) not null,
    TRIGGER_GROUP   varchar(190) not null,
    REPEAT_COUNT    bigint(7)    not null,
    REPEAT_INTERVAL bigint(12)   not null,
    TIMES_TRIGGERED bigint(10)   not null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_simple_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    row_format = DYNAMIC;

create table qrtz_simprop_triggers
(
    SCHED_NAME    varchar(120)   not null,
    TRIGGER_NAME  varchar(190)   not null,
    TRIGGER_GROUP varchar(190)   not null,
    STR_PROP_1    varchar(512)   null,
    STR_PROP_2    varchar(512)   null,
    STR_PROP_3    varchar(512)   null,
    INT_PROP_1    int            null,
    INT_PROP_2    int            null,
    LONG_PROP_1   bigint         null,
    LONG_PROP_2   bigint         null,
    DEC_PROP_1    decimal(13, 4) null,
    DEC_PROP_2    decimal(13, 4) null,
    BOOL_PROP_1   varchar(1)     null,
    BOOL_PROP_2   varchar(1)     null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint qrtz_simprop_triggers_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
    row_format = DYNAMIC;

create index IDX_QRTZ_T_C
    on qrtz_triggers (SCHED_NAME, CALENDAR_NAME);

create index IDX_QRTZ_T_G
    on qrtz_triggers (SCHED_NAME, TRIGGER_GROUP);

create index IDX_QRTZ_T_J
    on qrtz_triggers (SCHED_NAME, JOB_NAME, JOB_GROUP);

create index IDX_QRTZ_T_JG
    on qrtz_triggers (SCHED_NAME, JOB_GROUP);

create index IDX_QRTZ_T_NEXT_FIRE_TIME
    on qrtz_triggers (SCHED_NAME, NEXT_FIRE_TIME);

create index IDX_QRTZ_T_NFT_MISFIRE
    on qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME);

create index IDX_QRTZ_T_NFT_ST
    on qrtz_triggers (SCHED_NAME, TRIGGER_STATE, NEXT_FIRE_TIME);

create index IDX_QRTZ_T_NFT_ST_MISFIRE
    on qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_STATE);

create index IDX_QRTZ_T_NFT_ST_MISFIRE_GRP
    on qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_GROUP, TRIGGER_STATE);

create index IDX_QRTZ_T_N_G_STATE
    on qrtz_triggers (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);

create index IDX_QRTZ_T_N_STATE
    on qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);

create index IDX_QRTZ_T_STATE
    on qrtz_triggers (SCHED_NAME, TRIGGER_STATE);

create table rep_agency_bond
(
    id                 int unsigned auto_increment comment '代理债券发行业务数据表ID'
        primary key,
    current_project_id int(10)       not null comment '当前项目ID',
    current_team_id    int(10)       not null comment '当前团队ID',
    current_stage_id   int(3)        not null comment '当前阶段ID',
    bond_type          varchar(50)   null comment '债券类型',
    coupon_rate        double(10, 2) null comment '票面利率(%)',
    deadline           int(10)       null comment '期限',
    credit_rating      varchar(50)   null comment '信用评级',
    issue_amount       int(10)       null comment '发行金额',
    underwriting_bank  varchar(50)   null comment '承销发行银行',
    underwriting_mount int(10)       null comment '承销金额（百万元）',
    subscription_limit double(10, 2) null comment '认购额度（%）',
    underwriting_price double(10, 2) null comment '承销价格（%）',
    discount           int(10)       null comment '折扣（%）'
)
    comment '代理债券发行业务数据表' charset = utf8mb4
                                     row_format = DYNAMIC;

create table rep_balance_sheet
(
    id                                int unsigned auto_increment comment '资产负债表ID'
        primary key,
    current_project_id                int(10) not null comment '当前项目ID',
    current_team_id                   int(10) not null comment '当前团队ID',
    current_stage_id                  int(10) not null comment '当前阶段ID',
    cash                              int(10) null comment '现金',
    due_central_bank                  int(10) null comment '存放中央银行款项',
    deposit_interbank                 int(10) null comment '存放同业款项',
    loan_trade                        int(10) null comment '拆放同业',
    short_loan                        int(10) null comment '短期贷款',
    interest_receivable               int(10) null comment '应收利息',
    other_receivables                 int(10) null comment '其他应收款',
    discount                          int(10) null comment '贴现',
    short_investment                  int(10) null comment '短期投资',
    other_property                    int(10) null comment '其他流动资产',
    property_sum                      int(10) null comment '流动资产合计',
    mlong_loan                        int(10) null comment '中长期贷款',
    loss_reserves                     int(10) null comment '减：贷款损失准备',
    long_debt_investment              int(10) null comment '长期债权投资',
    long_equity_investment            int(10) null comment '长期股权投资',
    fixed_assets                      int(10) null comment '固定资产',
    construction_process              int(10) null comment '在建工程',
    long_total_asset                  int(10) null comment '长期资产合计',
    intangible_assets                 int(10) null comment '无形资产',
    other_assets                      int(10) null comment '其他资产',
    intangible_aother_assets          int(10) null comment '无形资产及其他资产合计',
    total_assets                      int(10) null comment '资产总计',
    short_deposit                     int(10) null comment '短期存款',
    short_savings_deposit             int(10) null comment '短期储蓄存款',
    borrowings_central_bank           int(10) null comment '向中央银行借款	',
    interbank_deposit                 int(10) null comment '同业存放款项',
    inter_bank                        int(10) null comment '同业拆入',
    deposit_short_margin              int(10) null comment '存入短期保证金',
    payment_interest                  int(10) null comment '应付利息',
    total_current_liability           int(10) null comment '流动负债合计',
    long_deposit                      int(10) null comment '长期存款',
    long_savings_deposit              int(10) null comment '长期储蓄存款',
    deposit_long_margin               int(10) null comment '存入长期保证金',
    payment_long_bond                 int(10) null comment '应付长期债券',
    payment_sub_bond                  int(10) null comment '应付次级债券',
    total_long_liabilities            int(10) null comment '长期负债合计',
    total_liabilities                 int(10) null comment '负债合计',
    capital                           int(10) null comment '股本',
    capital_reserve                   int(10) null comment '资本公积',
    surplus_reserve                   int(10) null comment '盈余公积',
    undistributed_profit              int(10) null comment '未分配利润',
    total_shareholders_equity         int(10) null comment '股东权益合计',
    total_liabilities_equity_indebted int(10) null comment '负债和股东权益总计'
)
    comment '资产负债表' charset = utf8mb4
                         row_format = DYNAMIC;

create table rep_capital_adequacy
(
    id                                  int unsigned auto_increment comment '资本充足率报告ID'
        primary key,
    current_project_id                  int(10)     not null comment '当前项目ID',
    current_team_id                     int(10)     not null comment '当前团队ID',
    current_stage_id                    int(3)      not null comment '当前阶段ID',
    property_risk                       varchar(50) null comment '资产风险(账面价值 风险加权系数% 风险加权资产)',
    cash                                int(10)     null comment '现金',
    due_central_bank                    int(10)     null comment '存放中央银行款项',
    deposit_interbank                   int(10)     null comment '存放同业款项',
    loan_trade                          int(10)     null comment '拆放同业',
    short_mortgage_loan                 int(10)     null comment '短期_抵押贷款',
    short_hypothecated_loan             int(10)     null comment '短期_质押贷款',
    short_guarantee_loan                int(10)     null comment '短期_保证贷款',
    short_credit_loan                   int(10)     null comment '短期_信用贷款',
    discount                            int(10)     null comment '贴现',
    mlong_mortgage_loan                 int(10)     null comment '中长期_抵押贷款',
    mlong_hypothecated_loan             int(10)     null comment '中长期_质押贷款',
    mlong_guarantee_loan                int(10)     null comment '中长期_保证贷款',
    mlong_credit_loan                   int(10)     null comment '中长期_信用贷款',
    current_investment_bond             int(10)     null comment '短期投资-债券',
    current_investment_stock            int(10)     null comment '短期投资-股票',
    long_debt_investment                int(10)     null comment '长期债权投资',
    long_equity_investment              int(10)     null comment '长期股权投资',
    fixed_assets                        int(10)     null comment '固定资产',
    intangible_assets                   int(10)     null comment '无形资产',
    other_assets                        int(10)     null comment '其他资产',
    total                               int(10)     null comment '总计',
    capital_adequacy_requirements       int(10)     null comment '资本充足率要求',
    capital_source_core_stock           int(10)     null comment '资本来源-核心资本-股本',
    capital_source_core_capital_reserve int(10)     null comment '资本来源-核心资本-资本公积',
    capital_source_core_surplus_reserve int(10)     null comment '资本来源-核心资本-盈余公积',
    capital_source_core_un_profit       int(10)     null comment '资本来源-核心资本-未分配利润',
    capital_source_loss_reserves        int(10)     null comment '资本来源-附属资本-贷款损失准备',
    capital_source_subord_bond          int(10)     null comment '资本来源-附属资本-次级债券',
    capital_source_total                int(10)     null comment '资本来源总额',
    capital_surplus_shortage            int(10)     null comment '资本盈余/不足'
)
    comment '资本充足率报告' charset = utf8mb4
                             row_format = DYNAMIC;

create table rep_comprehensive_ranking_score
(
    id                        int unsigned auto_increment comment '综合排名分数表ID'
        primary key,
    current_project_id        int(10)       not null comment '当前项目ID',
    current_team_id           int(10)       not null comment '当前团队ID',
    current_stage_id          int(3)        not null comment '当前阶段ID',
    capital_adequacy_ratio    double(10, 2) null comment '资本充足率',
    asset_scale               double(10, 2) null comment '资产规模',
    non_performing_loan_ratio double(10, 2) null comment '不良贷款率',
    npl_provision_coverage    double(10, 2) null comment '不良贷款拨备覆盖率',
    liquidity_ratio           double(10, 2) null comment '流动性比率',
    deposit_loan_ratio        double(10, 2) null comment '存贷比',
    net_profit_loss           double(10, 2) null comment '净利润（净亏损）',
    return_on_assets          double(10, 2) null comment '资产收益率',
    return_on_net_assets      double(10, 2) null comment '净资产收益率',
    economic_value_added      double(10, 2) null comment 'EVA',
    cost_income_ratio         double(10, 2) null comment '成本收入比',
    bridge_loan               double(10, 2) null comment '搭桥贷款',
    plan_quality              double(10, 2) null comment '计划质量',
    share_out_bonus           double(10, 2) null comment '分红',
    total_score               double(10, 2) null comment '总分数'
)
    comment '综合排名分数表' charset = utf8mb4
                             row_format = DYNAMIC;

create table rep_investbus_operat_data_bond
(
    id                  int unsigned auto_increment comment '投资债券ID'
        primary key,
    current_project_id  int(10)       not null comment '当前项目ID',
    current_team_id     int(10)       not null comment '当前团队ID',
    current_stage_id    int(3)        not null comment '当前阶段ID',
    bond_kind           varchar(50)   null comment '债券种类(来自各个阶段)',
    bond_price          double(10, 1) null comment '价格(%)',
    bond_type           varchar(50)   null comment '债券投资类型(长期或短期)',
    real_value          int(10)       null comment '实际价值',
    nominal_value       int(10)       null comment '名义价值',
    rate_interest       double(10, 2) null comment '利率(%)',
    interest            int(10)       null comment '利息',
    account_profit_loss int(10)       null comment '账面损益'
)
    comment '投资业务营业数据表(债券)' charset = utf8mb4
                                       row_format = DYNAMIC;

create table rep_investbus_operat_data_stock
(
    id                 int unsigned auto_increment comment '投资股票ID'
        primary key,
    current_project_id int(10)       not null comment '当前项目ID',
    current_team_id    int(10)       not null comment '当前团队ID',
    current_stage_id   int(3)        not null comment '当前阶段ID',
    stock_project      varchar(50)   null comment '项目',
    stock_index        double(10, 1) null comment '股票指数',
    stock_type         varchar(50)   null comment '股票类型(长期或短期)',
    stock_number       int(10)       null comment '股票数量(手)',
    stock_cost         int(10)       null comment '股票价值',
    stock_dividend     int(10)       null comment '股息',
    stock_account      int(10)       null comment '账面损益'
)
    comment '投资业务营业数据表(股票)' charset = utf8mb4
                                       row_format = DYNAMIC;

create table rep_liquidity_cash
(
    id                          int unsigned auto_increment comment '流动性报表-现金ID'
        primary key,
    current_project_id          int(10) not null comment '当前项目ID',
    current_team_id             int(10) not null comment '当前团队ID',
    current_stage_id            int(3)  not null comment '当前阶段ID',
    interbank_deposit           int(10) null comment '同业存放款项',
    inter_bank                  int(10) null comment '同业拆入',
    short_deposit               int(10) null comment '短期存款',
    long_deposit                int(10) null comment '长期存款',
    short_savings_deposit       int(10) null comment '短期储蓄存款',
    long_savings_deposit        int(10) null comment '长期储蓄存款',
    total_short_debt            int(10) null comment '短期债务总计',
    cash_liquidity_requirements int(10) null comment '现金流动性要求',
    solvency                    int(10) null comment '清偿力',
    cash_solvency               int(10) null comment '现金清偿力'
)
    comment '流动性报表-现金' charset = utf8mb4
                              row_format = DYNAMIC;

create table rep_liquidity_totality
(
    id                                 int unsigned auto_increment comment '流动性报表-总体ID'
        primary key,
    current_project_id                 int(10) not null comment '当前项目ID',
    current_team_id                    int(10) not null comment '当前团队ID',
    current_stage_id                   int(3)  not null comment '当前阶段ID',
    due_central_bank                   int(10) null comment '存放中央银行款项',
    deposit_interbank                  int(10) null comment '存放同业款项',
    loan_trade                         int(10) null comment '拆放同业',
    discount                           int(10) null comment '贴现',
    short_investment                   int(10) null comment '短期投资',
    long_debt_investment               int(10) null comment '长期债权投资',
    long_equity_investment             int(10) null comment '长期股权投资',
    total_realized_assets              int(10) null comment '易变现资产总计',
    interbank_deposit                  int(10) null comment '同业存放款项',
    inter_bank                         int(10) null comment '同业拆入',
    short_deposit                      int(10) null comment '短期存款',
    long_deposit                       int(10) null comment '长期存款',
    short_savings_deposit              int(10) null comment '短期储蓄存款',
    long_savings_deposit               int(10) null comment '长期储蓄存款',
    deposit_short_long_margin          int(10) null comment '存入短期及长期保证金',
    borrowings_central_bank            int(10) null comment '向中央银行借款',
    total_current_liabilities          int(10) null comment '流动性负债总计',
    overall_liquidity_requirements     int(10) null comment '总体流动性要求',
    solvency                           int(10) null comment '清偿力',
    totality_solvency                  int(10) null comment '总体清偿力',
    overall_liquidity_surplus_shortage int(10) null comment '总体流动性盈余/不足'
)
    comment '流动性报表-总体' charset = utf8mb4
                              row_format = DYNAMIC;

create table rep_market_data_customer_account
(
    id                            int unsigned auto_increment comment '市场营销数据(客户账户)ID'
        primary key,
    current_project_id            int(10)     not null comment '当前项目ID',
    current_team_id               int(10)     not null comment '当前团队ID',
    current_stage_id              int(3)      not null comment '当前阶段ID',
    customer_market_business_data varchar(50) null comment '客户账户营业数据',
    account_type                  varchar(10) null comment '账户数据类型[A=账户数量(单位：个)，V=单位账户平均价值(单位：千元)]',
    less_age                      int(10)     null comment '<35岁',
    between_age                   int(10)     null comment '35~55岁',
    greater_age                   int(10)     null comment '>55岁',
    small_enterprise              int(10)     null comment '小型企业',
    medium_enterprise             int(10)     null comment '中型企业',
    major_enterprise              int(10)     null comment '大型企业',
    public_institution            int(10)     null comment '公共机构',
    account_sum                   int(10)     null comment '账户合计'
)
    comment '市场营销数据(客户账户)' charset = utf8mb4
                                     row_format = DYNAMIC;

create table rep_market_data_customer_bazaar
(
    id                            int unsigned auto_increment comment '市场营销数据(客户市场)ID'
        primary key,
    current_project_id            int(10)     not null comment '当前项目ID',
    current_team_id               int(10)     not null comment '当前团队ID',
    current_stage_id              int(3)      not null comment '当前阶段ID',
    customer_market_business_data varchar(50) null comment '客户市场营业数据',
    less_age                      int(10)     null comment '<35岁',
    between_age                   int(10)     null comment '35~55岁',
    greater_age                   int(10)     null comment '>55岁',
    small_enterprise              int(10)     null comment '小型企业',
    medium_enterprise             int(10)     null comment '中型企业',
    major_enterprise              int(10)     null comment '大型企业',
    public_institution            int(10)     null comment '公共机构',
    amount_sum                    int(10)     null comment '金额合计'
)
    comment '市场营销数据(客户市场)单位：百万元' charset = utf8mb4
                                                row_format = DYNAMIC;

create table rep_peer_report_purchase
(
    id          int unsigned auto_increment comment '主键id'
        primary key,
    project_id  int(10)                            null comment '项目ID',
    team_id     int(10)                            null,
    stage_id    int(3)                             null comment '阶段ID',
    user_id     int(10)                            null comment '购买人ID',
    project     varchar(50)                        null comment '项目',
    team_name   varchar(255)                       null,
    stage       varchar(255)                       null comment '阶段',
    user_name   varchar(50)                        null comment '购买人姓名',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    create_time datetime default CURRENT_TIMESTAMP null comment '购买时间'
)
    comment '同业报告购买记录表' charset = utf8mb4
                                 row_format = DYNAMIC;

create table rep_people_logistics
(
    id                      int unsigned auto_increment comment '人事后勤信息录入ID'
        primary key,
    current_project_id      int(10)       not null comment '当前项目ID',
    current_team_id         int(10)       not null comment '当前团队ID',
    current_stage_id        int(3)        not null comment '当前阶段ID',
    business_type           varchar(50)   null comment '业务类型',
    start_employee_quantity int(10)       null comment '期初员工数(人)',
    hare_fire               int(10)       null comment '本阶段雇佣/解雇(人)',
    drain                   int(10)       null comment '本阶段流失人数(人)',
    end_employee_quantity   int(10)       null comment '期末员工数(人)',
    loss_rate               double(10, 1) null comment '下阶段人员流失率(%)',
    lost_personnel          int(10)       null comment '下阶段流失人员数(人)',
    effective_time          double(10, 1) null comment '有效营业时间(千小时)',
    training_time           double(10, 1) null comment '人员调配(千小时)-培训时间',
    market_development      double(10, 1) null comment '人员调配(千小时)-市场拓展',
    customer_maintenance    double(10, 1) null comment '人员调配(千小时)-客户维护',
    available_time          double(10, 1) null comment '可用营业时间(千小时)',
    need_time               double(10, 1) null comment '需要营业时间(千小时)',
    work_overtime           double(10, 1) null comment '加班(千小时)',
    temporary_worker        double(10, 1) null comment '临时工(千小时)',
    training_level          varchar(50)   null comment '培训水平',
    job_satisfaction        varchar(50)   null comment '工作满意度'
)
    comment '人事后勤信息录入' charset = utf8mb4
                               row_format = DYNAMIC;

create table rep_personnel_logistics_data_automation
(
    id                    int unsigned auto_increment comment '自动化投资'
        primary key,
    current_project_id    int(10)       not null comment '当前项目ID',
    current_team_id       int(10)       not null comment '当前团队ID',
    current_stage_id      int(3)        not null comment '当前阶段ID',
    project               varchar(50)   null comment '项目',
    present_stage         int(10)       null comment '本阶段',
    cumulative_investment int(10)       null comment '累计投资',
    exponent              double(10, 1) null comment '指数'
)
    comment '人事后勤数据表(自动化投资)' charset = utf8mb4
                                         row_format = DYNAMIC;

create table rep_precomputing_num
(
    id                 int unsigned auto_increment comment '预计算次数ID'
        primary key,
    current_project_id int(10) not null comment '当前项目ID',
    current_team_id    int(10) not null comment '当前团队ID',
    current_stage_id   int(3)  not null comment '当前阶段ID',
    pre_num            int(10) null comment '预计算次数'
)
    comment '预计算次数表' charset = utf8mb4
                           row_format = DYNAMIC;

create table rep_profit
(
    id                                          int unsigned auto_increment comment '利润表ID'
        primary key,
    current_project_id                          int(10) not null comment '当前项目ID',
    current_team_id                             int(10) not null comment '当前团队ID',
    current_stage_id                            int(3)  not null comment '当前阶段ID',
    interest_income                             int(10) null comment '利息收入',
    financial_organization_income               int(10) null comment '金融机构往来收入',
    service_charge_income                       int(10) null comment '手续费收入',
    total_revenues                              int(10) null comment '营业收入合计',
    interest_expense                            int(10) null comment '利息支出',
    financial_organization_expense              int(10) null comment '金融机构往来支出',
    service_charge_expense                      int(10) null comment '手续费支出',
    expense_revenues                            int(10) null comment '营业支出合计',
    people_costs                                int(10) null comment '人员开支',
    Addition_people_costs                       int(10) null comment '附加人员成本',
    other_people_costs                          int(10) null comment '其他人员开支',
    fixed_assets_depreciation                   int(10) null comment '固定资产折旧',
    other_business_costs                        int(10) null comment '其它营业费用',
    total_operating_expenses                    int(10) null comment '营业费用合计',
    income_investment                           int(10) null comment '投资收益',
    operating_profit                            int(10) null comment '营业利润',
    subtract_bus_tax_surch                      int(10) null comment '减：营业税金及附加',
    add_nonbus_income                           int(10) null comment '加：营业外收入',
    subtract_nonbus_expend                      int(10) null comment '减：营业外支出',
    deduct_asset_impairment_before_total_profit int(10) null comment '扣除资产减值前利润总额',
    subtract_asset_reserve_expense              int(10) null comment '减：资产准备支出',
    deduct_asset_impairment_after_total_profit  int(10) null comment '扣除资产减值后利润总额',
    subtract_income_tax                         int(10) null comment '减：所得税',
    retained_profits                            int(10) null comment '净利润'
)
    comment '利润表' charset = utf8mb4
                     row_format = DYNAMIC;

create table rep_stock_price
(
    id                        int unsigned auto_increment comment '股价表ID'
        primary key,
    current_project_id        int(10)       not null comment '当前项目ID',
    current_team_id           int(10)       not null comment '当前团队ID',
    current_stage_id          int(3)        not null comment '当前阶段ID',
    capital_adequacy_ratio    double(10, 2) null comment '资本充足率',
    asset_scale               double(10, 2) null comment '资产规模',
    non_performing_loan_ratio double(10, 2) null comment '不良贷款率',
    liquidity_ratio           double(10, 2) null comment '流动性比率',
    return_on_net_assets      double(10, 2) null comment '净资产收益率',
    share_out_bonus           double(10, 2) null comment '每股股利（分红）',
    per_share_profit          double(10, 2) null comment '每股收益',
    per_share_net_assets      double(10, 2) null comment '每股净资产',
    stock_price               double(10, 2) null comment '股价'
)
    comment '股价表' charset = utf8mb4
                     row_format = DYNAMIC;

create table sys_log
(
    id         bigint auto_increment
        primary key,
    user_id    bigint        null comment '用户id',
    username   varchar(50)   null comment '用户名',
    operation  varchar(50)   null comment '用户操作',
    time       int           null comment '响应时间',
    method     varchar(200)  null comment '请求方法',
    params     varchar(5000) null comment '请求参数',
    ip         varchar(64)   null comment 'IP地址',
    gmt_create datetime      null comment '创建时间'
)
    comment '系统日志' charset = utf8
                       row_format = DYNAMIC;

create table sys_project
(
    pro_id      int(10) auto_increment comment '项目id'
        primary key,
    pro_name    varchar(255) null comment '项目名称',
    create_time timestamp    null comment '开启时间',
    update_time timestamp    null comment '更新时间',
    status      int(3)       null comment '项目状态',
    remarks     varchar(255) null comment '备注'
)
    comment '项目表' charset = utf8mb4
                     row_format = DYNAMIC;

create table sys_project_stage
(
    id       int(10) auto_increment
        primary key,
    pro_id   int(10) null comment '项目id',
    stage_id int(10) null comment '阶段id',
    status   int(1)  null comment '阶段是否开放（0：未开放1：已开放 2：结束）'
)
    comment '项目阶段关联表' charset = utf8mb4
                             row_format = DYNAMIC;

create table sys_project_team
(
    id      int(10) auto_increment comment '主键id'
        primary key,
    pro_id  int(10) null comment '项目id',
    team_id int(10) null comment '团队id'
)
    comment '项目团队关联表' charset = utf8mb4
                             row_format = DYNAMIC;

create table sys_project_team_stage
(
    id            int(10) auto_increment
        primary key,
    pro_id        int          null comment '项目id',
    team_id       int          null comment '团队id',
    stage_id      int          null comment '阶段id',
    business_name varchar(255) null comment '业务名称',
    status        int(1)       null comment '业务提交状态'
)
    comment '项目团队阶段业务关联表' charset = utf8mb4
                                     row_format = DYNAMIC;

create table sys_stage
(
    stage_id            int(3)       not null comment '阶段id'
        primary key,
    stage_name          varchar(255) null comment '阶段名称',
    stage_name_eng      varchar(3)   null comment '阶段英文名称',
    stage_business_name varchar(100) null
)
    comment '阶段表' charset = utf8mb4
                     row_format = DYNAMIC;

create table sys_stage_business
(
    id            int(10) auto_increment comment '阶段业务名称ID'
        primary key,
    stage_id      int(50)     null comment '阶段id',
    stage_name    varchar(50) null comment '阶段名称',
    business_name varchar(50) null comment '业务名称'
)
    comment '阶段业务名称' charset = utf8mb4
                           row_format = DYNAMIC;

create table sys_team
(
    team_id     int(10) auto_increment comment '团队id'
        primary key,
    team_name   varchar(255) null comment '团队名称',
    pro_id      int(10)      null comment '项目id',
    create_time timestamp    null comment '创建时间',
    delete_time timestamp    null comment '结束时间',
    sort        varchar(255) null comment '排序'
)
    comment '团队表' charset = utf8mb4
                     row_format = DYNAMIC;

create table sys_team_stage
(
    id       int(10) auto_increment comment 'id'
        primary key,
    team_id  int(10) null comment '团队id',
    stage_id int(10) null comment '阶段id'
)
    comment '团队阶段关联表' charset = utf8mb4
                             row_format = DYNAMIC;

create table t_decision_summary
(
    id          int(10) auto_increment
        primary key,
    project     varchar(255)                       null comment '项目',
    stage       varchar(255)                       null comment '阶段',
    business    varchar(255)                       null comment '业务',
    team        varchar(255)                       null comment '决策团队',
    name        varchar(255)                       null comment '决策人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime                           null on update CURRENT_TIMESTAMP comment '上次更新时间',
    status      int(1)                             null comment '总结状态'
)
    comment '决策总结表' charset = utf8mb4
                         row_format = DYNAMIC;

create table t_department
(
    id          int unsigned auto_increment comment '????id'
        primary key,
    name        varchar(50)                        not null comment '????',
    short_name  varchar(50)                        null comment '????',
    manager_id  int unsigned                       null comment '?????id',
    parent_id   int unsigned                       null comment '?????id',
    sort        int                                not null comment '????',
    update_time datetime default CURRENT_TIMESTAMP not null comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????',
    project_id  int(10)                            null comment '项目ID',
    logo        varchar(255)                       null comment '头像'
)
    comment '???' row_format = DYNAMIC;

create index parent_id
    on t_department (parent_id);

create table t_email
(
    id          int auto_increment
        primary key,
    title       varchar(100)                       not null comment '??',
    to_emails   varchar(200)                       not null comment '???',
    send_status tinyint  default 0                 not null comment '???? 0??? 1???',
    content     longtext                           not null comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????'
)
    row_format = DYNAMIC;

create table t_employee
(
    id            int unsigned auto_increment comment '??'
        primary key,
    login_name    varchar(30) charset utf8              not null comment '????',
    login_pwd     varchar(50) charset utf8              not null comment '????',
    actual_name   varchar(30) charset utf8              not null comment '????',
    nick_name     varchar(30) default ''                null comment '??',
    phone         varchar(15) charset utf8              null comment '????',
    id_card       varchar(18) charset utf8              null comment '???',
    birthday      date                                  null comment '????',
    email         varchar(50)                           null comment '??',
    department_id int unsigned                          not null comment '??id',
    is_leave      int         default 0                 not null comment '????1?',
    is_disabled   int         default 0                 not null comment '????? 0?1?',
    remark        varchar(200)                          null comment '??',
    create_user   int unsigned                          not null comment '???id',
    update_time   datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time   datetime    default CURRENT_TIMESTAMP not null comment '????',
    is_delete     int         default 0                 not null comment '????0? 1?'
)
    comment '???' row_format = DYNAMIC;

create table t_file
(
    id                 int auto_increment comment '主键ID'
        primary key,
    module_id          varchar(50)                        not null comment '相关业务id',
    module_type        varchar(50)                        not null comment '相关业务类型',
    file_name          varchar(255)                       null comment '文件名称',
    file_upload_type   int                                null comment '文件上传类型(1：培训课程资料，2：专业知识，3：决策信息解释)',
    file_size          varchar(255)                       null comment '文件大小',
    file_type          varchar(50)                        null comment '文件类型，程序中枚举控制，文件类型：如身份证正面，三证合一等等',
    file_path          varchar(255)                       not null comment '文件key，用于文件下载',
    file_location_type int                                not null comment '文件位置类型',
    creater_user       int                                not null comment '创建人，即上传人',
    update_time        datetime                           null on update CURRENT_TIMESTAMP comment '上次更新时间',
    create_time        datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    row_format = DYNAMIC;

create index module_id_module_type
    on t_file (module_id, module_type);

create index module_type
    on t_file (module_type);

create table t_heart_beat_record
(
    id                 int auto_increment comment '??id'
        primary key,
    project_path       varchar(100) null comment '????',
    server_ip          varchar(200) null comment '???ip',
    process_no         int          null comment '???',
    process_start_time datetime     null comment '??????',
    heart_beat_time    datetime     null comment '????'
)
    row_format = DYNAMIC;

create table t_id_generator
(
    id          int           null,
    key_name    varchar(50)   not null comment '??key',
    rule_format varchar(500)  not null comment '?????no_cycle????, year_cycle ???, month_cycle???, day_cycle ???',
    rule_type   varchar(50)   not null comment '??[yyyy]???,[mm]???,[dd]???,[nnn]??????',
    init_number int default 1 not null comment '???',
    last_number int           null comment '?????id, ????',
    remark      varchar(1000) not null comment '??',
    update_time datetime      null,
    create_time datetime      not null,
    constraint key_name
        unique (key_name)
)
    comment 'id??????' row_format = DYNAMIC;

create table t_id_generator_record
(
    generator_id int not null,
    year         int not null,
    month        int not null,
    day          int not null,
    last_number  int not null,
    primary key (generator_id, year, month, day)
)
    comment 'id_generator???' row_format = DYNAMIC;

create table t_message
(
    id           int(10) auto_increment
        primary key,
    message_name varchar(255)                       null comment '消息名称',
    content      varchar(255)                       null comment '内容',
    team         varchar(255)                       null comment '发布范围',
    message_type int(1)                             null comment '消息类型',
    status       int(1)                             null comment '消息状态',
    release_mode varchar(255)                       null comment '发布方式',
    creater_user int                                null comment '创建人，即发布人',
    release_time datetime                           null comment '发布时间',
    end_time     datetime                           null comment '结束时间',
    update_time  datetime                           null on update CURRENT_TIMESTAMP comment '上次更新时间',
    create_time  datetime default CURRENT_TIMESTAMP null comment '创建时间'
)
    comment '消息管理表' charset = utf8mb4
                         row_format = DYNAMIC;

create table t_notice
(
    id          bigint auto_increment
        primary key,
    title       varchar(200)                               not null comment '????',
    content     longtext                                   not null comment '????',
    deleted     tinyint unsigned default 0                 not null comment '?????0??? 0?? ',
    send_status tinyint(1)       default 0                 not null comment '???? 0??? 1??',
    create_user bigint                                     not null comment '?????',
    create_time datetime         default CURRENT_TIMESTAMP not null comment '????',
    update_time datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????'
)
    row_format = DYNAMIC;

create table t_notice_receive_record
(
    id          bigint auto_increment
        primary key,
    notice_id   bigint                             not null comment '??id',
    employee_id bigint                             not null comment '??id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????'
)
    row_format = DYNAMIC;

create table t_order_operate_log
(
    id              bigint auto_increment
        primary key,
    order_id        int                                not null comment '?????id',
    order_type      int                                not null comment '????',
    operate_type    int                                not null comment '????',
    operate_content text                               not null comment '???? ?????',
    operate_remark  text                               null comment '????',
    employee_id     int                                not null comment '??id',
    employee_name   varchar(1000)                      not null comment '????',
    ext_data        text                               null comment '????',
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '????'
)
    comment '????????
' row_format = DYNAMIC;

create index order_id_order_type
    on t_order_operate_log (order_id, order_type);

create table t_peony
(
    id          bigint auto_increment comment 'ID'
        primary key,
    kind        varchar(500)                       null comment '??',
    name        varchar(500)                       null comment '??',
    color       varchar(500)                       null comment '??',
    image_url   text                               null comment '????',
    create_time datetime default CURRENT_TIMESTAMP null comment '????',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '????'
)
    comment '???' charset = utf8
                  row_format = DYNAMIC;

create table t_position
(
    id            int unsigned auto_increment
        primary key,
    position_name varchar(255)                       null comment '????',
    remark        varchar(255) charset utf8          null comment '????',
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '????'
)
    comment '???' row_format = DYNAMIC;

create table t_position_relation
(
    id          int unsigned auto_increment
        primary key,
    position_id int                                null comment '角色ID',
    employee_id int                                null comment '用户ID',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '?????' row_format = DYNAMIC;

create index employee_id
    on t_position_relation (employee_id);

create index job_id
    on t_position_relation (position_id);

create table t_privilege
(
    id          int unsigned auto_increment comment '???????id'
        primary key,
    type        tinyint                            not null comment '1.?? 2.???',
    name        varchar(50)                        not null comment '????',
    `key`       varchar(1000)                      not null comment '??name ?????',
    url         text                               null comment '??path/type=3?API??',
    sort        int      default 0                 not null comment '??',
    parent_key  varchar(1000)                      null comment '??key',
    update_time datetime                           not null on update CURRENT_TIMESTAMP comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????',
    constraint `key`
        unique (`key`)
)
    comment '?????' charset = utf8
                    row_format = DYNAMIC;

create index parent_key
    on t_privilege (parent_key);

create index type
    on t_privilege (type);

create table t_quartz_task
(
    id          int auto_increment
        primary key,
    task_name   varchar(50)                        not null comment '????',
    task_bean   varchar(50)                        not null comment 'spring bean??',
    task_params varchar(1000)                      null comment '????',
    task_cron   varchar(50)                        not null comment '??cron???',
    task_status tinyint  default 0                 not null comment '????0:???1:??',
    remark      varchar(500)                       null comment '??',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????'
)
    row_format = DYNAMIC;

create table t_quartz_task_log
(
    id               int auto_increment
        primary key,
    task_id          int                                not null comment '??id',
    task_name        varchar(50)                        not null comment '????',
    task_params      varchar(1000)                      null comment '????',
    process_status   tinyint                            not null comment '??????0:???1:??',
    process_duration bigint   default 0                 not null comment '????',
    process_log      text                               null comment '??',
    ip_address       varchar(50)                        null comment '????ip',
    update_time      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '????'
)
    row_format = DYNAMIC;

create table t_reload_item
(
    tag            varchar(255) charset utf8          not null comment '???'
        primary key,
    args           varchar(255)                       null comment '?? ??',
    identification varchar(255) charset utf8          not null comment '????',
    update_time    datetime                           null on update CURRENT_TIMESTAMP,
    create_time    datetime default CURRENT_TIMESTAMP not null
)
    row_format = DYNAMIC;

create table t_reload_result
(
    tag            varchar(255)                       not null,
    identification varchar(255)                       not null comment '????',
    args           varchar(255)                       null,
    result         tinyint unsigned                   not null comment '???? ',
    exception      text                               null,
    create_time    datetime default CURRENT_TIMESTAMP not null
)
    row_format = DYNAMIC;

create table t_role
(
    id          int unsigned auto_increment comment '??'
        primary key,
    role_name   varchar(20)                        not null comment '????',
    remark      varchar(255)                       null comment '????',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????'
)
    comment '???' charset = utf8
                  row_format = DYNAMIC;

create table t_role_data_scope
(
    id              int auto_increment
        primary key,
    data_scope_type int                                not null comment '????id',
    view_type       int                                not null comment '??????',
    role_id         int                                not null comment '??id',
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '????'
)
    row_format = DYNAMIC;

create table t_role_employee
(
    id          int auto_increment
        primary key,
    role_id     int                                not null comment '??id',
    employee_id int                                not null comment '??id',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????'
)
    comment '???????' row_format = DYNAMIC;

create table t_role_privilege
(
    id            int auto_increment
        primary key,
    role_id       int                                not null comment '??id',
    privilege_key varchar(1000)                      not null comment '??key',
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '????'
)
    comment '???????' row_format = DYNAMIC;

create table t_scene_management
(
    id           int(10) auto_increment
        primary key,
    title        varchar(255)                       null comment '标题',
    content      varchar(255)                       null comment '内容',
    team         varchar(255)                       null comment '发布范围',
    creater_user int                                null comment '创建人，即发布人',
    release_type int(1)                             null comment '发布类型',
    release_mode varchar(255)                       null comment '发布方式',
    status       int(1)                             null comment '状态',
    release_time datetime                           null comment '发布时间',
    end_time     datetime                           null comment '结束时间',
    create_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  datetime                           null on update CURRENT_TIMESTAMP comment '上次更新时间'
)
    comment '场景管理表' charset = utf8mb4
                         row_format = DYNAMIC;

create table t_system_config
(
    id           int unsigned auto_increment comment '主键'
        primary key,
    config_name  varchar(255)                       not null comment '参数名字',
    config_key   varchar(255)                       not null comment '参数key',
    config_value text                               not null,
    config_group varchar(255)                       not null comment '参数类别',
    is_using     int                                not null comment '是否使用0 否 1 是',
    remark       varchar(255)                       null,
    update_time  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '上次修改时间',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    row_format = DYNAMIC;

create table t_user_login_log
(
    id             int unsigned auto_increment comment '??'
        primary key,
    user_id        int                                not null comment '??id',
    user_name      varchar(50)                        not null comment '???',
    remote_ip      varchar(50)                        null comment '??ip',
    remote_port    int                                null comment '????',
    remote_browser varchar(100)                       null comment '???',
    remote_os      varchar(50)                        null comment '????',
    login_status   tinyint                            not null comment '???? 0 ??  1??',
    update_time    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '????'
)
    comment '??????' row_format = DYNAMIC;

create index auditor_id
    on t_user_login_log (remote_browser);

create index customer_id
    on t_user_login_log (user_id);

create table t_user_operate_log
(
    id          int auto_increment comment '??'
        primary key,
    user_id     int                                not null comment '??id',
    user_name   varchar(50)                        not null comment '????',
    module      varchar(50)                        null comment '????',
    content     varchar(500)                       null comment '????',
    url         varchar(100)                       null comment '????',
    method      varchar(500)                       null comment '????',
    param       text                               null comment '????',
    result      tinyint                            null comment '???? 0?? 1??',
    fail_reason longtext                           null comment '????',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '????',
    create_time datetime default CURRENT_TIMESTAMP not null comment '????'
)
    row_format = DYNAMIC;

create table user_photo
(
    id         int(11) unsigned auto_increment
        primary key,
    user_id    int          not null,
    user_photo varchar(100) not null
)
    charset = utf8mb4
    row_format = DYNAMIC;


