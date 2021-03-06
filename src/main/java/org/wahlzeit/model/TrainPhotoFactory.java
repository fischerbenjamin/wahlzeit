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
 * Factory for creating train photos of type {@link TrainPhoto}.
 */
public class TrainPhotoFactory extends PhotoFactory {

	/**
	 * Creates a new instance of type {@link TrainPhoto}.
	 * 
	 * @return new photo of a train
	 * @methodtype factory
	 */
	@Override
	public TrainPhoto createPhoto() {
		return new TrainPhoto();
	}

	/**
	 * Creates a new instance of type {@link TrainPhoto} with a specified ID.
	 * 
	 * @return new photo of a train with certain ID
	 * @methodtype factory
	 */
	@Override
	public TrainPhoto createPhoto(PhotoId id) {
		return new TrainPhoto(id);
	}
	
	/**
	 * Creates a new instance of type {@link TrainPhoto} with given values.
	 * 
	 * @return new photo of a specific train
	 * @methodtype factory
	 */
	public TrainPhoto createPhoto(Train train) {
		return new TrainPhoto(train);
	}
	
	/**
	 * Loads a photo of a train.
	 * 
	 * @return loaded photo
	 * @methodtype factory
	 */
	@Override
	public TrainPhoto loadPhoto(PhotoId id) {
		return null;
	}

}