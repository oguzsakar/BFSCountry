package com.ogz.bfs.BFSCountry;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PathFinderService {
    @Autowired
    private  RestTemplate restTemplate;

    private List<Country> listOfCountries;

    private HashMap<String, Country> countries;

    private List<Country> getCountryArrayFromUrl() {
        String url = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";
        String jsonData = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        Country[] list = gson.fromJson(jsonData, Country[].class);
        return Arrays.asList(list);
    }

    public Route getRoute(String start, String end) {
        // make sure only fetch once from URL
        if (listOfCountries == null) {
            listOfCountries = getCountryArrayFromUrl();
        }
        checkInput(start.toUpperCase(), end.toUpperCase());
        Route route = new Route();
        route.setRoute(findPath(countries.get(start), countries.get(end)));
        return route;
    }

    private void checkInput(String start, String end) {
        if (start == null || end == null) {
            throw new UnsupportedOperationException("Missing  proper country destination input");
        }
        // convert list to 
        if (countries == null) {
            countries = (HashMap<String, Country>) listOfCountries.stream()
                    .collect(Collectors.toMap(Country::getName, Function.identity()));
        }
        if (!(countries.containsKey(start) && countries.containsKey(end))) {
            throw new UnsupportedOperationException("Wrong Input Please Try again");
        }
    }

    // Here Calculate the shortest path between 2 countries by DFS Algorithm.
    private List<String> findPath(Country startCountry, Country endCountry) {
        Queue<Country> queue = new ArrayDeque<>();
        Set<Country> visited = new HashSet<>();
        Map<Country, Country> predecessors = new HashMap<>();

        queue.offer(startCountry);
        visited.add(startCountry);

        while (!queue.isEmpty()) {
            Country currentCountry = queue.poll();
            for (String borderCode : currentCountry.getBorders()) {
                Country neighbor = countries.get(borderCode);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, currentCountry);
                    queue.offer(neighbor);
                    if (neighbor.equals(endCountry)) {
                        return constructPath(predecessors, startCountry, endCountry);
                    }
                }
            }
        }
        // If reaches here it means no route between two countries
        throw new UnsupportedOperationException("No land crossing!!");
    }

    private List<String> constructPath(Map<Country, Country> predecessors, Country startCountry, Country endCountry) {
        List<String> path = new ArrayList<>();
        Country currentCountry = endCountry;
        while (!currentCountry.equals(startCountry)) {
            path.add(0, currentCountry.getName());
            currentCountry = predecessors.get(currentCountry);
        }
        path.add(0, startCountry.getName());
        return path;
    }
}