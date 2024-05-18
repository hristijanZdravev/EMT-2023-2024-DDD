package mk.ukim.finki.mk.ordermanagement.services.forms;

import lombok.Data;
import mk.ukim.finki.mk.ordermanagement.domain.valueobjects.Quantity;
import mk.ukim.finki.mk.ordermanagement.domain.valueobjects.Shoe;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class OrderItemForm {

    @NotNull
    private Shoe product;

    @Min(1)
    private Quantity quantity = new Quantity();

}
