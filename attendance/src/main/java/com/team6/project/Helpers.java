package com.team6.project;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;

public class Helpers extends GoogleStuff {
	
	public String getDate() {
		SimpleDateFormat date = new SimpleDateFormat("M/dd/yyyy");
		return date.format(new Date());
	}
	
	public String getTime() {
		SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
		return time.format(new Date());
	}

	public int searchColumn(List<List<Object>> values, int row, String text) {
		int counter = 0;
		
		for (Object column : values.get(row)) {
			if (column.equals(text)) {
				return counter;
			}
			++counter;
		}		
		return -1;
	}
	
	public int searchRow(List<List<Object>> values, int column, String text) {
		int counter = 0;
        	
		for (List<Object> row : values) {
			if (row.get(column).equals(text)) {
				return counter;
        	}
			++counter;
        }	
		return -1;
	} 
        
	public boolean check(List<List<Object>> values, int row, int column, String text) { 	
		return (values.get(row).get(column).equals(text)) ? true : false;
	}
        
	public void update(Sheets service, String spreadsheetId, int sheetId, int row, int column, String text) throws IOException {
		// Create requests object
		List<Request> requests = new ArrayList<Request>();

		// Create values object
		List<CellData> values = new ArrayList<CellData>();
            
		// Add string 6/21/2016 value
		values.add(new CellData().setUserEnteredValue(new ExtendedValue().setStringValue((text))));

		// Prepare request with proper row and column and its value
		requests.add(new Request()
				.setUpdateCells(new UpdateCellsRequest()
				.setStart(new GridCoordinate()
				.setSheetId(sheetId)
				.setRowIndex(row)
				.setColumnIndex(column)) // set the new column 6 to value 9/12/2016 at row 0
				.setRows(Arrays.asList(new RowData().setValues(values)))
				.setFields("userEnteredValue,userEnteredFormat.backgroundColor")));
		
		BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest).execute();
	}
	
}
