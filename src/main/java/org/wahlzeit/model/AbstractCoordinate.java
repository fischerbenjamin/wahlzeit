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

import org.wahlzeit.model.exceptions.InvalidCoordinateCalculationException;
import org.wahlzeit.model.exceptions.InvalidCoordinateStateException;
import org.wahlzeit.utils.PatternInstance;

/**
 * This is an abstract superclass for the concrete implementations of
 * three-dimensional coordinates. Currently, two representations are
 * supported by {@link SphericCoordinate} and {@link CartesianCoordinate}.
 */
@PatternInstance(
		patternName = "Template Method",
		participants = {
				"AbstractClass@class.AbstractCoordinate",
				"ConcreteClass@class.CartesianCoordinate,class.SphericCoordinate"
		}
)
public abstract class AbstractCoordinate implements Coordinate {
	
	
	/**
	 * Converts a coordinate into a Cartesian coordinate.
	 * 
	 * @methodtype conversion
	 */
	protected abstract CartesianCoordinate doAsCartesianCoordinate() throws IllegalStateException;
	
	/**
	 * Converts a coordinate into a spheric coordinate.
	 * 
	 * @methodtype conversion
	 */
	protected abstract SphericCoordinate doAsSphericCoordinate() throws IllegalStateException;
	
	/**
	 * Values cannot be cloned. 
	 * 
	 * @return this 
	 */
	@Override
	protected Object clone() {
		return this;
	}
	
	/**
	 * Wrapper for {@link #doAsCartesianCoordinate()}.
	 * 
	 * @return this as {@link CartesianCoordinate}
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate() throws InvalidCoordinateStateException {
		try {
			CartesianCoordinate converted = this.doAsCartesianCoordinate();
			return converted;
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated");
		}
	}
	
	/**
	 * Wrapper for {@link #doAsSphericCoordinate()}.
	 * 
	 * @return this as {@link SphericCoordinate}
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate() throws InvalidCoordinateStateException {
		try {
			SphericCoordinate converted = this.doAsSphericCoordinate();
			return converted;
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated");
		} 
	}
	
	/**
	 * Wrapper method for {@link #doGetCartesianDistance(Coordinate)}.
	 * 
	 * @param coordinate	coordinate to compute the distance to
	 * @return distance between both coordinates
	 * @throws InvalidCoordinateCalculationException	if a negative distance was computed
	 * @throws InvalidCoordinateStateException	if class invariants were violated 
	 * @methodtype get
	 */
	public double getCartesianDistance(Coordinate coordinate)
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException {
		try {
			double distance = doGetCartesianDistance(coordinate);
			return distance;
		} catch (IllegalArgumentException e) {
			throw new InvalidCoordinateCalculationException("Calculation returned a negative distance");
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated during calculation");
		}
	}
	
	/**
	 * Computes and returns the Cartesian distance between two coordinates.
	 * Therefore, the Cartesian representation of both coordinates is used.
	 * 
	 * @param coordinate	coordinate to compute the distance to
	 * @return distance between both coordinates
	 * @throws InvalidCoordinateStateException	if conversion of coordinate into a Cartesian coordinate failed
	 * @throws IllegalStateException	if class invariants were violated
	 * @throws IllegalArgumentException	if a negative distance was calculated
	 * @methodtype get
	 */
	protected double doGetCartesianDistance(Coordinate coordinate) 
			throws IllegalStateException, IllegalArgumentException, InvalidCoordinateStateException {
		//@PRE
		assertCoordinateIsNotNull(coordinate);
		//@PRE
		CartesianCoordinate self = this.asCartesianCoordinate();
		CartesianCoordinate other = coordinate.asCartesianCoordinate();
		double distance = Math.pow(self.getX() - other.getX(), 2);
		distance += Math.pow(self.getY() - other.getY(), 2);
		distance += Math.pow(self.getZ() - other.getZ(), 2);
		distance = Math.sqrt(distance);
		//@POST
		assertDistanceIsNotNegative(distance);
		this.assertClassInvariants();
		other.assertClassInvariants();
		//@POST
		return distance;
	}

	/**
	 * Wrapper method for the computation of the central angle between two coordinates.
	 * Calculation is done by {@link #doGetCentralAngle(Coordinate)}.
	 * 
	 * @param coordinate	coordinate to compute the central angle to
	 * @return central angle between both coordinates
	 * @throws InvalidCoordinateCalculationException	if the calculation computed an invalid angle
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype get
	 */
	public double getCentralAngle(Coordinate coordinate)
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException {
		try {
			double angle = doGetCentralAngle(coordinate);
			return angle;
		} catch (IllegalArgumentException e) {
			throw new InvalidCoordinateCalculationException("Calculation returned an invalid angle");
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated");
		}
	}
	
