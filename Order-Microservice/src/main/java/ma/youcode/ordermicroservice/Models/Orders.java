package ma.youcode.ordermicroservice.Models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrder;

    @Column (name = "idProduit")
    private Integer idProduit;

    @Column (name = "quantity")
    private Integer quantite;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

    @Column (name = "commandePayee")
    private Boolean commandePayee;

    private String paymentType;

    @Column (name = "couponCode")
    private String codeCoupon;

    public Orders() {
    }

    public Orders(long idOrder, Integer idProduit, Integer quantite, Date dateCommande, Boolean commandePayee, String codeCoupon) {
        this.idOrder = idOrder;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.dateCommande = dateCommande;
        this.commandePayee = commandePayee;
        this.codeCoupon = codeCoupon;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Boolean getCommandePayee() {
        return commandePayee;
    }

    public void setCommandePayee(Boolean commandePayee) {
        this.commandePayee = commandePayee;
    }

    public String getCodeCoupon() {
        return codeCoupon;
    }

    public void setCodeCoupon(String codeCoupon) {
        this.codeCoupon = codeCoupon;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + idOrder +
                ", idProduit=" + idProduit +
                ", quantite=" + quantite +
                ", dateCommande=" + dateCommande +
                ", commandePayee=" + commandePayee +
                ", codeCoupon='" + codeCoupon + '\'' +
                '}';
    }
}
