# spring_backend_demo
Project and environment setting snippet using eclipse(or Intellij) & Spring(boot) 

## 1. demo_mvc
Dynamic Web Project -> Spring -> Spring MVC with JSP -> REST API Controller

## 2. demo_boot
Spring Boot Starter(Initializer) -> Spring MVC with JSP -> REST API Controller

## 3. demo_jwt
demo_boot + REST API based Authentication using Spring Security & JWT

## 4. demo_mail

Spring Boot Starter(Initializer) -> PostgreSQL as a database ->  JPA with JpaRepository -> Spring Security with UserDetail(Service) -> Token Based Registration -> email authentication using MailDev

## Prerequisite

### Java Version
```
openjdk version "1.8.0_275"
```

### Used Database
```
Server version: 10.5.8-MariaDB mariadb.org binary distribution
```
### Database Schema
```sql
-- create database
create database java_db;
-- username, password
create user 'scott'@'localhost' identified by 'tiger';
grant all privileges on java_db.* to 'jb'@'localhost';
-- create table
create table users(
    id int(10) not null auto_increment primary key ,
    userid varchar(100) not null,
    name varchar(100) not null,
    gender varchar(10),
    city varchar(100),
    regdate datetime default now()
);
-- create index(userid)
alter table users add unique index users_userid_idx(userid);
```
