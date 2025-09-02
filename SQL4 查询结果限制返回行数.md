# **SQL4** **查询结果限制返回行数**

## 描述

题目：现在运营只需要查看前2个用户明细设备ID数据，请你从用户信息表 user_profile 中取出相应结果。

示例：

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

代码：

```sql
SELECT device_id
FROM user_profile
LIMIT 2
```

