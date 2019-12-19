package pl.mzlnk.bitjava.reflection.json.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Json {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Include { }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Exclude { }

}
