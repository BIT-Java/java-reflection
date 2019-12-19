package pl.mzlnk.bitjava.reflection.json;

import pl.mzlnk.bitjava.reflection.json.annotations.Json;
import pl.mzlnk.bitjava.reflection.json.annotations.JsonStrategy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

import static pl.mzlnk.bitjava.reflection.json.annotations.JsonStrategy.JsonStrategyEnum.EXPLICITLY_EXCLUDE;
import static pl.mzlnk.bitjava.reflection.json.annotations.JsonStrategy.JsonStrategyEnum.EXPLICITLY_INCLUDE;

public class JsonCreator {

    private static final String TAB = "\t";
    private static final String NEW_LINE = "\n";
    private static final List<String> baseTypes = new ArrayList<>();

    static {
        baseTypes.add("java.lang.Integer");
        baseTypes.add("java.lang.String");
        baseTypes.add("java.lang.Boolean");
        baseTypes.add("java.lang.Byte");
        baseTypes.add("java.lang.Long");
        baseTypes.add("java.lang.Float");
        baseTypes.add("java.lang.Double");
    }

    private Object object;
    private List<Field> fields;

    private Class<?> clazz;
    private JsonStrategy.JsonStrategyEnum strategy;


    public JsonCreator(Object object) {
        this.object = object;
        this.clazz = object.getClass();
        this.fields = Arrays.asList(this.clazz.getDeclaredFields());
        this.strategy = Optional.ofNullable(this.clazz.getAnnotation(JsonStrategy.class)).map(JsonStrategy::strategy).orElse(EXPLICITLY_EXCLUDE);
    }

    public String toJson() {
        return this.toJson(0);
    }

    private String toJson(int tabs) {
        StringBuilder stringBuilder = new StringBuilder();

        if (fields.size() == 0 || baseTypes.contains(clazz.getName())) {
            return this.objectValue();
        }

        stringBuilder.append(NEW_LINE);

        for(Field f : fields) {
            if(!canBeIncluded(f)) {
                continue;
            }

            stringBuilder
                    .append(tabs(tabs))
                    .append("\"")
                    .append(f.getName())
                    .append("\"")
                    .append(": ");

            try {
                f.setAccessible(true);
                stringBuilder
                        .append(new JsonCreator(f.get(this.object)).toJson(tabs + 1));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    private String objectValue() {
        if (this.object instanceof String) {
            return "\"" + this.object + "\"";
        }
        return String.valueOf(this.object);
    }

    private boolean canBeIncluded(Field f) {
        if(strategy == EXPLICITLY_EXCLUDE) {
            return f.getAnnotation(Json.Exclude.class) == null;
        }
        if(strategy == EXPLICITLY_INCLUDE) {
            return f.getAnnotation(Json.Include.class) != null;
        }
        return true;
    }

    private static String tabs(int tabs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabs; i++) {
            sb.append(TAB);
        }
        return sb.toString();
    }

}
