# Spring Boot 2.x Practices

> SpringBoot 2.1.8-RELEASE

## Quick Start

### 环境准备

- Visual Studio Code [Spring Boot in Visual Studio Code](https://code.visualstudio.com/docs/java/java-spring-boot)

- JDK 1.8 + 

- Gradle 5.0+


### 生成项目工程

在 vscode 中，调出 `Command Palette `输入框， 输入 `Spring Initializr`, 参考一下步骤创建新的工程。

1. `Spring Initializr: Generate a Gradle Project`
2. Specify Project Language: `Java`
3. Specify Group Id: `io.github.shankai.springboot`
4. Specify Artifact Id: `quickstart`
5. Specify Spring Boot Version: `2.1.8`
6. Search for dependencies: `Spring Web`
7. Press <Enter> to continue. 

### 构建&运行

#### vscode

1. 等待 vscode 将所有依赖项准备就绪；
2. 在包含 `@SpringBootApplication` 注解的类中直接运行`main`方法 / 在 `Spring-Boot Dashboard `面板选择 `quickstart` 右键 `Start` 等
3. 发送 Http `Get` 请求 `http://localhost:8080/`，若返回 `Hello SpringBoot.`即为成功。

#### 命令行

1. 构建 `gradle clean build`
2. 运行 `java -jar build/libs/quickstart-0.0.1-SNAPSHOT.jar `
3. 发送 Http `Get` 请求 `http://localhost:8080/`，若返回 `Hello SpringBoot.`即为成功。



## Restful

### 依赖

- lombok 插件
- JAVA Stream API (JDK1.8 +)



### 注解

`@RestController` 注释用于定义RESTful Web服务。它提供JSON，XML和自定义响应。

`@RequestMapping` 注释用于定义访问REST端点的Request URI。可以定义Request方法来使用和生成对象。默认请求方法是:`GET`

`@RequestBody` 注释用于定义请求正文内容类型。

`@PathVariable` 注释用于定义自定义或动态请求URI。 请求URI中的Path变量定义为花括号`{}`。

`@RequestParam` 注释用于从请求URL读取请求参数。默认情况下，它是必需参数。还可以为请求参数设置默认值。



### Restful API 请求示例

```
// get all users
curl -X GET http://localhost:8080/users

// search by name, contains param name value
curl -X GET http://localhost:8080/users?name=1

// get specify user by id
curl -X GET http://localhost:8080/user/2

// create new user
curl -X POST http://localhost:8080/user -d '{"id":"3", "name":"user3", "age":30}' -H "Content-Type:application/json"

// delete user
curl -X DELETE http://localhost:8080/user/1

// update user
curl -X POST http://localhost:8080/user/2 -d '{"id":"2", "name":"change2", "age":20}' -H "Content-Type:application/json"
```



## War in External 



1. 扩展 `DemoApplication` 类`SpringBootServletInitializer`以支持WAR文件部署

   ```
   @SpringBootApplication
   @RestController
   public class DemoApplication extends SpringBootServletInitializer {
   
   	public static void main(String[] args) {
   		SpringApplication.run(DemoApplication.class, args);
   	}
   
   	@RequestMapping("/")
   	public String info() {
   		return "Hello SpringBoot from External Container";
   	}
   
   	@Override
      protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(DemoApplication.class);
      }
   
   }
   ```

   

2. 修改 build.gradle 文件

   添加插件：

  ```
  apply plugin: 'war'
  apply plugin: 'application'
  ```

设置应用程序引导类：

```
mainClassName="io.github.shankai.springboot.warinexternal.DemoApplication"
```



3. 构建 `gradle clean build`
4. 将 `build/libs/war-in-external-0.0.1-SNAPSHOT.war` 放入外部容器，如 tomcat 的 webapps 目录；
5. 发送 Http `Get` 请求 `http://localhost:8080/war-in-external-0.0.1-SNAPSHOT/`，返回 `Hello SpringBoot from External Container`即为成功。



## Bean Autowired

### @Autowired 与 @Qualifier

`@Autowired` 默认`byType`（按照类型）装配，要求类型必须存在；若不确定 Bean 是否存在，则可以设置 `required` 属性，如：`@Autowired(required=false)`。

当存在相同类型不同实例的 Bean 时，也可以`byName`（按照名称）装配，则需要使用 `@Qualifier("beanName")` 注解指定名称。

@Autowired 与 @Qualifier 组合使用时，自动装配的策略从 `byType`变为 `byName`。



### @Bean 与@Configuration

`@Bean("beanName")`用来声明在容器内 Bean 的名称。

`@Configuration` 作用是将当前应用的类放入 Spring 容器的上下文中。该注解用来代替 applicationContext.xml 配置文件。



## Runner

`ApplicationRunner` 与 `CommandLineRunner`

## Properties


### 属性文件

默认情况下，属性文件为类路径下的 `application.properties` 文件。

示例：
1. 调整缺省应用容器的端口为 `8090`, 应用的上下文根为 `/p`。如下：
```
server.port=8090
server.servlet.context-path=/p
```

2. 构建并启动服务
```
gradle clean build
java -jar build/libs/properties-0.0.1-SNAPSHOT.jar
```

3. 访问 `http://localhost:8090/p/` 查看结果。

### 命令行

可以通过在命令行运行应用程序时指定相关属性值。

示例：
1. 在之前的环境及设置基础上，通过命令行启动服务，并指定属性值。

调整缺省应用容器的端口为 `8088`, 应用的上下文根为 `/line`。

`java -jar build/libs/properties-0.0.1-SNAPSHOT.jar --server.port=8088 --server.servlet.context-path=/line`

2. 访问 `http://localhost:8088/line/` 查看结果。

### @Value

`@Value` 注释用于读取Java代码中的环境或应用程序属性值。

以下示例将服务端口设置到 `port` 属性:

```
@Value("${server.port}")
int port;
```

若指定的属性不存在, 服务在启动时会报错。可以通过指定属性默认值避免这种错误。语法为：
```
@Value("${property_key_name:default_value}")
```

以下示例中，`prop.not.exist` 属性不存在，则会设置为默认值`true`。
```
@Value("${prop.not.exist:true}")
String notExist;
```

### 外部配置文件

通过设置 `spring.config.location` 属性，更改默认的应用配置文件位置。

如启动命令为 `java -jar build/libs/properties-0.0.1-SNAPSHOT.jar --spring.config.location=/Users/shankai/Desktop/app.properties`。

### 概要属性文件

概要属性文件的命名规则为 `application-{profile}.properties`，其中 `{profile}` 为概要的代码，大小写敏感。比如为 `application-dev.properties`、`application-prod.properties`等。

默认情况下，应用服务在启动时会加载 `application.properties`。通过在命令行激活指定的概要配置，指定的概要配置不存在时，会自动使用缺省的配置。

启动命令：
`java -jar build/libs/properties-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev`

输出：
```
2019-09-30 13:06:26.795  INFO 98934 --- [           main] i.g.s.s.properties.DemoApplication       : The following profiles are active: dev
2019-09-30 13:06:27.719  INFO 98934 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8091 (http)
2019-09-30 13:06:27.752  INFO 98934 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-09-30 13:06:27.752  INFO 98934 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.24]
2019-09-30 13:06:27.845  INFO 98934 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/p]      : Initializing Spring embedded WebApplicationContext
2019-09-30 13:06:27.845  INFO 98934 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1003 ms
2019-09-30 13:06:28.024  INFO 98934 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-09-30 13:06:28.206  INFO 98934 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8091 (http) with context path '/p'
2019-09-30 13:06:28.209  INFO 98934 --- [           main] i.g.s.s.properties.DemoApplication       : Started DemoApplication in 1.778 seconds (JVM running for 2.14)
2019-09-30 13:06:48.743  INFO 98934 --- [nio-8091-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/p]      : Initializing Spring DispatcherServlet 'dispatcherServlet'
2019-09-30 13:06:48.743  INFO 98934 --- [nio-8091-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2019-09-30 13:06:48.748  INFO 98934 --- [nio-8091-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
```


## Logger

SpringBoot + Log4j2 + Lombok 示例

- build.gradle
```
configurations {
	providedRuntime
	all*.exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}

dependencies {
	...
	implementation 'org.springframework.boot:spring-boot-starter-log4j2'
   ...
}
```

- log4j2.xml

详见 `log4j2.xml` 文件内容

## Exception

### @ControllerAdvice
通过在类上添加 `@ControllerAdvice` 注解声明全局异常处理类。

### @ExceptionHandler
通过在方法上添加 `@ExceptionHandler` 注解声明捕获的异常类型。
- 比如：`@ExceptionHandler(value = ArithmeticException.class)`，请求 `http://localhost:8080/div/1/0` 查看结果。
- 比如：`@ExceptionHandler(value = NumberFormatException.class)`，请求 `http://localhost:8080/div/1/a` 查看结果。

以上实现详见 `io.github.shankai.springboot.except.GlobalAdvice`类。

## Interceptor

1. 自定义拦截器，需要实现 `HandlerInterceptor` 接口；
  - preHandle: 在控制器之前触发；
  - postHandle: 在控制器之后触发；
  - afterCompletion：请求响应成功后触发；
2. 将自定义拦截器注册到容器上下文；
3. 访问 `http://localhost:8080/`，成功后控制台日志输出：

```
...
2019-09-30 23:02:50.233  INFO 20058 --- [nio-8080-exec-1] i.g.s.s.interceptor.MyInterceptor        : --- preHandle ---
2019-09-30 23:02:50.239  INFO 20058 --- [nio-8080-exec-1] i.g.s.s.interceptor.DemoApplication      : >>> request >>>
2019-09-30 23:02:50.256  INFO 20058 --- [nio-8080-exec-1] i.g.s.s.interceptor.MyInterceptor        : --- postHandle ---
2019-09-30 23:02:50.256  INFO 20058 --- [nio-8080-exec-1] i.g.s.s.interceptor.MyInterceptor        : --- afterCompletion ---
...
```

## Filter

自定义过滤器需要实现 `javax.servlet.Filter`
