package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import Control.GameEngine.Log;

public class ResourceImages {
	private static final String DIRECTORY_PATH = "res" + File.separator + "images";
	public static final String ACCEPTED_EXTENSION = "png";
	
	public static final String KONAMI_CODE_PATH = "res" + File.separator + "images" + File.separator + "special" + File.separator + "KonamiCode.png";

	public static ArrayList<BufferedImage> getAllAvailableImagePaths() {
		File resourceFolder = new File(DIRECTORY_PATH);
		if (!resourceFolder.isDirectory() || !resourceFolder.exists()) {
			resourceFolder.mkdirs();
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
			throw new FileSystemNotFoundException();
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
