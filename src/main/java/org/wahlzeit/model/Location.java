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
 * This class is used to store information about the location
 * of a photo. A location is defined by two properties:
 * 	{@link #coordinate}		coordinate of the location
 * 	{@link #name}			name of the location
 */
public class Location {
	
	private final String name;					// Name of the location.
	private final Coordinate coordinate;		// Coordinate of the location.
	
	/**
	 * Creates a new instance of {@link Location} with a given
	 * coordinate of type {@link Coordinate}.
	 * 
	 * @methodtype constructor
	 */
	public Location(Coordinate coordinate, String name) {
		//@PRE
		AbstractCoordinate.assertCoordinateIsNotNull(coordinate);;
		assert(name != null);
		//@PRE
		this.name = name;
		this.coordinate = coordinate;
	}
	
	/**
	 * Returns the coordinate {@link #coordinate} of a location.
	 * 
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	/**
	 * Returns the name {@link #name} of a location.
	 * 
	 * @methodtype get
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Checks if two locations are equal. Therefore, their coordinates and names must be equal. 
	 * 
	 * @param obj	object to compare
	 * @methodtype comparison
	 */
	public boolean equals(Object obj) {
		// No further restrictions on the preconditions here because an object of type Object is passed.
		if (obj == null) {
			return false;
		}
		if (obj instanceof Location) {
			Location location = (Location) obj;
			// A location must have a valid coordinate at this point.
			try {
				if (this.getCoordinate().isEqual(location.getCoordinate()) && this.getName() == location.getName()) {
					return true;
				}
			} catch (InvalidCoordinateStateException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
		
}