
DROP TABLE Abonne_Favori;
DROP TABLE Favori;
DROP TABLE Abonne;
DROP TABLE Taux;

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

CREATE TABLE Favori
   (
    idFavori INTEGER NOT NULL AUTO_INCREMENT,
    libelleFavori VARCHAR(32) NULL,
    leTaux_idTaux INTEGER NULL,
    CONSTRAINT PK_idFavori PRIMARY KEY (idFavori),
    CONSTRAINT FK_Taux FOREIGN KEY (leTaux_idTaux)
                              REFERENCES Taux (idTaux)
   ) ;
   

CREATE TABLE Abonne_Favori
    (
    AbonneEntity_idAbonne INTEGER  NOT NULL,
    lesFavoris_idFavori INTEGER NOT NULL,
    CONSTRAINT PK_Abonne_Favori PRIMARY KEY (AbonneEntity_idAbonne, lesFavoris_idFavori),
    CONSTRAINT FK_AF FOREIGN KEY (AbonneEntity_idAbonne)
                              REFERENCES Abonne (idAbonne),
     CONSTRAINT FK_FA FOREIGN KEY (lesFavoris_idFavori)
      REFERENCES Favori (idFavori)
    );
   

