package parser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacansiesRepository extends CrudRepository<Vacancies, Long> {
    @Query("SELECT u FROM Vacancies u WHERE u.url = u.profession  + u.level + u.city ")
    List<Vacancies> findByUsers();
    public List<Vacancies> findVacanciesByacName(String vacName, String url);
}
