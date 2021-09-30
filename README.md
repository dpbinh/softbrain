# softbrain

- using command for run project: mvn spring-boot:run
- access ui link: http://localhost:8080/swagger-ui.html

login POST http://localhost:8080/login 

{
    "username" : "admin",
    "password" : "123456"
}

call api :

add header Authorization  Bearer <Login Token>
