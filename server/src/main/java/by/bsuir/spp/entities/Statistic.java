package by.bsuir.spp.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "statistic")
public class Statistic {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private Date beginDate;
    @Column
    private Date endDate;
    @OneToOne
    private UserOrder userOrder;
    @OneToOne
    private User user;
    @OneToOne
    private User customer;

    public Statistic() {

    }

    public long getId() {
        return id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public User getCustomer() {
        return customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginDate, endDate, customer, userOrder, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Statistic statistic = (Statistic) obj;
        return id == statistic.id &&
                Objects.equals(beginDate, statistic.beginDate) &&
                Objects.equals(endDate, statistic.endDate) &&
                Objects.equals(userOrder, statistic.userOrder) &&
                Objects.equals(customer, statistic.customer) &&
                Objects.equals(user, statistic.user);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Statistic{");
        sb.append("id=").append(id);
        sb.append(", beginDate='").append(beginDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", customer='").append(customer).append('\'');
        sb.append(", userOrder='").append(userOrder).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
