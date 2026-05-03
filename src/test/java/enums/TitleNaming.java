package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TitleNaming {
    PRODUCTS("Productss"),
    CART("Your Cart"),
    CHECKOUT("Checkout: Your Information");

    private final String displayName;
}
