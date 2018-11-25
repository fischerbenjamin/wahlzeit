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

public abstract class AbstractCoordinate implements Coordinate {

	protected static final double DISTANCE_EQUALITY = 10E-12;
	
	public abstract CartesianCoordinate asCartesianCoordinate();
	
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * @methodtype get
	 */
	public double getCartesianDistance(Coordinate coordinate) {
		checkCoordinate(coordinate);
		CartesianCoordinate self = this.asCartesianCoordinate();
		CartesianCoordinate other = coordinate.asCartesianCoordinate();
		double dx = Math.pow(self.getX() - other.getX(), 2);
		double dy = Math.pow(self.getY() - other.getY(), 2);
		double dz = Math.pow(self.getZ() - other.getZ(), 2);
		return Math.sqrt(dx + dy + dz);
	}

	/**
	 * @methodtype get
	 */
	public double getCentralAngle(Coordinate coordinate) {
		checkCoordinate(coordinate);
		SphericCoordinate self = this.asSphericCoordinate();
		SphericCoordinate other = coordinate.asSphericCoordinate();
		if (self.getRadius() != other.getRadius()) {
			throw new IllegalArgumentException("The coordinates must be on the same sphere.");
		}
		double lat1 = self.getPolar().convertPolarToLatitudeRad();
		double lat2 = other.getPolar().convertPolarToLatitudeRad();
		double long1 = self.getAzimut().convertAzimutToLongitudeRad();
		double long2 = other.getAzimut().convertAzimutToLongitudeRad();
		return Math.acos((Math.sin(lat1)*Math.sin(lat2)) + (Math.cos(lat1)*Math.cos(long2)
				* Math.cos(Math.abs(long1-long2))));
	}

	/**
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate coordinate) {
		checkCoordinate(coordinate);
		if (this.getCartesianDistance(coordinate) < DISTANCE_EQUALITY) {
			return true;
		}
		return false;
	}

	/**
	 * @methodtype assert
	 */
	protected static void checkCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new NullPointerException("Coordinate must not be null");
		}
	}
	
	/**
	 * @methodtype assert
	 */
	protected static void checkDouble(double x, String msg) {
		if (!Double.isFinite(x)) {
			throw new IllegalArgumentException("Value '" + msg + "' must be a valid double value.");
		}
	}
	
}