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
public class CheckIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIn(){
        super(); // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// inputs from index.jsp
		String studentId = request.getParameter("studentId");
		String key = request.getParameter("key");
		String AdminId = request.getParameter("AdminId");
		String AdminKey = request.getParameter("AdminKey");
		String spreadsheetId = "1uOa1xu8k47jSzVswPsb_KMhH1vQ2-YWGCKyoDKBccLk";
		String range = "csc131!A1:Z";
		String timeBegin = "01:30 PM";
		String timeEnd = "02:45 AM";
		Helpers gs = new Helpers();
		Sheets service = gs.getSheetsService();
		String date = gs.getDate();
		String time = gs.getTime();
		int sheetId = 2142932724;
		int columnID = 3;													
		int rowDate = 0;
		int rowKey = 40;

		
		// Student Login
		if(  !(("").equals(studentId)||("").equals(key))  &&  !(studentId==null || key==null)  
				&& studentId.length()==9){		
			//#1 - Set sheet and Values
			ValueRange sheet = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = sheet.getValues();	
			
			int columnDate = gs.searchColumn(values, rowDate, date);
			int rowStudent = gs.searchRow(values, columnID, studentId);
		
			System.out.println("begin to t: " + timeBegin.compareTo(time) + " t to end: " + time.compareTo(timeEnd));
			if (timeBegin.compareTo(time) <= 0 && time.compareTo(timeEnd) <= 0){
				System.out.println("Time: " + time + " true");
			}
			if (columnDate < 0) {												
				request.setAttribute("error", "CLASS NOT IN SESSION!");
				request.getRequestDispatcher("index.jsp").forward(request, response);	
			}else{
				if (rowStudent < 0){											
					request.setAttribute("error", "No User");
					System.out.println(values.get(17).get(columnID).equals(studentId));
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					if (!values.get(rowKey).get(columnDate).equals(key)) {	
						request.setAttribute("error", "WRONG KEY!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}else{
						if (values.get(rowStudent).get(columnDate).equals("Y")) {	
							request.setAttribute("error", "YOU ALREADY CHECKED IN!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}else{
							gs.update(service, spreadsheetId, sheetId, rowStudent, columnDate, "Y");
							request.setAttribute("error", "UPDATED!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}//updated
					}//already checked in
				}// wrongkey
			}// no user
		}//if its a student	
		else{ 
			if(  !(("").equals(AdminId)||("").equals(AdminKey))   &&   !(AdminId==null || AdminKey==null)
					&& AdminId.length()==9){
				studentId = AdminId;
				key = AdminKey;
				range = "AdminLogin!A1:Z";
				sheetId = 1900717557;
				
				ValueRange sheet = service.spreadsheets().values().get(spreadsheetId, range).execute();
				List<List<Object>> values = sheet.getValues();	
				
				int columnDate = gs.searchColumn(values, rowDate, date);
				int rowStudent = gs.searchRow(values, columnID, studentId);	
							
				if (rowStudent < 0){											
					request.setAttribute("error", "No User");
					System.out.println(values.get(17).get(columnID).equals(studentId));
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					if (!values.get(rowKey).get(columnDate).equals(key)) {	
						request.setAttribute("error", "WRONG KEY!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					else{
						gs.redUrl();
					}//redirecting url to google sheets
				}//Wrong Key
			}//If its an Admin
			request.setAttribute("error", "MISSING INPUTS!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}//else	
		//Missing inputs for either Admin or Student
	}//doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}//doPost
}//Entire class