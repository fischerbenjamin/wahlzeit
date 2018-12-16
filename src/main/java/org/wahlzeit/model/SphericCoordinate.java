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
 * A class representing spheric coordinates. A spheric coordinate consists
 * of three values:
 * 	{@link #polar}:		polar angle		([0;180))
 * 	{@link #azimut}:	azimut angle	([0;360))
 *	{@link #radius}:	radius			([0;Double.MAX_VALUE])
 * All fields are declared as private and final. Values must be passed in degrees.
 */
public class SphericCoordinate extends AbstractCoordinate {

	/**
	 * Stores the values of type {@link SphericCoordinate}.
	 */
	private static HashMap<Integer, SphericCoordinate> coordinates = new HashMap<>();
	
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
	private SphericCoordinate(double polar, double azimut, double radius) {
		this.polar = polar;
		this.azimut = azimut;
		this.radius = radius;
	}
	
	/**
	 * This method is used to maintain the {@link SphericCoordinate} values.
	 * If the value was already created, it simply returns its reference.
	 * Otherwise, a new value is created and is added to the map of known values.
	 * 
	 * @param polar		polar angle
	 * @param azimut	azimut angle
	 * @param radius	radius
	 * @return	instance of {@link SphericCoordinate} used as a value
	 * @throws IllegalArgumentException if one of the values is invalid
	 * @methodtype factory
	 */
	public static synchronized SphericCoordinate getSphericCoordinate(double polar, double azimut, double radius)
		throws IllegalArgumentException
	{
		//@PRE
		assertPolarAngleIsValid(polar);
		assertAzimutAngleIsValid(azimut);
		assertRadiusIsValid(radius);
		//@PRE
		int hash = doHashCode(polar, azimut, radius);
		if (coordinates.containsKey(hash)) {
			return coordinates.get(hash);
		}
		SphericCoordinate coordinate = new SphericCoordinate(polar, azimut, radius);
		coordinates.put(coordinate.hashCode(), coordinate);
		return coordinate;
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
		CartesianCoordinate converted = CartesianCoordinate.getCartesianCoordinate(x, y, z);
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
	 * Create hash code for coordinates of type {@link SphericCoordinate}.
	 * This method forwards the calculation to a private helper method.
	 * 
	 * @methodtype get
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		return doHashCode(this.getPolar(), this.getAzimut(), this.getRadius());
	}
	
	/**
	 * Actual implementation of generating hash codes.
	 * 
	 * @param polar		polar angle
	 * @param azimut	azimut angle
	 * @param radius	radius
	 * @return hash code
	 * @methodtype get
	 */
	private static int doHashCode(double polar, double azimut, double radius) {
		return Objects.hash(polar, azimut, radius);
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