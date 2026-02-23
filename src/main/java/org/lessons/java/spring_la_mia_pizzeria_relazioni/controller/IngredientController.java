package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingredient", new Ingredient());

        return "ingredients/form-ingredient";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/form-ingredient";
        }

        ingredientRepository.save(formIngredient);

        return "redirect:/ingredient";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredients/form-ingredient";
    }

    @PostMapping("edit/{id}")
    public String upate(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/form-ingredient";
        }

        ingredientRepository.save(formIngredient);

        return "redirect:/ingredient";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
       
        ingredientRepository.deleteById(id);
        
        return "redirect:/ingredient";
    }
    

}
