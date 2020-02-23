package com.veezy.moviecatalogservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.veezy.moviecatalogservice.model.CatalogItem;
import com.veezy.moviecatalogservice.model.Movie;
import com.veezy.moviecatalogservice.model.Rating;
import com.veezy.moviecatalogservice.model.UserRating;
import com.veezy.moviecatalogservice.service.MovieInfoService;
import com.veezy.moviecatalogservice.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private UserRatingService userRatingService;

    @Autowired
    private MovieInfoService movieInfoService;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingService.getUserRating(userId);

        return userRating.getRatings().stream()
                .map(movieInfoService::getCatalogItem)
                .collect(Collectors.toList());

    }

}
