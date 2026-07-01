CREATE TABLE clienti (
                         id_client SERIAL PRIMARY KEY,
                         nume VARCHAR(50) NOT NULL,
                         prenume VARCHAR(50) NOT NULL,
                         email VARCHAR(100) UNIQUE,
                         telefon_mobil VARCHAR(20),
                         adresa TEXT,
                         oras VARCHAR(50),
                         data_inregistrarii DATE DEFAULT CURRENT_DATE
);

CREATE TABLE filme (
                       id_film SERIAL PRIMARY KEY,
                       titlu VARCHAR(100) NOT NULL,
                       descriere TEXT,
                       categorie VARCHAR(30),
                       data_lansarii DATE,
                       rating_mediu DECIMAL(4,2) DEFAULT 0
);

CREATE TABLE actori (
                        id_actor SERIAL PRIMARY KEY,
                        nume_familie VARCHAR(50),
                        prenume VARCHAR(50),
                        nume_scena VARCHAR(100),
                        data_nasterii DATE
);

CREATE TABLE versiuni_filme (
                                id_versiune SERIAL PRIMARY KEY,
                                id_film INTEGER REFERENCES filme(id_film),
                                format VARCHAR(20),
                                limba VARCHAR(30),
                                rezolutie VARCHAR(20)
);

CREATE TABLE distributie_filme (
                                   id_film INTEGER REFERENCES filme(id_film),
                                   id_actor INTEGER REFERENCES actori(id_actor),
                                   rol VARCHAR(100),
                                   observatii_prestatie TEXT,
                                   PRIMARY KEY (id_film, id_actor)
);

CREATE TABLE vizualizari (
                             id_vizualizare SERIAL PRIMARY KEY,
                             id_client INTEGER REFERENCES clienti(id_client),
                             id_versiune INTEGER REFERENCES versiuni_filme(id_versiune),
                             data_vizualizarii TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             durata_vizionata_minute INTEGER,
                             stare_finalizata BOOLEAN DEFAULT TRUE
);

CREATE TABLE recenzii (
                          id_recenzie SERIAL PRIMARY KEY,
                          id_client INTEGER REFERENCES clienti(id_client),
                          id_film INTEGER REFERENCES filme(id_film),
                          vot INTEGER CHECK (vot BETWEEN 1 AND 10),
                          comentariu TEXT,
                          optiune_predefinita VARCHAR(50),
                          sentiment_detectat VARCHAR(20)
);

CREATE TABLE recenzii_actori (
                                 id_recenzie_actor SERIAL PRIMARY KEY,
                                 id_client INTEGER REFERENCES clienti(id_client) ON DELETE CASCADE,
                                 id_actor INTEGER REFERENCES actori(id_actor) ON DELETE CASCADE,
                                 comentariu TEXT,
                                 optiune_predefinita VARCHAR(255),
                                 sentiment_detectat VARCHAR(20)
);

ALTER TABLE clienti ADD COLUMN parola VARCHAR(255);