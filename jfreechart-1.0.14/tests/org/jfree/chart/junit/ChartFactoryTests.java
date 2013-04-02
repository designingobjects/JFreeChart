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
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GradientBarPainter;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.GradientXYBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;

/**
 * Tests for an area chart.
 */
public class ChartFactoryTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(ChartFactoryTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public ChartFactoryTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
	}

	/**
	 * Basic checks for cloning.
	 */
	public void testSetChartTheme_NullTheme() {
		try {
			ChartFactory.setChartTheme(null);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testSetChartTheme_StandardChartThemeLegacy() {
		ChartTheme expected = new StandardChartTheme("Legacy");
		ChartFactory.setChartTheme(expected);
		assertTrue(ChartFactory.getChartTheme() instanceof StandardChartTheme);
		assertTrue(BarRenderer.getDefaultBarPainter() instanceof StandardBarPainter);
		assertTrue(XYBarRenderer.getDefaultBarPainter() instanceof StandardXYBarPainter);
	}

	public void testSetChartTheme_StandardChartThemeNonLegacy() {
		ChartTheme theme = new StandardChartTheme("Non Legacy");
		ChartFactory.setChartTheme(theme);
		assertTrue(ChartFactory.getChartTheme() instanceof StandardChartTheme);
		assertTrue(BarRenderer.getDefaultBarPainter() instanceof GradientBarPainter);
		assertTrue(XYBarRenderer.getDefaultBarPainter() instanceof GradientXYBarPainter);
	}

	public void testSetChartTheme_NonStandardChartTheme() {
		ChartTheme theme = new ChartThemeTests();
		ChartFactory.setChartTheme(theme);
		assertTrue(ChartFactory.getChartTheme() instanceof ChartThemeTests);
		assertTrue(BarRenderer.getDefaultBarPainter() instanceof GradientBarPainter);
		assertTrue(XYBarRenderer.getDefaultBarPainter() instanceof GradientXYBarPainter);
	}

	// For testing purposes
	private class ChartThemeTests implements ChartTheme {

		@Override
		public void apply(JFreeChart chart) {

		}

	}

}