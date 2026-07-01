CREATE OR REPLACE FUNCTION analizeaza_sentiment_complex(text_comentariu TEXT)
    RETURNS VARCHAR AS $$
DECLARE
    scor INTEGER := 0;

    expresii_negative TEXT[] := ARRAY[
        'nu mi-a placut', 'nu mi-a plăcut',
        'nu mi-a fost pe plac',
        'nu recomand', 'nu recomanda',
        'nu merita', 'nu merită',
        'nu e bun', 'nu e buna', 'nu este bun', 'nu este buna',
        'nu a fost bun', 'nu a fost buna',
        'nu e interesant', 'nu e interesanta',
        'nu e captivant', 'nu e superb',
        'nu e excelent', 'nu e genial',
        'nu e emotionant', 'nu e fascinant',
        'nu m-a impresionat', 'nu m-a captivat',
        'nu m-a convins', 'nu m-a miscat',
        'nu as mai vedea', 'nu as mai viziona',
        'nu as recomanda', 'nu aș recomanda',
        'deloc bun', 'deloc interesant', 'deloc captivant',
        'deloc emotionant', 'deloc placut', 'deloc plăcut',
        'nicidecum bun', 'departe de bun',
        'nu e ce m-am asteptat', 'sub asteptari', 'sub așteptări',
        'pierdere de timp', 'pierdere de bani',
        'nu merita vazut', 'nu merita urmarit',
        'nu m-a placut', 'nu m-a plăcut'
        ];

    expresii_pozitive TEXT[] := ARRAY[
        'mi-a placut', 'mi-a plăcut', 'mi-a fost pe plac',
        'mi-a depasit asteptarile', 'mi-a depășit așteptările',
        'recomand cu caldura', 'recomand cu căldură',
        'recomand tuturor', 'recomand oricui',
        'merita vazut', 'merită văzut',
        'merita urmarit', 'merită urmărit',
        'trebuie vazut', 'trebuie urmărit',
        'as recomanda', 'aș recomanda',
        'as mai vedea', 'as mai viziona',
        'aș mai vedea', 'aș mai viziona',
        'foarte bun', 'foarte buna', 'foarte bună',
        'extrem de bun', 'extrem de captivant',
        'unul dintre cele mai bune',
        'cel mai bun', 'cea mai buna', 'cea mai bună',
        'm-a captivat', 'm-a fascinat', 'm-a impresionat',
        'm-a emotionat', 'm-a mișcat', 'm-a miscat',
        'm-a convins', 'am adorat', 'am iubit'
        ];

    cuvinte_pozitive TEXT[] := ARRAY[
        'bun', 'buna', 'buni', 'bune', 'foarte bun', 'foarte buna',
        'excelent', 'excelenta', 'exceptional', 'exceptionala',
        'superb', 'superba', 'magnific', 'magnifica', 'splendid', 'splendida',
        'extraordinar', 'extraordinara', 'fenomenal', 'fenomenala',
        'perfect', 'perfecta', 'impecabil', 'impecabila',
        'minunat', 'minunata', 'uimitor', 'uimitoare',
        'recomand', 'recomanda', 'merit', 'merita', 'trebuie vazut',
        'trebuie urmarit', 'must watch', 'must see',
        'placut', 'placuta', 'mi-a placut', 'mi-a plăcut',
        'am adorat', 'ador', 'iubesc', 'am iubit',
        'mi-a depasit asteptarile', 'depasit asteptarile',
        'captivant', 'captivanta', 'fascinant', 'fascinanta',
        'hipnotizant', 'hipnotizanta', 'absorbit', 'nu te lasa',
        'nu poti da pauza', 'nu am putut opri',
        'emotionant', 'emotionanta', 'miscat', 'miscata',
        'lacrimi', 'plans de emotie', 'suflet', 'profund', 'profunda',
        'inspirational', 'inspirationala', 'motivant', 'motivanta',
        'interesant', 'interesanta', 'genial', 'geniala',
        'capodopera', 'masterpiece', 'scenariu bun', 'poveste buna',
        'bine scris', 'bine scrisa', 'original', 'originala',
        'creativ', 'creativa', 'complex', 'complexa',
        'bine construit', 'bine construita',
        'actorie buna', 'actorie excelenta', 'actorie superba',
        'prestatie excelenta', 'prestatie superba', 'rol bun',
        'convingator', 'convingatoare', 'natural', 'naturala',
        'talentat', 'talentata', 'impresionant', 'impresionanta',
        'efecte speciale bune', 'cinematografie superba',
        'imagine superba', 'coloana sonora superba',
        'regie excelenta', 'productie de calitate',
        'distractiv', 'distractiva', 'haios', 'haioas',
        'amuzant', 'amuzanta', 'funny', 'comic', 'comica',
        'wow', 'bravo', 'chapeau', '10 din 10', '10/10',
        'nota 10', '5 stele', 'as mai vedea', 'as mai viziona',
        'am revazut', 'am revăzut', 'merit revazut'
        ];

    cuvinte_negative TEXT[] := ARRAY[
        'slab', 'slaba', 'slabi', 'slabe', 'foarte slab', 'foarte slaba',
        'prost', 'proasta', 'prosti', 'proaste',
        'nasol', 'nasola', 'groaznic', 'groaznica',
        'oribil', 'oribila', 'teribil', 'teribila',
        'penibil', 'penibila', 'jalnic', 'jalnica',
        'execrabil', 'execrabila', 'dezgustator', 'dezgustatoare',
        'dezamagitor', 'dezamagitoare', 'dezamagit', 'dezamăgit',
        'dezamagire', 'dezamăgire', 'asteptari neindeplinite',
        'sub asteptari', 'sub așteptări', 'nu s-a ridicat',
        'plictisitor', 'plictisitoare', 'plictisit', 'plictiseala',
        'boring', 'plictiseste', 'adormit', 'adoarme',
        'lent', 'lenta', 'prea lung', 'prea lunga', 'taraganat',
        'scenariu slab', 'poveste slaba', 'poveste proasta',
        'scenariu prost', 'scenariu banal', 'previzibil', 'previzibila',
        'cliseu', 'clisee', 'stereotip', 'stereotipuri', 'banal', 'banala',
        'fara logica', 'fără logică', 'absurd', 'absurda',
        'nu are sens', 'fara sens', 'fără sens',
        'actorie slaba', 'actorie proasta', 'actorie penibila',
        'rol slab', 'neconvingator', 'neconvingatoare',
        'supraestimat', 'supraestimata', 'supraapreciat',
        'nu recomand', 'nu recomanda', 'nu merita', 'nu merită',
        'pierdere de timp', 'pierdere de bani', 'regret',
        'pacat de timp', 'nu vedeti', 'nu il vedeti', 'evitati',
        'efecte speciale proaste', 'efecte ieftine', 'low budget',
        'productie ieftina', 'regie slaba', 'imagine slaba',
        'urat', 'urata', 'horror in sens rau', '1 din 10', '1/10',
        'nota 1', 'nota 2', '1 stea', '2 stele',
        'cea mai proasta', 'cel mai prost', 'niciodata'
        ];

    cuvinte_neutre TEXT[] := ARRAY[
        'ok', 'okay', 'acceptabil', 'acceptabila',
        'merge', 'mediu', 'medie', 'obisnut', 'obisnuita',
        'decent', 'decenta', 'satisfacator', 'satisfacatoare',
        'nici bun nici rau', 'asa si asa', 'nu e rau',
        'nu e nici bun', 'mediocru', 'mediocra',
        'trecator', 'trecatoare', 'de umplut timpul',
        'pentru o singura vizionare', 'o data'
        ];

    cuvant TEXT;
    comentariu_lowercase TEXT;
    scor_pozitiv INTEGER := 0;
    scor_negativ INTEGER := 0;

