package Game.Model.Resources;

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
			return ImageIO.read(imageFile);
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
}
