# User Management & UserProfile MicroService

## Yêu cầu hệ thống
Để chạy hai microservice này, bạn cần cài đặt các công cụ sau trên máy:

1. **MySQL**
   - Phiên bản: 8.0 trở lên.
2. **Java Development Kit (JDK)**
   - Phiên bản: 17.
3. **Maven**
   - Phiên bản: 3.6.0 trở lên.
4. **Postman hoặc công cụ API tương tự** (không bắt buộc) để kiểm tra API.

## Cài đặt MySQL

1. Tải và cài đặt MySQL từ [trang chủ MySQL](https://dev.mysql.com/downloads/).
2. Tạo một database mới:
   ```sql
   CREATE DATABASE test3;
   ```
3. Cấu hình tài khoản MySQL (username và password).
   - Username mặc định: `root`
   - Password: `password` (thay đổi nếu cần).
4. Cập nhật file `application.properties` hoặc `application.yml` của từng microservice với thông tin kết nối MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/test3
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

## Hướng dẫn chạy User Management MicroService

1. Chuyển vào thư mục `UserManagementService`:
   ```bash
   cd UserManagementService
   ```
2. Cài đặt các dependencies bằng Maven:
   ```bash
   mvn clean install
   ```
3. Chạy ứng dụng:
   ```bash
   mvn spring-boot:run
   ```
4. API sẽ khả dụng tại: `http://localhost:8080`
5. Kiểm tra các endpoint chính bằng Postman:
   - **Đăng ký người dùng:**
     ```http
     POST /api/v1/users
     ```
   - **Lấy danh sách người dùng:**
     ```http
     GET /api/v1/users
     ```

## Hướng dẫn chạy UserProfile MicroService

1. Chuyển vào thư mục `UserProfileMicroService`:
   ```bash
   cd UserProfileMicroService
   ```
2. Cài đặt các dependencies bằng Maven:
   ```bash
   mvn clean install
   ```
3. Chạy ứng dụng:
   ```bash
   mvn spring-boot:run
   ```
4. API sẽ khả dụng tại: `http://localhost:8081`
5. Kiểm tra các endpoint chính bằng Postman:
   - **Thêm hồ sơ người dùng:**
     ```http
     POST /api/v1/profiles
     ```
   - **Lấy hồ sơ người dùng theo ID:**
     ```http
     GET /api/v1/profiles/{id}
     ```

## Chạy toàn bộ hệ thống

1. Đảm bảo MySQL đã chạy và database `user_management` đã được tạo.
2. Chạy lần lượt hai microservice theo hướng dẫn trên.
3. Sử dụng Postman hoặc frontend để kiểm tra luồng hoạt động của hệ thống.

## Ghi chú
- Đảm bảo rằng các cổng (ports) của hai service không bị trùng lặp.
- Nếu cần thay đổi cổng, cập nhật trong file `application.properties` của từng service:
  ```properties
  server.port=8080
  ```

## Liên hệ
Nếu gặp vấn đề khi chạy hệ thống, vui lòng liên hệ qua email: `tienthanh11042003@gmail.com`. Chúng tôi sẵn sàng hỗ trợ!
