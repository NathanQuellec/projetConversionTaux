package conversionTaux.entity;

import javax.persistence.*;
import java.util.*;

/*
 classe java à completer et a transformer en classe entity JPA
- attributs : idFavori, libelleFavori et l'attribut resultant de la traduction de l'association entre FavoriEntity et TauxEntity
- getters-setters de chaque attribut et au moins un constructeur vide
- annotations JPA
 */

@Entity
@Table (name = "Favori")
public class FavoriEntity {

    // Id de FavoriEntity
    @Id @GeneratedValue
    private int idFavori;
	public int getIdFavori() {
		return this.idFavori;
	}

	public void setIdFavori(int idFavori) {
		this.idFavori = idFavori;
	}


    private String libelleFavori;
	public String getLibelleFavori() {
		return this.libelleFavori;
	}

	public void setLibelleFavori(String libelleFavori) {
		this.libelleFavori = libelleFavori;
	}

    // association avec TauxEntity
    private TauxEntity leTaux;

    @ManyToOne

    public TauxEntity getLeTaux(){
        return this.leTaux;
    }

    public void setLeTaux(TauxEntity leTaux){
        this.leTaux = leTaux;
    }


    // constructeurs
    public FavoriEntity(){

    }

    public FavoriEntity(String libelleFavori){
        this.libelleFavori = libelleFavori;
    }
    
}
