package crawl;

import model.Festival;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class HistoricalFestivalCrawl {
    public static void FestivalCrawl() throws IOException {
        Document doc = Jsoup.connect("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam").get();
        Elements table = doc.select("table.prettytable.wikitable");
        Elements rows = table.select("tr");
        List<Festival> lehoiList = new ArrayList<>();
        for (int i = 1; i < rows.size() - 1; i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            Festival lehoi = new Festival();
            lehoi.setDate(cols.get(0).text());
            lehoi.setLocation(cols.get(1).text());
            lehoi.setName(cols.get(2).text());
            lehoi.setFirstDate(cols.get(3).text());
            lehoi.setRelatedNvat(cols.get(4).text());
            lehoiList.add(lehoi);
            if (cols.size() == 6) {
                lehoi.setNote(cols.get(5).text());
            }

            Elements details = cols.get(2).select("a");
            if (details != null) {
                for (Element element : details) {
                    try {
                        doc = Jsoup.connect(element.attr("abs:href")).timeout(10000).get();
                        Element detail = doc.select(".mw-body-content.mw-content-ltr p").first();
                        String detailText = detail.text();
                        lehoi.setDetail(detailText);
                    } catch (Exception e) {
                        // System.out.println("Link is unavailable");;
                    }
                }
            }
        }
        // Element link = doc.select("a").first();
        // String absHref = link.attr("abs:href"); // == "/"
        // System.out.println(absHref);
        Gson gson = new Gson();
        String json = gson.toJson(lehoiList);
        // FileWriter writer = new FileWriter("lehoiList.json");
        // writer.write(json);
        // writer.close();
        OutputStreamWriter char_output = new OutputStreamWriter(
                new FileOutputStream("historical_festival.json"),
                Charset.forName("UTF-8").newEncoder());
        char_output.write(json);
        char_output.close();
    }
}
