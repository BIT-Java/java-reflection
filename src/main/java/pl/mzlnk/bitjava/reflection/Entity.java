package pl.mzlnk.bitjava.reflection;

import lombok.AllArgsConstructor;
import pl.mzlnk.bitjava.reflection.json.annotations.Json;
import pl.mzlnk.bitjava.reflection.json.annotations.JsonStrategy;

import static pl.mzlnk.bitjava.reflection.json.annotations.JsonStrategy.JsonStrategyEnum.EXPLICITLY_EXCLUDE;

@AllArgsConstructor
@JsonStrategy(strategy = EXPLICITLY_EXCLUDE)
public class Entity {

    @Json.Include
    private String name;
    private String surname;
    private int age;
    @Json.Include
    private Address address;
    @Json.Exclude
    private double ratio;


}
