# SQL语法基础

### **SQL 基础查询**

在 SQL 中，`SELECT` 语句是最基本的查询语句，用于从数据库表中检索数据。通过 `SELECT` 语句，你可以选择表中的所有列或特定列，并根据需要进行过滤和排序。以下是对 `SELECT` 语句的详细讲解。

#### **1. 基本语法**

`SELECT` 语句的基本语法如下：

```sql
SELECT column1, column2, ...
FROM table_name;
```

- **`column1, column2, ...`**：指定要查询的列名。可以是表中的具体列名，也可以是表达式。
- **`table_name`**：指定要查询的表名。

#### **2. 查询所有列**

如果你想查询表中的所有列，可以使用 `*` 通配符。`*` 表示选择表中的所有列。

```sql
SELECT * FROM table_name;
```

这条语句会返回 `table_name` 表中的所有列和所有行。

#### **3. 查询特定列**

如果你想查询表中的特定列，可以明确指定列名。例如：

```sql
SELECT first_name, last_name, salary
FROM table_name;
```

这条语句会返回 `table_name` 表中的 `first_name`、`last_name` 和 `salary` 列的所有行。

#### **4. 使用别名**

在查询时，可以为列或表指定别名，使查询结果更易读。例如：

```sql
SELECT first_name AS name, last_name AS surname, salary
FROM table_name AS tn;
```

这条语句会返回 `employees` 表中的 `first_name` 和 `last_name` 列，并将它们分别重命名为 `name` 和 `surname`。同时，表 `table_name` 被重命名为 `tn`。

### **示例**

假设 `employees` 表的数据如下：

| employee_id | first_name | last_name | salary | department_id |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |

#### **1. 查询所有列**

查询 `employees` 表中的所有列：

```sql
SELECT * 
FROM employees;
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |

#### **2. 查询特定列**

查询 `employees` 表中的 `first_name`、`last_name` 和 `salary` 列：

```sql
SELECT first_name, last_name, salary 
FROM employees;
```

**结果：**

| first_name | last_name | salary |
| ------- | ------- | ----- |
| Alice   | Smith   | 60000 |
| Bob     | Johnson | 70000 |
| Charlie | Brown   | 50000 |

#### **3. 使用别名**

查询 `employees` 表中的 `first_name` 和 `last_name` 列，并为它们分别指定别名 `fname` 和 `lname`：

```sql
SELECT first_name AS fname, last_name AS lname FROM employees;
```

**结果：**

| fname | lname |
| ------- | ------- |
| Alice   | Smith   |
| Bob     | Johnson |
| Charlie | Brown   |

# 简单处理查询结果

前面章节中的SELECT 语句都是返回所有匹配的行。但是如果你想对结果进行简单处理，你应该怎么办呢？

#### **1. 查询结果的过滤**

使用 `WHERE` 子句可以根据条件过滤查询结果。

#### **语法：**

```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

- `condition`：限制条件。

#### **2. 查询结果的排序**

使用 `ORDER BY` 子句可以根据指定的列对查询结果进行排序。

#### **语法：**

```sql
SELECT column1, column2, ...
FROM table_name
ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...;
```

- `column1 [ASC|DESC]`：按照column1 [升序|降序]进行排序，若不加`ASC`或者`DESC`则默认升序。

#### **3. 查询结果的去重**

使用 `DISTINCT` 关键字可以去除查询结果中的重复行。

#### **语法：**

```sql
SELECT DISTINCT column1, column2, ...
FROM table_name;
```

- 对`column1, column2, ...`列中的重复行进行去除。

#### **4. 查询结果的限制**

在某些数据库系统中，可以使用 `LIMIT` 子句限制查询结果的行数。

#### **语法：**

```sql
SELECT column1, column2, ...
FROM table_name
LIMIT n;
```

- 限制输出`n`行。

### **示例**

假设 `employees` 表的数据如下：

| employee_id | first_name | last_name | salary | department_id |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |
| 4    | David   | Davis   | 80000 | 102  |
| 5    | Eve     | White   | 90000 | 103  |

#### 查询结果的过滤

查询 `employees` 表中工资大于 60000 的员工。

```sql
SELECT * 
FROM employees
WHERE salary > 60000;
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ----- | ------- | ----- | ---- |
| 2    | Bob   | Johnson | 70000 | 101  |
| 4    | David | Davis   | 80000 | 102  |
| 5    | Eve   | White   | 90000 | 103  |

#### 查询结果的排序

查询 `employees` 表中的所有员工，并按工资降序排序。

```sql
SELECT * 
FROM employees
ORDER BY salary DESC;
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ------- | ------- | ----- | ---- |
| 5    | Eve     | White   | 90000 | 103  |
| 4    | David   | Davis   | 80000 | 102  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 1    | Alice   | Smith   | 60000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |

#### 查询结果的去重

查询 `employees` 表中所有（不重复）的部门 ID。

```sql
SELECT DISTINCT department_id 
FROM employees;
```

**结果：**

| department_id |
| ---- |
| 101  |
| 102  |
| 103  |

#### 查询结果的限制

查询 `employees` 表中工资最高的 3 名员工。

```sql
SELECT * 
FROM employees
ORDER BY salary DESC
LIMIT 3;
```

- 把工资降序排序，然后输出前三个。

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ----- | ------- | ----- | ---- |
| 5    | Eve   | White   | 90000 | 103  |
| 4    | David | Davis   | 80000 | 102  |
| 2    | Bob   | Johnson | 70000 | 101  |

### **SQL 基础排序 - ORDER BY 子句**

在 SQL 中，`ORDER BY` 子句用于对查询结果进行排序。通过 `ORDER BY` 子句，你可以根据一个或多个列对结果集进行升序或降序排列。这在处理大量数据时非常有用，可以帮助你更清晰地查看和分析数据。

#### **1. `ORDER BY` 子句的使用**

`ORDER BY` 子句的基本语法如下：

```sql
SELECT column1, column2, ...
FROM table_name
ORDER BY column1 [ASC|DESC];
```

- **`column1`**：指定要排序的列名。
- **`ASC`**：表示按升序排序（默认值）。
- **`DESC`**：表示按降序排序。

#### **2. 多列排序**

`ORDER BY` 子句也可以根据多个列进行排序。语法如下：

```sql
ORDER BY column1 ASC, column2 DESC;
```

### 示例

假设有一个 `employees` 表，包含以下数据：

| employee_id | first_name | last_name | salary | department_id |
| ----------- | ---------- | --------- | ------ | ------------- |
| 1           | Alice      | Smith     | 60000  | 101           |
| 2           | Bob        | Johnson   | 70000  | 101           |
| 3           | Charlie    | Brown     | 50000  | 102           |
| 4           | David      | Davis     | 80000  | 102           |
| 5           | Eve        | White     | 90000  | 103           |

#### **单列排序**

查询所有员工，并按工资升序排序：

```sql
SELECT employee_id, first_name, last_name, salary
FROM employees
ORDER BY salary ASC;
```

**结果：**

| employee_id | first_name | last_name | salary |
| ----------- | ---------- | --------- | ------ |
| 3           | Charlie    | Brown     | 50000  |
| 1           | Alice      | Smith     | 60000  |
| 2           | Bob        | Johnson   | 70000  |
| 4           | David      | Davis     | 80000  |
| 5           | Eve        | White     | 90000  |

查询所有员工，并按工资降序排序：

```sql
SELECT employee_id, first_name, last_name, salary
FROM employees
ORDER BY salary DESC;
```

**结果：**

| employee_id | first_name | last_name | salary |
| ---- | ------- | ------- | ----- |
| 5    | Eve     | White   | 90000 |
| 4    | David   | Davis   | 80000 |
| 2    | Bob     | Johnson | 70000 |
| 1    | Alice   | Smith   | 60000 |
| 3    | Charlie | Brown   | 50000 |

#### **多列排序**

查询所有员工，并先按部门 ID 升序排序，再按工资降序排序：

```sql
SELECT employee_id, first_name, last_name, salary, department_id
FROM employees
ORDER BY department_id ASC, salary DESC;
```

**结果：**

employee_id first_name last_name salary department_id

