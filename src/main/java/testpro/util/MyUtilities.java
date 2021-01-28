package testpro.util;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MyUtilities {
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
	public static String getAbsolutePath(String relativePath, File currentFolder) {
		
		if(relativePath.indexOf("../") != -1){
			int numberOfReturn = relativePath.split("\\.\\./").length;
			for(int i=0;i<numberOfReturn-1;i++) {
				currentFolder = currentFolder.getParentFile();
			}
		}
		String currentFolderPath = currentFolder.getAbsolutePath();
		String absolutePath = currentFolderPath + "/" + relativePath.replace("../", "");

		return absolutePath.replace("\\", "/").replace("//", "/");
	}
}
