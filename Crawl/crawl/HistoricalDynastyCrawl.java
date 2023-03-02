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

import model.Dynasty;

import java.util.*;

public class HistoricalDynastyCrawl {
    // public static void DynastyCrawl( main args[]) throws JSONException {
    public static void DynastyCrawl() throws JSONException {
        String url = "https://nguoikesu.com/tu-lieu/bang-doi-chieu-cac-trieu-dai-viet-nam-va-cac-trieu-dai-trung-quoc";
        Document doc = null;
        Elements link;
        Document doc_link;
        try {

            doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(20000).get();

            Elements table = doc.select("table.table.table-bordered");
            Elements rows = table.select("tr");
            List<Dynasty> siteList = new ArrayList<Dynasty>();
            for (int i = 1; i < rows.size(); i++) {

                Element row = rows.get(i);
                Elements cols = row.select("td");
                int check = cols.size();
                if (check > 3) {
                    Dynasty historical_dynasty = new Dynasty();
                    List<String> Namelist = new ArrayList<>();
                    int count = cols.get(0).select("a.annotation").size();
                    for (int j = 0; j < count; j++) {
                        Namelist.add(cols.get(0).select("a.annotation").get(j).text());
                    }
                    // historical_dynasty.setName(cols.get(0).text());
                    historical_dynasty.setNameList(Namelist);
                    historical_dynasty.setName(cols.get(0).text());
                    // Elements
                    historical_dynasty.setKingname(cols.get(1).text());
                    historical_dynasty.setLunardate(cols.get(3).text());
                    historical_dynasty.setDate(cols.get(2).text());
                    historical_dynasty.setChinesename(cols.get(4).text());
                    if (cols.size() == 6) {
                        historical_dynasty.setChineseperiod(cols.get(5).text());
                    }
                    historical_dynasty.setDescription(cols.select("a").attr("title"));
                    siteList.add(historical_dynasty);
                    link = cols.select("a[href]");

                }

            }
            Gson gson = new Gson();
            String json = gson.toJson(siteList);
            OutputStreamWriter char_output = new OutputStreamWriter(
                    new FileOutputStream("historical_dynasty.json"), Charset.forName("UTF-8").newEncoder());
            char_output.write(json);
            char_output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