BEGIN
    IF text_comentariu IS NULL OR TRIM(text_comentariu) = '' THEN
        RETURN 'Neutru';
    END IF;

    comentariu_lowercase := lower(text_comentariu);

    FOREACH cuvant IN ARRAY expresii_negative LOOP
            IF comentariu_lowercase LIKE '%' || cuvant || '%' THEN
                scor := scor - 4;
            END IF;
    END LOOP;

    FOREACH cuvant IN ARRAY expresii_pozitive LOOP
            IF comentariu_lowercase LIKE '%' || cuvant || '%' THEN
                scor := scor + 4;
            END IF;
    END LOOP;


    FOREACH cuvant IN ARRAY cuvinte_pozitive LOOP
            IF comentariu_lowercase LIKE '%' || cuvant || '%' THEN
                IF NOT (comentariu_lowercase LIKE '%nu ' || cuvant || '%'
                    OR comentariu_lowercase LIKE '%deloc ' || cuvant || '%'
                    OR comentariu_lowercase LIKE '%nicidecum ' || cuvant || '%') THEN
                    scor := scor + 2;
                END IF;
            END IF;
        END LOOP;

    FOREACH cuvant IN ARRAY cuvinte_negative LOOP
            IF comentariu_lowercase LIKE '%' || cuvant || '%' THEN
                scor := scor - 2;
                scor_negativ := scor_negativ + 2;
            END IF;
        END LOOP;

    FOREACH cuvant IN ARRAY cuvinte_neutre LOOP
            IF comentariu_lowercase LIKE '%' || cuvant || '%' THEN
                IF scor > 0 THEN scor := scor - 1;
                ELSIF scor < 0 THEN scor := scor + 1;
                END IF;
            END IF;
        END LOOP;


    IF scor >= 2 THEN
        RETURN 'Pozitiv';
    ELSIF scor <= -2 THEN
        RETURN 'Negativ';
    ELSE
        RETURN 'Neutru';
    END IF;

