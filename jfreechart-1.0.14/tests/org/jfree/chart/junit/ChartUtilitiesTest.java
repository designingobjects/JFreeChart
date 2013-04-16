package org.jfree.chart.junit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ChartUtilitiesTest extends TestCase {
	
    public static Test suite() {
        return new TestSuite(ChartUtilitiesTest.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public ChartUtilitiesTest(String name) {
        super(name);
    }
    
    public void testwriteChartAsPNG() throws IOException{
    	
    	
        Number[][] data = new Integer[][]
                {{new Integer(-3), new Integer(-2)},
                 {new Integer(-1), new Integer(1)},
                 {new Integer(2), new Integer(3)}};

            CategoryDataset dataset = DatasetUtilities.createCategoryDataset("S",
                    "C", data);

            // create the chart...
           JFreeChart c1 =  ChartFactory.createBarChart(
                "Bar Chart",
                "Domain", "Range",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,     // include legend
                true,
                true
            );
           
           //ChartUtilities.applyCurrentTheme(c1);
           ByteArrayOutputStream buffer = new ByteArrayOutputStream();
           boolean success = false;
           try{
           ChartUtilities.writeChartAsPNG(buffer, c1, 300, 200);
           buffer.close();
           success = true;
           }
           catch(IOException e){
        	   success = false;   
           }
           assertTrue(success);
    	
    }
    
    
 public void testwriteScaledChartAsPNG() throws IOException{
    	
    	
        Number[][] data = new Integer[][]
                {{new Integer(-3), new Integer(-2)},
                 {new Integer(-1), new Integer(1)},
                 {new Integer(2), new Integer(3)}};

            CategoryDataset dataset = DatasetUtilities.createCategoryDataset("S",
                    "C", data);

            // create the chart...
           JFreeChart c1 =  ChartFactory.createBarChart(
                "Bar Chart",
                "Domain", "Range",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,     // include legend
                true,
                true
            );
           
           //ChartUtilities.applyCurrentTheme(c1);
           ByteArrayOutputStream buffer1 = new ByteArrayOutputStream();
           //ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
           boolean success = false;
           try{
           ChartUtilities.writeScaledChartAsPNG(buffer1, c1, 300, 200, 1, 1);
           buffer1.close();
           
           success = true;
           }
           catch(IOException e){
        	   success = false;   
           }

           assertTrue(success);
    	
    }
 
 public void testSaveChartAsJPEG() throws IOException{
 	
 	
     Number[][] data = new Integer[][]
             {{new Integer(-3), new Integer(-2)},
              {new Integer(-1), new Integer(1)},
              {new Integer(2), new Integer(3)}};

         CategoryDataset dataset = DatasetUtilities.createCategoryDataset("S",
                 "C", data);

         // create the chart...
        JFreeChart c1 =  ChartFactory.createBarChart(
             "Bar Chart",
             "Domain", "Range",
             dataset,
             PlotOrientation.HORIZONTAL,
             true,     // include legend
             true,
             true
         );
        
        ByteArrayOutputStream buffer1 = new ByteArrayOutputStream();
        boolean success = false;
        try{
        	
        File file = new File("test.txt");	
        ChartUtilities.saveChartAsJPEG(file, c1, 300, 200);
        buffer1.close();
        file.delete();
        
        success = true;
        }
        catch(IOException e){
     	   success = false;   
        }

        assertTrue(success);
 	
 }
    
    
    

}
