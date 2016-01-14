package Game.Model.Resources;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Control.GameEngine.Log;

public class ResourceImages {
	private static final String DIRECTORY_PATH = "res" + File.separator + "images";
	public static final String ACCEPTED_EXTENSION = "png";
	
	public static final String DEFAULT_IMAGE_DIRECTORY_NAME = "default";
	public static final String ANIME_DIRECTORY_PATH = "res" + File.separator + "images" + File.separator + "special";
	public static final int THUMBNAIL_SIZE = 100;


	public static ArrayList<BufferedImage> getDefaultImages() {
		return getAllImagesFromDirectory(DIRECTORY_PATH + File.separator + DEFAULT_IMAGE_DIRECTORY_NAME);
	}
	
	public static ArrayList<BufferedImage> getAllImagesFromDirectory(String path)
	{
		File resourceFolder = new File(path);
		if (!resourceFolder.isDirectory() || !resourceFolder.exists()) {
			Log.writeln("could not find: " + path);
			return new ArrayList<BufferedImage>();
		}
		List<File> allImageFiles = Arrays.asList(resourceFolder.listFiles());
		ArrayList<BufferedImage> allImages = new ArrayList<BufferedImage>();
		for (int i = 0; i < allImageFiles.size(); i++) {
			File imageFile = allImageFiles.get(i);
			if (canLoadImage(imageFile)) {
				BufferedImage image = loadImage(imageFile);
				if (image != null) {
					allImages.add(image);
				}
			} else
			{
				Log.writeln("can't load file: " + imageFile.getPath());
			}
		}
		if (allImages.size() == 0) {
			Log.writeln("Found no images at: " + path);
		}
		return allImages;
	}

	public static BufferedImage loadImage(String imagePath) {
		File imageFile = new File(imagePath);
		if (canLoadImage(imageFile)) {
			return loadImage(imageFile);
		}
		return null;
	}
	
	private static BufferedImage loadImage(File imageFile) {
		try {
			return toCompatibleImage(ImageIO.read(imageFile));
		} catch (IOException e) {
			Log.writeln("Something went wrong with the image loading process");
			Log.writeError(e);
			return null;
		}
	}
	
	private static boolean canLoadImage(File imageFile)
	{
		return imageFile.exists() && imageFile.isFile()
				&& getFileExtension(imageFile.getPath()).equals(ACCEPTED_EXTENSION);
	}

	private static String getFileExtension(String filePath) {
		int index = filePath.lastIndexOf(".");
		if (index == -1) {
			return "";
		}
		return filePath.substring(index + 1);
	}

	public static ArrayList<BufferedImage> convertToThumbNails(ArrayList<BufferedImage> images)
	{
		ArrayList<BufferedImage> thumbnails = new ArrayList<BufferedImage>();
		for (BufferedImage image : images) {
			thumbnails.add(convertToThumbnail(image));
		}
		return thumbnails;
	}
	
	public static BufferedImage convertToThumbnail(BufferedImage image)
	{
		BufferedImage thumbNail = new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, image.getType());
		Graphics2D g = thumbNail.createGraphics();
		g.drawImage(image, 0, 0, THUMBNAIL_SIZE, THUMBNAIL_SIZE, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		return thumbNail;
	}
	
	public static void releaseImagesResources(ArrayList<BufferedImage> images)
	{
		for (BufferedImage image : images) {
			releaseImageResource(image);
		}
	}
	
	public static void releaseImageResource(BufferedImage image)
	{
		image.flush();
	}
	
	/**
	 * http://stackoverflow.com/questions/196890/java2d-performance-issues
	 * @param image
	 * @return
	 */
	private static BufferedImage toCompatibleImage(BufferedImage image)
	{
		// obtain the current system graphical settings
		GraphicsConfiguration gfx_config = GraphicsEnvironment.
			getLocalGraphicsEnvironment().getDefaultScreenDevice().
			getDefaultConfiguration();

		/*
		 * if image is already compatible and optimized for current system 
		 * settings, simply return it
		 */
		if (image.getColorModel().equals(gfx_config.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage new_image = gfx_config.createCompatibleImage(
				image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);

		// get the graphics context of the new image to draw the old image on
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();

		// actually draw the image and dispose of context no longer needed
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		// return the new optimized image
		return new_image; 
	}
}
