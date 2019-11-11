use scoring_system;
##-------------------------------------##
# 1. 添加学生信息
##-------------------------------------##

#   name: proc_insert_student
#   for: 添加学生信息
#   author: 于越

delimiter $$
create procedure proc_insert_student(in sno char(10),in sname varchar(10), in sclass char(5), in spro char(6), in sfeature char(50), out flag int)
modifies sql data
begin
    insert into student values(sno,sname,sclass,spro,sfeature);
end $$
delimiter ;

#   1.1
#   name: proc_delete_student
#   for: 删除学生信息
#   author: 于越

delimiter $$
create procedure proc_delete_student(in sno char(10),out flag int)
modifies sql data
begin
    set flag = 0;
    delete from student where s_no = sno;
    set flag = 1;
end $$
delimiter ;

#   1.2
#   name: proc_update_student
#   for: 修改学生信息
#   author: 于越

delimiter $$
create procedure proc_update_student(in sno char(10),in supdate char(50),inout flag int)
modifies sql data
begin
    if(flag = 2) then   # 修改学生姓名
        update student set s_name = supdate where s_no = sno;
        set flag = 1;
    elseif(flag = 3) then   # 修改学生班级号
        update student set s_class = supdate where s_no = sno;
        set flag = 1;
    elseif(flag = 4) then   # 修改学生系别专业号
        update student set s_pro = supdate where s_no = sno;
        set flag = 1;
    else    # 修改学生属性
        update student set s_feature = supdate where s_no = sno;
        set flag = 1;
    end if;
end $$
delimiter ;

#   1.3
#   name: proc_select_student
#   for: 查询学生信息
#   author: 于越

delimiter $$
create procedure proc_select_student(in sselect char(10),in flag int)
modifies sql data
begin
    if(flag = 1) then   # 按学号查询
        select * from student where s_no = sselect;
        # 显示一条信息
    elseif(flag = 2) then   # 按姓名查询
        select * from student where s_name = sselect;
        # 显示一条信息，除非重名
    elseif(flag = 3) then   # 按班级查询
        select * from student where s_class = sselect order by s_no;
    else    # 按系部专业查询
        select * from student where s_pro = sselect order by s_no;
    end if;
end $$
delimiter ;


##-------------------------------------##
# 4. 审核学生申报加分项信息
##-------------------------------------##

#   4.1
#   name: proc_get_extra_year
#   for: 查询加分项所属年份
#   author: 于越

delimiter $$
drop procedure if exists proc_get_extra_year;
create procedure proc_get_extra_year()
reads sql data
begin
    select distinct year from extra order by year;
end $$

#   4.2
#   name: proc_get_extra_classes
#   for: 查询加分项所属年份
#   author: 于越

delimiter $$
drop procedure if exists proc_get_extra_classes;
create procedure proc_get_extra_classes(in y char(10))
reads sql data
begin
    select student.s_class class, d_no, department, major from department, student
    where student.s_no in(
        select distinct s_no from extra
        where year = y
        )
    and student.s_pro = department.d_no
    order by d_no, class;
end $$

#   4.3
#   name: proc_admin_get_extras
#   for: 按年份&班级检索符合条件的学生情况
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_get_extras;
create procedure proc_admin_get_extras(in y char(10), in c char(5))
reads sql data
begin
    select year, extra.s_no, student.s_name as s_name, extra.extra as extra, extra.detail as detail, extra_name.name as name, remarks, s_point, g_point, status, pass from student,extra,extra_name
    where extra.s_no  = student.s_no
    and student.s_no in(
        select s_no from student
        where s_class = c
        )
    and extra.year = y
    and extra_name.extra = extra.extra and extra_name.detail = extra.detail
    order by extra.s_no, extra.extra, cast(extra.detail as signed);
end $$
delimiter ;


#   4.4
#   name: proc_admin_check_one_extra
#   for: 审核一条加分项记录
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_check_one_extra;
create procedure proc_admin_check_one_extra(in sno char(10), in etr char(10), in dtl char(2), in pas char(10), in gp char(5), out flag int)
modifies sql  data
begin
    declare continue handler for 1690
        begin
            rollback ;
        end ;
    set flag = 0;
    start transaction ;
    if(pas = '1') then
        update extra set pass = '1' where s_no = sno and extra = etr and detail = dtl;
    end if ;
    if(gp != 'null') then
        update extra set g_point = gp where s_no = sno and extra = etr and detail = dtl;
    end if ;
    update extra set status = '1' where s_no = sno and extra = etr and detail = dtl;
    commit;
    set flag = 1;
