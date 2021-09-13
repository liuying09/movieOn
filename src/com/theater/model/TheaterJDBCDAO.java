package com.theater.model;


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

import com.area.model.AreaJDBCDAO;
import com.area.model.AreaVO;
import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

public class TheaterJDBCDAO implements TheaterDAO_interface {

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
			"INSERT INTO MOVIEON.Theater (areaNum,theaterName) VALUES (?, ?)";
	private static final String UPDATE = 
			"UPDATE MOVIEON.Theater SET areaNum=?,theaterName=? WHERE theaterId=?";
	private static final String DELETE = 
			"DELETE FROM MOVIEON.Theater WHERE theaterId=?";
	private static final String GET_ONE_STMT = 
			"SELECT theaterId,areaNum,theaterName FROM MOVIEON.Theater WHERE theaterId=?";
	private static final String GET_ALL_STMT = 
			"SELECT theaterId,areaNum,theaterName FROM MOVIEON.Theater";
	private static final String GET_BY_AREA = 
			"SELECT theaterId,areaNum,theaterName FROM MOVIEON.Theater where areaNum=?";
	

	@Override
	public void insert(TheaterVO theaterVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, theaterVO.getAreaNum());
			pstmt.setString(2, theaterVO.getTheaterName());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(TheaterVO theaterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, theaterVO.getAreaNum());
			pstmt.setString(2, theaterVO.getTheaterName());
			pstmt.setInt(3, theaterVO.getTheaterId());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer theaterId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, theaterId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public TheaterVO findByPrimaryKey(Integer theaterId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TheaterVO theaterVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, theaterId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				theaterVO = new TheaterVO();
				theaterVO.setTheaterId(rs.getInt("theaterId"));
				theaterVO.setAreaNum(rs.getInt("areaNum"));
				theaterVO.setTheaterName(rs.getString("theaterName"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return theaterVO;
		
	}

	@Override
	public List<TheaterVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TheaterVO theaterVO = null;
		List<TheaterVO> list = new ArrayList<TheaterVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				theaterVO = new TheaterVO();
				theaterVO.setTheaterId(rs.getInt("theaterId"));
				theaterVO.setAreaNum(rs.getInt("areaNum"));
				theaterVO.setTheaterName(rs.getString("theaterName"));
				
				list.add(theaterVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<TheaterVO> getByArea(Integer areaNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TheaterVO> list = new ArrayList<TheaterVO>();
		TheaterVO theaterVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_AREA);
			pstmt.setInt(1, areaNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				theaterVO = new TheaterVO();
				theaterVO.setTheaterId(rs.getInt("theaterId"));
				theaterVO.setAreaNum(rs.getInt("areaNum"));
				theaterVO.setTheaterName(rs.getString("theaterName"));
				list.add(theaterVO);
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
