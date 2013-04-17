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
 * PieChartTests.java
 * ------------------
 * (C) Copyright 2002-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 11-Jun-2002 : Version 1 (DG);
 * 17-Oct-2002 : Fixed errors reported by Checkstyle (DG);
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
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.TableOrder;

/**
 * Tests for a pie chart.
 * 
 */
public class PieChartTests extends TestCase {

	/** A chart. */
	private JFreeChart pieChart;

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(PieChartTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public PieChartTests(String name) {
		super(name);
	}

	/**
	 * Common test setup.
	 */
	protected void setUp() {

		this.pieChart = createPieChart();

	}

	public void testCreatePieChart_WithoutURL() {
		JFreeChart pie = createPieChart_WithoutURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertNotNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
	}

	public void testCreatePieChart_WithoutURLMinimalOptions() {
		JFreeChart pie = createPieChart_WithoutURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart", pie.getTitle().getText());
		assertNull(pie.getLegend());
		assertNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
	}

	public void testCreatePieChart_MinimalOptions() {
		JFreeChart pie = createPieChart_MinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart", pie.getTitle().getText());
		assertNull(pie.getLegend());
		assertNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
		assertNull(((PiePlot) pie.getPlot()).getURLGenerator());
	}

	public void testCreatePieChart_EnabledURL() {
		JFreeChart pie = createPieChart_EnabledURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart", pie.getTitle().getText());
		assertNotNull(((PiePlot) pie.getPlot()).getURLGenerator());
	}

	public void testCreatePieChart_TwoDataSets() {
		JFreeChart pie = createPieChart_TwoDataSets();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart Two Data Sets", pie.getTitle().getText());
		assertEquals("Bright red=change >=-10%, Bright green=change >=+10%",
				((TextTitle) pie.getSubtitles().get(1)).getText());
		assertNull(pie.getBackgroundImage());
		assertNotNull(pie.getLegend());
		assertNotNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
	}

	public void testCreatePieChart_TwoDataSetsMinimalOptions() {
		JFreeChart pie = createPieChart_TwoDataSetsMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart Minimal Options", pie.getTitle().getText());
		assertNull(pie.getBackgroundImage());
		assertNull(pie.getLegend());
		assertNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
	}

	public void testCreatePieChart_TwoDataSetsSubtitleWithoutGreenIncrease() {
		JFreeChart pie = createPieChart_TwoDataSetsSubtitleWithoutGreenIncrease();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart Subtitle Without Green Increase", pie
				.getTitle().getText());
		assertEquals("Bright green=change >=-101%, Bright red=change >=+101%",
				((TextTitle) pie.getSubtitles().get(0)).getText());
	}

	public void testCreatePieChart_TwoDataSetsWithURL() {
		JFreeChart pie = createPieChart_TwoDataSetsWithURL();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart Two Data Sets", pie.getTitle().getText());
		assertEquals("Bright red=change >=-10%, Bright green=change >=+10%",
				((TextTitle) pie.getSubtitles().get(1)).getText());
		assertNull(pie.getBackgroundImage());
		assertNotNull(pie.getLegend());
		assertNotNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
		assertNotNull(((PiePlot) pie.getPlot()).getURLGenerator());
	}

	public void testCreatePieChart_TwoDataSetsWithURLMinimalOptions() {
		JFreeChart pie = createPieChart_TwoDataSetsWithURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart Minimal Options", pie.getTitle().getText());
		assertNull(pie.getBackgroundImage());
		assertNull(pie.getLegend());
		assertNull(((PiePlot) pie.getPlot()).getToolTipGenerator());
		assertNull(((PiePlot) pie.getPlot()).getURLGenerator());
	}

