package parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private VacansiesRepository vacansiesRepository;
    @ResponseBody
    @RequestMapping("/")
    public String index(){
        Iterable<Vacancies> all = vacansiesRepository.findAll();
        StringBuilder sb = new StringBuilder();
        all.forEach(p-> sb.append(p.getVacName() + "<br>"));
        return sb.toString();
    }
}
