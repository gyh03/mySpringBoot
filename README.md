# mySpringBoot
SpringBoot demo
实现的功能有：
<br>
bean绑定，日期格式式化<br>
responsebody，json默认使用Jackson。日期格式化<br>
配置log4j<br>
配置aop<br>
配置拦截器interceptor<br>
controller统一异常处理<br>
使用xml文件定义sql<br>
log4j打印sql语句<br>
发送邮件<br>
使用注解配置filter和linstener<br>
filter、linstener和interceptor的执行顺序>
listener for ServletContext init>
filter init>
filter pre doFilter... 此处可以做 过滤敏感词、设置字符编码、url级别权限访问等控制>
interceptor ： preHandle ... 可以在此处验证是否登录>
目标方法>
interceptor ： postHandle...>
interceptor ： afterCompletion...

