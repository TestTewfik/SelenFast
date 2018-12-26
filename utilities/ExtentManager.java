package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance() {

		// start reporterscreateInstance
		String htmlFile = TestBase.projectPath+"/src/test/resources/reports/extent-" + TestUtil.getDate() + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(htmlFile);
		htmlReporter.loadXMLConfig("Extent-config.xml");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	
	public static ExtentReports getInstance() {

		if (extent == null) {
			createInstance();
		}
		
		return extent;
	}

}