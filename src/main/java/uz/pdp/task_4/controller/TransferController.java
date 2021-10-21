package uz.pdp.task_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_4.entity.Card;
import uz.pdp.task_4.entity.Income;
import uz.pdp.task_4.entity.Outcome;
import uz.pdp.task_4.payload.TransferDto;
import uz.pdp.task_4.repository.CardRepository;
import uz.pdp.task_4.repository.IncomeRepository;
import uz.pdp.task_4.repository.OutcomeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;

    @GetMapping()
    public HttpEntity<?> getTransfer(){
        List<Outcome> all = outcomeRepository.findAll();
        return ResponseEntity.status(201).body(all);
    }

    @PostMapping()
    public HttpEntity<?> addTransfer(@RequestBody TransferDto outcomeDto){
        Optional<Card> optionalFromCard = cardRepository.findById(outcomeDto.getFromCardId());
        if (!optionalFromCard.isPresent()){
            return ResponseEntity.status(409).body("card not found");
        }
        Optional<Card> optionalToCard = cardRepository.findById(outcomeDto.getToCardId());
        if (!optionalToCard.isPresent()){
            return ResponseEntity.status(409).body("card not found");
        }

        Outcome outcome = new Outcome();
        Income income = new Income();

        Card fromCard = optionalFromCard.get();
        Card toCard = optionalToCard.get();

        double balance = fromCard.getBalance();
        double comissionMoney = outcomeDto.getAmount()*0.01;
        double umumiyChiqayotganPul = outcomeDto.getAmount()+comissionMoney;
        if (balance < umumiyChiqayotganPul){
            return ResponseEntity.status(400).body("you have not enough money");
        }
        double qoldiqPul = balance - umumiyChiqayotganPul;

        fromCard.setBalance(qoldiqPul);
        toCard.setBalance(toCard.getBalance()+outcomeDto.getAmount());

        income.setFromCardId(fromCard);
        income.setToCardId(toCard);
        income.setAmount(outcomeDto.getAmount());
        income.setDate(outcomeDto.getDate());
        incomeRepository.save(income);

        outcome.setFromCardId(fromCard);
        outcome.setToCardId(toCard);
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setDate(outcomeDto.getDate());
        outcome.setCommisionAmount(comissionMoney);
        Outcome outcome1 = outcomeRepository.save(outcome);
        return ResponseEntity.status(201).body(outcome1);
    }
}
