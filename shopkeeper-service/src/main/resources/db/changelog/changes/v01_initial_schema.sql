create table "shopkeeper_details"(
   id                  BIGSERIAL NOT NULL ,
                       PRIMARY KEY (id) ,
   name                VARCHAR(50) NOT NULL ,
   status              CHAR(50) NOT NULL ,
   created_at          TIMESTAMP NOT NULL ,
   updated_at          TIMESTAMP NOT NULL
);

create table "location_details"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   shopkeeper           BIGSERIAL NOT NULL ,
                        FOREIGN KEY (shopkeeper)  REFERENCES shopkeeper_details (id) ,
   address_line1        VARCHAR(256) ,
   address_line2        VARCHAR(256) ,
   postcode             VARCHAR(10) ,
   city                 VARCHAR(50) ,
   country              VARCHAR(50) ,
   contact_number       VARCHAR(15) ,
   email                VARCHAR(100) ,
   latitude             DOUBLE PRECISION NOT NULL ,
   longitude            DOUBLE PRECISION NOT NULL ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

create table "menu_details"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   name                 VARCHAR(50) NOT NULL ,
   shopkeeper           BIGSERIAL NOT NULL ,
                        FOREIGN KEY (shopkeeper)  REFERENCES shopkeeper_details (id) ,
   start_time           VARCHAR(15) ,
   end_time             VARCHAR(15) ,
   status               CHAR(50) NOT NULL ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

create table "menu_item_details"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   menu                 BIGSERIAL NOT NULL ,
                        FOREIGN KEY (menu)  REFERENCES menu_details (id) ,
   category             CHAR(50) NOT NULL ,
   sub_category         CHAR(50) NOT NULL ,
   item_name            VARCHAR(50) NOT NULL ,
   price                DECIMAL(15,2) NOT NULL ,
   quantity             INT NOT NULL ,
   preparing_duration   INT NOT NULL,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

create table "location_menu_details"(
   location_id          BIGSERIAL NOT NULL ,
                        FOREIGN KEY (location_id)  REFERENCES location_details (id) ,
   menu_id              BIGSERIAL NOT NULL ,
                        FOREIGN KEY (menu_id)  REFERENCES menu_details (id)
);

create table "purchaser_order_details"(
   id                         BIGSERIAL NOT NULL ,
                              PRIMARY KEY (id) ,
   shopkeeper                 BIGSERIAL NOT NULL ,
                              FOREIGN KEY (shopkeeper)  REFERENCES shopkeeper_details (id) ,
   location_id                BIGINT NOT NULL ,
   menu_id                    BIGINT NOT NULL ,
   purchaser_id                BIGINT NOT NULL ,
   status                     CHAR(50) NOT NULL ,
   pick_up_time               TIMESTAMP,
   order_preparing_time       TIMESTAMP,
   total                      DECIMAL(15,2) NOT NULL ,
   created_at                 TIMESTAMP NOT NULL ,
   updated_at                 TIMESTAMP NOT NULL
);

create table "purchaser_order_item_details"(
   id                   BIGSERIAL NOT NULL ,
                        PRIMARY KEY (id) ,
   menu_item_id         BIGINT NOT NULL,
   price                DECIMAL(15,2) NOT NULL ,
   quantity             INT NOT NULL ,
   purchaser_order      BIGSERIAL NOT NULL ,
                        FOREIGN KEY (purchaser_order)  REFERENCES purchaser_order_details (id) ,
   created_at           TIMESTAMP NOT NULL ,
   updated_at           TIMESTAMP NOT NULL
);

insert into shopkeeper_details (name, status, created_at, updated_at) values ( 'Starbucks', 'ACTIVE', now(), now());
insert into shopkeeper_details (name, status, created_at, updated_at) values ( 'Espresso', 'ACTIVE', now(), now());
insert into shopkeeper_details (name, status, created_at, updated_at) values ( 'Nescafe', 'ACTIVE', now(), now());


insert into location_details (shopkeeper, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 1, '35 A/ Paragatinager', 'near manva aashram', '123456', 'Mehesana', 'INDIA', '+91 9822989891',  'starbucks@abc.com', 51.449943, -0.3757451, now(), now());

insert into location_details (shopkeeper, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 1, '2 sai-baba nagar', '34 main road', '123456', 'Mehesana', 'INDIA', '+91 9822989891',  'starbucks@abc.com', 51.449657, -0.8757451, now(), now());

