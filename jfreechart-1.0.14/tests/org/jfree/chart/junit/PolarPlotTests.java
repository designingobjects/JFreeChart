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
 * ------------------
 * BarChartTests.java
 * ------------------
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 11-Jun-2002 : Version 1 (DG);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 17-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 14-Jul-2003 : Renamed BarChartTests.java (DG);
 *
 */

package org.jfree.chart.junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * Tests for a bar chart.
 */
public class PolarPlotTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(PolarPlotTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public PolarPlotTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	public void testCreatePolarChart_MinimalOptions() {
		JFreeChart chart = createPolarChart_MinimalOptions();

		assertEquals("Polar Chart", chart.getTitle().getText());
		assertNull(chart.getLegend());
		assertNull(((PolarPlot) chart.getPlot()).getRenderer()
				.getURLGenerator());
		assertNull(((PolarPlot) chart.getPlot()).getRenderer()
				.getBaseToolTipGenerator());
	}

	/**
	 * Create a bar chart with sample data in the range -3 to +3.
	 * 
	 * @return The chart.
	 */
	private static JFreeChart createPolarChart_MinimalOptions() {

		// create a dataset...
		double[][] data = { { 1.0, 2.0 }, { 3.0, 4.0 } };

		DefaultXYDataset dataset = new DefaultXYDataset();
		dataset.addSeries("data", data);

		// create the chart...
		return ChartFactory.createPolarChart("Polar Chart", dataset, false,
				false, false);
	}
}
