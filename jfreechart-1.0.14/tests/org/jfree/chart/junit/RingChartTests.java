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

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Tests for an area chart.
 */
public class RingChartTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(RingChartTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public RingChartTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	public void testCreateRingChart_WithoutURL() {
		JFreeChart ring = createRingChart_WithoutURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(ring);

		assertEquals("Ring Chart", ring.getTitle().getText());
		assertNotNull(ring.getLegend());
		assertTrue(ring.getPlot() instanceof RingPlot);
		assertNotNull(((RingPlot) ring.getPlot()).getToolTipGenerator());
	}

	public void testCreateRingChart_WithoutURLMinimalOptions() {
		JFreeChart ring = createRingChart_WithoutURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(ring);

		assertEquals("Ring Chart", ring.getTitle().getText());
		assertNull(ring.getLegend());
		assertTrue(ring.getPlot() instanceof RingPlot);
		assertNull(((RingPlot) ring.getPlot()).getToolTipGenerator());
	}

	public void testCreateRingChart_EnabledURL() {
		JFreeChart ring = createRingChart_EnabledURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(ring);

		assertEquals("Ring Chart", ring.getTitle().getText());
		assertNotNull(ring.getLegend());
		assertTrue(ring.getPlot() instanceof RingPlot);
		assertNotNull(((RingPlot) ring.getPlot()).getToolTipGenerator());
		assertNotNull(((RingPlot) ring.getPlot()).getURLGenerator());
	}

	public void testCreateRingChart_EnabledURLMinimalOptions() {
		JFreeChart ring = createRingChart_EnabledURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(ring);

		assertEquals("Ring Chart", ring.getTitle().getText());
		assertNull(ring.getLegend());
		assertTrue(ring.getPlot() instanceof RingPlot);
		assertNull(((RingPlot) ring.getPlot()).getToolTipGenerator());
		assertNull(((RingPlot) ring.getPlot()).getURLGenerator());
	}

	private static JFreeChart createRingChart_WithoutURL() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createRingChart("Ring Chart", // chart title
				data, // data
				true, // include legend
				true, new Locale("en"));
	}

	private static JFreeChart createRingChart_WithoutURLMinimalOptions() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createRingChart("Ring Chart", // chart title
				data, // data
				false, // include legend
				false, new Locale("en"));
	}

	private static JFreeChart createRingChart_EnabledURL() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createRingChart("Ring Chart", // chart title
				data, // data
				true, // include legend
				true, true);
	}

	private static JFreeChart createRingChart_EnabledURLMinimalOptions() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createRingChart("Ring Chart", // chart title
				data, // data
				false, // include legend
				false, false);
	}
}
