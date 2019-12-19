package pl.mzlnk.bitjava.reflection;

import pl.mzlnk.bitjava.reflection.json.JsonCreator;

import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) throws Exception {

        Entity entity = new Entity("John", "Smiths", 23, new Address("10th Street", 1730, "New York"), 0.5);
        String json = new JsonCreator(entity).toJson();

        //System.out.println(json);

        Class<?> clazz = Class.forName("pl.mzlnk.bitjava.reflection.Address");
        Constructor<?> cnst = clazz.getConstructor(String.class, Integer.class, String.class);
        Address address = (Address) cnst.newInstance(new Object[] {"a", 1, "b"});

        System.out.println(address.toString());

    }

}
