package com.example.demo.Controller;


import com.example.demo.Entities.Evenement;
import com.example.demo.Service.EventService;
import com.example.demo.enums.TypeEvenement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
@CrossOrigin(origins = "http://localhost:4200")
public class EventeController {
    private final EventService eventService;

    public EventeController(EventService eventService){
        this.eventService=eventService;
    }
    @GetMapping("/entreprise/{id}")
    public List<Evenement> getAllEvent(@PathVariable Long id){
        return eventService.getAllByEntrepriseId(id);
    }

    @GetMapping
    public List<Evenement> getEventByType(@RequestParam Long entreprise_id, @RequestParam TypeEvenement type){
        return eventService.getByEntrepriseIdAndType(entreprise_id,type);
    }
    @GetMapping("/{id}")
    public Evenement getAll(@PathVariable Long id){
        return eventService.getById(id);
    }

    @PostMapping
    public Evenement save(@RequestParam Evenement evenement){
        return eventService.save(evenement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        eventService.delete(id);
    }

}
