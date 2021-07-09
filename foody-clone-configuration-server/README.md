
# foody-clone-configuration-server
This project is a config server that can communicate with other applications.
## Running the application
You can run the application in dev mode by the command:
 ```
 mvn spring-boot:run
 ```
 ## How to read configurations from this application
 1. Add the dependency : <b>io.quarkus.quarkus-spring-cloud-config-client</b> to the client application
 2. Add these configuration to client's application.properties <br /> 
```json
quarkus.spring-cloud-config.enabled=true
quarkus.spring-cloud-config.url=http://localhost:8888
```
 3.  Run the server application
 4. Run the client application
## Adding a new configuration
You can add new configurations to profiles:
* [Dev profile](https://github.com/aavn-ct-workshop/configuration-files/blob/main/foody-clone-dev.properties)
* [Production profile](https://github.com/aavn-ct-workshop/configuration-files/blob/main/foody-clone-prod.properties)
