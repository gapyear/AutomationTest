package com.automation.testsuite;

import java.util.ArrayList;
import java.util.List;

public class TestThread {
	
	public static void main(String[] args) throws Exception {
		
		List<String> casepath = new ArrayList<String>();
		casepath.add("case1_login");
		casepath.add("case2_addUser");
		casepath.add("case3_editUser");
		
		for(int i=0;i<casepath.size();i++){
			ThreadRunCases tc = new ThreadRunCases(casepath.get(i));
			tc.start();
		}
	}
}