END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_recalculare_rating()
    RETURNS TRIGGER AS $$
DECLARE
    v_id_film INT;
    v_medie_noua DECIMAL(4,2);
BEGIN
    IF (TG_OP = 'DELETE') THEN
        v_id_film := OLD.id_film;
    ELSE
        v_id_film := NEW.id_film;
    END IF;

    SELECT AVG(vot) INTO v_medie_noua
    FROM recenzii
    WHERE id_film = v_id_film;

    UPDATE filme
    SET rating_mediu = v_medie_noua
    WHERE id_film = v_id_film;

    IF (TG_OP = 'DELETE') THEN
        RETURN OLD;
    ELSE
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_valideaza_date_client()
    RETURNS TRIGGER AS $$
BEGIN

    IF LENGTH(NEW.telefon_mobil) != 10 THEN
        RAISE EXCEPTION 'Eroare: Numarul de telefon % este invalid. Trebuie sa aiba fix 10 cifre!', NEW.telefon_mobil;
    END IF;


    NEW.nume := INITCAP(LOWER(NEW.nume));
    NEW.prenume := INITCAP(LOWER(NEW.prenume));

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verifica_nota_recenzie(p_vot INT)
    RETURNS VOID AS $$
BEGIN
    IF (p_vot < 1 OR p_vot > 10) THEN
        RAISE EXCEPTION 'NOTA_INVALIDA'
            USING DETAIL = 'Votul trebuie sa fie intre 1 si 10. Valoarea primita: ' || p_vot,
                ERRCODE = '45001';
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION valideaza_comentariu_recenzie(p_vot INT, p_comentariu TEXT)
    RETURNS VOID AS $$
BEGIN
    IF (p_vot < 5 AND (p_comentariu IS NULL OR LENGTH(TRIM(p_comentariu)) < 5)) THEN
        RAISE EXCEPTION 'COMENTARIU_INSUFICIENT'
            USING DETAIL = 'Pentru note mici este obligatoriu un comentariu explicativ.',
                ERRCODE = '45002';
    END IF;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION calculeaza_sentiment_film(titlu_cautat TEXT)
    RETURNS TEXT AS $$
DECLARE
    v_exista_film BOOLEAN;
    v_total INT := 0;
    v_poz   INT := 0;
    v_neg   INT := 0;
    v_neu   INT := 0;
BEGIN
    SELECT EXISTS(SELECT 1 FROM filme WHERE titlu ILIKE titlu_cautat) INTO v_exista_film;

    IF NOT v_exista_film THEN
        RETURN 'FILM_INEXISTENT';
    END IF;

    SELECT
        COUNT(*),
        COUNT(CASE WHEN LOWER(analizeaza_sentiment_complex(r.comentariu)) = 'pozitiv' THEN 1 END),
        COUNT(CASE WHEN LOWER(analizeaza_sentiment_complex(r.comentariu)) = 'negativ' THEN 1 END),
        COUNT(CASE WHEN LOWER(analizeaza_sentiment_complex(r.comentariu)) = 'neutru' THEN 1 END)
    INTO v_total, v_poz, v_neg, v_neu
    FROM recenzii r
             JOIN filme f ON r.id_film = f.id_film
    WHERE f.titlu ILIKE titlu_cautat;

    IF v_total = 0 THEN
        RETURN 'NICIO RECENZIE';
    END IF;

    IF v_poz >= v_neg AND v_poz >= v_neu THEN RETURN 'POZITIV';
    ELSIF v_neg >= v_poz AND v_neg >= v_neu THEN RETURN 'NEGATIV';
    ELSE RETURN 'NEUTRU'; END IF;
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION calculeaza_sentiment_actor(prenume_cautat TEXT, nume_familie_cautat TEXT)
    RETURNS TEXT AS $$
