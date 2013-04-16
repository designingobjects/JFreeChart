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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.data.general.WaferMapDataset;

/**
 * Tests for an area chart.
 */
public class WaferMapChartTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(WaferMapChartTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public WaferMapChartTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	public void testCreateChart() {
		JFreeChart chart = createChart();

		assertEquals("Wafer Chart", chart.getTitle().getText());
		assertNotNull(chart.getLegend());
		assertNotNull(chart.getPlot());
		assertTrue(chart.getPlot() instanceof WaferMapPlot);
	}

	public void testCreateChart_MinimalOptions() {
		JFreeChart chart = createChart_MinimalOptions();

		assertEquals("Wafer Chart", chart.getTitle().getText());
		assertNull(chart.getLegend());
		assertNotNull(chart.getPlot());
		assertTrue(chart.getPlot() instanceof WaferMapPlot);
	}

	public void testCreateChart_NullOrientation() {
		try {
			ChartFactory.createWaferMapChart("Wafer Chart", null, null, true,
					true, true);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Create an area chart with sample data in the range -3 to +3.
	 * 
	 * @return The chart.
	 */
	private static JFreeChart createChart() {
		WaferMapDataset dataset = new WaferMapDataset(0, 5);
		dataset.addValue(1, 2, 1);
		dataset.addValue(2, 1, 3);
		dataset.addValue(3, 3, 2);
		return ChartFactory.createWaferMapChart("Wafer Chart", dataset,
				PlotOrientation.HORIZONTAL, true, true, true);
	}

	private static JFreeChart createChart_MinimalOptions() {
		WaferMapDataset dataset = new WaferMapDataset(0, 5);
		dataset.addValue(1, 2, 1);
		dataset.addValue(2, 1, 3);
		dataset.addValue(3, 3, 2);
		return ChartFactory.createWaferMapChart("Wafer Chart", dataset,
				PlotOrientation.HORIZONTAL, false, false, false);
	}
}
