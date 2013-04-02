package org.jfree.chart.panel.junit;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PanelPackageTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(PanelPackageTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(CrosshairOverlayTests.suite());
		//$JUnit-END$
		return suite;
	}

}
