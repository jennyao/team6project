package com.team6.project;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

/**
 * Servlet implementation class CheckIn
 */
public class CheckIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String studentId = request.getParameter("studentId");					// inputs from index.jsp
		String key = request.getParameter("key");
		
		if (("").equals(studentId) || ("").equals(key)) {								// check for inputs; no inputs; error
			request.setAttribute("error", "MISSING INPUTS!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {																// inputs; continue
			Helpers gs = new Helpers();
			Sheets service = gs.getSheetsService();
			String spreadsheetId = "1xM90oj3BDOuQNvDU-T0W1BleX9TKHJ-ElyXVlzp29ig";
			String range = "csc-131-2-attandance!A1:Z";
			int sheetId = 1764605134;
			ValueRange sheet = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = sheet.getValues();
			
			int columnID = 3;													// sheet setup
			int rowDate = 0;
			int rowKey = 40;
			
			String date = gs.getDate();
			String time = gs.getTime();

			String timeBegin = "01:30 PM";
			String timeEnd = "02:45 PM";
//			System.out.println("int end: %i" + Integer.parseInt(timeEnd));
//			System.out.println("int time: %i" + Integer.parseInt(time));
			
			System.out.println("begin to t: " + timeBegin.compareTo(time) + " t to end: " + time.compareTo(timeEnd));
			if (timeBegin.compareTo(time) <= 0 && time.compareTo(timeEnd) <= 0) 
				System.out.println("Time: " + time + " true");
			
			int columnDate = gs.searchColumn(values, rowDate, date);
			if (columnDate < 0) {												// check for date; date not found; error
				request.setAttribute("error", "NO CLASS IN SESSION!");
				request.getRequestDispatcher("index.jsp").forward(request, response);				
			} else {															// date found; continue
				int rowStudent = gs.searchRow(values, columnID, studentId);
				if (rowStudent < 0) {											// check for student; student not found; error
					request.setAttribute("error", "ID NOT FOUND!");
					System.out.println(values.get(17).get(columnID).equals(studentId));
					System.out.println(studentId);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else { 														// student found; continue
					if (values.get(rowStudent).get(columnDate).equals("Y")) {	// check for status; already checked in; error
						request.setAttribute("error", "YOU ALREADY CHECKED IN!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else {													// has not checked in; continue
						if (!values.get(rowKey).get(columnDate).equals(key)) {	// check for key; wrong key; error
							request.setAttribute("error", "WRONG KEY!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						} else {												// correct key; check in; end!
							gs.update(service, spreadsheetId, sheetId, rowStudent, columnDate, "Y");
							request.setAttribute("error", "UPDATED!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}