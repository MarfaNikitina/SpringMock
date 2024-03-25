package com.example.springtest.controller;

import com.example.springtest.logic.Pet;
import com.example.springtest.logic.PetModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private boolean isFirst = true;
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        if(isFirst){
            isFirst= false;
            return "Поздравляем, вы создали своего первого питомца";
        }
        return "Поздравляем, вы создали своего питомца";
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAl() {
        return petModel.getAll();
    }

    /*
    {
        "id": 3
    }
     */
    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList((id.get("id")));
    }

    /*
  {
      "id": 1
  }
   */
    @DeleteMapping(value = "/deletePet", consumes = "application/json")
    public String deletePet(@RequestBody Map<String, Integer> id) {
        if (id.get("id") > petModel.getAll().size()) {
            return "Нет питомца с id:" + id;
        }
        petModel.delete(id.get("id"));
        return "Питомец с id:" + id + " удален";
    }


    /*
    {
        1: {
        "name": "Sue",
        "type": "cat",
        "age": 4
    }
*/
    @PutMapping(value = "/changePet", consumes = "application/json")
    public void editPet(@RequestParam("id") int id, @RequestBody Pet pet) {
        if (id > petModel.getAll().size()) {
            return;
        }
        petModel.put(id, pet);
    }
}
