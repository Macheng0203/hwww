package com.swufe.javaee.request_response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.stream.events.DTD;

/**
 * ClassName:    UploadServlet
 * Package:    com.swufe.javaee.request_response
 * Description:
 */


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        System.out.println("upload");
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FileItemFactory factory = new DiskFileItemFactory();

        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 开始解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(request);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        String fileName = null;
        // 对所有请求信息进行判断
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            // 信息为普通的格式
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String value = item.getString();
                request.setAttribute(fieldName, value);
            }
            // 信息为文件格式
            else {
                fileName = item.getName();
                System.out.println(fileName);
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                request.setAttribute("realFileName", fileName);

                String basePath = request.getRealPath("/images");
                String str1=fileName.substring(0, fileName.indexOf("."));
                String filetype=fileName.substring(str1.length()+1);
                fileName=dateToString(new Date())+"."+filetype;
                File file = new File(basePath, fileName);
                try {
                    item.write(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            request.setAttribute("msg","/images/"+fileName);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private static String dateToString(Date time){
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat ("yyyyMMddHHmmss");

        String ctime = formatter.format(time);

        return ctime;

    }

}

















