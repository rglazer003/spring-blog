use blog_db;

create table uploads (
  id int unsigned not null auto_increment,
  filename varchar(100) not null,
  primary key (id)
)