CREATE DATABASE order_ms;
CREATE USER 'order'@'%' IDENTIFIED BY 'order';
GRANT ALL PRIVILEGES ON order_ms.* TO 'order'@'%';

CREATE DATABASE IF NOT EXISTS customer_ms;
CREATE USER IF NOT EXISTS 'customer'@'%' IDENTIFIED BY 'customer';
GRANT ALL PRIVILEGES ON customer_ms.* TO 'customer'@'%';

FLUSH PRIVILEGES;