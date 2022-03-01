-- 1 - actor ve customer tablolarında bulunan first_name sütunları için tüm verileri sıralayalım.

(
	SELECT first_name from actor
)
UNION
(
	SELECT first_name from customer
);


-- 2 - actor ve customer tablolarında bulunan first_name sütunları için kesişen verileri sıralayalım.

(
	SELECT first_name from actor
)
INTERSECT
(
	SELECT first_name from customer
)


-- 3 - actor ve customer tablolarında bulunan first_name sütunları için 
-- ilk tabloda bulunan ancak ikinci tabloda bulunmayan verileri sıralayalım.

(
	SELECT first_name from actor
)
EXCEPT
(
	SELECT first_name from customer
)


-- 4 - İlk 3 sorguyu tekrar eden veriler için de yapalım.

-- For union
(
	SELECT first_name from actor
)
UNION ALL
(
	SELECT first_name from customer
);


-- For intersect 
(
	SELECT first_name from actor
)
INTERSECT ALL
(
	SELECT first_name from customer
);

-- For except
(
	SELECT first_name from actor
)
EXCEPT ALL
(
	SELECT first_name from customer
);
 









