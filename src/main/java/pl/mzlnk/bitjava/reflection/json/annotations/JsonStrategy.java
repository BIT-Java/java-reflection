package pl.mzlnk.bitjava.reflection.json.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonStrategy {

    JsonStrategyEnum strategy();

    enum JsonStrategyEnum {

        EXPLICITLY_INCLUDE,
        EXPLICITLY_EXCLUDE;

    }
}
