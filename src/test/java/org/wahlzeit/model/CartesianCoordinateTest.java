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

public class CartesianCoordinateTest {
	
	private static final double EPSILON = 10E-9;
	
	private CartesianCoordinate cartesianA;
	private CartesianCoordinate cartesianB;
	private SphericCoordinate sphericA;

	
	@Before
	public void setUp() {
		cartesianA = new CartesianCoordinate(-1.0, 1.0, -Math.sqrt(2));
		cartesianB = cartesianA.asCartesianCoordinate();
		sphericA = new SphericCoordinate(Math.toDegrees(0.75*Math.PI), Math.toDegrees(0.75*Math.PI), 2);
	}

	@Test
	public void testAsCartesianCoordinate() {
		assertFalse(cartesianA == cartesianB);
	}
	
	@Test
	public void testAsCartesianCoordinateValues() {
		assertEquals(cartesianA.getX(), cartesianB.getX(), EPSILON);
		assertEquals(cartesianA.getY(), cartesianB.getY(), EPSILON);
		assertEquals(cartesianA.getZ(), cartesianB.getZ(), EPSILON);
	}
	
	@Test
	public void testAsSphericCoordinate() {
		SphericCoordinate testA = cartesianA.asSphericCoordinate();
		assertEquals(sphericA.getAzimut(), testA.getAzimut());
		assertEquals(sphericA.getPolar(), testA.getPolar());
		assertEquals(sphericA.getRadius(), testA.getRadius(), EPSILON);
	}
	
}
