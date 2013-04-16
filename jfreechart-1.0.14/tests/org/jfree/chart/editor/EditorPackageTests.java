package org.jfree.chart.editor;

import junit.framework.Test;
import junit.framework.TestSuite;

public class EditorPackageTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(EditorPackageTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTest(DefaultChartEditorTests.suite());
		//$JUnit-END$
		return suite;
	}

}