|      |         |         |       |      |
| ---- | ------- | ------- | ----- | ---- |
| 2    | Bob     | Johnson | 70000 | 101  |
| 1    | Alice   | Smith   | 60000 | 101  |
| 4    | David   | Davis   | 80000 | 102  |
| 3    | Charlie | Brown   | 50000 | 102  |
| 5    | Eve     | White   | 90000 | 103  |

#### **使用别名排序**

你也可以在 `ORDER BY` 子句中使用列的别名进行排序。例如：

```sql
SELECT employee_id, first_name, last_name, salary AS emp_salary
FROM employees
ORDER BY emp_salary DESC;
```

**结果：**

employee_id first_name last_name emp_salary

|      |         |         |       |
| ---- | ------- | ------- | ----- |
| 5    | Eve     | White   | 90000 |
| 4    | David   | Davis   | 80000 |
| 2    | Bob     | Johnson | 70000 |
| 1    | Alice   | Smith   | 60000 |
| 3    | Charlie | Brown   | 50000 |

### **SQL 基础操作符**

在 SQL 中，操作符用于在 `WHERE` 子句中指定条件，以过滤查询结果。以下是一些常用的基础操作符及其用法。

#### **1. 比较运算符**

比较运算符用于比较两个值，返回布尔值（`TRUE` 或 `FALSE`）。常用的比较运算符包括：

- `=`：等于
- `<>` 或 `!=`：不等于
- `>`：大于
- `<`：小于
- `>=`：大于等于
- `<=`：小于等于

#### **2. 逻辑运算符**

逻辑运算符用于组合多个条件，返回布尔值。常用的逻辑运算符包括：

- `AND`：逻辑与
- `OR`：逻辑或
- `NOT`：逻辑非

#### **3. `IN` 操作符**

`IN` 操作符用于指定多个可能的值，返回布尔值。语法如下：

```sql
SELECT column1, column2 
FROM table_name 
WHERE column1 IN (value1, value2, ...);
```

#### **4. `BETWEEN` 操作符**

`BETWEEN` 操作符用于指定一个范围，返回布尔值。语法如下：

```sql
SELECT column1, column2 
FROM table_name 
WHERE column1 BETWEEN value1 AND value2;
```

#### **5. `LIKE` 操作符**

`LIKE` 操作符用于模式匹配，返回布尔值。语法如下：

```sql
SELECT column1, column2 
FROM table_name 
WHERE column1 LIKE pattern;
```

- `%`：匹配任意数量的字符（包括零个字符）。
- `_`：匹配单个字符。

### **示例**

假设有一个 `employees` 表，包含以下数据：

employee_id first_name last_name salary department_id

|      |         |         |       |      |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |
| 4    | David   | Davis   | 80000 | 102  |
| 5    | Eve     | White   | 90000 | 103  |

#### **比较运算符**

查询工资大于 60000 的员工：

```sql
SELECT * 
FROM employees
WHERE salary > 60000;
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ----- | ------- | ----- | ---- |
| 2    | Bob   | Johnson | 70000 | 101  |
| 4    | David | Davis   | 80000 | 102  |
| 5    | Eve   | White   | 90000 | 103  |

#### **示例 2：逻辑运算符**

查询工资大于 60000 且部门 ID 为 102 的员工：

```sql
SELECT * 
FROM employees
WHERE salary > 60000 AND department_id = 102;
```

**结果：**

employee_id first_name last_name salary department_id

|      |       |       |       |      |
| ---- | ----- | ----- | ----- | ---- |
| 4    | David | Davis | 80000 | 102  |

#### **示例 3：`IN` 操作符**

查询部门 ID 为 101 或 103 的员工：

```sql
SELECT * 
FROM employees
WHERE department_id IN (101, 103);
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ----- | ------- | ----- | ---- |
| 1    | Alice | Smith   | 60000 | 101  |
| 2    | Bob   | Johnson | 70000 | 101  |
| 5    | Eve   | White   | 90000 | 103  |

#### **示例 4：`BETWEEN` 操作符**

查询工资在 50000 到 70000 之间的员工：

```sql
SELECT * 
FROM employees
WHERE salary BETWEEN 50000 AND 70000;
```

**结果：**

employee_id first_name last_name salary department_id

|      |         |         |       |      |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |

#### **示例 5：`LIKE` 操作符**

查询名字以字母 "A" 开头的员工：

```sql
SELECT * 
FROM employees
WHERE first_name LIKE 'A%';
```

**结果：**

employee_id first_name last_name salary department_id

|      |       |       |       |      |
| ---- | ----- | ----- | ----- | ---- |
| 1    | Alice | Smith | 60000 | 101  |

### **SQL 高级操作符**

在 SQL 中，高级操作符可以帮助你更灵活和高效地操作数据库中的数据。以下是一些常见的高级操作符及其用法。

#### **1. `IS NULL` 和 `IS NOT NULL`**

`IS NULL` 和 `IS NOT NULL` 用于检查一个列是否为 `NULL` 或不为 `NULL`。

- **`IS NULL`**：检查列是否为 `NULL`。
- **`IS NOT NULL`**：检查列是否不为 `NULL`。

#### **2. `EXISTS` 操作符**

`EXISTS` 操作符用于检查子查询是否返回任何行。如果子查询返回至少一行，则 `EXISTS` 条件为 `TRUE`。

#### **语法：**

```sql
SELECT column1, column2 
FROM table_name
WHERE EXISTS (SELECT 1 FROM another_table WHERE condition);
```

#### **3. `ALL` 和 `ANY` 操作符**

`ALL` 和 `ANY` 操作符用于比较一个值与子查询返回的值集。

- **`ALL`**：与子查询返回的所有值进行比较。
- **`ANY`**：与子查询返回的任意一个值进行比较。

#### **语法：**

```sql
SELECT column1, column2 
FROM table_name
WHERE column1 > ALL (SELECT column1 FROM another_table WHERE condition);
```

### **示例**

假设有一个 `employees` 表，包含以下数据：

| employee_id | first_name | last_name | salary | department_id |
| :---------: | :--------: | :-------: | :----: | :-----------: |
|      1      |   Alice    |   Smith   | 60000  |      101      |
|      2      |    Bob     |  Johnson  | 70000  |      101      |
|      3      |  Charlie   |   Brown   |  NULL  |      102      |
|      4      |   David    |   Davis   | 80000  |      102      |
|      5      |    Eve     |   White   | 90000  |      103      |

#### **`IS NULL` 和 `IS NOT NULL`**

查询工资为 `NULL` 的员工：

```sql
SELECT * 
FROM employees
WHERE salary IS NULL;
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| :---------: | :--------: | :-------: | :----: | :-----------: |
|      3      |  Charlie   |   Brown   |  NULL  |      102      |

查询工资不为 `NULL` 的员工：

```sql
SELECT * 
FROM employees
WHERE salary IS NOT NULL;
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| :---------: | :--------: | :-------: | :----: | :-----------: |
|      1      |   Alice    |   Smith   | 60000  |      101      |
|      2      |    Bob     |  Johnson  | 70000  |      101      |
|      4      |   David    |   Davis   | 80000  |      102      |
|      5      |    Eve     |   White   | 90000  |      103      |

#### **`EXISTS` 操作符**

现在，我们想查询所有工资不低于表中任何其他员工工资的员工。这里可以使用 `EXISTS` 操作符来实现。

```sql
SELECT *
FROM user_profile e1
WHERE NOT EXISTS (
    SELECT 1
    FROM user_profile e2
    WHERE e2.device_id > e1.device_id
);
```

#### 解释

- 对于每个 e1，子查询查找是否存在工资比 e1 更高的员工（e2.salary > e1.salary）。
- 如果不存在（NOT EXISTS），说明 e1 的工资是最高的。

**结果：**

| employee_id | first_name | last_name | salary |
| :---------: | :--------: | :-------: | :----: |
|      5      |    Eve     |   White   | 90000  |

- 员工 `Eve` 的工资是 `90000`，这是表中最高的工资。
- 其他员工的工资都低于 `90000`，因此 `Eve` 是唯一满足条件的员工。

#### **`ALL` 和 `ANY` 操作符**

