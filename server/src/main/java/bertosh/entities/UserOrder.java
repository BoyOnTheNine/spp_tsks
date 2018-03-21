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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_workers", joinColumns
            = @JoinColumn(name = "order_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id",
                    referencedColumnName = "id"))
    private Set<User> workers;

    public UserOrder() {

    }

    public long getId() {
        return id;
    }

    public Set<User> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<User> workers) {
        this.workers = workers;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", offer=" + offer +
                ", workers=" + workers +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workers, offer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder userOrder = (UserOrder) o;

        if (id != userOrder.id) return false;
        if (offer != null ? !offer.equals(userOrder.offer) : userOrder.offer != null) return false;
        return workers != null ? workers.equals(userOrder.workers) : userOrder.workers == null;
    }
}
