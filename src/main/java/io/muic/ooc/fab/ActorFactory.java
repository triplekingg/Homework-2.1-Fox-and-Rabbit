package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;

public class ActorFactory {

    private static Map<ActorType, Class> animalClassMap = new HashMap<ActorType, Class>(){{
        ActorType[] actorTypes = ActorType.values();
        for(int i = 0; i < actorTypes.length; i++){
            put(actorTypes[i], actorTypes[i].getAnimalClass());
        }
    }};


    public static Animal createAnimal(ActorType actorType, Field field, Location location){
        Class animalClass = animalClassMap.get(actorType);
        return createAnimal(animalClass,field,location);
    }

    public static Animal createAnimal(Class animalClass, Field field, Location location){
        if(animalClass != null){
            try {
                Animal animal = (Animal) animalClass.newInstance();
                animal.initialize(true,field,location);
                return animal;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        throw new IllegalArgumentException("Unknown Animal");
    }
}
