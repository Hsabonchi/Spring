Spring MVC looks for a file named `[servlet-name]-servlet.xml in the WEB-INF directory of your web application and creates the beans defined there,
overriding the definitions of any beans defined with the same name in the global scope.

  <code> 
            <web-app>

                <servlet>
                    <servlet-name>golfing</servlet-name>
                    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
                    <load-on-startup>1</load-on-startup>
                </servlet>

                <servlet-mapping>
                    <servlet-name>golfing</servlet-name>
                    <url-pattern>/golfing/*</url-pattern>
                </servlet-mapping>

            </web-app>

</code> 
With the above Servlet configuration in place, you will need to have a file called /WEB-INF/golfing-servlet.xml in your application; this file will contain all of your Spring Web MVC-specific components (beans).
  

<code> 
              @Controller
              public class HelloWorldController {

                  @RequestMapping("/helloWorld")
                  public String helloWorld(Model model) {
                      model.addAttribute("message", "Hello World!");
                      return "helloWorld";
                  }
              }
    </code>
