package com.cardpay.pccredit.report.model;

public class LCJC {
	private static final long serialVersionUID = 1L;

	private String total;
	private String display_name;
	private String examine_result;
	private String seq_no;
	private String userid;//userid或bank
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getExamine_result() {
		return examine_result;
	}
	public void setExamine_result(String examine_result) {
		this.examine_result = examine_result;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
