CREATE TABLE "products" (
                        id                      int8,
                        product_name            TEXT,
                        type                    TEXT,
                        cost                    int,

                        CONSTRAINT product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );
CREATE SEQUENCE "product_id_seq" START 1;

CREATE TABLE "available_products" (
                        id                      int8 NOT NULL UNIQUE,
                        product_id              int8,
                        quantity                int,
                        product_location_id     int8,
                        CONSTRAINT available_product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );

CREATE SEQUENCE "available_product_id_seq" START 1;

CREATE TABLE "changed_products" (
                        id                      int8 NOT NULL UNIQUE,
                        product_id              int8,
                        date                    DATE,
                        quantity                int,
                        product_location_id     int8,
                        status                  TEXT,
                        invoice_id              int8,
                        CONSTRAINT changed_product_pk PRIMARY KEY (id)
) WITH (
      OIDS=FALSE
    );
CREATE SEQUENCE "changed_product_id_seq" START 1;

CREATE TABLE "locations"(
                        id                      int8 NOT NULL UNIQUE,
                        location_name           TEXT,
                        CONSTRAINT location_pk PRIMARY KEY (id)
) WITH (
    OIDS=FALSE
);

CREATE SEQUENCE "location_id_seq" START 1;

CREATE TABLE "sold_invoice_info"(
                        id                      int8 NOT NULL UNIQUE,
                        date                    DATE,
                        invoice_id              int8,
                        sum                     int,
                        product_location_id     int8
) WITH (
    OIDS=FALSE
);

CREATE SEQUENCE "sold_info_id_seq" START 1;

