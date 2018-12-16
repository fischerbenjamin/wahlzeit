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

import java.util.HashMap;
import java.util.Objects;

/**
 * A class representing Cartesian coordinates. A Cartesian coordinate consists
 * of three values:
 * 	{@link #x}:	x-dimension	([Double.MIN_VALUE;Double.MAX_VALUE])
 *	{@link #y}:	y-dimension	([Double.MIN_VALUE;Double.MAX_VALUE])
 *	{@link #z}:	z-dimension	([Double.MIN_VALUE;Double.MAX_VALUE])
 * All fields are declared as private and final.
 */
public class CartesianCoordinate extends AbstractCoordinate {
	
	/**
	 * Stores the values of type {@link SphericCoordinate}.
	 */
	private static HashMap<Integer, CartesianCoordinate> coordinates = new HashMap<>();
	
	private final double x;		// x-dimension
	private final double y;		// y-dimension
	private final double z;		// z-dimension
	
	/**
	 * Create a new Cartesian coordinate with given values.
	 * 
	 * @param x	x dimension (must be a finite double value)
	 * @param y	y dimension (must be a finite double value)
	 * @param z	z dimension (must be a finite double value)
	 * @return new Cartesian coordinate
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * This method is used to maintain the {@link CartesianCoordinate} values.
	 * If the value was already created, it simply returns its reference.
	 * Otherwise, a new value is created and is added to the map of known values.
	 *
	 * @param x	x-dimension
	 * @param y y-dimension
	 * @param z z-dimension
	 * @return	instance of {@link CartesianCoordinate} used as a value
	 */
	public static synchronized CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		//@PRE
		AbstractCoordinate.assertDoubleIsFinite(x);
		AbstractCoordinate.assertDoubleIsFinite(y);
		AbstractCoordinate.assertDoubleIsFinite(z);
		//@PRE
		int hash = doHashCode(x, y, z);
		if (coordinates.containsKey(hash)) {
			return coordinates.get(hash);
		}
		CartesianCoordinate coordinate = new CartesianCoordinate(x, y, z);
		coordinates.put(coordinate.hashCode(), coordinate);
		return coordinate;
	}
	
	/**
	 * Returns dimension {@link x} of a Cartesian coordinate.
	 * 
	 * @return x-dimension
	 * @methodtype get
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Returns dimension {@link y} of a Cartesian coordinate.
	 * 
	 * @return y-dimension
	 * @methodtype get
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns dimension {@link z} of a Cartesian coordinate.
	 * 
	 * @return z-dimension
	 * @methodtype get
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * The conversion of a Cartesian coordinate into a Cartesian
	 * coordinate is the instance itself.
	 * 
	 * @return the coordinate itself
	 * @throws IllegalStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	@Override
	protected CartesianCoordinate doAsCartesianCoordinate() throws IllegalStateException {
		//@PRE
		this.assertClassInvariants();
		//@PRE
		return this;
	}	
	
	/**
	 * Converts the Cartesian coordinate into a spheric coordinate and
	 * returns a new instance of {@link SphericCoordinate}.
	 * 
	 * @throws IllegalStateException	if class invariants are violated
	 * @return the Cartesian coordinate as a spheric coordinate
	 * @throws InvalidCoordinateCalculationException 
	 * @methodtype conversion
	 */
	@Override
	protected SphericCoordinate doAsSphericCoordinate() throws IllegalStateException{
		//@PRE
		this.assertClassInvariants();
		//@PRE
		double radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
		double azimut = Math.toDegrees(Math.atan2(this.getY(), this.getX()));
		double polar = Math.toDegrees(Math.acos(this.getZ()/radius));
		SphericCoordinate converted = SphericCoordinate.getSphericCoordinate(polar, azimut, radius);
		//@POST
		converted.assertClassInvariants();
		this.assertClassInvariants();
		//@POST
		return converted;
	}
	
	/**
	 * Create hash code for coordinates of type {@link CartesianCoordinate}.
	 * This method forwards the calculation to a private helper method.
	 * 
	 * @methodtype get
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		return doHashCode(this.getX(), this.getY(), this.getZ());
	}
	
	/**
	 * Actual implementation of generating hash codes.
	 * 
	 * @param x	x-dimension
	 * @param y y-dimension
	 * @param z z-dimension
	 * @return hash code
	 * @methodtype get
	 */
	private static int doHashCode(double x, double y, double z) {
		return Objects.hash(x, y, z);
	}

	/**
	 * Asserts that a Cartesian Coordinate is valid. In order to be valid,
	 * all dimensions of the coordinate must be valid.
	 * 
	 * @throws IllegalStateException
	 * @methodtype assert
	 */
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		try {
			AbstractCoordinate.assertDoubleIsFinite(this.getX());
			AbstractCoordinate.assertDoubleIsFinite(this.getY());
			AbstractCoordinate.assertDoubleIsFinite(this.getZ());		
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException("Class invariants violated");
		}
	}
	
}