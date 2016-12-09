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
		// ����,���,�зµ��� �˻��ؼ� ���������� ���׸� �ҷ�����
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
		//form���� ���� ���� ����
		String name = request.getParameter("Name");
		
		String sn01 = request.getParameter("Sn01");
		String sn02 = request.getParameter("Sn02");
		String Sn = sn01 + "-" + sn02;
		System.out.println(Sn + "�ֹι�ȣ");
		
		String graduateday = request.getParameter("Graduateday");
		int schoolNo = (Integer.parseInt(request.getParameter("SchoolNo")));
		int religionNo = (Integer.parseInt(request.getParameter("ReligionNo")));
		
		
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setNo(schoolNo);
		ReligionDto religionDto = new ReligionDto();
		religionDto.setNo(religionNo);
		
		//���� ���� staff���� �ּҰ� �����Ϳ����� ����
		StaffDto staff = new StaffDto();
		staff.setName(name);
		staff.setSn(Sn);
		staff.setGraduateday(graduateday);
		staff.setSchoolNo(schoolDto);
		staff.setReligionNo(religionDto);

		//üũ�ڽ��� �ߺ����� ���� ���� �迭�� ���ؼ� �����Ѵ�.
		String[] skillStr = (request.getParameterValues("skillcheck"));
		System.out.println("skill[] : " + skillStr);
		int[] skillNo = new int[skillStr.length];
		for(int i =0; i<skillStr.length;i++){
			skillNo[i]=Integer.parseInt(skillStr[i]);
			System.out.println(skillStr[i]+" - skill[]�迭�� �ֳ�?");			
		}
		
		AllStaffDao dao = new AllStaffDao();
		dao.insertStaff(staff, skillNo);// staff, skillNo �ּҰ��� �ִ� �����Ϳ��� dao�� �̵��Ͽ� �������� �����Ѵ�.

	}
}
