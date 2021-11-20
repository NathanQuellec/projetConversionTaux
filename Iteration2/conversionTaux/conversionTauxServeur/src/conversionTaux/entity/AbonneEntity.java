package conversionTaux.entity;

import javax.persistence.*;
import java.util.*;

/*
 classe java à completer et a transformer en classe entity JPA
- attributs : idAbonne, login, passwd et l'attribut resultant de la traduction de l'association entre AbonneEntity et FavoriEntity
- getters-setters de chaque attribut et au moins un constructeur vide
- annotations JPA
 */

@Entity
@Table(name = "Abonne")
public class AbonneEntity {
    
    // Id de AbonneEntity
    @Id @GeneratedValue
    private int idAbonne;
    public int getIdAbonne() {
        return this.idAbonne;
    }

    public void setIdAbonne(int idAbonne) {
        this.idAbonne = idAbonne;
    }

    private String login;
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


    private String passwd;
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

    // association avec FavoriEntity
    private List<FavoriEntity> lesFavoris;

    @OneToMany
    
    public List<FavoriEntity> getLesFavoris(){
        return this.lesFavoris;
    }

    public void setLesFavoris(List<FavoriEntity> lesFavoris){
        this.lesFavoris = lesFavoris;
    }

    // constructeurs
    public AbonneEntity(){

    }

    public AbonneEntity(String login, String passwd){
        this.login = login;
        this.passwd = passwd;
    }    
}
