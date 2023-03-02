package crawl;

import java.io.*;
import java.io.IOException;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;

import model.Person_WarHero;

import java.util.*;

public class HistoricalPersonCrawl {
    public static void PersonCrawl() throws JSONException {
        String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Anh_h%C3%B9ng_L%E1%BB%B1c_l%C6%B0%E1%BB%A3ng_v%C5%A9_trang_nh%C3%A2n_d%C3%A2n";
        Document doc = null;
        Elements link;
        Document doc_link;
        try {

            doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(20000).get();

            Elements table = doc.select("table.wikitable.sortable"); //
            Elements rows = table.select("tr");
            List<Person_WarHero> siteList = new ArrayList<Person_WarHero>();
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                Person_WarHero historical_person = new Person_WarHero();
                historical_person.setName(cols.get(0).text());
                historical_person.setDate(cols.get(1).text());
                historical_person.setEthnicity(cols.get(3).text());
                historical_person.setBirthplace(cols.get(2).text());
                historical_person.setReleased_year(cols.get(4).text());
                if (cols.size() == 6) {
                    historical_person.setAchivement(cols.get(5).text());
                }
                siteList.add(historical_person);
                link = cols.select("a[href]");
                if (link.attr("abs:href") != "") {
                    try {
                        doc_link = Jsoup.connect(link.attr("abs:href")).timeout(10000).get();
                        historical_person
                                .setDescription(doc_link.select("div[class=mw-body-content mw-content-ltr]").text());

                    } catch (Exception e) {
                        System.out.println("Link is unavailable");
                    }
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(siteList);
            OutputStreamWriter char_output = new OutputStreamWriter(
                    new FileOutputStream("historical_person.json"), Charset.forName("UTF-8").newEncoder());
            char_output.write(json);
            char_output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
