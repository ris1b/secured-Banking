# CORS

### @CrossOrigin Annotation

- `@CrossOrigin` can be used at class level or method level.
- it is used to tell that from which origins we want to accept communication

#### Example: 
1. To specify a particular domain 
    ``@CrossOrigin(origins = "http://loacalhost:4200")``

2. Allow any domain
    ``@CrossOrigin(origins = "*")``

### Pre-Flight request
- Browsers makes a Pre-flight request. eg
  - we want to invoke notices API from UI to the Backend application, here the Browser
  knows that it is a Cross Origin communication
  - For a Cross Origin communication, the browser won't send the Notices API. Browser will
  make a Pre-flight request to the Backend stating that 
  "xyz CrossOrigin is trying to communicate with Backend"
  - If the Backend server accepts the "xyz CrossOrigin", then the Browser will
  send the actual Notices API request to the backend server.
- Backend communicates to the Browser via the configurations
- We can define the configurations globally with the help of Spring Security Framework.
We can define the CORS configuration inside the SecurityFilterChain Bean
- Using the  CORS configuration we can tell the Browser about 
  - the allowed origins,
  - the allowed methods (HTTP methods), 
  - allowed credentials (to accept and pass the credentials to-and-fro from this application)
  - the allowed headers
  - the max age (browser will cache these configs details till the time specified)
- 