package com.veezy.moviecatalogservice.controller;

import com.veezy.moviecatalogservice.model.CatalogItem;
import com.veezy.moviecatalogservice.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<Rating> ratingsList = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );

        return ratingsList.stream()
                .map(rating -> new CatalogItem("Name", "Desc", rating.getRating()))
                .collect(Collectors.toList());

    }
}
