#  base image
FROM openjdk:17-jdk-slim

# Đặt thư mục làm thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR vào container
COPY target/*.jar app.jar

# Cấu hình port container
EXPOSE 8080

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
