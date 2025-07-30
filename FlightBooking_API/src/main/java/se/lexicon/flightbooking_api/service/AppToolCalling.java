package se.lexicon.flightbooking_api.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class AppToolCalling {

    List<String> storage = new ArrayList<>();

    public AppToolCalling() {
        storage.addAll(Arrays.asList("Boti", "Faszomat a peldakba"));
    }


    @Tool(description = "Fetches all Names from the application")
    public List<String> fetchAllNames() {
        return storage.stream().toList();
    }

    @Tool
    public String addNewName(String name) {
        storage.add(name);
        return name;
    }

    @Tool
    public String findByName(String name) {
        List<String> foundNames = storage.stream()
                .filter(n -> n.toLowerCase().contains(n.toLowerCase()))
                .toList();

        if (foundNames.isEmpty()) {
           return "No name found";
        }

        return "Found names: "  + String.join(", ", foundNames);
    }
}
