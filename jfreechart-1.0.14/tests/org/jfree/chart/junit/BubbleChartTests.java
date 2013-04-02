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
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 * Tests for an area chart.
 */
public class BubbleChartTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(BubbleChartTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public BubbleChartTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	public void testCreateChart() {
		JFreeChart chart = createChart();

		assertEquals("Bubble Chart", chart.getTitle().getText());
		assertNotNull(chart.getLegend());
		assertNotNull(chart.getPlot());
		assertTrue(((XYPlot) chart.getPlot()).getRenderer() instanceof XYBubbleRenderer);

		XYBubbleRenderer renderer = (XYBubbleRenderer) ((XYPlot) chart
				.getPlot()).getRenderer();
		assertNotNull(renderer.getBaseToolTipGenerator());
		assertNotNull(renderer.getURLGenerator());
	}

	public void testCreateChart_MinimalOptions() {
		JFreeChart chart = createChart_MinimalOptions();

		assertEquals("Bubble Chart", chart.getTitle().getText());
		assertNull(chart.getLegend());
		assertNotNull(chart.getPlot());
		assertTrue(((XYPlot) chart.getPlot()).getRenderer() instanceof XYBubbleRenderer);

		XYBubbleRenderer renderer = (XYBubbleRenderer) ((XYPlot) chart
				.getPlot()).getRenderer();
		assertNull(renderer.getBaseToolTipGenerator());
		assertNull(renderer.getURLGenerator());
	}

	public void testCreateChart_NullOrientation() {
		try {
			ChartFactory.createBubbleChart("Bubble Chart", "X Axis", "Y Axis",
					null, null, false, false, false);
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

		double[][] data = { { 1, 2 }, { 3, 4 }, { 5, 6 } };

		DefaultXYZDataset dataset = new DefaultXYZDataset();
		dataset.addSeries("data", data);
		return ChartFactory
				.createBubbleChart("Bubble Chart", "X Axis", "Y Axis", dataset,
						PlotOrientation.HORIZONTAL, true, true, true);
	}

	private static JFreeChart createChart_MinimalOptions() {

		double[][] data = { { 1, 2 }, { 3, 4 }, { 5, 6 } };

		DefaultXYZDataset dataset = new DefaultXYZDataset();
		dataset.addSeries("data", data);
		return ChartFactory.createBubbleChart("Bubble Chart", "X Axis",
				"Y Axis", dataset, PlotOrientation.VERTICAL, false, false,
				false);
	}
}
