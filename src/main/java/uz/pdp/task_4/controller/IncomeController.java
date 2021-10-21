package uz.pdp.task_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_4.entity.Card;
import uz.pdp.task_4.entity.Income;
import uz.pdp.task_4.entity.Outcome;
import uz.pdp.task_4.payload.IncomeDto;
import uz.pdp.task_4.repository.CardRepository;
import uz.pdp.task_4.repository.IncomeRepository;
import uz.pdp.task_4.repository.OutcomeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    @GetMapping
    public HttpEntity<?> getIncome(){
        return ResponseEntity.status(201).body(outcomeRepository.findAll());
    }
}
