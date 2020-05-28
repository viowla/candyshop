package kz.iitu.candyshop.controller;

import io.swagger.annotations.Api;
import kz.iitu.candyshop.entity.Dessert;
import kz.iitu.candyshop.repository.DessertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/desserts")
@Api(value = "Desserts Controller class")
public class DessertController {
    @Autowired
    private DessertRepository dessertRepository;

    @GetMapping("")
    public String getAllDesserts(Map<String, Object> model){
        List<Dessert> dessert = dessertRepository.findAll();

        model.put("desserts", dessert);
        return "desserts";
    }

    @PostMapping("/create")
    public Dessert addDessert(@RequestBody Dessert dessert){
        return dessertRepository.saveAndFlush(dessert);
    }

    @GetMapping("/{id}")
    public Dessert getDessertById(@PathVariable Long id){
        Dessert dessert = dessertRepository.findById(id).get();

        return dessert;
    }

    @PutMapping("/{id}")
    public void updateDessert(@PathVariable Long id, @RequestBody Dessert dessert){
        Dessert dessert1 = dessertRepository.findById(id).orElse(null);

        if (dessert1 != null) {
            dessert1.setTitle(dessert.getTitle());
            dessert1.setPrice(dessert.getPrice());
            dessert1.setIngridients(dessert.getIngridients());

            dessertRepository.saveAndFlush(dessert1);
        }

    }

    @DeleteMapping("/{id}")
    public void deleteDessert(@PathVariable Long id){
        dessertRepository.deleteById(id);
    }



}
