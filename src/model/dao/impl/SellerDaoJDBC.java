package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller sel) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)");
			st.setString(1, "Victor");
			st.setString(2, "victor@victor.com");
			st.setDate(3, (java.sql.Date) new Date("16/06/2002"));
			st.setDouble(4, 1000.00);
			st.setInt(5, 1);

			if (rs.next()) {
				System.out.println("Seller Created!");
			} else {
				System.out.println("Error!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Seller sel) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("UPDATE seller " + "WHERE seller.Id = ? " + "SET Name = ?");
			st.setInt(1, 2);
			st.setString(2, "Jhon");
			if (rs.next()) {
				System.out.println("ID updated!");
			} else {
				System.out.println("ID not found!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE seller.Id = ?");
			st.setInt(1, 2);
			rs = st.executeQuery();
			if (rs.next()) {
				System.out.println("ID deleted!");
			} else {
				System.out.println("ID not found!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "	ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				Seller sel = new Seller();
				sel.setId(rs.getInt("Id"));
				sel.setName(rs.getString("Name"));
				sel.setEmail(rs.getString("Email"));
				sel.setBaseSalary(rs.getDouble("BaseSalary"));
				sel.setBirthDate(rs.getDate("BirthDate"));
				sel.setDepartment(dep);
				return sel;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findAll() {

	}

}
