package com.lgh.common.tools.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;


public class JsonUtil {
	 public static void outToJson(HttpServletResponse response, Object data) 
		        throws IOException {
		        ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("text/json; charset=utf-8");
		        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
		        PrintWriter out = response.getWriter();
		        
		        
		         String s = "";
		         s =  mapper.writeValueAsString(data);
		         
		         
		        out.print(s);
		        out.flush();
		        out.close();
		    }

}
