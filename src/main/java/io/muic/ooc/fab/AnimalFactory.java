package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    private static Map<AnimalType, Class> animalClassMap = new HashMap<AnimalType, Class>(){{
        AnimalType[] animalTypes = AnimalType.values();
        for(int i = 0; i < animalTypes.length; i++){
            put(animalTypes[i], animalTypes[i].getAnimalClass());
        }
    }};


    public static Animal createAnimal(AnimalType animalType, Field field, Location location){
        Class animalClass = animalClassMap.get(animalType);
        switch (animalType){
            case RABBIT:
                return new Rabbit(true,field,location);
            case FOX:
                return new Fox(true,field,location);
            default:
                throw new IllegalArgumentException("Unknown animalType");
        }
    }
}
