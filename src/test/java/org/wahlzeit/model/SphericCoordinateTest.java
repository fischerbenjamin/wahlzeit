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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	
	private static final double DEFAULT = 42.0;
	private static final double EPSILON = 10E-9;
	
	private SphericCoordinate sphericCoordinateA;
	private SphericCoordinate sphericCoordinateD;
	private CartesianCoordinate cartesianA;
	
	@Before
	public void setUp() {
		sphericCoordinateA = new SphericCoordinate(Math.toDegrees(0.75*Math.PI), Math.toDegrees(0.75*Math.PI), 2);
		sphericCoordinateD = sphericCoordinateA.asSphericCoordinate();
		cartesianA = new CartesianCoordinate(-1.0, 1.0, -Math.sqrt(2));
	}

	@Test
	public void testAsSphericCoordinate() {
		assertFalse(sphericCoordinateD == sphericCoordinateA);
	}
	
	@Test
	public void testAsSphericCoordinateValues() {
		assertEquals(sphericCoordinateA.getPolar(), sphericCoordinateD.getPolar());
		assertEquals(sphericCoordinateA.getAzimut(), sphericCoordinateD.getAzimut());
		assertEquals(sphericCoordinateA.getRadius(), sphericCoordinateD.getRadius(), EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPolarAngleNegative() {
		SphericCoordinate tmp = new SphericCoordinate(-1.0, DEFAULT, DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPolarAnglePostive() {
		SphericCoordinate tmp = new SphericCoordinate(181.0, DEFAULT, DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAzimutAnglePostive() {
		SphericCoordinate tmp = new SphericCoordinate(DEFAULT, 361.0, DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAzimutAngleNegative() {
		SphericCoordinate tmp = new SphericCoordinate(DEFAULT, -1.0, DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRadiusNegative() {
		SphericCoordinate tmp = new SphericCoordinate(DEFAULT, DEFAULT, -1.0);
	}
	
	@Test
	public void testAsCartesianCoordinate() {
		CartesianCoordinate testA = sphericCoordinateA.asCartesianCoordinate();
		assertEquals(testA.getX(), cartesianA.getX(), EPSILON);
		assertEquals(testA.getY(), cartesianA.getY(), EPSILON);
		assertEquals(testA.getZ(), cartesianA.getZ(), EPSILON);
	}
	
}
