/*
 * Copyright (c) 2018 by Benjamin Fischer
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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Component test for class: Coordinate.
 */
public class CoordinateTest {

	protected static final double EPSILON = 10e-9;
	
	protected Coordinate coordinateA;
	protected Coordinate coordinateB;
	protected Coordinate coordinateC;
	protected Coordinate coordinateD;
	protected Coordinate coordinateE;
	
	@Before
	public void setUp() {
		coordinateA = new Coordinate(1.0, 2.0, 3.0);
		coordinateB = new Coordinate(-3.0, -2.0, -1.0);
		coordinateC = new Coordinate(1.0, 2.0, 3.0);
		coordinateD = new Coordinate(0.9, 0.01, 100.0);
	}

	@Test
	public void testDistance() {
		assertEquals(0.0, coordinateA.getDistance(coordinateA), EPSILON);
		assertEquals(Math.sqrt(48.0), coordinateB.getDistance(coordinateA), EPSILON);
		assertEquals(0.0, coordinateC.getDistance(coordinateA), EPSILON);
		assertEquals(Math.sqrt(9412.9701), coordinateD.getDistance(coordinateA), EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDistanceNull() {
		coordinateA.getDistance(null);
	}
	
	@Test
	public void testEquals() {
		assertFalse(coordinateB.equals(null));
		assertTrue(coordinateC.equals(coordinateA));
		assertFalse(coordinateD.equals(coordinateC));
		assertTrue(coordinateD.equals(coordinateD));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEqualNull() {
		coordinateA.isEqual(null);
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(coordinateB.isEqual(coordinateB));
		assertFalse(coordinateA.isEqual(coordinateB));
		assertFalse(coordinateC.isEqual(coordinateD));
		assertFalse(coordinateD.isEqual(coordinateA));
	}
	
}
