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

import org.wahlzeit.model.TrainPhoto.Engine;
import org.wahlzeit.model.TrainPhoto.Type;

/**
 * Factory for creating train photos.
 */
public class TrainPhotoFactory extends PhotoFactory {

	/**
	 * @methodtype factory
	 */
	public TrainPhoto createPhoto() {
		return new TrainPhoto();
	}

	/**
	 * @methodtype factory
	 */
	public TrainPhoto createPhoto(PhotoId id) {
		return new TrainPhoto(id);
	}
	
	/**
	 * @methodtype factory
	 */
	public TrainPhoto createPhoto(Type type, Engine engine, int speed, int weight, int buildYear) {
		return new TrainPhoto(type, engine, speed, weight, buildYear);
	}
	
	/**
	 * @methodtype factory
	 */
	public TrainPhoto loadPhoto(PhotoId id) {
		return null;
	}

}