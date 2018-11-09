package com.example.demo;


import com.example.demo.Classes.Course;
import com.example.demo.Classes.User;
import com.example.demo.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
    private UserService userService;
    @Autowired
    private CourseRepo courseRepo;


    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "homepage";
    }
    @RequestMapping("/afterlogin")
    public String afterLogin(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "newhome";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/homePage")
    public String loginHome(){
        return "newhome";
    }


    @RequestMapping("/addCourse")
    public String addCourse(Model model) {

        model.addAttribute("course", new Course());


        return "courseform";
    }

    @PostMapping("/processcourse")
    public String saveCourse(@ModelAttribute("course") Course course, BindingResult result) {

        if (result.hasErrors()) {
            return "courseform";
        }
        courseRepo.save(course);

        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") long id, Model model) {

        model.addAttribute("course", courseRepo.findById(id).get());

        return "detailpage";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("course", courseRepo.findById(id).get());

        return "courseform";

    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id) {

        courseRepo.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        model.addAttribute("user", user);
        if (result.hasErrors()) {

            return "registration";
        } else {

            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "homepage";
    }

}
