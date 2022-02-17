package conversionTaux.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Abonne")
public class AbonneEntity {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
	private int idAbonne;
    public int getIdAbonne ()  {
        return this.idAbonne;
    }

    public void setIdAbonne (int value)  {
        this.idAbonne = value; 
    }

    private String login;
    public String getLogin ()  {
        return this.login;
    }

    public void setLogin (String value)  {
        this.login = value;
    }

    private String passwd;
    public String getPasswd ()  {
        return this.passwd;
    }

    public void setPasswd (String value)  {
        this.passwd = value; 
    }

    @OneToMany (cascade = CascadeType.ALL)
    private Collection<FavoriEntity> lesFavoris;
    
    public Collection<FavoriEntity> getLesFavoris() 
    {
        return this.lesFavoris;
    }

    public void setLesFavoris(Collection<FavoriEntity> lesFavoris) {
        this.lesFavoris = lesFavoris; 
    }

    public AbonneEntity()
    {
    }
    
    public AbonneEntity(String login, String passwd)
    {
        this.login = login;
        this.passwd = passwd;
    }
    
}
