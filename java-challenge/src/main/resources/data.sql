insert into point_of_sale (name) VALUES
                                                    ('CABA'),
                                                    ('GBA_1'),
                                                    ('GBA_2'),
                                                    ('Santa Fe'),
                                                    ('Córdoba'),
                                                    ('Misiones'),
                                                    ('Salta'),
                                                    ('Chubut'),
                                                    ('Santa Cruz'),
                                                    ('Catamarca');
insert into point_of_sale_cost (ida, idb, cost) VALUES
                                                                   (1,2,2),
                                                                   (1,3,3),
                                                                   (2,3,5),
                                                                   (2,4,10),
                                                                   (1,4,11),
                                                                   (4,5,5),
                                                                   (2,5,14),
                                                                   (6,7,32),
                                                                   (8,9,11),
                                                                   (10,7,5),
                                                                   (3,8,10),
                                                                   (5,8,30),
                                                                   (10,5,5),
                                                                   (4,6,6);

INSERT INTO roles (id, name)
SELECT 1, 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_USER');

INSERT INTO roles (id, name)
SELECT 2, 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_ADMIN');