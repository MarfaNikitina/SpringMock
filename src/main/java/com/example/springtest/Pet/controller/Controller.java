package com.example.springtest.Pet.controller;

import com.example.springtest.Pet.logic.Pet;
import com.example.springtest.Pet.logic.PetModel;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private boolean isFirst = true;
    private static final AtomicInteger newId = new AtomicInteger(1);


    /*
    {
"name": "Tom",
"type": "cat",
"age": 7
}
     */
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
    @GetMapping(value = "/getPet", produces = "application/json")
    public Pet getPet(@RequestParam("id") int id) {
        return petModel.getFromList(id);
    }

    /*
http://localhost:8989/deletePet?id=1
   */
    @DeleteMapping(value = "/deletePet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deletePet(@RequestParam("id") int id) {
        if (id > petModel.getAll().size()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(JSONObject.quote("Нет питомца c id: " + id));
        }
        petModel.delete(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(JSONObject.quote("Питомец с id: " + id + " успешно удален"));
    }


    /*
    http://localhost:8989/changePet?id=1
     body:
     {
        "name": "Sue",
        "type": "cat",
        "age": 4
    }
*/
    @PutMapping(value = "/changePet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> editPet(@RequestParam("id") int id, @RequestBody Pet pet) {
        if (id > petModel.getAll().size()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(JSONObject.quote("Нет питомца c id: " + id));
        }
        petModel.put(id, pet);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(JSONObject.quote("Питомец с id: " + id + " успешно изменен"));
    }
}
