use scoring_system;
##-------------------------------------##
# 0. log in
##-------------------------------------##


#   name: proc_isin_student_s_no
#   for: 判断输入的学号是否在student表中存在
#   author: 于越

delimiter $$
drop procedure if exists proc_isin_student_s_no;
create procedure proc_isin_student_s_no(in sno char(10))
reads sql data
begin
    select count(*) as flag from student where s_no = sno;
end $$
delimiter ;


##-------------------------------------##
# 1. 申请加分项
##-------------------------------------##

#   1.1
#   name: proc_get_extra_name_info
#   for: 获得加分明细的编号和名称
#   author: 于越

delimiter $$
use scoring_system;
drop procedure if exists proc_get_extra_detail_name;
create procedure proc_get_extra_detail_name(in etr char(10))
reads sql data
begin
    select extra,detail,name,max,min from extra_name where extra = etr order by cast(detail as signed);
end $$


#   1.2
#   name: proc_insert_extra
#   for: 插入一条加分项记录
#   author: 于越

delimiter $$
drop procedure if exists proc_insert_extra;
create procedure proc_insert_extra(in year char(10), in sno char(10), in etr char(10), in dtl char(2), in rmrks text, in sp char(5), in gp char(5), in sts char(2), in pas char(2))
modifies sql data
begin
    insert into extra values(null,year,sno,etr,dtl,rmrks,sp,gp,sts,pas);
    call proc_get_extra(sno);
end $$
delimiter ;


##-------------------------------------##
# 2. 查看已申请的加分项
##-------------------------------------##


#   2.1.2
#   name: proc_get_extra
#   for: 显示一条加分项记录
#   author: 于越

delimiter $$
drop procedure if exists proc_get_extra;
create procedure proc_get_extra(in sno char(10))
reads sql data
begin
    select * from extra where s_no = sno;
end $$

#   2.1.1
#   name: proc_get_extras_by_n_extra
#   for: 按分类查询加分项记录
#   remarks: 默认按照detail列进行排序
#   author: 于越

delimiter $$
drop procedure if exists proc_get_extras_by_n_extra;
create procedure proc_get_extras_by_n_extra(in sno char(10), in etr char(10))
reads sql data
begin
    select year, extra_name.name as name, s_point, g_point, status, pass from extra
    inner join extra_name
    on extra.extra = extra_name.extra and extra.detail = extra_name.detail
    where s_no = sno and extra.extra = etr
    order by cast(extra.detail as signed);
end $$