假设有一个 `employees` 表和一个 `departments` 表，我们想查询工资高于所有部门平均工资的员工。

```sql
SELECT * 
FROM employees e
WHERE salary > ALL (
    SELECT AVG(salary) 
    FROM employees 
    GROUP BY department_id
);
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| :---------: | :--------: | :-------: | :----: | :-----------: |
|      4      |   David    |   Davis   | 80000  |      102      |
|      5      |    Eve     |   White   | 90000  |      103      |

`ALL` 和 `ANY` 操作符在处理子查询时非常有用，尤其是在需要与子查询返回的值集进行比较时。



# **计算函数**

在 SQL 中，计算函数用于对数据进行各种数学计算和统计分析。这些函数可以分为聚合函数和取整函数。以下是对这些函数的详细讲解。

### 知识点

#### **1. 聚合函数**

聚合函数用于对一组数据进行计算，返回一个单一的结果。以下是一些常用的聚合函数：

- **`SUM(column)`**：计算数值列的总和。
- **`AVG(column)`**：计算数值列的平均值。
- **`COUNT(column)`**：计算列中非 `NULL` 值的数量。
- **`MAX(column)`**：返回列中的最大值。
- **`MIN(column)`**：返回列中的最小值。

#### **2. 取整函数**

取整函数用于对数值进行取整操作。以下是一些常用的取整函数：

- **`CEIL(number)`** 或 **`CEILING(number)`**：返回大于或等于给定数值的最小整数。
- **`FLOOR(number)`**：返回小于或等于给定数值的最大整数。
- **`ROUND(number, decimals)`**：将数值四舍五入到指定的小数位数。

另：在 SQL 中，`CAST` 函数用于将一个值显式地转换为指定的数据类型。虽然 `CAST` 本身不是专门的取整函数，但它可以与取整函数结合使用，以实现更灵活的数据类型转换和取整操作。

```sql
CAST(expression AS data_type)
```

- **`expression`**：要转换的值或表达式。
- **`data_type`**：目标数据类型，例如 `SIGNED`、`DECIMAL`、`VARCHAR` 等。

### 示例

##### **示例 1：聚合函数**

假设有一个 `employees` 表，包含以下数据：

employee_id first_name last_name salary department_id

|      |         |         |       |      |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | NULL  | 102  |
| 4    | David   | Davis   | 80000 | 102  |
| 5    | Eve     | White   | 90000 | 103  |

计算所有员工的总工资：

```sql
SELECT SUM(salary) AS total_salary
FROM employees;
```

**结果：**

| total_salary |
| ------ |
| 300000 |

计算所有员工的平均工资：

```sql
SELECT AVG(salary) AS average_salary
FROM employees;
```

**结果：**

| average_salary |
| ----- |
| 75000 |

计算非 `NULL` 工资的员工数量：

```sql
SELECT COUNT(salary) AS count_salary
FROM employees;
```

**结果：**

| count_salary |
| ---- |
| 4    |

计算最高工资：

```sql
SELECT MAX(salary) AS max_salary
FROM employees;
```

**结果：**

| max_salary |
| ----- |
| 90000 |

计算最低工资：

```sql
SELECT MIN(salary) AS min_salary
FROM employees;
```

**结果：**

| min_salary |
| ----- |
| 60000 |

##### **示例 2：取整函数**

假设 `employees` 表包含以下数据：

employee_id first_name last_name salary

|      |         |         |       |
| ---- | ------- | ------- | ----- |
| 1    | Alice   | Smith   | 60000 |
| 2    | Bob     | Johnson | 70000 |
| 3    | Charlie | Brown   | 55000 |
| 4    | David   | Davis   | 85000 |
| 5    | Eve     | White   | 95000 |

**SQL 查询**

我们将使用 `CEIL`、`FLOOR` 和 `ROUND` 函数来计算每个员工工资的上限值、下限值和四舍五入值（单位：万元）。

```sql
SELECT 
    first_name, 
    last_name, 
    CEIL(salary / 10000) AS ceil_salary,
    FLOOR(salary / 10000) AS floor_salary,
    ROUND(salary / 10000) AS round_salary
FROM 
    employees;
