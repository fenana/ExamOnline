package com.exam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.exam.model.Exam;
import com.exam.model.ExamClass;
import com.exam.model.ExamStudent;
import com.exam.model.Question;
import com.exam.model.User;
import com.exam.service.IExamClassService;
import com.exam.service.IExamService;

/*
 * 登记Controller类需要的公共方法，如getCurrentUser()
 */
public class BaseController {
	
	/**
	 * 获得当前用户
	 * @param request
	 * @return
	 */
	protected User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User)session.getAttribute("user");
	}
	
	/**
	 * 更新当前用户
	 * @param request
	 * @param newUser
	 */
	protected void updateCurrentUser(HttpServletRequest request, User newUser) {
		HttpSession session = request.getSession();
		session.setAttribute("user", newUser);
	}
	
	/**
	 * 接收上传文件
	 * @param request
	 * @return
	 */
	protected String saveHttpFile(HttpServletRequest request) {
		String filePath = "";
		List<Question> questionList = new ArrayList<Question>();
		
		DiskFileUpload diskFileUpload = new DiskFileUpload();//这个已经被废弃了，之后改成菜鸟教程的示例中的做法‘
		try {
			List<FileItem> fileList = diskFileUpload.parseRequest(request);//解析表单中每一个字段的数据，
			//包装成独立的FileItem对象并且以List方式返回
			//下面处理所有的FileItem
			for(FileItem i:fileList) {
				if(!i.isFormField()) {
					//File remoteFile = new File(new String(i.getName().getBytes(),"UTF-8"));//获得要上传的文件的引用
					File remoteFile = new File(i.getName());//不使用转换编码格式的话就可以传递文件名为中文的文件了
					//下面指定要把上面的remoteFile保存为？
					File saveFile = new File(new String(remoteFile.getName()));
					saveFile.createNewFile();
					
					//下面把remoteFile的内容输出到saveFile
					InputStream ins = i.getInputStream();//获得这个fileItem的输入流
					OutputStream ous = new FileOutputStream(saveFile);//获得saveFile的输出流
					
					byte[] buffer = new byte[1024];
					int len=0;
					while( (len=ins.read(buffer)) > -1) {
						ous.write(buffer, 0, len);
					}
					ous.close();
					ins.close(); 
					
					filePath = saveFile.getAbsolutePath();
					break;//找到第一个文件就不再继续
				}
			}
		} catch (FileUploadException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filePath;
	}
	
	/**
	 * 解析Excel文件，返回内容
	 * @param path
	 * @return
	 */
	protected List<List<String>> getExcelFileContent(String path){
		if(path.lastIndexOf(".xls")>=1 && path.lastIndexOf(".xlsx")<=0) {
			return getXlsFileContent(path);
		}else if(path.lastIndexOf(".xlsx")>=1) {
			return getXlsxFileContent(path);
		}
		
		return null;
	}
	
	/**
	 * excel in .xls
	 * use HSSF
	 * @param path
	 * @return
	 */
	private List<List<String>> getXlsFileContent(String path){
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			InputStream is = new FileInputStream(path);
			HSSFWorkbook xssfWorkbook = new HSSFWorkbook(is);//代表整个Excel文件
			//遍历文件的每一页
			for(int i=0;i<xssfWorkbook.getNumberOfSheets();i++) {
				HSSFSheet sheet = xssfWorkbook.getSheetAt(i);//该页
				if(sheet==null) {
					continue;
				}
				//处理当前页，循环读取每一行
				int lines = sheet.getLastRowNum();
				for(int rowNum=0;rowNum<=lines;rowNum++) {
					HSSFRow row = sheet.getRow(rowNum);//这一行
					if(row==null) {
						continue;
					}
					int minColIx = row.getFirstCellNum();
					int lastColIx = row.getLastCellNum();
					List<String> rowList = new ArrayList<String>();
					//遍历这一行，读取每一个Cell元素
					for(int colIx = minColIx; colIx<lastColIx; colIx++) {
						HSSFCell cell = row.getCell(colIx);
						//rowList.add(ExcelUtils.getStringVal(cell));
						rowList.add(cell.getStringCellValue());
						//cell.toString();
					}
					list.add(rowList);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * excel in .xlsx
	 * use XSSF
	 * @param path
	 * @return
	 */
	private List<List<String>> getXlsxFileContent(String path){
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			InputStream is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);//代表整个Excel文件
			//遍历文件的每一页
			for(int i=0;i<xssfWorkbook.getNumberOfSheets();i++) {
				XSSFSheet sheet = xssfWorkbook.getSheetAt(i);//该页
				if(sheet==null) {
					continue;
				}
				//处理当前页，循环读取每一行
				int lines = sheet.getLastRowNum();
				for(int rowNum=0;rowNum<=lines;rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);//这一行
					if(row==null) {
						continue;
					}
					int minColIx = row.getFirstCellNum();
					int lastColIx = row.getLastCellNum();
					List<String> rowList = new ArrayList<String>();
					//遍历这一行，读取每一个Cell元素
					for(int colIx = minColIx; colIx<lastColIx; colIx++) {
						XSSFCell cell = row.getCell(colIx);
						//rowList.add(ExcelUtils.getStringVal(cell));
						rowList.add(cell.toString());
						//cell.toString();
					}
					list.add(rowList);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Autowired 
	IExamClassService examClassService;
	/**
	 * 返回可选课程列表
	 * @return
	 */
	protected List<ExamClass> getExamClassAsList(){
		return examClassService.getExamClassList();
	}
	
	@Autowired 
	IExamService examService;
	/**
	 * 返回考试列表
	 * @return
	 */
	protected List<Exam> getExamList(){
		return examService.getExamList();
	}
	
	/**
	 * send email
	 * @param exam
	 * @param studentList
	 */
	protected void senEmail(Exam exam,List<ExamStudent> studentList) {
		sendEmail(studentList,exam.getName(),exam.getPassword(),exam.getStartTime().toLocaleString(),exam.getEndTime().toLocaleString());
	}
	
	/**
	 * 发送邮件至学生邮箱
	 * 邮件需包括：学生信息、考试名称、考试密码、开始和结束时间
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	protected boolean sendEmail(List<ExamStudent> list,String examName,String examPassword,String startTime,String endTime) {
		// 发件人的邮箱和密码
		String myEmailAccount = "1272072141@qq.com";
		String myEmailPassword = "gavubktcmkyhjaac";
		
		// 发件人邮箱的SMTP服务器地址，必须准确，不同邮件服务器地址不同，一般格式为：smtp.xxx.com
		//QQ邮箱的SMTP服务器地址为：smtp.qq.com
		String myEmailSMTPHost = "smtp.qq.com";
		//收件人
		String receiveMailAccount = list.get(0).getStudentEmail();
		return true;
	}
	
	private void createEmai(List<ExamStudent> list,String examName,String examPassword,String startTime,String endTime,String myEmailSMTPHost,String myEmailAccount, String receiveMailAccount, String myEmailPassword) throws Exception {
		// 1.创建参数配置，用于连接邮件服务器的参数配置
				Properties props = new Properties();		//参数配置
				props.setProperty("mail.transport.protocol", "smtp");	 	//使用的协议（JavaMail规范要求）
				props.setProperty("mail.smtp.host", myEmailSMTPHost);		//发件人的邮箱的SMTP服务器地址
				props.setProperty("mail.smtp.auth", "true");				//需要请求验证
				
				//PS:某些邮箱服务器要求SMTP连接需要使用SSL安全验证（为了提高安全性，邮箱支持SSL连接，也可以自己开启），
				//	如果无法连接邮件服务器，仔细查看控制台打印的log，如果有类似“连接失败，要求SSL安全连接”等错误，
				// 	打开下面/* ··· */之间的注释代码，开启SSL安全连接。
				/*
				 * SMTP服务器的端口（非SSL连接的端口一般默认为25，可以不添加，如果开启了SSL连接，
				 * 				  需要改为对应的邮箱SMTP服务器的端口，具体可查看对应邮箱服务器的帮助，
				 * 	    			  QQ邮箱的SMTP（SLL）端口为465或587，其他邮箱自行去查看）
				
				*/
				final String smtpPort ="465";
				props.setProperty("mail.smtp.port", smtpPort);
				props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.setProperty("mail.smtp.socketFactory.fallback", "false");
				props.setProperty("mail.smtp.socketFactory.port", smtpPort);
			
				// 2.根据配置创建会话对象，用于和邮件服务器交互
				Session session = Session.getInstance(props);
				session.setDebug(true);		//设置为debug模式，可以查看详细的发送log
				
				// 3.创建一封邮件
				MimeMessage message = createMimeMessage(list,examName,examPassword, startTime,endTime,session,myEmailAccount,receiveMailAccount);
				
				// 4.根据Session获取邮件传输对象
				Transport transport = session.getTransport();
				
				// 5.使用邮箱账号和密码连接邮件服务器，这里认证的邮箱必须和message中的发件人邮箱一直，否则报错
				//		
				//	PS_01:成败的判断关键在此一句，如果连接服务器失败，都会在控制台输出相应失败原因的log，	
				//		  仔细查看失败原因，有些邮箱服务器会返回错误码或查看错误类型的链接，根据给出的错误
				//		  类型到对应邮件服务器的帮助网站上查看具体失败原因
				//		
				//	PS_02：连接失败的原因通常为以下几点，自己检查代码：
				//		   （1）邮箱没有开启SMTP服务；
				//		   （2）邮箱密码错误，例如某些邮箱开启了独立密码；
				//		   （3）邮箱服务器要求必须使用SSL安全链接；
				//		   （4）请求过于频繁或其他原因，被邮件服务器拒绝服务；
				//		   （5）如果以上几点都确定无误，到邮件服务器网站查找帮助。
				//		
				//	PS_03：仔细看log，认真看log，看懂log，错误原因都在log已说明
				transport.connect(myEmailAccount,myEmailPassword);
				
				// 6.发送邮件，发送到所有的收件地址，message.getAllRecipients()
				transport.sendMessage(message, message.getAllRecipients());
				
				// 7.关闭链接
				transport.close();

	}

	private MimeMessage createMimeMessage(List<ExamStudent> list,String examName,String examPassword,String startTime,String endTime,Session session, String sendMail, String receiveMail) throws Exception {
		// 1.创建一封邮件
				MimeMessage message = new MimeMessage(session);	//创建邮件对象
				
				//2.From:发件人
				//	其中 InternetAddress 的三个参数分别为：邮箱，显示的昵称（只用于显示，没有特别的要求），昵称的字符集编码
				//  真正要发送时，邮箱必须是真实有效的邮箱
				message.setFrom(new InternetAddress("1272072141@qq.com", "茗柯", "UTF-8"));
				
				//3.To:收件人
				message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(list.get(0).getStudentEmail(), list.get(0).getStudentName(),"UTF-8"));
				for (int i = 1; i < list.size(); i++) {
					message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(list.get(i).getStudentEmail(), list.get(i).getStudentName(),"UTF-8"));
				}
					
				// 4.Subject:邮件主题
				message.setSubject(examName,"UTF-8");
				
				// 5.Content:邮件正文（可以使用html标签）
				message.setContent("各位同学，"+examName+"的密码为"+examPassword+"，本次考试时间为"+startTime+"——"+endTime,"text/html;charset=UTF-8");
				
				// 6.设置显示的发件时间
				message.setSentDate(new Date());
				
				// 7.保存前面的设置
				message.saveChanges();
				
				return message;
	}
	
	

}
