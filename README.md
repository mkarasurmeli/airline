## Proje Hakkında
Havayolları biletleme sistemi için bir backend servis uygulaması geliştirildi. \
Serviste havayolu şirketi, havaalanı, rota, uçuş ve bilet için CRUD işlemleri yapıldı.  
Aynı zamanda bilet kayıt işlemi esnasında kredi kartı maskeleme işlemi ve bilet iptali gerçekleştirme işlemleri mevcut.  
 
Kullanılan teknolojiler
- JAVA 8
- Maven
- Spring Boot
- Hibernate (JPA,JPQL) 
- H2 Database
- Restful Web Service (JSON response)
- i18n message localization
- Swagger, mapstruct, lombok 

### Yükleme

```
mvn clean compile
```
### Servislere ait dokümantasyona swagger ui üzerinden ulaşılabilir.
Swagger-ui url: http://localhost:8080/swagger-ui/ \