```

**执行结果**

执行上述查询后，结果如下：

| first_name | last_name | ceil_salary | floor_salary | round_salary |
| ------- | ------- | ---- | ---- | ---- |
| Alice   | Smith   | 7    | 6    | 6    |
| Bob     | Johnson | 8    | 7    | 7    |
| Charlie | Brown   | 6    | 5    | 6    |
| David   | Davis   | 9    | 8    | 9    |
| Eve     | White   | 10   | 9    | 10   |

1. **`CEIL(salary / 10000)`**：

   - 计算工资除以 10000 后的上限值，即向上取整。
   - 例如，`60000 / 10000 = 6.0`，向上取整为 7。
   - `55000 / 10000 = 5.5`，向上取整为 6。

2. **`FLOOR(salary / 10000)`**：

   - 计算工资除以 10000 后的下限值，即向下取整。
   - 例如，`60000 / 10000 = 6.0`，向下取整为 6。
   - `55000 / 10000 = 5.5`，向下取整为 5。

3. **`ROUND(salary / 10000)`**：

   - 计算工资除以 10000 后的四舍五入值。
   - 例如，`60000 / 10000 = 6.0`，四舍五入为 6。
   - `55000 / 10000 = 5.5`，四舍五入为 6。
   - `85000 / 10000 = 8.5`，四舍五入为 9。

   # **分组查询**

   在 SQL 中，分组查询是数据处理和分析中非常重要的部分。通过 `GROUP BY` 子句，可以将数据按指定列分组，并对每个分组进行聚合计算、排序、过滤等操作。这在统计分析、报表生成和数据汇总中非常有用。以下是对分组查询的详细讲解。

   #### **1. `GROUP BY` 子句的基本语法**

   - `GROUP BY` 子句用于将数据按指定列分组。语法如下：

   ```sql
   SELECT column1, column2, aggregate_function(column3)
   FROM table_name
   GROUP BY column1, column2;
   ```

   - 其中：
     - **`column1, column2`**：指定分组的列。
     - **`aggregate_function(column3)`**：对每个分组进行聚合计算的函数，如 `SUM`、`AVG`、`COUNT`、`MAX`、`MIN` 等。
     - `GROUP BY` 子句可以包含任意数目的列，因而可以对分组进行嵌套， 更细致地进行数据分组。
     - 除聚集计算语句外，`SELECT` 语句中的每一列都必须在 `GROUP BY` 子句 中同时给出。
     - 如果分组列中包含具有 `NULL` 值的行，则 `NULL` 将作为一个分组返回。 如果列中有多行 `NULL` 值，它们将分为一组。
     - `GROUP BY` 子句必须出现在 `WHERE` 子句之后，`ORDER BY` 子句之前。
   - `HAVING` 子句用于对分组后的结果进行过滤。语法如下：

   ```sql
   SELECT column1, aggregate_function(column2)
   FROM table_name
   GROUP BY column1
   HAVING condition;
   ```

   - `ORDER BY` 子句用于对分组后的结果进行排序。语法如下：

   ```sql
   SELECT column1, aggregate_function(column2)
   FROM table_name
   GROUP BY column1
   ORDER BY column2[ASC|DESC];
   ```

   #### **2. 示例**

   假设有一个 `employees` 表，包含以下数据：

   employee_id first_name last_name salary department_id

   |      |         |         |       |      |
   | ---- | ------- | ------- | ----- | ---- |
   | 1    | Alice   | Smith   | 60000 | 101  |
   | 2    | Bob     | Johnson | 70000 | 101  |
   | 3    | Charlie | Brown   | 50000 | 102  |
   | 4    | David   | Davis   | 80000 | 102  |
   | 5    | Eve     | White   | 90000 | 103  |

   **1：按单列分组**

   按部门分组，计算每个部门的平均工资：

   ```sql
   SELECT department_id, AVG(salary) AS average_salary
   FROM employees
   GROUP BY department_id;
   ```

   **结果：**

   | department_id | average_salary |
   | ---- | ----- |
   | 101  | 65000 |
   | 102  | 65000 |
   | 103  | 90000 |

   **2：按多列分组**

   按部门和工资范围分组，计算每个部门中不同工资范围的员工数量：

   ```sql
   SELECT 
       department_id, 
       CASE 
           WHEN salary BETWEEN 50000 AND 60000 THEN '50000-60000'
           WHEN salary BETWEEN 60001 AND 70000 THEN '60001-70000'
           WHEN salary BETWEEN 70001 AND 80000 THEN '70001-80000'
           WHEN salary BETWEEN 80001 AND 90000 THEN '80001-90000'
           ELSE '90000+'
       END AS salary_range,
       COUNT(*) AS employee_count
   FROM employees
   GROUP BY department_id, salary_range;
   ```

   **结果：**

   | department_id | salary_range | employee_count |
   | ---- | ----------- | ---- |
   | 101  | 60001-70000 | 2    |
   | 102  | 50000-60000 | 1    |
   | 102  | 70001-80000 | 1    |
   | 103  | 90000+      | 1    |

   **3：结合 `HAVING` 子句**

   按部门分组，计算每个部门的总工资，并过滤出总工资大于 120000 的部门：

   ```sql
   SELECT department_id, SUM(salary) AS total_salary
   FROM employees
   GROUP BY department_id
   HAVING SUM(salary) > 120000;
   ```

   **结果：**

   | department_id | total_salary |
   | ---- | ------ |
   | 102  | 130000 |
   | 103  | 90000  |

   **4：结合 `ORDER BY` 子句**

   按部门分组，计算每个部门的平均工资，并按平均工资降序排序：

   ```sql
   SELECT department_id, AVG(salary) AS average_salary
   FROM employees
   GROUP BY department_id
   ORDER BY average_salary DESC;
   ```

   **结果：**

   | department_id | average_salary |
   | ---- | ----- |
   | 103  | 90000 |
   | 102  | 65000 |
   | 101  | 65000 |

# **子查询**

在 SQL 中，子查询（Subquery）是一个嵌套在另一个查询中的查询语句。子查询可以用于在 `SELECT`、`INSERT`、`UPDATE` 或 `DELETE` 语句中提供数据或条件。子查询可以返回单个值、一行、一列或多行多列数据。以下是对子查询的详细讲解。

#### **1. 子查询的基本语法**

子查询的基本语法如下：

```sql
SELECT column1, column2, ...
FROM table_name
WHERE column_name operator (
    SELECT column1, column2, ...
    FROM another_table
    WHERE condition
);
```

- **`column1, column2, ...`**：要查询的列。
- **`table_name`**：主查询的表。
- **`column_name`**：主查询中用于比较的列。
- **`operator`**：比较运算符，如 `=`, `<>`, `>`, `<`, `>=`, `<=`, `IN`, `NOT IN`, `EXISTS`, `NOT EXISTS` 等。
- **`another_table`**：子查询的表。
- **`condition`**：子查询的条件。

#### **2. 子查询的类型**

子查询可以分为以下几种类型：

- **标量子查询**：返回单个值。
- **行子查询**：返回一行数据。
- **列子查询**：返回一列数据。
- **表子查询**：返回多行多列数据。

### 示例

假设有一个 `employees` 表，包含以下数据：

employee_id first_name last_name salary department_id

|      |         |         |       |      |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | 101  |
| 2    | Bob     | Johnson | 70000 | 101  |
| 3    | Charlie | Brown   | 50000 | 102  |
| 4    | David   | Davis   | 80000 | 102  |
| 5    | Eve     | White   | 90000 | 103  |

#### **标量子查询**

查询工资高于平均工资的员工：

```sql
SELECT * 
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);
```

**结果：**

| employee_id | first_name | last_name | salary | department_id |
| ---- | ----- | ----- | ----- | ---- |
| 4    | David | Davis | 80000 | 102  |
| 5    | Eve   | White | 90000 | 103  |

#### **行子查询**

查询工资和部门 ID 与员工 `Alice` 相同的员工：

```sql
SELECT * 
FROM employees
WHERE (salary, department_id) = (
    SELECT salary, department_id
    FROM employees
    WHERE first_name = 'Alice'
);
```

**结果：**

employee_id first_name last_name salary department_id

|      |       |         |       |      |
| ---- | ----- | ------- | ----- | ---- |
| 1    | Alice | Smith   | 60000 | 101  |
| 2    | Bob   | Johnson | 70000 | 101  |

#### **列子查询**

查询工资高于部门 101 的任何员工工资的员工：

```sql
SELECT * 
FROM employees
WHERE salary > ANY (
    SELECT salary
    FROM employees
    WHERE department_id = 101
);
```

**结果：**

employee_id first_name last_name salary department_id

|      |       |       |       |      |
| ---- | ----- | ----- | ----- | ---- |
| 4    | David | Davis | 80000 | 102  |
| 5    | Eve   | White | 90000 | 103  |

#### **表子查询**

查询每个部门工资最高的员工：

```sql
SELECT e1.*
FROM employees e1
WHERE (
    SELECT COUNT(*)
    FROM employees e2
    WHERE e2.department_id = e1.department_id AND e2.salary > e1.salary
) = 0;
```

**结果：**

employee_id first_name last_name salary department_id

|      |       |         |       |      |
| ---- | ----- | ------- | ----- | ---- |
| 2    | Bob   | Johnson | 70000 | 101  |
| 4    | David | Davis   | 80000 | 102  |
| 5    | Eve   | White   | 90000 | 103  |

#### **`EXISTS` 和 `NOT EXISTS`**

查询没有订单的客户：

```sql
SELECT *
FROM customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
);
```

**结果：**

| customer_id | customer_name | customer_email | customer_age |
| ---- | ------- | ------------------------------------------------- | ---- |
| 3    | Charlie | [charlie@example.com](mailto:charlie@example.com) | 22   |

# **链接查询**

在 SQL 中，链接查询（Join Query）是将两个或多个表中的数据组合在一起的查询。通过使用不同的链接类型，可以实现不同的数据组合方式。以下是对链接查询的详细讲解，包括 `INNER JOIN`、`LEFT JOIN`、`RIGHT JOIN` 和 `FULL JOIN`。

假设有两个表 `employees` 和 `departments`，数据如下：

**`employees` 表：**

| employee_id | first_name | last_name | department_id |
| ---- | ------- | ------- | ---- |
| 1    | Alice   | Smith   | 101  |
| 2    | Bob     | Johnson | 102  |
| 3    | Charlie | Brown   | NULL |

**`departments` 表：**

| department_id | department_name |
| ---- | --------- |
| 101  | Sales     |
| 102  | Marketing |

#### **1. `INNER JOIN`**

`INNER JOIN` 是最基本的链接类型，它返回两个表中匹配的记录。`JOIN` 是 `INNER JOIN` 的简写形式。如果某个表中的记录在另一个表中没有匹配的记录，则这些记录不会出现在结果集中。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
INNER JOIN table2
ON table1.column = table2.column;
```

##### **示例：**

查询所有员工及其所属部门：

```sql
SELECT e.employee_id, e.first_name, e.last_name, d.department_name
FROM employees e
INNER JOIN departments d
ON e.department_id = d.department_id;
```

**结果：**

| employee_id | first_name | last_name | department_name |
| ---- | ----- | ------- | --------- |
| 1    | Alice | Smith   | Sales     |
| 2    | Bob   | Johnson | Marketing |

#### **2. `LEFT JOIN`**

`LEFT JOIN` 返回左表（`table1`）中的所有记录，即使右表（`table2`）中没有匹配的记录。如果右表中没有匹配的记录，则结果集中右表的列将包含 `NULL`。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
LEFT JOIN table2
ON table1.column = table2.column;
```

##### **示例：**

查询所有员工及其所属部门，即使某些员工没有分配部门：

```sql
SELECT e.employee_id, e.first_name, e.last_name, d.department_name
FROM employees e
LEFT JOIN departments d
ON e.department_id = d.department_id;
```

**结果：**

| employee_id | first_name | last_name | department_name |
| ---- | ------- | ------- | --------- |
| 1    | Alice   | Smith   | Sales     |
| 2    | Bob     | Johnson | Marketing |
| 3    | Charlie | Brown   | NULL      |

#### **3. `RIGHT JOIN`**

`RIGHT JOIN` 返回右表（`table2`）中的所有记录，即使左表（`table1`）中没有匹配的记录。如果左表中没有匹配的记录，则结果集中左表的列将包含 `NULL`。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
RIGHT JOIN table2
ON table1.column = table2.column;
```

