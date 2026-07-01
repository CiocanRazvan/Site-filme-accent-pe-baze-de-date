INSERT INTO clienti (nume, prenume, email, telefon_mobil, oras, data_inregistrarii) VALUES
                                                                                        ('Popescu', 'Ion', 'ion.p@email.com', '0722111222', 'Bucuresti', '2025-01-10'),
                                                                                        ('Ionescu', 'Maria', 'maria.i@email.com', '0733444555', 'Cluj', '2025-01-12'),
                                                                                        ('Georgescu', 'Dan', 'dan.g@email.com', '0744777888', 'Iasi', '2025-01-15'),
                                                                                        ('Constantin', 'Elena', 'elena.c@email.com', '0755888999', 'Timisoara', '2025-01-20'),
                                                                                        ('Dumitru', 'Andrei', 'andrei.d@email.com', '0766111333', 'Brasov', '2025-02-01'),
                                                                                        ('Stan', 'Carmen', 'carmen.s@email.com', '0721000999', 'Constanta', '2025-02-05'),
                                                                                        ('Stoica', 'Mihai', 'mihai.s@email.com', '0732111444', 'Craiova', '2025-02-10'),
                                                                                        ('Radu', 'Vasile', 'vasile.r@email.com', '0743222555', 'Galati', '2025-02-15'),
                                                                                        ('Dinu', 'Anca', 'anca.d@email.com', '0754333666', 'Oradea', '2025-02-20'),
                                                                                        ('Lupu', 'Cristian', 'cristi.l@email.com', '0765444777', 'Sibiu', '2025-02-25'),
                                                                                        ('Marin', 'Simona', 'simona.m@email.com', '0726555888', 'Arad', '2025-03-01'),
                                                                                        ('Matei', 'Victor', 'victor.m@email.com', '0737666999', 'Bacau', '2025-03-05'),
                                                                                        ('Nistor', 'Laura', 'laura.n@email.com', '0748777000', 'Ploiesti', '2025-03-10'),
                                                                                        ('Oprea', 'Bogdan', 'bogdan.o@email.com', '0759888111', 'Braila', '2025-03-15'),
                                                                                        ('Pavel', 'Diana', 'diana.p@email.com', '0760999222', 'Pitesti', '2025-03-20');

INSERT INTO filme (titlu, descriere, categorie, data_lansarii, rating_mediu) VALUES
                                                                                 ('The Matrix', 'O lume virtuala', 'SF', '1999-03-31', 0),
                                                                                 ('Inception', 'Vise in vise', 'SF', '2010-07-16', 0),
                                                                                 ('The Godfather', 'Mafia italiana', 'Drama', '1972-03-24', 0),
                                                                                 ('Interstellar', 'Calatorie in timp', 'SF', '2014-11-07', 0),
                                                                                 ('Gladiator', 'Lupta in Roma', 'Actiune', '2000-05-05', 0),
                                                                                 ('Joker', 'Povestea unui raufacator', 'Drama', '2019-10-04', 0),
                                                                                 ('Titanic', 'Scufundarea vaporului', 'Romance', '1997-12-19', 0),
                                                                                 ('Avatar', 'Planeta Pandora', 'SF', '2009-12-18', 0),
                                                                                 ('Se7en', 'Detectivi si crime', 'Thriller', '1995-09-22', 0),
                                                                                 ('Pulp Fiction', 'Povesti intrebuintate', 'Crime', '1994-10-14', 0),
                                                                                 ('The Dark Knight', 'Batman vs Joker', 'Actiune', '2008-07-18', 0),
                                                                                 ('Parasite', 'O familie saraca', 'Thriller', '2019-05-30', 0),
                                                                                 ('Shrek', 'Un capcaun verde', 'Animatie', '2001-05-18', 0),
                                                                                 ('The Shining', 'Hotelul bantuit', 'Horror', '1980-05-23', 0),
                                                                                 ('The Lion King', 'Povestea lui Simba', 'Animatie', '1994-06-15', 0);

