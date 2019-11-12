# course
# crs_no,crs_name,crs_point,crs_type
insert into course values('102023','线性代数','2','t');
insert into course values('107401','讲座','1','e');
insert into course values('103011','数字媒体技术','2','t');
insert into course values('105007','写作1','1','e');
insert into course values('102014','会计学','2','t');
insert into course values('108004','操作系统','3','t');
insert into course values('101013','大学英语','2','t');
insert into course values('106027','国家地理','1','e');
insert into course values('102033','女性学导论','2','t');
insert into course values('104008','数据库设计','3','t');

# department
# d_no,department,major
insert into department values('10101','计算机系','数字媒体技术');
insert into department values('10102','计算机系','移动互联网技术');
insert into department values('10301','儿童发展与教育学院','学前教育');
insert into department values('10501','女性学系','女性学');
insert into department values('10402','外语系','英语');
insert into department values('10702','艺术学院','视觉传达设计');
insert into department values('10701','艺术学院','服装设计');
insert into department values('10603','管理学院','人力资源管理');
insert into department values('10903','文化传播学院','网络与新媒体');
insert into department values('10801','法学院','法学');

# student
# s_no,s_name,s_class,s_pro,s_feature
insert into student values('170101008','姜澜','1701','10101','null');
insert into student values('170801032','徐正航','1704','10801','null');
insert into student values('160501021','赵三','1602','10501','班长');
insert into student values('160603033','邓钰','1604','10603','null');
insert into student values('180102047','赵五','1803','10102','null');
insert into student values('180501002','林一','1805','10501','团支书');
insert into student values('170603049','刘越','1704','10603','null');
insert into student values('190801052','王越','1901','10801','null');
insert into student values('170101024','张越','1701','10101','null');
insert into student values('180102006','林五','1803','10102','null');
insert into student values('170101026','于越','1701','10101','null');

# extra_name
# no,extra,detail,name,max,min
insert into extra_name values(1,'me_etr','1','助人为乐','2','0')
insert into extra_name values(2,'me_etr','2','志愿服务活动','2','0')
insert into extra_name values(3,'me_etr','3','集体活动','2','0')
insert into extra_name values(4,'me_etr','4','学生干部','3','0')
insert into extra_name values(5,'me_etr','5','发布作品','2','0')
insert into extra_name values(6,'me_etr','6','献血','2','0')
insert into extra_name values(7,'me_etr','7','获奖','2','0')
insert into extra_name values(8,'me_etr','8','先进集体','2','0')
insert into extra_name values(9,'me_etr','9','各项活动','3','0')
insert into extra_name values(10,'me_etr','10','假期值班','1.5','0')
insert into extra_name values(11,'me_mns','11','卫生差','1','0')
insert into extra_name values(12,'me_mns','12','无故晚归','0.5','0')
insert into extra_name values(13,'pe_etr','13','校级以上运动会','2','0')
insert into extra_name values(14,'pe_etr','14','校级运动会','1','0')
insert into extra_name values(15,'pe_etr','15','学校体育比赛','2','0')
insert into extra_name values(16,'pe_etr','16','趣味运动会','1','0')
insert into extra_name values(17,'ie_etr','17','发表学术论文','5','0')
insert into extra_name values(18,'ie_etr','18','学术/学科竞赛','5','0')
insert into extra_name values(19,'ie_etr','19','暑期社会实践','1.5','0')

# extra
# no,year,s_no,extra,detail,remarks,s_point,g_point,status,pass

# '2018~2019','170101008'
insert into extra values(null,'2018~2019','170101008','me_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101008','me_etr','2',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101008','me_etr','6',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101008','me_etr','4',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101008','me_etr','8',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101008','me_etr','1',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101008','me_mns','1',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101008','ie_etr','1',null,'5','5','0','0');
insert into extra values(null,'2018~2019','170101008','ie_etr','2',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101008','ie_etr','3',null,'3','3','0','0');
#
insert into extra values(null,'2018~2019','170101008','pe_etr','1',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101008','pe_etr','3',null,'1','1','0','0');





# '2018~2019','170101024'
insert into extra values(null,'2018~2019','170101024','me_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101024','me_etr','2',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101024','me_etr','3',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101024','me_etr','7',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101024','me_etr','8',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101024','me_etr','1',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101024','me_mns','2',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101024','ie_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101024','ie_etr','2',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101024','ie_etr','3',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101024','pe_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101024','pe_etr','2',null,'1','1','0','0');



# '2018~2019','170101026'
insert into extra values(null,'2018~2019','170101026','me_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101026','me_etr','10',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101026','me_etr','6',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101026','me_etr','7',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101026','me_etr','8',null,'2','2','0','0');
insert into extra values(null,'2018~2019','170101026','me_etr','3',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101026','me_mns','1',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101026','ie_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101026','ie_etr','2',null,'3','3','0','0');
insert into extra values(null,'2018~2019','170101026','ie_etr','3',null,'1','1','0','0');
#
insert into extra values(null,'2018~2019','170101026','pe_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','170101026','pe_etr','2',null,'1','1','0','0');


# other values
insert into extra values(null,'2018~2019','180102047','ie_etr','1',null,'5','5','0','0');
insert into extra values(null,'2017~2018','170801032','me_etr','2',null,'2','2','0','0');
insert into extra values(null,'2016~2017','160501021','me_etr','6',null,'2','2','0','0');
insert into extra values(null,'2016~2017','160603033','me_etr','4',null,'1','1','0','0');
insert into extra values(null,'2018~2019','180102047','ie_etr','1',null,'5','5','0','0');
insert into extra values(null,'2018~2019','180501002','ie_etr','2',null,'1','1','0','0');
insert into extra values(null,'2017~2018','170603049','ie_etr','1',null,'1','1','0','0');
insert into extra values(null,'2018~2019','190801052','ie_etr','3',null,'3','3','0','0');
insert into extra values(null,'2018~2019','180102006','pe_etr','4',null,'3','3','0','0');

# grade_by_course
# no,year,s_no,crs_no,crs_grade
# 年份格式不对
insert into grade_by_course values(null,'2017','170101008','102023','94');
insert into grade_by_course values(null,'2017','170101008','104008','88');
insert into grade_by_course values(null,'2017','170101008','108004','85');
insert into grade_by_course values(null,'2017','170101008','102033','93');
insert into grade_by_course values(null,'2017','170101008','101013','95');
insert into grade_by_course values(null,'2017','170101024','102023','88');
insert into grade_by_course values(null,'2017','170101024','104008','94');
insert into grade_by_course values(null,'2017','170101024','108004','83');
insert into grade_by_course values(null,'2017','170101024','102033','90');
insert into grade_by_course values(null,'2017','170101024','101013','85');
insert into grade_by_course values(null,'2017','170101026','102023','91');
insert into grade_by_course values(null,'2017','170101026','104008','92');
insert into grade_by_course values(null,'2017','170101026','108004','83');
insert into grade_by_course values(null,'2017','170101026','102033','88');
insert into grade_by_course values(null,'2017','170101026','101013','97');
insert into grade_by_course values(null,'2017','170801032','107401','87');
insert into grade_by_course values(null,'2016','160501021','102014','85');
insert into grade_by_course values(null,'2016','160603033','105007','73');
insert into grade_by_course values(null,'2018','180102047','106027','62');
insert into grade_by_course values(null,'2018','180501002','102033','78');
insert into grade_by_course values(null,'2017','170603049','101013','89');
insert into grade_by_course values(null,'2019','190801052','102023','94');
insert into grade_by_course values(null,'2016','160101024','107401','75');
insert into grade_by_course values(null,'2018','180102006','106027','77');
