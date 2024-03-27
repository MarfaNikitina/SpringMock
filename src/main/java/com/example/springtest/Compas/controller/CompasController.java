package com.example.springtest.Compas.controller;

import com.example.springtest.Compas.logic.Compas;
import com.example.springtest.Compas.logic.WorldSideRange;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CompasController {
    private static Compas compas = null;

    /*
{
    "North": "341-45",
        "North-East": "46-90",
        "East": "91-135",
        "South-East": "136-180",
        "South": "181-225",
        "South-West": "226-270",
        "West": "271-315",
        "North-West": "316-340"
}
*/
    @PostMapping(value="/add-info", consumes = "application/json")
    public String addInfo(@RequestBody Map<String, String> input) {
        WorldSideRange[] sideRanges = new WorldSideRange[8];
        int i = 0;
        for (Map.Entry<String, String> pair: input.entrySet()) {
            String name = pair.getKey();
            int startDegree = Integer.parseInt(pair.getValue().split("-")[0]);
            int endDegree= Integer.parseInt(pair.getValue().split("-")[1]);
            WorldSideRange side = new WorldSideRange(name, startDegree, endDegree);
            sideRanges[i] = side;
            i++;
        }
        compas = new Compas(sideRanges);
        return "Information about ranges has been saved";
    }

    @GetMapping(value="/get-info", consumes = "application/json", produces = "application/json")
    public String getInfo(@RequestParam("degree") int degree) {
        String side = compas.getSide(degree);
        return "{\"Side\" : \"" + side + "\"}";
    }
}
