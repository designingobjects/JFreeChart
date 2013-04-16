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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PlotEntityTests extends TestCase {
		
	    public static Test suite() {
	        return new TestSuite(PlotEntityTests.class);
	    }

	    /**
	     * Constructs a new set of tests.
	     *
	     * @param name  the name of the tests.
	     */
	    public PlotEntityTests(String name) {
	        super(name);
	    }
	    
		public void testEquals() {
			Rectangle2D r1 = new Rectangle2D.Double();
			DefaultCategoryDataset datasetA = new DefaultCategoryDataset();
	        DefaultCategoryDataset datasetB = new DefaultCategoryDataset();
	        datasetB.addValue(50.0, "R1", "C1");
	        datasetB.addValue(80.0, "R1", "C1");
	        CategoryPlot plot = new CategoryPlot(datasetA, new CategoryAxis(null),
	                new NumberAxis(null), new LineAndShapeRenderer());
	        
	        CategoryPlot plot2 = new CategoryPlot(datasetB, new CategoryAxis("555"),
	                new NumberAxis(null), new LineAndShapeRenderer());

			PlotEntity a1 = new PlotEntity(r1,plot);
			PlotEntity a2 = new PlotEntity(r1,plot);
			PlotEntity a3 = new PlotEntity(r1,plot2);
			
			assertFalse(a1.equals(a3));
			assertTrue(a1.equals(a2));
			assertTrue(a1.equals(a1));
			assertFalse(a1.equals(r1));
			
			a1.setArea(new Rectangle2D.Double(50.0,100,0,0));
			assertFalse(a1.equals(a2));
			a2.setArea(new Rectangle2D.Double(50.0,100,0,0));
			assertTrue(a1.equals(a1));
			
			a1.setToolTipText("test");
			assertFalse(a1.equals(a2));
			a2.setToolTipText("test");
			assertTrue(a1.equals(a1));
			
			a1.setURLText("test");
			assertFalse(a1.equals(a2));
			a2.setURLText("test");
			assertTrue(a1.equals(a1));
			
			
		}
		
		public void testToString(){
			Rectangle2D r1 = new Rectangle2D.Double();
			DefaultCategoryDataset datasetA = new DefaultCategoryDataset();
	        CategoryPlot plot = new CategoryPlot(datasetA, new CategoryAxis(null),
	                new NumberAxis(null), new LineAndShapeRenderer());

			PlotEntity a1 = new PlotEntity(r1,plot);
			
			assertEquals("PlotEntity: tooltip = null", a1.toString());
			
			
		}
		
		public void testClone() throws CloneNotSupportedException{
	    	
			Rectangle2D r1 = new Rectangle2D.Double();
			DefaultCategoryDataset datasetA = new DefaultCategoryDataset();
	        CategoryPlot plot = new CategoryPlot(datasetA, new CategoryAxis(null),
	                new NumberAxis(null), new LineAndShapeRenderer());

			PlotEntity a1 = new PlotEntity(r1,plot);
			PlotEntity a2 = (PlotEntity) a1.clone();
			
	}
		public void testHashCode() throws CloneNotSupportedException {
			Rectangle2D r1 = new Rectangle2D.Double();
			DefaultCategoryDataset datasetA = new DefaultCategoryDataset();
	        CategoryPlot plot = new CategoryPlot(datasetA, new CategoryAxis(null),
	                new NumberAxis(null), new LineAndShapeRenderer());

			PlotEntity a1 = new PlotEntity(r1,plot);
			PlotEntity a2 = (PlotEntity) a1.clone();
			
	        assertTrue(a1.equals(a2));
	        int h1 = a1.hashCode();
	        int h2 = a2.hashCode();
	        assertEquals(h1, h2);
	    }
		
		
	    public void testSerialization() throws CloneNotSupportedException {
	    	
			Rectangle2D r1 = new Rectangle2D.Double();
			DefaultCategoryDataset datasetA = new DefaultCategoryDataset();
	        CategoryPlot plot = new CategoryPlot(datasetA, new CategoryAxis(null),
	                new NumberAxis(null), new LineAndShapeRenderer());

			PlotEntity a1 = new PlotEntity(r1,plot);
			PlotEntity a2 = (PlotEntity) a1.clone();

	        try {
	            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	            ObjectOutput out = new ObjectOutputStream(buffer);
	            out.writeObject(a1);
	            out.close();

	            ObjectInput in = new ObjectInputStream(
	                    new ByteArrayInputStream(buffer.toByteArray()));
	            a2 = (PlotEntity) in.readObject();
	            in.close();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        assertEquals(a1, a2);
	    }
}
