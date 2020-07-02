package com.core.base.socket.nio.tomcat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class IndexServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 2480837748874267949L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println(name);
		System.out.println(req.getHeader("Content-Type"));
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getHeader("Content-Type"));
//		String name = req.getParameter("name");
//		System.out.println(name);
//		resp.getWriter().print("this is index by tomcat exe");
		doPost2(req, resp);
	}
	
	/**
	 * 文件上传
	 * @param fileName
	 */
	public static void uploadFile(String fileName) {  
	    try {  

	        // 换行符  
	        final String newLine = "\r\n";  
	        //数据分隔线  
	        final String BOUNDARY = "【这里随意设置】";//可以随意设置，一般是用  ---------------加一堆随机字符
	        //文件结束标识
	        final String boundaryPrefix = "--";  

	        // 服务器的域名  
	        URL url = new URL("http://localhost:8070/secondary/HandleFile");  
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	        // 设置为POST情  
	        conn.setRequestMethod("POST");  
	        // 发送POST请求必须设置如下两行  
	        conn.setDoOutput(true);  
	        //conn.setDoInput(true);/不必加，默认为true  
	        //conn.setUseCaches(false);//用于设置缓存，默认为true，不改也没有影响（至少在传输单个文件这里没有）  

	        // 设置请求头参数  
	        //关于keep-alive的说明：https://www.kafan.cn/edu/5110681.html
	        //conn.setRequestProperty("connection", "Keep-Alive");//现在的默认设置一般即为keep-Alive，因此此项为强调用，可以不加
	        //conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows Nt 5.1; SV1)");//用于模拟浏览器，非必须

	        //用于表示上传形式，必须
	        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);  
	        //这里是Charset，网上大多都是Charsert？？？我的天，笑哭。不过好像没什么影响...不知道哪位大佬解释一下
	        conn.setRequestProperty("Charset", "UTF-8");

	        //获取conn的输出流用于向服务器输出信息
	        OutputStream out = new DataOutputStream(conn.getOutputStream());  

	        //构造文件的结构  
	        //写参数头
	        StringBuilder sb = new StringBuilder();  
	        sb.append(boundaryPrefix)//表示报文开始
	          .append(BOUNDARY)//添加文件分界线
	          .append(newLine);//换行，换行方式必须严格约束
	        //固定格式,其中name的参数名可以随意修改，只需要在后台有相应的识别就可以，filename填你想要被后台识别的文件名，可以包含路径
	        sb.append("Content-Disposition: form-data;name=\"file\";")
	            .append("filename=\"").append(fileName)
	            .append("\"")
	            .append(newLine);  
	        sb.append("Content-Type:application/octet-stream");  
	        //换行，为必须格式
	        sb.append(newLine);  
	        sb.append(newLine);  

	        //将参数头的数据写入到输出流中  
	        out.write(sb.toString().getBytes());  
	        System.out.print(sb);

	        //写文件数据（通过数据输入流）  
	        File file = new File(fileName);
	        DataInputStream in = new DataInputStream(new FileInputStream(  
	                file));  
	        byte[] bufferOut = new byte[1024];  
	        int bytes = 0;  
	        //每次读1KB数据,并且将文件数据写入到输出流中  
	        while ((bytes = in.read(bufferOut)) != -1) {  
	            out.write(bufferOut, 0, bytes);  
	        }  
	        in.close();

	        //写参数尾  
	        out.write(newLine.getBytes());  
	        System.out.print(new String(newLine.getBytes()));

	        // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
	        sb = new StringBuilder();
	        sb.append(newLine)
	            .append(boundaryPrefix)
	            .append(BOUNDARY)
	            .append(boundaryPrefix)
	            .append(newLine);
	        // 写上结尾标识  
	        out.write(sb.toString().getBytes());  
	        System.out.println(sb);
	        //输出结束，关闭输出流
	        out.flush();  
	        out.close();  

	        //定义BufferedReader输入流来读取URL的响应 ,注意必须接受来自服务器的返回，否则服务器不会对发送的post请求做处理！！这里坑了我好久
	        BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                conn.getInputStream()));  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {  
	            System.out.println(line);  
	        }  

	    } catch (Exception e) {  
	        System.out.println("发送POST请求出现异常！" + e);  
	        e.printStackTrace();  
	    }  
	}  
	
	/**
	 * 文件上传处理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setCharacterEncoding("UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    //输出到客户端浏览器
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload sup = new ServletFileUpload(factory);//这里要将factory传入，否则会报NullPointerException: No FileItemFactory has been set.
	    try{
	        List<FileItem> list = sup.parseRequest(request);
	        for(FileItem fileItem:list){
	            System.out.println(fileItem.getFieldName()+"--"+fileItem.getName());
	            if(!fileItem.isFormField()){
	                if("file".equals(fileItem.getFieldName())){
	                    //获取远程文件名
	                    String remoteFilename = new String(fileItem.getName().getBytes(),"UTF-8");
	                    File remoteFile = new File(remoteFilename);

	                    //设置服务器端存放文件的位置
	                    File locate = new File("/Users/zhangzhigang/Desktop/file",remoteFile.getName());

//	                      locate.getParentFile().mkdirs();//用于确保文件目录存在,如果为单级目录可以去掉
	                    locate.createNewFile(); //创建新文件

	                    InputStream ins = fileItem.getInputStream();   //FileItem的内容
	                    OutputStream ous = new FileOutputStream(locate); //输出
	                    try{
	                        byte[] buffer = new byte[1024]; //缓冲字节
	                        int len = 0;
	                        while((len = ins.read(buffer))>-1)
	                            ous.write(buffer, 0, len);
	                    }finally{
	                        ous.close();
	                        ins.close();
	                    }
	                }
	            }       
	        }
	    }catch (FileUploadException e){}

	    out.print("everything is ok");
	    out.flush();
	    out.close();

	}
	
	public static void main(String[] args) {
		byte[] b = {71, 69, 84, 32, 47, 100, 101, 109, 111, 47, 105, 110, 100, 101, 120, 63, 110, 97, 109, 101, 61, 50, 50, 50, 50, 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 104, 111, 115, 116, 58, 108, 111, 99, 97, 108, 104, 111, 115, 116, 58, 56, 56, 56, 56, 56, 13, 10, 99, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 107, 101, 101, 112, 45, 97, 108, 105, 118, 101, 101, 13, 10, 99, 97, 99, 104, 101, 45, 99, 111, 110, 116, 114, 111, 108, 58, 109, 97, 120, 45, 97, 103, 101, 61, 48, 48, 13, 10, 117, 112, 103, 114, 97, 100, 101, 45, 105, 110, 115, 101, 99, 117, 114, 101, 45, 114, 101, 113, 117, 101, 115, 116, 115, 58, 49, 49, 13, 10, 117, 115, 101, 114, 45, 97, 103, 101, 110, 116, 58, 77, 111, 122, 105, 108, 108, 97, 47, 53, 46, 48, 32, 40, 87, 105, 110, 100, 111, 119, 115, 32, 78, 84, 32, 49, 48, 46, 48, 59, 32, 87, 105, 110, 54, 52, 59, 32, 120, 54, 52, 41, 32, 65, 112, 112, 108, 101, 87, 101, 98, 75, 105, 116, 47, 53, 51, 55, 46, 51, 54, 32, 40, 75, 72, 84, 77, 76, 44, 32, 108, 105, 107, 101, 32, 71, 101, 99, 107, 111, 41, 32, 67, 104, 114, 111, 109, 101, 47, 55, 55, 46, 48, 46, 51, 56, 54, 53, 46, 57, 48, 32, 83, 97, 102, 97, 114, 105, 47, 53, 51, 55, 46, 51, 54, 54, 13, 10, 115, 101, 99, 45, 102, 101, 116, 99, 104, 45, 109, 111, 100, 101, 58, 110, 97, 118, 105, 103, 97, 116, 101, 101, 13, 10, 115, 101, 99, 45, 102, 101, 116, 99, 104, 45, 117, 115, 101, 114, 58, 63, 49, 49, 13, 10, 97, 99, 99, 101, 112, 116, 58, 116, 101, 120, 116, 47, 104, 116, 109, 108, 44, 97, 112, 112, 108, 105, 99, 97, 116, 105, 111, 110, 47, 120, 104, 116, 109, 108, 43, 120, 109, 108, 44, 97, 112, 112, 108, 105, 99, 97, 116, 105, 111, 110, 47, 120, 109, 108, 59, 113, 61, 48, 46, 57, 44, 105, 109, 97, 103, 101, 47, 119, 101, 98, 112, 44, 105, 109, 97, 103, 101, 47, 97, 112, 110, 103, 44, 42, 47, 42, 59, 113, 61, 48, 46, 56, 44, 97, 112, 112, 108, 105, 99, 97, 116, 105, 111, 110, 47, 115, 105, 103, 110, 101, 100, 45, 101, 120, 99, 104, 97, 110, 103, 101, 59, 118, 61, 98, 51, 51, 13, 10, 115, 101, 99, 45, 102, 101, 116, 99, 104, 45, 115, 105, 116, 101, 58, 99, 114, 111, 115, 115, 45, 115, 105, 116, 101, 101, 13, 10, 97, 99, 99, 101, 112, 116, 45, 101, 110, 99, 111, 100, 105, 110, 103, 58, 103, 122, 105, 112, 44, 32, 100, 101, 102, 108, 97, 116, 101, 44, 32, 98, 114, 114, 13, 10, 97, 99, 99, 101, 112, 116, 45, 108, 97, 110, 103, 117, 97, 103, 101, 58, 122, 104, 45, 67, 78, 44, 122, 104, 59, 113, 61, 48, 46, 57, 57, 13, 10, 13, 10};
		byte[] b2 = {80, 79, 83, 84, 32, 47, 100, 101, 109, 111, 47, 105, 110, 100, 101, 120, 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 99, 111, 110, 116, 101, 110, 116, 45, 116, 121, 112, 101, 58, 109, 117, 108, 116, 105, 112, 97, 114, 116, 47, 102, 111, 114, 109, 45, 100, 97, 116, 97, 59, 32, 98, 111, 117, 110, 100, 97, 114, 121, 61, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 54, 54, 50, 57, 57, 57, 53, 56, 54, 52, 51, 50, 56, 56, 49, 48, 51, 55, 48, 52, 49, 54, 54, 51, 51, 13, 10, 117, 115, 101, 114, 45, 97, 103, 101, 110, 116, 58, 80, 111, 115, 116, 109, 97, 110, 82, 117, 110, 116, 105, 109, 101, 47, 55, 46, 49, 55, 46, 49, 49, 13, 10, 97, 99, 99, 101, 112, 116, 58, 42, 47, 42, 42, 13, 10, 99, 97, 99, 104, 101, 45, 99, 111, 110, 116, 114, 111, 108, 58, 110, 111, 45, 99, 97, 99, 104, 101, 101, 13, 10, 112, 111, 115, 116, 109, 97, 110, 45, 116, 111, 107, 101, 110, 58, 55, 98, 57, 100, 97, 55, 53, 98, 45, 101, 102, 56, 97, 45, 52, 55, 50, 102, 45, 97, 102, 56, 57, 45, 54, 51, 100, 51, 48, 49, 51, 52, 55, 49, 100, 54, 54, 13, 10, 104, 111, 115, 116, 58, 108, 111, 99, 97, 108, 104, 111, 115, 116, 58, 56, 56, 56, 56, 56, 13, 10, 97, 99, 99, 101, 112, 116, 45, 101, 110, 99, 111, 100, 105, 110, 103, 58, 103, 122, 105, 112, 44, 32, 100, 101, 102, 108, 97, 116, 101, 101, 13, 10, 99, 111, 110, 116, 101, 110, 116, 45, 108, 101, 110, 103, 116, 104, 58, 49, 54, 50, 50, 13, 10, 99, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 107, 101, 101, 112, 45, 97, 108, 105, 118, 101, 101, 13, 10, 13, 10, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 54, 54, 50, 57, 57, 57, 53, 56, 54, 52, 51, 50, 56, 56, 49, 48, 51, 55, 48, 52, 49, 54, 54, 51, 13, 10, 67, 111, 110, 116, 101, 110, 116, 45, 68, 105, 115, 112, 111, 115, 105, 116, 105, 111, 110, 58, 32, 102, 111, 114, 109, 45, 100, 97, 116, 97, 59, 32, 110, 97, 109, 101, 61, 34, 110, 97, 109, 101, 34, 13, 10, 13, 10, 49, 50, 51, 13, 10, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 54, 54, 50, 57, 57, 57, 53, 56, 54, 52, 51, 50, 56, 56, 49, 48, 51, 55, 48, 52, 49, 54, 54, 51, 45, 45, 13, 10};
		byte[] b3 = {45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 
					 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 
					 45, 45, 45, 45, 45, 45, 45, 45, 54, 54, 
					 50, 57, 57, 57, 53, 56, 54, 52, 51, 50, 
					 56, 56, 49, 48, 51, 55, 48, 52, 49, 54, 
					 54, 51, 
					 13, 10, 
					 67, 111, 110, 116, 101, 110, 116, 45, 68, 105, 
					 115, 112, 111, 115, 105, 116, 105, 111, 110, 58, 
					 32, 102, 111, 114, 109, 45, 100, 97, 116, 97, 
					 59, 32, 110, 97, 109, 101, 61, 34, 110, 97, 
					 109, 101, 34, 
					 13, 10, 13, 10, 
					 49, 50, 51, 
					 13, 10, 
					 45, 45, 45, 45, 
					 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 
					 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 
					 45, 45, 45, 45, 
					 54, 54, 50, 57, 57, 57, 53, 56, 54, 52, 51, 50, 56, 56, 49, 48, 51, 55, 48, 52, 49, 54, 54, 51, 
					 45, 45, 
					 13, 10};
		System.out.println(new String(b3));
	}
}