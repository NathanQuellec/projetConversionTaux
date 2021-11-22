
CREATE TABLE Abonne
   (
    idAbonne INTEGER  NOT NULL,
    login VARCHAR(10) NULL,
    passwd VARCHAR(32)  NULL,
    CONSTRAINT PK_idAbonne PRIMARY KEY (idAbonne)
   ) ;


CREATE TABLE Favori
   (
    idFavori INTEGER NOT NULL,
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
   