INSERT INTO actori (nume_familie, prenume, nume_scena, data_nasterii) VALUES
-- 1-5 Matrix
('Reeves', 'Keanu', 'Neo', '1964-09-02'), ('Moss', 'Carrie-Anne', 'Trinity', '1967-08-21'), ('Fishburne', 'Laurence', 'Morpheus', '1961-07-30'), ('Weaving', 'Hugo', 'Agent Smith', '1960-04-04'), ('Pantoliano', 'Joe', 'Cypher', '1951-09-12'),
-- 6-10 Inception
('DiCaprio', 'Leonardo', 'Cobb', '1974-11-11'), ('Hardy', 'Tom', 'Eames', '1977-09-15'), ('Murphy', 'Cillian', 'Fischer', '1976-05-25'), ('Page', 'Elliot', 'Ariadne', '1987-02-21'), ('Gordon-Levitt', 'Joseph', 'Arthur', '1981-01-20'),
-- 11-15 Godfather
('Pacino', 'Al', 'Michael Corleone', '1940-04-25'), ('Brando', 'Marlon', 'Vito Corleone', '1924-04-03'), ('Caan', 'James', 'Sonny Corleone', '1940-03-26'), ('Duvall', 'Robert', 'Tom Hagen', '1931-01-05'), ('Cazale', 'John', 'Fredo', '1935-08-12'),
-- 16-20 Interstellar
('McConaughey', 'Matthew', 'Cooper', '1969-11-04'), ('Hathaway', 'Anne', 'Brand', '1982-11-12'), ('Chastain', 'Jessica', 'Murph', '1977-03-24'), ('Caine', 'Michael', 'Professor Brand', '1933-03-14'), ('Affleck', 'Casey', 'Tom', '1975-08-12'),
-- 21-25 Gladiator
('Crowe', 'Russell', 'Maximus', '1964-04-07'), ('Phoenix', 'Joaquin', 'Commodus', '1974-10-28'), ('Nielsen', 'Connie', 'Lucilla', '1965-07-03'), ('Harris', 'Richard', 'Marcus Aurelius', '1930-10-01'), ('Hounsou', 'Djimon', 'Juba', '1964-04-24'),
-- 26-30 Joker
('Phoenix', 'Joaquin', 'Arthur Fleck', '1974-10-28'), ('De Niro', 'Robert', 'Murray Franklin', '1943-08-17'), ('Beetz', 'Zazie', 'Sophie Dumond', '1991-06-01'), ('Conroy', 'Frances', 'Penny Fleck', '1953-11-13'), ('Brett', 'Cullen', 'Thomas Wayne', '1956-08-26'),
-- 31-35 Titanic
('Winslet', 'Kate', 'Rose', '1975-10-05'), ('DiCaprio', 'Leonardo', 'Jack Dawson', '1974-11-11'), ('Zane', 'Billy', 'Cal Hockley', '1966-02-24'), ('Bates', 'Kathy', 'Molly Brown', '1948-06-28'), ('Fisher', 'Frances', 'Ruth', '1952-05-11'),
-- 36-40 Avatar
('Worthington', 'Sam', 'Jake Sully', '1976-08-02'), ('Saldana', 'Zoe', 'Neytiri', '1978-06-19'), ('Weaver', 'Sigourney', 'Dr. Grace', '1949-10-08'), ('Lang', 'Stephen', 'Quaritch', '1952-07-11'), ('Rodriguez', 'Michelle', 'Trudy', '1978-07-12'),
-- 41-45 Se7en
('Pitt', 'Brad', 'Mills', '1963-12-18'), ('Freeman', 'Morgan', 'Somerset', '1937-06-01'), ('Spacey', 'Kevin', 'John Doe', '1959-07-26'), ('Paltrow', 'Gwyneth', 'Tracy', '1972-09-27'), ('Lee', 'R. Ermey', 'Police Captain', '1944-03-24'),
-- 46-50 Pulp Fiction
('Travolta', 'John', 'Vincent Vega', '1954-02-18'), ('Jackson', 'Samuel L.', 'Jules', '1948-12-21'), ('Thurman', 'Uma', 'Mia Wallace', '1970-04-29'), ('Willis', 'Bruce', 'Butch Coolidge', '1955-03-19'), ('Rhames', 'Ving', 'Marsellus Wallace', '1959-05-12'),
-- 51-55 The Dark Knight
('Bale', 'Christian', 'Batman', '1974-01-30'), ('Ledger', 'Heath', 'The Joker', '1979-04-04'), ('Oldman', 'Gary', 'Jim Gordon', '1958-03-21'), ('Eckhart', 'Aaron', 'Harvey Dent', '1968-03-12'), ('Gyllenhaal', 'Maggie', 'Rachel Dawes', '1977-11-16'),
-- 56-60 Parasite
('Kang-ho', 'Song', 'Kim Ki-taek', '1967-01-17'), ('Sun-kyun', 'Lee', 'Park Dong-ik', '1975-03-02'), ('Yeo-jeong', 'Cho', 'Choi Yeon-gyo', '1981-02-10'), ('Woo-shik', 'Choi', 'Kim Ki-woo', '1990-03-26'), ('So-dam', 'Park', 'Kim Ki-jung', '1991-09-08'),
-- 61-65 Shrek
('Myers', 'Mike', 'Shrek', '1963-05-25'), ('Murphy', 'Eddie', 'Donkey', '1961-04-03'), ('Diaz', 'Cameron', 'Fiona', '1972-08-30'), ('Lithgow', 'John', 'Lord Farquaad', '1945-10-19'), ('Cassel', 'Vincent', 'Monsieur Hood', '1966-11-23'),
-- 66-70 The Shining
('Nicholson', 'Jack', 'Jack Torrance', '1937-04-22'), ('Duvall', 'Shelley', 'Wendy Torrance', '1949-07-07'), ('Lloyd', 'Danny', 'Danny Torrance', '1972-10-13'), ('Crothers', 'Scatman', 'Dick Hallorann', '1910-05-23'), ('Nelson', 'Barry', 'Ullman', '1917-04-16'),
-- 71-75 The Lion King
('Broderick', 'Matthew', 'Simba', '1962-03-21'), ('Irons', 'Jeremy', 'Scar', '1948-09-19'), ('Jones', 'James Earl', 'Mufasa', '1931-01-17'), ('Moira', 'Kelly', 'Nala', '1968-03-06'), ('Lane', 'Nathan', 'Timon', '1956-02-03');

