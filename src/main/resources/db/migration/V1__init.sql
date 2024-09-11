CREATE TABLE "categories" (
  "id" serial PRIMARY KEY,
  "name" varchar(100),
  "description" varchar(500),
  "create_at" timestamp
);

CREATE TABLE "products" (
  "id" serial PRIMARY KEY,
  "name" varchar(100),
  "quantity" int,
  "price" numeric,
  "create_at" timestamp,
  "category_id" bigint
);

ALTER TABLE "products" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

INSERT INTO categories (name, description, create_at) VALUES
('Electrónica', 'Dispositivos electrónicos y accesorios', NOW()),
('Ropa', 'Prendas de vestir para todas las ocasiones', NOW());


INSERT INTO products (name, quantity, price, create_at, category_id) VALUES
('Laptop', 20, 800.00, NOW(), 1),
('Tablet', 15, 350.00, NOW(), 1),
('Auriculares inalámbricos', 50, 80.00, NOW(), 1),
('Smartwatch', 30, 200.00, NOW(), 1),
('Altavoz Bluetooth', 40, 50.00, NOW(), 1),
('Consola de videojuegos', 10, 400.00, NOW(), 1),
('Cámara digital', 15, 300.00, NOW(), 1),
('Disco duro externo', 30, 100.00, NOW(), 1),
('Impresora', 10, 200.00, NOW(), 1),
('Cargador portátil', 60, 25.00, NOW(), 1),
('Pantalón jeans', 50, 40.00, NOW(), 2),
('Vestido', 30, 60.00, NOW(), 2),
('Sudadera', 40, 30.00, NOW(), 2),
('Zapatos deportivos', 60, 50.00, NOW(), 2),
('Chaqueta', 20, 80.00, NOW(), 2),
('Bañador', 30, 25.00, NOW(), 2),
('Calcetines', 100, 5.00, NOW(), 2),
('Corbata', 20, 15.00, NOW(), 2),
('Bufanda', 30, 10.00, NOW(), 2),
('Gorro', 40, 8.00, NOW(), 2);