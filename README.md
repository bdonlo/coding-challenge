# Serai Coding Challenge

Coding Challenge from Serai.

Done by Brandon Lo @ 17/03/2022

## Basic Info

```
Port 8080

Database: embedded H2

Service call: No Istio service mesh. RestTemplate on localhost
```

## Commands
```
mvn test
mvn clean package
docker build -t <image name> .
docker run --name=<daemon name> -d -p 8080:8080 --rm <image name>:latest
```

## Basic auth for API and Swagger:

Credentials
```
user: serai
password: serai
```


## Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```
