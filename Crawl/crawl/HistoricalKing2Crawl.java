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

import model.Person_King2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoricalKing2Crawl {
    private static List<Person_King2> siteList = new ArrayList<Person_King2>();

    public static void KingCrawl() throws JSONException {
        String url = "http://www.hannom.org.vn/detail.asp?param=1020&Catid=493";
        Document doc = null;

        try {

            doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(20000).get();

            Element table = doc.select("table.MsoNormalTable").get(0); //
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                Person_King2 historical_king = new Person_King2();
                historical_king.setNienhieu(cols.get(0).text());
                historical_king.setDate(cols.get(2).text());
                historical_king.setName(cols.get(3).text());
                siteList.add(historical_king);
            }
            // Gson gson = new Gson();
            // String json = gson.toJson(siteList);
            // OutputStreamWriter char_output = new OutputStreamWriter(
            // new FileOutputStream("historical_king.json"),
            // Charset.forName("UTF-8").newEncoder());
            // char_output.write(json);
            // char_output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void King2Crawl() throws JSONException {
        String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
        Document doc = null;

        try {

            doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(20000).get();
            Elements tables = doc.select("table");
            for (Element table : tables) {
                String keyword = "Th";
                Elements tr = table.select("tr:eq(3)");
                Elements td = tr.select("td");
                for (Element cell : td) {
                    if (cell.text().contains(keyword)) {
                        Elements link;
                        Document doc_link;
                        Elements rows = table.select("tr");

                        for (int i = 1; i < rows.size(); i++) {
                            Element row = rows.get(i);
                            Elements cols = row.select("td");
                            Person_King2 crawlTest = new Person_King2();
                            String json1 = cols.get(1).text();
                            Pattern pattern1 = Pattern.compile("\\[.*?\\]");
                            Matcher matcher1 = pattern1.matcher(json1);
                            json1 = matcher1.replaceAll("");
                            crawlTest.setName(json1);
                            String json2 = cols.get(2).text();
                            Pattern pattern2 = Pattern.compile("\\[.*?\\]");
                            Matcher matcher2 = pattern2.matcher(json2);
                            json2 = matcher2.replaceAll("");
                            if (json2.contains("kh")) {
                                crawlTest.setMienhieu("");
                            } else
                                crawlTest.setMienhieu(json2);
                            String json3 = cols.get(3).text();
                            Pattern pattern3 = Pattern.compile("\\[.*?\\]");
                            Matcher matcher3 = pattern3.matcher(json3);
                            json3 = matcher3.replaceAll("");
                            if (json3.contains("kh")) {
                                crawlTest.setThuyhieu("");
                            } else
                                crawlTest.setThuyhieu(json3);
                            String json4 = cols.get(4).text();
                            Pattern pattern4 = Pattern.compile("\\[.*?\\]");
                            Matcher matcher4 = pattern4.matcher(json4);
                            json4 = matcher4.replaceAll("");
                            if (json4.contains("kh")) {
                                crawlTest.setNienhieu("");
                            } else
                                crawlTest.setNienhieu(json4);
                            String json7 = cols.get(7).text();
                            Pattern pattern7 = Pattern.compile("\\[.*?\\]");
                            Matcher matcher7 = pattern7.matcher(json7);
                            json7 = matcher7.replaceAll("");
                            crawlTest.setPeriod(json7);
                            String json6 = cols.get(6).text();
                            Pattern pattern6 = Pattern.compile("\\[.*?\\]");
                            Matcher matcher6 = pattern6.matcher(json6);
                            json6 = matcher6.replaceAll("");
                            crawlTest.setPeriod(json6);

                            link = cols.get(1).select("a[href]");
                            if (link.attr("abs:href") != "") {
                                try {
                                    doc_link = Jsoup.connect(link.attr("abs:href")).timeout(10000).get();
                                    crawlTest
                                            .setUrl(
                                                    doc_link.select("a[href]").attr("abs:href"));

                                } catch (Exception e) {
                                    System.out.println("Link is unavailable");
                                }
                            }
                            siteList.add(crawlTest);
                        }

                    }
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(siteList);
            OutputStreamWriter char_output = new OutputStreamWriter(
                    new FileOutputStream("historical_king.json"), Charset.forName("UTF-8").newEncoder());
            char_output.write(json);
            char_output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // public static void KingCrawl_Combine() {
    public static void KingCrawl_Combine() {
        KingCrawl();
        King2Crawl();
    }
}