package com.example.test.controller;

import com.example.test.domain.Restaurant;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestaurantController {
    Restaurant restaurant1 = new Restaurant("스시로", 198, 3.8, true);
    Restaurant restaurant2 = new Restaurant("위자네", 156, 4.2, true);
    Restaurant restaurant3 = new Restaurant("Mung", 325, 4.9, false);
    List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    Map<Integer, Restaurant> restaurantMap = new HashMap<>();

    @GetMapping("/restaurants")
    public String restaurant(Model model) {
        restaurantMap.put(1, restaurant1);
        restaurantMap.put(2, restaurant2);
        restaurantMap.put(3, restaurant3);

        model.addAttribute("entry", restaurantMap.entrySet());
        return "restaurants";
    }

    @GetMapping("/restaurants/{id}")
    public String restaurantDetail(
            @PathVariable("id") int id,
            Model model,
            HttpServletResponse response
    ) {
        restaurantMap.put(1, restaurant1);
        restaurantMap.put(2, restaurant2);
        restaurantMap.put(3, restaurant3);

        Restaurant restaurant = restaurantMap.get(id);
        if (restaurant == null) {
            System.out.println("===========> null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("restaurantNotFound", "맛집을 찾지 못했습니다.");
            return "restaurant-not-found";
        }

        model.addAttribute("id", id);
        model.addAttribute("restaurant", restaurant);
        return "restaurant-detail";
    }

    @GetMapping("/restaurants/{id}/reviews/new")
    public String showRestaurantReview(
            @PathVariable("id") int id,
            Model model
    ) {
        String storeName = restaurantMap.get(id).getStoreName();
        model.addAttribute("id", id);
        model.addAttribute("storeName", storeName);
        return "/restaurant-review-form";
    }

    @PostMapping("/restaurants/{id}/reviews/new")
    public String submitRestaurantReview(
            @PathVariable("id") int id,
            @RequestParam("restaurantName") String restaurantName,
            @RequestParam("restaurantRating") double restaurantRating,
            @RequestParam("restaurantComment") String restaurantComment,
            Model model
    ) {
        model.addAttribute("id", id);
        model.addAttribute("restaurantName", restaurantName);
        model.addAttribute("restaurantRating", restaurantRating);
        model.addAttribute("restaurantComment", restaurantComment);

        return "/restaurant-review-form";
    }
}
