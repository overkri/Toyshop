CREATE TABLE "products" (
                        id                      int8,
                        product_name            TEXT,
                        type                    TEXT,
                        quantity                int,
                        cost                    int,
                        CONSTRAINT product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );
CREATE SEQUENCE "product_id_seq" START 1;

CREATE TABLE "available_products" (
                        id                      int8,
                        product_id              int8,
                        quantity                int,
                        product_location        TEXT,
                        CONSTRAINT available_product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );

CREATE SEQUENCE "available_product_id_seq" START 1;

CREATE TABLE "removed_products" (
                        id                      int8,
                        product_id              int8,
                        storage_removed_date    DATE,
                        quantity                int,
                        product_location        TEXT,
                        CONSTRAINT removed_product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );
CREATE SEQUENCE "removed_product_id_seq" START 1;

CREATE TABLE "added_products" (
                        id                      int8,
                        product_id              int8,
                        storage_added_date      DATE,
                        quantity                int,
                        product_location        TEXT,
                        CONSTRAINT added_product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );
CREATE SEQUENCE "added_product_id_seq" START 1;

CREATE TABLE "sold_products" (
                        id                      int8,
                        order_number            int8,
                        product_id              int8,
                        sold_date               DATE,
                        quantity                int,
                        product_location        TEXT,
                        CONSTRAINT sold_product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );
CREATE SEQUENCE "sold_product_id_seq" START 1;