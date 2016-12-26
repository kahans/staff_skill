package net.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.Dto.ReligionDto;
import net.Dto.SchoolDto;
import net.Dto.SkillDto;
import net.Dto.StaffDto;
import net.Dto.StaffSkillDto;

public class AllStaffDao {
	DataSource ds;
	static Connection conn;
	PreparedStatement stmt;
	ResultSet rs;

	private static final String driverClassName;
	private static final String url;
	private static final String username;
	private static final String password;
	static {
		driverClassName = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/dev22db07?useUnicode=true&characterEncoding=euckr";
		username = "dev22id07";
		password = "dev22pw07";
	}

	// staff ����Ʈ ���̱�
	public List<StaffDto> searchStaff() {
		List<StaffDto> searchStaff= new ArrayList<StaffDto>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement( "select staff.no, sn,graduateday, school.graduate, religion.name,skill.name"
	+"from staff"
	+"join school on staff.schoolno = school.no"
	+"join religion on staff.religionno = religion.no"
	+"join staffskill on staff.no = staffskill.staffno"
	+"join skill on staffskill.skillno = skill.no");
			stmt.executeQuery();
			//staff ����Ʈ ���̱�
			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, null);
		}
		return searchStaff;

	}
	//staff ��ȸ
	public List<StaffDto> joinStaff(){
		
		
		return null;
		
	}

	// insert staff ��� �� staffskill ���
	public int insertStaff(StaffDto staff, int[] skillNo) {
		int rowCount = 0;
		SchoolDto school;
		school = staff.getSchoolNo();
		ReligionDto religion;
		religion = staff.getReligionNo();

		try {
			// staff �̸�, ����, �з�, �ֹι�ȣ, ������ ���
			conn = getConnection();
			stmt = conn
					.prepareStatement("INSERT INTO staff(name, sn, graduateday,schoolno,religionno) values(?,?,?,?,?)");
			stmt.setString(1, staff.getName());
			stmt.setString(2, staff.getSn());
			stmt.setString(3, staff.getGraduateday());
			stmt.setInt(4, school.getNo());
			stmt.setInt(5, religion.getNo());
			rowCount = stmt.executeUpdate();

			// staff�� �˻����� sn�� �˻��Ͽ� no���� ���� ������ ��ų�� ���
			stmt = conn.prepareStatement(
					"INSERT INTO staffskill(staffno,skillno) values((SELECT no FROM staff WHERE sn=?),?)");
			for (int i = 0; i < skillNo.length; i++) {
				stmt.setString(1, staff.getSn());
				stmt.setInt(2, skillNo[i]);
				stmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, null);
		}
		System.out.println(rowCount + " dao.java Ȯ�� ���� ������?");
		return rowCount;

	}

	// ���
	public List<SkillDto> searchSkill() {
		List<SkillDto> skillList = new ArrayList<SkillDto>();
		try {
			conn = getConnection();
			String sql = "select * from skill ORDER BY no asc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				SkillDto sDto = new SkillDto();
				sDto.setNo(rs.getInt("no"));
				sDto.setName(rs.getString("name"));
				skillList.add(sDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, rs);
		}

		return skillList;

	}

	// �з�
	public List<SchoolDto> searchSchool() {
		List<SchoolDto> schoollist = new ArrayList<SchoolDto>();
		try {
			conn = getConnection();
			String sql = "select * from school ORDER BY no asc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				SchoolDto scDto = new SchoolDto();
				scDto.setNo(rs.getInt("no"));
				scDto.setGraduate(rs.getString("graduate"));
				schoollist.add(scDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, rs);
		}

		return schoollist;

	}

	// ����
	public List<ReligionDto> searchReligion() {
		List<ReligionDto> religionList = new ArrayList<ReligionDto>();

		try {
			conn = getConnection();
			String sql = "select * from religion ORDER BY no asc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ReligionDto sDto = new ReligionDto();
				sDto.setNo(rs.getInt("no"));
				sDto.setName(rs.getString("name"));
				religionList.add(sDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, rs);
		}

		return religionList;

	}

	// static�� ��� �ֳ�?? -
	private static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(driverClassName);
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("����̹� ����");
		return conn;
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