	public void testCreatePieChart_TwoDataSetsWithURLSubtitleWithoutGreenIncrease() {
		JFreeChart pie = createPieChart_TwoDataSetsWithURLSubtitleWithoutGreenIncrease();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Pie Chart Subtitle Without Green Increase", pie
				.getTitle().getText());
		assertEquals("Bright green=change >=-101%, Bright red=change >=+101%",
				((TextTitle) pie.getSubtitles().get(0)).getText());
	}

	public void testCreateMultiplePieChart_EnabledURLByRow() {
		JFreeChart pie = createMultiplePieChart_EnabledURLByRow();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Multiple Pie Chart", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof MultiplePiePlot);
		MultiplePiePlot mpp = (MultiplePiePlot) pie.getPlot();
		assertEquals(TableOrder.BY_ROW, mpp.getDataExtractOrder());
		PiePlot pp = ((PiePlot) mpp.getPieChart().getPlot());
		assertNotNull(pp.getToolTipGenerator());
		assertNotNull(pp.getURLGenerator());
	}

	public void testCreateMultiplePieChart_EnabledURLByCol() {
		JFreeChart pie = createMultiplePieChart_EnabledURLByCol();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Multiple Pie Chart", pie.getTitle().getText());
		assertNotNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof MultiplePiePlot);
		MultiplePiePlot mpp = (MultiplePiePlot) pie.getPlot();
		assertEquals(TableOrder.BY_COLUMN, mpp.getDataExtractOrder());
		PiePlot pp = ((PiePlot) mpp.getPieChart().getPlot());
		assertNotNull(pp.getToolTipGenerator());
		assertNotNull(pp.getURLGenerator());
	}

	public void testCreateMultiplePieChart_EnabledURLMinimalOptions() {
		JFreeChart pie = createMultiplePieChart_EnabledURLMinimalOptions();
		ChartTheme theme = StandardChartTheme.createDarknessTheme();
		theme.apply(pie);

		assertEquals("Multiple Pie Chart", pie.getTitle().getText());
		assertNull(pie.getLegend());
		assertTrue(pie.getPlot() instanceof MultiplePiePlot);
		PiePlot pp = ((PiePlot) ((MultiplePiePlot) pie.getPlot()).getPieChart()
				.getPlot());
		assertNull(pp.getToolTipGenerator());
		assertNull(pp.getURLGenerator());
	}

	public void testCreateMultiplePieChart_NullTableOrder() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");

		try {
			ChartFactory.createMultiplePieChart("Multiple Pie Chart", dataSet,
					null, false, false, false);
			fail();
		} catch (IllegalArgumentException e) {
		}
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
	 * Creates a pie chart.
	 * 
	 * @return The pie chart.
	 */

	private static JFreeChart createPieChart_WithoutURL() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart", // chart title
				data, // data
				true, // include legend
				true, new Locale("en"));
	}

	private static JFreeChart createPieChart_WithoutURLMinimalOptions() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart", // chart title
				data, // data
				false, // include legend
				false, new Locale("en"));
	}

	private static JFreeChart createPieChart() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));
		data.setValue("Visual Basic", new Double(0.0));
		data.setValue("C/C++", new Double(17.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart", // chart title
				data, // data
				true, // include legend
				true, false);
	}

	private static JFreeChart createPieChart_EnabledURL() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));
		data.setValue("Visual Basic", new Double(0.0));
		data.setValue("C/C++", new Double(17.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart", // chart title
				data, // data
				false, // include legend
				false, true);
	}

	private static JFreeChart createPieChart_MinimalOptions() {
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Java", new Double(43.2));
		data.setValue("Visual Basic", new Double(0.0));
		data.setValue("C/C++", new Double(17.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart", // chart title
				data, // data
				false, // include legend
				false, false);
	}

	private static JFreeChart createPieChart_TwoDataSets() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("a", new Double(43.2));
		dataset.setValue("b", new Double(0.0));
		dataset.setValue("c", new Double(17.5));

		DefaultPieDataset previousData = new DefaultPieDataset();
		previousData.setValue("a", new Double(53.2));
		previousData.setValue("b", null);
		previousData.setValue("c", new Double(7.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart Two Data Sets", // chart
																		// title
				(PieDataset) dataset, // data
				(PieDataset) previousData, // previous data
				10, // percent diff for max scale
				true, // for increase
				true, // include legend
				true, // tooltips
				new Locale("en"), // locale
				true, // subtitle
				true // show difference
				);
	}

	private static JFreeChart createPieChart_TwoDataSetsMinimalOptions() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("a", new Double(443.2));
		dataset.setValue("b", new Double(0.0));
		dataset.setValue("c", new Double(7.5));

		DefaultPieDataset previousData = new DefaultPieDataset();
		previousData.setValue("a", new Double(53.2));
		previousData.setValue("b", null);
		previousData.setValue("c", new Double(227.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart Minimal Options", // chart
																		// title
				(PieDataset) dataset, // data
				(PieDataset) previousData, // previous data
				101, // percent diff for max scale
				false, // for increase
				false, // include legend
				false, // tooltips
				new Locale("en"), // locale
				false, // subtitle
				false // show difference
				);
	}

	private static JFreeChart createPieChart_TwoDataSetsSubtitleWithoutGreenIncrease() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("a", new Double(443.2));

		DefaultPieDataset previousData = new DefaultPieDataset();
		previousData.setValue("a", new Double(53.2));

		// create the chart...
		return ChartFactory.createPieChart(
				"Pie Chart Subtitle Without Green Increase", // chart title
				(PieDataset) dataset, // data
				(PieDataset) previousData, // previous data
				101, // percent diff for max scale
				false, // for increase
				false, // include legend
				false, // tooltips
				new Locale("en"), // locale
				true, // subtitle
				false // show difference
				);
	}

	private static JFreeChart createPieChart_TwoDataSetsWithURL() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("a", new Double(43.2));
		dataset.setValue("b", new Double(0.0));
		dataset.setValue("c", new Double(17.5));

		DefaultPieDataset previousData = new DefaultPieDataset();
		previousData.setValue("a", new Double(53.2));
		previousData.setValue("b", null);
		previousData.setValue("c", new Double(7.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart Two Data Sets", // chart
																		// title
				(PieDataset) dataset, // data
				(PieDataset) previousData, // previous data
				10, // percent diff for max scale
				true, // for increase
				true, // include legend
				true, // tooltips
				true, // URL
				true, // subtitle
				true // show difference
				);
	}

	private static JFreeChart createPieChart_TwoDataSetsWithURLMinimalOptions() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("a", new Double(443.2));
		dataset.setValue("b", new Double(0.0));
		dataset.setValue("c", new Double(7.5));

		DefaultPieDataset previousData = new DefaultPieDataset();
		previousData.setValue("a", new Double(53.2));
		previousData.setValue("b", null);
		previousData.setValue("c", new Double(227.5));

		// create the chart...
		return ChartFactory.createPieChart("Pie Chart Minimal Options", // chart
																		// title
				(PieDataset) dataset, // data
				(PieDataset) previousData, // previous data
				101, // percent diff for max scale
				false, // for increase
				false, // include legend
				false, // tooltips
				false, // URL
				false, // subtitle
				false // show difference
				);
	}

	private static JFreeChart createPieChart_TwoDataSetsWithURLSubtitleWithoutGreenIncrease() {
		// create a dataset...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("a", new Double(443.2));

		DefaultPieDataset previousData = new DefaultPieDataset();
		previousData.setValue("a", new Double(53.2));

		// create the chart...
		return ChartFactory.createPieChart(
				"Pie Chart Subtitle Without Green Increase", // chart title
				(PieDataset) dataset, // data
				(PieDataset) previousData, // previous data
				101, // percent diff for max scale
				false, // for increase
				false, // include legend
				false, // tooltips
				true, // URL
				true, // subtitle
				false // show difference
				);
	}

	private static JFreeChart createMultiplePieChart_EnabledURLByRow() {
		// create a dataset...
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");
		dataSet.addValue(22, "row2", "col1");

		// create the chart...
		return ChartFactory.createMultiplePieChart("Multiple Pie Chart", // chart
																			// title
				dataSet, // data
				TableOrder.BY_ROW, true, // include legend
				true, // tooltips
				true); // url
	}

	private static JFreeChart createMultiplePieChart_EnabledURLByCol() {
		// create a dataset...
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");
		dataSet.addValue(22, "row2", "col1");

		// create the chart...
		return ChartFactory.createMultiplePieChart("Multiple Pie Chart", // chart
																			// title
				dataSet, // data
				TableOrder.BY_COLUMN, true, // include legend
				true, // tooltips
				true); // url
	}

	private static JFreeChart createMultiplePieChart_EnabledURLMinimalOptions() {
		// create a dataset...
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(11, "row1", "col2");
		dataSet.addValue(22, "row2", "col1");

		// create the chart...
		return ChartFactory.createMultiplePieChart("Multiple Pie Chart", // chart
																			// title
				dataSet, // data
				TableOrder.BY_ROW, false, // include legend
				false, false);
	}

	/**
	 * A chart change listener.
	 * 
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