##### **示例：**

查询所有部门及其员工，即使某些部门没有员工：

```sql
SELECT e.employee_id, e.first_name, e.last_name, d.department_name
FROM employees e
RIGHT JOIN departments d
ON e.department_id = d.department_id;
```

**结果：**

| employee_id | first_name | last_name | department_name |
| ---- | ----- | ------- | --------- |
| 1    | Alice | Smith   | Sales     |
| 2    | Bob   | Johnson | Marketing |
| NULL | NULL  | NULL    | NULL      |

#### **4. `FULL JOIN`**

`FULL JOIN` 返回左表和右表中的所有记录，即使某些记录在另一表中没有匹配的记录。如果某表中没有匹配的记录，则结果集中该表的列将包含 `NULL`。需要注意的是，`FULL JOIN` 在某些数据库系统（如 MySQL）中不支持。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
FULL JOIN table2
ON table1.column = table2.column;
```

##### **示例：**

查询所有员工及其所属部门，以及所有部门及其员工：

```sql
SELECT e.employee_id, e.first_name, e.last_name, d.department_name
FROM employees e
FULL JOIN departments d
ON e.department_id = d.department_id;
```

**结果：**

| employee_id | first_name | last_name | department_name |
| ---- | ------- | ------- | --------- |
| 1    | Alice   | Smith   | Sales     |
| 2    | Bob     | Johnson | Marketing |
| 3    | Charlie | Brown   | NULL      |
| NULL | NULL    | NULL    | NULL      |

### **总结**

通过使用不同的链接类型，可以实现不同的数据组合方式：

- **`INNER JOIN`**：返回两个表中匹配的记录。
- **`LEFT JOIN`**：返回左表中的所有记录，右表中没有匹配的记录则为 `NULL`。
- **`RIGHT JOIN`**：返回右表中的所有记录，左表中没有匹配的记录则为 `NULL`。
- **`FULL JOIN`**：返回两个表中的所有记录，不匹配的记录则为 `NULL`。

### **组合查询**

在 SQL 中，组合查询（Combined Queries）允许你将多个查询的结果集合并成一个结果集。这在需要从多个表中检索数据并将其合并显示时非常有用。组合查询通常使用 `UNION` 和 `UNION ALL` 来实现。

#### **1. `UNION`**

`UNION` 用于组合两个或多个 `SELECT` 语句的结果集，并自动去除重复的行。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
UNION
SELECT column1, column2, ...
FROM table2;
```

- **`column1, column2, ...`**：要选择的列，必须在所有查询中具有相同的名称和数据类型。
- **`table1, table2, ...`**：要查询的表。

#### **2. `UNION ALL`**

`UNION ALL` 也用于组合两个或多个 `SELECT` 语句的结果集，但不会去除重复的行。这在需要保留所有重复行时非常有用。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
UNION ALL
SELECT column1, column2, ...
FROM table2;
```

#### **3. `INTERSECT`**

`INTERSECT` 用于返回两个查询结果集的交集，即两个结果集中都存在的记录。需要注意的是，`INTERSECT` 在某些数据库系统（如 MySQL）中不支持。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
INTERSECT
SELECT column1, column2, ...
FROM table2;
```

#### **4. `EXCEPT` 或 `MINUS`**

`EXCEPT`（在 SQL Server 中）或 `MINUS`（在 Oracle 中）用于返回两个查询结果集的差集，即第一个结果集中存在但第二个结果集中不存在的记录。需要注意的是，`EXCEPT` 和 `MINUS` 在某些数据库系统（如 MySQL）中不支持。

##### **语法：**

```sql
SELECT column1, column2, ...
FROM table1
EXCEPT
SELECT column1, column2, ...
FROM table2;
```

##### **示例：**

假设有两个表 `employees` 和 `contractors`，数据如下：

**`employees` 表：**

| employee_id | name | role |
| ---- | ------- | --------- |
| 1    | Alice   | Developer |
| 2    | Bob     | Manager   |
| 3    | Charlie | Designer  |

**`contractors` 表：**

| contractor_id | name | role |
| ---- | ----- | ---------- |
| 1    | David | Consultant |
| 2    | Eve   | Analyst    |
| 3    | Alice | Developer  |

##### **UNION**

将两个表中的数据合并，显示所有员工和承包商的名称和角色：

```sql
SELECT name, role
FROM employees
UNION
SELECT name, role
FROM contractors;
```

**结果：**

| name | role |
| ------- | ---------- |
| Alice   | Developer  |
| Bob     | Manager    |
| Charlie | Designer   |
| David   | Consultant |
| Eve     | Analyst    |

##### **UNION ALL：**

将两个表中的数据合并，显示所有员工和承包商的名称和角色，保留重复行：

```sql
SELECT name, role
FROM employees
UNION ALL
SELECT name, role
FROM contractors;
```

**结果：**

| name | role |
| ------- | ---------- |
| Alice   | Developer  |
| Bob     | Manager    |
| Charlie | Designer   |
| David   | Consultant |
| Eve     | Analyst    |
| Alice   | Developer  |

##### **INTERSECT：**

查询两个表中都存在的记录：

```sql
SELECT name, role
FROM employees
INTERSECT
SELECT name, role
FROM contractors;
```

**结果：**

| name | role |
| ----- | --------- |
| Alice | Developer |

##### **EXCEPT 或 MINUS**

查询 `employees` 表中存在但 `contractors` 表中不存在的记录：

```sql
SELECT name, role
FROM employees
EXCEPT
SELECT name, role
FROM contractors;
```

**结果：**

| name | role |
| ------- | -------- |
| Bob     | Manager  |
| Charlie | Designer |

### **总结**

通过使用 `UNION` 和 `UNION ALL`，可以将多个查询的结果集合并成一个结果集。`UNION` 会自动去除重复的行，而 `UNION ALL` 会保留所有重复行。此外，`INTERSECT` 用于返回两个结果集的交集，`EXCEPT` 或 `MINUS` 用于返回两个结果集的差集。掌握这些组合查询的用法，可以帮助你更灵活地处理和分析数据。

### **条件函数**

在 SQL 中，条件函数用于根据特定条件返回不同的值。这些函数可以帮助你在查询中实现复杂的逻辑，使查询结果更符合业务需求。以下是一些常用的条件函数及其用法。

#### **1. `IF` 函数**

`IF` 函数用于根据条件返回两个值中的一个。它类似于编程语言中的 `if-else` 语句。

##### **语法：**

```sql
IF(condition, true_value, false_value)
```

- **`condition`**：条件表达式。
- **`true_value`**：如果条件为 `TRUE`，则返回的值。
- **`false_value`**：如果条件为 `FALSE`，则返回的值。

#### **2. `CASE` 语句**

`CASE` 语句用于根据多个条件返回不同的值。它类似于编程语言中的 `switch-case` 语句。

##### **语法：**

```sql
CASE
    WHEN condition1 THEN result1
    WHEN condition2 THEN result2
    ...
    ELSE default_result
END
```

#### **3. `COALESCE` 函数**

`COALESCE` 函数用于返回参数列表中的第一个非 `NULL` 值。如果没有非 `NULL` 值，则返回 `NULL`。

##### **语法：**

```sql
COALESCE(value1, value2, ...)
```

#### **4. `NULLIF` 函数**

`NULLIF` 函数用于比较两个值，如果它们相等，则返回 `NULL`，否则返回第一个值。

##### **语法：**

```sql
NULLIF(value1, value2)
```

#### **5. `IFNULL` 函数**

`IFNULL` 用于检查一个值是否为 `NULL`，如果是 `NULL`，则返回一个指定的默认值。

##### **语法**

```sql
IFNULL(expression, default_value)
```

- **`expression`**：要检查的表达式。
- **`default_value`**：如果 `expression` 为 `NULL`，则返回的默认值。

