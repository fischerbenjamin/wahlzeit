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

/**
 * Component test for class {@link Train}
 */
public class TrainTest {

    private Train train;
    private Train train2;
    private Train train3;
    
    @Before
    public void setUp() {
        train = new Train();
        train2 = new Train();
        train3 = new Train(TrainType.DEFAULT, "Diesel", 1997, "5627HAVJD62");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeNull() {
        train.setType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullType() {
        train = new Train(null, Defaults.STRING, Defaults.POSITIVE_INTEGER, Defaults.STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEngine() {
        train = new Train(TrainType.DEFAULT, null, Defaults.POSITIVE_INTEGER, Defaults.STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyEngine() {
        train = new Train(TrainType.DEFAULT, Defaults.EMPTY_STRING, Defaults.POSITIVE_INTEGER, Defaults.STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBuildYear() {
        train = new Train(TrainType.DEFAULT, Defaults.STRING, Defaults.NEGATIVE_INTEGER, Defaults.STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSerialNumber() {
        train = new Train(TrainType.DEFAULT, Defaults.STRING , Defaults.POSITIVE_INTEGER, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySerialNumber() {
        train = new Train(TrainType.DEFAULT, Defaults.EMPTY_STRING, Defaults.POSITIVE_INTEGER, Defaults.EMPTY_STRING);
    }

    @Test
    public void testHashCode() {
        assertEquals(train.hashCode(), train2.hashCode());
        assertNotEquals(train.hashCode(), train3.hashCode());
        assertNotEquals(train2.hashCode(), train3.hashCode());
    }

}