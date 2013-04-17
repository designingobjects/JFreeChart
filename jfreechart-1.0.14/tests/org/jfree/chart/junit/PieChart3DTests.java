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
 * --------------------
 * PieChart3DTests.java
 * --------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 21-May-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.junit;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.TableOrder;

/**
 * Tests for a pie chart with a 3D effect.
 */
public class PieChart3DTests extends TestCase {

	/** A chart. */
	private JFreeChart pieChart;

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(PieChart3DTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public PieChart3DTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Java", new Double(43.2));
		dataset.setValue("Visual Basic", new Double(0.0));
		dataset.setValue("C/C++", new Double(17.5));
		this.pieChart = createPieChart3D(dataset);
	}

	/**
	 * Using a regular pie chart, we replace the dataset with null. Expect to
	 * receive notification of a chart change event, and (of course) the dataset
	 * should be null.
	 */
	public void testReplaceDatasetOnPieChart() {
		LocalListener l = new LocalListener();
		this.pieChart.addChangeListener(l);
		PiePlot plot = (PiePlot) this.pieChart.getPlot();
		plot.setDataset(null);
		assertEquals(true, l.flag);
		assertNull(plot.getDataset());
	}

	/**
	 * Tests that no exceptions are thrown when there is a <code>null</code>
	 * value in the dataset.
	 */
	public void testNullValueInDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Section 1", 10.0);
		dataset.setValue("Section 2", 11.0);
		dataset.setValue("Section 3", null);
		JFreeChart chart = createPieChart3D(dataset);
		boolean success = false;
		try {
			BufferedImage image = new BufferedImage(200, 100,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			chart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null, null);
			g2.dispose();
			success = true;
		} catch (Throwable t) {
			success = false;
		}
		assertTrue(success);
	}

	public void testCreatePieChart3D_WithoutURL() {
		JFreeChart pie = createPieChart3D_WithoutURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart 3D", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof PiePlot3D);
		assertNotNull(((PiePlot3D) pie.getPlot()).getToolTipGenerator());
	}

	public void testCreatePieChart3D_WithoutURLMinimalOptions() {
		JFreeChart pie = createPieChart3D_WithoutURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart 3D", pie.getTitle().getText());
		assertNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof PiePlot3D);
		assertNull(((PiePlot3D) pie.getPlot()).getToolTipGenerator());
	}

	public void testCreatePieChart3D_EnabledURL() {
		JFreeChart pie = createPieChart3D_EnabledURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart 3D", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof PiePlot3D);
		assertNotNull(((PiePlot3D) pie.getPlot()).getToolTipGenerator());
		assertNotNull(((PiePlot3D) pie.getPlot()).getURLGenerator());
	}

	public void testCreatePieChart3D_EnabledURLMinimalOptions() {
		JFreeChart pie = createPieChart3D_EnabledURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart 3D", pie.getTitle().getText());
		assertNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof PiePlot3D);
		assertNull(((PiePlot3D) pie.getPlot()).getToolTipGenerator());
		assertNull(((PiePlot3D) pie.getPlot()).getURLGenerator());
	}

	public void testCreateMultiplePieChart3D_EnabledURLByRow() {
		JFreeChart pie = createMultiplePieChart3D_EnabledURLByRow();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Multiple Pie Chart 3D", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof MultiplePiePlot);
		MultiplePiePlot mpp = (MultiplePiePlot) pie.getPlot();
		assertEquals(TableOrder.BY_ROW, mpp.getDataExtractOrder());
		assertTrue(mpp.getPieChart().getPlot() instanceof PiePlot3D);
		PiePlot3D pp = ((PiePlot3D) mpp.getPieChart().getPlot());
		assertNotNull(pp.getToolTipGenerator());
		assertNotNull(pp.getURLGenerator());
	}

	public void testCreateMultiplePieChart3D_EnabledURLByCol() {
		JFreeChart pie = createMultiplePieChart3D_EnabledURLByCol();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Multiple Pie Chart 3D", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof MultiplePiePlot);
		MultiplePiePlot mpp = (MultiplePiePlot) pie.getPlot();
		assertEquals(TableOrder.BY_COLUMN, mpp.getDataExtractOrder());
		assertTrue(mpp.getPieChart().getPlot() instanceof PiePlot3D);
		PiePlot3D pp = ((PiePlot3D) mpp.getPieChart().getPlot());
		assertNotNull(pp.getToolTipGenerator());
		assertNotNull(pp.getURLGenerator());
	}

	public void testCreateMultiplePieChart3D_EnabledURLMinimalOptions() {
		JFreeChart pie = createMultiplePieChart3D_EnabledURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Multiple Pie Chart 3D", pie.getTitle().getText());
		assertNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof MultiplePiePlot);
		assertTrue(((MultiplePiePlot) pie.getPlot()).getPieChart().getPlot() instanceof PiePlot3D);
		PiePlot pp = ((PiePlot) ((MultiplePiePlot) pie.getPlot()).getPieChart()
				.getPlot());
		assertNull(pp.getToolTipGenerator());
		assertNull(pp.getURLGenerator());
	}

	public void testCreateMultiplePieChart3D_NullTableOrder() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");

		try {
			ChartFactory.createMultiplePieChart3D("Multiple Pie Chart 3D",
					dataSet, null, false, false, false);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Creates a pie chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return The pie chart.
	 */
	private static JFreeChart createPieChart3D(PieDataset dataset) {

		return ChartFactory.createPieChart3D("Pie Chart", // chart title
				dataset, // data
				true, // include legend
				true, false);
	}

	private static JFreeChart createPieChart3D_WithoutURL() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createPieChart3D("Pie Chart 3D", // chart title
				data, // data
				true, // include legend
				true, new Locale("en"));
	}

	private static JFreeChart createPieChart3D_WithoutURLMinimalOptions() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createPieChart3D("Pie Chart 3D", // chart title
				data, // data
				false, // include legend
				false, new Locale("en"));
	}

	private static JFreeChart createPieChart3D_EnabledURL() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createPieChart3D("Pie Chart 3D", // chart title
				data, // data
				true, // include legend
				true, true);
	}

	private static JFreeChart createPieChart3D_EnabledURLMinimalOptions() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createPieChart3D("Pie Chart 3D", // chart title
				data, // data
				false, // include legend
				false, false);
	}

	private static JFreeChart createMultiplePieChart3D_EnabledURLByRow() {
		// create a dataset...
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");
		dataSet.addValue(22, "row2", "col1");

		// create the chart...
		return ChartFactory.createMultiplePieChart3D("Multiple Pie Chart 3D", // chart
																				// title
				dataSet, // data
				TableOrder.BY_ROW, true, // include legend
				true, // tooltips
				true); // url
	}

	private static JFreeChart createMultiplePieChart3D_EnabledURLByCol() {
		// create a dataset...
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");
		dataSet.addValue(22, "row2", "col1");

		// create the chart...
		return ChartFactory.createMultiplePieChart3D("Multiple Pie Chart 3D", // chart
																				// title
				dataSet, // data
				TableOrder.BY_COLUMN, true, // include legend
				true, // tooltips
				true); // url
	}

	private static JFreeChart createMultiplePieChart3D_EnabledURLMinimalOptions() {
		// create a dataset...
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");
		dataSet.addValue(22, "row2", "col1");

		// create the chart...
		return ChartFactory.createMultiplePieChart3D("Multiple Pie Chart 3D", // chart
																				// title
				dataSet, // data
				TableOrder.BY_ROW, false, // include legend
				false, false);
	}

	/**
	 * A chart change listener.
	 */
	static class LocalListener implements ChartChangeListener {

		/** A flag. */
		private boolean flag = false;

		/**
		 * Event handler.
		 * 
		 * @param event
		 *            the event.
		 */
		public void chartChanged(ChartChangeEvent event) {
			this.flag = true;
		}

	}

}
