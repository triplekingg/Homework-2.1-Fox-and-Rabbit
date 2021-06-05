package io.muic.ooc.fab;

import java.util.List;

public class Rabbit extends Animal {

    /**
     * Create a new rabbit. A rabbit may be created with age zero (a new born)
     * or with a random age.
     *
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */

    /**
     * This is what the rabbit does most of the time - it runs around. Sometimes
     * it will breed or die of old age.
     *
     * @param animals A list to return newly born rabbits.
     */
    @Override
    public void act(List<Animal> animals) {
        incrementAge();
        if (isAlive()) {
            giveBirth(animals);
            // Try to move into a free location.
            Location newLocation = moveToNewLocation();
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    @Override
    protected double getBreedingProbability() {
        return 0.12;
    }

    @Override
    protected int getMaxLitterSize() {
        return 4;
    }

    @Override
    public int getMaxAge() {
        return 40;
    }

    @Override
    protected int getBreedingAge() {
        return 5;
    }

    @Override
    protected int getRabbitFoodValue() {
        return 0;
    }

    @Override
    protected int getFoxFoodValue() {
        return 0;
    }

    @Override
    protected Animal breedOne(boolean randomAge, Field field, Location location) {
        return AnimalFactory.createAnimal(this.getClass(),field,location);
    }

    @Override
    protected Location moveToNewLocation() {
        return field.freeAdjacentLocation(getLocation());
    }
}
