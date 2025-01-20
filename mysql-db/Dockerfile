# MySQL Dockerfile
FROM mysql:latest

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD root_password
ENV MYSQL_USER taa
ENV MYSQL_PASSWORD 4141
ENV MYSQL_DATABASE gfgmicroservicesdemo

# Copy the SQL startup script
COPY ./mysql-init-scripts/init-app.sql /docker-entrypoint-initdb.d/

# Expose MySQL port
EXPOSE 3306

# Healthcheck for MySQL
HEALTHCHECK --interval=10s --timeout=5s --retries=3 CMD mysqladmin ping -h 127.0.0.1 || exit 1

# Start MySQL service
CMD ["docker-entrypoint.sh"]
