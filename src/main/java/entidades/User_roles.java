package entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@IdClass(User_roles.class)
public class User_roles implements Serializable {
 

 
	 @Id
	@OneToOne(targetEntity = User.class)
	 @JoinColumn(
             name = "user_id", referencedColumnName = "id")
	private User user_id;
	 @Id
	@OneToOne(targetEntity = Role.class)
	 @JoinColumn(name = "roles_id", referencedColumnName = "id")
	 private User roles_id;

 
 
}
