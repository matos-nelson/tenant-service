CREATE TABLE IF NOT EXISTS tenant (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  manager_id varchar(255) NOT NULL,
  property_id bigint NOT NULL,
  preferred_name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  full_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY manager_id_idx (manager_id),
  KEY property_id_idx (property_id),
  INDEX email_idx (email)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS tenant_vehicle (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  tenant_id bigint NOT NULL,
  make varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  model varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  year_made integer DEFAULT NULL,
  color varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  license_num varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY tenant_id_idx (tenant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS tenant_occupant (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  tenant_id bigint NOT NULL,
  first_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  last_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  date_of_birth date NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY tenant_id_idx (tenant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS tenant_pet (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  tenant_id bigint NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  breed varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  weight integer DEFAULT NULL,
  age TINYINT DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY tenant_id_idx (tenant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;