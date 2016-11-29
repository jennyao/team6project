package com.team6.project;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

//Servlet implementation class CheckIn
public class AdminL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminL(){
        super(); // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// inputs from index.jsp
		//String studentId = request.getParameter("studentId");
		//String key = request.getParameter("key");
		//String AdminId = request.getParameter("AdminId");
		//String AdminKey = request.getParameter("AdminKey");
		String spreadsheetId = "1uOa1xu8k47jSzVswPsb_KMhH1vQ2-YWGCKyoDKBccLk";
		String range = "csc131!A1:Z";
		//String timeBegin = "01:30 PM";
		//String timeEnd = "02:45 AM";
		Helpers gs = new Helpers();
		Sheets service = gs.getSheetsService();
		String date = gs.getDate();
		//String time = gs.getTime();
		String keygen = gs.makeId();
		int sheetId = 2142932724;
		int columnID = 3;													
		int rowDate = 0;
		int rowKey = 40;
		String rndP = request.getParameter("rndBtn");

		
		if( rndP=="Generate Random Key"  ){
			ValueRange sheet = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = sheet.getValues();
			
			int columnDate = gs.searchColumn(values, rowDate, date);
			gs.update(service, spreadsheetId, sheetId, rowKey, columnDate, keygen);
			
			//request.setAttribute(keygen, "No User");
			//	System.out.println(values.get(17).get(columnID).equals(studentId));
			//request.getRequestDispatcher("adminSuccess.jsp").forward(request, response);
		}
	}//doGet
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}//doPost
}//Entire class