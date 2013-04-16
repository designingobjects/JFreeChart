	package org.jfree.chart.junit;

	import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
	import java.awt.geom.Rectangle2D;
	import java.io.ByteArrayInputStream;
	import java.io.ByteArrayOutputStream;
	import java.io.ObjectInput;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutput;
	import java.io.ObjectOutputStream;

	import junit.framework.Test;
	import junit.framework.TestCase;
	import junit.framework.TestSuite;

	import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartTransferable;
	import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;

	/**
	 * Tests for the {@link ChartRenderingInfo} class.
	 */
	public class ChartTransferableTests extends TestCase {
		
		final DataFlavor imageFlavor = new DataFlavor(
	            "image/x-java-image; class=java.awt.Image", "Image");

	    /**
	     * Returns the tests as a test suite.
	     *
	     * @return The test suite.
	     */
	    public static Test suite() {
	        return new TestSuite(ChartTransferableTests.class);
	    }

	    /**
	     * Constructs a new set of tests.
	     *
	     * @param name  the name of the tests.
	     */
	    public ChartTransferableTests(String name) {
	        super(name);
	    }
	    
	    public void testIsDataFlavorSupported() {
	    	ChartTransferable chart = new ChartTransferable(null, 0, 0);
	    	assertTrue(chart.isDataFlavorSupported(imageFlavor));
	    }
	}