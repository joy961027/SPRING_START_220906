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

SQL>