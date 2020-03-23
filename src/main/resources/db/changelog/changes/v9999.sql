INSERT INTO products (id, product_name, type, cost)
values (nextval('product_id_seq'), 'Barbie doll', 'DOLL', 1250),
       (nextval('product_id_seq'), 'MonsterX', 'RADIO_CAR', 5750),
       (nextval('product_id_seq'), 'Plush lion', 'ANIMAL', 450),
       (nextval('product_id_seq'), 'Rubiks Cube', 'EDUCATIONAL', 200),
       (nextval('product_id_seq'), 'Ken doll', 'DOLL', 1250),
       (nextval('product_id_seq'), 'Darth Vader', 'ACTION_FIGURE', 4655),
       (nextval('product_id_seq'), 'Plush dog', 'ANIMAL', 370),
       (nextval('product_id_seq'), 'Labyrinth Puzzle', 'EDUCATIONAL', 600),
       (nextval('product_id_seq'), 'Neocube', 'EDUCATIONAL', 780);

Insert INTO locations (id, location_name)
values (nextval('location_id_seq'), 'Kazan, Pushkina 9 '),
       (nextval('location_id_seq'), 'Moscow, Pushkina 9 '),
       (nextval('location_id_seq'), 'Samara, Pushkina 9 '),
       (nextval('location_id_seq'), 'St-Petersburg, Pushkina 9 '),
       (nextval('location_id_seq'), 'Innopolis, Pushkina 9 '),
       (nextval('location_id_seq'), 'Perm, Pushkina 9 ');


INSERT INTO available_products (id, product_id, quantity, product_location_id)
values (nextval('available_product_id_seq'), 1, 64, 1),
       (nextval('available_product_id_seq'), 1, 15, 2),
       (nextval('available_product_id_seq'), 2, 50, 3),
       (nextval('available_product_id_seq'), 3, 70, 6),
       (nextval('available_product_id_seq'), 4, 10, 5),
       (nextval('available_product_id_seq'), 6, 20, 3),
       (nextval('available_product_id_seq'), 7, 18, 4),
       (nextval('available_product_id_seq'), 5, 22, 2),
       (nextval('available_product_id_seq'), 8, 72, 1);

insert into sold_invoice_info(id, date, sum, invoice_id, product_location_id)
values (nextval('sold_info_id_seq'), '2017-1-1', 2150, 499989, 1),
       (nextval('sold_info_id_seq'), '2019-1-1', 2150, 763451, 1),
       (nextval('sold_info_id_seq'), '2012-1-1', 2150, 699989, 1),
       (nextval('sold_info_id_seq'), '2016-1-1', 1, 333333, 1),
       (nextval('sold_info_id_seq'), '2018-1-1', 2150, 099989, 2),
       (nextval('sold_info_id_seq'), '2017-1-1', 2150, 499444, 2),
       (nextval('sold_info_id_seq'), '2019-1-1', 2150, 643321, 3),
       (nextval('sold_info_id_seq'), '2013-1-1', 2150, 907651, 3),
       (nextval('sold_info_id_seq'), '2015-1-1', 2150, 340000, 3),
       (nextval('sold_info_id_seq'), '2016-1-1', 2150, 100000, 4);

insert into changed_products(id, product_id, date, quantity, product_location_id, status, invoice_id)
values (nextval('changed_product_id_seq'), 1, '2016-2-2', 20, 1, 'SOLD', 165432),
       (nextval('changed_product_id_seq'), 2, '2016-6-18', 20, 1, 'SOLD', 316542),
       (nextval('changed_product_id_seq'), 3, '2016-12-12', 20, 1, 'REMOVED', 456321),
       (nextval('changed_product_id_seq'), 4, '2016-8-8', 20, 1, 'ADDED', 124563),
       (nextval('changed_product_id_seq'), 5, '2016-9-9', 20, 2, 'SOLD', 321654),
       (nextval('changed_product_id_seq'), 6, '2016-10-10', 20, 2, 'SOLD', 321456),
       (nextval('changed_product_id_seq'), 3, '2016-4-4', 20, 3, 'SOLD', 432156),
       (nextval('changed_product_id_seq'), 2, '2016-5-5', 20, 4, 'SOLD', 564321),
       (nextval('changed_product_id_seq'), 1, '2016-6-6', 20, 1, 'REMOVED', 654321);