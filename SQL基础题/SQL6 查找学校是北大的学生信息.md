# **SQL6** **查找学校是北大的学生信息**

## 描述

题目：现在运营想要筛选出所有北京大学的学生进行用户调研，请你从用户信息表中取出满足条件的数据，结果返回设备id和学校。

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

代码：

```sql
SELECT device_id,university 
FROM user_profile
WHERE university="北京大学"
```

