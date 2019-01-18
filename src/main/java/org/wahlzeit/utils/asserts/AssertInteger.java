package org.wahlzeit.utils.asserts;

public class AssertInteger {

    /**
     * Assert that an integer is positive.
     *
     * @param x     value to check
     * @param name   name of the value which is checked
     * @methodtype  assert
     */
    public static void assertIntegerIsPositive(int x, String name) {
        if (x <= 0) {
            throw new IllegalArgumentException("Integer " + name + " must be positive");
        }
    }
}
