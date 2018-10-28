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
 *	This class represents Cartesian coordinates. 
 */
public class Coordinate {
	
	private double x;
	private double y;
	private double z;
	
	public Coordinate() {}
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * The distance of two coordinates is the Euclidian distance
	 * in the three-dimensional space.
	 */
	public double getDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("Coordinate must not be null.");
		}
		double dx = Math.pow(this.x - coordinate.x, 2);
		double dy = Math.pow(this.y - coordinate.y, 2);
		double dz = Math.pow(this.z - coordinate.z, 2);
		return Math.sqrt(dx + dy + dz);
	}
	
	/**
	 * Two coordinates are considered as equal if their
	 * x/y/z values are equal.
	 */
	public boolean isEqual(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("Coordinate must not be null");
		}
		if (this.x == coordinate.x
				&& this.y == coordinate.y
				&& this.z == coordinate.z) {
			return true;
		}
		return false;
	}

	/**
	 * This method simply forwards to {@link #isEqual(Coordinate)}.
	 */
	public boolean equals(Object obj) {
		if (obj == null ) {
			return false;
		}
		if (obj instanceof Coordinate) {
			Coordinate coordinate = (Coordinate) obj;
			return this.isEqual(coordinate);
		}
		return false;
	}

}
