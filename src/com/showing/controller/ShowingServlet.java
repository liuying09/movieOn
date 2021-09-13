package com.showing.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.history.model.HistoryService;
import com.history.model.HistoryVO;
import com.showing.model.ShowingService;
import com.showing.model.ShowingVO;

public class ShowingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/****************************** 新增 ****************************/

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			String successMsg = "";
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer theaterId = new Integer(req.getParameter("theaterId"));
				Integer movieId = new Integer(req.getParameter("movieId"));

				java.sql.Date showingDate = null;
				try {
					showingDate = java.sql.Date.valueOf(req.getParameter("showingDate").trim());
				} catch (IllegalArgumentException e) {
					showingDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}

				java.sql.Time showingTime = null;
				try {
					showingTime = java.sql.Time.valueOf(req.getParameter("showingTime").trim());
				} catch (IllegalArgumentException e) {
					showingTime = new java.sql.Time(1000 * 60 * 60 * 24);
					errorMsgs.add("請選擇時間");
				}

				/****************** 多筆時間設定 ***********************/

				java.sql.Time showingTime2 = null;
				try {
					showingTime2 = java.sql.Time.valueOf(req.getParameter("showingTime2").trim());
				} catch (Exception e) {

				}

				java.sql.Time showingTime3 = null;
				try {
					showingTime3 = java.sql.Time.valueOf(req.getParameter("showingTime3").trim());
				} catch (Exception e) {
				}

				java.sql.Time showingTime4 = null;
				try {
					showingTime4 = java.sql.Time.valueOf(req.getParameter("showingTime4").trim());
				} catch (Exception e) {
				}
				/*******************************************/

				ShowingVO showingVO = new ShowingVO();
				showingVO.setTheaterId(theaterId);
				showingVO.setMovieId(movieId);
				showingVO.setShowingDate(showingDate);
				showingVO.setShowingTime(showingTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("showingVO", showingVO);
					RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_add_time.jsp");
					failurView.forward(req, res);
					return;
				}

				/**************************** 開始新增 **************************************/

				ShowingService showingSvc = new ShowingService();
				showingSvc.addShowing(theaterId, movieId, showingDate, showingTime);

				if (showingTime2 != null) {
					showingSvc.addShowing(theaterId, movieId, showingDate, showingTime2);
				}
				if (showingTime3 != null) {
					showingSvc.addShowing(theaterId, movieId, showingDate, showingTime3);
				}
				if (showingTime4 != null) {
					showingSvc.addShowing(theaterId, movieId, showingDate, showingTime4);
				}

				/************************** 轉回頁面 *********************************/
				String url = "/Backstage/backstage_add_time.jsp";
				successMsg = "新增成功";
				req.setAttribute("successMsg", successMsg);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_add_time.jsp");
				failurView.forward(req, res);
			}
		}

		/**********************************選擇影城與電影查詢**************************************/

		if ("getBy_Movie_Theater".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer theaterId = new Integer(req.getParameter("theaterId"));
				Integer movieId = new Integer(req.getParameter("movieId"));

				/***********************************************************************/
				ShowingService showingSvc = new ShowingService();
				List<ShowingVO> showingVO = showingSvc.getByTheaterAndMovie(theaterId, movieId);
				if (showingVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_showing_select.jsp");
					failurView.forward(req, res);
					return;
				}
				/*************************************************************************/
				req.setAttribute("showingVO", showingVO);
				String url = "/Backstage/backstage_showing_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_showing_select.jsp");
				failurView.forward(req, res);
			}

		}

		/******************************* 選擇一筆更新 *************************************/

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer showingId = new Integer(req.getParameter("showingId"));

				ShowingService showingSvc = new ShowingService();
				ShowingVO showingVO = showingSvc.getOneShowing(showingId);

				req.setAttribute("showingVO", showingVO);
				String url = "/Backstage/backstage_showing_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_showing_view");
				failurView.forward(req, res);
			}
		}

		/***********************************更新*****************************************/

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			String successMsg = "";
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer showingId = new Integer(req.getParameter("showingId"));
				Integer theaterId = new Integer(req.getParameter("theaterId"));
				Integer movieId = new Integer(req.getParameter("movieId"));

				java.sql.Date showingDate = null;
				try {
					showingDate = java.sql.Date.valueOf(req.getParameter("showingDate").trim());
				} catch (IllegalArgumentException e) {
					showingDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}

				java.sql.Time showingTime = null;
				try {
					showingTime = java.sql.Time.valueOf(req.getParameter("showingTime").trim());
				} catch (IllegalArgumentException e) {
					showingTime = new java.sql.Time(1000 * 60 * 60 * 24);
					errorMsgs.add("請選擇時間");
				}

				ShowingVO showingVO = new ShowingVO();
				showingVO.setShowingId(showingId);
				showingVO.setTheaterId(theaterId);
				showingVO.setMovieId(movieId);
				showingVO.setShowingDate(showingDate);
				showingVO.setShowingTime(showingTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("showingVO", showingVO);
					RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_showing_update.jsp");
					failurView.forward(req, res);
					return;
				}

				/****************************************************************************/

				ShowingService showingSvc = new ShowingService();
				showingVO = showingSvc.updaShowing(theaterId, movieId, showingDate, showingTime, showingId);

				successMsg = "修改成功";
				req.setAttribute("successMsg", successMsg);
				req.setAttribute("showingVO", showingVO);
				String url = "/Backstage/backstage_showing_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法更新資料" + e.getMessage());
				RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_showing_update.jsp");
				failurView.forward(req, res);
			}
		}

		/*****************************************一鍵刪除舊的時刻表*******************************************/

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			String successMsg = "";
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				ShowingService showingSvc = new ShowingService();
				List<ShowingVO> list = showingSvc.getAll();

				HistoryService hisyorySvc = new HistoryService();
				List<HistoryVO> history = hisyorySvc.getAll();

				Date today = new Date(System.currentTimeMillis());

				Integer showingId;
				Date showingDate;
				Integer history_showingId;
				Integer userId;

				for (ShowingVO showingVO : list) {
					showingId = showingVO.getShowingId();
					showingDate = showingVO.getShowingDate();
					if (showingDate.before(today)) {

						for (HistoryVO historyVO : history) {
							history_showingId = historyVO.getShowingId();
							userId = historyVO.getUserId();
							if (history_showingId == showingId) {
								hisyorySvc.deleteHistory(userId, history_showingId);
							}
						}
						showingSvc.deleteShowing(showingId);
					}

				}

				successMsg = "刪除成功";
				req.setAttribute("successMsg", successMsg);
				String url = "/Backstage/backstage_showing_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗" + e.getMessage());
				RequestDispatcher failurView = req.getRequestDispatcher("/Backstage/backstage_showing_select.jsp");
				failurView.forward(req, res);
			}
		}
	}
}