package xyz.santima.logic;

import java.io.IOException;

public class CommandExecutor {
	
	public static boolean changeScreenShotFolder(String path){
		
		try {

			if(path.contains(" ")){
				path = path.replace(" ", "\\ ");
				Runtime.getRuntime().exec(new String[]{"defaults", "write",
						"com.apple.screencapture", "location"
						, path});
			}
			else{
				Runtime.getRuntime().exec("defaults write com.apple.screencapture location " + path);
			}

			Runtime.getRuntime().exec("killall SystemUIServer");

			return true;

		} catch (IOException e) {
			return false;
		}

	}

}
