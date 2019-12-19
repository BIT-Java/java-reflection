package pl.mzlnk.bitjava.reflection;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
public class Address {

    private String street;
    private int streetNumber;
    private String city;

    public Address(String street, Integer streetNumber, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
    }

}