##### **与其他函数的比较**

- **`COALESCE`**：
  - `COALESCE` 函数可以接受多个参数，并返回第一个非 `NULL` 的值。
  - 如果所有参数都是 `NULL`，则返回 `NULL`。
  - 语法：`COALESCE(value1, value2, ...)`。
  - 示例：`COALESCE(salary, 0, 'Not Available')`。
- **`IF`**：
  - `IF` 函数用于根据条件返回两个值中的一个。
  - 语法：`IF(condition, true_value, false_value)`。
  - 示例：`IF(salary IS NULL, 0, salary)`。

#### **示例：**

假设有一个 `employees` 表，包含以下数据：

| employee_id | first_name | last_name | salary |
| ---- | ------- | ------- | ----- |
| 1    | Alice   | Smith   | 60000 |
| 2    | Bob     | Johnson | 70000 |
| 3    | Charlie | Brown   | 50000 |

##### **`IF` 函数**

查询每个员工的工资级别，如果工资大于 60000，则为 "High"，否则为 "Low"：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    salary, 
    IF(salary > 60000, 'High', 'Low') AS salary_level
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | salary | salary_level |
| ---- | ------- | ------- | ----- | ---- |
| 1    | Alice   | Smith   | 60000 | Low  |
| 2    | Bob     | Johnson | 70000 | High |
| 3    | Charlie | Brown   | 50000 | Low  |

##### **`CASE`语句**

查询每个员工的工资级别，如果工资大于 70000，则为 "High"，如果工资在 50000 到 70000 之间，则为 "Medium"，否则为 "Low"：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    salary, 
    CASE 
        WHEN salary > 70000 THEN 'High'
        WHEN salary BETWEEN 50000 AND 70000 THEN 'Medium'
        ELSE 'Low'
    END AS salary_level
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | salary | salary_level |
| ---- | ------- | ------- | ----- | ------ |
| 1    | Alice   | Smith   | 60000 | Medium |
| 2    | Bob     | Johnson | 70000 | Medium |
| 3    | Charlie | Brown   | 50000 | Low    |

##### **`NULLIF`函数**

查询每个员工的工资，如果工资等于 60000，则返回 `NULL`：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    NULLIF(salary, 60000) AS salary
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | salary |
| ---- | ------- | ------- | ----- |
| 1    | Alice   | Smith   | NULL  |
| 2    | Bob     | Johnson | 70000 |
| 3    | Charlie | Brown   | 50000 |

##### **`COALESCE` 函数**

假设有一个 `employees` 表，包含以下数据：

| employee_id | first_name | last_name | salary |
| ---- | ------- | ------- | ----- |
| 1    | Alice   | Smith   | 60000 |
| 2    | Bob     | Johnson | NULL  |
| 3    | Charlie | Brown   | 50000 |

查询每个员工的工资，如果工资为 `NULL`，则返回默认值 0：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    COALESCE(salary, 0) AS salary
FROM 
    employees;
```

**结果：**

employee_id first_name last_name salary

|      |         |         |       |
| ---- | ------- | ------- | ----- |
| 1    | Alice   | Smith   | 60000 |
| 2    | Bob     | Johnson | 0     |
| 3    | Charlie | Brown   | 50000 |

##### **`IFNULL` 函数**

查询每个员工的工资，如果工资为 `NULL`，则返回字符串 "Not Available"：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    IFNULL(salary, 'Not Available') AS salary
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | salary |
| ---- | ------- | ------- | ------------- |
| 1    | Alice   | Smith   | 60000         |
| 2    | Bob     | Johnson | Not Available |
| 3    | Charlie | Brown   | 50000         |

### **总结**

通过使用条件函数，如 `IF`、`CASE`、`IFNULL`、`COALESCE` 和 `NULLIF`，可以在 SQL 查询中实现复杂的逻辑，使查询结果更符合业务需求。这些函数在处理 `NULL` 值、条件判断和多条件分支时非常有用。掌握这些函数的用法，可以帮助你更灵活地处理和分析数据。

# **日期函数**

在 SQL 中，日期函数用于处理和操作日期和时间数据。这些函数可以帮助你执行各种日期相关的操作，如提取日期部分、计算日期差、格式化日期等。以下是一些常用的日期函数及其用法。

#### **1. 获取当前日期和时间**

- **`NOW()`**：返回当前日期和时间。
- **`CURDATE()`**：返回当前日期。
- **`CURTIME()`**：返回当前时间。

##### **示例：**

```sql
SELECT NOW() AS current_datetime, CURDATE() AS current_date, CURTIME() AS current_time;
```

**结果：**

| current_datetime | current_date | current_time |
| ------------------- | ---------- | -------- |
| 2025-04-01 17:48:56 | 2025-04-01 | 17:48:56 |

#### **2. 提取日期部分**

- **`YEAR(date)`**：提取日期的年份部分。
- **`MONTH(date)`**：提取日期的月份部分。
- **`DAY(date)`**：提取日期的日部分。
- **`HOUR(time)`**：提取时间的小时部分。
- **`MINUTE(time)`**：提取时间的分钟部分。
- **`SECOND(time)`**：提取时间的秒部分。

#### **3. 日期计算**

- **`DATE_ADD(date, INTERVAL expr type)`**：在日期上加上一个时间间隔。
- **`DATE_SUB(date, INTERVAL expr type)`**：从日期中减去一个时间间隔。
- **`DATEDIFF(date1, date2)`**：计算两个日期之间的天数差。

#### **4. 格式化日期**

- **`DATE_FORMAT(date, format)`**：将日期格式化为指定的格式。

#### **5. 其他日期函数**

- **`DAYOFWEEK(date)`**：返回日期是星期几（1 = 星期天，2 = 星期一，...，7 = 星期六）。
- **`DAYOFYEAR(date)`**：返回日期是一年中的第几天。
- **`WEEK(date)`**：返回日期是一年中的第几周。
- **`LAST_DAY(date)`**：返回指定日期所在月份的最后一天。

### **示例**

假设有一个 `employees` 表，包含以下数据：

| employee_id | first_name | last_name | hire_date |
| ---- | ------- | ------- | ---------- |
| 1    | Alice   | Smith   | 2021-01-15 |
| 2    | Bob     | Johnson | 2022-02-20 |
| 3    | Charlie | Brown   | 2023-03-10 |

##### **提取日期部分**

查询每个员工的入职年份、月份和日期：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    hire_date, 
    YEAR(hire_date) AS hire_year, 
    MONTH(hire_date) AS hire_month, 
    DAY(hire_date) AS hire_day
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | hire_date | hire_year | hire_month | hire_day |
| ---- | ------- | ------- | ---------- | ---- | ---- | ---- |
| 1    | Alice   | Smith   | 2021-01-15 | 2021 | 1    | 15   |
| 2    | Bob     | Johnson | 2022-02-20 | 2022 | 2    | 20   |
| 3    | Charlie | Brown   | 2023-03-10 | 2023 | 3    | 10   |

##### **日期计算**

查询每个员工入职后的第 100 天：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    hire_date, 
    DATE_ADD(hire_date, INTERVAL 100 DAY) AS hundred_days_later
FROM 
    employees;
```

**结果：**

employee_id first_name last_name hire_date hundred_days_later

|      |         |         |            |            |
| ---- | ------- | ------- | ---------- | ---------- |
| 1    | Alice   | Smith   | 2021-01-15 | 2021-04-25 |
| 2    | Bob     | Johnson | 2022-02-20 | 2022-05-31 |
| 3    | Charlie | Brown   | 2023-03-10 | 2023-06-18 |

##### **格式化日期**

查询每个员工的入职日期，并按 `YYYY-MM` 格式返回：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    DATE_FORMAT(hire_date, '%Y-%m') AS formatted_hire_date
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | formatted_hire_date |
| ---- | ------- | ------- | ------- |
| 1    | Alice   | Smith   | 2021-01 |
| 2    | Bob     | Johnson | 2022-02 |
| 3    | Charlie | Brown   | 2023-03 |

##### **其他日期函数**

查询每个员工入职日期的星期几和所在月份的最后一天：

