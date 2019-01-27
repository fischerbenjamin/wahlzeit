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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Component test for class {@link TrainManager}
 */
public class TrainManagerTest {

    private Train t1;
    private Train t2;
    private TrainType tt1;
    private TrainType tt2;
    private TrainType tt3;


    @Before
    public void setUp() {
        t1 = TrainManager.getInstance().createTrain();
        t2 = new Train(TrainType.DEFAULT, "Diesel", 1997, "5627HAVJD62");
        tt1 = new TrainType(Defaults.STRING, Defaults.STRING, Defaults.POSITIVE_INTEGER, true);
        tt2 = new TrainType(Defaults.STRING, Defaults.STRING, 2, true);
        tt3 = new TrainType(Defaults.STRING, Defaults.STRING, 2, true);
    }

    @Test
    public void testGetInstance() {
        assertNotNull(TrainManager.getInstance());
    }

    @Test
    public void testGetTrainType() {
        TrainType type1 = TrainManager.getInstance().getTrainType(Defaults.STRING, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, true);
        TrainType type2 = TrainManager.getInstance().getTrainType(Defaults.STRING, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, true);
        TrainType type3 = TrainManager.getInstance().getTrainType(Defaults.STRING, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, false);
        assertEquals(type1, type2);
        assertNotEquals(type1, type3);
    }

    @Test
    public void testGetTrain() {
        Train train1 = TrainManager.getInstance().getTrain(tt1, Defaults.STRING, Defaults.POSITIVE_INTEGER,
                Defaults.STRING);
        assertNull(train1);
        train1 = TrainManager.getInstance().createTrain(Defaults.STRING, Defaults.STRING, Defaults.POSITIVE_INTEGER,
                true, Defaults.STRING, Defaults.POSITIVE_INTEGER, Defaults.STRING);
        Train train2 = TrainManager.getInstance().getTrain(tt1, Defaults.STRING, Defaults.POSITIVE_INTEGER,
                Defaults.STRING);
        assertNotNull(train2);
    }

    @Test
    public void testCreateTrain() {
        Train train = TrainManager.getInstance().createTrain(Defaults.STRING, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, true, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, Defaults.STRING);
        assertNotNull(train);
        assertEquals(TrainManager.getInstance().getTrain(tt1, Defaults.STRING, Defaults.POSITIVE_INTEGER,
                Defaults.STRING), train);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTrain2() {
        Train train = TrainManager.getInstance().createTrain(Defaults.STRING, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, true, Defaults.STRING,
                Defaults.POSITIVE_INTEGER, null);
    }

}