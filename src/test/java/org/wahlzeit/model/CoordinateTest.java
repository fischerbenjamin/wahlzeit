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

	private static final double EPSILON = 10E-7;
	private static final double DEFAULT = 42.0;
	
	private CartesianCoordinate cartesianA;
	private CartesianCoordinate cartesianB;
	private CartesianCoordinate cartesianC;
	
	private Coordinate coord;
	
	private SphericCoordinate sphericA;
	private SphericCoordinate sphericB;
	private SphericCoordinate sphericC;
	private SphericCoordinate sphericD;
	
	@Before
	public void setUp() {
		cartesianA = new CartesianCoordinate(1.0, 1.0, 0.0);
		cartesianB = new CartesianCoordinate(-1.0, 1.0, 1.0);
		cartesianC = new CartesianCoordinate(DEFAULT, DEFAULT, DEFAULT);
		sphericA = new SphericCoordinate(90.0, 90.0, 2.0);
		sphericB = new SphericCoordinate(45.0, 45.0, 10.0);
		sphericC = new SphericCoordinate(30.0, 60.0, 2.0);
		sphericD = new SphericCoordinate(90.0, 45.0, Math.sqrt(2.0));
		coord = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckDoubleNaN() {
		Coordinate tmp = new CartesianCoordinate(Double.NaN, DEFAULT, DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckDoublePositiveInfinity() {
		Coordinate tmp = new CartesianCoordinate(Double.POSITIVE_INFINITY, DEFAULT, DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckDoubleNegativeInfinity() {
		Coordinate tmp = new CartesianCoordinate(Double.NEGATIVE_INFINITY, DEFAULT, DEFAULT);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetCartesianDistanceNull() {
		cartesianA.getCartesianDistance(coord);
	}
	
	@Test
	public void testGetCartesianDistanceBothCartesian() {
		assertEquals(Math.sqrt(5.0), cartesianA.getCartesianDistance(cartesianB), EPSILON);
	}
	
	@Test
	public void testGetCartesianDistanceOneCartesianOneSpheric() {
		assertEquals(Math.sqrt(2.0), cartesianA.getCartesianDistance(sphericA), EPSILON);
	}
	
	@Test
	public void testGetCartesianBothSpheric() {
		assertEquals(9.165151, sphericA.getCartesianDistance(sphericB), EPSILON);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetCentralAngleNull() {
		cartesianA.getCentralAngle(coord);
	}

	@Test
	public void testGetCentralAngleSameSphere() {
		assertEquals(Math.toRadians(64.34109316), sphericA.getCentralAngle(sphericC), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCentralAngleOtherSphere() {
		sphericA.getCentralAngle(cartesianC);
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(cartesianA.isEqual(cartesianA));
		assertFalse(cartesianA.isEqual(cartesianB));
		assertTrue(cartesianA.isEqual(sphericD));
	}
}
