use blog_db;

create table uploads (
  id int unsigned not null auto_increment,
  filename varchar(100) not null,
  primary key (id)
)

create table users (
  id int unsigned not null auto_increment,
  username varchar(50) not null,
  password varchar(100) not null,
  email varchar(100) not null,
  primary key (id)
)
