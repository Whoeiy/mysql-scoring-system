[TOC]

# 学生表 student

1. <u>学号</u> *s_no*
   - char(10)
   - PRIMARY KEY
2. 姓名 *s_name*：varchar(10)
3. 班级 *s_class*：char(5)——‘1701‘



# 测评总表 summary

1. <u>序号</u> *no*
   - int
   - auto_increment 
   - PRIMARY KEY
2. 学号 *s_no*
   - char(10)
3. 德育 *s_me*
   - char(5)
4. 智育 *s_ie*
   - char(5)
5. 体育 *s_pe*
   - char(5)
6. 总分 *s_total*
   - char(5)
7. 学习成绩 *s_grade*
   - char(5)



# 德育表 moral

1. <u>序号</u> *no*

   - int
   - auto_increment 
   - PRIMARY KEY

2. 学号 *s_no*

   - char(10)
   - **FOREIGN KEY**

3. 基础分 *me_basic*

   - int

   - 有default值

4. 加分项总分 *me_extra*

   - char(5)

5. 减分项总分 *me_minus*

   - char(5)

6. 总分 *me_total*

   - char(5)
   - 总分 = 加分总分 - 减分总分



- 加分项总分可以调用储存过程传入？



### 德育加分项统计表 me_extra

1. <u>序号</u> *no*

   - int
   - auto_increment 
   - PRIMARY KEY

2. 学号 *s_no*

   - char(10)
   - **FOREIGN KEY**

3. 助人为乐 *me_etr_1*

   - char(5)

   - 基础分：1分
   - 最高分：2分

4. 志愿服务活动 *me_etr_2*

   - char(5)

   - 基础分：0.5分？
   - 最高分：2分

5. 集体活动 *me_etr_3*

   - char(5)

   - 基础分
   - 最高分：2分

6. 学生干部 *me_etr_4*

   - char(5)

   - 可能分值：0，2，2.5，3分
   - 最高分：3分

7. 发表作品(?) *me_etr_5*

   - char(5)

   - 可能分值：0，0.5，1，1.5，2分
   - 最高分：2分

8. 献血 *me_etr_6*

   - char(5)

   - 可能分值：0，1.5分

9. 获奖 *me_etr_7*

   - char(5)

   - 可能分值：0，1，1.5，2分
   - 最高分：有吗？

10. 先进集体 *me_etr_8*

   - char(5)

   - 可能分值：0，0.5，1，1.5分
   - 最高分：有吗？

11. 各项活动 *me_etr_9*

    - char(5)

    - 可能分值：0，0.2的倍数，0.5倍数
    - 最高分：3分
    - 自行补充的活动每项0.2分

12. 假期值班 *me_etr_10*

    - char(5)

    - 可能分值：0，0.5，1，1.5分
    - 最高分：1.5分（根据身份而不同）？

13. 加分总分 *me_extra*

    - char(5)
    - me_extra = me_etr_1+...+me_etr_10







### 德育减分项统计表 me_minus

1. <u>序号</u> *no*

   - int
   - auto_increment 
   - PRIMARY KEY

2. 学号 *s_no*

   - char(10)
   - **FOREIGN KEY**

3. 卫生差 *me_mns_1*

   - char(5)

   - 可能分值：0，0.5，1分
   - 有上限吗

4. 无故晚归 *me_mns_2*
   - char(5)
   - 可能分值：0，0.5的倍数
   - 每被登记一次扣去0.5分
   - 有上限吗？应该没有

5. 扣分总分 *me_minus*

   - char(5)
   - me_minus = me_mns_1 + me_mns_2



# 智育表 intellectual

1. <u>序号</u> *no*

   - int
   - auto_increment 
   - PRIMARY KEY

2. 学号 *s_no*

   - char(10)
   - **FOREIGN KEY**

3. 学习成绩——最后得分 *s_studyscore*

   - char(5)

   - 考试课平均分、考查课平均分、总分均与**二级表学习成绩总表**关联
   - 最后得分不超过60分
   - <u>**最后得分 = 总分 * 0.65**</u>

