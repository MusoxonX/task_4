package uz.pdp.task_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.task_4.entity.Outcome;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {
}
