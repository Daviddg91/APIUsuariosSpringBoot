# APIUsuariosSpringBoot
API Rest con SpringBoot, con interfaz de clientes y roles en SpringSecurity, mapeado de tablas automático con Spring Data, subida de ficheros y renderizado con Thymeleaf, configuración de Beans custom, validaciones custom, Maven y funcionalidades CRUD

resour

spring.datasource.url=jdbc:mysql://host:3306/Database
spring.datasource.username=root
spring.datasource.password=
spring.jpa.open-in-view=true
server.port = 8080
 
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

# dont use pagination
#spring.data.rest.page-param-name=page
#spring.data.rest.limit-param-name=size

spring.thymeleaf.mode=HTML5

#render front pages wihtin restart spring
spring.thymeleaf.cache=false

#spring.thymeleaf.mode=LEGACYHTML5

#debug querys jpa
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=6MB


#spring mobile, no esta en uso
#spring.mobile.devicedelegatingviewresolver.enabled: true
#spring.mobile.sitepreference.enabled: true
