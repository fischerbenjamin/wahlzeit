package org.wahlzeit.utils.asserts;

public class AssertObject {

    /**
     * Assert that an object is not null.
     *
     * @param obj    object to check
     * @param name   name of the object which is checked
     * @methodtype  assert
     */
    public static void assertNotNull(Object obj, String name) {
        if (obj == null) {
            throw new IllegalArgumentException("Object " + name + " must not be null.");
        }
    }

}
