package com.nexusy.tomcat;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author lan
 * @since 2016-02-03
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 2048;
    private Request request;
    private OutputStream output;
    private PrintWriter writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        File file = new File(Constants.WEB_ROOT, request.getUri());
        if (file.exists()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            String headers = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html;charset=UTF-8\r\n"
                    + "\r\n";
            output.write(headers.getBytes("UTF-8"));
            try (FileInputStream fis = new FileInputStream(file)) {
                int ch;
                while ((ch = fis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    output.write(buffer, 0, ch);
                }
            }
        } else {
            String fileNotFount = "HTTP/1.1 404 File Not Found\r\n"
                    + "Content-Type: text/html;charset=UTF-8\r\n"
                    + "Content-Length: 23\r\n"
                    + "\r\n"
                    + "<h1>File Not Found</h1>";
            output.write(fileNotFount.getBytes("UTF-8"));
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
