# **SQL3** **查询结果去重**

## 描述

题目：现在运营需要查看用户来自于哪些学校，请从用户信息表中取出学校的去重数据。

示例:user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

代码：

```sql
SELECT university 
FROM user_profile 
GROUP BY  UNIVERSITY
```

