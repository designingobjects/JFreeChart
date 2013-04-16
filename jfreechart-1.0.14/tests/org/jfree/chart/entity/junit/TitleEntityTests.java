package org.jfree.chart.entity.junit;

import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.TitleEntity;
import org.jfree.chart.title.TextTitle;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TitleEntityTests extends TestCase {
    public static Test suite() {
        return new TestSuite(TitleEntityTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public TitleEntityTests(String name) {
        super(name);
    }
    
    public void testEquals(){
		Rectangle2D r1 = new Rectangle2D.Double();
		Rectangle2D r2 = new Rectangle2D.Double(10.0, 1, 0, 0);
		TextTitle t1 = new TextTitle();
		TextTitle t2 = new TextTitle("test");
		
		
		TitleEntity a1 = new TitleEntity(r1, t1, "test");
		TitleEntity a2 = new TitleEntity(r1, t1, "test");
		TitleEntity a3 = new TitleEntity(r1, t1, "test2");
		TitleEntity a4 = new TitleEntity(r1, t2, "test");
		
		
		
		assertTrue(a1.equals(a2));
		assertTrue(a1.equals(a1));
		assertFalse(a1.equals(r1));
		assertFalse(a1.getTitle().equals(a4.getTitle()));
		assertFalse(a1.equals(a4));
		

		assertFalse(a1.equals(a3));
		assertTrue(a1.getTitle().equals(a3.getTitle()));
		
		a2.setURLText("test2");
		assertFalse(a1.equals(a2));
		a1.setURLText("test2");
		assertTrue(a1.equals(a2));
		
		a2.setArea(r2);
		assertFalse(a1.equals(a2));
		a1.setArea(r2);
		assertTrue(a1.equals(a2));
		
		
		

		
		
		
	}
	
	public void testToString(){
		Rectangle2D r1 = new Rectangle2D.Double();
		TextTitle t1 = new TextTitle();

		TitleEntity a1 = new TitleEntity(r1, t1, "test");
		
		assertEquals("TitleEntity: tooltip = test", a1.toString());
		
		
	}
	
	public void testClone() throws CloneNotSupportedException{
    	    	
		Rectangle2D r1 = new Rectangle2D.Double();
		TextTitle t1 = new TextTitle();
		
		TitleEntity a1 = new TitleEntity(r1, t1, "test");
		TitleEntity a2 = (TitleEntity) a1.clone();
		assertTrue(a1.equals(a2));
	
}
	public void testHashCode() throws CloneNotSupportedException {
		Rectangle2D r1 = new Rectangle2D.Double();
		TextTitle t1 = new TextTitle();
		
		TitleEntity a1 = new TitleEntity(r1, t1, "test");
		TitleEntity a2 = (TitleEntity) a1.clone();
		assertTrue(a1.equals(a2));
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }
	
	
    public void testSerialization() throws CloneNotSupportedException {
    	
		Rectangle2D r1 = new Rectangle2D.Double();
		TextTitle t1 = new TextTitle();
		
		TitleEntity a1 = new TitleEntity(r1, t1, "test");
		TitleEntity a2 = (TitleEntity) a1.clone();
		assertTrue(a1.equals(a2));

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(a1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            a2 = (TitleEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a1, a2);
    }
    
    
    
    
}
