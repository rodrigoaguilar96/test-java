-- Crear la tabla brand
CREATE TABLE IF NOT EXISTS brand
(
    brand_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL
);

-- Crear la tabla product
CREATE TABLE IF NOT EXISTS product
(
    product_id   BIGINT PRIMARY KEY,
    brand_id     BIGINT,
    product_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brand (brand_id)
);

-- Crear la tabla prices
CREATE TABLE IF NOT EXISTS prices
(
    price_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id   BIGINT,
    start_date TIMESTAMP      NOT NULL,
    end_date   TIMESTAMP      NOT NULL,
    price_list BIGINT         NOT NULL,
    product_id BIGINT,
    priority   BIGINT         NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    curr       VARCHAR(3)     NOT NULL,
    CONSTRAINT fk_prices_brand FOREIGN KEY (brand_id) REFERENCES brand (brand_id),
    CONSTRAINT fk_prices_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);
-- Agregado de indices
CREATE INDEX idx_prices_query_optimization ON prices (product_id, brand_id, start_date, end_date, priority);
CREATE INDEX idx_brand_name ON brand (brand_name);