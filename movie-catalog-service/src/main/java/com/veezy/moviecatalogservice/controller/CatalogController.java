package com.veezy.moviecatalogservice.controller;

import com.veezy.moviecatalogservice.model.CatalogItem;
import com.veezy.moviecatalogservice.model.Movie;
import com.veezy.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<Rating> ratingsList = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );

        return ratingsList.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
