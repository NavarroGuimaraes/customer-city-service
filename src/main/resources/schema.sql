CREATE TABLE IF NOT EXISTS cities (  
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,  
    name VARCHAR(100) NOT NULL,  
    state VARCHAR(30) NOT NULL,
    is_enabled tinyint(1) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp null,
    is_deleted tinyint(1) not null  
);  
CREATE TABLE IF NOT EXISTS customers (  
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,  
    name VARCHAR(200) NOT NULL,  
    gender VARCHAR(20) NULL,
    birth_date TIMESTAMP NOT NULL,
    age SMALLINT NOT NULL,
    customer_city_id BIGINT NULL,
    is_enabled tinyint(1) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp null,
    is_deleted tinyint(1) not null
);  
ALTER TABLE customers ADD FOREIGN KEY ( customer_city_id ) REFERENCES cities( id ) ;