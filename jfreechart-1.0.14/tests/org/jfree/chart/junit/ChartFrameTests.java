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
 * -----------------
 * OutlierTests.java
 * -----------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 21-Nov-2007 : Version 1 (DG);
 *
 */

package org.jfree.chart.junit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.Outlier;
import org.jfree.chart.renderer.OutlierListCollection;


/**
 * Tests for the {@link OutlierListCollection} class.
 */
public class ChartFrameTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(ChartFrameTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public ChartFrameTests(String name) {
        super(name);
    }

    public void testEquals() {
    	Plot plot = new XYPlot();
    	JFreeChart r1 = new JFreeChart(plot);
    	ChartFrame r2 = new ChartFrame("title", r1);
    	ChartFrame r3 = new ChartFrame("title", r1);
    	
    	assertEquals(r2.getTitle(),r3.getTitle());
    }
    
    public void testConstructor() {
    	Plot plot = new XYPlot();
    	JFreeChart r1 = new JFreeChart(plot);
    	ChartPanel r0 = new ChartPanel(r1);
    	ChartFrame r2 = new ChartFrame("title", r1);
    	assertEquals(r2.getChartPanel().getChart(), r0.getChart());
    }
    
    
}

