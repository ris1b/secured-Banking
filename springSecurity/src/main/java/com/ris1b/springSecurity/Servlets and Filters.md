# Servlets & Filters

Any java web application accepts the request and send the response via HTTP protocol.
Using the HTTP Protocols, the browsers can send a request to the backend web application.

Servlet container or Web Servers sits between the Java App and the browser.
Eg. Apache Tomcat is a WebServer.

These containers convert the HTTP Message from the Browser into an HTTP Servlet Request Object,and this Request object will be 
passed to the Spring Framework.

While sending the Response back to the Browser, the Servlet container takes the object of the 
Http Servlet Response and again converts back it to the HTTP Servlet message.

Similarly, we have Filters inside the Web Application. Filters are special kind of servlets which 
we can use to intercept each and every request that is coming to our web application. 

## Spring Security Internal Flow
``Give link to internal flow
``

