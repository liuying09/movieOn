package com.showing.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.theater.model.TheaterJDBCDAO;
import com.theater.model.TheaterVO;

public class ShowingJDBCDAO implements ShowingDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MOVIEON");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO MOVIEON.Showing (theaterId,movieId,showingDate,showingTime) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE MOVIEON.Showing SET theaterId=?,movieId=?,showingDate=?,showingTime=? WHERE showingId=?";
	private static final String DELETE = 
			"DELETE FROM MOVIEON.Showing WHERE showingId=?";
	private static final String GET_ONE_STMT = 
			"SELECT showingId,theaterId,movieId,showingDate,showingTime FROM MOVIEON.Showing WHERE showingId=?";
	private static final String GET_ALL_STMT = 
			"SELECT showingId,theaterId,movieId,showingDate,showingTime FROM MOVIEON.Showing";
	private static final String GET_DATE_TIME = 
			"SELECT showingId,theaterId,movieId,showingDate,showingTime FROM MOVIEON.Showing WHERE theaterId=? and movieId=?";
	private static final String GET_BY_THEATER = 
			"SELECT showingId,theaterId,movieId,showingDate,showingTime FROM MOVIEON.Showing WHERE theaterId=?";
	private static final String GET_SHOWING_TIME = 
			"SELECT showingId,theaterId,movieId,showingDate,showingTime FROM MOVIEON.Showing WHERE theaterId=? and movieId=? and showingDate=?";
	private static final String GET_BY_SHOWINGID = 
			"SELECT showingId,theaterId,movieId,showingDate,showingTime FROM MOVIEON.Showing WHERE showingId=?";
	
	@Override
	public void insert(ShowingVO showingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, showingVO.getTheaterId());
			pstmt.setInt(2, showingVO.getMovieId());
			pstmt.setDate(3, showingVO.getShowingDate());
			pstmt.setTime(4, showingVO.getShowingTime());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(ShowingVO showingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, showingVO.getTheaterId());
			pstmt.setInt(2, showingVO.getMovieId());
			pstmt.setDate(3, showingVO.getShowingDate());
			pstmt.setTime(4, showingVO.getShowingTime());
			pstmt.setInt(5, showingVO.getShowingId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void delete(Integer showingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, showingId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public ShowingVO findByPrimaryKey(Integer showingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShowingVO showingVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, showingId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(rs.getInt("showingId"));
				showingVO.setTheaterId(rs.getInt("theaterId"));
				showingVO.setMovieId(rs.getInt("movieId"));
				showingVO.setShowingDate(rs.getDate("showingDate"));
				showingVO.setShowingTime(rs.getTime("showingTime"));
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return showingVO;
	}


	@Override
	public List<ShowingVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShowingVO showingVO = null;
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(rs.getInt("showingId"));
				showingVO.setTheaterId(rs.getInt("theaterId"));
				showingVO.setMovieId(rs.getInt("movieId"));
				showingVO.setShowingDate(rs.getDate("showingDate"));
				showingVO.setShowingTime(rs.getTime("showingTime"));
				
				list.add(showingVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


	@Override
	public List<ShowingVO> getByTheaterAndMovie(Integer theaterId, Integer movieId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShowingVO showingVO = null;
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DATE_TIME);
			pstmt.setInt(1, theaterId);
			pstmt.setInt(2, movieId);
	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(rs.getInt("showingId"));
				showingVO.setTheaterId(rs.getInt("theaterId"));
				showingVO.setMovieId(rs.getInt("movieId"));
				showingVO.setShowingDate(rs.getDate("showingDate"));
				showingVO.setShowingTime(rs.getTime("showingTime"));
				list.add(showingVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
		return list;
	}


	@Override
	public List<ShowingVO> getByTheater(Integer theaterId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		ShowingVO showingVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_THEATER);
			pstmt.setInt(1, theaterId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(rs.getInt("showingId"));
				showingVO.setTheaterId(rs.getInt("theaterId"));
				showingVO.setMovieId(rs.getInt("movieId"));
				showingVO.setShowingDate(rs.getDate("showingDate"));
				showingVO.setShowingTime(rs.getTime("showingTime"));
				list.add(showingVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}


	@Override
	public List<ShowingVO> getShowingTime(Integer theaterId, Integer movieId, Date showingDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		ShowingVO showingVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SHOWING_TIME);
			pstmt.setInt(1, theaterId);
			pstmt.setInt(2, movieId);
			pstmt.setDate(3, showingDate);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(rs.getInt("showingId"));
				showingVO.setTheaterId(rs.getInt("theaterId"));
				showingVO.setMovieId(rs.getInt("movieId"));
				showingVO.setShowingDate(rs.getDate("showingDate"));
				showingVO.setShowingTime(rs.getTime("showingTime"));
				list.add(showingVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		
		return list;
	}


	@Override
	public List<ShowingVO> getByShowingId(Integer showingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		ShowingVO showingVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_SHOWINGID);
			pstmt.setInt(1, showingId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setShowingId(rs.getInt("showingId"));
				showingVO.setTheaterId(rs.getInt("theaterId"));
				showingVO.setMovieId(rs.getInt("movieId"));
				showingVO.setShowingDate(rs.getDate("showingDate"));
				showingVO.setShowingTime(rs.getTime("showingTime"));
				list.add(showingVO);
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
}
