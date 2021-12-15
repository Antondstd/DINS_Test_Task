package ru.dins.test_task.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dins.test_task.Models.TableOne;

public interface TableOneRepository extends JpaRepository<TableOne,Integer> {
}
