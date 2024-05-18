package mk.ukim.finki.mk.ordermanagement.services.forms;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.enums.Currency;

import javax.validation.Valid;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();

}
