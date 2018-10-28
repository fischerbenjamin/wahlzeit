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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Component test for class: Location.
 */
public class LocationTest {

	protected Location locationA;
	protected Location locationB;
	protected Location locationC;
	protected Coordinate coordinateA;
	protected Coordinate coordinateB;
	protected Coordinate coordinateC;
	
	@Before
	public void setUp() {
		coordinateA = new Coordinate(1.0, 2.0, 3.0);
		coordinateB = new Coordinate(-3.0, -2.0, -1.0);
		locationA = new Location(coordinateA);
		locationB = new Location(coordinateB);
		coordinateC = new Coordinate(-3.0000000001, -2.0, -1.0);
		locationC = new Location(coordinateC);
	}

	@Test
	public void testLocation() {
		assertFalse(locationA.getCoordinate().equals(locationB.getCoordinate()));
		assertTrue(locationA.getCoordinate().equals(coordinateA));
		assertFalse(locationA.getCoordinate().equals(null));
		assertTrue(locationB.equals(locationC));
	}
	
}
