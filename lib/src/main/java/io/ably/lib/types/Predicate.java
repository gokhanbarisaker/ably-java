package io.ably.lib.types;

/**
 * <p>Created by gokhanbarisaker on 1/12/16.</p>
 *
 * <p>Represents a predicate (boolean-valued function) of one argument.</p>
 *
 * <p>Simplified backport version of Java 8 Predicate functional interface.</p>
 *
 * @param <T> the type of the input to the predicate
 */
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);
}
