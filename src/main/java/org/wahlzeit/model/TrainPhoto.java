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
 * It is characterized by some properties:
 * 	{@link #type}			type of the train
 * 	{@link #engine}			type of the engine the train uses
 * 	{@link #speed}			maximum speed the train can reach
 * 	{@link #weight}			weight of the train
 * 	{@link #buildYear}		the year the train was built
 * All fields are declared as final.
 */
public class TrainPhoto extends Photo {

	/**
	 * Default value (used for default constructors).
	 */
	private static final int DEFAULT = 1;
	
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
	
	private Type type;			// type of the train
	private Engine engine;		// type of the engine the train uses
	private int speed;			// maximum speed the train can reach
	private int weight;			// weight of the train
	private int buildYear;		// the year the train was built

	/**
	 * Default constructor.
	 * 
	 * @methodtype constructor
	 */
	public TrainPhoto() {
		this.setType(Type.UNKNOWN);
		this.setEngine(Engine.UNKNOWN);
		this.setSpeed(TrainPhoto.DEFAULT);
		this.setWeight(TrainPhoto.DEFAULT);
		this.setBuildYear(TrainPhoto.DEFAULT);
	}
	
	/**
	 * Default constructor with specified ID.
	 * 
	 * @methodtype constructor
	 */
	public TrainPhoto(PhotoId myID) {
		super(myID);
		this.setType(Type.UNKNOWN);
		this.setEngine(Engine.UNKNOWN);
		this.setSpeed(TrainPhoto.DEFAULT);
		this.setWeight(TrainPhoto.DEFAULT);
		this.setBuildYear(TrainPhoto.DEFAULT);
	}

	/**
	 * Creates a new instance of type {@link TrainPhoto} with given values.
	 * 
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
	 * Returns the type {@link #type} of a train. 
	 * 
	 * @methodtype get
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * Returns the engine {@link #engine} of a train. 
	 * 
	 * @methodtype get
	 */
	public Engine getEngine() {
		return this.engine;
	}
	
	/**
	 * Returns the speed {@link #speed} of a train. 
	 * 
	 * @methodtype get
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * Returns the weight {@link #weight} of a train. 
	 * 
	 * @methodtype get
	 */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Returns the year the train was built {@link #buildYear}. 
	 * 
	 * @methodtype get
	 */
	public int getBuildYear() {
		return this.buildYear;
	}
	
	/**
	 * Sets the type of a train {@link #type} to the given value.
	 * 
	 * @methodtype set
	 */
	public void setType(Type type) {
		//@PRE
		assert(type != null);
		//@PRE
		this.type = type;
	}

	/**
	 * Sets the engine of a train {@link #engine} to the given value.
	 * 
	 * @methodtype set
	 */
	public void setEngine(Engine engine) {
		//@PRE
		assert(engine != null);
		//@PRE
		this.engine = engine;
	}
	
	/**
	 * Sets the speed of a train {@link #speed} to the given value.
	 * 
	 * @methodtype set
	 */
	public void setSpeed(int speed) {
		//@PRE
		assert(speed > 0);
		//@PRE
		this.speed = speed;
	}
	
	/**
	 * Sets the weight of a train {@link #weight} to the given value.
	 * 
	 * @methodtype set
	 */
	public void setWeight(int weight) {
		//@PRE
		assert(weight > 0);
		//@PRE
		this.weight = weight;
	}
	
	/**
	 * Sets the year the train was built {@link #buildYear} to the given value.
	 * 
	 * @methodtype set
	 */
	public void setBuildYear(int buildYear) {
		//@PRE
		assert(buildYear > 0);
		//@PRE
		this.buildYear = buildYear;
	}

}