insert into location_details (shopkeeper, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 2, 'show 45 Road', 'shivam cross road', '123456', 'Mehesana', 'INDIA', '+91 9822989891',  'espresso@abc.com', 51.449243, -0.9757451, now(), now());

insert into location_details (shopkeeper, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 2, 'vaibhav vila road', 'near circle', '123456', 'Mehesana', 'INDIA', '+91 9822989891',  'espresso@abc.com', 53.459943, -0.1757451, now(), now());

insert into location_details (shopkeeper, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 3 , 'city Tower', '1 Ring Road', '123456', 'Mehesana', 'INDIA', '+91 9822989891',  'nescafehub@abc.com', 51.449943, -0.9757451, now(), now());

insert into location_details (shopkeeper, address_line1, address_line2, postcode, city, country, contact_number, email, latitude, longitude, created_at, updated_at)
values ( 3, 'city mall', 'raja garden', '123456', 'Mehesana', 'INDIA', '+91 9822989891',  'nescafehub@abc.com', 51.449963, -0.9757451, now(), now());



insert into menu_details (name, shopkeeper, start_time, end_time, status, created_at, updated_at)
values ('Super Noon', 1, '06:00 AM', '06:00 PM', 'ACTIVE', now(), now());

insert into menu_details (name, shopkeeper, start_time, end_time, status, created_at, updated_at)
values ('Max Morning', 1, '08:00 AM', '10:00 AM', 'ACTIVE', now(), now());

insert into menu_details (name, shopkeeper, start_time, end_time, status, created_at, updated_at)
values ('Evening Choice', 2, '08:00 AM', '05:00 PM', 'ACTIVE', now(), now());

insert into menu_details (name, shopkeeper, start_time, end_time, status, created_at, updated_at)
values ('Good Morning Brackfast', 2, '10:00 AM', '10:00 PM', 'ACTIVE', now(), now());

insert into menu_details (name, shopkeeper, start_time, end_time, status, created_at, updated_at)
values ('S1 super', 3, '11:00 AM', '01:00 PM', 'ACTIVE', now(), now());

insert into menu_details (name, shopkeeper, start_time, end_time, status, created_at, updated_at)
values ('Bring Super', 3, '02:00 AM', '06:00 PM', 'ACTIVE', now(), now());


insert into menu_item_details (menu, category, sub_category, item_name, price, quantity, preparing_duration, created_at, updated_at)
values ( 1, 'DRINK', 'SOFT_DRINKS', 'Pepsi', 20.00, 1, 2,now(), now());

insert into menu_item_details (menu, category, sub_category, item_name, price, quantity, preparing_duration, created_at, updated_at)
values ( 1, 'SNACK', 'COOKIES', 'Berger cookies', 30.00, 1, 10, now(), now());

insert into menu_item_details (menu, category, sub_category, item_name, price, quantity, preparing_duration, created_at, updated_at)
values ( 1, 'DRINK', 'COFFEE', 'Super Coffee', 50.00, 1, 12, now(), now());


insert into menu_item_details (menu, category, sub_category, item_name, price, quantity, preparing_duration, created_at, updated_at)
values ( 2, 'SNACK', 'BISCUITS', 'BourBorn', 20.00, 1, 6, now(), now());

insert into menu_item_details (menu, category, sub_category, item_name, price, quantity, preparing_duration, created_at, updated_at)
values ( 2, 'DRINK', 'HOT_COFFEE', 'hot coffee', 25.00, 1, 5, now(), now());

insert into menu_item_details (menu, category, sub_category, item_name, price, quantity, preparing_duration, created_at, updated_at)
values ( 3, 'DRINK', 'COLD_COFFEE', 'cold coffee max', 100.00, 1, 8, now(), now());


insert into location_menu_details (location_id, menu_id) values (1, 1);
insert into location_menu_details (location_id, menu_id) values (1, 2);
insert into location_menu_details (location_id, menu_id) values (2, 1);
insert into location_menu_details (location_id, menu_id) values (2, 2);
insert into location_menu_details (location_id, menu_id) values (3, 3);
insert into location_menu_details (location_id, menu_id) values (3, 4);
insert into location_menu_details (location_id, menu_id) values (4, 5);
insert into location_menu_details (location_id, menu_id) values (4, 6);