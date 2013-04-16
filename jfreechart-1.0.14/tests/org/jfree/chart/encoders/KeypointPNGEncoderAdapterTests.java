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
 * ----------------------------
 * CategoryItemEntityTests.java
 * ----------------------------
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

package org.jfree.chart.encoders;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.entity.CategoryItemEntity;

/**
 * Tests for the {@link CategoryItemEntity} class.
 */
public class KeypointPNGEncoderAdapterTests extends TestCase {

	private KeypointPNGEncoderAdapter pea;

	/**
	 * Returns the tests as a test suite.
	 * 
	 * @return The test suite.
	 */
	public static Test suite() {
		return new TestSuite(KeypointPNGEncoderAdapterTests.class);
	}

	/**
	 * Constructs a new set of tests.
	 * 
	 * @param name
	 *            the name of the tests.
	 */
	public KeypointPNGEncoderAdapterTests(String name) {
		super(name);
	}

	protected void setUp() {
		this.pea = new KeypointPNGEncoderAdapter();
	}

	public void testEncode() throws IOException {
		BufferedImage image = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, Color.RED.getRed());
		byte[] pngRawData = pea.encode(image);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(pngRawData);
		BufferedImage actualEncodedPNG = ImageIO.read(inputStream);

		assertEquals(1, actualEncodedPNG.getHeight());
		assertEquals(1, actualEncodedPNG.getWidth());
		assertEquals(Color.RED.getRed(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(0));
		assertEquals(Color.RED.getGreen(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(1));
		assertEquals(Color.RED.getBlue(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(2));
	}

	public void testEncode_DifferentQualityAndEnableAlpha() throws IOException {
		KeypointPNGEncoderAdapter pea = new KeypointPNGEncoderAdapter();
		int quality = 0;// no compression
		boolean isAlpha = true;
		pea.setQuality(quality);
		pea.setEncodingAlpha(isAlpha);

		BufferedImage image = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, Color.RED.getRed());
		byte[] pngRawData = pea.encode(image);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(pngRawData);
		BufferedImage actualEncodedPNG = ImageIO.read(inputStream);

		assertTrue(quality == pea.getQuality());
		assertEquals(isAlpha, pea.isEncodingAlpha());
		assertEquals(1, actualEncodedPNG.getHeight());
		assertEquals(1, actualEncodedPNG.getWidth());
		assertEquals(Color.RED.getAlpha(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(0));
		assertEquals(Color.RED.getRed(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(1));
		assertEquals(Color.RED.getGreen(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(2));
		assertEquals(Color.RED.getBlue(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(3));
	}

	public void testEncode_NullArgument() throws IOException {
		try {
			BufferedImage image = null;
			pea.encode(image);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testEncodeToOutputStream() throws IOException {
		BufferedImage image = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, Color.RED.getRed());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		pea.encode(image, outputStream);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				outputStream.toByteArray());
		BufferedImage actualEncodedPNG = ImageIO.read(inputStream);

		assertEquals(1, actualEncodedPNG.getHeight());
		assertEquals(1, actualEncodedPNG.getWidth());
		assertEquals(Color.RED.getRed(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(0));
		assertEquals(Color.RED.getGreen(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(1));
		assertEquals(Color.RED.getBlue(), actualEncodedPNG.getData()
				.getDataBuffer().getElem(2));
	}

	public void testEncodeToOutputStream_NullOutputStream() throws IOException {
		try {
			BufferedImage image = new BufferedImage(1, 1,
					BufferedImage.TYPE_INT_RGB);
			pea.encode(image, null);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testEncodeToOutputStream_NullImage() throws IOException {
		try {
			pea.encode(null, new ByteArrayOutputStream());
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}
