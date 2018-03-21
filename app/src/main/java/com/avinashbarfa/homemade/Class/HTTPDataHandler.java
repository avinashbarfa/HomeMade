package com.avinashbarfa.homemade.Class;

/**
 * Created by Avinash Barfa on 3/21/2018.
 */
 
public class HTTPDataHandler {
	
	static String stream = null;
	
	public HTTPDataHandler() {
		
	}
	
	public String GetHTTPData(String urlString) {
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			
			if(urlConnection.getResonseCode == 200) {
			    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				BufferedReader r = new BufferedReader(new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String line;
				while((line !=r.readLine()) != null) {
					sb.append(line);
					stream = sb.toString();
					urlConnection.disconnect();
				}				
			} else {
				
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} 