INSERT INTO distributie_filme (id_film, id_actor, rol, observatii_prestatie) VALUES
-- Matrix (Film 1)
(1, 1, 'Neo', 'Interpretare de exceptie, a definit era filmelor SF.'),
(1, 2, 'Trinity', 'Chimia cu protagonistul este punctul forte al filmului.'),
(1, 3, 'Morpheus', 'Prezenta scenica impunatoare si voce memorabila.'),
(1, 4, 'Agent Smith', 'Unul dintre cei mai buni antagonisti, prestatie geniala.'),
(1, 5, 'Cypher', 'Prestatie destul de slaba, personajul a fost plictisitor.'),

-- Inception (Film 2)
(2, 6, 'Dom Cobb', 'O prestatie profunda si plina de emotie.'),
(2, 7, 'Eames', 'Aduce un plus de carisma si umor fin distributiei.'),
(2, 8, 'Robert Fischer', 'Interpretare vulnerabila, foarte convingator.'),
(2, 9, 'Ariadne', 'Evolutie buna a personajului pe parcursul povestii.'),
(2, 10, 'Arthur', 'Actorul a parut absent, un rol dezamagitor fata de asteptari.'),

-- The Godfather (Film 3)
(3, 11, 'Michael Corleone', 'Cea mai buna transformare a unui personaj din istorie.'),
(3, 12, 'Vito Corleone', 'O capodopera actoriceasca, prezenta iconica.'),
(3, 13, 'Sonny Corleone', 'Energie bruta si interpretare exploziva.'),
(3, 14, 'Tom Hagen', 'Subtil si calculat, o prestatie de sustinere perfecta.'),
(3, 15, 'Fredo', 'Scenariu slab pentru acest personaj, actorul nu a convins deloc.'),

