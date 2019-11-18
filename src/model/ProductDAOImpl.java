package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends DAOBase implements ProductDAO {
	private static ProductDAOImpl instance = new ProductDAOImpl();

	public static ProductDAOImpl getInstance() {
		return instance;
	}

	private ProductDAOImpl() {
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;
	private String sql_insert = "insert into product values (?, ?, ?, ?, ?, ?, ?)";
	private String sql_update = "update product set pname = ?, cost = ?, pnum = ?, jnum = ?, sale = ?, gcode = ? where code = ?";
	private String sql_delete = "delete from product where code = ?";
	private String sql_getone = "select * from product where code= ?";
	private String sql_getList = "select * from product";
	private final String TEST = "TEST/TEST/TEST"; 
	
	
	@Override
	public int create(ProductVO vo) throws SQLException {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, vo.getCode());
			pstmt.setString(2, vo.getPname());
			pstmt.setInt(3, vo.getCost());
			pstmt.setInt(4, vo.getPnum());
			pstmt.setInt(5, vo.getJnum());
			pstmt.setInt(6, vo.getSale());
			pstmt.setString(7, vo.getGcode());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ProductVO readOne(ProductVO vo) throws SQLException {
		ProductVO pdt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_getone);
			pstmt.setString(1, vo.getCode());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pdt = new ProductVO();
				pdt.setCode(rs.getString(1));
				pdt.setPname(rs.getString(2));
				pdt.setCost(rs.getInt(3));
				pdt.setPnum(rs.getInt(4));
				pdt.setJnum(rs.getInt(5));
				pdt.setSale(rs.getInt(6));
				pdt.setGcode(rs.getString(7));
				return pdt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdt;
	}

	@Override
	public List<ProductVO> readList() throws SQLException {
		List<ProductVO> pdts = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_getList);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pdts = new ArrayList<ProductVO>();
				do {
					ProductVO pdt = new ProductVO();
					pdt.setCode(rs.getString(1));
					pdt.setPname(rs.getString(2));
					pdt.setCost(rs.getInt(3));
					pdt.setPnum(rs.getInt(4));
					pdt.setJnum(rs.getInt(5));
					pdt.setSale(rs.getInt(6));
					pdt.setGcode(rs.getString(7));
					pdts.add(pdt);
				} while (rs.next());
				return pdts;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdts;
	}

	@Override
	public int update(ProductVO vo) throws SQLException {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, vo.getPname());
			pstmt.setInt(2, vo.getCost());
			pstmt.setInt(3, vo.getPnum());
			pstmt.setInt(4, vo.getJnum());
			pstmt.setInt(5, vo.getSale());
			pstmt.setString(6, vo.getGcode());
			pstmt.setString(7, vo.getCode());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(ProductVO vo) throws SQLException {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, vo.getCode());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