	/**
	 * Computes and returns the central angle between two coordinates.
	 * Therefore, they must be located on the same sphere. Otherwise, an exception
	 * of type {@link IllegalArgumentException} is thrown indicating this error.
	 * The spheric representation of both coordinates is used for this calculation.
	 * 
	 * @param coordinate coordinate to compute the central angle to
	 * @return the central angle between both coordinates
	 * @throws IllegalArgumentException	if the calculation failed
	 * @throws IllegalStateException	if class invariants were violated
	 * @throws InvalidCoordinateStateException 
	 * @methodtype get
	 */
	protected double doGetCentralAngle(Coordinate coordinate) 
			throws IllegalArgumentException, IllegalStateException, InvalidCoordinateStateException {
		//@PRE
		assertCoordinateIsNotNull(coordinate);
		this.assertClassInvariants();
		//@PRE
		SphericCoordinate self = this.asSphericCoordinate();
		SphericCoordinate other = coordinate.asSphericCoordinate(); // Simply propagate this exception.
		// Verify that both coordinates are located on the same sphere.
		if (Math.abs(self.getRadius() - other.getRadius()) >= Coordinate.EPSILON) {
			throw new IllegalArgumentException("Coordinates must be on the same sphere.");
		}
		double numA = Math.cos(Math.toRadians(other.getPolar()));
		numA *= Math.sin(Math.toRadians(Math.abs(self.getAzimut()-other.getAzimut())));
		numA = Math.pow(numA, 2);
		double numB = Math.cos(Math.toRadians(self.getPolar()) * Math.sin(Math.toRadians(other.getPolar())));
		numB -= Math.sin(Math.toRadians(self.getPolar())) * Math.cos(Math.toRadians(other.getPolar()))
				 * Math.cos(Math.toRadians(Math.abs(self.getAzimut()-other.getAzimut())));
		numB = Math.pow(numB, 2);
		double den = Math.sin(Math.toRadians(self.getPolar())) * Math.sin(Math.toRadians(other.getPolar()));
		den += Math.cos(Math.toRadians(self.getPolar())) * Math.cos(Math.toRadians(self.getPolar())) 
				* Math.cos(Math.toRadians(Math.abs(self.getAzimut()-other.getAzimut())));
		double result = Math.atan2(Math.sqrt(numA + numB), den);
		//@POST
		assertDoubleIsFinite(result);
		this.assertClassInvariants();
		other.assertClassInvariants();
		//@POST
		return result;
	}

	/**
	 * Wrapper method for {@link #doIsEqual(Coordinate)}.
	 * 
	 * @param coordinate coordinate to compare
	 * @return true if they are equal, false otherwise
	 * @throws InvalidCoordinateStateException if one of the coordinates violated the class invariants
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate coordinate) throws InvalidCoordinateStateException {
		try {
			boolean equality = doIsEqual(coordinate);
			return equality;
		} catch (IllegalStateException e) {
			throw new InvalidCoordinateStateException("Class invariants were violated");
		}
	}
	
	/**
	 * Checks if two coordinates are equal by converting both coordinates into
	 * Cartesian coordinates and comparing each dimension.
	 * 
	 * @param coordinate coordinate to compare
	 * @return true if they are equal, false otherwise
	 * @throws IllegalStateException if one of the coordinates violated the class invariants
	 * @throws InvalidCoordinateStateException	if the conversion of coordinate into a Cartesian coordinate failed
	 * @methodtype boolean query
	 */
	protected boolean doIsEqual(Coordinate coordinate) throws IllegalStateException, InvalidCoordinateStateException {
		//@PRE
		assertCoordinateIsNotNull(coordinate);
		this.assertClassInvariants();
		//@PRE
		CartesianCoordinate self = this.asCartesianCoordinate();
		CartesianCoordinate other = coordinate.asCartesianCoordinate();
		boolean dx = Math.abs(self.getX() - other.getX()) < Coordinate.EPSILON;
		boolean dy = Math.abs(self.getY() - other.getY()) < Coordinate.EPSILON;
		boolean dz = Math.abs(self.getZ() - other.getZ()) < Coordinate.EPSILON;
		//@POST
		this.assertClassInvariants();
		other.assertClassInvariants();
		//@POST
		return dx && dy && dz;
	}

	/**
	 * Asserts that a coordinate is valid.
	 * 
	 * @throws IllegalStateException	if these invariants were violated
	 * @methodtype assert
	 */
	protected abstract void assertClassInvariants() throws IllegalStateException;
	
	/**
	 * Asserts that the given double value is finite.
	 * 
	 * @param x	double value to check
	 * @throws IllegalArgumentException	if the passed value is not finite		
	 * @methodtype assert
	 */
	protected static void assertDoubleIsFinite(double x) throws IllegalArgumentException {
		if (!Double.isFinite(x)) {
			throw new IllegalArgumentException("Double must be finite.");
		}
	}

	/**
	 * Asserts that the given coordinate is not null.
	 * 
	 * @param coordinate coordinate object to check
	 * @throws NullPointerException	if the coordinate is null
	 * @methodtype assert
	 */
	protected static void assertCoordinateIsNotNull(Coordinate coordinate) throws NullPointerException {
		if (coordinate == null) {
			throw new NullPointerException("Coordinate must not be null.");
		}
	}

	/**
	 * Assert that the given distance is positive or 0.
	 * 
	 * @param distance	distance to check
	 * @throws IllegalArgumentException	if distance is less than 0	
	 * @methodtype assert
	 */
	protected static void assertDistanceIsNotNegative(double distance) throws IllegalArgumentException {
		if (distance < 0) {
			throw new IllegalArgumentException("Distance must be positive or 0.");
		}
	}
	
}