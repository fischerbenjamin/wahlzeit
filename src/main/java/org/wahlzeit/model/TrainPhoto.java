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
 * A TrainPhoto represents an uploaded photo of a train by the user.
 */

public class TrainPhoto extends Photo {

	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = -6467518261973525569L;

	/**
	 * Enum type for the type of a train.
	 */
	enum Type {
		SBAHN, REGIO, ICE, UNKNOWN
	}
	
	/**
	 * Enum type for the type of the engine of a train.
	 */
	enum Engine {
		ELECTRO, DIESEL, COAL, UNKNOWN
	}
	
	/**
	 * Properties of a train.
	 */
	private Type type;
	private Engine engine;
	private int speed;
	private int weight;
	private int buildYear;
	
	/**
	 * @methodtype constructor
	 */
	public TrainPhoto() {
		super();
		this.setType(Type.UNKNOWN);
		this.setEngine(Engine.UNKNOWN);
	}
	
	/**
	 * @methodtype constructor
	 */
	public TrainPhoto(PhotoId myID) {
		super(myID);
		this.setType(Type.UNKNOWN);
		this.setEngine(Engine.UNKNOWN);
	}
	
	/**
	 * @methodtype constructor
	 */
	public TrainPhoto(Type type, Engine engine, int speed, int weight, int buildYear) {
		super();
		this.setType(type);
		this.setEngine(engine);
		this.setSpeed(speed);
		this.setWeight(weight);
		this.setBuildYear(buildYear);
	}
	
	/**
	 * @methodtype get
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * @methodtype set
	 */
	public void setType(Type type) {
		checkType(type);
		this.type = type;
	}
	
	/**
	 * @methodtype get
	 */
	public Engine getEngine() {
		return this.engine;
	}

	/**
	 * @methodtype set
	 */
	public void setEngine(Engine engine) {
		checkEngine(engine);
		this.engine = engine;
	}
	
	/**
	 * @methodtype get
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * @methodtype set
	 */
	public void setSpeed(int speed) {
		checkPositive(speed);
		this.speed = speed;
	}
	
	/**
	 * @methodtype get
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * @methodtype set
	 */
	public void setWeight(int weight) {
		checkPositive(weight);
		this.weight = weight;
	}
	
	/**
	 * @methodtype get
	 */
	public int getBuildYear() {
		return this.buildYear;
	}
	
	/**
	 * @methodtype set
	 */
	public void setBuildYear(int buildYear) {
		checkPositive(buildYear);
		this.buildYear = buildYear;
	}
	
	/**
	 * @methodtype assertion
	 */
	private void checkType(Type type) {
		if (type == null) {
			throw new IllegalArgumentException("The type of the train must not be null.");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void checkEngine(Engine engine) {
		if (engine == null) {
			throw new IllegalArgumentException("The engine of the train must not be null.");
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void checkPositive(int x) {
		if (x <= 0) {
			throw new IllegalArgumentException("Value must be positive.");
		}
	}
	
}
