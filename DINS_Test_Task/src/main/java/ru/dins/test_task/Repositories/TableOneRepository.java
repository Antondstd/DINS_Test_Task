package ru.dins.test_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dins.test_task.models.TableOne;

@Repository
public interface TableOneRepository extends JpaRepository<TableOne,Integer> {
}
