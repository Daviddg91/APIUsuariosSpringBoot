package entidades;


import javax.persistence.*;

 
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Embeddable
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    /* Asi es como veria */
  /*  
   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private  Collection<Role> roles;
 */
   @OneToMany(fetch = FetchType.EAGER, targetEntity = User_roles.class , cascade = CascadeType.ALL)
   private Set<User_roles> user_roles;
   
    /* Aqui carga los roles */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles;
    
     
  /*  @OneToOne(fetch = FetchType.EAGER, targetEntity = User_roles.class)
    private Collection<User_roles> user_roles;
    */
    
    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password, Collection < Role > roles, Set<User_roles> usr_roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles =  roles;
        this.user_roles =  usr_roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

 
 

	 

 
 
 

	public Set<User_roles> getUser_roles() {
		return user_roles;
	}

	public void setUser_roles(Set<User_roles> user_roles) {
		this.user_roles = user_roles;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
        this.password = password;
    }
/*
    public Collection < Role > getRoles() {
        return roles;
    }

   public void setRoles(Collection < Role > roles) {
        this.roles = roles;
    }
*/
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + "*********" + '\'' +
          //  ", roles=" + roles +
             
            '}';
    }
}