package by.bsuir.spp.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.*;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 30)
    private String name;
    @Column
    private String description;
    @Column
    private Date date;
    @Column
    private double price;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "offer_category", joinColumns
            = @JoinColumn(name = "offer_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "id"))
    private List<Category> categories = new ArrayList<>();
    @OneToOne
    private User customer;


    public Offer() {

    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (id != offer.id) return false;
        if (Double.compare(offer.price, price) != 0) return false;
        if (name != null ? !name.equals(offer.name) : offer.name != null) return false;
        if (description != null ? !description.equals(offer.description) : offer.description != null) return false;
        if (date != null ? !date.equals(offer.date) : offer.date != null) return false;
        if (categories != null ? !categories.equals(offer.categories) : offer.categories != null) return false;
        return customer != null ? customer.equals(offer.customer) : offer.customer == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, description, categories, price, customer);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", categories=" + categories +
                ", customer=" + customer +
                '}';
    }
}
