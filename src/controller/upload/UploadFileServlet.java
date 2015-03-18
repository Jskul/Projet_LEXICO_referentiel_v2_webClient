package controller.upload;

import java.io.File;
import java.io.IOException;
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
 * TODO
 * 
 * @author	JL
 * @see		http://www.journaldev.com/1964/servlet-upload-file-and-download-file-example
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	/**
	 * TODO
	 */
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    
    /**
     * TODO
     */
    @Override
    public void init() throws ServletException{
    	Utilities.trace(this.getClass().getName(), ".init()", null, true, false);
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
        Utilities.trace(this.getClass().getName(), ".init()", null, false, false);
    }
    
    /**
     * TODO
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utilities.trace(this.getClass().getName(), ".doGet()", null, true, false);
    	// TODO
    	Utilities.trace(this.getClass().getName(), ".doGet()", null, false, false);
    }
 
    /**
     * TODO
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utilities.trace(this.getClass().getName(), ".doPost()", null, true, false);
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("TODO Content type is not multipart/form-data"); // TODO
        }

        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();

            while(fileItemsIterator.hasNext()){
                FileItem fileItem = fileItemsIterator.next();
                File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                Utilities.trace(this.getClass().getName(), ".doPost()", "FieldName = " + fileItem.getFieldName(), null, false);
                Utilities.trace(this.getClass().getName(), ".doPost()", "FileName = " + fileItem.getName(), null, false);
                Utilities.trace(this.getClass().getName(), ".doPost()", "ContentType = " + fileItem.getContentType(), null, false);
                Utilities.trace(this.getClass().getName(), ".doPost()", "Size in bytes = " + fileItem.getSize(), null, false);
                Utilities.trace(this.getClass().getName(), ".doPost()", "Absolute Path at server = " + file.getAbsolutePath(), null, false);
                fileItem.write(file);
            }
        } catch (FileUploadException e) {
        	//throw new Exception("TODO FileUploadException in uploading file."); // TODO
        } catch (Exception e) {
        	//throw new Exception("TODO Exception in uploading file."); // TODO
        }

        Utilities.trace(this.getClass().getName(), ".doPost()", null, false, false);
    }
 
}
