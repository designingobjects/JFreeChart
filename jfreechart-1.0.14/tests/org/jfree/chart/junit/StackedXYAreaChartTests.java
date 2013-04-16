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
 * --------------------------
 * StackedAreaChartTests.java
 * --------------------------
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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;

/**
 * Some tests for a stacked area chart.
 */
public class StackedXYAreaChartTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(StackedXYAreaChartTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public StackedXYAreaChartTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	public void testCreateStackedXYAreaChart_NullOrientation() {
		try {
			ChartFactory.createStackedXYAreaChart("Area Chart", // chart title
					"Domain", "Range", null, // data
					null, true, // include legend
					true, // tooltips
					true // urls
					);

			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testCreateStackedXYAreaChart() {
		JFreeChart chart = createChart();

		assertNotNull(chart.getLegend());

		assertNotNull(chart.getPlot() instanceof XYPlot);
		XYPlot plot = (XYPlot) chart.getPlot();
		StackedXYAreaRenderer2 renderer = (StackedXYAreaRenderer2) plot
				.getRenderer();

		assertNotNull(renderer.getURLGenerator());
		assertNotNull(renderer.getBaseToolTipGenerator());
	}

	/**
	 * Create a horizontal bar chart with sample data in the range -3 to +3.
	 * 
	 * @return The chart.
	 */
	private static JFreeChart createChart() {
		// create a dataset...
		XYSeries series1 = new XYSeries("Series 1", true, false);
		series1.add(1.0, 2.0);
		series1.add(2.0, 4.0);
		series1.add(3.0, 6.0);

		DefaultTableXYDataset dataset = new DefaultTableXYDataset();
		dataset.addSeries(series1);

		// create the chart...
		return ChartFactory.createStackedXYAreaChart("Step Chart", // chart
																	// title
				"Domain", "Range", dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				true // urls
				);
	}
}
