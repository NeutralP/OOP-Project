

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchJSON {
    public static JSONArray search(JSONArray jsonArray, String keyword) {
      JSONArray result = new JSONArray();
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        if (jsonObject.toString().contains(keyword)) {
          result.put(jsonObject);
        }
      }
      return result;
    }
  }
  
