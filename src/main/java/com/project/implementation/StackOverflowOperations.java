package com.project.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

import com.project.dto.Tag;

public class StackOverflowOperations {

	public List<Tag> getTopTrending(){
		ArrayList<Tag> list = null;
	   	String url = "https://api.stackexchange.com/2.0/tags?order=desc&sort=popular&site=stackoverflow";
        try {
 			String jsonString = callURL(url);
 			JSONObject jsonObject = new JSONObject(jsonString);
 			JSONArray  itemArray = jsonObject.getJSONArray("items");
 			Tag tagObj;
 			list = new ArrayList<Tag>();
 			for(int i = 0; i < itemArray.length(); i++){
 				String tagName = itemArray.getJSONObject(i).getString("name");
 				int tagCount = itemArray.getJSONObject(i).getInt("count");
 				//System.out.println(tagName + "==>" + tagCount);
 				tagObj = new Tag();
 				tagObj.setCount(tagCount);
 				tagObj.setName(tagName);
 				list.add(tagObj);
 			}
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
        return list;
	}
	
    public static String callURL(String myURL) {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				GZIPInputStream gs = new GZIPInputStream(urlConn.getInputStream());
				in = new InputStreamReader(gs,
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
		return sb.toString();
	}
	
}
