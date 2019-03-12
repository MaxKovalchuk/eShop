//package rest;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.google.gson.Gson;
//
//import pack.Pudge;
//
//public class Main {
//	public static void main(String[] args) {
//		try {
//		      URL url = new URL("http://172.17.13.231/maximus/products.php");
//		      HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
//		      urlcon.setRequestMethod("GET");
////		      String param = "name=Daniel";
////		      urlcon.setRequestProperty("Content-Length",""+ Integer.toString((param.getBytes().length)));
//		      urlcon.setDoInput(true);
//		      urlcon.setDoOutput(true);
//		      OutputStream os = urlcon.getOutputStream();
////		      os.write(param.getBytes("UTF-8"));
////		      urlcon.connect();
//		      InputStream is = urlcon.getInputStream();
//		      BufferedReader br = new BufferedReader(new InputStreamReader(is));
//		      String line;
//		      StringBuilder sb = new StringBuilder();
//		      while((line = br.readLine()) != null) 
//		      {
//		       sb.append(line);
//		       
//		      }
//		      System.out.println(sb.toString());
//		      Gson gson = new Gson();
//		      Products products = gson.fromJson(sb.toString(), Products.class);
//		      System.out.println(products);
//		      ProductRest prod = gson.fromJson(sb.toString(), ProductRest.class);
//		      System.out.println(prod);
//		      is.close();
//		    } catch (MalformedURLException e) {
//		      e.printStackTrace();
//		    } catch (IOException e) {
//		      e.printStackTrace();
//		    }
//	}
//}
