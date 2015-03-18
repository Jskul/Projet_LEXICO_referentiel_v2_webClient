package controller.upload;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import clientServer.utility.Utilities;

/**
 * TODO
 * 
 * @author	JL
 * @see		http://www.journaldev.com/1964/servlet-upload-file-and-download-file-example
 */
@WebListener
public class FileLocationContextListener implements ServletContextListener {
 
	/**
	 * TODO
	 */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	Utilities.trace(this.getClass().getName(), ".contextInitialized()", null, true, false);
        String rootPath = System.getProperty("catalina.home");
        ServletContext ctx = servletContextEvent.getServletContext();
        String relativePath = ctx.getInitParameter("tempfile.dir");
        File file = new File(rootPath + File.separator + relativePath);
        if(!file.exists()) file.mkdirs();
        ctx.setAttribute("FILES_DIR_FILE", file);
        ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
        Utilities.trace(this.getClass().getName(), ".contextInitialized()", null, false, false);
    }
 
    /**
     * TODO
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }

}
