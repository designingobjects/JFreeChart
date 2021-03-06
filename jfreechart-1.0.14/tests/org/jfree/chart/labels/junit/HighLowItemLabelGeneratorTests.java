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
 * -----------------------------------
 * HighLowItemLabelGeneratorTests.java
 * -----------------------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 18-Mar-2003 : Version 1 (DG);
 * 23-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.chart.labels.junit;

import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.labels.HighLowItemLabelGenerator;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 * Tests for the {@link HighLowItemLabelGenerator} class.
 */
public class HighLowItemLabelGeneratorTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(HighLowItemLabelGeneratorTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public HighLowItemLabelGeneratorTests(String name) {
        super(name);
    }

    /**
     * Tests that the equals method can distinguish all fields.
     */
    public void testEquals() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = new HighLowItemLabelGenerator();
        assertTrue(g1.equals(g2));
        assertTrue(g2.equals(g1));

        g1 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                NumberFormat.getInstance());
        assertFalse(g1.equals(g2));
        g2 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                NumberFormat.getInstance());
        assertTrue(g1.equals(g2));

        g1 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0.000"));
        assertFalse(g1.equals(g2));
        g2 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0.000"));
        assertTrue(g1.equals(g2));
    }

    /**
     * Simple check that hashCode is implemented.
     */
    public void testHashCode() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = new HighLowItemLabelGenerator();
        assertTrue(g1.equals(g2));
        assertTrue(g1.hashCode() == g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = null;
        try {
            g2 = (HighLowItemLabelGenerator) g1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(g1 != g2);
        assertTrue(g1.getClass() == g2.getClass());
        assertTrue(g1.equals(g2));
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    public void testPublicCloneable() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(g1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            g2 = (HighLowItemLabelGenerator) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(g1, g2);
        
        
        

    }
    
    
    //also added a test to actually run the equals method
    public void testgenerateToolTip(){
    	
    	HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = new HighLowItemLabelGenerator();	
        int series = 5;
		int item = 1;
		DefaultTableXYDataset dataset = new DefaultTableXYDataset();
		assertEquals(null,g1.generateToolTip(dataset , series , item ));
		
		
        OHLCDataItem item1 = new OHLCDataItem(new Date(1L), 1.0, 2.0, 3.0, 4.0,
                5.0);
        OHLCDataItem item2 = new OHLCDataItem(new Date(2L), 6.0, 7.0, 8.0, 9.0,
                10.0);
        
        // create an array of items in reverse order
        OHLCDataItem[] items = new OHLCDataItem[] {item2, item1};
        DefaultOHLCDataset d1 = new DefaultOHLCDataset("Series 1", items);
        
        Number x = d1.getX(series, item);
        Date date = new Date(x.longValue());
        assertEquals("Series 1--> Date=" + DateFormat.getInstance().format(x) + " High=2 Low=3 Open=1 Close=4", g1.generateToolTip(d1 , series , item));
        
  
    	
    }

}
