create table `user`(
	`id`          integer auto_increment primary key,
    `first_name`  varchar(50)  not null,
    `last_name`   varchar(50)  not null,
    `email`       varchar(250) not null,
    `password`    varchar(250) not null,
    `description` varchar(250),
    `active`      boolean not null,
    `created_at`  datetime not null,
    `updated_at`  datetime
);

create table `user_role`(
	`id`      integer auto_increment primary key,
    `role`    varchar(50),
    `user_id` integer,
    foreign key (`user_id`) references `user`(`id`)
);