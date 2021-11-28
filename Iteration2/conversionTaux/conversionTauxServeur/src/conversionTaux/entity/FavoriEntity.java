package conversionTaux.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Favori")
public class FavoriEntity {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
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

    @ManyToOne (cascade = CascadeType.ALL)
    private TauxEntity leTaux;

    public TauxEntity getLeTaux() {
        return leTaux;
    }

    public void setLeTaux(TauxEntity t) {
        this.leTaux = t ; 
    }

    public FavoriEntity()
    {

    }

    public FavoriEntity(String libelleFavori, TauxEntity t)
    {
        this.libelleFavori = libelleFavori;
        this.leTaux = t;
    }
}
