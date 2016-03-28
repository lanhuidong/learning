package com.nexusy.tomcat;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author lan
 * @since 2016-02-03
 */
public class ServletProcessor {

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);
        URL[] urls = new URL[1];
        URLStreamHandler handler = null;
        File classPath = new File(Constants.WEB_ROOT);
        try {
            String repository = new URL("file", null, classPath.getCanonicalPath()) + File.separator;
            urls[0] = new URL(null, repository, handler);
            URLClassLoader loader = new URLClassLoader(urls);
            Class myClass = loader.loadClass(servletName);
            Servlet servlet = (Servlet) myClass.newInstance();
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
