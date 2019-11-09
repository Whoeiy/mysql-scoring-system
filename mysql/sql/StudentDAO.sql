use scoring_system;
##-------------------------------------##
# 0. log in
##-------------------------------------##


#   name: proc_isin_student_s_no
#   for: 判断输入的学号是否在student表中存在
#   author: 于越

delimiter $$
create procedure proc_isin_student_s_no(in sno char(10), out isin int)
reads sql data
begin
    select count(*) into isin from student where s_no = sno;
end $$
delimiter ;


##-------------------------------------##
# 1. 申请加分项
##-------------------------------------##

#   1.1
#   name: proc_get__extra_name_info
#   for: 获得加分明细的编号和名称
#   author: 于越

delimiter $$
use scoring_system;
create procedure proc_get_extra_detail_name(in etr char(10))
reads sql data
begin
    select detail,name from extra_name where extra = 'me_etr' order by cast(detail as signed);
end $$

drop procedure proc_get_extra_detail_name;
