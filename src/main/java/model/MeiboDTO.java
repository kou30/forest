/** 
* Filename: MeiboDTO.java
* 
* Description: 
* このクラスは、名簿情報の各フィールドを保持し、
* セッターゲッターを提供します。
* 名簿情報には名前、読み仮名、性別、生年月日、分類、続柄、
* メモ、および画像データが含まれています。
* 
* Author: nagai kosuke 
* Creation Date: 2023-10-04 
* 
* Copyright (C) 2023 Forest All rights reserved. 
* 
* 
*/

package model;

public class MeiboDTO {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int meibo_id;
	private int user_nr;
	private String name;
	private String yomi;
	private int sex;
	private String birthday;
	private String bunrui;
	private int relationship;
	private String memo;

	private byte[] imageData;

	//getter/setter（対象フィールド：name）
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//getter/setter（対象フィールド：age）
	public String getYomi() {
		return yomi;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	//getter/setter（対象フィールド：sex）
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	//getter/setter（対象フィールド：food）
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	//getter/setter（対象フィールド：satisfactionLevel）
	public int getRelationship() {
		return relationship;
	}

	public void setRelationship(int relationship) {
		this.relationship = relationship;
	}

	public String getBunrui() {
		return bunrui;
	}

	public void setBunrui(String bunrui) {
		this.bunrui = bunrui;
	}

	//getter/setter（対象フィールド：message）
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public int getMeibo_id() {
		return meibo_id;
	}

	public void setMeibo_id(int meibo_id) {
		this.meibo_id = meibo_id;
	}

	public int getUser_nr() {
		return user_nr;
	}

	public void setUser_nr(int user_nr) {
		this.user_nr = user_nr;
	}

}
