package org.jfree.chart.entity.junit;

import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.entity.ChartEntity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ChartEntityTests extends TestCase {
	
	
    public static Test suite() {
        return new TestSuite(ChartEntityTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public ChartEntityTests(String name) {
        super(name);
    }
	
	public void testEquals() throws CloneNotSupportedException{
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		ChartEntity a1 = new ChartEntity(r1);
		ChartEntity a2 = (ChartEntity) a1.clone();
		assertTrue(a1.equals(a2));
    	assertTrue(a1.equals(a2));
    	assertTrue(a1.equals(a1));
    	assertTrue(a1.toString().equals(a2.toString()));
    	assertFalse(a1.equals(r1));
		
		
	}
	
	public void testToString(){
		Rectangle2D r1 = new Rectangle2D.Double();
		ChartEntity a1 = new ChartEntity(r1);
		
		assertEquals("ChartEntity: tooltip = null", a1.toString());
		
		
	}
	
	public void testClone() throws CloneNotSupportedException{
    	
    	
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		ChartEntity a1 = new ChartEntity(r1);
		ChartEntity a2 = (ChartEntity) a1.clone();
		assertTrue(a1.equals(a2));
	
}
	public void testHashCode() throws CloneNotSupportedException {
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		ChartEntity a1 = new ChartEntity(r1);
		ChartEntity a2 = (ChartEntity) a1.clone();
        assertTrue(a1.equals(a2));
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }
	
	
    public void testSerialization() throws CloneNotSupportedException {
    	
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		ChartEntity a1 = new ChartEntity(r1);
		ChartEntity a2 = (ChartEntity) a1.clone();

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(a1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            a2 = (ChartEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a1, a2);
    }

}
