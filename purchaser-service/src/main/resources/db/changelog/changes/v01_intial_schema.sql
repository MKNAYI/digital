create table "purchaser_details"(
   id                  BIGSERIAL NOT NULL,
                       PRIMARY KEY (id) ,
   firstName           VARCHAR(50) NOT NULL ,
   lastName            VARCHAR(50) NOT NULL ,
   mobile_number       CHAR(15) ,
   address_line1       VARCHAR(256) ,
   address_line2       VARCHAR(256) ,
   city                VARCHAR(50) ,
   postcode            VARCHAR(10) ,
   country             VARCHAR(50) ,
   status              CHAR(50) NOT NULL ,
   addressType         CHAR(50) NOT NULL ,
   created_at          TIMESTAMP NOT NULL ,
   updated_at          TIMESTAMP NOT NULL
);

insert into purchaser_details (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Dinesh','Patel', '919898959898', 'green city', 'sector 23', 'Mahesana', '123456', 'INDIA', 'ACTIVE', 'HOME', now(), now());

insert into purchaser_details (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('chintan', 'Prajapati', '916898987798', 'xyz tower', 'CJ road', 'Mahesana', '123456', 'INDIA', 'ACTIVE', 'WORK', now(), now());

insert into purchaser_details (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Kishan', 'Rami', '919897987898', 'MO/201 House', 'near wing road', 'Mahesana', '123456', 'INDIA', 'ACTIVE', 'HOME', now(), now());

insert into purchaser_details (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Vishal', 'nayak', '919898990898', 'shop-21 good max', 'time tower cross road', 'Mahesana', '123456', 'INDIA', 'ACTIVE', 'WORK', now(), now());

insert into purchaser_details (firstName, lastName, mobile_number, address_line1, address_line2, city, postcode, country, status, addressType, created_at, updated_at)
values('Sanjay', 'Patel', '917398589898', '102/flat number', 'near GAS pump', 'Mahesana', '123456', 'INDIA', 'ACTIVE', 'HOME', now(), now());