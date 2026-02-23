package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


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
    

}
