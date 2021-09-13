package com.history.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.history.model.HistoryService;
import com.history.model.HistoryVO;
import com.member.model.MemberVO;
import com.showing.model.ShowingService;
import com.showing.model.ShowingVO;

public class HistoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		/******************************** 查詢出登入者的歷史紀錄 *********************************/

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {

			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute("memberVO");

			if (member == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
				res.sendRedirect(req.getContextPath() + "/member/log_in.jsp"); // *工作2 : 請該user去登入網頁(login.html) , 進行登入
				return;
			}

			Integer userId = member.getUserid();

			// 從userId抓出他所有的showingId
			HistoryService historySvc = new HistoryService();
			List<HistoryVO> historyVO = historySvc.getByUser(userId);

			ShowingVO showingVO = null;
			List<ShowingVO> showingVO1 = new ArrayList<ShowingVO>();

			// 透過showingId抓出單一showingVO物件並裝入集合
			for (HistoryVO historyVO2 : historyVO) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(historyVO2.getShowingId());
				showingVO1.add(showingVO);
			}

			// 使用者沒有紀錄的處理
			if (showingVO1.size() == 0) {
				req.setAttribute("message", "尚無紀錄");
			}

			req.setAttribute("showingVO1", showingVO1);
			String url = "/showing/history.jsp";
			RequestDispatcher success = req.getRequestDispatcher(url);
			success.forward(req, res);

		} catch (Exception e) {
			errorMsgs.add("查無資料" + e.getMessage());
			RequestDispatcher failur = req.getRequestDispatcher("/showing/showing_search.jsp");
			failur.forward(req, res);
		}

	}

}
