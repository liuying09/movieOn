package com.showing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.realm.JAASCallbackHandler;
import org.json.JSONArray;

import com.movie.model.MovieService;
import com.movie.model.MovieVO;
import com.showing.model.ShowingService;
import com.showing.model.ShowingVO;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;

public class ShowingSelectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");

		try {
			Integer areaNum = new Integer(req.getParameter("areaNum"));

			TheaterService theaterSvc = new TheaterService();
			List<TheaterVO> list = theaterSvc.getByArea(areaNum);

			JSONArray jsonArray = new JSONArray();

			// 把抓出來的值全放進jsonArray裡

			for (TheaterVO theaterVO : list) {
				jsonArray.put(theaterVO.getTheaterId());
				jsonArray.put(theaterVO.getTheaterName());

			}

			// 傳回去
			PrintWriter out = res.getWriter();
			out.println(jsonArray);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/*************************************接收影城欄位回傳電影********************************************/

		try {
			Integer theaterId = new Integer(req.getParameter("theaterId"));

			ShowingService showingSvc = new ShowingService();
			List<ShowingVO> list = showingSvc.getByTheater(theaterId);

			MovieService movieSvc = new MovieService();

			JSONArray jsonArray = new JSONArray();

			for (ShowingVO showingVO : list) {
				jsonArray.put(showingVO.getMovieId());
				jsonArray.put((movieSvc.getOneMovie(showingVO.getMovieId())).getMovieName());

			}

			// 傳回去
			PrintWriter out = res.getWriter();
			out.println(jsonArray);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/*************************************接收影城電影欄位回傳日期********************************************/

		try {
			Integer theaterId = new Integer(req.getParameter("theaterId1"));
			Integer movieId = new Integer(req.getParameter("movieId1"));

			ShowingService showingSvc = new ShowingService();
			List<ShowingVO> list = showingSvc.getByTheaterAndMovie(theaterId, movieId);

			JSONArray jsonArray = new JSONArray();

			for (ShowingVO showingVO : list) {
				jsonArray.put(showingVO.getShowingDate());
			}

			// 傳回去
			PrintWriter out = res.getWriter();
			out.println(jsonArray);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}