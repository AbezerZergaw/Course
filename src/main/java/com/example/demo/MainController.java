package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @Autowired
    CourseRepo courseRepo;


    @RequestMapping("/")
        public String homePage(Model model){
            model.addAttribute("courses", courseRepo.findAll());
            return "homepage";
    }


    @RequestMapping("/addCourse")
    public String addDepartment(Model model){

        model.addAttribute("course", new Course());


        return "courseform";
    }
    @PostMapping("/processcourse")
    public String saveCourse(@ModelAttribute("course") Course course, BindingResult result){

        if(result.hasErrors()){
            return "courseform";
        }
        courseRepo.save(course);

        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") long id, Model model){

        model.addAttribute("course", courseRepo.findById(id).get());

        return "detailpage";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepo.findById(id).get());

        return "courseform";

    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){

        courseRepo.deleteById(id);
        return "redirect:/";
    }



}
