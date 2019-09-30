# Spring Boot 
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




