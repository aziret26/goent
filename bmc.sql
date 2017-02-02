CREATE TABLE project(
	id int primary key auto_increment,
	project_title varchar(25),
	description varchar(25),
	project_date date
);

CREATE TABLE customer_segment(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE value_proposition(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE distribution_channels(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE customer_relationship(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE revenue_streams(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE key_resources(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE key_activities(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE key_partners(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE cost_structure(
	id int primary key auto_increment,
	c_title varchar(25),
	s_title varchar(25),
	repsonsibility varchar(25),
	c_hash varchar(4),
	s_hash varchar(4)
);

CREATE TABLE segment(
	id int primary key auto_increment,
	name varchar(25)
);

CREATE TABLE history(
	id int primary key auto_increment,
	project_id int,
	segment int,
	author int,
	modify_date date,
	FOREIGN KEY(segment) REFERENCES segment(id),
	FOREIGN KEY(author) REFERENCES user(id),
	FOREIGN KEY(project_id) REFERENCES project(id)
);

CREATE TABLE project_user(
	id int primary key auto_increment,
	user_id int,
	project_id int,
	user_role tinyint(1),
	activation_date date,
	last_active_date date,
	status tinyint(1),
	FOREIGN KEY(user_id) REFERENCES user(id),
	FOREIGN KEY(project_id) REFERENCES project(id)
);

CREATE TABLE bmc(
	id int primary key auto_increment,
	project_id int,
	customer_segment_id int,
	value_proposition_id int,
	distribution_channels_id int,
	customer_relationship_id int,
	revenue_streams_id int,
	key_resources_id int,
	key_activities_id int,
	key_partners_id int,
	cost_structure_id int,
	FOREIGN KEY(project_id) REFERENCES project(id),
	FOREIGN KEY(customer_segment_id) REFERENCES customer_segment(id),
	FOREIGN KEY(value_proposition_id) REFERENCES value_proposition(id),
	FOREIGN KEY(distribution_channels_id) REFERENCES distribution_channels(id),
	FOREIGN KEY(customer_relationship_id) REFERENCES customer_relationship(id),
	FOREIGN KEY(revenue_streams_id) REFERENCES revenue_streams(id),
	FOREIGN KEY(key_resources_id) REFERENCES key_resources(id),
	FOREIGN KEY(key_activities_id) REFERENCES key_activities(id),
	FOREIGN KEY(key_partners_id) REFERENCES key_partners(id),
	FOREIGN KEY(cost_structure_id) REFERENCES cost_structure(id)
);
