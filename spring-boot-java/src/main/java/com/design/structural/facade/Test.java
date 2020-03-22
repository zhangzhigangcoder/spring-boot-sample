package com.design.structural.facade;

import java.sql.Connection;

import com.design.structural.facade.HelperFacade.DBTypes;
import com.design.structural.facade.HelperFacade.ReportTypes;

public class Test {
	
	public static void main(String[] args) {
		String tableName = "Employee";
		
		// generating MySql HTML report and Oracle PDF report without using Facade
		Connection con = MySqlHelper.getMySqlDBConnection();
		MySqlHelper mySqlHelper = new MySqlHelper();
		mySqlHelper.generateMySqlHTMLReport(tableName, con);
		
		Connection con1 = OracleHelper.getOracleDBConnection();
		OracleHelper oracleHelper = new OracleHelper();
		oracleHelper.generateOracleHTMLReport(tableName, con1);
		
		// generating MySql HTML report and Oracle PDF report using Facade
		HelperFacade.generateReport(DBTypes.MYSQL, ReportTypes.HTML, tableName);
		HelperFacade.generateReport(DBTypes.ORACLE, ReportTypes.HTML, tableName);
	}
	
}
