package com.example.ujianlima.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static void saveFile (String uploadDir, String filename, MultipartFile file)  throws IOException{
		Path  path  = Paths.get(uploadDir);
		
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
		
		try (InputStream inputStream = file.getInputStream()){
			
			
			Files.copy(inputStream,path,StandardCopyOption.REPLACE_EXISTING);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}

}
