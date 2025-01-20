# MySQL Dockerfile
FROM mysql:latest

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD root_password
ENV MYSQL_DATABASE microservicesDB

# Copy the SQL startup script
COPY ./mysql-init-scripts/init-app.sql /docker-entrypoint-initdb.d/

# Expose MySQL port
EXPOSE 3306

