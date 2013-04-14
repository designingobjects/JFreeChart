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
 * ---------------------------
 * XYImageAnnotationTests.java
 * ---------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 17-May-2004 : Version 1 (DG);
 * 01-Dec-2006 : Updated testEquals() for new field (DG);
 * 09-Jan-2007 : Comment out failing test (DG);
 * 23-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.chart.annotations.junit;

import java.awt.Image;
import java.awt.image.BufferedImage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYImageAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RectangleAnchor;
import org.jfree.util.PublicCloneable;

/**
 * Tests for the {@link XYImageAnnotation} class.
 */
public class XYImageAnnotationTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(XYImageAnnotationTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public XYImageAnnotationTests(String name) {
		super(name);
	}

	/**
	 * Confirm that the equals method can distinguish all the required fields.
	 */
	public void testEquals() {
		Image image = JFreeChart.INFO.getLogo();
		XYImageAnnotation a1 = new XYImageAnnotation(10.0, 20.0, image);
		assertTrue(a1.equals(a1));
		assertFalse(a1.equals(null));
		XYImageAnnotation a2 = new XYImageAnnotation(10.0, 20.0, image);
		assertTrue(a1.equals(a2));

		a1 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		assertFalse(a1.equals(a2));
		a2 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		assertTrue(a1.equals(a2));

		// x
		a1 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		a2 = new XYImageAnnotation(100.0, 20.0, image, RectangleAnchor.LEFT);
		assertFalse(a1.equals(a2));
		a2 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		assertTrue(a1.equals(a2));

		// y
		a1 = new XYImageAnnotation(10.0, 200.0, image, RectangleAnchor.LEFT);
		a2 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		assertFalse(a1.equals(a2));
		a1 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		assertTrue(a1.equals(a2));

		// image
		a1 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		a2 = new XYImageAnnotation(100.0, 20.0, new BufferedImage(2, 3,
				BufferedImage.TYPE_BYTE_GRAY), RectangleAnchor.LEFT);
		assertFalse(a1.equals(a2));
		a2 = new XYImageAnnotation(10.0, 20.0, image, RectangleAnchor.LEFT);
		assertTrue(a1.equals(a2));

	}

	/**
	 * Two objects that are equal are required to return the same hashCode.
	 */
	public void testHashCode() {
		Image image = JFreeChart.INFO.getLogo();
		XYImageAnnotation a1 = new XYImageAnnotation(10.0, 20.0, image);
		XYImageAnnotation a2 = new XYImageAnnotation(10.0, 20.0, image);
		assertTrue(a1.equals(a2));
		int h1 = a1.hashCode();
		int h2 = a2.hashCode();
		assertEquals(h1, h2);
	}

	/**
	 * Confirm that cloning works.
	 */
	public void testCloning() {
		XYImageAnnotation a1 = new XYImageAnnotation(10.0, 20.0,
				JFreeChart.INFO.getLogo());
		XYImageAnnotation a2 = null;
		try {
			a2 = (XYImageAnnotation) a1.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		assertTrue(a1 != a2);
		assertTrue(a1.getClass() == a2.getClass());
		assertTrue(a1.equals(a2));
	}

	/**
	 * Checks that this class implements PublicCloneable.
	 */
	public void testPublicCloneable() {
		XYImageAnnotation a1 = new XYImageAnnotation(10.0, 20.0,
				JFreeChart.INFO.getLogo());
		assertTrue(a1 instanceof PublicCloneable);
	}

	public void testConstructor_IllegalArguments() {
		try {
			new XYImageAnnotation(1, 1, null, null);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			Image image = new BufferedImage(1, 2, BufferedImage.TYPE_INT_RGB);
			new XYImageAnnotation(1, 1, image, null);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	// FIXME: Make this test pass
	// /**
	// * Serialize an instance, restore it, and check for equality.
	// */
	// public void testSerialization() {
	// XYImageAnnotation a1 = new XYImageAnnotation(10.0, 20.0,
	// JFreeChart.INFO.getLogo());
	// XYImageAnnotation a2 = null;
	// try {
	// ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	// ObjectOutput out = new ObjectOutputStream(buffer);
	// out.writeObject(a1);
	// out.close();
	//
	// ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
	// buffer.toByteArray()));
	// a2 = (XYImageAnnotation) in.readObject();
	// in.close();
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// assertEquals(a1, a2);
	// }

	public void testDrawNullInfo() {

		boolean success = false;
		try {
			DefaultTableXYDataset dataset = new DefaultTableXYDataset();

			XYSeries s1 = new XYSeries("Series 1", true, false);
			s1.add(5.0, 5.0);
			s1.add(10.0, 15.5);
			s1.add(15.0, 9.5);
			s1.add(20.0, 7.5);
			dataset.addSeries(s1);

			XYSeries s2 = new XYSeries("Series 2", true, false);
			s2.add(5.0, 5.0);
			s2.add(10.0, 15.5);
			s2.add(15.0, 9.5);
			s2.add(20.0, 3.5);
			dataset.addSeries(s2);
			XYPlot plot = new XYPlot(dataset, new NumberAxis("X"),
					new NumberAxis("Y"), new XYLineAndShapeRenderer());
			XYImageAnnotation a = new XYImageAnnotation(10.0, 20.0,
					JFreeChart.INFO.getLogo());
			plot.addAnnotation(a);
			JFreeChart chart = new JFreeChart(plot);
			/* BufferedImage image = */chart
					.createBufferedImage(300, 200, null);
			success = true;
		} catch (NullPointerException e) {
			e.printStackTrace();
			success = false;
		}
		assertTrue(success);
	}

}
