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
 * ------------------------------
 * XYDrawableAnnotationTests.java
 * ------------------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Aug-2003 : Version 1 (DG);
 * 01-Oct-2004 : Fixed bugs in tests (DG);
 * 07-Jan-2005 : Added hashCode() test (DG);
 * 23-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.chart.annotations.junit;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.annotations.XYDataImageAnnotation;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.ui.Drawable;
import org.jfree.util.PublicCloneable;

/**
 * Tests for the {@link XYDataImageAnnotation} class.
 */
public class XYDataImageAnnotationTests extends TestCase {

    static class TestDrawable implements Drawable, Cloneable, Serializable {
        /**
         * Default constructor.
         */
        public TestDrawable() {
        }
        /**
         * Draws something.
         * @param g2  the graphics device.
         * @param area  the area in which to draw.
         */
        public void draw(Graphics2D g2, Rectangle2D area) {
            // do nothing
        }
        /**
         * Tests this object for equality with an arbitrary object.
         * @param obj  the object to test against (<code>null</code> permitted).
         * @return A boolean.
         */
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TestDrawable)) {
                return false;
            }
            return true;
        }
        /**
         * Returns a clone.
         *
         * @return A clone.
         *
         * @throws CloneNotSupportedException if there is a problem cloning.
         */
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(XYDrawableAnnotationTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
   
    //Matt's Tests
    public void testConstructors() {
    	BufferedImage image = new BufferedImage(10, 10, 10);
    	XYDataImageAnnotation a = new XYDataImageAnnotation(image, 1, 1, 1, 1);
    	XYDataImageAnnotation b = new XYDataImageAnnotation(image, 1, 1, 1, 1, false);
    	assertEquals(a, b);
    	
    }
    
    public void testEquals() {
    	BufferedImage image = new BufferedImage(10, 10, 10);
    	XYDataImageAnnotation a = new XYDataImageAnnotation(image, 0, 0, 0, 0);
    	assertTrue(a.equals(a));
    	
    	assertFalse(a.equals(null));
    }

}
