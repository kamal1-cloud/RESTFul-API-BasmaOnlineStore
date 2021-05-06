package ma.youcode.store.Modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
//@JsonIgnoreProperties(value = {"password", "idUser"})
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUser;
    @Column(nullable = false, length = 25)
    private String nom;
    @Column(nullable = false, length = 30)
    private String prenom;
    @Column(nullable = false, length = 255, unique=true)
    private String email;
//    @JsonIgnore
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false)
    private boolean status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole")
    )
    private Set<Roles> roles = new HashSet<>();


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}