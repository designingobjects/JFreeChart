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
 * -----------------------
 * HashUtilitiesTests.java
 * -----------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Mar-2007 : Version 1 (DG);
 *
 */

package org.jfree.chart.junit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.HashUtilities;
import org.jfree.util.BooleanList;
import org.jfree.util.PaintList;
import org.jfree.util.StrokeList;

/**
 * Tests for the {@link HashUtilities} class.
 */
public class HashUtilitiesTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(HashUtilitiesTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public HashUtilitiesTests(String name) {
		super(name);
	}

	public void testHashCodeForPaint() {
		Paint paint = null;
		int expected = 0;
		assertEquals(expected, HashUtilities.hashCodeForPaint(paint));

		paint = Color.BLACK;
		expected = -16777216;
		assertEquals(expected, HashUtilities.hashCodeForPaint(paint));

		paint = new GradientPaint(new Point2D.Double(1, 1), Color.BLUE,
				new Point2D.Double(2, 2), Color.RED);
		expected = 1074750676;
		assertEquals(expected, HashUtilities.hashCodeForPaint(paint));
	}

	/**
	 * Some sanity checks for the hashCodeForDoubleArray() method.
	 */
	public void testHashCodeForDoubleArray() {
		double[] a1 = new double[] { 1.0 };
		double[] a2 = new double[] { 1.0 };
		int h1 = HashUtilities.hashCodeForDoubleArray(a1);
		int h2 = HashUtilities.hashCodeForDoubleArray(a2);
		assertTrue(h1 == h2);

		double[] a3 = new double[] { 0.5, 1.0 };
		int h3 = HashUtilities.hashCodeForDoubleArray(a3);
		assertFalse(h1 == h3);
	}

	public void testHashCodeForDoubleArray_null() {
		int expected = 0;
		int actual = HashUtilities.hashCodeForDoubleArray(null);
		assertEquals(expected, actual);
	}

	public void testHashCode_Boolean() {
		int pre = 1;

		int expected = 37;
		int actual = HashUtilities.hashCode(pre, true);
		assertEquals(expected, actual);

		expected = 38;
		actual = HashUtilities.hashCode(pre, false);
		assertEquals(expected, actual);
	}

	public void testHashCode_Integer() {
		int pre = 1;
		int integer = 10;

		int expected = 47;
		int actual = HashUtilities.hashCode(pre, integer);
		assertEquals(expected, actual);
	}

	public void testHashCode_Double() {
		int pre = 1;
		double dbl = 10;

		int expected = 1076101157;
		int actual = HashUtilities.hashCode(pre, dbl);
		assertEquals(expected, actual);
	}

	public void testHashCode_Paint() {
		Paint paint = Color.BLACK;
		int pre = 1;
		int expected = -16777179;
		assertEquals(expected, HashUtilities.hashCode(pre, paint));

	}

	public void testHashCode_Stroke() {
		Stroke s = null;
		int pre = 1;

		int expected = 37;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = new BasicStroke((float) 3.14159);
		expected = 929504915;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

	public void testHashCode_String() {
		String s = null;
		int pre = 1;

		int expected = 37;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = "Hello World";
		expected = -862545239;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

	public void testHashCode_Comparable() {
		Comparable<String> s = null;
		int pre = 1;

		int expected = 37;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = "Hello World";
		expected = -862545239;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

	public void testHashCode_Object() {
		Object s = null;
		int pre = 1;

		int expected = 37;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = "Hello World";
		expected = -862545239;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

	public void testHashCode_BooleanList() {
		BooleanList s = null;
		int pre = 1;

		int expected = pre;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = new BooleanList();
		expected = 4736;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setBoolean(0, true);
		expected = 175168;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setBoolean(1, false);
		expected = 6482490;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setBoolean(2, false);
		expected = 239902688;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

	public void testHashCode_PaintList() {
		PaintList s = null;
		int pre = 1;

		int expected = pre;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = new PaintList();
		expected = 4736;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setPaint(0, Color.RED);
		expected = 108401;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setPaint(1, Color.GREEN);
		expected = -12701062;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setPaint(2, Color.YELLOW);
		expected = 131730251;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

	public void testHashCode_StrokeList() {
		StrokeList s = null;
		int pre = 1;

		int expected = pre;
		int actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s = new StrokeList();
		expected = 4736;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setStroke(0, new BasicStroke(1));
		expected = -777869393;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setStroke(1, new BasicStroke(2));
		expected = 1302477998;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);

		s.setStroke(2, new BasicStroke(3));
		expected = -1470921203;
		actual = HashUtilities.hashCode(pre, s);
		assertEquals(expected, actual);
	}

}
