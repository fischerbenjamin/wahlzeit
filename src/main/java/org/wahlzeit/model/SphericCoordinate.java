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
 * A class representing spheric coordinates. A spheric coordinate consists
 * of three values:
 * 	{@link #polar}:		polar angle		([0;180))
 * 	{@link #azimut}:	azimut angle	([0;360))
 *	{@link #radius}:	radius			([0;+inf))
 * All fields are declared as private and final. Values must be passed in degrees.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private final double polar;		// polar angle of a spheric coordinate.
	private final double azimut;	// azimut angle of a spheric coordinate.
	private final double radius;	// radius of a spheric coordinate.
		
	/**
	 * Creates a new instance of {@link SphericCoordinate} with given values.
	 * 
	 * @methodtype constructor
	 */
	public SphericCoordinate(double polar, double azimut, double radius) {
		//@PRE
		AbstractCoordinate.assertDoubleIsFinite(polar);
		AbstractCoordinate.assertDoubleIsFinite(azimut);
		AbstractCoordinate.assertDoubleIsFinite(radius);
		//@PRE
		this.polar = polar;
		this.azimut = azimut;
		this.radius = radius;
		//@POST
		this.assertClassInvariants();
		//@POST
	}
	
	/**
	 * Returns the polar angle {@link #polar} of the coordinate.
	 * 
	 * @methodtype get
	 */
	public double getPolar() {
		return this.polar;
	}
	
	/**
	 * Returns the azimut angle {@link #azimut} of the coordinate.
	 * 
	 * @methodtype get
	 */
	public double getAzimut() {
		return this.azimut;
	}
	
	/**
	 * Returns the radius {@link #radius} of the coordinate.
	 * 
	 * @methodtype get
	 */
	public double getRadius() {
		return this.radius;
	}
			
	/**
	 * Converts the spheric coordinate into a Cartesian coordinate and
	 * returns a new instance of {@link CartesianCoordinate}.
	 * 
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		//@PRE
		this.assertClassInvariants();
		//@PRE
		double x = this.getRadius() * Math.sin(Math.toRadians(this.getPolar())) * Math.cos(Math.toRadians(this.getAzimut()));
		double y = this.getRadius() * Math.sin(Math.toRadians(this.getPolar())) * Math.sin(Math.toRadians(this.getAzimut()));
		double z = this.getRadius() * Math.cos(Math.toRadians(this.getPolar()));
		CartesianCoordinate converted = new CartesianCoordinate(x, y, z);
		//@POST
		this.assertClassInvariants();
		converted.assertClassInvariants();
		//@POST
		return converted;
	}

	/**
	 * The conversion of a spheric coordinate into a spheric
	 * coordinate is the instance itself.
	 * 
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		//@PRE
		this.assertClassInvariants();
		//@PRE
		return this;
	}
	
	/**
	 * Asserts that a spheric coordinate is valid. In order to be valid,
	 * the azimut/polar/radius must be valid.
	 * 
	 * @methodtype assert
	 */
	@Override
	public void assertClassInvariants() {
		this.assertPolarAngleIsValid();
		this.assertAzimutAngleIsValid();
		this.assertRadiusIsValid();
	}
	
	/**
	 * Asserts that the polar angle {@link #polar} of the spheric coordinate is valid.
	 * 
	 * @methodtype assert
	 */
	private void assertPolarAngleIsValid() {
		AbstractCoordinate.assertDoubleIsFinite(this.getPolar());
		assert(this.getPolar() >= 0.0);
		assert(this.getPolar() < 180.0);
	}

	/**
	 * Asserts that the azimut angle {@link #azimut} of the spheric coordinate is valid.
	 * 
	 * @methodtype assert
	 */
	private void assertAzimutAngleIsValid() {
		AbstractCoordinate.assertDoubleIsFinite(this.getAzimut());
		assert(this.getAzimut() >= 0.0);
		assert(this.getAzimut() < 360.0);
	}
	
	/**
	 * Asserts that the radius {@link #radius} of the spheric coordinate is valid.
	 * 
	 * @methodtype assert
	 */
	private void assertRadiusIsValid() {
		AbstractCoordinate.assertDoubleIsFinite(this.getRadius());
		assert(this.getRadius() >= 0.0);
	}
	
}