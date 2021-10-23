drop user 'root'@'172.17.0.1';
CREATE USER 'root'@'172.17.0.1' IDENTIFIED BY 'test';

DROP SCHEMA IF EXISTS `userservice`;
CREATE SCHEMA `userservice`;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'172.17.0.1' WITH GRANT OPTION;
USE `userservice` ;
CREATE TABLE IF NOT EXISTS `userservice`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;
CREATE TABLE IF NOT EXISTS `userservice`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `username` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,

  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;
create table `userservice`.`user_roles` (
       user_id bigint not null,
        roles_id bigint not null
    );
alter table `userservice`.`user_roles`
       add constraint fk_roles
       foreign key (roles_id)
       references role(id)   ;
alter table `userservice`.`user_roles`
       add constraint fk_roles1
       foreign key (user_id)
       references user(id);
