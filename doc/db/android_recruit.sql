------------------------------------------
-- Export file for user ANDROID_RECRUIT --
-- Created by YQ on 2020-05-31, 9:48:14 --
------------------------------------------

set define off
spool android_recruit.log

prompt
prompt Creating table T_COLLECTION
prompt ===========================
prompt
create table ANDROID_RECRUIT.T_COLLECTION
(
  person_id         NUMBER(4),
  company_id        NUMBER(4),
  company_to_person VARCHAR2(1),
  resume_name       VARCHAR2(30),
  recruit_id        NUMBER(4),
  times             DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_EDUCATION
prompt ==========================
prompt
create table ANDROID_RECRUIT.T_EDUCATION
(
  edu_id      NUMBER(4),
  user_id     NUMBER(4),
  r_name      VARCHAR2(30),
  years_start DATE,
  years_end   DATE,
  school      VARCHAR2(60),
  major       VARCHAR2(30)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_EDU_JOB_CONDITION
prompt ==================================
prompt
create table ANDROID_RECRUIT.T_EDU_JOB_CONDITION
(
  edu_job_condition_id NUMBER(4),
  user_id              NUMBER(4),
  r_name               VARCHAR2(30),
  times                DATE,
  con_describe         VARCHAR2(90)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_ENTERPRISE
prompt ===========================
prompt
create table ANDROID_RECRUIT.T_ENTERPRISE
(
  e_id       NUMBER(4),
  e_name     VARCHAR2(60),
  e_property VARCHAR2(15),
  e_scale    VARCHAR2(15),
  e_city     VARCHAR2(15),
  e_addr     VARCHAR2(90),
  e_info     VARCHAR2(900),
  is_freeze  VARCHAR2(1),
  e_tel      VARCHAR2(11)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_EXPERIENCE
prompt ===========================
prompt
create table ANDROID_RECRUIT.T_EXPERIENCE
(
  exp_id       NUMBER(4),
  user_id      NUMBER(4),
  r_name       VARCHAR2(30),
  years_start  DATE,
  years_end    DATE,
  company_name VARCHAR2(40),
  job          VARCHAR2(21),
  job_describe VARCHAR2(300)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_JOB_INTENT
prompt ===========================
prompt
create table ANDROID_RECRUIT.T_JOB_INTENT
(
  intent_id       NUMBER(4),
  user_id         NUMBER(4),
  r_name          VARCHAR2(30),
  city            VARCHAR2(15),
  job             VARCHAR2(30),
  intent_salary   NUMBER(4),
  arrival_time    VARCHAR2(15),
  self_evaluation VARCHAR2(300),
  self_tag        VARCHAR2(150)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_PERSON_COMPANY
prompt ===============================
prompt
create table ANDROID_RECRUIT.T_PERSON_COMPANY
(
  company_id        NUMBER(4),
  person_id         NUMBER(4),
  company_to_person VARCHAR2(1),
  success           VARCHAR2(1),
  message           VARCHAR2(300),
  times             DATE,
  resume_name       VARCHAR2(30),
  recruit_id        NUMBER(4)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_PROJECT_EXPRIENCE
prompt ==================================
prompt
create table ANDROID_RECRUIT.T_PROJECT_EXPRIENCE
(
  project_exp_id NUMBER(4),
  user_id        NUMBER(4),
  r_name         VARCHAR2(30),
  time_start     DATE,
  time_end       DATE,
  company_name   VARCHAR2(45),
  project_name   VARCHAR2(45),
  pro_describe   VARCHAR2(300)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_RECRUITMENT_INFO
prompt =================================
prompt
create table ANDROID_RECRUIT.T_RECRUITMENT_INFO
(
  e_id         NUMBER(4),
  job          VARCHAR2(30),
  staff_num    VARCHAR2(3),
  salary_start VARCHAR2(4),
  salary_end   VARCHAR2(4),
  recruit_info VARCHAR2(900),
  experience   VARCHAR2(150),
  recruit_id   NUMBER(4),
  times        DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_RESUME
prompt =======================
prompt
create table ANDROID_RECRUIT.T_RESUME
(
  user_id          NUMBER(4) not null,
  r_name           VARCHAR2(30) not null,
  r_gender         VARCHAR2(1),
  r_birthday       DATE,
  r_age            VARCHAR2(4),
  r_city           VARCHAR2(15),
  r_tel            VARCHAR2(11),
  r_qualifications VARCHAR2(9),
  r_work_year      VARCHAR2(2),
  state            VARCHAR2(1),
  isvisible        VARCHAR2(1)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ANDROID_RECRUIT.T_RESUME
  add constraint PK_RESUME primary key (USER_ID, R_NAME)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_USER
prompt =====================
prompt
create table ANDROID_RECRUIT.T_USER
(
  user_id    NUMBER(4),
  u_username VARCHAR2(27),
  u_password VARCHAR2(20),
  u_kind     VARCHAR2(1)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence SEQ_EDU
prompt =========================
prompt
create sequence ANDROID_RECRUIT.SEQ_EDU
minvalue 1
maxvalue 9999999999999999999999999999
start with 81
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_EDU_JOB_CONDITION
prompt =======================================
prompt
create sequence ANDROID_RECRUIT.SEQ_EDU_JOB_CONDITION
minvalue 1
maxvalue 9999999999999999999999999999
start with 101
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_EXP
prompt =========================
prompt
create sequence ANDROID_RECRUIT.SEQ_EXP
minvalue 1
maxvalue 9999999999999999999999999999
start with 101
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_INTENT
prompt ============================
prompt
create sequence ANDROID_RECRUIT.SEQ_INTENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 121
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PRO_EXP
prompt =============================
prompt
create sequence ANDROID_RECRUIT.SEQ_PRO_EXP
minvalue 1
maxvalue 9999999999999999999999999999
start with 101
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_RECRUITMENT_INFO
prompt ======================================
prompt
create sequence ANDROID_RECRUIT.SEQ_RECRUITMENT_INFO
minvalue 1
maxvalue 9999999999999999999999999999
start with 64
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_USER
prompt ==========================
prompt
create sequence ANDROID_RECRUIT.SEQ_USER
minvalue 1
maxvalue 9999999999999999999999999999
start with 161
increment by 1
cache 20;

prompt
prompt Creating procedure PCINSERTORUPDATEMSG
prompt ======================================
prompt
create or replace procedure android_recruit.PCInsertOrUpdateMsg(
         companyId varchar2 :='1',
         personId varchar2 :='6',
         resume_name varchar2 :='¼òÀú1',
         msg varchar2 := ''
  )
  as
  BEGIN
    IF  not exists (select 1 from t_person_company where person_id = personId and resume_name = resume_name and company_id = companyId and company_to_person = '1' and  success = '1')  THEN
     insert into t_person_company values (company_id = companyId,person_id = personId,company_to_person = '1',success = '1' ,msg,sysdate,resume_name = resume_name,null);
    ELSE
     update t_person_company set message = msg , company_to_person = '1' where person_id = personId and company_id = companyId and resume_name = resume_name and success = '1');
    END IF;
  END;
  /
/


spool off
