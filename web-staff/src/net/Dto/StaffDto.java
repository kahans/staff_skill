package net.Dto;

public class StaffDto {
	private Integer No;
	private String Name;
	private String Sn;
	private String Graduateday;
	private SchoolDto SchoolNo;
	private ReligionDto ReligionNo;	
	
	public StaffDto() {
		super();
	}
	public StaffDto(Integer no, String name, String sn, String graduateday, SchoolDto schoolNo,
			ReligionDto religionNo) {
		super();
		this.No = no;
		this.Name = name;
		this.Sn = sn;
		this.Graduateday = graduateday;
		this.SchoolNo = schoolNo;
		this.ReligionNo = religionNo;
	}
	public Integer getNo() {
		return No;
	}
	public void setNo(Integer no) {
		No = no;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSn() {
		return Sn;
	}
	public void setSn(String sn) {
		Sn = sn;
	}
	public String getGraduateday() {
		return Graduateday;
	}
	public void setGraduateday(String graduateday) {
		Graduateday = graduateday;
	}
	public SchoolDto getSchoolNo() {
		return SchoolNo;
	}
	public void setSchoolNo(SchoolDto schoolNo) {
		SchoolNo = schoolNo;
	}
	public ReligionDto getReligionNo() {
		return ReligionNo;
	}
	public void setReligionNo(ReligionDto religionNo) {
		ReligionNo = religionNo;
	}
	
}
