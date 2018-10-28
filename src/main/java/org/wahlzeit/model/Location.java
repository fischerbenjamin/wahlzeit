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
 * This class is used to store information about the location
 * of a photo.
 */
public class Location {

	/**
	 * This constant is used to check if two locations
	 * are equal. They can be considered as equal when
	 * the distance between these two locations is at most
	 * EPSILON.
	 */
	public static final double EPSILON = 10e-9;
	
	/**
	 * Cartesian coordinate of the location.
	 */
	public Coordinate coordinate;
	
	public Location(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	/**
	 * Checks if two locations are the same. They are considered
	 * as equal when their distance to each other is at most
	 * EPSILON.
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Location) {
			Location location = (Location) obj;
			double distance = this.coordinate.getDistance(location.getCoordinate()); 
			if (distance < EPSILON) {
				return true;
			}
		}
		return false;
	}
		
}