-- Interstellar (Film 4)
(4, 16, 'Cooper', 'Emotionant pana la lacrimi in scenele cu familia.'),
(4, 17, 'Amelia Brand', 'O prestatie echilibrata intre stiinta si sentiment.'),
(4, 18, 'Murph', 'Interpretare matura pentru un actor atat de tanar.'),
(4, 19, 'Prof Brand', 'Vocea autoritara adauga greutate scenariului.'),
(4, 20, 'Tom', 'O pierdere de timp, interpretarea a fost rigida si lipsita de emotie.'),

-- Gladiator (Film 5)
(5, 21, 'Maximus', 'O forta a naturii, prestatie castigatoare de Oscar.'),
(5, 22, 'Commodus', 'Interpretare tulburatoare a unui tiran nesigur pe el.'),
(5, 23, 'Lucilla', 'Eleganta si teama redate perfect prin privire.'),
(5, 24, 'Marcus Aurelius', 'Scurta dar memorabila aparitie, plina de intelepciune.'),
(5, 25, 'Juba', 'Interpretare foarte proasta, a stricat dinamica scenelor de lupta.'),

-- Joker (Film 6)
(6, 26, 'Arthur Fleck', 'Un tur de forta psihologic, absolut captivant.'),
(6, 27, 'Murray Franklin', 'Prestatie carismatica ce ascunde un cinism rece.'),
(6, 28, 'Sophie Dumond', 'Interpreteaza bine rolul de ancora in realitate.'),
(6, 29, 'Penny Fleck', 'Tulburatoare in fragilitatea ei mentala.'),
(6, 30, 'Thomas Wayne', 'Un rol nasol, nu s-a potrivit deloc cu atmosfera filmului.'),

-- Titanic (Film 7)
(7, 31, 'Rose', 'O interpretare plina de pasiune si dorinta de libertate.'),
(7, 32, 'Jack', 'Carisma debordanta, a cucerit publicul instantaneu.'),
(7, 33, 'Cal', 'Antagonistul perfect, prestatie plina de aroganta.'),
(7, 34, 'Molly', 'Aduce o gura de aer proaspat si umor in momente grele.'),
(7, 35, 'Ruth', 'Dialoguri groaznice, actorul a parut ca citeste de pe foaie.'),

-- Avatar (Film 8)
(8, 36, 'Jake', 'Evolutie buna de la soldat la lider spiritual.'),
(8, 37, 'Neytiri', 'Prestatie remarcabila prin motion-capture, foarte expresiva.'),
(8, 38, 'Dr Grace', 'Autoritara si pasionata, un pilon de incredere.'),
(8, 39, 'Quaritch', 'Un personaj negativ dur, interpretat fara cusur.'),
(8, 40, 'Trudy', 'Efectele vizuale nu pot salva o prestatie atat de plictisitoare.'),

-- Se7en (Film 9)
(9, 41, 'Mills', 'Impulsivitatea si tineretea redate cu mare energie.'),
(9, 42, 'Somerset', 'Calm si metodic, echilibrul perfect pentru film.'),
(9, 43, 'John Doe', 'Infricosator prin calmul si logica sa bolnava.'),
(9, 44, 'Tracy', 'Aduce singura nota de lumina intr-un film intunecat.'),
(9, 45, 'The Captain', 'Finalul a fost previzibil din cauza jocului actoricesc slab.'),

-- Pulp Fiction (Film 10)
(10, 46, 'Vincent', 'Stil si umor negru intr-o prestatie memorabila.'),
(10, 47, 'Jules', 'Monologurile sale sunt punctul culminant al filmului.'),
(10, 48, 'Mia', 'Misterioasa si magnetica pe ecran.'),
(10, 49, 'Butch', 'Interpretare bruta, foarte credibila in scenele tensionate.'),
(10, 50, 'Marsellus', 'Nu recomand acest actor in acest rol, a fost total pe langa.'),

