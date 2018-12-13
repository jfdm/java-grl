package uk.ac.gla.socs.grl.utility;

import java.util.*;

public final class FromMaybe {

    public static <T> Maybe<T> optional(Optional<T> v) {
        try {
            return new Just<>(v.orElseThrow());
        } catch (NoSuchElementException e) {
            return new Nothing<>();
        }
    }
}
