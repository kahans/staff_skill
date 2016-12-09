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

/**
 * Servlet implementation class searchStaffAction
 */
@WebServlet("/staff/SearchStaffAction")
public class SearchStaffAction extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//종교,기술,학력들을 검색해서 가입페이지 각항목에 불러오기
				AllStaffDao Dao= new AllStaffDao();
				List<ReligionDto> religionList= Dao.searchReligion();
				List<SchoolDto> schoolList = Dao.searchSchool();
				List<SkillDto> skillList = Dao.searchSkill();
				
				request.setAttribute("skillList", skillList);
				request.setAttribute("schoolList", schoolList);
				request.setAttribute("religionList", religionList);
				
				//staff 전체 리스트
				List<StaffDto> staffList = Dao.searchStaff();
				request.setAttribute("staffList", staffList);
				
				request.getRequestDispatcher("/staff/searchStaff.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
	}

}
