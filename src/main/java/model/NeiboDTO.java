package model;

import java.io.Serializable;
import java.sql.Date;

public class NeiboDTO implements Serializable{
	private String name;
	private String yomi;
	private String relationship;
	private String address;
	private String sex;//1が男2が女
	private Date birthday;
	private String note;
	private String photo;
	
	public NeiboDTO() {}
	
	public NeiboDTO(String name,String yomi,String relationship,String address,String sex,Date birthday,String note,String photo) {
		this.name=name;
		this.yomi=yomi;
		this.relationship=relationship;
		this.address=address;
		this.sex=sex;
		this.birthday=birthday;
		this.note=note;
		this.photo=photo;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYomi() {
		return yomi;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
