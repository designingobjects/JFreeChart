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
 * LineUtilitiesTests.java
 * -----------------------
 * (C) Copyright 2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Nov-2008 : Version 1 (DG);
 *
 */

package org.jfree.chart.util.junit;

import java.text.FieldPosition;
import java.text.NumberFormat.Field;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.util.HexNumberFormat;
import org.jfree.chart.util.LineUtilities;

/**
 * Tests for the {@link LineUtilities} class.
 */
public class HexNumberFormatTests extends TestCase {

	private HexNumberFormat hexFormat;

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(HexNumberFormatTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public HexNumberFormatTests(String name) {
		super(name);
	}

	protected void setUp() {
		hexFormat = new HexNumberFormat();
	}

	public void testFormat_double() {
		double value = 314159;
		String expected = "0x0004CB2F";
		String actual = hexFormat.format(value);
		assertEquals(expected, actual);
	}

	public void testFormat_doublePrefixAndPosition() {

		String expected = "0x0004CB2F";
		StringBuffer sb = new StringBuffer();
		sb.append("pre");
		FieldPosition ps = new FieldPosition(Field.INTEGER);
		String actual = hexFormat.format(314159, sb, ps).toString();
		assertEquals(expected, actual);
	}

	public void testFormat_lengthSpecifiedShorter() {
		hexFormat.setNumberOfDigits(HexNumberFormat.BYTE);
		String expected = "0x4CB2F";
		String actual = hexFormat.format(314159);
		assertEquals(expected, actual);
	}

	public void testFormat_differentLengths() {
		assertEquals(8, hexFormat.getNumberOfDigits());

		hexFormat.setNumberOfDigits(HexNumberFormat.BYTE);
		String expected = "0x0C";
		String actual = hexFormat.format(12);
		assertEquals(expected, actual);

		hexFormat.setNumberOfDigits(HexNumberFormat.WORD);
		expected = "0x000C";
		actual = hexFormat.format(12);
		assertEquals(expected, actual);

		hexFormat.setNumberOfDigits(HexNumberFormat.DWORD);
		expected = "0x0000000C";
		actual = hexFormat.format(12);
		assertEquals(expected, actual);

		hexFormat.setNumberOfDigits(HexNumberFormat.QWORD);
		expected = "0x000000000000000C";
		actual = hexFormat.format(12);
		assertEquals(expected, actual);
	}
}
