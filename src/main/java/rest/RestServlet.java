package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

@Controller
@RequestMapping("/rest")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String url = "http://172.17.13.231/maximus/products.php";
	
	public RestServlet() {
	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(ModelMap model) {
		try {
		      URL url = new URL(this.url);
		      HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
		      urlcon.setRequestMethod("GET");
//		      String param = "name=Daniel";
//		      urlcon.setRequestProperty("Content-Length",""+ Integer.toString((param.getBytes().length)));
		      urlcon.setDoInput(true);
		      urlcon.setDoOutput(true);
		      OutputStream os = urlcon.getOutputStream();
//		      os.write(param.getBytes("UTF-8"));
//		      urlcon.connect();
		      InputStream is = urlcon.getInputStream();
		      BufferedReader br = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuilder sb = new StringBuilder();
		      while((line = br.readLine()) != null) 
		      {
		       sb.append(line);
		      }
		      is.close();
		      os.close();
		      System.out.println(sb.toString());
		      Gson gson = new Gson();
		      Products products = gson.fromJson(sb.toString(), Products.class);
		      model.addAttribute("response", products.toString());
		      
		    } catch (MalformedURLException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		return "rest";
	}
}
