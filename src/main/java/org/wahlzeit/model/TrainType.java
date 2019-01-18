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

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import org.wahlzeit.utils.asserts.*;
import org.wahlzeit.services.DataObject;

public class TrainType extends DataObject {

    public static final TrainType DEFAULT = new TrainType("DEFAULT", "DEFAULT", 1, false);

    protected int speed;                      // maximum speed
    protected String brand;                   // company that produced this type of train
    protected String model;                   // model of this type
    protected boolean isPassengerTrain;       // indicates whether this type transports persons

    protected TrainType superType;                                    // super type of this type
    protected Set<TrainType> subTypes = new HashSet<TrainType>();     // subtypes of this type


    /**
     * Public constructor.
     *
     * @param speed
     * @param brand
     * @param model
     * @param isPassengerTrain
     * @methodtype constructor
     */
    public TrainType(String brand, String model, int speed, boolean isPassengerTrain) {
        setBrand(brand);
        setModel(model);
        setSpeed(speed);
        setPassengerTrain(isPassengerTrain);
    }

    /**
     * Creates a new instance of type {@link Train} and returns it.
     *
     * @param engine       engine of the train
     * @param buildYear    year of manufacturing
     * @param serialNumber serial number
     * @return new instance of type {@link Train}
     * @methodtype factory
     */
    public Train createInstance(String engine, int buildYear, String serialNumber) {
        return new Train(this, engine, buildYear, serialNumber);
    }

    /**
     * Adds a {@link TrainType} to the list of subtypes.
     *
     * @param subType instance of {@link TrainType} to be added to the subTypes
     * @throws IllegalArgumentException if subType was null
     * @methodtype command
     */
    public void addSubType(TrainType subType) {
        AssertObject.assertNotNull(subType, "subType");
        subType.setSuperType(this);
        subTypes.add(subType);
    }

    /**
     * Checks if {@link this} is a subtype of the given train type.
     * Therefore, it recursivly compares all subtypes of the train type with {@link this}
     * via dfs and returns true if the type was found and false otherwise.
     *
     * @param trainType super type to check
     * @return true if {@link this} is subtype (recursively) of given train type
     * @methodtype boolean query
     */
    public boolean isSubtype(TrainType trainType) {
        AssertObject.assertNotNull(trainType, "train type");
        Iterator<TrainType> it = trainType.getSubTypesIterator();
        while (it.hasNext()) {
            TrainType sub = it.next();
            if (this == sub) {
                return true;
            }
            return isSubtype(sub);
        }
        return false;
    }

    /**
     * Checks if this type has the given instance.
     *
     * @param train train
     * @return true if {@code train} is instance of this type, false otherwise
     * @methodtype boolean query
     */
    public boolean hasInstance(Train train) {
        AssertObject.assertNotNull(train, "train");
        if (train.getType() == this) {
            return true;
        }
        for (TrainType type : subTypes) {
            if (type.hasInstance(train)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates hash code for this type.
     *
     * @return hash code
     * @methodtype get
     */
    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getModel(), getSpeed(), getIsPassengerTrain());
    }

    /**
     * Sets the super type.
     *
     * @throws IllegalArgumentException if trainType was null
     * @methodtype set
     */
    public void setSuperType(TrainType trainType) {
        AssertObject.assertNotNull(trainType, "trainType");
        this.superType = trainType;
    }

    /**
     * Returns an iterator for all subtypes of the current train type.
     *
     * @return iterator of {@link TrainType}
     * @methodtype get
     */
    public Iterator<TrainType> getSubTypesIterator() {
        return subTypes.iterator();
    }

    /**
     * Returns the super type.
     *
     * @return super type
     * @methodtype get
     */
    public TrainType getSuperType() {
        return superType;
    }

    /**
     * Set method for {@link #speed}
     *
     * @methodtype set
     */
    public void setSpeed(int speed) {
        AssertInteger.assertIntegerIsPositive(speed, "speed");
        this.speed = speed;
    }

    /**
     * Set method for {@link #brand}
     *
     * @methodtype set
     */
    public void setBrand(String brand) {
        AssertString.assertStringNotNullorEmpty(brand, "brand");
        this.brand = brand;
    }

    /**
     * Set method for {@link #model}
     *
     * @methodtype set
     */
    public void setModel(String model) {
        AssertString.assertStringNotNullorEmpty(model, "model");
        this.model = model;
    }

    /**
     * Set method for {@link #isPassengerTrain}
     *
     * @methodtype set
     */
    public void setPassengerTrain(boolean passengerTrain) {
        isPassengerTrain = passengerTrain;
    }

    /**
     * Get method for {@link #speed}
     *
     * @return speed
     * @methodtype get
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Get method for {@link #brand}
     *
     * @return brand
     * @methodtype get
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get method for {@link #model}
     *
     * @return model
     * @methodtype get
     */
    public String getModel() {
        return model;
    }

    /**
     * Get method for {@link #isPassengerTrain}
     *
     * @return isPassengerTrain
     * @methodtype get
     */
    public boolean getIsPassengerTrain() {
        return isPassengerTrain;
    }

}