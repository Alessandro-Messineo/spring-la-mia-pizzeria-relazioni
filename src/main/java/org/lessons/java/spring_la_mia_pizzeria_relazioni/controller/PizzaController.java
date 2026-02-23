package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.SpecialOfferRepository;
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

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model,
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {

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
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizzas/form-pizza";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/form-pizza";
        }

        Pizza saved = repository.save(formPizza);
        
        return "redirect:/pizza/" + saved.getId();
    }

    @GetMapping("pizza/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("pizza", repository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("edit", true);

        return "pizzas/form-pizza";
    }

    @PostMapping("pizza/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            model.addAttribute("edit", true);
            return "pizzas/form-pizza";
        }

        Pizza saved = repository.save(formPizza);

        return "redirect:/pizza/" + saved.getId();
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {

        Pizza pizza = repository.findById(id).get();

        for (SpecialOffer offerToDelete : pizza.getSpecialOffers()) {
            specialOfferRepository.delete(offerToDelete);
        }

        repository.delete(pizza);

        return "redirect:/";
    }

}
