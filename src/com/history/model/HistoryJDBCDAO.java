package com.history.model;

import java.sql.Connection;
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

public class HistoryJDBCDAO implements HistoryDAO_interface {
	
//	public static final String driver = "com.mysql.cj.jdbc.Driver";
//	public static final String url = "jdbc:mysql://mysql0719.jnlyc.cloudns.cl:3306/MOVIEON?serverTimezone=Asia/Taipei";
//	public static final String userid = "root";
//	public static final String passwd = "Ab3345678";
	
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
			"INSERT INTO MOVIEON.History (userId,showingId) VALUES (?, ?)";
	private static final String UPDATE = 
			"UPDATE MOVIEON.History SET userId=?,showingId=? WHERE historyId=?";
	private static final String DELETE = 
			"DELETE FROM MOVIEON.History WHERE userId=? AND showingId=?";
	private static final String GET_ONE_STMT = 
			"SELECT historyId,userId,showingId FROM MOVIEON.History WHERE historyId=?";
	private static final String GET_ALL_STMT = 
			"SELECT historyId,userId,showingId FROM MOVIEON.History";
	private static final String GET_BY_USER = 
			"SELECT historyId,userId,showingId FROM MOVIEON.History WHERE userId =?";
	
//	static {
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void insert(HistoryVO historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, historyVO.getUserId());
			pstmt.setInt(2, historyVO.getShowingId());
			
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
	public void update(HistoryVO historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, historyVO.getUserId());
			pstmt.setInt(2, historyVO.getShowingId());
			pstmt.setInt(3, historyVO.getHistoryId());
			
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
	public void delete(Integer userId,Integer showingId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, userId);
			pstmt.setInt(2, showingId);
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
	public HistoryVO findByPrimaryKey(Integer historyId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HistoryVO historyVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, historyId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				historyVO = new HistoryVO();
				historyVO.setHistoryId(rs.getInt("historyId"));
				historyVO.setUserId(rs.getInt("userId"));
				historyVO.setShowingId(rs.getInt("showingId"));
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
		
		
		return historyVO;
	}

	@Override
	public List<HistoryVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HistoryVO historyVO = null;
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				historyVO = new HistoryVO();
				historyVO.setHistoryId(rs.getInt("historyId"));
				historyVO.setUserId(rs.getInt("userId"));
				historyVO.setShowingId(rs.getInt("showingId"));
				
				list.add(historyVO);
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
	public List<HistoryVO> getByUderId(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HistoryVO historyVO = null;
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_USER);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				historyVO = new HistoryVO();
				historyVO.setHistoryId(rs.getInt("historyId"));
				historyVO.setUserId(rs.getInt("userId"));
				historyVO.setShowingId(rs.getInt("showingId"));
				list.add(historyVO);
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
