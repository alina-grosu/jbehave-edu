package jbehave.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jbehave.core.annotations.AsJson;

import java.util.List;
@AsJson
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Feq {
    private Bar bar;
    private Foo foo;
    private List<String> strings;
}
