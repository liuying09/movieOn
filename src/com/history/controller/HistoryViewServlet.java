package com.history.controller;

import java.io.IOException;
import java.sql.Date;
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

public class HistoryViewServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/**********************************查看單一歷史紀錄*************************************/

		if ("getOne_For_View".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer movieId = new Integer(req.getParameter("movieId"));
				Integer theaterId = new Integer(req.getParameter("theaterId"));
				String showingDateStr = req.getParameter("showingDate");
				Date showingDate = Date.valueOf(showingDateStr);

				ShowingService showingSvc = new ShowingService();
				List<ShowingVO> showingVO = showingSvc.getShowingTime(theaterId, movieId, showingDate);

				req.setAttribute("showingVO", showingVO);
				String url = "/showing/history_view.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統異常!" + e.getMessage());
				RequestDispatcher failur = req.getRequestDispatcher("/showing/showing_searcg.jsp");
				failur.forward(req, res);
			}

		}

		/*****************************************刪除***********************************************/

		if ("getOne_For_Delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				HttpSession session = req.getSession();
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
				Integer userId = memberVO.getUserid();

				Integer movieId = new Integer(req.getParameter("movieId"));
				Integer theaterId = new Integer(req.getParameter("theaterId"));
				String showingDateStr = req.getParameter("showingDate");
				Date showingDate = Date.valueOf(showingDateStr);

				ShowingService showingSvc = new ShowingService();
				List<ShowingVO> list = showingSvc.getShowingTime(theaterId, movieId, showingDate);
				HistoryService historySvc = new HistoryService();

				for (ShowingVO showingVO2 : list) {
					historySvc.deleteHistory(userId, (showingVO2.getShowingId()));
				}

				/********************************刪除後抓取新的歷史紀錄丟回去************************************/

				// 從userId抓出他所有的showingId
				List<HistoryVO> historyVO = historySvc.getByUser(userId);

				ShowingVO showingVO = null;
				List<ShowingVO> showingVO1 = new ArrayList<ShowingVO>();

				// 透過showingId抓出單一showingVO物件並裝入集合
				for (HistoryVO historyVO2 : historyVO) {
					showingVO = new ShowingVO();
					showingVO.setShowingId(historyVO2.getShowingId());
					showingVO1.add(showingVO);
				}

				req.setAttribute("showingVO1", showingVO1);
				String url = "/showing/history.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("系統異常!" + e.getMessage());
				RequestDispatcher failur = req.getRequestDispatcher("/showing/showing_searcg.jsp");
				failur.forward(req, res);
			}
		}
	}
}
