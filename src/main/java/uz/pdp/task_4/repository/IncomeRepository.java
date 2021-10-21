package uz.pdp.task_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_4.entity.Income;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
}