-- The Dark Knight (Film 11)
(11, 51, 'Batman', 'Cea mai sobra si intensa interpretare a eroului.'),
(11, 52, 'Joker', 'Cea mai buna interpretare a unui raufacator din istorie.'),
(11, 53, 'Gordon', 'Un actor care dispare complet in rolul politistului onest.'),
(11, 54, 'Harvey', 'Transformarea in Two-Face este redata tragic si dureros.'),
(11, 55, 'Rachel', 'Personajul a fost urat conturat, o dezamagire totala.'),

-- Parasite (Film 12)
(12, 56, 'Ki-taek', 'Naturalete uluitoare, oscileaza intre comic si tragic.'),
(12, 57, 'Dong-ik', 'Reda perfect aroganta subtila a clasei superioare.'),
(12, 58, 'Yeon-gyo', 'Naivitatea personajului este interpretata genial.'),
(12, 59, 'Ki-woo', 'Sustine bine greutatea planului initial al familiei.'),
(12, 60, 'Ki-jung', 'Desi filmul e bun, acest actor a avut o prestatie mediocra.'),

-- Shrek (Film 13)
(13, 61, 'Shrek', 'Vocea perfecta pentru a imbina duritatea cu bunatatea.'),
(13, 62, 'Donkey', 'Energie nesfarsita, cel mai amuzant personaj secundar.'),
(13, 63, 'Fiona', 'Aduce o perspectiva moderna si curajoasa printesei clasice.'),
(13, 64, 'Farquaad', 'Interpretare vocala plina de umor si grandomanie.'),
(13, 65, 'Monsieur Hood', 'Vocea a fost enervanta, a facut personajul greu de urmarit.'),

-- The Shining (Film 14)
(14, 66, 'Jack', 'Interpretare legendara, te trece prin toti fiorii.'),
(14, 67, 'Wendy', 'Reda teroarea si disperarea intr-un mod foarte visceral.'),
(14, 68, 'Danny', 'Surprinzator de bun pentru un copil, foarte credibil.'),
(14, 69, 'Dick', 'Aduce o nota de caldura si mister povestii.'),
(14, 70, 'Ullman', 'Prestatia a fost sub asteptari, lipsita de carisma necesara.'),

-- The Lion King (Film 15)
(15, 71, 'Simba', 'Vocea potrivita pentru cresterea si maturizarea eroului.'),
(15, 72, 'Scar', 'Voce sarcastica si amenintatoare, pur si simplu perfecta.'),
(15, 73, 'Mufasa', 'O voce calda si autoritara care impune respect.'),
(15, 74, 'Nala', 'Prestatie vocala plina de hotarare si forta.'),
(15, 75, 'Timon', 'Interpretare slaba, nu a reusit sa transmita nicio emotie.');

