package jbehave.dto.json;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jbehave.core.annotations.AsJson;

@AsJson
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Foo {
    private String baz;
    private String beb;
    private String foq;


    public String getBaz() {
        return baz;
    }

    public void setBaz(String baz) {
        this.baz = baz;
    }

    public String getBeb() {
        return beb;
    }

    public void setBeb(String beb) {
        this.beb = beb;
    }

    public String getFoq() {
        return foq;
    }

    public void setFoq(String foq) {
        this.foq = foq;
    }
}
