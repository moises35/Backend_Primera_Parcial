## Pasos para ejecutar

1. Configurar el archivo `standalone.xml`
    ```xml
    <connection-url>jdbc:postgresql://localhost:5432/backendElectiva</connection-url>
    <driver>postgres</driver>
    <pool>
        <max-pool-size>20</max-pool-size>
    </pool>
    <security>
        <user-name>postgres</user-name>
        <password>admin</password>
    </security>
    ```

2. Iniciar el servidor de Wildfly 
   ```sh
   C:\servers\wildfly-18.0.1.Final\bin>standalone.bat
   ```

