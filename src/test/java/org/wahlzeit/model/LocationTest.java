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
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Component test for class {@link Location}.
 */
public class LocationTest {

	private Location locationA;
	private Location locationB;
	private Location locationC;
	
	@SuppressWarnings("unused")
	private Location tmp;
	
	@Before
	public void setUp() {
		locationA = new Location(CartesianCoordinate.getCartesianCoordinate(1.0, 2.0, 3.0), "locA");
		locationB = new Location(CartesianCoordinate.getCartesianCoordinate(-3.0, -2.0, -1.0), "locB");
		locationC = new Location(CartesianCoordinate.getCartesianCoordinate(-3.0-Coordinate.EPSILON*10E-2, -2.0, -1.0), "locB");
	}

	@Test
	public void testValidLocation() {
		assertFalse(locationA.equals(locationB));
		assertTrue(locationA.equals(locationA));
		assertTrue(locationB.equals(locationC));
	}
	
	@Test(expected = NullPointerException.class)
	public void testInvalidCoordinate() {
		tmp = new Location(null, "foo");
	}
	
	@Test(expected = AssertionError.class)
	public void testInvalidName() {
		tmp = new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 0), null);
	}
	
}
