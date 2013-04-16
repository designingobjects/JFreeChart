/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2011, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -------------------
 * AreaChartTests.java
 * -------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 12-Apr-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.junit;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

/**
 * Tests for an area chart.
 */
public class BoxAndWhiskerChartTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(BoxAndWhiskerChartTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public BoxAndWhiskerChartTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	public void testCreateChart_UsingCategoryDataset() {
		JFreeChart chart = createChart_UsingCategoryDataset();

		assertEquals("Box and Whisker Chart", chart.getTitle().getText());
		assertNotNull(chart.getLegend());
		assertNotNull(chart.getPlot());
		assertTrue(((CategoryPlot) chart.getPlot()).getRenderer() instanceof BoxAndWhiskerRenderer);
	}

	/**
	 * Create an area chart with sample data in the range -3 to +3.
	 * 
	 * @return The chart.
	 */
	private static JFreeChart createChart_UsingCategoryDataset() {
		ArrayList<String> data = new ArrayList<String>();
		data.add("a");
		data.add("b");

		DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		dataset.add(data, "row1", "col1");
		return ChartFactory.createBoxAndWhiskerChart("Box and Whisker Chart",
				"Category Axis", "Value Axis", dataset, true);

	}
}