DECLARE
    v_exista_actor BOOLEAN;
    v_total INT := 0;
    v_poz   INT := 0;
    v_neg   INT := 0;
    v_neu   INT := 0;
BEGIN
    SELECT EXISTS(
        SELECT 1 FROM actori
        WHERE prenume ILIKE prenume_cautat AND nume_familie ILIKE nume_familie_cautat
    ) INTO v_exista_actor;

    IF NOT v_exista_actor THEN
        RETURN 'ACTOR_INEXISTENT';
    END IF;

    SELECT
        COUNT(*),
        COUNT(CASE WHEN LOWER(ra.sentiment_detectat) = 'pozitiv' THEN 1 END),
        COUNT(CASE WHEN LOWER(ra.sentiment_detectat) = 'negativ' THEN 1 END),
        COUNT(CASE WHEN LOWER(ra.sentiment_detectat) = 'neutru' THEN 1 END)
    INTO v_total, v_poz, v_neg, v_neu
    FROM recenzii_actori ra
             JOIN actori a ON ra.id_actor = a.id_actor
    WHERE a.prenume ILIKE prenume_cautat
      AND a.nume_familie ILIKE nume_familie_cautat;

    IF v_total = 0 THEN
        RETURN 'NICIO RECENZIE';
    END IF;

    IF v_poz >= v_neg AND v_poz >= v_neu THEN RETURN 'POZITIV';
    ELSIF v_neg >= v_poz AND v_neg >= v_neu THEN RETURN 'NEGATIV';
    ELSE RETURN 'NEUTRU'; END IF;
END;
$$ LANGUAGE plpgsql;







CREATE OR REPLACE FUNCTION profil_cinematografic(p_id_client BIGINT)
    RETURNS TABLE(
                     categorie_preferata    TEXT,
                     actor_favorit          TEXT,
                     sentiment_dominant     TEXT,
                     total_vizualizari      BIGINT,
                     total_recenzii         BIGINT,
                     vizualizari_abandonate BIGINT,
                     minute_totale          BIGINT
                 ) AS $$
DECLARE
    v_categorie   TEXT;
    v_actor       TEXT;
    v_sentiment   TEXT;
    v_viz_total   BIGINT;
    v_rec_total   BIGINT;
    v_abandonate  BIGINT;
    v_minute      BIGINT;
