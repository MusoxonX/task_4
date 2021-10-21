package uz.pdp.task_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_4.entity.Card;
import uz.pdp.task_4.repository.CardRepository;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @GetMapping()
    public HttpEntity<?> getCards(){
        return ResponseEntity.status(200).body(cardRepository.findAll());
    }

    @PostMapping()
    public HttpEntity<?> addCard(@RequestBody Card card){
        cardRepository.save(card);
        return ResponseEntity.status(201).body("card added");
    }
}
