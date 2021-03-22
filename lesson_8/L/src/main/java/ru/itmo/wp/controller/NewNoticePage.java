package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.validator.NoticeCredentialValidator;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class NewNoticePage extends Page{
    private final NoticeService noticeService;
    private final NoticeCredentialValidator noticeCredentialValidator;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (noticeCredentialValidator.supports(Objects.requireNonNull(binder.getTarget()).getClass())) {
            binder.addValidators(noticeCredentialValidator);
        }
    }

    public NewNoticePage(NoticeService noticeService, NoticeCredentialValidator noticeCredentialValidator) {
        this.noticeService = noticeService;
        this.noticeCredentialValidator = noticeCredentialValidator;
    }


    @GetMapping({"/notice"})
    public String newNotice(Model model) {
        model.addAttribute("noticeForm", new NoticeCredentials());
        return "NewNoticePage";
    }

    @PostMapping("/notice")
    public String registerPost(@Valid @ModelAttribute("noticeForm") NoticeCredentials noticeForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NewNoticePage";
        }

        noticeService.add(noticeForm);
        putMessage(httpSession, "Congrats, your post has been added!");

        return "redirect:";
    }



}
