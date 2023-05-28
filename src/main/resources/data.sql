insert into roles (rolename) values ('USER'), ('ADMIN');

insert into users (user_id, username, password) values (1, 'Admin', '$2a$10$yyHn1Kwoog7tFlBmN.k7yeWQ8p3AU0DwdxtBkbhXhyK2Z/OF./gRu'), (2, 'User', '$2a$10$yyHn1Kwoog7tFlBmN.k7yeWQ8p3AU0DwdxtBkbhXhyK2Z/OF./gRu');
insert into users_roles(users_user_id, roles_rolename) values (1, 'ADMIN'),(1, 'USER'), (2, 'USER');
----
--insert into areas (id, name, address, description) values (100, 'Avalonia', 'Duitsland', 'Dug by Daniel the caveman');
----insert into blocks (id, block_name, stone_type, area_id) values (100, 'Cave', 'Granite', 100), (101, 'Slab', 'Granite', 100),(102, 'Slope', 'Balast', 100);
----insert into boulders (id, boulder_grade, boulder_image, boulder_name, boulder_notes, hold_type, block_id) values (100, '7a', 'image', 'Cyrano','HIGH, dangerous', 'Slopers', 100), (101, '7a+', 'image', 'Pueblo','Overhang and Compression boulder', 'Crimps', 101);
----insert into attempts (id, send, notes, video, user_id, boulder_id) values (100, 'false', 'Pull harder next time..', 'Videofile', 100, 100), (101, 'true', 'Topped the boulder!', 'Videofile', 100, 100), (102, 'true', 'Yess!', 'Videofile', 101, 101)
----
