package bertosh.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class UserOrder {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String description;
    @Column
    private Date date;
    @Column
    private double price;
    @Column
    private User idWorker;
    @Column
    private User idCustomer;

    public UserOrder() {

    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public User getIdCustomer() {
        return idCustomer;
    }

    public User getIdUser() {
        return idWorker;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdCustomer(User idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdUser(User idWorker) {
        this.idWorker = idWorker;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserOrder{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", idCustomer='").append(idCustomer).append('\'');
        sb.append(", idUser='").append(idWorker).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, date, price, idCustomer, idWorker);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserOrder userOrder = (UserOrder) obj;
        return id == userOrder.id &&
                Objects.equals(description, userOrder.description) &&
                Objects.equals(date, userOrder.date) &&
                Objects.equals(price, userOrder.price) &&
                Objects.equals(idCustomer, userOrder.idCustomer) &&
                Objects.equals(idWorker, userOrder.idWorker);
    }
}
