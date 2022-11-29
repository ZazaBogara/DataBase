Use lab2;

-- Task 1
SELECT model, type, price
FROM printer
WHERE price < 300
ORDER BY type;

-- Task 2
SELECT name
FROM battles
WHERE name NOT LIKE '%c'; 

-- Task 3
SELECT name, ships.class, country, launched
FROM ships LEFT JOIN classes ON classes.class = ships.class
ORDER BY launched;

-- Task 4
SELECT distinct maker
FROM product as p
WHERE NOT
	p.maker = ANY (SELECT maker from product where type = 'laptop')
    AND type = 'PC';

-- Task 5
SELECT name, launched, displacement
FROM ships LEFT JOIN classes ON ships.class = classes.class
WHERE launched > 1922 AND displacement > 35000;

-- Task 6
SELECT CONCAT('code:', code) code,  CONCAT('model:', model) model,  CONCAT('speed:', speed) speed,  CONCAT('ram:', ram) ram,  CONCAT('hd:', hd) hd,  CONCAT('cd:', cd) cd,  CONCAT('price:', price) price
FROM pc;

-- Task 7
SELECT ship, battle, country, count(country)
FROM outcomes 
	LEFT JOIN ships ON outcomes.ship = ships.name
    LEFT JOIN classes ON ships.class = classes.class
WHERE country IS NOT NULL 
GROUP BY battle, country
HAVING count(country) >= 2;

-- Task 8
SELECT maker, count(maker), avg(screen)
FROM product RIGHT JOIN laptop ON product.type = 'Laptop'
WHERE laptop.model = product.model
GROUP BY maker;


-- Task 10 !!!!!!!!!! Exists
SELECT class as c
FROM ships 
GROUP BY class
HAVING count(c) = 1
UNION ALL
SELECT ship as c
FROM outcomes 
GROUP BY ship
HAVING count(c) = 1;


-- Task 9 !!!!!!!!!!! Case
SELECT name, numGuns, bore, displacement, type, country, launched, classes.class
FROM ships LEFT JOIN classes ON ships.class = classes.class
WHERE CASE
	WHEN classes.numGuns = 9 
		THEN CASE
			WHEN bore = 16 THEN CASE
							WHEN displacement = 46000 THEN 1 = 1
							WHEN type = 'bb' THEN 1 = 1
							WHEN country = 'Japan' THEN 1 = 1
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN displacement = 46000 THEN CASE
							WHEN type = 'bb' THEN 1 = 1
							WHEN country = 'Japan' THEN 1 = 1
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN type = 'bb' THEN CASE
							WHEN country = 'Japan' THEN 1 = 1
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN country = 'Japan' THEN CASE
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN launched = 1916 THEN CASE
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
		END
    WHEN bore = 16 THEN CASE
			WHEN displacement = 46000 THEN CASE
							WHEN type = 'bb' THEN 1 = 1
							WHEN country = 'Japan' THEN 1 = 1
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN type = 'bb' THEN CASE
							WHEN country = 'Japan' THEN 1 = 1
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN country = 'Japan' THEN CASE
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN launched = 1916 THEN CASE
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
		END
    WHEN displacement = 46000 THEN CASE
			WHEN type = 'bb' THEN CASE
							WHEN country = 'Japan' THEN 1 = 1
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN country = 'Japan' THEN CASE
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN launched = 1916 THEN CASE
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
		END
    WHEN type = 'bb' THEN CASE
			WHEN country = 'Japan' THEN CASE
							WHEN launched = 1916 THEN 1 = 1
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
			WHEN launched = 1916 THEN CASE
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
		END
    WHEN country = 'Japan' THEN CASE
			WHEN launched = 1916 THEN CASE
							WHEN classes.class = 'Revenge' THEN 1 = 1
						END
		END
	ELSE 1 = 0
    END
;

