package io.ably.lib.util;

import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import io.ably.lib.types.Predicate;

import java.util.ArrayList;

/**
 * Created by gokhanbarisaker on 1/12/16.
 */
public class Validator<T> {
    private final T t;
    private final ArrayList<Throwable> throwables = new ArrayList<>();

    public static <T> Validator<T> of(T t) {
        return new Validator<>(t);
    }

    private Validator(T t) {
        this.t = t;
    }

    public Validator<T> validate(Predicate<T> validation, String message) {
        if (!validation.test(t)) {
            throwables.add(new IllegalStateException(message));
        }

        return this;
    }

    /**
     *
     * @return
     * @throws AblyException
     */
    public T get() throws AblyException {
        if (throwables.isEmpty()) {
            return t;
        }

        AblyException e = new AblyException(new ErrorInfo("Failed validation", 0, 0));

        for (Throwable t : throwables) {
            e.addSuppressed(t);
        }

        throw e;
    }
}
