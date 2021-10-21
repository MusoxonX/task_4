package uz.pdp.task_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_4.entity.Card;
import uz.pdp.task_4.entity.Outcome;
import uz.pdp.task_4.payload.OutcomeDto;
import uz.pdp.task_4.repository.CardRepository;
import uz.pdp.task_4.repository.OutcomeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CardRepository cardRepository;

    @GetMapping()
    public HttpEntity<?> getOutcome(){
        List<Outcome> all = outcomeRepository.findAll();
        return ResponseEntity.status(201).body(all);
    }

    @PostMapping()
    public HttpEntity<?> addOutput(@RequestBody OutcomeDto outcomeDto){
        Outcome outcome = new Outcome();
        Optional<Card> optionalFromCard = cardRepository.findById(outcomeDto.getFromCardId());
        if (!optionalFromCard.isPresent()){
            return ResponseEntity.status(409).body("card not found");
        }
        Card fromCard = optionalFromCard.get();
        double balance = fromCard.getBalance();
        if (balance < outcomeDto.getAmount()){
            return ResponseEntity.status(400).body("you have not enough money");
        }
        double c = outcomeDto.getAmount()*0.01;
        double k = outcomeDto.getAmount()+c;
        double v = balance - k;
        fromCard.setBalance(v);
        Optional<Card> optionalToCard = cardRepository.findById(outcomeDto.getToCardId());
        if (!optionalToCard.isPresent()){
            return ResponseEntity.status(409).body("card not found");
        }
        Card toCard = optionalToCard.get();
        toCard.setBalance(toCard.getBalance()+outcomeDto.getAmount());

        outcome.setFromCardId(fromCard);
        outcome.setToCardId(toCard);
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setDate(outcomeDto.getDate());
        outcome.setCommisionAmount(c);
        Outcome outcome1 = outcomeRepository.save(outcome);
        return ResponseEntity.status(201).body(outcome1);
    }
}
