/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */



package com.main.springboot.controller;


import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asprise.ocr.Ocr;
@RestController
public class OCRController {
	
	

@PostMapping("/v2/OCR")
public String default_View(@RequestParam("file") MultipartFile file) {
    String result =" ";

    // Save the file temporarily
    
    File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + UUID.randomUUID().toString() + ".jpg");
  
    try {
    	
        file.transferTo(tempFile);
        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_FAST);
        URL imageurl=tempFile.toURI().toURL();
        result= ocr.recognize(new URL[] {imageurl}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        System.out.println("sentence : "+ result);
    } catch (Exception e) {
        // TODO: handle exception
    }

    // Delete the temporary file
    tempFile.delete();

    return result;
}
	
	@GetMapping("/v1/OCR")
	public String default_View() {
		
		String result =" ";
		File file= new File("\\Users\\Ashfaq PC\\Downloads\\imagetext.jpg");
//		imagetext.jpg
		Ocr.setUp();
		try {
			
			Ocr ocr = new Ocr();
			ocr.startEngine("eng", Ocr.SPEED_FAST);
			
			URL imageurl=file.toURI().toURL();
			
			 result= ocr.recognize(new URL[] {imageurl}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
			System.out.println("sentence : "+ result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
			return result;
	}
	
}