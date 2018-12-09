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
import org.wahlzeit.model.exceptions.InvalidCoordinateCalculationException;
import org.wahlzeit.model.exceptions.InvalidCoordinateStateException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Component test for class: Coordinate.
 */
public class AbstractCoordinateTest {
	
	private CartesianCoordinate cartesianA;
	private CartesianCoordinate cartesianB;
	private SphericCoordinate sphericA;
	private SphericCoordinate sphericB;
	private SphericCoordinate sphericC;
	
	@Before
	public void setUp() {
		cartesianA = new CartesianCoordinate(1.0, 1.0, 1.0);
		cartesianB = new CartesianCoordinate(-1.0, 0, -1.0);
		sphericA = new SphericCoordinate(90.0, 90.0, 2.0);		// new CartesianCoordinate(0, 2, 0);
		sphericB = new SphericCoordinate(45.0, 45.0, 10.0);		// new CartesianCoordinate(5, 5, 7.0710678118655);
		sphericC = new SphericCoordinate(45, 30, 2);
	}

	@Test(expected = NullPointerException.class)
	public void testGetCartesianDistanceNull() 
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException 
	{
		cartesianA.getCartesianDistance(null);
	}
	
	@Test
	public void testGetCartesianDistanceBothCartesian() 
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException 
	{
		assertEquals(3, cartesianA.getCartesianDistance(cartesianB), Coordinate.EPSILON);
		assertEquals(3, cartesianB.getCartesianDistance(cartesianA), Coordinate.EPSILON);
		assertEquals(0, cartesianB.getCartesianDistance(cartesianB), Coordinate.EPSILON);
		assertEquals(0, cartesianA.getCartesianDistance(cartesianA), Coordinate.EPSILON);
	}
	
	@Test
	public void testGetCartesianDistanceBothSpheric() 
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException 
	{
		assertEquals(Math.sqrt(84), sphericA.getCartesianDistance(sphericB), Coordinate.EPSILON);
		assertEquals(Math.sqrt(84), sphericB.getCartesianDistance(sphericA), Coordinate.EPSILON);
		assertEquals(0, sphericB.getCartesianDistance(sphericB), Coordinate.EPSILON);
		assertEquals(0, sphericA.getCartesianDistance(sphericA), Coordinate.EPSILON);
	}
	
	@Test
	public void testGetCartesianDistance() 
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException 
	{
		assertEquals(Math.sqrt(3), cartesianA.getCartesianDistance(sphericA), Coordinate.EPSILON);
		assertEquals(Math.sqrt(126.14213562373), cartesianB.getCartesianDistance(sphericB), Coordinate.EPSILON);
		assertEquals(Math.sqrt(6), cartesianB.getCartesianDistance(sphericA), Coordinate.EPSILON);
		assertEquals(Math.sqrt(68.857864376269), cartesianA.getCartesianDistance(sphericB), Coordinate.EPSILON);
	}
	
	@Test(expected = InvalidCoordinateCalculationException.class)
	public void testGetCentralAngleDiffferentSphere() 
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException {
		cartesianA.getCentralAngle(cartesianB);
	}
	
	@Test(expected = InvalidCoordinateCalculationException.class)
	public void testGetCentralAngleDiffferentSphere2()
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException 
	{
		sphericA.getCentralAngle(sphericB);
	}
	
	@Test(expected = InvalidCoordinateCalculationException.class)
	public void testGetCentralAngleDiffferentSphere3()
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException 
	{
		cartesianA.getCentralAngle(sphericB);
	}
	
	@Test(expected = InvalidCoordinateCalculationException.class)
	public void testGetCentralAngleDiffferentSphere4()
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException
	{
		sphericA.getCentralAngle(cartesianB);
	}
	
	@Test
	public void testGetCentralAngle() 
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException
	{
		assertEquals(0.7190700035, sphericA.getCentralAngle(sphericC), Coordinate.EPSILON);
	}
	
	@Test(expected = NullPointerException.class)
	public void testIsEqualNull() throws InvalidCoordinateStateException {
		cartesianA.isEqual(null);
	}
	
	@Test
	public void testIsEqual() throws InvalidCoordinateStateException {
		assertTrue(cartesianA.isEqual(cartesianA));
		assertFalse(cartesianA.isEqual(cartesianB));
		assertFalse(sphericB.isEqual(sphericA));
		assertTrue(sphericA.isEqual(new CartesianCoordinate(0, 2, 0)));
		assertTrue(sphericB.isEqual(new CartesianCoordinate(5, 5, 7.0710678118655)));
	}
	
}