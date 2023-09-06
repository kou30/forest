package model;

public class MeiboDTO {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int	meibo_id;
	private String  userid ;                //id
	private String    name ;                //名前
	private String    yomi ;                 //年齢
	private int       sex ;                 //性別
	private String    birthday ;                //名前
	private String       bunrui ;   //満足度
	private int       relationship ;   //満足度
	private String    memo ;             //メッセージ
	
	
	private byte[] imageData ;                



	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：userId）
	public String getUserId() { return userid; }
	public void setUserId(String userid) { this.userid = userid; }


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：name）
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	//getter/setter（対象フィールド：age）
	public String getYomi() { return yomi; }
	public void setYomi(String yomi) { this.yomi = yomi; }

	//getter/setter（対象フィールド：sex）
	public int getSex() { return sex; }
	public void setSex(int sex) { this.sex = sex; }

	//getter/setter（対象フィールド：food）
	public String getBirthday() { return birthday; }
	public void setBirthday(String birthday) { this.birthday = birthday; }

	//getter/setter（対象フィールド：satisfactionLevel）
	public int getRelationship() { return relationship; }
	public void setRelationship(int relationship) { this.relationship = relationship; }
	
	public String  getBunrui() { return bunrui; }
	public void setBunrui(String bunrui) { this.bunrui = bunrui; }

	//getter/setter（対象フィールド：message）
	public String getMemo() { return memo; }
	public void setMemo(String memo) { this.memo = memo; }

	
	
	
	
	public byte[] getImageData() { return imageData; }
	public void setImageData(byte[] imageData) { this.imageData = imageData; }
	public int getMeibo_id() {
		return meibo_id;
	}
	public void setMeibo_id(int meibo_id) {
		this.meibo_id = meibo_id;
	}

}
