DROP TABLE Abonne_Favori;
DROP TABLE Favori;
DROP TABLE Abonne;
CREATE TABLE Abonne
   (
    idAbonne NUMBER(10)  NOT NULL,
    login VARCHAR2(10) NULL,
    passwd VARCHAR2(32)  NULL,
    CONSTRAINT PK_idAbonne PRIMARY KEY (idAbonne)
   ) ;


CREATE TABLE Favori
   (
    idFavori NUMBER(10)  NOT NULL,
    libelleFavori VARCHAR2(32) NULL,
    leTaux_idTaux NUMBER(19,4) NULL,
    CONSTRAINT PK_idFavori PRIMARY KEY (idFavori),
    CONSTRAINT FK_Taux FOREIGN KEY (leTaux_idTaux)
                              REFERENCES Taux (idTaux)   -- ???
   ) ;
   

CREATE TABLE Abonne_Favori
    (
    AbonneEntity_idAbonne NUMBER (6) NOT NULL,
    lesFavoris_idFavori NUMBER(10) NOT NULL,
    CONSTRAINT PK_Abonne_Favori PRIMARY KEY (AbonneEntity_idAbonne, lesFavoris_idFavori),
    CONSTRAINT FK_AF FOREIGN KEY (AbonneEntity_idAbonne)
                              REFERENCES Abonne (idAbonne),
     CONSTRAINT FK_FA FOREIGN KEY (lesFavoris_idFavori)
      REFERENCES Favori (idFavori)
    );
   

