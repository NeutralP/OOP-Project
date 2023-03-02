package crawl;

// import java.io.*;
// import java.io.IOException;
// import java.nio.charset.Charset;
// import org.json.JSONException;
// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;
// import com.google.gson.Gson;

// import model.Person_King;

// import java.util.*;

public class HistoricalKingCrawl {
    // public static void KingCrawl() throws JSONException {
    // String url = "http://www.hannom.org.vn/detail.asp?param=1020&Catid=493";
    // Document doc = null;

    // try {

    // doc = Jsoup
    // .connect(url)
    // .userAgent("Jsoup client")
    // .timeout(20000).get();

    // Element table = doc.select("table.MsoNormalTable").get(0); //
    // Elements rows = table.select("tr");
    // List<Person_King> siteList = new ArrayList<Person_King>();
    // for (int i = 1; i < rows.size(); i++) {
    // Element row = rows.get(i);
    // Elements cols = row.select("td");
    // Person_King historical_king = new Person_King();
    // historical_king.setNienhieu(cols.get(0).text());
    // historical_king.setDate(cols.get(2).text());
    // historical_king.setName(cols.get(3).text());
    // siteList.add(historical_king);
    // }
    // // Gson gson = new Gson();
    // // String json = gson.toJson(siteList);
    // // OutputStreamWriter char_output = new OutputStreamWriter(
    // // new FileOutputStream("historical_king.json"),
    // // Charset.forName("UTF-8").newEncoder());
    // // char_output.write(json);
    // // char_output.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // }
}
