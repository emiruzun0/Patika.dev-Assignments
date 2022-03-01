-- 1 - test veritabanınızda employee isimli sütun bilgileri 
--id(INTEGER), name VARCHAR(50), birthday DATE, email VARCHAR(100) 
-- olan bir tablo oluşturalım.

CREATE TABLE employee (
	id INTEGER,
	name VARCHAR(50),
	birthday DATE,
	email VARCHAR(100)
);

-- 2 - Oluşturduğumuz employee tablosuna 'Mockaroo' servisini kullanarak 50 adet veri ekleyelim.
insert into employee (id, name, birthday, email) values (1, 'Dulci Willgoose', '1931-04-28', 'dwillgoose0@indiegogo.com');
insert into employee (id, name, birthday, email) values (2, 'Vance Luckes', '1970-10-01', 'vluckes1@umich.edu');
insert into employee (id, name, birthday, email) values (3, 'Yoko Doore', '1930-01-21', 'ydoore2@altervista.org');
insert into employee (id, name, birthday, email) values (4, 'Agnese Cornes', '1900-08-28', 'acornes3@jugem.jp');
insert into employee (id, name, birthday, email) values (5, 'Norry Hathway', '2013-08-23', 'nhathway4@va.gov');
insert into employee (id, name, birthday, email) values (6, 'Viv Lancaster', '1955-07-14', 'vlancaster5@go.com');
insert into employee (id, name, birthday, email) values (7, 'Michaelina O''Hoolahan', '1917-02-24', 'mohoolahan6@pinterest.com');
insert into employee (id, name, birthday, email) values (8, 'Hermine Doak', '1952-06-09', 'hdoak7@tinypic.com');
insert into employee (id, name, birthday, email) values (9, 'Britney Edlin', '1930-08-25', 'bedlin8@earthlink.net');
insert into employee (id, name, birthday, email) values (10, 'Shandee Halliburton', '1941-02-18', 'shalliburton9@sohu.com');
insert into employee (id, name, birthday, email) values (11, 'Gay Wondraschek', '1927-08-26', 'gwondrascheka@cam.ac.uk');
insert into employee (id, name, birthday, email) values (12, 'Candis O''Hartnett', '2016-02-26', 'cohartnettb@admin.ch');
insert into employee (id, name, birthday, email) values (13, 'Skelly Sommerland', '1982-02-02', 'ssommerlandc@weebly.com');
insert into employee (id, name, birthday, email) values (14, 'Minnie Decreuze', '1981-08-27', 'mdecreuzed@eventbrite.com');
insert into employee (id, name, birthday, email) values (15, 'Annmaria Mole', '1927-08-21', 'amolee@rambler.ru');
insert into employee (id, name, birthday, email) values (16, 'Zachery Mabbett', '1945-01-29', 'zmabbettf@dropbox.com');
insert into employee (id, name, birthday, email) values (17, 'Georgianne Portail', '1951-07-30', 'gportailg@state.gov');
insert into employee (id, name, birthday, email) values (18, 'Colan Emmanueli', '1939-11-28', 'cemmanuelih@reddit.com');
insert into employee (id, name, birthday, email) values (19, 'Kellyann Cassidy', '1923-07-28', 'kcassidyi@prlog.org');
insert into employee (id, name, birthday, email) values (20, 'Retha Hazlehurst', '1939-05-08', 'rhazlehurstj@4shared.com');
insert into employee (id, name, birthday, email) values (21, 'Brod Diano', '2002-02-22', 'bdianok@cargocollective.com');
insert into employee (id, name, birthday, email) values (22, 'Ailsun Dockray', '1930-08-04', 'adockrayl@harvard.edu');
insert into employee (id, name, birthday, email) values (23, 'Belita Abramin', '1959-11-02', 'babraminm@mit.edu');
insert into employee (id, name, birthday, email) values (24, 'Garner Gartenfeld', '2021-05-22', 'ggartenfeldn@examiner.com');
insert into employee (id, name, birthday, email) values (25, 'Jolynn Flight', '1914-08-01', 'jflighto@bloglovin.com');
insert into employee (id, name, birthday, email) values (26, 'Kelly Bisco', '2016-01-02', 'kbiscop@apache.org');
insert into employee (id, name, birthday, email) values (27, 'Tripp Olsson', '1924-08-03', 'tolssonq@who.int');
insert into employee (id, name, birthday, email) values (28, 'Dinah MacIntyre', '1951-08-22', 'dmacintyrer@arizona.edu');
insert into employee (id, name, birthday, email) values (29, 'Dimitri Kinner', '1900-12-23', 'dkinners@psu.edu');
insert into employee (id, name, birthday, email) values (30, 'Jerrilyn Date', '1972-06-03', 'jdatet@adobe.com');
insert into employee (id, name, birthday, email) values (31, 'Laryssa Billington', '1962-10-29', 'lbillingtonu@google.co.jp');
insert into employee (id, name, birthday, email) values (32, 'Lyndy Radbone', '1902-02-18', 'lradbonev@google.de');
insert into employee (id, name, birthday, email) values (33, 'Corbet Rossi', '1938-07-09', 'crossiw@google.ru');
insert into employee (id, name, birthday, email) values (34, 'Bernette Gribbon', '1997-06-14', 'bgribbonx@hibu.com');
insert into employee (id, name, birthday, email) values (35, 'Kienan Wein', '1989-07-22', 'kweiny@columbia.edu');
insert into employee (id, name, birthday, email) values (36, 'Antons Ridoutt', '1981-03-26', 'aridouttz@drupal.org');
insert into employee (id, name, birthday, email) values (37, 'Creight McClelland', '2007-06-09', 'cmcclelland10@alibaba.com');
insert into employee (id, name, birthday, email) values (38, 'Margarete Ballaam', '1953-01-04', 'mballaam11@posterous.com');
insert into employee (id, name, birthday, email) values (39, 'Iseabal Hasnney', '1924-06-21', 'ihasnney12@omniture.com');
insert into employee (id, name, birthday, email) values (40, 'Perry Garza', '1934-08-24', 'pgarza13@ftc.gov');
insert into employee (id, name, birthday, email) values (41, 'Nadean Charlton', '1914-08-16', 'ncharlton14@time.com');
insert into employee (id, name, birthday, email) values (42, 'Sonni Gooderridge', '1912-01-29', 'sgooderridge15@desdev.cn');
insert into employee (id, name, birthday, email) values (43, 'Kerianne Illwell', '1997-03-25', 'killwell16@ebay.com');
insert into employee (id, name, birthday, email) values (44, 'Georges Pidler', '1982-09-04', 'gpidler17@jalbum.net');
insert into employee (id, name, birthday, email) values (45, 'Addy Burley', '1945-04-06', 'aburley18@freewebs.com');
insert into employee (id, name, birthday, email) values (46, 'Stephani Dinesen', '1928-09-23', 'sdinesen19@google.com');
insert into employee (id, name, birthday, email) values (47, 'Christabel Kline', '1906-12-08', 'ckline1a@freewebs.com');
insert into employee (id, name, birthday, email) values (48, 'Fanchon Bartlomiej', '1966-02-22', 'fbartlomiej1b@buzzfeed.com');
insert into employee (id, name, birthday, email) values (49, 'Rob Armin', '2003-09-22', 'rarmin1c@lulu.com');
insert into employee (id, name, birthday, email) values (50, 'Ange Verbrugge', '1913-12-09', 'averbrugge1d@amazon.com');




-- 3 - Sütunların her birine göre diğer sütunları güncelleyecek 5 adet UPDATE işlemi yapalım.

UPDATE employee 
SET name = 'Emirhan Uzun',
	birthday = '1999-06-07'
WHERE id BETWEEN 7 AND 11;


--4.Sütunların her birine göre ilgili satırı silecek 5 adet DELETE işlemi yapalım.

DELETE FROM employee
WHERE id BETWEEN 34 AND 38
RETURNING *;
