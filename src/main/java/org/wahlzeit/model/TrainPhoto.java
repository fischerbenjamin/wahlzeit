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
 * It stores information about a train using a {@link Train} instance.
 */  
public class TrainPhoto extends Photo {
	
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = -6467518261973525569L;

	private final Train train;

	/**
	 * Default constructor.
	 * 
	 * @methodtype constructor
	 */
	public TrainPhoto() {
		this.train = new Train();
	}
	
	/**
	 * Default constructor with specified ID.
	 * 
	 * @methodtype constructor
	 */
	public TrainPhoto(PhotoId myID) {
		super(myID);
		this.train = new Train();
	}

	/**
	 * Creates a new instance of type {@link TrainPhoto} with given values.
	 * 
	 * @methodtype constructor
	 */
	public TrainPhoto(Train train) {
		super();
		assertTrainNotNull(train);
		this.train = train;
	}
	
	/**
	 * Getter of {@link #train}.
	 * 
	 * @return train of the photo
	 * @methodtype get
	 */
	public Train getTrain() {
		return this.train;
	}
	
	/**
	 * Asserts that a given train object is not null.
	 * 
	 * @param train train of the photo
	 * @throws IllegalArgumentException	if train is null
	 * @methodtype assert
	 */
	private static void assertTrainNotNull(Train train) throws IllegalArgumentException {
		if (train == null) {
			throw new IllegalArgumentException("Train must not be null");
		}
	}

}