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
 * -----------------------------
 * XYPolygonAnnotationTests.java
 * -----------------------------
 * (C) Copyright 2006-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 10-Jul-2006 : Version 1 (DG);
 * 23-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.chart.annotations.junit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Stroke;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPolygonAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.util.PublicCloneable;

/**
 * Tests for the {@link XYPolygonAnnotation} class.
 */
public class XYPolygonAnnotationTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(XYPolygonAnnotationTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public XYPolygonAnnotationTests(String name) {
		super(name);
	}

	/**
	 * Confirm that the equals method can distinguish all the required fields.
	 */
	public void testEquals() {
		Stroke stroke1 = new BasicStroke(2.0f);
		Stroke stroke2 = new BasicStroke(2.5f);
		XYPolygonAnnotation a1 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke1, Color.red, Color.blue);
		assertTrue(a1.equals(a1));
		assertFalse(a1.equals(null));
		XYPolygonAnnotation a2 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke1, Color.red, Color.blue);
		assertTrue(a1.equals(a2));
		assertTrue(a2.equals(a1));

		a1 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke1, Color.red, Color.blue);
		assertFalse(a1.equals(a2));
		a2 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke1, Color.red, Color.blue);
		assertTrue(a1.equals(a2));

		a1 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke2, Color.red, Color.blue);
		assertFalse(a1.equals(a2));
		a2 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke2, Color.red, Color.blue);
		assertTrue(a1.equals(a2));

		GradientPaint gp1 = new GradientPaint(1.0f, 2.0f, Color.yellow, 3.0f,
				4.0f, Color.white);
		GradientPaint gp2 = new GradientPaint(1.0f, 2.0f, Color.yellow, 3.0f,
				4.0f, Color.white);
		a1 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke2, gp1, Color.blue);
		assertFalse(a1.equals(a2));
		a2 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke2, gp2, Color.blue);
		assertTrue(a1.equals(a2));

		GradientPaint gp3 = new GradientPaint(1.0f, 2.0f, Color.green, 3.0f,
				4.0f, Color.white);
		GradientPaint gp4 = new GradientPaint(1.0f, 2.0f, Color.green, 3.0f,
				4.0f, Color.white);
		a1 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke2, gp1, gp3);
		assertFalse(a1.equals(a2));
		a2 = new XYPolygonAnnotation(new double[] { 99.0, 2.0, 3.0, 4.0, 5.0,
				6.0 }, stroke2, gp2, gp4);
		assertTrue(a1.equals(a2));
	}

	/**
	 * Two objects that are equal are required to return the same hashCode.
	 */
	public void testHashCode() {
		Stroke stroke = new BasicStroke(2.0f);
		XYPolygonAnnotation a1 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke, Color.red, Color.blue);
		XYPolygonAnnotation a2 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke, Color.red, Color.blue);
		assertTrue(a1.equals(a2));
		int h1 = a1.hashCode();
		int h2 = a2.hashCode();
		assertEquals(h1, h2);
	}

	/**
	 * Confirm that cloning works.
	 */
	public void testCloning() {
		Stroke stroke1 = new BasicStroke(2.0f);
		XYPolygonAnnotation a1 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke1, Color.red, Color.blue);
		XYPolygonAnnotation a2 = null;
		try {
			a2 = (XYPolygonAnnotation) a1.clone();
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
		Stroke stroke1 = new BasicStroke(2.0f);
		XYPolygonAnnotation a1 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke1, Color.red, Color.blue);
		assertTrue(a1 instanceof PublicCloneable);
	}

	/**
	 * Serialize an instance, restore it, and check for equality.
	 */
	public void testSerialization() {

		Stroke stroke1 = new BasicStroke(2.0f);
		XYPolygonAnnotation a1 = new XYPolygonAnnotation(new double[] { 1.0,
				2.0, 3.0, 4.0, 5.0, 6.0 }, stroke1, Color.red, Color.blue);
		XYPolygonAnnotation a2 = null;

		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(buffer);
			out.writeObject(a1);
			out.close();

			ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
					buffer.toByteArray()));
			a2 = (XYPolygonAnnotation) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(a1, a2);

	}

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
			Stroke stroke1 = new BasicStroke(2.0f);
			XYPolygonAnnotation a = new XYPolygonAnnotation(new double[] { 1.0,
					2.0, 3.0, 4.0, 5.0, 6.0 }, stroke1, Color.red, Color.blue);
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

	public void testConstructor_IllegalArguments() {
		try {
			new XYPolygonAnnotation(null);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}
