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
 *	Representation of a train. A train objects stores following information:
 *	{@link #type}		general type of the train
 *	{@link #engine}		engine the train uses
 *	{@link #speed}		speed of the train
 *	{@link #weight}		weight of the train
 *	{@link #buildYear}	build year of the train
 *	All fields are declared as final.
 */
public class Train {
	
	/**
	 * Default value.
	 */
	public static final int DEFAULT = 1;
	
	/**
	 * General type of a train.
	 */
	enum Type {
		SBAHN, REGIO, ICE, UNKNOWN
	}
	
	/**
	 * Engine a train uses.
	 */
	enum Engine {
		ELECTRO, DIESEL, COAL, UNKNOWN
	}
	
	private final Type type;			// type of the train
	private final Engine engine;		// type of the engine the train uses
	private final int speed;			// maximum speed the train can reach
	private final int weight;			// weight of the train
	private final int buildYear;		// the year the train was built
	
	/**
	 * Default constructor.
	 * 
	 * @return new {@link Train}
	 * @methodtype constructor
	 */
	public Train() {
		this.type = Type.UNKNOWN;
		this.engine = Engine.UNKNOWN;
		this.speed = DEFAULT;
		this.weight = DEFAULT;
		this.buildYear = DEFAULT;
	}
	
	/**
	 * Public constructor.
	 * 
	 * @param type	general type of the train
	 * @param engine	engine the train uses
	 * @param speed	speed of the train
	 * @param weight	weight of the train
	 * @param buildYear	year the train was built
	 * @return new {@link Train}
	 * @methodtype constructor
	 */
	public Train(Type type, Engine engine, int speed, int weight, int buildYear) {
		//@PRE
		assertType(type);
		assertEngine(engine);
		assertSpeed(speed);
		assertWeight(weight);
		assertBuildYear(buildYear);
		//@PRE
		this.type = type;
		this.engine = engine;
		this.speed = speed;
		this.weight = weight;
		this.buildYear = buildYear;
	}
	
	/**
	 * Assert method for {@link #type}.
	 * 
	 * @param type	general type of the train
	 * @throws IllegalArgumentException	if type is null
	 * @methodtype assert
	 */
	private void assertType(Type type) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException("Type must not be null.");
		}
	}
	
	/**
	 * Assert method for {@link #engine}.
	 * 
	 * @param engine	engine the train uses
	 * @throws IllegalArgumentException	if engine is null
	 * @methodtype assert
	 */
	private void assertEngine(Engine engine) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException("Engine must not be null.");
		}
	}

	/**
	 * Assert method for {@link #speed}.
	 * 
	 * @param speed	speed of the train
	 * @throws IllegalArgumentException	if speed is negative or zero
	 * @methodtype assert
	 */
	private void assertSpeed(int speed) throws IllegalArgumentException {
		if (speed <= 0) {
			throw new IllegalArgumentException("Speed must not be positive.");
		}
	}
	
	/**
	 * Assert method for {@link #weight}.
	 * 
	 * @param weight	weight of the train
	 * @throws IllegalArgumentException	if weight is negative or zero
	 * @methodtype assert
	 */
	private void assertWeight(int weight) throws IllegalArgumentException {
		if (weight <= 0.0) {
			throw new IllegalArgumentException("Weight must be positive.");
		}
	}
	
	/**
	 * Assert method for {@link #buildYear}.
	 * 
	 * @param builYear	build year of the train
	 * @throws IllegalArgumentException	if build year is negative
	 * @methodtype assert
	 */
	private void assertBuildYear(int buildYear) throws IllegalArgumentException {
		if (buildYear < 0) {
			throw new IllegalArgumentException("Build year must be positive.");
		}
	}
	
	/**
	 * Getter for {@link #type}.
	 * 
	 * @return general type of the train
	 * @methodtype get
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * Getter for {@link #engine}.
	 * 
	 * @return engine of the train
	 * @methodtype get
	 */
	public Engine getEngine() {
		return this.engine;
	}
	
	/**
	 * Getter for {@link #speed}.
	 * 
	 * @return speed of the train
	 * @methodtype get
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * Getter for {@link #weight}.
	 * 
	 * @return weight of the train
	 * @methodtype get
	 */
	public int getWeight() {
		return this.weight;
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
	
	
	
}
