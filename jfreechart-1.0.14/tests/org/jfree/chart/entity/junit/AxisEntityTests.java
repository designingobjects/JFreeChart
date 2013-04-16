package org.jfree.chart.entity.junit;

import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.labels.HighLowItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AxisEntityTests extends TestCase {
	
    public static Test suite() {
        return new TestSuite(AxisEntityTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public AxisEntityTests(String name) {
        super(name);
    }
	
	public void testEquals() throws CloneNotSupportedException{
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		AxisEntity a1 = new AxisEntity(r1,Axis);
		AxisEntity a2 = (AxisEntity) a1.clone();
		assertTrue(a1.getAxis().equals(a2.getAxis()));
		assertTrue(a1.equals(a2));
		
	}
	
	public void testToString(){
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		AxisEntity a1 = new AxisEntity(r1,Axis);
		AxisEntity a2 = new AxisEntity(r1,Axis);
		
		assertEquals("AxisEntity: tooltip = null", a1.toString());
		
		
	}
	
	public void testClone() throws CloneNotSupportedException{
    	
    	
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		AxisEntity a1 = new AxisEntity(r1,Axis);
		AxisEntity a2 = (AxisEntity) a1.clone();
		assertTrue(a1.equals(a2));
	
}
	public void testHashCode() throws CloneNotSupportedException {
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		AxisEntity a1 = new AxisEntity(r1,Axis);
		AxisEntity a2 = (AxisEntity) a1.clone();
        assertTrue(a1.equals(a2));
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }
	
	
    public void testSerialization() throws CloneNotSupportedException {
    	
		Rectangle2D r1 = new Rectangle2D.Double();
		CategoryAxis Axis = new CategoryAxis("CategoryAxis");
		AxisEntity a1 = new AxisEntity(r1,Axis);
		AxisEntity a2 = (AxisEntity) a1.clone();

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(a1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            a2 = (AxisEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(a1, a2);
    }
	

}
