package ru.dins.test_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dins.test_task.models.TableOne;

public interface TableOneRepository extends JpaRepository<TableOne,Integer> {
}
