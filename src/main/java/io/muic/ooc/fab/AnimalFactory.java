package io.muic.ooc.fab;

public class AnimalFactory {


    public Animal createAnimal(AnimalType animalType, Field field, Location location){
        if(animalType.equals(AnimalType.RABBIT)){
            return new Rabbit(true,field,location);
        }
        else if(animalType.equals(AnimalType.FOX)){
            return new Fox(true,field,location);
        }
        else{
            throw new IllegalArgumentException("Unknown animaltype")
        }
    }
}
