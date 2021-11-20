CREATE TABLE Taux
   (
    idTaux INTEGER NOT NULL  AUTO_INCREMENT,
    monnaieA VARCHAR(10) NULL,
    monnaieB VARCHAR(32)  NULL,
    taux FLOAT NULL,
    CONSTRAINT PK_idTaux PRIMARY KEY (idTaux)
   ) ;

insert into Taux values (1, 'euros', 'dollars', 1.27);
insert into Taux values (2, 'dollars', 'euros', 0.78);
insert into Taux values (3, 'euros', 'yen', 149.53);
insert into Taux values (4, 'yen', 'euros', 0.0066);
insert into Taux values (5, 'dollars', 'euros', 0.70);  -- Nouvelle paire dollars-euros pour tester l'exception "NonUniqueResultException"

CREATE TABLE Abonne
   (
    idAbonne INTEGER  NOT NULL  AUTO_INCREMENT,
    login VARCHAR(10) NULL,
    passwd VARCHAR(32)  NULL,
    CONSTRAINT PK_idAbonne PRIMARY KEY (idAbonne)
   ) ;
