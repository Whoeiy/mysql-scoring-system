/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/7 11:57:38                           */
/*==============================================================*/


drop table if exists course;

drop table if exists extra;

drop table if exists extra_name;

drop table if exists grade;

drop table if exists grade_by_course;

drop table if exists intellectual;

drop table if exists moral;

drop table if exists physical;

drop table if exists student;

drop table if exists summary;

drop table if exists department;

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   crs_no               char(15) not null,
   crs_name             char(20) not null,
   crs_point            char(5) not null,
   crs_type             char(5) not null,
   primary key (crs_no)
);

/*==============================================================*/
/* Table: extra                                                 */
/*==============================================================*/
create table extra
(
   no                   int not null auto_increment,
   year                 char(10) not null,
   s_no                 char(10) not null,
   extra                char(10) not null,
   detail               char(2) not null,
   remarks              text,
   s_point              char(5) not null,
   g_point              char(5) not null,
   status               char(2) not null,
   pass                 char(2) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: extra_name                                            */
/*==============================================================*/
create table extra_name
(
   no                   int not null auto_increment,
   extra                char(10) not null,
   detail               char(2) not null,
   name                 char(50) not null,
   max                  char(5),
   min                  char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: grade                                                 */
/*==============================================================*/
create table grade
(
   no                   int not null  auto_increment,
   year                 char(10) not null,
   s_no                 char(10) not null,
   s_test_total         char(5) not null,
   s_test_avg           char(5) not null,
   s_exam_total         char(5) not null,
   s_exam_avg           char(5) not null,
   s_weight_mean        char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: grade_by_course                                       */
/*==============================================================*/
create table grade_by_course
(
   no                   int not null auto_increment,
   year                 char(10) not null,
   s_no                 char(10) not null,
   crs_no               char(15) not null,
   crs_grade            char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: intellectual                                          */
/*==============================================================*/
create table intellectual
(
   no                   int not null auto_increment,
   year                 char(10) not null,
   s_no                 char(10) not null,
   s_studygrade         char(5) not null,
   ie_extra             char(5) not null,
   ie_total             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: moral                                                 */
/*==============================================================*/
create table moral
(
   no                   int not null auto_increment,
   year                 char(5) not null,
   s_no                 char(10) not null,
   me_basic             char(5) not null,
   me_extra             char(5) not null,
   me_minus             char(5) not null,
   me_total             char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: physical                                              */
/*==============================================================*/
create table physical
(
   no                   int not null auto_increment,
   year                 char(10) not null,
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
   s_pro                char(6) not null,
   s_feature            char(50) not null,
   primary key (s_no)
);

/*==============================================================*/
/* Table: summary                                               */
/*==============================================================*/
create table summary
(
   no                   int not null auto_increment,
   year                 char(10) not null,
   s_no                 char(10) not null,
   s_me                 char(5) not null,
   s_ie                 char(5) not null,
   s_pe                 char(5) not null,
   s_total              char(5) not null,
   s_grade              char(5) not null,
   primary key (no)
);

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department 
(
   d_no                 char(10) not null,
   department           char(25) not null,
   major                char(25) not null,
   primary key (d_no)
);


create index FK_s_grade on grade_by_course(s_no);
create index FK_corresponding1 on extra_name(extra);

alter table extra add constraint FK_corresponding1 foreign key (extra)
      references extra_name (extra) on delete restrict on update restrict;

alter table extra add constraint FK_has1 foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table grade add constraint FK_s_grade foreign key (s_no)
      references grade_by_course (s_no) on delete restrict on update restrict;

alter table grade_by_course add constraint FK_corresponding2 foreign key (crs_no)
      references course (crs_no) on delete restrict on update restrict;

alter table grade_by_course add constraint FK_has6 foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

alter table intellectual add constraint FK_ie foreign key (s_no)
      references extra (s_no) on delete restrict on update restrict;

alter table moral add constraint FK_me foreign key (s_no)
      references extra (s_no) on delete restrict on update restrict;

alter table physical add constraint FK_pe foreign key (s_no)
      references extra (s_no) on delete restrict on update restrict;

alter table summary add constraint FK_has3 foreign key (s_no)
      references student (s_no) on delete restrict on update restrict;

