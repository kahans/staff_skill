package net.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Dao.AllStaffDao;
import net.Dto.ReligionDto;
import net.Dto.SchoolDto;
import net.Dto.SkillDto;
import net.Dto.StaffDto;
import net.Dto.StaffSkillDto;

/**
 * Servlet implementation class AllStaffController
 */
@WebServlet("/staff/InsertStaffAction")
public class InsertStaffAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 종교,기술,학력들을 검색해서 가입페이지 각항목에 불러오기
		AllStaffDao Dao = new AllStaffDao();
		List<ReligionDto> religionList = Dao.searchReligion();
		List<SchoolDto> schoolList = Dao.searchSchool();
		List<SkillDto> skillList = Dao.searchSkill();

		request.setAttribute("skillList", skillList);
		request.setAttribute("schoolList", schoolList);
		request.setAttribute("religionList", religionList);

		request.getRequestDispatcher("/staff/insertStaff.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		//form에서 받은 값을 대입
		String name = request.getParameter("Name");
		
		String sn01 = request.getParameter("Sn01");
		String sn02 = request.getParameter("Sn02");
		String Sn = sn01 + "-" + sn02;
		System.out.println(Sn + "주민번호");
		
		String graduateday = request.getParameter("Graduateday");
		int schoolNo = (Integer.parseInt(request.getParameter("SchoolNo")));
		int religionNo = (Integer.parseInt(request.getParameter("ReligionNo")));
		
		
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setNo(schoolNo);
		ReligionDto religionDto = new ReligionDto();
		religionDto.setNo(religionNo);
		
		//받은 값을 staff변수 주소값 데이터영역에 대입
		StaffDto staff = new StaffDto();
		staff.setName(name);
		staff.setSn(Sn);
		staff.setGraduateday(graduateday);
		staff.setSchoolNo(schoolDto);
		staff.setReligionNo(religionDto);

		//체크박스에 중복으로 받은 값을 배열로 통해서 실행한다.
		String[] skillStr = (request.getParameterValues("skillcheck"));
		System.out.println("skill[] : " + skillStr);
		int[] skillNo = new int[skillStr.length];
		for(int i =0; i<skillStr.length;i++){
			skillNo[i]=Integer.parseInt(skillStr[i]);
			System.out.println(skillStr[i]+" - skill[]배열에 있나?");			
		}
		
		AllStaffDao dao = new AllStaffDao();
		dao.insertStaff(staff, skillNo);// staff, skillNo 주소값에 있는 데이터영역 dao로 이동하여 쿼리문을 실행한다.

	}
}
