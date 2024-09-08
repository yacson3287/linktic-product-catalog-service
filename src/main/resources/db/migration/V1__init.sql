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