4. 学术成果加分 *ie_extra*

   - char(5)

5. 总分 *ie_total*

   - char(5)



## 学术成果加分项统计表 ie_extra

1. <u>序号</u> *no*

   - int
   - auto_increment 
   - PRIMARY KEY
2. 学号 *s_no*

   - char(10)
   - **FOREIGN KEY**
3. 发表学术论文 *ie_etr_1*

   - char(5)
   - 每次加3分
4. 学术/学科竞赛 *ie_etr_2*

   - char(5)

   - 可能分值：0，2的倍数，1.5的倍数
5. 暑期社会实践 *ie_etr_3*

   - char(5)
- 可能分值：0，1，1.5 
6. 学术成果加分总分 *ie_extra*
   - char(5)
   - ie_extra = ie_etr_1+...+ie_etr_3



##学习成绩总表 grade

1. <u>序号</u> *no*
   - int
   - auto_increment 
   - PRIMARY KEY
2. 学号 *s_no*
   - char(10)
   - **FOREIGN KEY**
3. 考试课总分 *s_test_total*
   - char(5)
   - *s_test_total*=
4. 考试课**平均分** *s_test_avg*
   - char(5)
   - *s_test_avg* = s_test_total / 
5. 考查课总分 *s_exam_total*
   - char(5)
   - s_exam_total=
6. 考查课**平均分** *s_exam_avg*
   - char(5)
   - *s_exam_avg*=
7. 总分 *s_grade*
   - char(5)
   - 总分 = 考试课平均分 * 0.6 + 考查课平均分 * 0.4



### 课程表 course

1. <u>课程号</u> *crs_no*

   - char(15)
   - PRIMARY KEY

2. 课程名 *crs_name*

   - char(20)

3. 课程类型 *crs_type*

   - char(5)

   - t：考试课

     e：考查课



### 学生各科成绩表 grade_by_course

1. <u>序号</u> *no*
   - int
   - auto_increment 
   - PRIMARY KEY
2. 学号 *s_no*
   - char(10)
   - **FOREIGN KEY**
3. 课程号 crs_no
   - char(15)
   - **FOREIGN KEY**
4. 成绩 *crs_grade*
   - char(5)



# 体育表 physical

1. <u>序号</u> *no*
   - int
   - auto_increment 
   - PRIMARY KEY
2. 学号 *s_no*
   - char(10)
   - **FOREIGN KEY**
3. 基础分 *pe_basic*
   - char(5)
   - <=49：5
   - \>=50：6
4. 体质健康测量 *pe_test*
   - char(5)
   - 51~59：0.5
   - 60~79：1
   - \>=80：2
5. 体育加分项 *pe_extra*
   - char(5)
6. 总分 *pe_total*
   - char(5)



### 体育加分项统计表 pe_extra

1. <u>序号</u> *no*

   - int
   - auto_increment 
   - PRIMARY KEY
2. 学号 *s_no*

   - char(10)
   - **FOREIGN KEY**
3. 校级以上运动会 *pe_etr_1*

   - char(5)
   - 可能分值：2，1，0.5
   - 最高分：？
   - 累加吗？
4. 校级运动会 *pe_etr_2*

   - char(5)

   - 可能分值：1，0.5
   - 最高分：？
   - 累加吗？
5. 学校体育比赛 *pe_etr_3*

   - char(5)
   - 可能分值：1，0.5

   - 累加吗？
   - 获得名次最高加1分，未获得名次最高加0.5
6. 趣味运动会 *pe_etr_4*

   - char(5)
   - 可能分值：1，0.5
   - 最高分：1分
7. 体育加分项总分 *pe_extra*
   - char(5)
   - pe_extra = pe_etr_1+...+ie_etr_4



# 问题

1. 每个表的形式（列数）必须同Excel表格一致吗？学习成绩总表可以没有考试课各科目详细的成绩吗？
2. 这个系统面向谁呢？面向学生（无需输入课程成绩，系统自动导入）？基本是输入项目多少的问题。
3. 有审核机制吗？