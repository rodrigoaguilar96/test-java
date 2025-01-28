-- Insertar las marcas (Brand)
INSERT
INTO brand (brand_id, brand_name)
VALUES (1, 'BrandB');
INSERT
INTO brand (brand_id, brand_name)
VALUES (0, 'BrandA');

-- Insertar los productos (Product)
INSERT
INTO product (product_id, brand_id, product_name)
VALUES (35455, 1, 'Producto BrandB');
-- Puedes agregar más productos si lo deseas

-- Insertar los precios (Price)
INSERT
INTO prices (price_id, product_id, brand_id, start_date, end_date, price_list, priority, price, curr)
VALUES (1, 35455, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 0, 35.50, 'EUR'),
       (2, 35455, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 1, 25.45, 'EUR'),
       (3, 35455, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 1, 30.50, 'EUR'),
       (4, 35455, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 1, 38.95, 'EUR');