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

/**
 * A class representing Cartesian coordinates. A Cartesian coordinate consists
 * of three values:
 * 	{@link #x}:	x-dimension	((-inf;+inf))
 *	{@link #y}:	y-dimension	((-inf;+inf))
 *	{@link #z}:	z-dimension	((-inf;+inf))
 * All fields are declared as private and final.
 */
public class CartesianCoordinate extends AbstractCoordinate {
	
	private final double x;		// x-dimension
	private final double y;		// y-dimension
	private final double z;		// z-dimension
	
	/**
	 * Create a new Cartesian coordinate with given values.
	 * 
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		//@PRE
		AbstractCoordinate.assertDoubleIsFinite(x);
		AbstractCoordinate.assertDoubleIsFinite(y);
		AbstractCoordinate.assertDoubleIsFinite(z);
		//@PRE
		this.x = x;
		this.y = y;
		this.z = z;
		//@POST
		this.assertClassInvariants();
		//@POST
	}
	
	/**
	 * Returns dimension {@link x} of a Cartesian coordinate.
	 * 
	 * @methodtype get
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Returns dimension {@link y} of a Cartesian coordinate.
	 * 
	 * @methodtype get
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns dimension {@link z} of a Cartesian coordinate.
	 * 
	 * @methodtype get
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * The conversion of a Cartesian coordinate into a Cartesian
	 * coordinate is the instance itself.
	 * 
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		//@PRE
		this.assertClassInvariants();
		//@PRE
		return this;
	}
	
	/**
	 * Converts the Cartesian coordinate into a spheric coordinate and
	 * returns a new instance of {@link SphericCoordinate}.
	 * 
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
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
	 * @methodtype assert
	 */
	@Override
	public void assertClassInvariants() {
		AbstractCoordinate.assertDoubleIsFinite(this.getX());
		AbstractCoordinate.assertDoubleIsFinite(this.getY());
		AbstractCoordinate.assertDoubleIsFinite(this.getZ());		
	}
	
}