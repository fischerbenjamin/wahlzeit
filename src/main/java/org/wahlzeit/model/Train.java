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

import java.util.Objects;
import org.wahlzeit.utils.asserts.*;

/**
 *	Representation of a train. A train objects stores following information:
 *	{@link #type}		general type of the train
 *	{@link #engine}		engine the train uses
 *	{@link #buildYear}	build year of the train
 *	{@link #serialNumber}	serial number of the train
 */
public class Train {

	private static final int DEFAULT = 1;	// Default value

	protected TrainType type;				// type of the train
	private String engine;				// type of the engine the train uses
	private int buildYear;		// the year the train was built
	private String serialNumber;	// serial number of train

	/**
	 * Public constructor.
	 * 
	 * @param type	general type of the train
	 * @param engine	engine the train uses
	 * @param buildYear	year the train was built
	 * @methodtype constructor
	 */
	public Train(TrainType type, String engine, int buildYear, String serialNumber) {
		setType(type);
		setEngine(engine);
		setBuildYear(buildYear);
		setSerialNumber(serialNumber);
	}

	/**
	 * Set method for {@link #type}.
	 *
	 * @param type
	 * @methodtype set
	 */
	public void setType(TrainType type) {
		AssertObject.assertNotNull(type, "type");
		this.type = type;
	}

	/**
	 * Set method for {@link #engine}.
	 *
	 * @param engine
	 * @methodtype set
	 */
	public void setEngine(String engine) {
		AssertString.assertStringNotNullorEmpty(engine, "engine");
		this.engine = engine;
	}

	/**
	 * Set method for {@link #buildYear}.
	 *
	 * @param buildYear
	 * @methodtype set
	 */
	public void setBuildYear(int buildYear) {
		AssertInteger.assertIntegerIsPositive(buildYear, "buildYear");
		this.buildYear = buildYear;
	}

	/**
	 * Set method for {@link #serialNumber}.
	 *
	 * @param serialNumber
	 * @methodtype set
	 */
	public void setSerialNumber(String serialNumber) {
		AssertString.assertStringNotNullorEmpty(serialNumber, "serialNumber");
		this.serialNumber = serialNumber;
	}

	/**
	 * Getter for {@link #type}.
	 * 
	 * @return type of the train
	 * @methodtype get
	 */
	public TrainType getType() {
		return this.type;
	}

	/**
	 * Getter for {@link #engine}.
	 * 
	 * @return engine of the train
	 * @methodtype get
	 */
	public String getEngine() {
		return this.engine;
	}
	
	/**
	 * Getter for {@link #buildYear}.
	 * 
	 * @return build year of the train
	 * @methodtype get
	 */
	public int getBuildYear() {
		return this.buildYear;
	}

	/**
	 * Getter for {@link #serialNumber}.
	 *
	 * @return serial number of the train
	 * @methodtype get
	 */
	public String getSerialNumber() {
		return this.serialNumber;
	}

	/**
	 * Create hash code for instance.
	 * @return hash code
	 * @methodtype get
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getType(), getEngine(), getBuildYear(), getSerialNumber());
	}

}