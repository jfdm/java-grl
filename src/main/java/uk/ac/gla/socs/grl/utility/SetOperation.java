package uk.ac.gla.socs.grl.utility;

import java.util.Set;
import java.util.HashSet;

public class SetOperation {

    public static <T> Set<T> hashSetFromArray(T[] ts) {
        Set<T> result = new HashSet<T>();
        for (T t : ts) {
            result.add(t);
        }
        return result;
    }
}
