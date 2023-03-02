package crawl;

import com.google.gson.Gson;
import model.Event;
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

public class HistoricalEventCrawl {

    public static void EventCrawl() throws IOException {
        int first_paragraph = 0;
        String name = "";
        String startYear = "";
        String endYear = "";
        String trieuDai = "";
        String smalltrieuDai = "";
        List<Event> HisEventList = new ArrayList<Event>();
        Document doc = Jsoup
                .connect(
                        "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam")
                .get();
        Elements head1 = doc.select("#mw-content-text > div.mw-parser-output > h2, h3, p, dl > dd");
        Elements head2 = doc.select("mw-content-text > div.mw-parser-output > h3");
        Elements p = doc.select("#mw-content-text > div.mw-parser-output > dl");
        // Elements rows = table.select("tr");

        for (int i = 0; i < head1.size(); i++) {
            Element e = head1.get(i);
            if (!e.tagName().equals("h2") && first_paragraph == 0) {
                continue;
            } else {
                first_paragraph = 1;
            }
            if (e.text().equals("Tham khảo[sửa | sửa mã nguồn]"))
                break;
            // System.out.println(e.text());
            name = "";
            startYear = "";
            endYear = "";
            if (e.cssSelector().contains("#mw-content-text > div.mw-parser-output > h2")) {
                trieuDai = e.text().replace("[sửa | sửa mã nguồn]", "");
                // System.out.println(" ||| " + trieuDai + " ||| ");
            }
            if (e.cssSelector().contains("#mw-content-text > div.mw-parser-output > h3")) {
                smalltrieuDai = e.text().replace("[sửa | sửa mã nguồn]", "");
                ;
                System.out.println(" ||| " + smalltrieuDai + " ||| ");
            }
            if (e.cssSelector().contains("#mw-content-text > div.mw-parser-output > p")) {
                // head1.get(i+1).cssSelector().contains("#mw-content-text >
                // div.mw-parser-output > dl"
                if (head1.get(i + 1).cssSelector().contains("#mw-content-text > div.mw-parser-output > dl"))// Year -
                                                                                                            // Format 1
                                                                                                            // TH
                {
                    startYear = e.text().replaceAll("\\D+", "");
                    endYear = startYear;
                    int j = i;
                    do {
                        Element e2 = head1.get(++j);
                        name = e2.text();
                        Event h = new Event(name, startYear, endYear, trieuDai, smalltrieuDai);
                        HisEventList.add(h);
                        // System.out.println("." + name);
                    } while (head1.get(j + 1).cssSelector().contains("dd"));
                    // System.out.println(head1.get(j).cssSelector());
                } else {
                    // System.out.println(e.cssSelector());
                    Pattern pattern = Pattern.compile("(\\d*\\.?\\d*)");
                    Matcher matcher = pattern.matcher(e.text());
                    int cnt = 0;
                    for (int j = 0; j < 8; j++) {
                        matcher.find();
                        if (cnt == 0) {
                            startYear = matcher.group(1);
                            cnt++;
                        }
                        if (matcher.group(1) != "") {
                            endYear = matcher.group(1);
                        }
                    }
                    ;
                    if (Pattern.compile("TCN").matcher(e.text()).find()) {
                        startYear += " TCN";
                        endYear += " TCN";
                    }
                    // System.out.println(startYear + " - " + endYear);
                    // System.out.println(e.text());

                    name = e.text().replaceAll("\\d*\\.?\\d*", "");
                    name = name.replaceAll("TCN", "");
                    name = name.replaceAll("-", "").trim();
                    name = name.replaceAll("–", "").trim();
                    // System.out.println(name);
                    Event h = new Event(name, startYear, endYear, trieuDai, smalltrieuDai);
                    HisEventList.add(h);
                }
            }

            // System.out.println(name);
            // System.out.println(e.cssSelector());
            // System.out.println(e.text());
        }

        // for (int i = 1; i < rows.size() - 1; i++) {
        // Element row = rows.get(i);
        // Elements cols = row.select("td");
        // Lehoi lehoi = new Lehoi();
        // lehoi.setDate(cols.get(0).text());
        // lehoi.setLocation(cols.get(1).text());
        // lehoi.setName(cols.get(2).text());
        // lehoi.setFirstDate(cols.get(3).text());
        // lehoi.setRelatedNvat(cols.get(4).text());
        // lehoiList.add(lehoi);
        // if(cols.size() == 6) {
        // lehoi.setNote(cols.get(5).text());
        // }
        //
        // Elements details = cols.get(2).select("a");
        // if (details != null) {
        // int j = 0;
        // for (Element element : details) {
        // try {
        // doc = Jsoup.connect(element.attr("abs:href")).timeout(10000).get();
        // Element detail = doc.select(".mw-body-content.mw-content-ltr p").first();
        // String detailText = detail.text();
        // lehoi.setDetail(detailText);
        // } catch (Exception e) {
        // System.out.println("Link is unavailable");
        // }
        // }
        // }
        // }
        // Element link = doc.select("a").first();
        // String absHref = link.attr("abs:href"); // == "/"
        // System.out.println(absHref);
        Gson gson = new Gson();
        String json = gson.toJson(HisEventList);
        // FileWriter writer = new FileWriter(".json");
        // writer.write(json);
        // writer.close();
        OutputStreamWriter char_output = new OutputStreamWriter(
                new FileOutputStream("historical_event.json"),
                Charset.forName("UTF-8").newEncoder());
        char_output.write(json);
        char_output.close();
    }

}
