package com.example.test.controller;

import com.example.test.domain.Restaurant;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RestaurantController {
    Restaurant restaurant1 = new Restaurant("스시로", 198, 3.8, true);
    Restaurant restaurant2 = new Restaurant("위자네", 156, 4.2, true);
    Restaurant restaurant3 = new Restaurant("Mung", 325, 4.9, false);

    Map<Integer, Restaurant> restaurantMap = Map.of(
            1, restaurant1,
            2, restaurant2,
            3, restaurant3
    );


    @GetMapping("/restaurants")
    public String restaurant(Model model) {
        model.addAttribute("entry", restaurantMap.entrySet());
        return "restaurants";
    }

    @GetMapping("/restaurants/{id}")
    public String restaurantDetail(
            @PathVariable("id") int id,
            Model model,
            HttpServletResponse response
    ) {
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
            Model model,
            HttpServletResponse response
    ) {
        Restaurant restaurant = restaurantMap.get(id);
        if (restaurant == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("restaurantNotFound", "맛집을 찾지 못했습니다.");
            return "restaurant-not-found";
        }
        model.addAttribute("id", id);
        model.addAttribute("storeName", restaurant.getStoreName());
        return "/restaurant-review-form";
    }

    @PostMapping("/restaurants/{id}/reviews/new")
    public String submitRestaurantReview(
            @PathVariable("id") int id,
            @RequestParam("restaurantRating") String restaurantRating,
            @RequestParam("restaurantComment") String restaurantComment,
            Model model,
            HttpServletResponse response
    ) {
        Restaurant restaurant = restaurantMap.get(id);
        if (restaurant == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("restaurantNotFound", "맛집을 찾지 못했습니다.");
            return "restaurant-not-found";
        }

        model.addAttribute("id", id);
        model.addAttribute("storeName", restaurant.getStoreName());

        model.addAttribute("draftRating", restaurantRating);
        model.addAttribute("draftComment", restaurantComment);

        if (restaurantRating.isBlank()) {
            model.addAttribute("errorMessage", "평점을 입력해 주세요.");
            return "restaurant-review-form";
        }

        Double doubleRating;
        try {
            doubleRating = Double.parseDouble(restaurantRating);

            if (!Double.isFinite(doubleRating) || doubleRating < 0.0 || doubleRating > 5.0) {
                model.addAttribute("errorMessage", "0.0 이상 ~ 5.0 이하의 유효한 숫자를 입력해주세요.");
                return "restaurant-review-form";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "평점을 숫자로 입력해 주세요.");
            System.out.println(e.getMessage());
            return "restaurant-review-form";
        }

        if (restaurantComment.isBlank()) {
            model.addAttribute("errorMessage", "후기 내용을 입력해 주세요.");
            return "restaurant-review-form";
        }

        model.addAttribute("draftRating", "");
        model.addAttribute("draftComment", "");
        model.addAttribute("restaurantRating", doubleRating);
        model.addAttribute("restaurantComment", restaurantComment);

        return "restaurant-review-form";
    }

}
