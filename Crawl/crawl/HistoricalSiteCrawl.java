package crawl;

import com.google.gson.Gson;
import model.HistoricalSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoricalSiteCrawl {
    public static void SiteCrawl() throws IOException {
        String regex = "(\\d{2}/\\d{1,2}/\\d{4})";
        int colNum = 4;
        Document doc = Jsoup.connect("http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752").get();
        Elements table = doc.select("table.Table");
        Elements rows = table.select("tr");
        List<HistoricalSite> DitichList = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            HistoricalSite ditich = new HistoricalSite();
            ditich.setName(cols.get(1).text());

            // Get date
            System.out.println(cols.get(2).text());
            Matcher m = Pattern.compile(regex).matcher(cols.get(2).text());
            if (m.find())
                ditich.setDate(m.group(1));

            ditich.setLocation(cols.get(3).text());

            DitichList.add(ditich);
            if (cols.size() == colNum - 1) {
                System.out.println(i);
            }

            Elements details = cols.get(1).select("a");
            if (details != null) {
                int j = 0;
                for (Element element : details) {
                    // System.out.println(element.attr("abs:href"));
                    try {
                        doc = Jsoup.connect(element.attr("abs:href")).timeout(10000).get();
                        Element detail = doc.select(".description").first();
                        String detailText = detail.text();
                        ditich.setDetails(detailText);
                        // System.out.println(detailText);
                    } catch (Exception e) {
                        System.out.println("Link is unavailable");
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(DitichList);
        OutputStreamWriter char_output = new OutputStreamWriter(
                new FileOutputStream("historical_site.json"),
                Charset.forName("UTF-8").newEncoder());
        char_output.write(json);
        char_output.close();
        System.out.println(rows.size());
    }
}
