package mk.ukim.finki.emt.shoecatalog.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.shoecatalog.domain.models.enums.Categories;
import mk.ukim.finki.emt.shoecatalog.domain.models.enums.Gender;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ShoeId;

@Entity
@Data
@Table(name = "product_shoe")
public class Shoe extends AbstractEntity<ShoeId> {


    private  String seriesName;

    private Money price;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Categories category;
private Integer sales;

@ManyToOne
private Brand brand;

    @ManyToOne
    private Review review;
    protected Shoe() {
        super(DomainObjectId.randomId(ShoeId.class));
    }

    public static Shoe build(String seriesName, Money price, Integer sales,Gender gender,Categories category,Brand brand, Review review) {
        Shoe s = new Shoe();
        s.price = price;
        s.seriesName = seriesName;
        s.sales = sales;
        s.gender =gender;
        s.category=category;
        s.brand = brand;
        s.review= review;
        return s;
    }

    public void addSales(Integer qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(Integer qty) {
        this.sales -= qty;
    }



}
