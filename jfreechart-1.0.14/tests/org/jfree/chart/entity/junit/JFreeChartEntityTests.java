package org.jfree.chart.entity.junit;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JFreeChartEntityTests extends TestCase {
    public static Test suite() {
        return new TestSuite(JFreeChartEntityTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public JFreeChartEntityTests(String name) {
        super(name);
    }
    
    public void testToString(){
    	Rectangle2D r1 = new Rectangle2D.Double();
    	
    	JFreeChart chart = ChartFactory.createAreaChart("Test",
                "Category", "Value", null, PlotOrientation.VERTICAL,
                true, false, false);
    	CategoryPlot plot = (CategoryPlot) chart.getPlot();
    	CategoryAxis Axis = new CategoryAxis("CategoryAxis");
    	plot.setDomainAxis(Axis);
    	
    	JFreeChartEntity j1 = new JFreeChartEntity(r1, chart);
    	assertEquals(j1.toString(),"JFreeChartEntity: tooltip = null");
    	  	
    	
    }
    
    public void testEquals(){
    	
    	Rectangle2D r1 = new Rectangle2D.Double();
    	Rectangle2D r2 = new Rectangle2D.Double(10.5 , 10.1, 0, 0);
    	
    	JFreeChart chart = ChartFactory.createAreaChart("Test",
                "Category", "Value", null, PlotOrientation.VERTICAL,
                true, false, false);
    	CategoryPlot plot = (CategoryPlot) chart.getPlot();
    	CategoryAxis Axis = new CategoryAxis("CategoryAxis");
    	plot.setDomainAxis(Axis);
    	
    	JFreeChartEntity j1 = new JFreeChartEntity(r1, chart);
    	JFreeChartEntity j2 = new JFreeChartEntity(r1, chart);
    	JFreeChartEntity j3 = new JFreeChartEntity(r2, chart);
    	
    	assertEquals(j1.getChart(),j2.getChart());
    	assertTrue(j1.equals(j2));
    	assertTrue(j1.equals(j1));
    	assertTrue(j1.toString().equals(j2.toString()));
    	assertFalse(j1.equals(plot));
    	assertFalse(j1.equals(j3));
    	
    }
    
    public void testSerialization() {
    	
    	Rectangle2D r1 = new Rectangle2D.Double();
    	
    	JFreeChart chart = ChartFactory.createAreaChart("Test",
                "Category", "Value", null, PlotOrientation.VERTICAL,
                true, false, false);
    	CategoryPlot plot = (CategoryPlot) chart.getPlot();
    	CategoryAxis Axis = new CategoryAxis("CategoryAxis");
    	plot.setDomainAxis(Axis);
    	
    	JFreeChartEntity j1 = new JFreeChartEntity(r1, chart);
    	JFreeChartEntity j2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(j1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            j2 = (JFreeChartEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(j1, j2);
    }
    
    public void testClone() throws CloneNotSupportedException{
    	
    	
    	Rectangle2D r1 = new Rectangle2D.Double();
    	
    	JFreeChart chart = ChartFactory.createAreaChart("Test",
                "Category", "Value", null, PlotOrientation.VERTICAL,
                true, false, false);
    	CategoryPlot plot = (CategoryPlot) chart.getPlot();
    	CategoryAxis Axis = new CategoryAxis("CategoryAxis");
    	plot.setDomainAxis(Axis);
    	
    	JFreeChartEntity j1 = new JFreeChartEntity(r1, chart);
    	JFreeChartEntity j2 = null;
    	
    	j2 = (JFreeChartEntity) j1.clone();
    	
    	assertTrue(j1.equals(j2));
    	
    	
    	
    }
    
	public void testHashCode() throws CloneNotSupportedException {
    	Rectangle2D r1 = new Rectangle2D.Double();
    	
    	JFreeChart chart = ChartFactory.createAreaChart("Test",
                "Category", "Value", null, PlotOrientation.VERTICAL,
                true, false, false);
    	CategoryPlot plot = (CategoryPlot) chart.getPlot();
    	CategoryAxis Axis = new CategoryAxis("CategoryAxis");
    	plot.setDomainAxis(Axis);
    	
    	JFreeChartEntity j1 = new JFreeChartEntity(r1, chart);
    	JFreeChartEntity j2 = (JFreeChartEntity) j1.clone();
    	
        int h1 = j1.hashCode();
        int h2 = j2.hashCode();
        assertEquals(h1, h2);
    }
    
    
    
}
