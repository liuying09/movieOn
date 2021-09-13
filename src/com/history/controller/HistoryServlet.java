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

		/******************************** �d�ߥX�n�J�̪����v���� *********************************/

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {

			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute("memberVO");

			if (member == null) { // �p�� null, �N��user���n�J�L , �~���H�U�u�@
				res.sendRedirect(req.getContextPath() + "/member/log_in.jsp"); // *�u�@2 : �и�user�h�n�J����(login.html) , �i��n�J
				return;
			}

			Integer userId = member.getUserid();

			// �quserId��X�L�Ҧ���showingId
			HistoryService historySvc = new HistoryService();
			List<HistoryVO> historyVO = historySvc.getByUser(userId);

			ShowingVO showingVO = null;
			List<ShowingVO> showingVO1 = new ArrayList<ShowingVO>();

			// �z�LshowingId��X��@showingVO����øˤJ���X
			for (HistoryVO historyVO2 : historyVO) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(historyVO2.getShowingId());
				showingVO1.add(showingVO);
			}

			// �ϥΪ̨S���������B�z
			if (showingVO1.size() == 0) {
				req.setAttribute("message", "�|�L����");
			}

			req.setAttribute("showingVO1", showingVO1);
			String url = "/showing/history.jsp";
			RequestDispatcher success = req.getRequestDispatcher(url);
			success.forward(req, res);

		} catch (Exception e) {
			errorMsgs.add("�d�L���" + e.getMessage());
			RequestDispatcher failur = req.getRequestDispatcher("/showing/showing_search.jsp");
			failur.forward(req, res);
		}

	}

}
