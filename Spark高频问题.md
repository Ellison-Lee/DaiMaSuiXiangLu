# Spark高频问题

## Spark 的运行流程？

具体运行流程如下：

1. SparkContext 向资源管理器注册并向资源管理器申请运行，Executor资源管理器分配 Executor，然后资源管理器启动 Executor
3. Executor 发送心跳至资源管理器
4. **SparkContext 构建 DAG 有向无环图**
5. **将 DAG 分解成 Stage**
6. **把 Stage 发送给 TaskScheduler**
7. **Executor 向 SparkContext 申请 Task**
8. **TaskScheduler 将 Task 发送给 Executor 运行**
9. **同时 SparkContext 将应用程序代码发放给 Executor**
10. Task 在 Executor 上运行，运行完毕释放所有资源

##  Spark 有哪些组件？

1. master：管理集群和节点，不参与计算。
2. worker：计算节点，进程本身不参与计算，和 master 汇报。
3. Driver：运行程序的 main 方法，创建 spark context 对象。
4. spark context：控制整个 application 的生命周期，包括 dagsheduler 和 task scheduler 等组件。
5. client：用户提交程序的入口。

## Spark 中的 RDD 机制理解吗？

RDD是分布式弹性数据集，在逻辑上是一个 hdfs 文件，在抽象上是一种元素集合，包含了数据。它是被分区的，分为多个分区，每个分区分布在集群中的不同结点上，从而让 RDD 中的数据可以被并行操作。

RDD 的数据默认存放在内存中，但是当内存资源不足时，spark 会自动将 RDD 数据写入磁盘。RDD 的弹性体现在于 RDD 上自动进行内存和磁盘之间权衡和切换的机制。

## RDD 中 reduceBykey 与 groupByKey 哪个性能好，为什么？

**reduceByKey**：reduceByKey 会在结果发送至 reducer 之前会对每个 mapper 在本地进行 merge，有点类似于在 MapReduce 中的 combiner。这样做的好处在于，在 map 端进行一次 reduce 之后，数据量会大幅度减小，从而减小传输，保证 reduce 端能够更快的进行结果计算。

**groupByKey**：groupByKey 会对每一个 RDD 中的 value 值进行聚合形成一个序列，此操作发生在 reduce 端，所以势必会将所有的数据通过网络进行传输，造成不必要的浪费。同时如果数据量十分大，可能还会造成 OOM（Out Of Memory）。

所以在进行大量数据的 reduce 操作时候建议使用 reduceByKey。不仅可以提高速度，还可以防止使用 groupByKey 造成的内存溢出问题。

## 介绍一下 cogroup rdd 实现原理，你在什么场景下用过这个 rdd？

在 Spark 中，`cogroup`（co-group）是一种对多个 RDD 进行分组关联的操作，其核心原理是将多个 RDD 中具有相同 key 的数据聚合到一起，形成一个以 key 为键、以各 RDD 中对应 value 集合为值的新 RDD。

**与 reduceByKey 不同的是**：reduceByKey 针对**一个 RDD**中相同的 key 进行合并。而 cogroup 针对**多个 RDD**中相同的 key 的元素进行合并。

**cogroup 的函数实现**：这个实现根据要进行合并的两个 RDD 操作，生成一个 CoGroupedRDD 的实例，这个 RDD 的返回结果是把相同的 key 中两个 RDD 分别进行合并操作，最后返回的 RDD 的 value 是一个 Pair 的实例，这个实例包含两个 Iterable 的值，第一个值表示的是 RDD1 中相同 KEY 的值，第二个值表示的是 RDD2 中相同 key 的值。