```sql
SELECT 
    employee_id, 
    first_name, 
    last_name, 
    hire_date, 
    DAYOFWEEK(hire_date) AS day_of_week, 
    LAST_DAY(hire_date) AS last_day_of_month
FROM 
    employees;
```

**结果：**

| employee_id | first_name | last_name | hire_date | day_of_week | last_day_of_month |
| ---- | ------- | ------- | ---------- | ---- | ---------- |
| 1    | Alice   | Smith   | 2021-01-15 | 6    | 2021-01-31 |
| 2    | Bob     | Johnson | 2022-02-20 | 1    | 2022-02-28 |
| 3    | Charlie | Brown   | 2023-03-10 | 6    | 2023-03-31 |

# **文本函数**

在 SQL 中，文本函数用于处理和操作字符串数据。这些函数可以帮助你执行各种字符串相关的操作，如连接字符串、提取子字符串、转换大小写等。以下是一些常用的文本函数及其用法。

#### **1. 字符串连接**

- **`CONCAT(string1, string2, ...)`**：将多个字符串连接成一个字符串。

#### **2. 字符串长度**

- **`LENGTH(string)`**：返回字符串的长度（以字节为单位）。
- **`CHAR_LENGTH(string)`**：返回字符串的长度（以字符为单位）。

> 对于单字节字符（如 ASCII 字符），LENGTH 和 CHAR_LENGTH 的结果是相同的。但对于多字节字符（如 UTF-8 编码的 Unicode 字符），LENGTH 会返回更多的字节数。
>
> 在处理多字节字符时，建议使用 CHAR_LENGTH，因为它能更准确地反映字符串的字符数。

#### **3. 字符串大小写转换**

- **`UPPER(string)`**：将字符串转换为大写。
- **`LOWER(string)`**：将字符串转换为小写。

#### **4. 字符串截取**

- **`SUBSTRING(string, start, length)`**：从字符串中提取子字符串。

#### **5. 字符串替换**

- **`REPLACE(string, search, replace)`**：在字符串中查找并替换指定的子字符串。

#### **6. 去除空格**

- **`TRIM(string)`**：去除字符串两端的空格。
- **`LTRIM(string)`**：去除字符串左端的空格。
- **`RTRIM(string)`**：去除字符串右端的空格。

#### **7. 字符串比较**

- **`STRCMP(string1, string2)`**：比较两个字符串，返回 0 表示相等，返回负数表示 `string1` 小于 `string2`，返回正数表示 `string1` 大于 `string2`。

### **示例：**

假设有一个 `employees` 表，包含以下数据：

| employee_id | first_name | last_name |
| ---- | ------- | ------- |
| 1    | Alice   | Smith   |
| 2    | Bob     | Johnson |
| 3    | Charlie | Brown   |

##### **字符串连接**

查询每个员工的全名：

```sql
SELECT 
    employee_id, 
    CONCAT(first_name, ' ', last_name) AS full_name
FROM 
    employees;
```

**结果：**

| employee_id | full_name |
| ---- | ------------- |
| 1    | Alice Smith   |
| 2    | Bob Johnson   |
| 3    | Charlie Brown |

##### **字符串长度**

查询每个员工的名字长度：

```sql
SELECT 
    employee_id, 
    first_name, 
    LENGTH(first_name) AS length_in_bytes, 
    CHAR_LENGTH(first_name) AS length_in_characters
FROM 
    employees;
```

**结果：**

| employee_id | first_name | length_in_bytes | length_in_characters |
| ---- | ------- | ---- | ---- |
| 1    | Alice   | 5    | 5    |
| 2    | Bob     | 3    | 3    |
| 3    | Charlie | 7    | 7    |

##### **字符串大小写转换**

查询每个员工的名字的大写和小写形式：

```sql
SELECT 
    employee_id, 
    first_name, 
    UPPER(first_name) AS upper_name, 
    LOWER(first_name) AS lower_name
FROM 
    employees;
```

**结果：**

| employee_id | first_name | upper_name | lower_name |
| ---- | ------- | ------- | ------- |
| 1    | Alice   | ALICE   | alice   |
| 2    | Bob     | BOB     | bob     |
| 3    | Charlie | CHARLIE | charlie |

##### **字符串截取**

查询每个员工名字的前 3 个字符：

```sql
SELECT 
    employee_id, 
    first_name, 
    SUBSTRING(first_name, 1, 3) AS substring_name
FROM 
    employees;
```

**结果：**

| employee_id | first_name | substring_name |
| ---- | ------- | ---- |
| 1    | Alice   | Ali  |
| 2    | Bob     | Bob  |
| 3    | Charlie | Cha  |

##### **字符串替换**

查询每个员工名字中的 'a' 替换为 'x'：

```sql
SELECT 
    employee_id, 
    first_name, 
    REPLACE(first_name, 'a', 'x') AS replaced_name
FROM 
    employees;
```

**结果：**

employee_id first_name replaced_name

|      |         |          |
| ---- | ------- | -------- |
| 1    | Alice   | Alixe    |
| 2    | Bob     | Bob      |
| 3    | Charlie | Chxrxlie |

##### **字符串比较**

查询每个员工的名字是否等于 'Alice'：

```sql
SELECT 
    employee_id, 
    first_name, 
    STRCMP(first_name, 'Alice') AS comparison_result
FROM 
    employees;
```

**结果：**

| employee_id | first_name | comparison_result |
| ---- | ------- | ---- |
| 1    | Alice   | 0    |
| 2    | Bob     | 1    |
| 3    | Charlie | 1    |

##### **去除空格**

假设有一个 `employees` 表，包含以下数据：（由于显示原因，以‘-’表示空格）

| employee_id | first_name | last_name |
| ---- | ------- | --------- |
| 1    | --Alice | Smith-    |
| 2    | Bob     | --Johnson |
| 3    | Charlie | Brown     |

查询每个员工的名字和姓氏，去除两端的空格：

```sql
SELECT 
    employee_id, 
    TRIM(first_name) AS trimmed_first_name, 
    TRIM(last_name) AS trimmed_last_name
FROM 
    employees;
```

**结果：**

| employee_id | trimmed_first_name | trimmed_last_name |
| ---- | ------- | ------- |
| 1    | Alice   | Smith   |
| 2    | Bob     | Johnson |
| 3    | Charlie | Brown   |

# 排序和排名窗口函数

`RANK()`、`DENSE_RANK()` 和 `ROW_NUMBER()` 都是 SQL 中的窗口函数，用于为结果集中的行分配排名或序号。它们的主要区别在于处理并列排名时的行为。以下是它们的详细对比：

### **1. `ROW_NUMBER()`**

`ROW_NUMBER()` 为每一行分配一个唯一的序号，序号从 1 开始，按指定的排序规则递增。即使有并列的行，`ROW_NUMBER()` 也会为每一行分配一个唯一的序号。

#### **语法**

```
ROW_NUMBER() OVER (
    [PARTITION BY column1, column2, ...]
    ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...
)
```

- `PARTITION BY`：可选，用于将数据分组，窗口函数在每个分组内独立计算。
- `ORDER BY`：指定排序规则，必须包含至少一个列。

#### **行为：**

- 每一行都被分配一个唯一的序号。
- 即使有并列的行，序号也不会重复。

### **2. `RANK()`**

`RANK()` 为每一行分配一个排名，如果有并列的行，则会分配相同的排名，但后续的排名会跳过一些数字。

#### **语法**

```
RANK() OVER (
    [PARTITION BY column1, column2, ...]
    ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...
)
```

- `PARTITION BY`：可选，用于将数据分组，窗口函数在每个分组内独立计算。
- `ORDER BY`：指定排序规则，必须包含至少一个列。

#### **行为：**

- 如果有并列的行，它们会被分配相同的排名。
- 后续的排名会跳过一些数字，以保持总排名的连续性。

### **3. `DENSE_RANK()`**

`DENSE_RANK()` 为每一行分配一个排名，即使有并列的行，也不会跳过任何排名，始终保持连续。

#### **语法**

```
DENSE_RANK() OVER (
    [PARTITION BY column1, column2, ...]
    ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...
)
```

