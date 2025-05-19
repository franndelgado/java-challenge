-- Inserta en point_of_sale solo si no existen
INSERT INTO point_of_sale (name)
SELECT 'CABA' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'CABA');
INSERT INTO point_of_sale (name)
SELECT 'GBA_1' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'GBA_1');
INSERT INTO point_of_sale (name)
SELECT 'GBA_2' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'GBA_2');
INSERT INTO point_of_sale (name)
SELECT 'Santa Fe' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Santa Fe');
INSERT INTO point_of_sale (name)
SELECT 'Córdoba' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Córdoba');
INSERT INTO point_of_sale (name)
SELECT 'Misiones' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Misiones');
INSERT INTO point_of_sale (name)
SELECT 'Salta' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Salta');
INSERT INTO point_of_sale (name)
SELECT 'Chubut' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Chubut');
INSERT INTO point_of_sale (name)
SELECT 'Santa Cruz' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Santa Cruz');
INSERT INTO point_of_sale (name)
SELECT 'Catamarca' WHERE NOT EXISTS (SELECT 1 FROM point_of_sale WHERE name = 'Catamarca');

-- Inserta en point_of_sale_cost solo si no existen
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 1, 2, 2 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 1 AND idb = 2 AND cost = 2);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 1, 3, 3 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 1 AND idb = 3 AND cost = 3);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 2, 3, 5 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 2 AND idb = 3 AND cost = 5);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 2, 4, 10 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 2 AND idb = 4 AND cost = 10);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 1, 4, 11 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 1 AND idb = 4 AND cost = 11);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 4, 5, 5 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 4 AND idb = 5 AND cost = 5);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 2, 5, 14 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 2 AND idb = 5 AND cost = 14);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 6, 7, 32 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 6 AND idb = 7 AND cost = 32);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 8, 9, 11 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 8 AND idb = 9 AND cost = 11);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 10, 7, 5 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 10 AND idb = 7 AND cost = 5);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 3, 8, 10 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 3 AND idb = 8 AND cost = 10);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 5, 8, 30 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 5 AND idb = 8 AND cost = 30);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 10, 5, 5 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 10 AND idb = 5 AND cost = 5);
INSERT INTO point_of_sale_cost (ida, idb, cost)
SELECT 4, 6, 6 WHERE NOT EXISTS (SELECT 1 FROM point_of_sale_cost WHERE ida = 4 AND idb = 6 AND cost = 6);

-- Inserta en roles solo si no existen
INSERT INTO roles (name)
SELECT 'ROLE_USER' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_USER');
INSERT INTO roles (name)
SELECT 'ROLE_ADMIN' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_ADMIN');