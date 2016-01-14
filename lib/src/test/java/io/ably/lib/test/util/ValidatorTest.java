package io.ably.lib.test.util;

import io.ably.lib.types.AblyException;
import io.ably.lib.types.Predicate;
import io.ably.lib.util.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by gokhanbarisaker on 1/12/16.
 */
public class ValidatorTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private Predicate<String> nonEmptyPredicate;

    @Before
    public void setup() {
        nonEmptyPredicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s != null && s.trim().length() > 0;
            }
        };
    }

    @Test
    public void validate_single_success() throws AblyException {
        String validatee = "the thing";

        String validated = Validator.of(validatee)
                .validate(nonEmptyPredicate, "validatee is null")
                .get();

        Assert.assertEquals("", validated, validatee);
    }

    @Test
    public void validate_single_fail() throws AblyException {
        String validatee = null;

        thrown.expect(AblyException.class);
        Validator.of(validatee)
                .validate(nonEmptyPredicate, "validatee is null")
                .get();
    }
}
