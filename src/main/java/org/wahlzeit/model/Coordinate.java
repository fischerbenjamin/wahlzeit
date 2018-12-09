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
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtpye conversion
	 */
	CartesianCoordinate asCartesianCoordinate() throws InvalidCoordinateStateException;

	/**
	 * Converts a coordinate into a coordinate of type {@link SphericCoordinate}.
	 * 
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype conversion
	 */
	SphericCoordinate asSphericCoordinate() throws InvalidCoordinateStateException;
	
	/**
	 * Computes and returns the Cartesian distance between two coordinates.
	 * 
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @throws InvalidCoordinateCalculationException	if the calculation failed
	 * @methodtype get
	 */
	double getCartesianDistance(final Coordinate coordinate)
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException;

	/**
	 * Computes and returns the central angle distance between two coordinates.
	 *
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @throws InvalidCoordinateCalculationException	if the calculation failed
	 * @methodtype get
	 */	
	double getCentralAngle(final Coordinate coordinate)
			throws InvalidCoordinateCalculationException, InvalidCoordinateStateException;
	
	/**
	 * Checks if two coordinates are equal.
	 * 
	 * @throws InvalidCoordinateStateException	if class invariants were violated
	 * @methodtype boolean query
	 */
	boolean isEqual(final Coordinate coordinate) throws InvalidCoordinateStateException;
	
}