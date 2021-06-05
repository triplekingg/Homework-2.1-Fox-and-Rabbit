package io.muic.ooc.fab;

import java.util.Iterator;
import java.util.List;

public class Tiger extends Animal {
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.


    // The Tiger's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a fox. A fox can be created as a new born (age zero and not
     * hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge, field, location);
        foodLevel = RANDOM.nextInt(getFoxFoodValue());
    }

    /**
     * This is what the fox does most of the time: it hunts for rabbits. In the
     * process, it might breed, die of hunger, or die of old age.
     *
     * @param //field The field currently occupied.
     * @param animals A list to return newly born foxes.
     */

    @Override
    public void act(List<Animal> animals) {
        incrementAge();
        incrementHunger();
        if (isAlive()) {
            giveBirth(animals);
            // Move towards a source of food if found.
            Location newLocation = moveToNewLocation();
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Look for rabbits adjacent to the current location. Only the first live
     * rabbit is eaten.
     *
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = getRabbitFoodValue();
                    return where;
                }
            }
            else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = getFoxFoodValue();
                    return where;
                }
            }
        }
        return null;
    }

    @Override
    public int getMaxAge() {
        return 300;
    }

    @Override
    protected double getBreedingProbability() {
        return 0.07;
    }

    @Override
    protected int getMaxLitterSize() {
        return 1;
    }

    @Override
    protected int getBreedingAge() {
        return 30;
    }

    @Override
    protected int getRabbitFoodValue() {
        return 12;
    }

    @Override
    protected int getFoxFoodValue() {
        return 5;
    }

    @Override
    protected int getTigerFoodValue() {
        return 0;
    }

    @Override
    protected Animal breedOne(boolean randomAge, Field field, Location location) {
        return AnimalFactory.createAnimal(getClass(),field,location);
    }

    @Override
    protected Location moveToNewLocation() {
        Location newLocation = findFood();
        if (newLocation == null) {
            // No food found - try to move to a free location.
            newLocation = field.freeAdjacentLocation(location);
        }
        return newLocation;
    }
}
