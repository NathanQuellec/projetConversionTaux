package conversionTaux.entity;

import javax.persistence.*;
import java.util.*;

/*
 classe java à completer et a transformer en classe entity JPA
- attributs : idAbonne, login, passwd 
- getters-setters de chaque attribut et au moins un constructeur vide
- annotations JPA
 */

@Entity
@Table (name = "Admin")
public class AdminEntity {

    @Id
	private int idAdmin;
    public int getIdAdmin ()  {
        return this.idAdmin;
    }

    public void setIdAdmin (int value)  {
        this.idAdmin = value;
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

    public AdminEntity()
    {
    }
}
