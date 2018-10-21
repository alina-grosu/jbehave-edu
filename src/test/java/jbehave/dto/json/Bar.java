package jbehave.dto.json;

import lombok.*;
import org.jbehave.core.annotations.AsJson;

import java.math.BigDecimal;

@AsJson
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Bar {

    private String omg;
    private int number;
    private BigDecimal bigdex;

}
