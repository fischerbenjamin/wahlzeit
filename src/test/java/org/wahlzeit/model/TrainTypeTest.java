/*
 * Copyright (c) 2019 by Benjamin Fischer
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.wahlzeit.utils.Defaults;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Component test for class {@link TrainType}
 */
public class TrainTypeTest {

    private Train train;
    private TrainType t1;
    private TrainType t2;
    private TrainType t3;
    private TrainType tmp;
    
    @Before
    public void setUp() {
        t1 = new TrainType(Defaults.STRING, Defaults.STRING, 1, true);
        t2 = new TrainType(Defaults.STRING, Defaults.STRING, 2, true);
        t3 = new TrainType(Defaults.STRING, Defaults.STRING, Defaults.POSITIVE_INTEGER, false);
        train = new Train(t1, Defaults.STRING, Defaults.POSITIVE_INTEGER, Defaults.STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullBrand() {
        tmp = new TrainType(null, Defaults.STRING, Defaults.POSITIVE_INTEGER, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyBrand() {
        tmp = new TrainType(Defaults.EMPTY_STRING, Defaults.STRING, Defaults.POSITIVE_INTEGER, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullModel() {
        tmp = new TrainType(Defaults.STRING, null, Defaults.POSITIVE_INTEGER, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyModel() {
        tmp = new TrainType(Defaults.STRING, Defaults.EMPTY_STRING, Defaults.POSITIVE_INTEGER, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSpeed() {
        tmp = new TrainType(Defaults.STRING, Defaults.STRING, Defaults.NEGATIVE_INTEGER, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSuperType() {
        t1.setSuperType(null);
    }

    @Test
    public void testHashCode() {
        assertEquals(t1.hashCode(), t2.hashCode());
        assertNotEquals(t1.hashCode(), t3.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAddSubtype() {
        t1.addSubType(null);
    }

    @Test
    public void testAddSubtype() {
        t1.addSubType(t2);
        assertEquals(t2.getSuperType(), t1);
    }

    @Test
    public void testIsSubtype() {
        t1.addSubType(t2);
        assertTrue(t2.isSubtype(t1));
    }

    @Test
    public void testIsSubtype2() {
        t1.addSubType(t2);
        t2.addSubType(t3);
        assertTrue(t3.isSubtype(t1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullIsSubtype() {
        t1.addSubType(null);
    }

    @Test
    public void testHasInstance() {
        assertTrue(t1.hasInstance(train));
        assertFalse(t2.hasInstance(train));
        t3.addSubType(t2);
        t2.addSubType(t1);
        assertTrue(t3.hasInstance(train));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullHasInstance() {
        t1.hasInstance(null);
    }

}