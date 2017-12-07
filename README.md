# amqp-sample

This works:
```
$ ./mvnw clean package && cf push
```

This doesn't:
```
$ ./mvnw clean package -Pservice-registry && cf push
```

This gives us this error:
```
   2017-12-06T16:57:59.43-0800 [APP/PROC/WEB/0] OUT 2017-12-07 00:57:59.432  WARN 20 --- [           main] ationConfigEmbeddedWebApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'amqpSampleApplication': Unsatisfied dependency expressed through field 'amqpTemplate'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.amqp.core.AmqpTemplate' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
   2017-12-06T16:57:59.44-0800 [APP/PROC/WEB/0] OUT 2017-12-07 00:57:59.438  INFO 20 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
   2017-12-06T16:57:59.49-0800 [APP/PROC/WEB/0] OUT 2017-12-07 00:57:59.494  INFO 20 --- [           main] utoConfigurationReportLoggingInitializer :
   2017-12-06T16:57:59.49-0800 [APP/PROC/WEB/0] OUT Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT 2017-12-07 00:57:59.987 ERROR 20 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   :
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT ***************************
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT APPLICATION FAILED TO START
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT ***************************
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT Description:
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT Field amqpTemplate in io.pivotal.amqpsample.AmqpSampleApplication required a bean of type 'org.springframework.amqp.core.AmqpTemplate' that could not be found.
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT Action:
   2017-12-06T16:57:59.99-0800 [APP/PROC/WEB/0] OUT Consider defining a bean of type 'org.springframework.amqp.core.AmqpTemplate' in your configuration.
   2017-12-06T16:58:00.03-0800 [APP/PROC/WEB/0] OUT Exit status 1
   2017-12-06T16:58:00.03-0800 [CELL/SSHD/0] OUT Exit status 0
   2017-12-06T16:58:00.14-0800 [CELL/0] OUT Stopping instance c5b2e375-d7b2-442d-4141-a23b
   2017-12-06T16:58:00.31-0800 [CELL/0] OUT Creating container
   2017-12-06T16:58:00.14-0800 [CELL/0] OUT Destroying container
   2017-12-06T16:58:00.43-0800 [CELL/0] OUT Successfully destroyed container
```

The maven `service-registry` profile simply adds in the following dependency:
```xml
	<profiles>
		<profile>
			<id>service-registry</id>
			<dependencies>
				<dependency>
					<groupId>io.pivotal.spring.cloud</groupId>
					<artifactId>spring-cloud-services-starter-service-registry</artifactId>
				</dependency>
			</dependencies>
		</profile>
	</profiles>
```

Not sure why pulling in the `io.pivotal.spring.cloud:spring-cloud-services-starter-service-registry` dependency prevents us from autowiring the `AmqpTemplate`!!
