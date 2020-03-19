package entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class User_roles implements Serializable {
 
 
 
	@Id
	@OneToOne(targetEntity = User.class)
	 @JoinColumn(
             name = "user_id", referencedColumnName = "id")
	private User user_id;
	 
	@OneToOne(targetEntity = Role.class)
	 @JoinColumn(name = "roles_id", referencedColumnName = "id")
	 private User roles_id;

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public User getRoles_id() {
		return roles_id;
	}

	public void setRoles_id(User roles_id) {
		this.roles_id = roles_id;
	}
	
 
 
}
