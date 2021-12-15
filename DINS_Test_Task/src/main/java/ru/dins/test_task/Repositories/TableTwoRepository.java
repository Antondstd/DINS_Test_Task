package ru.dins.test_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dins.test_task.models.TableTwo;

public interface TableTwoRepository extends JpaRepository<TableTwo,Integer> {
}
