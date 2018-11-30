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

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class CartesianCoordinateTest {
	
	private CartesianCoordinate cartesianA;
	private CartesianCoordinate cartesianB;
	private SphericCoordinate sphericA;
	private SphericCoordinate sphericB;
	
	@Before
	public void setUp() {
		cartesianA = new CartesianCoordinate(-1.0, 1.0, -Math.sqrt(2));
		cartesianB = new CartesianCoordinate(-1.0, 1.0, 1.0);
		sphericA = new SphericCoordinate(135.0, 135.0, 2.0);
		sphericB = new SphericCoordinate(54.735610317245, 135.0, 1.7320508075689);
	}

	@Test
	public void testAsSphericCoordinateA() {
		SphericCoordinate converted = cartesianA.asSphericCoordinate();
		assertEquals(converted.getPolar(), sphericA.getPolar(), Coordinate.EPSILON);
		assertEquals(converted.getAzimut(), sphericA.getAzimut(), Coordinate.EPSILON);
		assertEquals(converted.getRadius(), sphericA.getRadius(), Coordinate.EPSILON);
	}
	
	@Test
	public void testAsSphericCoordinateB() {
		SphericCoordinate converted = cartesianB.asSphericCoordinate();
		assertEquals(converted.getPolar(), sphericB.getPolar(), Coordinate.EPSILON);
		assertEquals(converted.getAzimut(), sphericB.getAzimut(), Coordinate.EPSILON);
		assertEquals(converted.getRadius(), sphericB.getRadius(), Coordinate.EPSILON);
	}
	
	@Test
	public void testAsCartesianCoordinateA() {
		CartesianCoordinate converted = cartesianA.asCartesianCoordinate();
		assertEquals(converted.getX(), cartesianA.getX(), Coordinate.EPSILON);
		assertEquals(converted.getY(), cartesianA.getY(), Coordinate.EPSILON);
		assertEquals(converted.getZ(), cartesianA.getZ(), Coordinate.EPSILON);
	}

	@Test
	public void testAsCartesianCoordinateB() {
		CartesianCoordinate converted = cartesianB.asCartesianCoordinate();
		assertEquals(converted.getX(), cartesianB.getX(), Coordinate.EPSILON);
		assertEquals(converted.getY(), cartesianB.getY(), Coordinate.EPSILON);
		assertEquals(converted.getZ(), cartesianB.getZ(), Coordinate.EPSILON);
	}
	
	@Test(expected = AssertionError.class)
	public void testInvalidX() {
		@SuppressWarnings("unused")
		CartesianCoordinate tmp = new CartesianCoordinate(Double.NaN, 0, 0);
	}
	
	@Test(expected = AssertionError.class)
	public void testInvalidY() {
		@SuppressWarnings("unused")
		CartesianCoordinate	tmp = new CartesianCoordinate(0, Double.NEGATIVE_INFINITY, 0);
	}
	
	@Test(expected = AssertionError.class)
	public void testInvalidZ() {
		@SuppressWarnings("unused")
		CartesianCoordinate tmp = new CartesianCoordinate(0, 0, Double.POSITIVE_INFINITY);
	}
	
}