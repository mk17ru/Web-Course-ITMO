package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Controller
public class PostPage extends Page {

    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String getPost(Model model, HttpSession httpSession, @Pattern(regexp = "[0-9]+", message = "Expected numbers") @PathVariable String id) {
        Post post;
        try {
            post = postService.find(Long.parseLong(id));
        } catch (NumberFormatException e) {
            putMessage(httpSession, "Post not found");
            return "redirect:/";
        }

        if (post == null) {
            putMessage(httpSession, "Post not found");
            return "redirect:/";
        }

        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", post.getComments());
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String writeComment(Model model, @Valid @ModelAttribute("comment") Comment comment,
                                BindingResult bindingResult,
                                HttpSession httpSession, @Pattern(regexp = "[0-9]+", message = "Expected numbers") @PathVariable String id) {
        Post post = postService.findById(Long.valueOf(id)).orElse(null);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }

        if (post == null) {
            putMessage(httpSession, "Post not found");
            return "redirect:/";
        }

        postService.writeComment(post, comment, getUser(httpSession));
        model.addAttribute("post", post);
        putMessage(httpSession, "You published new comment");

        return "redirect:/post/" + id;
    }
}
