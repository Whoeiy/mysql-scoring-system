/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/7 0:37:15                            */
/*==============================================================*/


drop table if exists course;

drop table if exists grade;

drop table if exists grade_by_course;

drop table if exists ie_extra;

drop table if exists intellectual;

drop table if exists me_extra;

drop table if exists me_minus;

drop table if exists moral;

drop table if exists pe_extra;

drop table if exists physical;

drop table if exists student;

drop table if exists summary;

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   crs_no               char(15) not null,
   crs_name             char(20) not null,
   crs_type             char(5) not null,
   primary key (crs_no)
);

/*==============================================================*/
/* Table: grade                                                 */
/*==============================================================*/
create table grade
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   s_test_total         char(5) not null,
   s_test_avg           char(5) not null,
   s_exam_total         char(5) not null,
   s_exam_avg           char(5) not null,
   s_grade              char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: grade_by_course                                       */
/*==============================================================*/
create table grade_by_course
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   crs_no               char(15) not null,
   crs_grade            char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: ie_extra                                              */
/*==============================================================*/
create table ie_extra
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   ie_etr_1             char(5) not null,
   ie_etr_2             char(5) not null,
   ie_etr_3             char(5) not null,
   ie_extra             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: intellectual                                          */
/*==============================================================*/
create table intellectual
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   s_studygrade         char(5) not null,
   ie_extra             char(5) not null,
   ie_total             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: me_extra                                              */
/*==============================================================*/
create table me_extra
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   me_etr_1             char(5) not null,
   me_etr_2             char(5) not null,
   me_etr_3             char(5) not null,
   me_etr_4             char(5) not null,
   me_etr_5             char(5) not null,
   me_etr_6             char(5) not null,
   me_etr_7             char(5) not null,
   me_etr_8             char(5) not null,
   me_etr_9             char(5) not null,
   me_etr_10            char(5) not null,
   me_extra             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: me_minus                                              */
/*==============================================================*/
create table me_minus
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   me_mns_1             char(5) not null,
   me_mns_2             char(5) not null,
   me_minus             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: moral                                                 */
/*==============================================================*/
create table moral
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   me_basic             char(5) not null,
   me_extra             char(5) not null,
   me_minus             char(5) not null,
   me_total             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: pe_extra                                              */
/*==============================================================*/
create table pe_extra
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   pe_etr_1             char(5) not null,
   pe_etr_2             char(5) not null,
   pe_etr_3             char(5) not null,
   pe_etr_4             char(5) not null,
   pe_extra             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: physical                                              */
/*==============================================================*/
create table physical
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   pe_basic             char(5) not null,
   pe_test              char(5) not null,
   pe_extra             char(5) not null,
   pe_total             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   s_no                 char(10) not null,
   s_name               varchar(10) not null,
   s_class              char(5) not null,
   primary key (s_no)
);

/*==============================================================*/
/* Table: summary                                               */
/*==============================================================*/
create table summary
(
   no                   int not null auto_increment,
   s_no                 char(10) not null,
   s_me                 char(5) not null,
   s_ie                 char(5) not null,
   s_pe                 char(5) not null,
   s_total              char(5) not null,
   s_grade              char(5) not null,
   primary key (no)
);

alter table grade_by_course add constraint FK_corresponding foreign key (crs_no)
      references course (crs_no) on delete restrict on update restrict;

alter table grade_by_course add constraint FK_has_grade2 foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table ie_extra add constraint FK_has_ie foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table intellectual add constraint FK_ie_grade foreign key (s_no)
      references ie_extra (s_no) on delete restrict on update restrict;

alter table me_extra add constraint FK_has_me_extra foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table me_minus add constraint FK_has_me_minus foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table moral add constraint FK_me_extra2 foreign key (s_no)
      references me_extra (s_no) on delete restrict on update restrict;

alter table pe_extra add constraint FK_has_pe foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table physical add constraint FK_pe_extra2 foreign key (s_no)
      references pe_extra (s_no) on delete restrict on update restrict;

alter table summary add constraint FK_score foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

