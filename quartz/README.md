# Quartz Scheduler

- SpringBoot `2.2.1`
- Quartz Scheduler: `http://www.quartz-scheduler.org`
- RedisJobStore: `https://github.com/jlinn/quartz-redis-jobstore` Tag: `quartz-redis-jobstore-1.1.15`

## Dependencies

核心依赖： `quartz` + `redis-jobstore`

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>

<dependency>
    <groupId>net.joelinn</groupId>
    <artifactId>quartz-redis-jobstore</artifactId>
    <version>1.1.15</version>
    <exclusions>
        <exclusion>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
</dependency>
```

## Configuration

- quartz.properties 

本示例中暂未使用，该文件为原生 quartz scheduler 缺省配置文件。

- application.properties

与 springboot 集成后，支持的配置项需要增加前缀: `spring.quartz.properties.`，详见配置内容。

## API

- Create Demo Jobs

`curl -X POST http://localhost:8080/jobs `

## Support

- Recover: 单节点服务离线后，重新启动服务会根据 redis-jobstore 中的任务状态继续调度(不同类型的 trigger 有不同的 misfire 处理策略)
- Cluster: 默认支持集群模式，即启动多个服务实例，同一个作业同时仅有一个实例在负责调度，倘若该服务异常离线，则其他任一实例会抢占调度
- Misfire: 不同触发器有不同的处理机制，详见 `http://www.quartz-scheduler.org/documentation/`