end $$
delimiter ;


##-------------------------------------##
# 5. 汇总学生综合测评成绩信息
##-------------------------------------##

#   5.1
#   name: proc_admin_collect_summary
#   for: 汇总总体综测情况
#   author: 于越
describe summary;

delimiter $$
drop procedure if exists proc_admin_collect_summary;
create procedure proc_admin_collect_summary(in y char(10), in sno char(10))
modifies sql data
begin
    declare sme int;
    declare sie int;
    declare spe int;
    declare sttl int;
    declare sg int;
    declare continue handler for 1690
        begin
            rollback ;
        end ;
    start transaction ;
    call proc_admin_collect_moral(y,sno,sme);
    call proc_admin_collect_intellectual(y,sno,sie);
    call proc_admin_collect_physical(y,sno,spe);
    call proc_admin_collect_studygrade(sno,sg);
    set sttl = sme + sie + spe;
    set sg = sg / 0.55;

    delete from summary where s_no = sno and year = y;
    insert into summary values(null,y,sno,sme,sie,spe,sttl,sg);
    commit ;
end $$
delimiter ;

# test
# describe summary;
# delete from summary;
# alter table summary auto_increment = 1;
# call proc_admin_collect_summary('2018~2019','170101008');
# select * from summary;


#   5.2
#   name: proc_admin_collect_moral
#   for: 汇总德育评分情况
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_collect_moral;
create procedure proc_admin_collect_moral(in y char(10),in sno char(10),out me_ttl int)
modifies sql data
begin
    declare me_etr int;
    declare me_mns int;
    declare me_bas int;
    declare continue handler for 1690
        begin
            rollback ;
        end ;
    start transaction ;
    set me_bas = 15;
    set me_etr = 0;
    set me_ttl = 0;
    set me_mns = 0;
    call proc_admin_collect_me_etr(sno,'me_etr',me_etr);
    call proc_admin_collect_me_etr(sno,'me_mns',me_mns);
    set me_ttl = me_bas + me_etr - me_mns;
    if ((me_etr - me_mns) >= 10) then
        set me_ttl = me_bas + 10;
    elseif ((me_etr - me_mns) < 0) then
        set me_ttl = me_bas + 0;
    end if;
    delete from physical  where s_no = sno and year = y;
    insert into moral values(null,y,sno,concat(me_bas),concat(me_etr),concat(me_mns),concat(me_ttl));
    commit ;
end $$
delimiter ;

#test
# set @r = 0;
# call proc_admin_collect_moral('2018','170101008',@r);
# select @r;
# select * from moral;
# delete from moral;
# alter table moral auto_increment = 1;
#
# describe moral;
# call proc_admin_collect_me_extra('170101008');
# set @me = 0;
# select * from extra where s_no = '170101008' and extra = 'me_etr';


#   5.2.1
#   name: proc_admin_collect_me_etr
#   for: 某一加分项统计
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_collect_me_etr;
create procedure proc_admin_collect_me_etr(in sno char(10), in etr char(10), out sum int)
modifies sql data
begin
    select SUM(g_point) into sum from extra where s_no = sno and extra = etr and status = '1' and pass = '1';
    if(sum is null) then set sum = 0;
    end if;
end $$

# test
# set @sum = 0;
# call proc_admin_collect_me_etr('170101008','me_etr',@sum);
# select @sum;


#   5.3
#   name: proc_admin_collect_intellectual
#   for: 汇总智育评分情况
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_collect_intellectual;
create procedure proc_admin_collect_intellectual(in y char(10),in sno char(10),out ie_ttl int)
modifies sql data
begin
    declare ie_sg int;
    declare ie_etr int;
    declare continue handler for 1690
        begin
            rollback ;
        end ;
    start transaction ;
    set ie_etr = 0;
    set ie_ttl = 0;
    set ie_sg = 0;
    call proc_admin_collect_me_etr(sno,'ie_etr',ie_etr);
    call proc_admin_collect_studygrade(sno,ie_sg);
    if (ie_etr > 5) then
        set ie_etr = 5;
    elseif (ie_etr < 0) then
        set ie_etr = 0;
    end if;
    set ie_ttl = ie_sg + ie_etr;
    delete from intellectual  where s_no = sno and year = y;
    insert into intellectual values(null,y,sno,concat(ie_sg),concat(ie_etr),concat(ie_ttl));
    commit ;
