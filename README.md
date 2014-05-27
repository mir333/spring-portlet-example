This repository contains a simple spring portlet. It is intended as a step by step tutorial. 

1. Create a new maven web application project. Maven archetype can be used to generate the application (e.g. com.liferay.maven.archetypes:liferay-portlet-archetype). ```mvn archetype:generate```
2. Add dependencies on Spring into pom.xml.
 
  ```
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc-portlet</artifactId>
    <version>3.2.6.RELEASE</version>
  </dependency>
 ```
3. Insert spring initalization code inot web.xml.
 * Insert spring context listener
   
  ```
      <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
      </listener>
   ```
 * Specify the application spring configuration file location
   
  ```
      <context-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>/WEB-INF/spring-context/portlet-application-context.xml</param-value>
      </context-param>
   ```
 * Add servlet for view processing
   
  ```
      <servlet>
          <servlet-name>ViewRendererServlet</servlet-name>
          <servlet-class>org.springframework.web.servlet.ViewRendererServlet</servlet-class>
          <load-on-startup>1</load-on-startup>
      </servlet>
  
      <servlet-mapping>
          <servlet-name>ViewRendererServlet</servlet-name>
          <url-pattern>/WEB-INF/servlet/view</url-pattern>
      </servlet-mapping>
   ```
4. Create the application configuration file *portlet-application-context.xml*.
5. Activate annotation and configure view resolver in main context file.
  
  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="
         http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
         http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
         http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
         http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">
  
      <!--Enables @Autowired annotation-->
      <context:annotation-config/>
  
      <!-- Spring MVC VIEW Configuration -->
      <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
          <property name="contentType" value="text/html;charset=UTF-8" />
          <property name="prefix" value="/WEB-INF/jsp/" />
          <property name="suffix" value=".jsp" />
      </bean>
  </beans>
  ```
6. Define spring portlet
 * Define portlet in portlet.xml
   
  ```
    <portlet>
        <portlet-name>BasicSpringPortlet</portlet-name>
        <portlet-class>org.springframework.web.portlet.DispatcherPortlet</portlet-class>
        <init-param>
            <name>contextConfigLocation</name>
            <value>/WEB-INF/spring-context/portlet/basic-spring-portlet.xml</value>
        </init-param>
        <supports>
            <mime-type>text/html</mime-type>
            <portlet-mode>VIEW</portlet-mode>
        </supports>
        <supported-locale>en</supported-locale>
        <supported-locale>cs</supported-locale>
        <resource-bundle>content.basic-spring-portlet</resource-bundle>
    </portlet>
   ```
 * Configure portlet in liferay-portlet.xml
   
  ```
    <portlet>
        <portlet-name>BasicSpringPortlet</portlet-name>
        <instanceable>false</instanceable>
        <header-portlet-css>/css/main.css</header-portlet-css>
        <header-portlet-javascript>/js/main.js</header-portlet-javascript>
    </portlet>
   ```
 * Configure portlet in liferay-display.xml
  
   ```
  	<category name="category.sample">
          <portlet id="BasicSpringPortlet" />
  	</category>
   ```
 * Create portlet spring context file
  
   ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!--suppress SpringFacetInspection -->
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    
        <!-- Spring MVC Message Source -->
        <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
            <property name="useCodeAsDefaultMessage" value="true"/>
            <property name="basenames">
                <list>
                    <value>content.basic-spring-portlet</value>
                </list>
            </property>
        </bean>
    
    </beans>
   ```
 * Create resource bundles
7. Create Spring Controller
  
  ```java
  package eu.ibacz.sample.portlet.bascispring;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.portlet.bind.annotation.RenderMapping;
  
  /**
   * This class is base controller for VIEW mode.
   */
  @Controller
  @RequestMapping("VIEW")
  public class BasicSpringPortletViewController {
  
  
      @RenderMapping
      public String doView() {
          return BasicSpringPortletConstants.MAIN_VIEW;
      }
  
  
  }
  ```
8. Create view - *view.jsp*  
9. Add component scan to spring portlet configuration.

  ```
   <context:component-scan base-package="eu.ibacz.sample.portlet.bascispring.**"/>
  ```
10. 
