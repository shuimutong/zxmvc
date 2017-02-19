# zxmvc
This project is to finish the springMVC work, now supply:
##annotation mvc
*	@XController
*	@XService
*	@XRepository
*	@XComponent
##IOC
*	@XAutowired singleton
*	You can use like follow:
```java
<servlet>
	<servlet-name>XDispatcherServlet</servlet-name>
	<display-name>XDispatcherServlet</display-name>
	<servlet-class>cn.ourpass.zxmvc.servlet.XDispatcherServlet</servlet-class>
	<load-on-startup>2</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>XDispatcherServlet</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```
