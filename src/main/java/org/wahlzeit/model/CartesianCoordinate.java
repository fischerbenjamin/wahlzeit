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

import org.wahlzeit.model.exceptions.InvalidCoordinateStateException;

/**
 * A class representing Cartesian coordinates. A Cartesian coordinate consists
 * of three values:
 * 	{@link #x}:	x-dimension	([Double.MIN_VALUE;Double.MAX_VALUE])
 *	{@link #y}:	y-dimension	([Double.MIN_VALUE;Double.MAX_VALUE])
 *	{@link #z}:	z-dimension	([Double.MIN_VALUE;Double.MAX_VALUE])
 * All fields are declared as private and final.
 */
public class CartesianCoordinate extends AbstractCoordinate {
	
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
	 * @throws IllegalArgumentException if one of the arguments is not a finite double value
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
		//@PRE
		AbstractCoordinate.assertDoubleIsFinite(x);
		AbstractCoordinate.assertDoubleIsFinite(y);
		AbstractCoordinate.assertDoubleIsFinite(z);
		//@PRE
		this.x = x;
		this.y = y;
		this.z = z;
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
	 * Wrapper for {@link #doAsCartesianCoordinate()}.
	 * 
	 * @return the coordinate itself
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() throws InvalidCoordinateStateException {
		//@PRE
		try {
			this.assertClassInvariants();
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated during conversion");
		}
		//@PRE
		return this;
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
	 * Wrapper for {@link #doAsSphericCoordinate()}.
	 * 
	 * @throws InvalidCoordinateException	if class invariants were violated
	 * @return converted Cartesian coordinate as {@link SphericCoordinate}
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() throws InvalidCoordinateStateException {
		try {
			SphericCoordinate converted = doAsSphericCoordinate();
			return converted;
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated during conversion");
		}
	}
	
	/**
	 * Converts the Cartesian coordinate into a spheric coordinate and
	 * returns a new instance of {@link SphericCoordinate}.
	 * 
	 * @throws IllegalStateException	if class invariants are violated
	 * @return the Cartesian coordinate as a spheric coordinate
	 * @methodtype conversion
	 */
	protected SphericCoordinate doAsSphericCoordinate() throws IllegalStateException {
		//@PRE
		this.assertClassInvariants();
		//@PRE
		double radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
		double azimut = Math.toDegrees(Math.atan2(this.getY(), this.getX()));
		double polar = Math.toDegrees(Math.acos(this.getZ()/radius));
		SphericCoordinate converted = new SphericCoordinate(polar, azimut, radius);
		//@POST
		converted.assertClassInvariants();
		this.assertClassInvariants();
		//@POST
		return converted;
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