由于做 cogroup 的操作，需要通过 partitioner 进行重新分区的操作，因此，执行这个流程时，需要执行一次 [shuffle](https://zhida.zhihu.com/search?content_id=183329413&content_type=Article&match_order=1&q=shuffle&zhida_source=entity) 的操作(如果要进行合并的两个 RDD 的都已经是 shuffle 后的 rdd，同时他们对应的 partitioner 相同时，就不需要执行 shuffle)。

**场景**：表关联查询或者处理重复的 key。

## 如何区分 RDD 的宽窄依赖？

窄依赖:父 RDD 的一个分区只会被子 RDD 的一个分区依赖；

宽依赖:父 RDD 的一个分区会被子 RDD 的多个分区依赖(涉及到 shuffle)。

## 为什么要设计宽窄依赖？

1. **对于窄依赖：**
   窄依赖的多个分区可以并行计算；窄依赖的一个分区的数据如果丢失只需要重新计算对应的分区的数据就可以了。
2. **对于宽依赖：**
   划分 Stage(阶段)的依据:对于宽依赖,必须等到上一阶段计算完成才能计算下一阶段。

## DAG 是什么？

DAG(Directed Acyclic Graph 有向无环图)指的是数据转换执行的过程，有方向，无闭环(其实就是 RDD 执行的流程)；
原始的 RDD 通过一系列的转换操作就形成了 DAG 有向无环图，任务执行时，可以按照 DAG 的描述，执行真正的计算(数据被操作的一个过程)。

## DAG 中为什么要划分 Stage？

为了并行计算。

一个复杂的业务逻辑如果有 shuffle，那么就意味着前面阶段产生结果后，才能执行下一个阶段，即下一个阶段的计算要依赖上一个阶段的数据。那么我们按照 shuffle 进行划分(也就是按照宽依赖就行划分)，就可以将一个 DAG 划分成多个 Stage/阶段，在同一个 Stage 中，会有多个算子操作，可以形成一个 pipeline 流水线，流水线内的多个平行的分区可以并行执行。

## 如何划分 DAG 的 stage？

对于窄依赖，partition 的转换处理在 stage 中完成计算，不划分(将窄依赖尽量放在在同一个 stage 中，可以实现流水线计算)。

对于宽依赖，由于有 shuffle 的存在，只能在父 RDD 处理完成后，才能开始接下来的计算，也就是说需要要划分 stage。

## DAG 划分为 Stage 的算法了解吗？

**核心算法：回溯算法**

**从后往前回溯/反向解析，遇到窄依赖加入本 Stage，遇见宽依赖进行 Stage 切分。**

Spark 内核会从触发 Action 操作的那个 RDD 开始**从后往前推**，首先会为最后一个 RDD 创建一个 Stage，然后继续倒推，如果发现对某个 RDD 是宽依赖，那么就会将宽依赖的那个 RDD 创建一个新的 Stage，那个 RDD 就是新的 Stage 的最后一个 RDD。 然后依次类推，继续倒推，根据窄依赖或者宽依赖进行 Stage 的划分，直到所有的 RDD 全部遍历完成为止。

## 对于 Spark 中的数据倾斜问题你有什么好的方案？

在 Spark 中解决数据倾斜问题的常用方法包括：

1. 调整并行度：通过提高 shuffle 操作的并行度，让数据更均匀分布
2. 两阶段聚合：先局部聚合再全局聚合，减少单键数据量
3. 加盐处理：对倾斜的 key 添加随机前缀，分散到不同分区处理后再还原
4. 过滤或拆分倾斜 key：单独处理大量倾斜数据，其余正常处理
5. 使用广播变量：将小表广播，避免 shuffle 操作
6. 自定义分区器：根据数据分布特点设计更合理的分区策略

## 如何优化shuffle

1. **调整 Shuffle 分区数**
   - 避免分区过多（增加调度开销）或过少（数据倾斜）。
2. **减少 Shuffle 数据量**
   - 提前过滤：用`filter`/`where`剔除无关数据，减少参与 Shuffle 的记录数。
   - 选择合适 Join 策略：大表 Join 时只保留必要字段。
   - 复用数据：缓存（`cache()`/`persist()`）频繁使用的中间结果，避免重复计算和 Shuffle。
3. **优化数据倾斜**
   - 加盐处理：对倾斜 Key 添加随机前缀，分散到多个分区处理后再合并。
   - 拆分倾斜数据集：将倾斜 Key 单独处理（如广播小表对应部分），其余正常 Join 后汇总。
4. **避免不必要的 Shuffle**
   - 小表用广播 Join（`broadcast()`），避免 Shuffle。
   - 相同 Key 的聚合操作优先用`reduceByKey`（局部聚合后全局聚合）而非`groupByKey`（直接 Shuffle 所有数据）。

## Spark 中的 OOM 问题？

1. **调整内存配置**
   - 增加可用内存：如 Java 应用增大`-Xms`（初始内存）和`-Xmx`（最大内存）；Spark 任务调整`executor.memory`和`driver.memory`。
   - 合理分配堆内外内存：如 Spark 中调整`spark.memory.fraction`控制堆内内存占比，避免堆外内存溢出。
2. **优化内存使用**
   - 减少大对象创建：避免频繁创建临时对象，使用对象池复用对象，避免内存泄漏（如未释放的资源、静态集合无限制存储）。
   - 数据分片处理：大文件 / 数据集分片加载，避免一次性全量加载到内存。
3. **针对性场景优化**
   - **Spark 任务**：避免宽表 join，使用广播变量减小 shuffle 数据量，启用内存管理（如`spark.memory.offHeap.enabled`开启堆外内存）。
4. **代码逻辑优化**
   - 避免无限递归、死循环导致的栈溢出（StackOverflowError）。
   - 释放不再使用的资源：如关闭文件流、数据库连接，清理缓存（如 Redis、本地缓存）中过期数据。

关键是通过监控工具（如 JConsole、Spark UI、Prometheus）定位内存瓶颈，针对性优化而非盲目增加内存。

## Spark 中数据的位置是被谁管理的？

每个数据分片都对应具体物理位置，数据的位置是被**blockManager**管理，无论数据是在磁盘，内存还是 tacyan，都是由 blockManager 管理。

##  Spaek 程序执行，有时候默认为什么会产生很多 task，怎么修改默认 task 执行个数？

默认情况下，task 数量较多通常与以下因素相关：

1. **默认分区数设置**：
   Spark 对 RDD 或 DataFrame 操作时，默认分区数由 `spark.default.parallelism`（针对 RDD）和 `spark.sql.shuffle.partitions`（针对 DataFrame/DataSet 的 shuffle 操作）控制。默认值通常为集群核心数的 2-3 倍（如单机环境可能为 200），若数据量小但分区数大，会生成大量 task。
2. **数据来源的分区数**：
   读取外部数据时（如 HDFS、Kafka），分区数由数据源本身的分片机制决定（如 HDFS 的块数），可能导致 task 数量与数据分片数一致。

修改方法：
在 Spark 配置文件（`spark-defaults.conf`）或提交任务时设置参数

## 介绍一下 join 操作优化经验？

在 Spark 中，join 操作是常见的宽依赖操作，容易引发性能问题（如数据倾斜、shuffle 开销大等），优化需结合数据特征和操作类型，以下是核心经验：

1. **选择合适的 join 类型**

   - 小表与大表 join：用`broadcast join`（广播小表到所有 Executor，避免 shuffle），通过`broadcast()`函数显式指定，适合小表数据量在 Executor 内存可容纳范围内（通常建议 < 1GB）。
   - 大表与大表 join：优先用`shuffle hash join`或`sorted merge join`，Spark 会根据数据大小自动选择，也可通过`spark.sql.join.preferSortMergeJoin`控制。

2. **解决数据倾斜问题**

   - 对倾斜 key 加盐：给倾斜 key 添加随机前缀分散到多个分区，局部聚合后再还原。
   - 拆分倾斜数据集：将倾斜 key 单独过滤出来，与另一表的对应数据做广播 join，其余数据正常 join 后合并结果。
   - 调整 join 顺序：让数据量小的表作为驱动表（左表），减少 shuffle 数据量。

3. **优化数据格式与分区**

   - 预处理数据：对频繁 join 的字段提前进行清洗、去重，减少无效数据。
   - 合理分区：按 join 字段进行分区（`partitionBy`），使相同 key 的数据集中在同一分区，避免跨分区 shuffle。
   - 使用高效格式：采用 Parquet、ORC 等列式存储，减少 IO 和内存占用，提升 join 效率。

4. **避免不必要的 join**

   - 先过滤后 join：通过`where`/`filter`提前过滤掉无关数据，减小 join 的数据量。
   - 用广播变量替代：非等值 join 场景，可将小表转为广播变量，通过 map-side 操作实现关联，避免 shuffle。

   ## Spark 与 MapReduce 的 Shuffle 的区别？

   1. **处理模式**：MapReduce 的 Shuffle 是磁盘级操作，中间结果需写入磁盘，IO 开销大；Spark 的 Shuffle 优先使用内存，减少磁盘 IO，仅在内存不足时溢写磁盘。
   2. **阶段划分**：MapReduce 的 Shuffle 严格对应 Map 和 Reduce 阶段，流程固定死；Spark 的 Shuffle 更灵活，可根据算子（如 reduceByKey、join 等）动态适配，支持多种 Shuffle 策略（如 Hash、Sort 等）。
   3. **数据处理效率**：Spark 通过内存迭代计算和流水线优化，避免 MapReduce 中多次磁盘读写和任务启动开销，Shuffle 性能通常更高。
   4. **容错机制**：MapReduce 依赖磁盘存储中间结果实现容错；Spark 基于 RDD 的 lineage 机制，通过内存数据和 checkpoint 实现容错，恢复更高效。

   ## 通常来说，Spark 与 MapReduce 相比，Spark 运行效率更高。请说明效率更高来源于 Spark 内置的哪些机制？

   1. 基于内存计算，减少低效的磁盘交互；
   2. 高效的调度算法，基于 DAG；
   3. 容错机制 Linage。

   重点部分就是 DAG 和 Lingae

   ## Hadoop 和 Spark 的相同点和不同点？

   **相同点**：

   1. 均用于处理大规模数据集，支持分布式计算
   2. 都依赖运行在分布式集群上，依赖底层存储系统（如 HDFS）
   3. 支持容错机制，可应对节点故障

   **不同点**：

   1. 计算模型：Hadoop 基于 MapReduce，采用 "磁盘 IO + 批处理" 模式，中间结果写入磁盘；Spark 基于内存计算，中间结果优先存内存，支持迭代计算
   2. 适用场景：Hadoop 适合离线批处理（如日志分析）；Spark 除批处理外，还支持流处理（Spark Streaming）、机器学习（MLlib）等
   3. 编程模型：Hadoop 需编写 Map 和 Reduce 函数；Spark 提供 RDD/DataFrame/Dataset 等高级 API，支持更多算子
   4. 资源管理：Hadoop 使用 YARN 管理资源；Spark 可运行在 YARN、Mesos 或独立集群上
   5. 延迟性：Hadoop 延迟较高；Spark 延迟较低，更接近实时处理

## Spark 如何保证宕机迅速恢复?

1. 适当增加 spark standby master
2. 编写 shell 脚本，定期检测 master 状态，出现宕机后对 master 进行重启操作

## RDD 持久化原理？

调用 cache()和 persist()方法即可。cache()和 persist()的区别在于，cache()是 persist()的一种简化方式，cache()的底层就是调用 persist()的无参版本 persist(MEMORY_ONLY)，将数据持久化到内存中。

如果需要从内存中清除缓存，可以使用 unpersist()方法。RDD 持久化是可以手动选择不同的策略的。在调用 persist()时传入对应的 StorageLevel 即可。

## Checkpoint 检查点机制？

应用场景：当 spark 应用程序特别复杂，从初始的 RDD 开始到最后整个应用程序完成有很多的步骤，而且整个应用运行时间特别长，这种情况下就比较适合使用 checkpoint 功能。

原因：对于特别复杂的 Spark 应用，会出现某个反复使用的 RDD，即使之前持久化过但由于节点的故障导致数据丢失了，没有容错机制，所以需要重新计算一次数据。

Checkpoint 首先会调用 SparkContext 的 setCheckPointDIR()方法，设置一个容错的文件系统的目录，比如说 HDFS；然后对 RDD 调用 checkpoint()方法。之后在 RDD 所处的 job 运行结束之后，会启动一个单独的 job，来将 checkpoint 过的 RDD 数据写入之前设置的文件系统，进行高可用、容错的类持久化操作。

## Checkpoint 和持久化机制的区别？

最主要的区别在于持久化只是将数据保存在 BlockManager 中，但是 RDD 的 lineage(血缘关系，依赖关系)是不变的。但是 checkpoint 执行完之后，rdd 已经没有之前所谓的依赖 rdd 了，而只有一个强行为其设置的 checkpointRDD，checkpoint 之后 rdd 的 lineage 就改变了。

持久化的数据丢失的可能性更大，因为节点的故障会导致磁盘、内存的数据丢失。但是 checkpoint 的数据通常是保存在高可用的文件系统中，比如 HDFS 中，所以数据丢失可能性比较低

## Spark Streaming 以及基本工作原理？

Spark streaming 是 spark core API 的一种扩展，可以用于进行大规模、高吞吐量、容错的实时数据流的处理。

它支持从多种数据源读取数据，比如 Kafka、Flume、Twitter 和 TCP Socket，并且能够使用算子比如 map、reduce、join 和 window 等来处理数据，处理后的数据可以保存到文件系统、数据库等存储中。

Spark streaming 内部的基本工作原理是：接受实时输入数据流，然后将数据拆分成 batch，比如每收集一秒的数据封装成一个 batch，然后将每个 batch 交给 spark 的计算引擎进行处理，最后会生产处一个结果数据流，其中的数据也是一个一个的 batch 组成的。

## Spark 主备切换机制原理知道吗？

Master 实际上可以配置两个，Spark 原生的 standalone 模式是支持 Master 主备切换的。当 Active Master 节点挂掉以后，我们可以将 Standby Master 切换为 Active Master。

Spark Master 主备切换可以基于两种机制，一种是基于文件系统的，一种是基于 ZooKeeper 的。

基于文件系统的主备切换机制，需要在 Active Master 挂掉之后手动切换到 Standby Master 上；

而基于 Zookeeper 的主备切换机制，可以实现自动切换 Master。

## Spark 解决了 Hadoop 的哪些问题？

1. **MR**：抽象层次低，需要使用手工代码来完成程序编写，使用上难以上手；
   **Spark**：Spark 采用 RDD 计算模型，简单容易上手。
2. **MR**：只提供 map 和 reduce 两个操作，表达能力欠缺；
   **Spark**：Spark 采用更加丰富的算子模型，包括 map、flatmap、groupbykey、reducebykey 等；
3. **MR**：一个 job 只能包含 map 和 reduce 两个阶段，复杂的任务需要包含很多个 job，这些 job 之间的管理以来需要开发者自己进行管理；
   **Spark**：Spark 中一个 job 可以包含多个转换操作，在调度时可以生成多个 stage，而且如果多个 map 操作的分区不变，是可以放在同一个 task 里面去执行；
4. **MR**：中间结果存放在 hdfs 中；
   **Spark**：Spark 的中间结果一般存在内存中，只有当内存不够了，才会存入本地磁盘，而不是 hdfs；
5. **MR**：只适合 batch 批处理，时延高，对于交互式处理和实时处理支持不够；
   **Spark**：Spark streaming 可以将流拆成时间间隔的 batch 进行处理，实时计算。

## Spark Master HA 主从切换过程不会影响到集群已有作业的运行，为什么？

不会的。

因为程序在运行之前，已经申请过资源了，driver 和 Executors 通讯，不需要和 master 进行通讯的。

## RDD有哪些缺陷？

不支持细粒度的写和更新操作，Spark写数据是粗粒度的，所谓粗粒度，就是批量写入数据，目的是为了提高效率。但是Spark读数据是细粒度的，也就是说可以一条条的读。
不支持增量迭代计算，如果对Flink熟悉，可以说下Flink支持增量迭代计算。