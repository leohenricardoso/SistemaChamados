package br.com.sistemachamados.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/DataHora")
public class DataHora extends HttpServlet{
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		
		PrintWriter out;
		try {
			out = response.getWriter();
			Date d= new Date();
			SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
			out.println(""+df.format(d));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
