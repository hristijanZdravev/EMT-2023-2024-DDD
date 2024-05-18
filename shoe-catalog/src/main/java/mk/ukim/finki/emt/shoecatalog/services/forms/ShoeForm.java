package mk.ukim.finki.emt.shoecatalog.services.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.shoecatalog.domain.models.Brand;
import mk.ukim.finki.emt.shoecatalog.domain.models.Review;
import mk.ukim.finki.emt.shoecatalog.domain.models.enums.Categories;
import mk.ukim.finki.emt.shoecatalog.domain.models.enums.Gender;

import java.util.List;

@Data
public class ShoeForm {

    private String productName;
    private Money price;
    private Integer sales;

    private Gender gender;

    private Categories category;
    private Brand brand;
}
