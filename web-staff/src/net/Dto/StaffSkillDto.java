package net.Dto;

public class StaffSkillDto {
	private Integer No;
	private StaffDto StaffNo;
	private SkillDto SkillNo;
	
	
	public StaffSkillDto() {
		super();
	}

	public Integer getNo() {
		return No;
	}
	
	public void setNo(Integer no) {
		No = no;
	}
	public StaffDto getStaffNo() {
		return StaffNo;
	}
	public void setStaffNo(StaffDto staffNo) {
		StaffNo = staffNo;
	}
	public SkillDto getSkillNo() {
		return SkillNo;
	}
	public void setSkillNo(SkillDto skillNo) {
		SkillNo = skillNo;
	}
	
	
}
