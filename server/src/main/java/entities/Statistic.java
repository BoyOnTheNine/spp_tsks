package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "statistic")
public class Statistic {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private Date beginDate;
    @Column
    private Date endDate;
    @Column
    private int idOrder;
    @Column
    private int idUser;
    @Column
    private int idCustomer;

    public Statistic() {

    }

    public int getId() {
        return id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginDate, endDate, idCustomer, idOrder, idUser);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Statistic statistic = (Statistic) obj;
        return id == statistic.id &&
                Objects.equals(beginDate, statistic.beginDate) &&
                Objects.equals(endDate, statistic.endDate) &&
                Objects.equals(idOrder, statistic.idOrder) &&
                Objects.equals(idCustomer, statistic.idCustomer) &&
                Objects.equals(idUser, statistic.idUser);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Statistic{");
        sb.append("id=").append(id);
        sb.append(", beginDate='").append(beginDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", idUser='").append(idUser).append('\'');
        sb.append(", idCustomer='").append(idCustomer).append('\'');
        sb.append(", idOrder='").append(idOrder).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
