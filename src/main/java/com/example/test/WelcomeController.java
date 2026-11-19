package com.example.test;

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
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "greeting";
    }

    @GetMapping("/practice-page")
    public String practicePage() {
        return "practice";
    }

    @GetMapping("/menus")
    public String menus(Model model) {
        List<String> menus = List.of("menu1", "menu2");
        model.addAttribute("menuNames", menus);
        return "menus";
    }

    @GetMapping("/menus/{id}")
    public String menuDetail(
            @PathVariable("id") int id,
            Model model,
            HttpServletResponse response
    ) {
        if (id != 1) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "menu-not-found";
        }

        model.addAttribute("menuId", id);
        return "menu-detail";
    }

    @GetMapping("/books/{id}")
    public String bookDetail(
            @PathVariable(value = "id") int id,
            Model model,
            HttpServletResponse response
    ) {
        Map<Integer, String> bookMap = new HashMap<>();
        bookMap.put(1, "자바 입문");
        bookMap.put(2, "스프링 입문");

        String bookTitle = bookMap.get(id);

        if (bookTitle == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            // 요청한 ID가 맵에 없으면 지정한 기본 문구를 반환한다.
            bookTitle = "등록된 책이 없습니다.";
        }


        model.addAttribute("bookId", bookTitle);
        return "book-detail";
    }

    @GetMapping("/menu-form")
    public String showMenuForm() {
        return "menu-form";
    }

    @PostMapping("/menu-form")
    public String submitMenuForm(
            @RequestParam("menuName") String menuName,
            Model model
    ) {
        model.addAttribute("submittedMenu", menuName);
        return "menu-form";
    }

    @GetMapping("/book-review-form")
    public String showBookReviewForm() {
        return "book-review-form";
    }

    @PostMapping("/book-review-form")
    public String submitBookReviewForm(
            @RequestParam("bookTitle") String bookTitle,
            @RequestParam("comment") String comment,
            Model model
    ) {
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("comment", comment);

        return "book-review-form";
    }

}
