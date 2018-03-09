package bertosh.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.*;

@Entity
@Table(name = "orders")
public class UserOrder {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Offer offer;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<User> workers;
    @OneToOne(cascade = CascadeType.ALL)
    private User customer;

    public UserOrder() {

    }

    public long getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", offer=" + offer +
                ", workers=" + workers +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder userOrder = (UserOrder) o;

        if (id != userOrder.id) return false;
        if (offer != null ? !offer.equals(userOrder.offer) : userOrder.offer != null) return false;
        if (workers != null ? !workers.equals(userOrder.workers) : userOrder.workers != null) return false;
        return customer != null ? customer.equals(userOrder.customer) : userOrder.customer == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offer, workers, customer);
    }
}
