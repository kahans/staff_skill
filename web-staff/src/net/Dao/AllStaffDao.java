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

	// staff 리스트 보이기
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
			//staff 리스트 보이기
			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, null);
		}
		return searchStaff;

	}
	//staff 조회
	public List<StaffDto> joinStaff(){
		
		
		return null;
		
	}

	// insert staff 등록 및 staffskill 등록
	public int insertStaff(StaffDto staff, int[] skillNo) {
		int rowCount = 0;
		SchoolDto school;
		school = staff.getSchoolNo();
		ReligionDto religion;
		religion = staff.getReligionNo();

		try {
			// staff 이름, 종교, 학력, 주민번호, 졸업일 등록
			conn = getConnection();
			stmt = conn
					.prepareStatement("INSERT INTO staff(name, sn, graduateday,schoolno,religionno) values(?,?,?,?,?)");
			stmt.setString(1, staff.getName());
			stmt.setString(2, staff.getSn());
			stmt.setString(3, staff.getGraduateday());
			stmt.setInt(4, school.getNo());
			stmt.setInt(5, religion.getNo());
			rowCount = stmt.executeUpdate();

			// staff의 검색으로 sn로 검색하여 no값을 갖고 스태프 스킬에 등록
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
		System.out.println(rowCount + " dao.java 확인 지나 갔는지?");
		return rowCount;

	}

	// 기술
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

	// 학력
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

	// 종교
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

	// static을 어디에 있나?? -
	private static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(driverClassName);
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("드라이버 연결");
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
