package Game.Model.Resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Control.GameEngine.Log;

/**
 * Class used to handle to the loading process of images from a directory
 */
public class ResourceImages {
	private static final String DIRECTORY_PATH = "res" + File.separator + "images";
	public static final String ACCEPTED_EXTENSION = "jpg";

	public static final String DEFAULT_IMAGE_DIRECTORY_NAME = "default";
	public static final String ANIME_DIRECTORY_PATH = "res" + File.separator + "images"
			+ File.separator + "special";
	public static final int THUMBNAIL_SIZE = 100;

	public static ArrayList<BufferedImage> getDefaultImages() {
		return getAllImagesFromDirectory(DIRECTORY_PATH + File.separator
				+ DEFAULT_IMAGE_DIRECTORY_NAME);
	}

	/**
	 * @param path
	 * @return an arraylist of all images from the specified directory
	 */
	public static ArrayList<BufferedImage> getAllImagesFromDirectory(String path) {
		File resourceFolder = new File(path);
		//if could not find the directory return an empty list
		if (!resourceFolder.isDirectory() || !resourceFolder.exists()) {
			Log.writeln("could not find: " + path);
			return new ArrayList<BufferedImage>();
		}
		//iterate through the image files and try to load them and add them to the arraylist to be returned
		List<File> allImageFiles = Arrays.asList(resourceFolder.listFiles());
		ArrayList<BufferedImage> allImages = new ArrayList<BufferedImage>();
		for (int i = 0; i < allImageFiles.size(); i++) {
			File imageFile = allImageFiles.get(i);
			if (canLoadImage(imageFile)) {
				BufferedImage image = loadImage(imageFile);
				if (image != null) {
					allImages.add(image);
				}
			} else {
				Log.writeln("can't load file: " + imageFile.getPath());
			}
		}
		if (allImages.size() == 0) {
			Log.writeln("Found no images at: " + path);
			return null;
		}
		return allImages;
	}

	/**
	 * @param imagePath
	 * @return the image specified by the given path
	 */
	public static BufferedImage loadImage(String imagePath) {
		File imageFile = new File(imagePath);
		if (canLoadImage(imageFile)) {
			return loadImage(imageFile);
		}
		return null;
	}

	/**
	 * @param imageFile
	 * @return the buffered image if the image loading process has gone accoring
	 *         to plan
	 */
	private static BufferedImage loadImage(File imageFile) {
		try {
			return ImageIO.read(imageFile);
		} catch (IOException e) {
			Log.writeln("Something went wrong with the image loading process");
			Log.writeError(e);
			return null;
		}
	}

	/**
	 * @param imageFile
	 * @return true if the specified image file has the accepted extension (jpg)
	 */
	private static boolean canLoadImage(File imageFile) {
		return imageFile.exists() && imageFile.isFile()
				&& getFileExtension(imageFile.getPath()).equals(ACCEPTED_EXTENSION);
	}

	/**
	 * @param filePath
	 * @return the extension of the specified file path
	 */
	private static String getFileExtension(String filePath) {
		int index = filePath.lastIndexOf(".");
		if (index == -1) {
			return "";
		}
		return filePath.substring(index + 1);
	}

	public static ArrayList<BufferedImage> convertToThumbNails(ArrayList<BufferedImage> images) {
		ArrayList<BufferedImage> thumbnails = new ArrayList<BufferedImage>();
		for (BufferedImage image : images) {
			thumbnails.add(convertToThumbnail(image));
		}
		return thumbnails;
	}

	/**
	 * Used to convert a larger image to a thumb nail so it can be properly
	 * displayed in the settings menu
	 * 
	 * @param image
	 * @return image as thumb nail
	 */
	public static BufferedImage convertToThumbnail(BufferedImage image) {
		BufferedImage thumbNail = new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, image.getType());
		Graphics2D g = thumbNail.createGraphics();
		g.drawImage(image, 0, 0, THUMBNAIL_SIZE, THUMBNAIL_SIZE, 0, 0, image.getWidth(),
				image.getHeight(), null);
		g.dispose();
		return thumbNail;
	}

	public static void releaseImagesResources(ArrayList<BufferedImage> images) {
		for (BufferedImage image : images) {
			releaseImageResource(image);
		}
	}

	public static void releaseImageResource(BufferedImage image) {
		image.flush();
	}
}
