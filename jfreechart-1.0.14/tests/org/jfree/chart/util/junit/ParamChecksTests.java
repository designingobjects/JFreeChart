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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.util.LineUtilities;
import org.jfree.chart.util.ParamChecks;

/**
 * Tests for the {@link LineUtilities} class.
 */
public class ParamChecksTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(ParamChecksTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public ParamChecksTests(String name) {
		super(name);
	}

	protected void setUp() {
	}

	public void testNullNotPermitted() {
		try {
			ParamChecks.nullNotPermitted("NonNull String Object", "name");
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	public void testNullNotPermitted_NullObject() {
		try {
			ParamChecks.nullNotPermitted(null, "name");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}
