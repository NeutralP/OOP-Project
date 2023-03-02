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

import model.HistoricalHero;

import java.util.*;

public class HistoryHeroCrawl {
    public static void main(String[] args) throws JSONException {
        String url = "https://vi.wikipedia.org/wiki/Anh_h%C3%B9ng_d%C3%A2n_t%E1%BB%99c_Vi%E1%BB%87t_Nam";
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
            List<HistoricalHero> siteList = new ArrayList<HistoricalHero>();
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                System.out.println(cols.size());
                HistoricalHero historical_hero = new HistoricalHero();
                historical_hero.setId(cols.get(0).text());
                historical_hero.setName(cols.get(1).text());
                historical_hero.setBirthplace(cols.get(2).text());
                historical_hero.setEra(cols.get(3).text());
                historical_hero.setCountryname(cols.get(4).text());
                historical_hero.setCapital(cols.get(5).text());
                if (cols.size() >= 7) {
                    historical_hero.setStandard(cols.get(6).text());
                }
                siteList.add(historical_hero);
                link = cols.get(0).select("a[href]");
                if (link.attr("abs:href") != "") {
                    try {
                        doc_link = Jsoup.connect(link.attr("abs:href")).timeout(10000).get();
                        historical_hero
                                .setDescription(doc_link.select(".mw-body-content mw-content-ltr p").text());

                    } catch (Exception e) {
                        System.out.println("Link is unavailable");
                    }
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(siteList);
            OutputStreamWriter char_output = new OutputStreamWriter(
                    new FileOutputStream("historical_hero.json"), Charset.forName("UTF-8").newEncoder());
            char_output.write(json);
            char_output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
