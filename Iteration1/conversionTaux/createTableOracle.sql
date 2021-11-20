CREATE TABLE Taux
   (
    idTaux NUMBER(10)  NOT NULL,
    monnaieA VARCHAR2(10) NULL,
    monnaieB VARCHAR2(32)  NULL,
    taux NUMBER(19,4) NULL,
    CONSTRAINT PK_idTaux PRIMARY KEY (idTaux)
   ) ;

insert into Taux values (1, 'euros', 'dollars', 1.27);
insert into Taux values (2, 'dollars', 'euros', 0.78);
insert into Taux values (3, 'euros', 'yen', 149.53);
insert into Taux values (4, 'yen', 'euros', 0.0066);
insert into Taux values (5, 'dollars', 'euros', 0.70);  -- Nouvelle paire dollars-euros pour tester l'exception "NonUniqueResultException"

CREATE TABLE Abonne
   (
    idAbonne NUMBER(10)  NOT NULL,
    login VARCHAR2(10) NULL,
    passwd VARCHAR2(32)  NULL,
    CONSTRAINT PK_idAbonne PRIMARY KEY (idAbonne)
   ) ;
