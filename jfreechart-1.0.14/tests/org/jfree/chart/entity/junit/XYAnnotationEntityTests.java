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
 * ----------------------
 * XYItemEntityTests.java
 * ----------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-May-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.entity.junit;

import java.awt.Rectangle;
import java.awt.Shape;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYAnnotationEntity;
import org.jfree.chart.entity.XYItemEntity;

/**
 * Tests for the {@link XYItemEntity} class.
 */
public class XYAnnotationEntityTests extends TestCase {

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(XYAnnotationEntityTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public XYAnnotationEntityTests(String name) {
		super(name);
	}

	/**
	 * Confirm that the equals method can distinguish all the required fields.
	 */
	public void testEquals() {
		Shape rectangle = new Rectangle(2, 2);
		XYAnnotationEntity e1 = new XYAnnotationEntity(rectangle, 0, "ToolTip",
				"URL");
		assertTrue(e1.equals(e1));
		assertFalse(e1.equals(null));

		ChartEntity c1 = new ChartEntity(rectangle, "ToolTip", "URL");
		assertFalse(e1.equals(c1));

		rectangle = new Rectangle(3, 3);
		e1 = new XYAnnotationEntity(rectangle, 0, "ToolTip", "URL");
		XYAnnotationEntity e2 = new XYAnnotationEntity(rectangle, 0, "ToolTip",
				"URL");
		e2.setRendererIndex(2);
		assertTrue(e1.getRendererIndex() != e2.getRendererIndex());
		assertFalse(e1.equals(e2));

		e2 = new XYAnnotationEntity(rectangle, 0, "ToolTip", "URL");
		assertTrue(e1.equals(e2));

	}
}