INSERT INTO versiuni_filme (id_film, format, limba, rezolutie) VALUES
-- Matrix (ID 1)
(1, 'Digital', 'Engleza', '4K'), (1, 'BluRay', 'Romana', '1080p'), (1, 'DVD', 'Engleza', '720p'),
-- Inception (ID 2)
(2, 'Digital', 'Engleza', '4K'), (2, 'Streaming', 'Franceza', '1080p'), (2, 'BluRay', 'Engleza', '1080p'),
-- The Godfather (ID 3)
(3, 'DVD', 'Italiana', '720p'), (3, 'Digital', 'Engleza', '1080p'), (3, 'BluRay', 'Romana', '1080p'),
-- Interstellar (ID 4)
(4, 'Digital', 'Engleza', '4K'), (4, 'BluRay', 'Engleza', '1080p'), (4, 'Streaming', 'Germana', '1080p'),
-- Gladiator (ID 5)
(5, 'Digital', 'Romana', '1080p'), (5, 'DVD', 'Engleza', '720p'), (5, 'BluRay', 'Engleza', '4K'),
-- Joker (ID 6)
(6, 'Streaming', 'Engleza', '4K'), (6, 'Digital', 'Engleza', '1080p'), (6, 'DVD', 'Romana', '720p'),
-- Titanic (ID 7)
(7, 'BluRay', 'Engleza', '1080p'), (7, 'Digital', 'Romana', '1080p'), (7, 'DVD', 'Engleza', '720p'),
-- Avatar (ID 8)
(8, 'Digital', 'Engleza', '4K'), (8, 'Streaming', 'Na''vi', '4K'), (8, 'BluRay', 'Engleza', '1080p'),
-- Se7en (ID 9)
(9, 'DVD', 'Engleza', '1080p'), (9, 'Digital', 'Engleza', '1080p'), (9, 'BluRay', 'Franceza', '1080p'),
-- Pulp Fiction (ID 10)
(10, 'Streaming', 'Engleza', '1080p'), (10, 'Digital', 'Engleza', '4K'), (10, 'DVD', 'Romana', '720p'),
-- The Dark Knight (ID 11)
(11, 'BluRay', 'Engleza', '4K'), (11, 'Digital', 'Engleza', '1080p'), (11, 'Streaming', 'Romana', '1080p'),
-- Parasite (ID 12)
(12, 'Digital', 'Coreeana', '1080p'), (12, 'Streaming', 'Engleza', '1080p'), (12, 'BluRay', 'Romana', '1080p'),
-- Shrek (ID 13)
(13, 'Streaming', 'Romana', '720p'), (13, 'DVD', 'Engleza', '720p'), (13, 'Digital', 'Engleza', '1080p'),
-- The Shining (ID 14)
(14, 'BluRay', 'Engleza', '1080p'), (14, 'Digital', 'Engleza', '1080p'), (14, 'DVD', 'Romana', '720p'),
-- The Lion King (ID 15)
(15, 'Digital', 'Engleza', '1080p'), (15, 'Streaming', 'Romana', '1080p'), (15, 'BluRay', 'Engleza', '4K');

INSERT INTO vizualizari (id_client, id_versiune, data_vizualizarii, durata_vizionata_minute, stare_finalizata) VALUES
                                                                                                                   (1, 1, '2025-05-01 20:00:00', 136, TRUE), (2, 4, '2025-05-02 18:30:00', 148, TRUE),
                                                                                                                   (3, 7, '2025-05-03 21:00:00', 175, TRUE), (4, 10, '2025-05-04 15:45:00', 169, TRUE),
                                                                                                                   (5, 13, '2025-05-05 19:20:00', 155, TRUE), (6, 16, '2025-05-06 22:10:00', 122, TRUE),
                                                                                                                   (7, 19, '2025-05-07 14:00:00', 194, FALSE), (8, 22, '2025-05-08 17:00:00', 162, TRUE),
                                                                                                                   (9, 25, '2025-05-09 20:30:00', 127, TRUE), (10, 28, '2025-05-10 23:00:00', 154, TRUE),
                                                                                                                   (11, 31, '2025-05-11 19:00:00', 152, TRUE), (12, 34, '2025-05-11 21:00:00', 132, TRUE),
                                                                                                                   (13, 37, '2025-05-11 10:00:00', 90, TRUE), (14, 40, '2025-05-11 23:30:00', 146, TRUE),
                                                                                                                   (15, 43, '2025-05-11 12:00:00', 89, TRUE);

