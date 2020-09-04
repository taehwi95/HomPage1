package dto;

import java.sql.Date;

public class BoardDto {

	private int bNo, bHit, bDelete, bGroup, bGroupOrd, bDepth;
	private String mId, bTitle, bContent, bIp, bFilename;
	private Date bLastModify, bRegdate;
	
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public int getbDelete() {
		return bDelete;
	}
	public void setbDelete(int bDelete) {
		this.bDelete = bDelete;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbGroupOrd() {
		return bGroupOrd;
	}
	public void setbGroupOrd(int bGroupOrd) {
		this.bGroupOrd = bGroupOrd;
	}
	public int getbDepth() {
		return bDepth;
	}
	public void setbDepth(int bDepth) {
		this.bDepth = bDepth;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbIp() {
		return bIp;
	}
	public void setbIp(String bIp) {
		this.bIp = bIp;
	}
	public Date getbLastModify() {
		return bLastModify;
	}
	public void setbLastModify(Date bLastModify) {
		this.bLastModify = bLastModify;
	}
	public Date getbRegdate() {
		return bRegdate;
	}
	public void setbRegdate(Date bRegdate) {
		this.bRegdate = bRegdate;
	}
	public String getbFilename() {
		return bFilename;
	}
	public void setbFilename(String bFilename) {
		this.bFilename = bFilename;
	}
	
	
}
