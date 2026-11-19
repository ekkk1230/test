package com.example.test;

import com.example.test.domain.Restaurant;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class VariablePractice {
    static String formatRank(int rank, String storeName) {
        return rank + "위 맛집 - " + storeName;
    }

    static Restaurant findByName(List<Restaurant> restaurantList, String name) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (name.equals(restaurantList.get(i).getStoreName())) {
                return restaurantList.get(i);
            }
        }

        return null;
    }

    static void printRestaurants(List<Restaurant> restaurants) {
//        for (int i = 0; i <restaurants.size(); i++) {
//            System.out.println(restaurants.get(i).describe());
//        }

        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant.describe());
        }
    }

    static Optional<Restaurant> findByNameOptional(List<Restaurant> restaurants, String name) {
        return restaurants.stream().filter(store -> name.equals(store.getStoreName())).findFirst();
    }

    static Optional<Restaurant> findByRatingOptional(List<Restaurant> restaurants, Double rating) {
        return restaurants.stream().sorted((first, second) -> Double.compare(second.getRating(), first.getRating())).filter(store -> store.getRating() >= rating).findFirst();
    }

    static boolean isInWeek(LocalDate date, LocalDate weekStart) {
        LocalDate weekEnd = weekStart.plusWeeks(1);
        return !date.isBefore(weekStart) && date.isBefore(weekEnd);
    }

    public static void main(String[] args) {
        String storeName = "골목식당";
        int viewCount = 100;
        double rating = 4.7;
        boolean state = true;

        System.out.println("맛집 이름: " + storeName);
        System.out.println("조회수: " + viewCount);
        System.out.println("평점: " + rating);
        System.out.println("영업 중: " + state);

        int ratingCount = 3;
        int totalRating = 14;

        System.out.println(totalRating / ratingCount);
        System.out.println(totalRating / (double)ratingCount);

        // rating, viewCount, state를 써서 평점이 4.5 이상이고, 조회수가 100 이상이고, 영업 중이면 true가 되는 식
        boolean isRecommended = rating >= 4.5 && viewCount >= 100 && state;
        if (isRecommended) {
            System.out.println("추천 맛집입니다.");
        } else {
            System.out.println("추천 조건을 충족하지 않습니다");
        }

        String[] recommendStore = {"도미노피자", "꾸브라꼬", "위자네", "맥도날드"};

        for (int rank = 1; rank <= recommendStore.length; rank++) {
            System.out.println(formatRank(rank, recommendStore[rank - 1]));
        }

        Restaurant restaurant4 = new Restaurant("위자네", 25, 1.8, true);
        Restaurant restaurant2 = new Restaurant("스시로", 211, 4.3, false);
        Restaurant restaurant1 = new Restaurant("두찜", 122, 4.8, true);
        Restaurant sameValue = new Restaurant("두찜", 122, 3.5, true);

        System.out.println(restaurant1.describe());
        System.out.println(restaurant2.describe());

        Restaurant another = restaurant1;
        another.recordView();
        System.out.println(restaurant1.describe());
        System.out.println(another.describe());
        System.out.println(restaurant1 == sameValue);
        System.out.println(restaurant1 == another);

        Restaurant foundRestaurant = null;
        if (foundRestaurant == null) {
            System.out.println("맛집을 찾지 못했습니다.");
        } else {
            System.out.println(foundRestaurant.describe());
        }

        Restaurant[] restaurants = { restaurant1, restaurant2 };

//        Restaurant found = findByName(restaurants, "스시로");
//
//        if (found == null) {
//            System.out.println("맛집을 찾지 못했습니다.");
//        } else {
//            System.out.println(found.getStoreName());
//        }

        Review review1 = new Review(restaurant1, "양이 푸짐해졌어요.");
        Review review2 = new Review(restaurant1, "다음에 또 갈래요.");

        System.out.println(review1.describe());
        System.out.println(review2.describe());

        Comment comment = new Comment("저도 여기 좋아해요.", "작성자1");
        System.out.println(comment.getContent());
        System.out.println(comment.describe());

        TextPost post = comment;
        TextPost post2 = new TextPost("오야야");
        System.out.println(post.describe());
        System.out.println(post2.describe());

        TextPost[] posts = { post2, comment };
        for (int i = 0; i < posts.length; i++) {
            System.out.println(posts[i].describe());
        }

        Describable[] describables = { review1, restaurant1 };
        for (int i = 0; i < describables.length; i++) {
            System.out.println(describables[i].describe());
        }

        try {
            Restaurant restaurant3 = new Restaurant("test", 123, 5.0, false);
            System.out.println(restaurant3.describe());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        List<Restaurant> restaurantList = new ArrayList<>();

        restaurantList.add(restaurant4);
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        System.out.println(restaurantList.size());
        System.out.println(restaurantList.size());

        Restaurant found = findByName(restaurantList, "없는맛집");

        if (found == null) {
            System.out.println("존재하지 않는 맛집입니다.");
        } else {
            System.out.println(found.getStoreName());
        }

        Map<String, Restaurant> restaurantMap = new HashMap<>();
        restaurantMap.put(restaurant1.getStoreName(), restaurant1);
        restaurantMap.put(restaurant2.getStoreName(), restaurant2);
        restaurantMap.put(restaurant4.getStoreName(), restaurant4);
        restaurantMap.put(restaurant1.getStoreName(), sameValue);

        System.out.println(restaurantMap.size());
        System.out.println(restaurantMap.get("두찜").describe());


        Restaurant foundMap = restaurantMap.get("fsda");
        if (foundMap == null) {
            System.out.println("존재하지 않는 맛집 정보 입니다.");
        } else {
            System.out.println(foundMap.getStoreName());
        }

        Set<String> menuNames = new HashSet<>();
        System.out.println(menuNames.add("김밥"));
        System.out.println(menuNames.add("김밥"));
        System.out.println(menuNames.size());

        System.out.println(menuNames.add("두찜"));
        System.out.println(menuNames.add("스시로"));
        System.out.println(menuNames.add("두찜"));
        System.out.println(menuNames.size());

        Restaurant sameRestaurant1 = new Restaurant("test1", 11, 1.1, false);
        Restaurant sameRestaurant2 = new Restaurant("test1", 11, 1.1, false);
        System.out.println(sameRestaurant1 == sameRestaurant2);
        System.out.println(sameRestaurant1.equals(sameRestaurant2));

        Set<Restaurant> restaurantSet = new HashSet<>();
        System.out.println(restaurantSet.add(sameRestaurant1));
        System.out.println(restaurantSet.add(sameRestaurant2));
        System.out.println(restaurantSet.add(sameRestaurant1));
        System.out.println(restaurantSet.size());

        System.out.println("===================================");

        MenuCode code1 = new MenuCode("M01");
        MenuCode code2 = new MenuCode("M01");
        System.out.println(code1 == code2);
        System.out.println(code1.equals(code2));
        System.out.println(code1.hashCode());
        System.out.println(code2.hashCode());

        Set<MenuCode> menuCodeSet = new HashSet<>();
        System.out.println(menuCodeSet.add(code1));
        System.out.println(menuCodeSet.add(code2));
        System.out.println(menuCodeSet.size());
        printRestaurants(restaurantList);

        restaurantList.sort((first, second) -> Double.compare(second.getRating(), first.getRating()));
        printRestaurants(restaurantList);
        System.out.println("stream");
        List<Restaurant> recommendedRestaurants = restaurantList.stream().filter(store -> store.getRating() >= 4.5).toList();
        printRestaurants(recommendedRestaurants);
        System.out.println(restaurantList.size() + ", " + recommendedRestaurants.size());
        System.out.println("map");
        List<String> restaurantNames = recommendedRestaurants.stream().map(store -> store.getStoreName()).toList();
        System.out.println(restaurantNames);

        System.out.println("limit");
        List<Restaurant> topRestaurants =
                restaurantList.stream()
                        .sorted((first, second) -> Integer.compare(second.getViewCount(), first.getViewCount()))
                        .limit(2)
                        .toList();
        printRestaurants(topRestaurants);
        Optional<Restaurant> foundStore = findByNameOptional(restaurantList, "no");
        Optional<String> foundName = foundStore.map(store -> store.getStoreName());
        String message = foundName.orElse("존재하지 않는 맛집입니다.");

        System.out.println(message);

        Optional<Restaurant> foundByRatingStore = findByRatingOptional(restaurantList, 4.9);
        String foundByRatingName = foundByRatingStore.map(store -> store.getStoreName()).orElse("존재하지 않는 맛집입니다.");
        System.out.println(foundByRatingName);

        LocalDate today = LocalDate.of(2026, 11, 23);
        LocalDate weekStart = LocalDate.of(2026, 11, 16);
        System.out.println(isInWeek(today, weekStart));
    }
}
