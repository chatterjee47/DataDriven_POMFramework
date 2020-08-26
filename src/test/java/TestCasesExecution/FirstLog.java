package TestCasesExecution;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FirstLog {

	static Logger log = Logger.getLogger(FirstLog.class);
	public static void main(String[] args) {
		//PropertiesConfigurator is used to configure logger from a properties file
        PropertyConfigurator.configure("C:\\Users\\Rush 14\\workspace\\ReadExcelData_DataProvider\\config\\log4j.properties");

        //log the message to file
        log.trace("This is a debug message");
        log.info("This is an info message");
	}
}