- `PARTITION BY`：可选，用于将数据分组，窗口函数在每个分组内独立计算。
- `ORDER BY`：指定排序规则，必须包含至少一个列。

#### **行为：**

- 如果有并列的行，它们会被分配相同的排名。
- 后续的排名不会跳过任何数字，始终保持连续。

### **示例：**

假设有一个表 `scores`，数据如下：

| id | score |
| ---- | ---- |
| 1    | 90   |
| 2    | 85   |
| 3    | 90   |
| 4    | 80   |

#### 使用 `ROW_NUMBER()`：

```sql
SELECT id, score, ROW_NUMBER() OVER (ORDER BY score DESC) AS row_num
FROM scores;
```

结果：

| id | score | row_num |
| ---- | ---- | ---- |
| 1    | 90   | 1    |
| 3    | 90   | 2    |
| 2    | 85   | 3    |
| 4    | 80   | 4    |

解释：

- 每一行都被分配了一个唯一的序号。
- 即使有两个 `90` 的行，它们也被分配了不同的序号 `1` 和 `2`。

#### **使用 `RANK()`：**

```sql
SELECT id, score, RANK() OVER (ORDER BY score DESC) AS rank
FROM scores;
```

结果：

| id | score | rank |
| ---- | ---- | ---- |
| 1    | 90   | 1    |
| 3    | 90   | 1    |
| 2    | 85   | 3    |
| 4    | 80   | 4    |

解释：

- 两个 `90` 的行都被分配了排名 `1`。
- 排名 `2` 被跳过，因为有两个并列的 `1`。
- 排名 `3` 是下一个可用的排名。

#### **使用 `DENSE_RANK()`：**

```sql
SELECT id, score, DENSE_RANK() OVER (ORDER BY score DESC) AS dense_rank
FROM scores;
```

结果：

| id | score | dense_rank |
| ---- | ---- | ---- |
| 1    | 90   | 1    |
| 3    | 90   | 1    |
| 2    | 85   | 2    |
| 4    | 80   | 3    |

解释：

- 两个 `90` 的行都被分配了排名 `1`。
- 排名 `2` 是下一个可用的排名，即使有两个并列的 `1`。
- 排名 `3` 是下一个可用的排名。

### **4. 总结**

- **`ROW_NUMBER()`**：
  - 为每一行分配一个唯一的序号。
  - 即使有并列的行，序号也不会重复。
  - 适用于需要唯一标识每一行的场景。
- **`RANK()`**：
  - 为每一行分配一个排名。
  - 如果有并列的行，会分配相同的排名。
  - 后续的排名会跳过一些数字，以保持总排名的连续性。
  - 适用于需要反映并列情况的场景。
- **`DENSE_RANK()`**：
  - 为每一行分配一个排名。
  - 即使有并列的行，也不会跳过任何排名，始终保持连续。
  - 适用于需要保持排名连续的场景。

### **选择建议**

- 如果你需要为每一行分配一个唯一的序号，使用 `ROW_NUMBER()`。
- 如果你需要反映并列情况，并且可以接受跳过排名，使用 `RANK()`。
- 如果你需要保持排名连续，即使有并列，使用 `DENSE_RANK()`。

# 数学函数

### **1. 基本数学函数**

- **`ABS(x)`**：返回 `x` 的绝对值。
- **`CEIL(x)`** 或 **`CEILING(x)`**：返回大于或等于 `x` 的最小整数。
- **`FLOOR(x)`**：返回小于或等于 `x` 的最大整数。
- **`ROUND(x, n)`**：将 `x` 四舍五入到 `n` 位小数。
- **`TRUNC(x, n)`**：将 `x` 截断到 `n` 位小数（在某些数据库系统中，如 Oracle）。

### **2. 三角函数**

- **`SIN(x)`**：返回 `x` 的正弦值。
- **`COS(x)`**：返回 `x` 的余弦值。
- **`TAN(x)`**：返回 `x` 的正切值。
- **`ASIN(x)`**：返回 `x` 的反正弦值。
- **`ACOS(x)`**：返回 `x` 的反余弦值。
- **`ATAN(x)`**：返回 `x` 的反正切值。

### **3. 指数和对数函数**

- **`EXP(x)`**：返回 `e` 的 `x` 次幂。
- **`LN(x)`**：返回 `x` 的自然对数。
- **`LOG(x)`**：返回 `x` 的以 10 为底的对数。
- **`LOG2(x)`**：返回 `x` 的以 2 为底的对数。

### **4. 幂函数**

- **`POWER(x, y)`**：返回 `x` 的 `y` 次幂。
- **`SQRT(x)`**：返回 `x` 的平方根。

### **5. 随机函数**

- **`RAND()`**：返回一个在 0 到 1 之间的随机数。
- **`RANDOM()`**：返回一个在 0 到 1 之间的随机数（在某些数据库系统中，如 PostgreSQL）。

### **6. 其他数学函数**

- **`GREATEST(x, y, ...)`**：返回参数中的最大值。
- **`LEAST(x, y, ...)`**：返回参数中的最小值。
- **`MOD(x, y)`**：返回 `x` 除以 `y` 的余数。

### **示例**

假设有一个表 `numbers`，包含以下数据：

| id | value |
| ---- | ----- |
| 1    | 3.14  |
| 2    | -2.71 |
| 3    | 1.41  |

#### **示例 1：基本数学函数**

```sql
SELECT 
    id, 
    value, 
    ABS(value) AS absolute_value, 
    CEIL(value) AS ceiling_value, 
    FLOOR(value) AS floor_value, 
    ROUND(value, 2) AS rounded_value
FROM 
    numbers;
```

**结果：**

| id | value | absolute_value | ceiling_value | floor_value | rounded_value |
| ---- | ----- | ---- | ---- | ---- | ----- |
| 1    | 3.14  | 3.14 | 4    | 3    | 3.14  |
| 2    | -2.71 | 2.71 | -2   | -3   | -2.71 |
| 3    | 1.41  | 1.41 | 2    | 1    | 1.41  |

#### **示例 2：三角函数**

```sql
SELECT 
    id, 
    value, 
    SIN(value) AS sine_value, 
    COS(value) AS cosine_value, 
    TAN(value) AS tangent_value
FROM 
    numbers;
```

**结果：**

| id | value | sine_value | cosine_value | tangent_value |
| ---- | ----- | ------------ | ------------ | ----------- |
| 1    | 3.14  | 0.00159265   | -0.999999999 | -0.00159265 |
| 2    | -2.71 | -0.420201004 | -0.912945251 | 0.460170004 |
| 3    | 1.41  | 0.987346029  | 0.158333152  | 6.236147292 |

#### **示例 3：指数和对数函数**

```sql
SELECT 
    id, 
    value, 
    EXP(value) AS exponential_value, 
    LN(value) AS natural_logarithm, 
    LOG(value) AS common_logarithm
FROM 
    numbers;
```

**结果：**

| id | value | exponential_value | natural_logarithm | common_logarithm |
| ---- | ----- | ------------- | ------------ | ------------ |
| 1    | 3.14  | 23.104000057  | 1.144223892  | 0.496929648  |
| 2    | -2.71 | 0.06598803585 | -0.997547553 | -0.432659903 |
| 3    | 1.41  | 4.103566842   | 0.343323839  | 0.150162647  |

#### **示例 4：幂函数**

```sql
SELECT 
    id, 
    value, 
    POWER(value, 2) AS squared_value, 
    SQRT(value) AS square_root
FROM 
    numbers;
```

**结果：**

| id | value | squared_value | square_root |
| ---- | ----- | ------ | ----------- |
| 1    | 3.14  | 9.8596 | 1.772004515 |
| 2    | -2.71 | 7.3441 | NULL        |
| 3    | 1.41  | 1.9881 | 1.187434209 |

#### **示例 5：随机函数**

```sql
SELECT 
    id, 
    value, 
    RAND() AS random_value
FROM 
    numbers;
```

**结果：**

| id | value | random_value |
| ---- | ----- | ----------- |
| 1    | 3.14  | 0.123456789 |
| 2    | -2.71 | 0.987654321 |
| 3    | 1.41  | 0.543210987 |
