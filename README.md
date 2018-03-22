# spring-boot-oauth2-jwt

**Table of Contents**

- [Intro](#intro)
- [Building blocks](#building-blocks)
- [Run the application](#run-the-application)
- [Test the application](#test-the-application)
- [Contributions](#contributions)
- [License](#license)

### Intro

This is a sample Spring Boot application for securing REST API with OAuth2 and JWT. It can be used as a quick start
for your Spring Boot REST API project with fully functional security module.

### Building blocks

* [Spring Boot 1.5.10.RELEASE](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/)
* [OAuth2](https://oauth.net/2/)
* [JSON Web Tokens (JWT)](https://jwt.io/)
* [H2 Database Engine](http://www.h2database.com/)
* [Project Lombok](https://projectlombok.org/)

### Run the application

* Build the application using Gradle: `gradle clean build`
* Execute the resulting artifact (which can be found in build/libs) with: `java -jar spring-boot-oauth2-jwt.jar`

### Test the application

In the sample application Resource Owner Password Credentials Grant flow is implemented meaning that both client and
user credentials are required to obtain the access token. Please use next information to obtain the token:

* client: client
* secret: secret
* username: user
* password: user

Use the following command to generate the access token:
> `$ curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=user`

Access token response example:
```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "token_type": "bearer",
    "expires_in": 599,
    "scope": "read write",
    "jti": "d36284c3-0efe-4565-b721-4202c3203f7d"
}
```

Use the generated token to access protected resources.  

To find all users execute command:
> `curl http://localhost:8080/users -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."`

All users response example:
```
[
    {
        "id": 1,
        "name": "Ljubomir Paskali",
        "username": "user",
        "role": "USER_ROLE"
    },
    {
        "id": 2,
        "name": "John Doe",
        "username": "john.doe",
        "role": "USER_ROLE"
    }
]
```

To find a single user execute command:
> `curl http://localhost:8080/users/1 -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."`

Single user response example:
```
{
    "id": 1,
    "name": "Ljubomir Paskali",
    "username": "user",
    "role": "USER_ROLE"
}
```

### Contributions

Contributions are welcome. Please respect the [Code of Conduct](https://www.contributor-covenant.org/version/1/4/code-of-conduct.html).

### License

spring-boot-oauth2-jwt is licensed under the Apache License 2.0 License. See [LICENSE](LICENSE.md) for details.
