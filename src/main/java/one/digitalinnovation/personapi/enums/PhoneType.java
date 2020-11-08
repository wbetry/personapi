package one.digitalinnovation.personapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PhoneType {
    
    HOME(1, "Home"),
    MOBILE(2, "Mobile"),
    COMMERCIAL(3, "Commercial");

    private final Integer type;
    private final String description;

}
