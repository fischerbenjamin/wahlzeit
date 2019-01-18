/*
 * Copyright (c) 2019 by Benjamin Fischer
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

import java.util.HashMap;
import org.wahlzeit.utils.PatternInstance;

/**
 *  Manager class for {@link Train} and {@link TrainType}.
 */
@PatternInstance(
        patternName = "Singleton",
        participants = {
                "Singleton@class.TrainManager"
        }
)
public class TrainManager {

    // Stores all {@link Train} instances which have been created
    private HashMap<Integer, Train> allTrains = new HashMap<Integer, Train>();
    // Stores all {@link TrainType} instances which have been created
    private HashMap<Integer, TrainType> allTypes = new HashMap<Integer, TrainType>();
    // Singleton instance
    protected static final TrainManager instance = new TrainManager();

    /**
     * Return the singleton instance.
     *
     * @return  singleton
     * @methodtype get
     */
    public static TrainManager getInstance() {
        return instance;
    }

    /**
     * Returns the corresponding {@link TrainType} for given parameters.
     * Therefore, it checks if this type has already been created and returns this
     * instance (if so). Otherwise, a new object is created and stored in a map.
     *
     * @param brand company that created the train
     * @param model train type model
     * @param speed maximum speed this type of train is able to drive
     * @param isPassengerTrain indicates if this type of train is able to transport passengers
     * @return instance
     * @methodtype get
     */
    public TrainType getTrainType(String brand, String model, int speed, boolean isPassengerTrain) {
        // Assertions are checked in constructor call
        TrainType trainType = new TrainType(brand, model, speed, isPassengerTrain);
        if (allTypes.containsKey(trainType.hashCode())) {
            return allTypes.get(trainType.hashCode());
        }
        allTypes.put(trainType.hashCode(), trainType);
        return trainType;
    }

    /**
     * Returns the corresponding {@link TrainType} for given parameters.
     * Therefore, it checks if this type has already been created and returns this
     * instance (if so). Otherwise, null is returned.
     *
     * @param type
     * @param engine
     * @param buildYear
     * @param serialNumber serial number of train
     * @return instance of {@link Train} or null
     */
    public Train getTrain(TrainType type, String engine, int buildYear, String serialNumber) {
        // Assertions are checked in constructor call
        Train train = new Train(type, engine, buildYear, serialNumber);
        if (allTrains.containsKey(train.hashCode())) {
            return allTrains.get(train.hashCode());
        }
        return null;
    }

    /**
     * Creates a new {@link Train} object and updates the internal structure.
     *
     * @param brand company that created this type of train
     * @param model train type model
     * @param speed maximum speed this type of train is able to drive
     * @param isPassengerTrain  indicates if this type of train is able to transport passengers
     * @param engine    type of engine
     * @param buildYear year of manufacturing
     * @param serialNumber serial number of train
     * @return  new instance of {@link Train}
     * @methodtype factory
     */
    public Train createTrain(String brand, String model, int speed, boolean isPassengerTrain, String engine,
                                int buildYear, String serialNumber)
    {
        TrainType trainType = getTrainType(brand, model, speed, isPassengerTrain);
        Train train = trainType.createInstance(engine, buildYear, serialNumber);
        allTrains.put(train.hashCode(), train);
        allTypes.put(trainType.hashCode(), trainType);
        return train;
    }

}