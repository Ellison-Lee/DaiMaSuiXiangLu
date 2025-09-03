# **SQL2** **查询多列**

## 描述

题目：现在运营同学想要用户的设备id对应的性别、年龄和学校的数据，请你取出相应数据

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | Zhejiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

代码：

```sql
SELECT device_id,gender,age,university FROM user_profile
```

