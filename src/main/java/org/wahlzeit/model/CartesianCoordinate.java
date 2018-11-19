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
public class CartesianCoordinate extends AbstractCoordinate {
	
	private final double x;
	private final double y;
	private final double z;
	
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		checkDouble(x, "x");
		checkDouble(y, "y");
		checkDouble(z, "z");
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @methodtype get
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * @methodtype get
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return new CartesianCoordinate(this.getX(), this.getY(), this.getZ());
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2)+Math.pow(this.getZ(), 2));
		double azimut = Angle.convertDegToValidDeg(Math.toDegrees(Math.atan2(this.getY(),this.getX())));
		double polar = Angle.convertDegToValidDeg(Math.toDegrees(Math.acos(this.getZ()/radius)));
		return new SphericCoordinate(polar, azimut, radius);
	}

}
