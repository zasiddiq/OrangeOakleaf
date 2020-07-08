DROP DATABASE IF EXISTS OrangeOakleaf;
CREATE DATABASE OrangeOakleaf;
USE OrangeOakleaf;

DROP TABLE IF EXISTS 'user';
CREATE TABLE 'user' (
	id bigint(50) NOT NULL auto_increment,
	username varchar(255) default NULL,
	password varchar(255) default NULL,
 	PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE 'hotspot' (
	id bigint(50) NOT NULL auto_increment,
	type1 int(50),
	type2 int(50),
	PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
