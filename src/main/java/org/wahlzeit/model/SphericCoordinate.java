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
 * A class representing spheric coordinates. A spheric coordinate consists
 * of three values:
 * 	{@link #polar}:		polar angle		([0;180))
 * 	{@link #azimut}:	azimut angle	([0;360))
 *	{@link #radius}:	radius			([0;Double.MAX_VALUE])
 * All fields are declared as private and final. Values must be passed in degrees.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private final double polar;		// polar angle of a spheric coordinate.
	private final double azimut;	// azimut angle of a spheric coordinate.
	private final double radius;	// radius of a spheric coordinate.
		
	/**
	 * Creates a new instance of {@link SphericCoordinate} with given values.
	 * 
	 * @param polar	polar angle ([0;180))
	 * @param azimut	azimut angle ([0;360))
	 * @param radius	radius ([0;Double.MAX_VALUE])
	 * @return new instance of {@link SphericCoordinate}
	 * @methodtype constructor
	 */
	public SphericCoordinate(double polar, double azimut, double radius) {
		//@PRE
		assertPolarAngleIsValid(polar);
		assertAzimutAngleIsValid(azimut);
		assertRadiusIsValid(radius);
		//@PRE
		this.polar = polar;
		this.azimut = azimut;
		this.radius = radius;
	}
	
	/**
	 * Returns the polar angle {@link #polar} of the coordinate.
	 * 
	 * @return polar angle of the coordinate
	 * @methodtype get
	 */
	public double getPolar() {
		return this.polar;
	}
	
	/**
	 * Returns the azimut angle {@link #azimut} of the coordinate.
	 *
	 * @return azimut angle of the coordinate
	 * @methodtype get
	 */
	public double getAzimut() {
		return this.azimut;
	}
	
	/**
	 * Returns the radius {@link #radius} of the coordinate.
	 * 
	 * @return radius of the coordinate
	 * @methodtype get
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Wrapper for {@link #doAsCartesianCoordinate()}.
	 * 
	 * @return this as {@link CartesianCoordinate}
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() throws InvalidCoordinateStateException {
		try {
			CartesianCoordinate converted = this.doAsCartesianCoordinate();
			return converted;
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated");
		}
	}
	
	/**
	 * Converts the spheric coordinate into a Cartesian coordinate and
	 * returns a new instance of {@link CartesianCoordinate}.
	 * 
	 * @return this as {@link SphericCoordinate}
	 * @throws IllegalStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate doAsCartesianCoordinate() throws IllegalStateException {
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
	 * Wrapper for {@link #doAsSphericCoordinate()}.
	 * 
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @return this
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() throws InvalidCoordinateStateException {
		//@PRE
		try {
			this.assertClassInvariants();
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated");
		}
		//@PRE
		return this;
	}
	
	/**
	 * The conversion of a spheric coordinate into a spheric
	 * coordinate is the instance itself.
	 * 
	 * @throws IllegalStateException	if the spheric coordinate is not valid
	 * @return this
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate doAsSphericCoordinate() throws IllegalStateException {
		//@PRE
		this.assertClassInvariants();
		//@PRE
		return this;
	}
	
	/**
	 * Asserts that a spheric coordinate is valid. In order to be valid,
	 * the azimut/polar/radius must be valid.
	 * 
	 * @throws IllegalStateException	if one of the conditions is violated
	 * @methodtype assert
	 */
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		assertPolarAngleIsValid(this.getPolar());
		assertAzimutAngleIsValid(this.getAzimut());
		assertRadiusIsValid(this.getRadius());
	}
	
	/**
	 * Asserts that the polar angle of a spheric coordinate is valid.
	 * 
	 * @param polar	polar angle to check
	 * @throws IllegalArgumentException	if the polar angle is invalid
	 * @methodtype assert
	 */
	private static void assertPolarAngleIsValid(double polar) throws IllegalArgumentException {
		AbstractCoordinate.assertDoubleIsFinite(polar);
		if (polar >= 180.0 || polar < 0.0) {
			throw new IllegalArgumentException("Invalid value for polar angle.");
		}
	}
	
	/**
	 * Asserts that the azimut angle of a spheric coordinate is valid.
	 * 
	 * @param azimut	azimut angle to check
	 * @throws IllegalArgumentException	if the azimut angle is invalid
	 * @methodtype assert
	 */
	private static void assertAzimutAngleIsValid(double azimut) throws IllegalArgumentException {
		AbstractCoordinate.assertDoubleIsFinite(azimut);
		if (azimut >= 360.0 || azimut < 0.0) {
			throw new IllegalArgumentException("Invalid value for azimut angle.");
		}
	}
	
	/**
	 * Asserts that the radius of a spheric coordinate is valid.
	 * 
	 * @param radius	radius value to check
	 * @throws IllegalArgumentException if the radius value is not valid
	 * @methodtype assert
	 */
	private static void assertRadiusIsValid(double radius) throws IllegalArgumentException {
		AbstractCoordinate.assertDoubleIsFinite(radius);
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius is negative.");
		}
	}
	
}