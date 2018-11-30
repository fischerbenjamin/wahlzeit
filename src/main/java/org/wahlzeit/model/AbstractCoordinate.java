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
 * This is an abstract superclass for the concrete implementations of
 * three-dimensional coordinates. Currently, two representations are
 * supported by {@link SphericCoordinate} and {@link CartesianCoordinate}.
 */
public abstract class AbstractCoordinate implements Coordinate {
	
	/**
	 * Converts a coordinate into a Cartesian coordinate.
	 * 
	 * @methodtype conversion
	 */
	public abstract CartesianCoordinate asCartesianCoordinate();
	
	/**
	 * Converts a coordinate into a spheric coordinate.
	 * 
	 * @methodtype conversion
	 */
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * Asserts that a coordinate is valid.
	 * 
	 * @methodtype assert
	 */
	public abstract void assertClassInvariants();
	
	/**
	 * Computes and returns the Cartesian distance between two coordinates.
	 * Therefore, the Cartesian representation of both coordinates is used.
	 * 
	 * @methodtype get
	 */
	public double getCartesianDistance(Coordinate coordinate) {
		//@PRE
		assertCoordinateIsNotNull(coordinate);
		this.assertClassInvariants();
		coordinate.assertClassInvariants();
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
		coordinate.assertClassInvariants();
		//@POST
		return distance;
	}

	/**
	 * Computes and returns the central angle between two coordinates.
	 * Therefore, they must be located on the same sphere. Otherwise, an exception
	 * of type {@link IllegalArgumentException} is thrown indicating this error.
	 * The spheric representation of both coordinates is used for this calculation.
	 * 
	 * @methodtype get
	 */
	public double getCentralAngle(Coordinate coordinate) {
		//@PRE
		assertCoordinateIsNotNull(coordinate);
		this.assertClassInvariants();
		coordinate.assertClassInvariants();
		//@PRE
		SphericCoordinate self = this.asSphericCoordinate();
		SphericCoordinate other = coordinate.asSphericCoordinate();
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
		assertDistanceIsNotNegative(result);
		this.assertClassInvariants();
		coordinate.assertClassInvariants();
		//@POST
		return result;
	}

	/**
	 * Checks if two coordinates are equal by converting both coordinates into
	 * Cartesian coordinates and comparing each dimension.
	 * 
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate coordinate) {
		//@PRE
		assertCoordinateIsNotNull(coordinate);
		this.assertClassInvariants();
		coordinate.assertClassInvariants();
		//@PRE
		CartesianCoordinate self = this.asCartesianCoordinate();
		CartesianCoordinate other = coordinate.asCartesianCoordinate();
		boolean dx = Math.abs(self.getX() - other.getX()) < Coordinate.EPSILON;
		boolean dy = Math.abs(self.getY() - other.getY()) < Coordinate.EPSILON;
		boolean dz = Math.abs(self.getZ() - other.getZ()) < Coordinate.EPSILON;
		//@POST
		this.assertClassInvariants();
		coordinate.assertClassInvariants();
		//@POST
		return dx && dy && dz;
	}
	
	/**
	 * Asserts that the given double value is finite.
	 * 
	 * @methodtype assert
	 */
	protected static void assertDoubleIsFinite(double x) {
		assert(Double.isFinite(x));
	}

	/**
	 * Asserts that the given coordinate is not null.
	 * 
	 * @methodtype assert
	 * @param coordinate
	 */
	protected static void assertCoordinateIsNotNull(Coordinate coordinate) {
		assert(coordinate != null);
	}

	/**
	 * Assert that the given distance is positive or 0.
	 * 
	 * @methodtype assert
	 * @param coordinate
	 */
	protected static void assertDistanceIsNotNegative(double distance) {
		assert(distance >= 0.0);
	}
	
}