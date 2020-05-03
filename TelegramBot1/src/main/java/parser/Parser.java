package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    final static int ADMINISRTATIVE = 44;
    final static int BUSINESS_ANALYST = 45;
    final static int CPP = 932;
    final static int CNET = 930;
    final static int DEVOPS = 938;
    final static int FRONT_END = 940;
    final static int JAVA = 942;
    final static int PM = 47;
    final static int QA = 48;
    final static int SAP = 1183;
    final static int SOLUTION_ARCHITECTS = 948;
    final static int SYS_NET = 950;
    final static int TELECOM = 952;
    final static int WEB = 954;
    final static int OTHER = 946;
    final static String URL_INFOPULSE = "https://www.infopulse.com/career/";
    final static String REQ_BODY = "data[0][name]=vacancies-filter__search&data[0][value]=&data[1][name]=vacancies-filter__search-by&data[1][value]=1&data[2][name]=departments[]&data[2][value]=";

    public static void main(String[] args) throws IOException {
        List<Vacancies> VacList = new ArrayList<>();
        int[] iProf = {ADMINISRTATIVE, BUSINESS_ANALYST, CPP, CNET, DEVOPS, FRONT_END, JAVA, PM, QA, SAP, SOLUTION_ARCHITECTS, SYS_NET, TELECOM, WEB, OTHER};
        String[] sProf = {"ADMINISRTATIVE", "BUSINESS_ANALYST", "CPP", "CNET", "DEVOPS", "FRONT_END", "JAVA", "PM", "QA", "SAP", "SOLUTION_ARCHITECTS", "SYS_NET", "TELECOM", "WEB", "OTHER"};
        for (int i = 0; i < iProf.length; i++) {
            for (int j = 1; j < 5; j++) {
                Document doc = Jsoup.connect(URL_INFOPULSE + "?paged=" + j)
                        .requestBody(REQ_BODY + iProf[i])
                        .post();
                Elements listPost = doc.select("span.vacancy-item__link.clearfix");
                if (listPost.size() != 0) {
                    for (Element e : listPost) {
                        String url =e.text()+ e.attr("title");
                        String vacName =e.select(" a").attr("href");
                        VacList.add(new Vacancies(url,vacName));
                    }
                } else {
                    break;
                }
            }
            } for (Vacancies url  : VacList) {
            System.out.println(url);
        }
    }
}


