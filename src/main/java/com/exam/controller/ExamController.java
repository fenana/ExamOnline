package com.exam.controller;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exam.model.Exam;
import com.exam.model.ExamHistory;
import com.exam.model.ExamHistoryForStudent;
import com.exam.model.ExamScore;
import com.exam.model.ExamStudent;
import com.exam.model.ExamStudentDemo;
import com.exam.model.Question;
import com.exam.model.QuestionDemo;
import com.exam.model.User;
import com.exam.service.IExamClassService;
import com.exam.service.IExamHistoryService;
import com.exam.service.IExamScoreService;
import com.exam.service.IExamService;
import com.exam.service.IExamStudentService;
import com.exam.service.IQuestionService;
import com.exam.serviceImpl.ExamServiceImpl;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

	@Autowired
	IExamService examService;//null

	@Autowired
	IExamClassService examClassService;

	@Autowired
	IExamStudentService examStudentService;

	@Autowired
	IQuestionService questionService;

	@Autowired
	IExamHistoryService examHistoryService;

	@Autowired
	IExamScoreService examScoreService;

	/**
	 * 发布一场考试 首先需要验证用户身份是否为管理员，然后接收考试参数（包括考试名称、考试时间、考试人员、试题等），验证是否合法，然后发布考试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/publish")
	public void publishExam(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("publish...");
		Map<String, String[]> map = request.getParameterMap();
		Enumeration<String> paraNames = request.getParameterNames();
		dealExamPublishParas(map, paraNames);// 处理数据
	}

	// 处理数据
	private void dealExamPublishParas(Map<String, String[]> map,
			Enumeration<String> paraNames) {
		Exam exam = new Exam();
		List<Question> questionList = new ArrayList<>();
		List<ExamStudent> studentList = new ArrayList<>();

		// 处理参数
		while (paraNames.hasMoreElements()) {
			String thisPara = paraNames.nextElement();
			if (thisPara.equalsIgnoreCase("examName")) {
				exam.setName(map.get(thisPara)[0]);
			} else if (thisPara.equalsIgnoreCase("examPassword")) {
				exam.setPassword(map.get(thisPara)[0]);
			} else if (thisPara.equalsIgnoreCase("classId")) {
				exam.setClassId(Integer.parseInt(map.get(thisPara)[0]));
			} else if (thisPara.equalsIgnoreCase("startTime")) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				try {
					Date date = df.parse(map.get(thisPara)[0]);
					exam.setStartTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (thisPara.equalsIgnoreCase("length")) {
				int length = Integer.parseInt(map.get(thisPara)[0]);
				exam.setLength(length);
				if (exam.getStartTime() != null) {
					long s = exam.getStartTime().getTime();
					s = s + length * 60 * 1000;
					exam.setEndTime(new Date(s));
				}
			} else if (thisPara.contains("questionList")
					&& !thisPara.contains("questionList[0]")) {
				Question que = new Question();
				String[] thisQS = map.get(thisPara);
				if (thisQS.length == 5) {
					que.setContent(thisQS[1]);
					que.setOptions(thisQS[2]);
					que.setAnswer(thisQS[3]);
					que.setPoint((int) (Double.parseDouble(thisQS[4])));
					if (que.getAnswer().length() == 1) {
						que.setType(0);
					} else if (que.getAnswer().length() > 1) {
						que.setType(1);
					}
					que.setCreateTime(new Date());//
					// que.setClassId(exam.getClassId());//现在，exam的classid可能还没解析得到，放到最后进行设置即可。
					questionList.add(que);
				}
			} else if (thisPara.equalsIgnoreCase("questionNumber")) {
				exam.setNumber(Integer.parseInt(map.get(thisPara)[0]));
			} else if (thisPara.contains("studentList")
					&& !thisPara.contains("studentList[0]")) {
				ExamStudent es = new ExamStudent();
				String[] thisES = map.get(thisPara);
				if (thisES.length == 5) {
					// es.setExamId(exam.getId());//id是addExam之后由数据库自动生成的，放在下面设置
					es.setStudentName(thisES[1]);
					es.setStudentGrade(Integer.parseInt(thisES[2]));
					es.setStudentClass(Integer.parseInt(thisES[3]));
					es.setStudentEmail(thisES[4]);

					studentList.add(es);
				}
			}
		}

		// 保存数据至数据库
		if (exam != null && !questionList.isEmpty() && !studentList.isEmpty()) {
			exam.setCreateTime(new Date());
			examService.addExam(exam);
			for (int i = 0; i < questionList.size(); i++) {
				questionList.get(i).setClassId(exam.getClassId());// set classid
																	// for
																	// question
			}
			questionService.saveQuestionInList(questionList);

			Exam matchExam = examService.getMatchExam(exam);// 获得之前add的Exam
			for (int i = 0; i < studentList.size(); i++) {
				studentList.get(i).setExamId(matchExam.getId());
			}
			// senEmail(exam,studentList);//发送邮件，报错了，暂时注销
			examStudentService.saveStudentInList(studentList);
		}

	}

	@RequestMapping(value = "/publishSuccess")
	public ModelAndView publishSuccess(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("root/examHistory");
	}

	/**
	 * 上传考试题目，返回包含了考试题目的main界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadQuestions")
	@ResponseBody
	public Object uploadQuestions(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("开始上传文件至服务器");
		String filePath = saveHttpFile(request);
		List<Question> questionList = new ArrayList<Question>();

		if (filePath != null && !filePath.equals("")) {
			questionList = getQuestionFromFile(filePath);
		}

		Map map = new HashMap<>();
		map.put("questionList", questionList);
		return map;
	}

	/**
	 * 读取Excel文件内容，返回试题的列表
	 * 
	 * @param filepath
	 * @return
	 */
	public List<Question> getQuestionFromFile(String filePath) {
		List<Question> list = new ArrayList<Question>();
		File targetFile = new File(filePath);
		// 调用父类的通用方法来读取Excel文件内容
		// ArrayList<ArrayList<String>> content =
		// getExcelFileContent(targetFile);//注意现在是假数据
		List<List<String>> content = getExcelFileContent(filePath);
		if (content.size() >= 2) {
			List<String> header = content.remove(0);
			for (List<String> clist : content) {
				Question qe = new Question();
				qe.setContent(clist.get(0));
				qe.setAnswer(clist.get(clist.size() - 2));// 倒数第二个是答案
				qe.setPoint((int) (Double.parseDouble(clist.get(clist.size() - 1))));// 倒数第一个是分值,类型是String表示的double

				String options = "";
				for (int i = 1; i < clist.size() - 2; i++) {
					options = options + clist.get(i) + ";  ";// 特别注意，选项的分割是利用;+两个空格来决定的
				}
				qe.setOptions(options);

				if (qe.getAnswer().length() == 1) {//
					qe.setType(0);// 单选
				} else if (qe.getAnswer().length() > 1) {
					qe.setType(1);// 多选
				}
				qe.setCreateTime(new Date());

				list.add(qe);
			}
		}
		return list;
	}

	/**
	 * 上传考生名单，返回包含了考生的界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/uploadStudents")
	@ResponseBody
	public Object uploadStudents(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("开始上传文件至服务器");
		String filePath = saveHttpFile(request);
		List<ExamStudent> studentList = new ArrayList<ExamStudent>();

		if (filePath != null && !filePath.equals("")) {
			studentList = getExamStudentFromFile(filePath);
		}

		Map map = new HashMap<>();
		map.put("studentList", studentList);
		return map;
	}

	/**
	 * 读取Excel文件内容，返回考生列表
	 * 
	 * @param filepath
	 * @return
	 */
	public List<ExamStudent> getExamStudentFromFile(String filePath) {
		List<ExamStudent> list = new ArrayList<ExamStudent>();
		// 调用父类的通用方法来读取Excel文件内容
		List<List<String>> content = getExcelFileContent(filePath);
		if (content.size() >= 2) {
			List<String> header = content.remove(0);
			for (List<String> clist : content) {
				ExamStudent es = new ExamStudent();
				es.setStudentName(clist.get(0));
				es.setStudentGrade(Integer.parseInt(clist.get(1)
						.substring(0, 1)));
				es.setStudentClass(Integer.parseInt(clist.get(2)
						.substring(0, 1)));
				es.setStudentEmail(clist.get(3));

				list.add(es);
			}
		}
		return list;
	}

	/**
	 * ResponseBody的作用是自动将返回值转换为JSON
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object listExam(HttpServletRequest request,
			HttpServletResponse response) {
		List<Exam> list = examService.getExamList();
		List<Exam> inList = new ArrayList<Exam>();
		List<Exam> lastList = new ArrayList<Exam>();// 分为还未开始、正在进行与已经结束的考试，注意，一旦考生进行过某场考试，即使时间还未截止，依然算是考过了。。

		Date date = new Date();
		for (int i = 0; i < list.size(); i++) {
			User user = getCurrentUser(request);
			if(user.getIdentity()==1) {//学生用户需要区分已经被进行过但截至时间还未到的考试
				List<ExamHistory> historyList = examHistoryService.getExamHistoryByPara(list.get(i).getId(),
						user.getEmail());
				if(!historyList.isEmpty()) {//表示这名学生已经进行过这场考试了，需要放入历史考试中
					lastList.add(list.get(i));
				}else {//下面才能比较时间
					if (list.get(i).getEndTime().before(date)) {
						lastList.add(list.get(i));
					} else {
						inList.add(list.get(i));
					}
				}
			}else if(user.getIdentity()==0) {
				if (list.get(i).getEndTime().before(date)) {
					lastList.add(list.get(i));
				} else {
					inList.add(list.get(i));
				}
			}
			
		}

		HashMap map = new HashMap();
		map.put("inList", inList);
		map.put("lastList", lastList);
		return map;
	}

	/**
	 * 学生查看个人的考试记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/studentExamHistory")
	public ModelAndView listStudentExam(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("student/examHistory");
		User user = getCurrentUser(request);
		List<ExamScore> scoreList = examScoreService.getExamScoreList(user
				.getEmail());// 默认用户邮箱和考生邮箱是一致的

		List<ExamHistoryForStudent> examHistoryList = new ArrayList<ExamHistoryForStudent>();
		// 遍历ScoreList，将所有结果整合后放入examHistoryList
		for (int i = 0; i < scoreList.size(); i++) {
			ExamHistoryForStudent ehs = new ExamHistoryForStudent();
			ehs.setExamId(scoreList.get(i).getExamId());
			ehs.setStudentEmail(scoreList.get(i).getStudentEmail());
			ehs.setTotalPoint(scoreList.get(i).getTotalPoint());
			ehs.setTotalScore(scoreList.get(i).getTotalScore());

			Exam exam = examService.getExamById(ehs.getExamId());
			ehs.setName(exam.getName());
			ehs.setStartTime(exam.getStartTime());
			ehs.setEndTime(exam.getEndTime());
			ehs.setLength(exam.getLength());

			examHistoryList.add(ehs);// 添加至examHistoryList
		}

		view.addObject("examHistoryList", examHistoryList);
		return view;
	}


	@RequestMapping(value = "/examDetail")
	public ModelAndView lookExamDetail(HttpServletRequest request,
			HttpServletResponse response) {
		User user = getCurrentUser(request);
		if (user != null && user.getIdentity() == 0) {// 管理员
			ModelAndView view = new ModelAndView("root/examDetail");
			String examId = request.getParameter("examId");

			if (examId != null) {
				Exam exam = examService.getExamById(Integer.parseInt(examId));
				view.addObject("exam", exam);

				List<ExamScore> examSocreList = examScoreService
						.getExamScoreByExamId(Integer.parseInt(examId));
				view.addObject("examSocreList", examSocreList);
			}
			return view;
		}

		return new ModelAndView("false");
	}

	@RequestMapping(value = "/downloadFile")
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("运行到ExamController中的downloadFile");
		response.setContentType("octets/stream");
		String fileType = request.getParameter("filetype");
		if (fileType != null && fileType.equals("student")) {
			response.addHeader("Content-Disposition",
					"attachment;filename=studentDemo.xlsx");
			// 测试图书
			ExportExcel<ExamStudentDemo> ex = new ExportExcel<ExamStudentDemo>();
			String[] headers = { "姓名", "年级", "班级", "邮箱" };

			List<ExamStudentDemo> dataset = new ArrayList<>();
			ExamStudentDemo es = new ExamStudentDemo();
			es.setStudentName("gary");
			es.setStudentGrade(4);
			es.setStudentClass(2);
			es.setStudentEmail("46536207@gary.com");

			dataset.add(es);
			try {
				OutputStream out = response.getOutputStream();// 获得页面的输出流
				ex.exportExcel("student", headers, dataset, out, "yyy-MM-dd");// exportExcel方法里，将Excel表输出到了上面的输出流中
				out.close();
				System.out.println("studenDemo excel导出成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (fileType != null && fileType.equals("question")) {
			response.addHeader("Content-Disposition",
					"attachment;filename=questionDemo.xlsx");
			// 测试图书
			ExportExcel<QuestionDemo> ex = new ExportExcel<QuestionDemo>();
			String[] headers = { "题目", "选项A", "选项B", "选项C", "选项D", "答案", "分值" };

			List<QuestionDemo> dataset = new ArrayList<>();
			QuestionDemo ed = new QuestionDemo();
			ed.setContent("试问常见排序算法有几种呢？");
			ed.setOptionA("5");
			ed.setOptionB("6");
			ed.setOptionC("7");
			ed.setOptionD("8");
			ed.setAnswer("D");
			ed.setPoint(5);
			dataset.add(ed);

			try {
				OutputStream out = response.getOutputStream();// 获得页面的输出流
				ex.exportExcel("question", headers, dataset, out, "yyy-MM-dd");// exportExcel方法里，将Excel表输出到了上面的输出流中
				out.close();
				System.out.println("questionDemo excel导出成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/queryExam")
	public ModelAndView verifyExamInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("student/queryExam");
		String examId = request.getParameter("examId");
		if (examId != null && examId != "") {
			Exam exam = examService.getExamById(Integer.parseInt(examId));
			if (exam != null) {
				view.addObject("exam", exam);
			}
		}
		return view;
	}

	// 考试详情
	@RequestMapping(value = "/paperDetail")
	public ModelAndView queryExamDetail(HttpServletRequest request,
			HttpServletResponse response) {
//		ModelAndView view = new ModelAndView("student/paperDetail");
		ModelAndView view = null;
		User user = getCurrentUser(request);
		if (user.getIdentity()==0) {
			view = new ModelAndView("root/paperDetail");
		} else if(user.getIdentity()==1){
			view = new ModelAndView("student/paperDetail");

		}
		String examId = request.getParameter("examId");
		String studentEmail = request.getParameter("studentEmail");
		if (examId != null && examId != "" &&
				studentEmail != null && studentEmail != "") {
			List<ExamHistory> examHistory = examHistoryService
					.getExamHistoryByPara(Integer.parseInt(examId), studentEmail);//
//					.getExamHistoryByExamId(Integer.parseInt(examId));
			List content = new ArrayList();
			List options = new ArrayList();
			List answer = new ArrayList();

			for (int i = 0; i < examHistory.size(); i++) {
				// 题目内容
				String questionContent = questionService.selectQuestionById(
						examHistory.get(i).getQuestionId()).getContent();
				content.add(questionContent);
				// 题目选项
				String questionOptions = questionService.selectQuestionById(
						examHistory.get(i).getQuestionId()).getOptions();
				options.add(questionOptions);
				// 题目正确答案
				String questionAnswer = questionService.selectQuestionById(
						examHistory.get(i).getQuestionId()).getAnswer();

				answer.add(questionAnswer);

			}

			if (examHistory != null) {
				view.addObject("examHistory", examHistory);
				view.addObject("contentList", content);
				view.addObject("optionsList", options);
				view.addObject("answerList", answer);
			}
		}
		return view;
	}

	/**
	 * 开始考试 学生需要输入考试密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/startExam")
	public ModelAndView startExam(HttpServletRequest request,
			HttpServletResponse response) {
		String examId = request.getParameter("examId");
		String studentEmail = request.getParameter("studentEmail");
		String examPassword = request.getParameter("examPassword");

		// 验证考试和考生信息
		Exam exam = null;
		ExamStudent examStudent = null;
		if (examId != null && examId != "" && examPassword != null
				&& examPassword != "" && studentEmail != null
				&& studentEmail != "") {

			Exam ex = examService.getExamById(Integer.parseInt(examId));
			if (ex.getPassword().equals(examPassword)) {
				exam = ex;// 密码也必须相同
			}

			ExamStudent es = examStudentService.getExamStudentByPara(
					Integer.parseInt(examId), studentEmail);
			if (es != null) {
				examStudent = es;
			}
		}

		ModelAndView view = null;
		if (exam != null && examStudent != null) {
			view = new ModelAndView("student/inExam");
			// 返回的视图中应该包含该名学生要参加的这一门考试的试卷内容
			view.addObject("exam", exam);
			view.addObject("examStudent", examStudent);
			List<Question> list = questionService.getQuestionByClassId(exam
					.getClassId());
			List<Question> questionList = new ArrayList<>();
			for (int i = 0; i < exam.getNumber(); i++) {
				int index = (int) (Math.random() * exam.getNumber());// 生成0 -
																		// (exam.getNumber-1)之间的随机数
				Question thisQuestion = list.get(index);

				boolean isRepeated = false;
				for (int j = 0; j < questionList.size(); j++) {
					if (thisQuestion.getId() == questionList.get(j).getId()) {
						isRepeated = true;
					}
				}
				if (isRepeated == true) {// 问题重复
					i--;
					continue;
				} else {
					questionList.add(thisQuestion);
				}
			}

			view.addObject("questionList", questionList);
		} else {
			view = new ModelAndView("false");
		}
		return view;
	}

	/**
	 * 提交考试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/submit")
	public ModelAndView submitExam(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("student/main");
		Enumeration<String> names = request.getParameterNames();
		Map<String, String[]> map = request.getParameterMap();

		List<ExamHistory> examHistoryList = new ArrayList<ExamHistory>();
		int totalPoint = 0;// 存储试卷总分
		int totalScore = 0;// 存储试卷总得分

		int examId = 0;
		String studentEmail = null;
		while (names.hasMoreElements()) {
			String para = names.nextElement();// 除开examId和studentEmail两个参数外，前端传过来的都是复选框，它们的name就是question的id
			if (para.equals("examId")) {
				examId = Integer.parseInt(map.get(para)[0]);
			} else if (para.equals("studentEmail")) {
				studentEmail = map.get(para)[0];
			} else {
				// 剩下的都是复选框
				System.out.println(para + ":" + map.get(para)[0]);

				ExamHistory examHistory = new ExamHistory();
				if (examId != 0 && studentEmail != null) {
					examHistory.setExamId(examId);
					examHistory.setStudentEmail(studentEmail);
				}
				examHistory.setQuestionId(Integer.parseInt(para));
				String options = "";// 对于该题，用户的答案
				for (int i = 0; i < map.get(para).length; i++) {
					options = options + map.get(para)[i];
				}

				Question que = questionService.selectQuestionById(examHistory
						.getQuestionId());
				totalPoint = totalPoint + que.getPoint();// 添加该题分值至试卷总分值
				if (options.equals(que.getAnswer())) {
					examHistory.setScore(que.getPoint());
					totalScore = totalScore + que.getPoint();// 添加该题得分至总得分
				} else {
					examHistory.setScore(0);
					// totalScore = totalScore + 0;目前多选题错答、多答、少答也是0分
				}

				// 保存这一题到examHistoryList
				if (examHistory.getExamId() != null
						&& examHistory.getStudentEmail() != null
						&& examHistory.getQuestionId() > 0
						&& examHistory.getScore() >= 0) {
					examHistoryList.add(examHistory);
				}
			}

		}

		if (examHistoryList.size() > 0) {
			ExamScore examScore = new ExamScore();// 存储考试总成绩
			examScore.setExamId(examId);
			examScore.setStudentEmail(studentEmail);
			examScore.setTotalPoint(totalPoint);
			examScore.setTotalScore(totalScore);

			examScoreService.insertExamScore(examScore);// 保存试卷总分
			examHistoryService.saveExamHistoryList(examHistoryList);// 保存至数据库
		}
		// 获得学生信息、试卷信息与试卷答案；然后计算试卷分数、统计并存入数据库，待考试结束后发送至学生邮箱等
		return view;
	}

}
