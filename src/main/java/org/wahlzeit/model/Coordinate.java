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
 * Coordinate interface for the wahlzeit application.
 */
public interface Coordinate {

	/**
	 * Accuracy for all double comparisons in the coordinate context. 
	 */
	final double EPSILON = 10E-9;
	
	/**
	 * Converts a coordinate into a coordinate of type {@link CartesianCoordinate}.
	 * 
	 * @methodtpye conversion
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Converts a coordinate into a coordinate of type {@link SphericCoordinate}.
	 * 
	 * @methodtype conversion
	 */
	SphericCoordinate asSphericCoordinate();
	
	/**
	 * Computes and returns the Cartesian distance between two coordinates.
	 * 
	 * @methodtype get
	 */
	double getCartesianDistance(final Coordinate coordinate);

	/**
	 * Computes and returns the central angle distance between two coordinates.
	 * 
	 * @methodtype get
	 */	
	double getCentralAngle(final Coordinate coordinate);
	
	/**
	 * Checks if two coordinates are equal.
	 * 
	 * @methodtype boolean query
	 */
	boolean isEqual(final Coordinate coordinate);
	
	/**
	 * Asserts that the coordinate is valid.
	 * 
	 * @methodtype assert
	 */
	void assertClassInvariants();

}