BEGIN
    SELECT f.categorie
    INTO v_categorie
    FROM vizualizari v
             JOIN versiuni_filme vf ON v.id_versiune = vf.id_versiune
             JOIN filme f           ON vf.id_film   = f.id_film
    WHERE v.id_client = p_id_client
    GROUP BY f.categorie
    ORDER BY COUNT(*) DESC
    LIMIT 1;

    SELECT a.prenume || ' ' || a.nume_familie
    INTO v_actor
    FROM vizualizari v
             JOIN versiuni_filme    vf ON v.id_versiune = vf.id_versiune
             JOIN distributie_filme df ON vf.id_film  = df.id_film
             JOIN actori a              ON df.id_actor = a.id_actor
    WHERE v.id_client = p_id_client
    GROUP BY a.id_actor, a.prenume, a.nume_familie
    ORDER BY COUNT(*) DESC
    LIMIT 1;

    BEGIN
        SELECT analizeaza_sentiment_complex(r.comentariu)
        INTO v_sentiment
        FROM recenzii r
        WHERE r.id_client = p_id_client
        GROUP BY analizeaza_sentiment_complex(r.comentariu)
        ORDER BY COUNT(*) DESC
        LIMIT 1;
    EXCEPTION WHEN OTHERS THEN
        v_sentiment := NULL;
    END;

    SELECT COUNT(*)
    INTO v_viz_total
    FROM vizualizari
    WHERE id_client = p_id_client;

    SELECT
        (SELECT COUNT(*) FROM recenzii       WHERE id_client = p_id_client) +
        (SELECT COUNT(*) FROM recenzii_actori WHERE id_client = p_id_client)
    INTO v_rec_total;

    SELECT COUNT(*)
    INTO v_abandonate
    FROM vizualizari
    WHERE id_client = p_id_client AND stare_finalizata = false;

    SELECT COALESCE(SUM(durata_vizionata_minute), 0)
    INTO v_minute
    FROM vizualizari
    WHERE id_client = p_id_client;

    RETURN QUERY SELECT
                     v_categorie,
                     v_actor,
                     v_sentiment,
                     v_viz_total,
                     v_rec_total,
                     v_abandonate,
                     v_minute;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION grupare_clienti()
    RETURNS TABLE(
                     id_client           BIGINT,
                     nume_client         TEXT,
                     email               TEXT,
                     categorie_dominanta TEXT,
                     sentiment_dominant  TEXT,
                     nr_vizualizari      BIGINT,
                     vot_mediu           NUMERIC,
                     frecventa           TEXT,
                     preferinta_actor    TEXT,
                     grup                TEXT
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            c.id_client::BIGINT,
            (c.nume || ' ' || c.prenume)::TEXT,
            c.email::TEXT,

            COALESCE(cat.categorie, 'Fara preferinta')::TEXT,

            COALESCE(sent.sentiment, 'Neutru')::TEXT,

            COUNT(DISTINCT v.id_vizualizare)::BIGINT,

            COALESCE(ROUND(AVG(r.vot), 1), 0)::NUMERIC,

            CASE
                WHEN COUNT(DISTINCT v.id_vizualizare) = 0    THEN 'Inactiv'
                WHEN COUNT(DISTINCT v.id_vizualizare) <= 3   THEN 'Rar'
                WHEN COUNT(DISTINCT v.id_vizualizare) <= 10  THEN 'Mediu'
                ELSE 'Activ'
                END::TEXT,

            COALESCE(actor.nume_actor, 'Nedefinit')::TEXT,

            (
                COALESCE(cat.categorie, 'Fara preferinta') || ' - ' ||
                COALESCE(sent.sentiment, 'Neutru')         || ' - ' ||
                CASE
                    WHEN COUNT(DISTINCT v.id_vizualizare) = 0   THEN 'Inactiv'
                    WHEN COUNT(DISTINCT v.id_vizualizare) <= 3  THEN 'Rar'
                    WHEN COUNT(DISTINCT v.id_vizualizare) <= 10 THEN 'Mediu'
                    ELSE 'Activ'
                    END
                )::TEXT

        FROM clienti c
                 LEFT JOIN vizualizari      v   ON v.id_client  = c.id_client
                 LEFT JOIN recenzii         r   ON r.id_client  = c.id_client

                 LEFT JOIN LATERAL (
            SELECT f.categorie
            FROM vizualizari v2
                     JOIN versiuni_filme vf ON v2.id_versiune = vf.id_versiune
                     JOIN filme f           ON vf.id_film     = f.id_film
            WHERE v2.id_client = c.id_client
            GROUP BY f.categorie
            ORDER BY COUNT(*) DESC
            LIMIT 1
            ) cat ON true

                 LEFT JOIN LATERAL (
            SELECT analizeaza_sentiment_complex(r2.comentariu) AS sentiment
            FROM recenzii r2
            WHERE r2.id_client = c.id_client
            GROUP BY analizeaza_sentiment_complex(r2.comentariu)
            ORDER BY COUNT(*) DESC
            LIMIT 1
            ) sent ON true

                 LEFT JOIN LATERAL (
            SELECT a.prenume || ' ' || a.nume_familie AS nume_actor
            FROM vizualizari        v3
                     JOIN versiuni_filme     vf3 ON v3.id_versiune  = vf3.id_versiune
                     JOIN distributie_filme  df  ON vf3.id_film     = df.id_film
                     JOIN actori             a   ON df.id_actor      = a.id_actor
            WHERE v3.id_client = c.id_client
            GROUP BY a.id_actor, a.prenume, a.nume_familie
            ORDER BY COUNT(*) DESC
            LIMIT 1
            ) actor ON true

        GROUP BY
            c.id_client, c.nume, c.prenume, c.email,
            cat.categorie, sent.sentiment, actor.nume_actor

        ORDER BY grup, nume_client;
END;
$$ LANGUAGE plpgsql STABLE;


CREATE OR REPLACE FUNCTION predictii_sezoniere()
    RETURNS TABLE(
                     luna              INT,
                     luna_nume         TEXT,
                     categorie         TEXT,
                     numar_vizualizari BIGINT,
                     film_popular      TEXT,
                     tip_rezultat      TEXT
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            12::INT,
            'Decembrie'::TEXT,
            f.categorie::TEXT,
            COUNT(*)::BIGINT,
            (SELECT f2.titlu FROM filme f2
                                      JOIN versiuni_filme vf2 ON f2.id_film = vf2.id_film
                                      JOIN vizualizari v2 ON vf2.id_versiune = v2.id_versiune
             WHERE f2.categorie = f.categorie
               AND EXTRACT(MONTH FROM v2.data_vizualizarii) = 12
             GROUP BY f2.titlu ORDER BY COUNT(*) DESC LIMIT 1)::TEXT,
            'Sezonier - Craciun'::TEXT
        FROM filme f
                 JOIN versiuni_filme vf ON f.id_film = vf.id_film
                 JOIN vizualizari v ON vf.id_versiune = v.id_versiune
        WHERE f.categorie IN ('Animatie', 'Familie', 'Comedie')
          AND EXTRACT(MONTH FROM v.data_vizualizarii) = 12
        GROUP BY f.categorie
        ORDER BY COUNT(*) DESC;

    RETURN QUERY
        SELECT
            2::INT,
            'Februarie'::TEXT,
            f.categorie::TEXT,
            COUNT(*)::BIGINT,
            (SELECT f2.titlu FROM filme f2
                                      JOIN versiuni_filme vf2 ON f2.id_film = vf2.id_film
                                      JOIN vizualizari v2 ON vf2.id_versiune = v2.id_versiune
             WHERE f2.categorie = f.categorie
               AND EXTRACT(MONTH FROM v2.data_vizualizarii) = 2
             GROUP BY f2.titlu ORDER BY COUNT(*) DESC LIMIT 1)::TEXT,
            'Sezonier - Valentine'::TEXT
        FROM filme f
                 JOIN versiuni_filme vf ON f.id_film = vf.id_film
                 JOIN vizualizari v ON vf.id_versiune = v.id_versiune
        WHERE f.categorie IN ('Romance', 'Drama')
          AND EXTRACT(MONTH FROM v.data_vizualizarii) = 2
        GROUP BY f.categorie
        ORDER BY COUNT(*) DESC;

    RETURN QUERY
        SELECT
            10::INT,
            'Octombrie'::TEXT,
            f.categorie::TEXT,
            COUNT(*)::BIGINT,
            (SELECT f2.titlu FROM filme f2
                                      JOIN versiuni_filme vf2 ON f2.id_film = vf2.id_film
                                      JOIN vizualizari v2 ON vf2.id_versiune = v2.id_versiune
             WHERE f2.categorie = f.categorie
               AND EXTRACT(MONTH FROM v2.data_vizualizarii) = 10
             GROUP BY f2.titlu ORDER BY COUNT(*) DESC LIMIT 1)::TEXT,
            'Sezonier - Halloween'::TEXT
        FROM filme f
                 JOIN versiuni_filme vf ON f.id_film = vf.id_film
                 JOIN vizualizari v ON vf.id_versiune = v.id_versiune
        WHERE f.categorie IN ('Horror', 'Thriller')
          AND EXTRACT(MONTH FROM v.data_vizualizarii) = 10
        GROUP BY f.categorie
        ORDER BY COUNT(*) DESC;

    RETURN QUERY
        SELECT
            6::INT,
            'Iunie'::TEXT,
            f.categorie::TEXT,
            COUNT(*)::BIGINT,
            (SELECT f2.titlu FROM filme f2
                                      JOIN versiuni_filme vf2 ON f2.id_film = vf2.id_film
                                      JOIN vizualizari v2 ON vf2.id_versiune = v2.id_versiune
             WHERE f2.categorie = f.categorie
               AND EXTRACT(MONTH FROM v2.data_vizualizarii) = 6
             GROUP BY f2.titlu ORDER BY COUNT(*) DESC LIMIT 1)::TEXT,
            'Sezonier - Vara'::TEXT
        FROM filme f
                 JOIN versiuni_filme vf ON f.id_film = vf.id_film
                 JOIN vizualizari v ON vf.id_versiune = v.id_versiune
        WHERE f.categorie IN ('Actiune', 'SF', 'Aventura')
          AND EXTRACT(MONTH FROM v.data_vizualizarii) = 6
        GROUP BY f.categorie
        ORDER BY COUNT(*) DESC;

    RETURN QUERY
        SELECT
            7::INT,
            'Iulie'::TEXT,
            f.categorie::TEXT,
            COUNT(*)::BIGINT,
            (SELECT f2.titlu FROM filme f2
                                      JOIN versiuni_filme vf2 ON f2.id_film = vf2.id_film
                                      JOIN vizualizari v2 ON vf2.id_versiune = v2.id_versiune
             WHERE f2.categorie = f.categorie
               AND EXTRACT(MONTH FROM v2.data_vizualizarii) = 7
             GROUP BY f2.titlu ORDER BY COUNT(*) DESC LIMIT 1)::TEXT,
            'Sezonier - Vacanta'::TEXT
        FROM filme f
                 JOIN versiuni_filme vf ON f.id_film = vf.id_film
                 JOIN vizualizari v ON vf.id_versiune = v.id_versiune
        WHERE f.categorie IN ('Actiune', 'SF', 'Aventura')
          AND EXTRACT(MONTH FROM v.data_vizualizarii) = 7
        GROUP BY f.categorie
        ORDER BY COUNT(*) DESC;

    RETURN QUERY
        SELECT
            3::INT,
            'Martie'::TEXT,
            f.categorie::TEXT,
            COUNT(*)::BIGINT,
            (SELECT f2.titlu FROM filme f2
                                      JOIN versiuni_filme vf2 ON f2.id_film = vf2.id_film
                                      JOIN vizualizari v2 ON vf2.id_versiune = v2.id_versiune
             WHERE f2.categorie = f.categorie
               AND EXTRACT(MONTH FROM v2.data_vizualizarii) = 3
             GROUP BY f2.titlu ORDER BY COUNT(*) DESC LIMIT 1)::TEXT,
            'Sezonier - Oscar'::TEXT
        FROM filme f
                 JOIN versiuni_filme vf ON f.id_film = vf.id_film
                 JOIN vizualizari v ON vf.id_versiune = v.id_versiune
        WHERE f.categorie IN ('Drama', 'Biografie', 'Istoric')
          AND EXTRACT(MONTH FROM v.data_vizualizarii) = 3
        GROUP BY f.categorie
        ORDER BY COUNT(*) DESC;

    RETURN QUERY
        SELECT *
        FROM (
                 WITH lunar AS (
                     SELECT
                         EXTRACT(MONTH FROM v.data_vizualizarii)::INT AS m,
                         f.categorie                                   AS cat,
                         f.titlu,
                         COUNT(*)::BIGINT                              AS nr
                     FROM vizualizari v
                              JOIN versiuni_filme vf ON v.id_versiune = vf.id_versiune
                              JOIN filme         f  ON vf.id_film    = f.id_film
                     WHERE EXTRACT(MONTH FROM v.data_vizualizarii) NOT IN (2, 3, 6, 7, 10, 12)
                     GROUP BY
                         EXTRACT(MONTH FROM v.data_vizualizarii),
                         f.categorie,
                         f.titlu
                 ),
                      lunar_total AS (
                          SELECT
                              lunar.m,
                              lunar.cat,
                              SUM(lunar.nr)::BIGINT AS total_cat
                          FROM lunar
                          GROUP BY lunar.m, lunar.cat
                      ),
                      film_top AS (
                          SELECT ranked.m, ranked.cat, ranked.titlu AS film_top
                          FROM (
                                   SELECT
                                       lunar.m, lunar.cat, lunar.titlu,
                                       ROW_NUMBER() OVER (
                                           PARTITION BY lunar.m, lunar.cat
                                           ORDER BY lunar.nr DESC
                                           ) AS rn
                                   FROM lunar
                               ) ranked
                          WHERE ranked.rn = 1
                      )
                 SELECT
                     lt.m::INT,
                     TRIM(TO_CHAR(TO_DATE(lt.m::TEXT, 'MM'), 'Month'))::TEXT,
                     lt.cat::TEXT,
                     lt.total_cat::BIGINT,
                     ft.film_top::TEXT,
                     'Popular'::TEXT
                 FROM lunar_total lt
                          JOIN film_top ft ON lt.m = ft.m AND lt.cat = ft.cat
                 ORDER BY lt.m, lt.total_cat DESC
             ) rezultat;

END;
$$ LANGUAGE plpgsql STABLE;