

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReadJSON {
  public static JSONArray readDataFromJSON(String fileName) {
    JSONArray jsonArray = null;
    try {
      FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
      jsonArray = new JSONArray(new JSONTokener(reader));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return jsonArray;
  }
}
