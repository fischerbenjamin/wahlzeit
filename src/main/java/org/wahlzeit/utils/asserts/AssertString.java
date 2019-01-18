package org.wahlzeit.utils.asserts;

public class AssertString {

    /**
     * Assert that a string is not null nor empty.
     *
     * @param s     value to check
     * @param name   name of the value which is checked
     * @methodtype  assert
     */
    public static void assertStringNotNullorEmpty(String s, String name) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("String " + name + " must not be null or empty.");
        }
    }


}
