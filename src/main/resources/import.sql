INSERT INTO Abos (abos_id, titel, status) VALUES

    (1, 'Kaffeemaschiene', TRUE),

    (2, 'Obstschranck', TRUE),
    
    (3, 'Snackkasten', TRUE),
    
    (4, 'Geschierschrank', FALSE);

INSERT INTO Benutzer (benutzer_id, firstName, secoundName, eMail, passwort, isAdmin) VALUES

    (1, 'Rocco', 'Blackwell', 'rocco.blackwell@coworking.ch', 'adminpasswort', TRUE),

    (2, 'Eesha', 'Halliday', 'eesha.halliday@gmail.com', 'halli', FALSE),

    (3, 'test', 'test', 'test@test.ch', 'test', FALSE);



INSERT INTO Plaetze (plaetze_id, raumNr, platzNr) VALUES

    (1, 1, 1),

    (2, 1, 2),
    
    (3, 2, 1),

    (4, 2, 2);


INSERT INTO Buchungen (buchungen_id, status, startDate, endDate, benutzer_id) VALUES

    (1, TRUE, '2022-02-02', '2022-02-03', 1),

    (2, TRUE, '2022-02-02', '2022-02-03', 2),

    (3, FALSE, '2022-02-02', '2022-02-03', 3);