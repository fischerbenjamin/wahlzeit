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
 * A class that represents an angle.
 */
public class Angle {

	private final double angleDeg;
	private final double angleRad;
	
	/**
	 * @methodtype constructor
	 * 
	 * Creates an angle object from given angle in degrees of the interval [0;360).
	 */
	public Angle(double angle) {
		if ((angle == Double.NaN) || (angle < 0) || (angle >= 360)) {
			throw new IllegalArgumentException("Angle must be in the interval [0;360)");
		}
		this.angleDeg = angle;
		this.angleRad = Math.toRadians(angle);
	}
	
	/**
	 * @methodtype get
	 */
	public double getAngleDeg() {
		return this.angleDeg;
	}

	/**
	 * @methodtype get
	 */
	public double getAngleRad() {
		return this.angleRad;
	}
	
	/**
	 * @methodtype conversion
	 */
	public static final double convertDegToValidDeg(double deg) {
		AbstractCoordinate.checkDouble(deg, "deg");
		while(deg < 360) {
			deg += 360;
		}
		return deg % 360;
	}
	
	/**
	 * @methodtype boolean query
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Angle)) {
			return false;
		}
		Angle other = (Angle) obj;
		if (Double.compare(this.getAngleDeg(), other.angleDeg) == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @methodtype conversion
	 */
	public double convertPolarToLatitudeRad() {
		return Math.toRadians(this.getAngleDeg() - 90.0);
	}
	
	/**
	 * @methodtype conversion
	 */
	public double convertAzimutToLongitudeRad() {
		return this.getAngleRad();
	}
	
}
