-- 1 - film tablosunda film uzunluğu length sütununda gösterilmektedir. 
-- Uzunluğu ortalama film uzunluğundan fazla kaç tane film vardır?

SELECT COUNT(*) FROM film
WHERE length > 
(
	SELECT AVG(length) FROM film
)


-- 2 - film tablosunda en yüksek rental_rate değerine sahip kaç tane film vardır?

SELECT COUNT(*) FROM film
WHERE rental_rate = 
(
	SELECT MAX(rental_rate) FROM film
)


-- 3 - film tablosunda en düşük rental_rate ve en düşük replacement_cost değerlerine sahip filmleri sıralayınız.

(SELECT title, rental_rate, replacement_cost FROM film
WHERE rental_rate = 
(
	SELECT MIN(rental_rate) FROM film
))
INTERSECT 
(SELECT title, rental_rate, replacement_cost FROM film
WHERE replacement_cost = 
(
	SELECT MIN(replacement_cost) FROM film
))



-- 4 - payment tablosunda en fazla sayıda alışveriş yapan müşterileri(customer) sıralayınız.

SELECT SUM(amount), first_name, last_name FROM customer
JOIN payment ON customer.customer_id = payment.customer_id
GROUP BY customer.customer_id, first_name, last_name
ORDER BY SUM(amount) DESC




