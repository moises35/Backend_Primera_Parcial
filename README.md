## Configuraciones básicas del servidor

1. Descargar y descomprimir wildfly v26.1.3: https://www.wildfly.org/downloads/

2. Descargar JDBC postgresql v42.3.7: https://jdbc.postgresql.org/download/

3. En la ruta `wildfly-26.1.3.Final\modules\org\postgresql\main` pegar el JDBC del paso 2

4. En la ruta `wildfly-26.1.3.Final\modules\org\postgresql\main` crear un archivo `module.xml`: 
    ```xml
    <?xml version='1.0' encoding='UTF-8'?> 
 
    <module xmlns="urn:jboss:module:1.1" name="org.postgresql"> 
        <resources> 
            <resource-root path="postgresql-42.3.7.jar"/> 
        </resources> 
 
        <dependencies> 
            <module name="javax.api"/> 
            <module name="javax.transaction.api"/> 
        </dependencies> 
    </module> 
    ```
	
5. Configurar el archivo `standalone.xml` aprox. en la linea 120 por ahi, reemplazar el siguiente contenido:
    ```xml
    <subsystem xmlns="urn:jboss:domain:datasources:7.0">
        <datasources>
            <datasource jndi-name="java:jboss/datasources/pwb" pool-name="pwd" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
                <connection-url>jdbc:postgresql://localhost:5432/pwb</connection-url>
                <driver>postgresql</driver>
                <security>
                    <user-name>postgres</user-name>
                    <password>admin</password>
                </security>
            </datasource>
            <drivers>
                <driver name="postgresql" module="org.postgresql"> 
                    <driver-class>org.postgresql.Driver</driver-class> 
                </driver> 
            </drivers>
        </datasources>
    </subsystem>
    ```

6. Iniciar el servidor de Wildfly 
   ```sh
   C:\servers\wildfly-26.1.3.Final\bin>standalone.bat
   ```

7. El servidor ya está iniciado🚀

</br>
</br>


## Configuraciones para hacer el deploy

1. Iniciamos el `jboss-cli`
   ```sh
   C:\servers\wildfly-26.1.3.Final\bin>jboss-cli.bat
   ```

2. Ejecutamos el siguiente comando:
   ```sh
   deploy rutaDelWarGenerado
   ```

3. En caso de que ocurra un error del tipo `{"WFLYCTL0412: Required services that are not installed:" => ["jboss.naming.context.java.jboss.datasources.ExampleDS"]` ejecutar el siguiente comando:
   ```sh
    /subsystem=ee/service=default-bindings:undefine-attribute(name=datasource)
   ```

</br>
</br>

# RUTAS
host: `http://localhost:8080/backend`

+ Se puede importar el archivo `Postaman/postman_collection.json` en Postman para poder probar la API rapidamente.

## Rutas de la API cliente
+ `GET` /cliente
+ `GET` /cliente/all
+ `GET` /cliente/find/{id}
+ `POST` /cliente/add
+ `PUT` /cliente/update
+ `DELETE` /cliente/delete/{id}

## Rutas de la API premio
+ `GET` /premio
+ `GET` /premio/all
+ `GET` /premio/find/{id}
+ `POST` /premio/add
+ `PUT` /premio/update
+ `DELETE` /premio/delete/{id}
