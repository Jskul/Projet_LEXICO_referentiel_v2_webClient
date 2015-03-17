package controller.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import clientServer.utility.Utilities;


/**
 * 
 * @author	JL
 * @see		http://www.journaldev.com/1964/servlet-upload-file-and-download-file-example
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    
    @Override
    public void init() throws ServletException{
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utilities.trace(this.getClass().getName(), ".doGet()", null, true, false);
    	
    	Utilities.trace(this.getClass().getName(), ".doGet()", null, false, false);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utilities.trace(this.getClass().getName(), ".doPost()", null, true, false);
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Content type is not multipart/form-data");
        }
         
        Utilities.trace(this.getClass().getName(), ".doPost()", "A", null, false);
        
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.write("<html><head></head><body>");
        try {
        	Utilities.trace(this.getClass().getName(), ".doPost()", "B", null, false);
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Utilities.trace(this.getClass().getName(), ".doPost()", "C", null, false);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            Utilities.trace(this.getClass().getName(), ".doPost()", "D", null, false);

            while(fileItemsIterator.hasNext()){
                FileItem fileItem = fileItemsIterator.next();
                System.out.println("FieldName="+fileItem.getFieldName());
                System.out.println("FileName="+fileItem.getName());
                System.out.println("ContentType="+fileItem.getContentType());
                System.out.println("Size in bytes="+fileItem.getSize());
                 
                File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
System.out.println("Absolute Path at server="+file.getAbsolutePath());
                fileItem.write(file);
//                out.write("File "+fileItem.getName()+ " uploaded successfully.");
//                out.write("<br>");
//                out.write("<a href=\"UploadDownloadFileServlet?fileName="+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");
            }
        } catch (FileUploadException e) {
        	System.out.println("Raté 1");
//            out.write("Exception in uploading file.");
        } catch (Exception e) {
        	System.out.println("Raté 2");
//            out.write("Exception in uploading file.");
        }
//        out.write("</body></html>");
        
        Utilities.trace(this.getClass().getName(), ".doPost()", null, false, false);
    }
 
}
