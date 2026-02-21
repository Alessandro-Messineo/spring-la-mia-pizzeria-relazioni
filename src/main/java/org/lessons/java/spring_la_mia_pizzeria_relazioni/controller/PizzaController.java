package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.classes.Pizza;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String search) {

        List<Pizza> pizza;

        if (search.isEmpty()) {
            pizza = repository.findAll();
        } else {
            pizza = repository.findByNameContainingIgnoreCase(search);
        }

        model.addAttribute("pizza", pizza);
        model.addAttribute("search", search);

        return "pizzas/index";
    }

    @GetMapping("/pizza/{id}")
    public String show(Model model, @PathVariable Integer id) {

        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/detail-pizza";
    }

    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("pizza", new Pizza());

        return "pizzas/form-new-pizza";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "pizzas/form-new-pizza";
        }

        repository.save(formPizza);

        return "redirect:/";
    }

    @GetMapping("pizza/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

        model.addAttribute("pizza", repository.findById(id).get());

        return "pizzas/form-edit-pizza";
    }

    @PostMapping("pizza/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "pizzas/form-edit-pizza";
        }

        repository.save(formPizza);

        return "redirect:/";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, Model model){

        repository.deleteById(id);

        return "redirect:/";
    }

}
