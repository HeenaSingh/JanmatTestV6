package com.janmat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.janmat.dao.UserDao;
import com.janmat.model.User;


public class ImageUploader extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException, FileNotFoundException{
    		    
    		String imageChanged = null; 
    		User imgUser = (User) request.getSession().getAttribute("loginuser");
    		UserDao imgUserDao = new UserDao();
    		System.out.println("Image Uploader  User " +imgUser);
    			
        try{
        	
        	InputStream inputStream = null;
        	
        	System.out.println("INSIDE IMGUPLOADER USERID HERE"+imgUser.getUserid());
                	
            // obtains the upload file part in this multipart request  
    	    Part filePart = request.getPart("profileimage");  
            //Your photos couldn't be uploaded. Photos should be less than 4 MB and saved as JPG, PNG, GIF or TIFF files.
    	    System.out.println("filepart captured");
    		if (filePart != null ) {  
    		    // debug messages  
    		    System.out.println("filepart is not null");
    		    System.out.println(filePart.getName());  
    		    System.out.println(filePart.getSize());  
    		    String fileType = filePart.getContentType();
    		  
    		    System.out.println(fileType);  
    		    String userfileName = filePart.getHeader("content-disposition");
    		    System.out.println(userfileName);  
    		    
    		    String dirPath  = request.getServletContext().getRealPath("Images" + "/" + imgUser.getUserid()) ;
    		       		     			
    		    Files.createDirectories(Paths.get(dirPath));
    		    System.out.println(dirPath);
    		    			    
    		    // obtains input stream of the upload file  
    			
    			    
    			    inputStream = filePart.getInputStream();  
    			    String mimeType = URLConnection.guessContentTypeFromStream(inputStream);//gets the mime type
    			    Path thisUserFilePath = Paths.get(dirPath + "/profileimage");
    			    System.out.println(mimeType);//prints the mime type
    			    String[] type =  mimeType.split("/");
    			    System.out.println(type[0]); 
    			    System.out.println(type[1]); 
    			    if(type[0].equalsIgnoreCase("image")){
    			    	imageChanged = "success" ;
    			    	Files.copy(inputStream, thisUserFilePath, StandardCopyOption.REPLACE_EXISTING);
    			    	imgUserDao.changeProfileiImage("/" + "Images" + "/" +  imgUser.getUserid() + "/" +"profileimage",imgUser.getUserid());
    			    	System.out.println(imageChanged);
    			    }
    			    else{
    			    	imageChanged = "fail";
    			    	System.out.println(imageChanged);
    			    //"Your photos couldn't be uploaded. Photos should be saved as JPG, PNG, GIF or TIFF files."
    				 
    			    }
    			 }
    			 
    		else{
    		    System.out.println("File not found");	
    		}

    	}catch (FileNotFoundException e) {
    	    System.out.println(e.getMessage());	
    	}    
        catch (IOException e) {
    	    System.out.println(e.getMessage());	
    	}
        catch (RuntimeException e) {
    	    System.out.println(e.getMessage());	
    	}
        catch (Exception e) {
    	    System.out.println(e.getMessage());	
    	}
        request.setAttribute("imageChanged", imageChanged);
        response.sendRedirect(request.getContextPath() + "/refresh.jsp");
        
    } 
    }
     
       
