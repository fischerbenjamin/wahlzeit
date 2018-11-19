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
 * A class representing spheric coordinates. Angles must be passed in degrees.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private final Angle polar;
	private final Angle azimut;
	private final double radius;
		
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double polar, double azimut, double radius) {
		checkArguments(polar, azimut, radius);
		this.polar = new Angle(polar);
		this.azimut = new Angle(azimut);
		this.radius = radius;
	}

	/**
	 * @methodtype assert
	 */
	private void checkArguments(double polar, double azimut, double radius) {
		doCheckPolar(polar);
		doCheckAzimut(azimut);
		doCheckRadius(radius);
	}
	
	/**
	 * @methodtype get
	 */
	public Angle getPolar() {
		return this.polar;
	}
	
	/**
	 * @methodtype get
	 */
	public Angle getAzimut() {
		return this.azimut;
	}
	
	/**
	 * @methodtype get
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * @methodtype assert
	 */
	private void doCheckAzimut(double azimut) {
		checkDouble(azimut, "azimut");
		if (azimut < 0 || azimut >= 360) {
			throw new IllegalArgumentException("Phi must be in range of [0;360).");
		}
	}

	/**
	 * @methodtype assert
	 */
	private void doCheckPolar(double polar) {
		checkDouble(polar, "polar");
		if (polar < 0 || polar > 180) {
			throw new IllegalArgumentException("Theta must be in range of [0;180].");
		}
	}
	
	/**
	 * @methodtype assert
	 */
	private void doCheckRadius(double radius) {
		checkDouble(radius, "radius");
		if (radius < 0) {
			throw new IllegalArgumentException("Radius must be greater than 0.");
		}
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x,y,z; 
		x = this.getRadius() * Math.sin(this.getPolar().getAngleRad()) * Math.cos(this.getAzimut().getAngleRad());
		y = this.getRadius() * Math.sin(this.getPolar().getAngleRad()) * Math.sin(this.getAzimut().getAngleRad());
		z = this.getRadius() * Math.cos(this.getPolar().getAngleRad());
		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return new SphericCoordinate(this.getPolar().getAngleDeg(), this.getAzimut().getAngleDeg(), this.getRadius());
	}
	
}
