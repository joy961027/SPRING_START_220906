1)계정은 system 계정의로 접속
혹시 계정 비번을 까먹었다. 
sqlplus > sys/ as sysdba 로 접속한다

--tablespace 만들기
SQL> create tablespace shop
  2  datafile 'C:\oraclexe\app\oracle\oradata\XE\shop.dbf'
  3  size 10M;

Tablespace created.

SQL> --유저 생성하고 권한 부여
SQL> create user client
  2  identified by 1234
  3  default tablespace shop
  4  quota unlimited on shop;

User created.

SQL> grant create table, create sequence to client;

Grant succeeded.

SQL> grant create session to client;

Grant succeeded.

SQL> conn client/1234
Connected.

  1  create table admin(
  2     admin_id number primary key,
  3     user_id varchar(30),
  4     pass varchar(80),
  5     name varchar(30)
  6* )

Table created.


  1  create table admin(
  2     admin_id number primary key,
  3     user_id varchar(30),
  4     pass varchar(80),
  5     name varchar(30)
  6* )
SQL> create sequence seq_admin
  2  increment by 1
  3  start with 1;

Sequence created.

--상위 카테고리 
create table topcategory(
	topcategory_id number primary key,
	category_name varchar(30)
);

--하위 카테고리
create table subcategory(
	subcategory_id number primary key,
	category_name varchar(30),
	topcategory_id number,
	constraint fk_topcategory_subcategory foreign key (topcategory_id) references topcategory(topcatgory_id)
);

--상품
create table product(
	product_id number primary key
	,product_name varchar(80)
	,brand varchar(30)
	,price number default 0
	,discount number default 0
	,memo varchar(1000)
	,detail clob
	,product_img varchar(100)
	,subcategory_id number
	,constraint fk_subcategory_product foreign key (subcategory_id)
	 references subcategory(subcategory_id)
);

--sequence
create sequence seq_topcategory
increment by 1
start with 1;

create sequence seq_subcategory
increment by 1
start with 1;

create sequence seq_product
increment by 1
start with 1;

--회원테이블
create table member(
	member_id number primary key
	,customer_id varchar(30)
	,customer_name varchar(30)
	,customer_pass varchar(30)
	,customer_email varchar(50)
);

create sequence seq_member
increment by 1
start with 1

--결제 방법
create table paymethod(
	paymethod_id number primary key
	,payname varchar(30)
);


--주문 요약
create table ordersummary(
	ordersummary_id number primary key
	,member_id number			--누가 fk
	,paymethod_id number		--어떤 결제 방법 fk
	,totalbuy number default 0 -- 얼마나
	,totalpay nubmer default 0 --실제 결제금
	,buydate date default sysdate -- 언제 샀어
	,constraint fk_member_ordersummery foreign key(member_id)
		references member(member_id)
	,constraint fk_paymethod_ordersummery foreign key(paymethod_id)
		references paymethod(paymethod_id)
);

create table orderdetail(
	orderdetail_id number primary key
	,product_id number 
	,ea number default 0
	,ordersummary_id number
	,constraint fk_product_orderdetail foreign key(product_id)
		references product(product_id)
	,constraint fk_ordersummary_orderdetail foreign key(ordersummary_id)
		references ordersummary(ordersummary_id)
);

create sequence seq_paymethod
increment by 1
start with 1;

create sequence seq_ordersummary
increment by 1
start with 1;

create sequence seq_orderdetail
increment by 1
start with 1;

INSERT  INTO PAYMETHOD VALUES(seq_paymethod.nextval, '카드결제');
INSERT  INTO PAYMETHOD VALUES(seq_paymethod.nextval, '핸드폰');
INSERT  INTO PAYMETHOD VALUES(seq_paymethod.nextval, '가상계좌');
INSERT  INTO PAYMETHOD VALUES(seq_paymethod.nextval, '온라인입금');



















