package ru.dins.test_task.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dins.test_task.Models.TableOne;
import ru.dins.test_task.Models.TableTwo;

public interface TableTwoRepository extends JpaRepository<TableTwo,Integer> {
}
