package com.key.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class DownloadUtil {

    public static void download(String filePath, HttpServletRequest request, HttpServletResponse response) {
        try {
            int index = filePath.lastIndexOf("/");
            String fileName = filePath.substring(index + 1);
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            String realPath = request.getServletContext().getRealPath(filePath);
            File file = new File(realPath);
            if(!file.exists()) {
                return;
            }
            InputStream in = new FileInputStream(file);

            download(in, request, response);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 启动下载
     * @param fileName 下载后要保存成的文件名称
     * @param response 设置下载模式
     */
    public static void startDownload(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
    }

    public static void download(InputStream in, HttpServletRequest request, HttpServletResponse response) {
            try {
                int len = 0;
                byte[] buffer = new byte[1024];
                OutputStream out = response.getOutputStream();
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                in.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

    }

}
