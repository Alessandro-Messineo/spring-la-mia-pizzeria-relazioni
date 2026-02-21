package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.SpecialOfferRepository;
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

@Controller
@RequestMapping("/special-offer")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/create/{id}")
    public String create(@PathVariable Integer id, Model model) {

        SpecialOffer specialOffer = new SpecialOffer();

        specialOffer.setPizza(pizzaRepository.findById(id).get());

        model.addAttribute("specialOffer", specialOffer);
        return "special-offers/form-special-offer";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "special-offers/form-special-offer";
        }

        Integer idPizza = formOffer.getPizza().getId();

        specialOfferRepository.save(formOffer);
        return "redirect:/pizza/" + idPizza;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("specialOffer", specialOfferRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "special-offers/form-special-offer";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formOffer, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "special-offers/form-special-offer";
        }

        Integer idPizza = formOffer.getPizza().getId();

        specialOfferRepository.save(formOffer);

        return "redirect:/pizza/" + idPizza;
    }

}
