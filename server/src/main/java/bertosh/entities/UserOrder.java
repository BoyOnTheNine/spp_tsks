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
    //@OneToOne
    //private Offer offer;
    @OneToMany
    private List<User> workers = new ArrayList<>();

    public UserOrder() {

    }

    public long getId() {
        return id;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    /*public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }*/

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                //", offer=" + offer +
                ", workers=" + workers +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workers/*, offer*/);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder userOrder = (UserOrder) o;

        if (id != userOrder.id) return false;
        return workers != null ? workers.equals(userOrder.workers) : userOrder.workers == null;
    }
}
