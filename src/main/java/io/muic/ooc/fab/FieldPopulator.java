package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FieldPopulator {

    private Map<AnimalType, Double> probabilityMap = new HashMap<AnimalType, Double>(){{
        AnimalType[] animalTypes = AnimalType.values();
        for(int i = 0; i < animalTypes.length; i++){
            if(!animalTypes[i].equals(AnimalType.HUNTER)){
            put(animalTypes[i], animalTypes[i].getBreedingProbability());
            }
        }
    }};
    private static final Random RANDOM = new Random();

    /**
     * Randomly populate the field with foxes,rabbits and tigers.
     */

    public void insertHunter(Field field, List<Animal> animals) {
        field.clear();
        for (int row = 0; row < field.getDepth()/10; row++) {
            for (int col = 0; col < field.getWidth()/10; col++) {
                Location location = new Location(row, col);
                Animal animal = AnimalFactory.createAnimal(AnimalType.HUNTER, field, location);
                animals.add(animal);
            }
        }
        for (int row = field.getDepth()-1; row > 76; row--) {
            for (int col = field.getWidth()-1; col > 76; col--) {
                Location location = new Location(row, col);
                Animal animal = AnimalFactory.createAnimal(AnimalType.HUNTER, field, location);
                animals.add(animal);
            }
        }
        for (int row = field.getDepth()/2; row > 37; row--) {
            for (int col = field.getWidth()/2; col > 37; col--) {
                Location location = new Location(row, col);
                Animal animal = AnimalFactory.createAnimal(AnimalType.HUNTER, field, location);
                animals.add(animal);
            }
        }

    }

    public void populate(Field field, List<Animal> animals) {
        field.clear();
        for (int row = field.getDepth()/10; row < field.getDepth(); row++) {
            for (int col = field.getWidth()/10; col < field.getWidth(); col++) {
                for(Map.Entry<AnimalType,Double> entry : probabilityMap.entrySet()){
                    if(RANDOM.nextDouble() <= entry.getValue()){
                        Location location = new Location(row, col);
                        Animal animal = AnimalFactory.createAnimal(entry.getKey(), field, location);
                        animals.add(animal);
                        break;
                    }
                }
            }
        }
    }
}