end $$
delimiter ;

# test
# call proc_admin_collect_intellectual('2018~2019','170101008',@g);
# select * from intellectual;


#   5.3.1
#   name: proc_admin_collect_studygrade
#   for: 某位同学成绩计算
#   author: 于越

# describe grade_by_course;
delimiter $$
drop procedure if exists proc_admin_collect_studygrade;
create procedure proc_admin_collect_studygrade(in sno char(10), out sg int)
modifies sql data
begin
    declare state char(20);
    declare sum_gxf int;
    declare sum_xf int;
    declare g char(5);
    declare p char(5);
    declare sg_cursor cursor for select crs_grade, course.crs_point from grade_by_course, course where s_no = sno and grade_by_course.crs_no = course.crs_no;
    declare continue handler for 1329 set state = 'error';
    set sg = 0;
    set sum_gxf = 0;
    set sum_xf = 0;
    open sg_cursor;
    repeat
        fetch sg_cursor into g, p;
        set sum_gxf = sum_gxf + (cast(g as signed ) * cast(p as signed));
        set sum_xf = sum_xf + cast(p as signed);
    until state = 'error' end repeat ;
    close sg_cursor;
    set sg = (sum_gxf / sum_xf) * 0.55;
end $$

# test
# select grade_by_course.crs_no, crs_grade, course.crs_point from grade_by_course, course where s_no = '170101024' and grade_by_course.crs_no = course.crs_no;
# set @g = 0;
# call proc_admin_collect_studygrade('170101008',@g);
# select @g;


#   5.4
#   name: proc_admin_collect_physical
#   for: 体育评分情况
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_collect_physical;
create procedure proc_admin_collect_physical(in y char(10),in sno char(10),out pe_ttl int)
modifies sql data
begin
    declare pe_tes int;
    declare pe_etr int;
    declare pe_bas int;
    declare continue handler for 1690
        begin
            rollback ;
        end ;
    start transaction ;
    set pe_bas = 7;
    set pe_etr = 0;
    set pe_ttl = 0;
    set pe_tes = 1;
    call proc_admin_collect_me_etr(sno,'pe_etr',pe_etr);
    set pe_ttl = pe_bas + pe_etr + pe_tes;
    if (pe_etr > 6) then
        set pe_etr = 6;
    elseif (pe_etr < 0) then
        set pe_etr = 0;
    end if;
    set pe_ttl = pe_bas + pe_etr + pe_tes;
    delete from physical  where s_no = sno and year = y;
    insert into physical values(null,y,sno,concat(pe_bas),concat(pe_tes),concat(pe_etr),concat(pe_ttl));
    commit ;
end $$
delimiter ;

#test
call proc_admin_collect_physical('2018~2019','170101008',@g);
select * from physical;

#   5.5
#   name: proc_admin_collect
#   for: 生成综测总表
#   author: 于越

delimiter $$
drop procedure if exists proc_admin_collect;
create procedure proc_admin_collect(in y char(10), in c char(5))
modifies sql data
begin
    declare sno char(10);
    declare state char(20);
    declare c_cursor cursor for select distinct extra.s_no from student,extra
    where extra.s_no  = student.s_no
      and student.s_no in(
          select s_no from student
          where s_class = c
          )
      and extra.year = y;
    declare continue handler for 1329 set state = 'error';
    open c_cursor;
    repeat
    fetch c_cursor into sno;
    call proc_admin_collect_summary(y,sno);
    until state = 'error' end repeat ;
    close c_cursor;
    select year,summary.s_no as s_no,s_name,s_me,s_ie,s_pe,s_total,s_grade from summary,student
    where summary.s_no = student.s_no
    order by s_total desc ;
end $$
delimiter ;

# test
# call proc_admin_collect('2018~2019','1701');
# delete from summary;