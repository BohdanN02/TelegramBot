package parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class VacanciesServise implements ApplicationRunner {

    private VacansiesRepository vacansiesRepository;

    @Autowired
    public VacanciesServise(VacansiesRepository vacansiesRepository) {
        this.vacansiesRepository = vacansiesRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
