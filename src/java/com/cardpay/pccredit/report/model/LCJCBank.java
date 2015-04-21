package com.cardpay.pccredit.report.model;

public class LCJCBank {
	private static final long serialVersionUID = 1L;

	private String total;
	private String org_name;
	private String examine_result;
	private String seq_no;
	private String org_id;//userid或bank
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
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
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
}
