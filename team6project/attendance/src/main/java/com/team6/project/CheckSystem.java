package com.team6.project;

public class CheckSystem {

	public static boolean checkInputs(String studentID, String key) {
		return (studentID.equals("") || key.equals("")) ? true : false;
	}

}
