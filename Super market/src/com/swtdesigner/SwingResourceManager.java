package com.swtdesigner;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ImageIcon;

/**
 * Utility class for managing resources such as colors, fonts, images, etc.
 * 
 * This class may be freely distributed as part of any application or plugin.
 * <p>
 * Copyright (c) 2003 - 2004, Instantiations, Inc. <br>All Rights Reserved
 * 
 * @author scheglov_ke
 */
public class SwingResourceManager {
	
	/**
	 * Maps image names to images
	 */
	private static HashMap<String, Image> m_ClassImageMap = new HashMap<>();
	
    /**
     * Returns an image encoded by the specified input stream
     * @param is InputStream The input stream encoding the image data
     * @return Image The image encoded by the specified input stream
     */
	private static Image getImage(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buf[] = new byte[1024 * 4];
			while (true) {
				int n = is.read(buf);
				if (n == -1)
					break;
				baos.write(buf, 0, n);
			}
			baos.close();
			return Toolkit.getDefaultToolkit().createImage(baos.toByteArray());
		} catch (Throwable e) {
			return null;
		}
	}
	
    /**
     * Returns an image stored in the file at the specified path relative to the specified class
     * @param clazz Class The class relative to which to find the image
     * @param path String The path to the image file
     * @return Image The image stored in the file at the specified path
     */
	public static Image getImage(Class clazz, String path) {
		String key = clazz.getName() + '|' + path;
		Image image = m_ClassImageMap.get(key);
		if (image == null) {
			if ((path.length() > 0) && (path.charAt(0) == '/')) {
				String newPath = path.substring(1);
				image = getImage(new BufferedInputStream(clazz.getClassLoader().getResourceAsStream(newPath)));
			} else {
				image = getImage(clazz.getResourceAsStream(path));
			}
			m_ClassImageMap.put(key, image);
		}
		return image;
	}

	/**
     * Returns an icon stored in the file at the specified path relative to the specified class
     * @param clazz Class The class relative to which to find the icon
     * @param path String The path to the icon file
     * @return Icon The icon stored in the file at the specified path
     */
	public static ImageIcon getIcon(Class clazz, String path) {
		return getIcon(getImage(clazz, path));
	}

	/**
     * Returns an icon based on the specified image
     * @param image Image The original image
     * @return Icon The icon based on the image
     */
	private static ImageIcon getIcon(Image image) {
		if (image == null)
			return null;
		return new ImageIcon(image);
	}
}