INSERT INTO recenzii (id_client, id_film, vot, comentariu, optiune_predefinita) VALUES
                                                                                    (1, 1, 10, 'Un film excelent, inovator pentru vremea lui!', 'as recomanda'),
                                                                                    (2, 2, 9, 'Genial lucrat, poveste complexa si captivanta.', 'interesant'),
                                                                                    (3, 3, 10, 'O capodopera absoluta a genului drama.', 'as mai viziona'),
                                                                                    (4, 4, 10, 'Absolut superb, finalul a fost extrem de emotionant.', 'as recomanda'),
                                                                                    (5, 5, 8, 'Actiune de calitate si interpretare buna.', 'mi-a placut'),
                                                                                    (6, 6, 10, 'Interpretare de Oscar, un film genial.', 'actor principal apreciat'),
                                                                                    (7, 7, 6, 'Povestea e ok, dar filmul este prea plictisitor pe alocuri.', 'plictisitor'),
                                                                                    (8, 8, 9, 'Vizual este incredibil, o experienta placuta.', 'interesant'),
                                                                                    (9, 9, 9, 'Un thriller perfect, te tine in tensiune.', 'as recomanda'),
                                                                                    (10, 10, 10, 'Dialoguri legendare si regie de top.', 'genial'),
                                                                                    (11, 11, 9, 'Cel mai bun Batman din istorie.', 'actor principal apreciat'),
                                                                                    (12, 12, 4, 'Nu mi-a placut deloc finalul, a fost dezamagitor.', 'nu mi-a placut'),
                                                                                    (13, 13, 8, 'Foarte amuzant pentru toata familia.', 'as recomanda'),
                                                                                    (14, 14, 5, 'Slab fata de carte, scenariu slab si actiune inceata.', 'scenariu slab'),
                                                                                    (15, 15, 9, 'O poveste nemuritoare cu muzica superba.', 'emotionant');


INSERT INTO filme (titlu, descriere, categorie, data_lansarii, rating_mediu) VALUES
    ('John Wick',
     'Un fost asasin legendar iese din retragere dupa ce gangsterii fiului unui boss rus ii ucid cainele si ii fura masina. Ceea ce nu stiau: tocmai l-au trezit pe cel mai redutabil om din lume.',
     'Actiune', '2014-10-24', 8.4);

INSERT INTO actori (nume_familie, prenume, nume_scena, data_nasterii) VALUES
                                                                          ('McShane',  'Ian',       'Winston',       '1942-09-29'),
                                                                          ('Nyqvist',  'Michael',   'Viggo Tarasov', '1960-11-08'),
                                                                          ('Allen',    'Alfie',     'Iosef Tarasov', '1986-09-12'),
                                                                          ('Dafoe',    'Willem',    'Marcus',        '1955-07-22'),
                                                                          ('Palicki',  'Adrianne',  'Ms. Perkins',   '1983-05-06');

INSERT INTO distributie_filme (id_film, id_actor, rol, observatii_prestatie) VALUES
                                                                                 (16, 1,  'John Wick',    'Revenire spectaculoasa — Reeves transforma tacerea in forta pura. Cel mai bun rol al carierei sale.'),
                                                                                 (16, 76, 'Winston',      'Autoritate rece si eleganta. Stapanul Continentalului domina fiecare scena fara sa ridice vocea.'),
                                                                                 (16, 77, 'Viggo Tarasov','Antagonist credibil — frica autentica in fata legendei, vulnerabilitate bine dozata.'),
                                                                                 (16, 78, 'Iosef Tarasov','Aroganta si imaturitate redate convingator. Personajul pe care il urasti instantaneu.'),
                                                                                 (16, 79, 'Marcus',       'Prezenta discreta dar memorabila. Loialitate misterioasa redata cu economie de mijloace.');

INSERT INTO versiuni_filme (id_film, format, limba, rezolutie) VALUES
-- John Wick
(16, 'Blu-ray',    'Engleza',          '1080p'),
(16, 'Blu-ray 4K', 'Romana subtitrat', '4K HDR'),
(16, 'Digital',    'Romana dublat',    '720p'),
-- Matrix
(1,  'Blu-ray',    'Engleza',          '1080p'),
(1,  'DVD',        'Romana subtitrat', '480p'),
-- Inception
(2,  'Blu-ray 4K', 'Engleza',          '4K HDR'),
(2,  'Digital',    'Romana subtitrat', '1080p'),
-- The Dark Knight
(11, 'Blu-ray',    'Engleza',          '1080p'),
(11, 'DVD',        'Romana dublat',    '480p'),
-- Interstellar
(4,  'Blu-ray 4K', 'Engleza',          '4K HDR'),
(4,  'Digital',    'Romana subtitrat', '1080p'),
-- Gladiator
(5,  'Blu-ray',    'Engleza',          '1080p'),
(5,  'DVD',        'Romana dublat',    '480p');