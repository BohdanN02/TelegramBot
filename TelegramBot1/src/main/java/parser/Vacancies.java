package parser;

import com.example.demo.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Vacancies {

        @Id
        @GeneratedValue
        @Column(name = "Id", nullable = false)
        private Long id;
        @Column(name = "VACNAME", length = 64, nullable = false)
        private String vacName;
    @Column(name = "URL", length = 64, nullable = false)
        private  String url;
    @OneToMany(mappedBy = "vacancies", cascade = CascadeType.ALL)
    private List<User> User = new ArrayList<>();

    public Vacancies() {
    }

    public Vacancies(String vacName, String url) {
        this.vacName=vacName;
        this.url=url;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    @Override
    public String toString() {
        return "Vacancies{" +
                "id=" + id +
                ", vacName='" + vacName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
