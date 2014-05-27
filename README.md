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
10. Add a simple form to jsp file.

 ```jsp
 <%@ page import="eu.ibacz.sample.portlet.bascispring.BasicSpringPortletConstants" %>
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
 
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
 <c:set var="ns"><portlet:namespace/></c:set>
 
 <spring:message code="basicspring-question"/>
 <br/>
 <portlet:actionURL var="actionUrl" name="<%=BasicSpringPortletConstants.TEST_ACTION%>"/>
 <form action="${actionUrl}" method="POST">
     <input type="text" name="${ns}<%=BasicSpringPortletConstants.NAME_PARAM%>">
     <input type="submit" value="<spring:message code="basicspring-submit"/>" />
 </form>
 ```
11. Handle the form data in controler.

 ```java
     @ActionMapping(BasicSpringPortletConstants.TEST_ACTION)
     public void doAction(@RequestParam(BasicSpringPortletConstants.NAME_PARAM) String name) {
         LOG.warn("I've got name " + name);
     }
 
 ```
12. Add a greetings view.
 * Add additional render method to the controller
  ```java
    @RenderMapping(params = PARAM_VIEW + "=" + GREETING)
    public String greeting(
            @RequestParam(NAME_PARAM) String name,
            Model model) {
        model.addAttribute(NAME_PARAM,name);
        return GREETING_VIEW;
    }

    @ActionMapping(TEST_ACTION)
    public void doAction(ActionRequest request, ActionResponse response) {
        LOG.warn("Processing name.");

        //order if these lines is important!
        response.setRenderParameters(request.getParameterMap());
        response.setRenderParameter(PARAM_VIEW, GREETING);
    }
  ```
 * Add *greetings.jsp*

  ```
   <%@page contentType="text/html" pageEncoding="UTF-8" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
   
   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
   
   
   <spring:message code="basicspring-greeting"/>&nbsp;<b><c:out value="${name}"/></b>!!!
   <br/>
   <a href="<portlet:renderURL />"><spring:message code="basicspring-back"/> </a>
  ```
13. Create transfer object *PersonPto*

 ```java
   public class PersonPto {
      private String name;
      private DateTime dateOfBirth;
  
      public DateTime getDateOfBirth() {
          return dateOfBirth;
      }
  
      public void setDateOfBirth(DateTime dateOfBirth) {
          this.dateOfBirth = dateOfBirth;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      @Override
      public String toString() {
          return "PersonPto{" +
                  "dateOfBirth=" + dateOfBirth +
                  ", name='" + name + '\'' +
                  "} " + super.toString();
      }
  }
 ```
13. Switch from parameters to model attribute.

 ```java
 @ActionMapping(TEST_ACTION)
    public void doAction(
            @ModelAttribute(PERSON_PTO) PersonPto personPto,
            BindingResult result,
            ActionResponse response) {
        LOG.warn("Processing person " + personPto);

        response.setRenderParameter(PARAM_VIEW, GREETING);
    }
 ```
14. Register InitBinder for joda time.

 ```java
     @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DateTime.class, new JodaDateEditor(DATE_TIME_PATTERN));
    }
 ```
15. Change the simple form to spring form.

 ```jsp
 <portlet:actionURL var="actionUrl" name="<%=TEST_ACTION%>"/>
<form:form action="${actionUrl}" method="POST" modelAttribute="<%=PERSON_PTO%>">
    <p>
        <form:label path="name" for="${ns}name"><spring:message code="basicspring-form-name"/></form:label>
        <form:input path="name" id="${ns}name"/>
    </p>
    <p>
        <form:label path="dateOfBirth" for="${ns}dateOfBirth"><spring:message code="basicspring-form-date-of-birth"/></form:label>
        <form:input path="dateOfBirth" id="${ns}dateOfBirth"/>
    </p>

    <input type="submit" value="<spring:message code="basicspring-submit"/>" />
</form:form>

 ```
16. Calculate days till birthday.

 ```jsp
     private Integer daysToBirthday(DateTime dateOfBirth) {
        DateTime now = (new DateTime()).withTimeAtStartOfDay();
        int year = now.getYear();
        DateTime birthday = dateOfBirth.withYear(year);
        if (birthday.isBeforeNow()) {
            birthday = birthday.plusYears(1);
        }
        return Days.daysBetween(now, birthday).getDays();
    }
 ```
17. Add validation
 * Create Validator
 
 ```java
   @Component
  public class PersonPtoValidator implements Validator {
      private static final DateTime minDate = DateTime.now().minusYears(130);
      private static final DateTime maxDate = DateTime.now().plusYears(130);
  
      @Override
      public boolean supports(Class<?> clazz) {
          return PersonPtoValidator.class.isAssignableFrom(clazz);
      }
  
      @Override
      public void validate(Object target, Errors errors) {
          ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "basicspring-err-null-value");
          ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "basicspring-err-null-value");
          if (!errors.hasErrors()) {
              PersonPto personPto = (PersonPto) target;
              if (personPto.getDateOfBirth().isBefore(minDate)) {
                  errors.rejectValue("dateOfBirth", "basicspring-err-to-early");
              }
              if (personPto.getDateOfBirth().isAfter(maxDate)) {
                  errors.rejectValue("dateOfBirth", "basicspring-err-to-late");
              }
          }
      }
  }

 ```
 * Use validator in action.
 
 ```java
        personPtoValidator.validate(personPto,result);
        if (!result.hasErrors()) {
            response.setRenderParameter(PARAM_VIEW, GREETING);
        }
 ```
 * Add validation error message placeholders.
 
 ```jsp
  <form:errors path="name"  element="span" cssClass="${errorClass}"/>
 ```
