package com.ogz.bfs.BFSCountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PathFinderController {
    @Autowired
    private PathFinderService pathFinderService;

    @GetMapping("/findPath/{start}/{end}")
    public ResponseEntity<Route> getPath(@PathVariable String start, @PathVariable String end) {
        return ResponseEntity.ok(pathFinderService.getRoute(start, end));
    }
}
