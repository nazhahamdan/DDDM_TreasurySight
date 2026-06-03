package com.example.demo.Service;

import com.example.demo.Entities.Evenement;
import com.example.demo.Repositories.EventRepository;
import com.example.demo.enums.TypeEvenement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository=eventRepository;
    }
    //recuperer les evenement par idEntreprise et type evennement
    public List<Evenement> getByEntrepriseIdAndType(Long entreprise_id, TypeEvenement type){
        return eventRepository.findByEntrepriseIdAndType(entreprise_id,type);
    }

    //recuperer les events par id de l'entreprise
    public List<Evenement> getAllByEntrepriseId(Long entreprise_id){
        return eventRepository.findByEntrepriseId(entreprise_id);
    }
    public Evenement getById(Long id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evenement non trouvé"));
    }

    //modifier ou ajouter une evenement
    public Evenement save(Evenement evenement){
        return eventRepository.save(evenement);
    }

    // supprimer une event
    public void delete(Long id){
        eventRepository.deleteById(id);
    }
}
