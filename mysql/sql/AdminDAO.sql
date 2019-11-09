use scoring_system;
##-------------------------------------##
# 1. 添加学生信息
##-------------------------------------##

#   name: proc_insert_student
#   for: 添加学生信息
#   author:

delimiter $$
create procedure proc_insert_student(in sno char(10),in sname varchar(10), in sclass char(5), in spro char(6), in sfeature char(50), out flag int)
modifies sql data
begin
    
end $$
delimiter ;

#   1.1
#   name: proc_delete_student
#   for: 删除学生信息
#   author:

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
#   author:

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
#   author:

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



