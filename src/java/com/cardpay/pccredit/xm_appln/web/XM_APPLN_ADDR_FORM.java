package com.cardpay.pccredit.xm_appln.web;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ADDR;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_JCZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_TJINFO;
import com.wicresoft.jrad.base.web.form.BaseForm;

public class XM_APPLN_ADDR_FORM extends BaseForm {
	public static final long serialVersionUID = 1L;

	//xm_appln_tjinfo
	private String customer_id;
	private String intr_nbr;
	private String intr_name;
	private String intr_cnbr;
	private String intr_recom;
	private String brnchcrlmt;
	private String brnch_intr;
	private String vrf_cntnt;
	private String intr_qc;
	private String intro_code;
	private String intr_rl;
	private String intro_text;
	
	//xm_appln_addr
	private String addr1_l1;
	private String addr1_l2;
	private String addr1_l3;
	private String addr1_l4;
	private String addr1_l5;
	private String addr1_type;
	private String osea_f1;
	private String state_c1;
	private String postcode1;
	private String addr2_l1;
	private String addr2_l2;
	private String addr2_l3;
	private String addr2_l4;
	private String addr2_l5;
	private String addr2_type;
	private String osea_f2;
	private String state_c2;
	private String postcode2;
	private String addr3_l1;
	private String addr3_l2;
	private String addr3_l3;
	private String addr3_l4;
	private String addr3_l5;
	private String addr3_type;
	private String osea_f3;
	private String state_c3;
	private String postcode3;
	private String addr4_l1;
	private String addr4_l2;
	private String addr4_l3;
	private String addr4_l4;
	private String addr4_l5;
	private String addr4_type;
	private String osea_f4;
	private String state_c4;
	private String postcode4;
	
	/*added by nihc 20150706 保存家庭电话、公司电话 begin*/
	private String home_phone;
	private String busi_phone;
	private String extension;
	
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getBusi_phone() {
		return busi_phone;
	}
	public void setBusi_phone(String busi_phone) {
		this.busi_phone = busi_phone;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	//
	private String mail_to;
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getIntr_nbr() {
		return intr_nbr;
	}
	public void setIntr_nbr(String intr_nbr) {
		this.intr_nbr = intr_nbr;
	}
	public String getIntr_name() {
		return intr_name;
	}
	public void setIntr_name(String intr_name) {
		this.intr_name = intr_name;
	}
	public String getIntr_cnbr() {
		return intr_cnbr;
	}
	public void setIntr_cnbr(String intr_cnbr) {
		this.intr_cnbr = intr_cnbr;
	}
	public String getIntr_recom() {
		return intr_recom;
	}
	public void setIntr_recom(String intr_recom) {
		this.intr_recom = intr_recom;
	}
	public String getBrnchcrlmt() {
		return brnchcrlmt;
	}
	public void setBrnchcrlmt(String brnchcrlmt) {
		this.brnchcrlmt = brnchcrlmt;
	}
	public String getBrnch_intr() {
		return brnch_intr;
	}
	public void setBrnch_intr(String brnch_intr) {
		this.brnch_intr = brnch_intr;
	}
	public String getVrf_cntnt() {
		return vrf_cntnt;
	}
	public void setVrf_cntnt(String vrf_cntnt) {
		this.vrf_cntnt = vrf_cntnt;
	}
	public String getIntr_qc() {
		return intr_qc;
	}
	public void setIntr_qc(String intr_qc) {
		this.intr_qc = intr_qc;
	}
	public String getIntro_code() {
		return intro_code;
	}
	public void setIntro_code(String intro_code) {
		this.intro_code = intro_code;
	}
	public String getIntr_rl() {
		return intr_rl;
	}
	public void setIntr_rl(String intr_rl) {
		this.intr_rl = intr_rl;
	}
	public String getIntro_text() {
		return intro_text;
	}
	public void setIntro_text(String intro_text) {
		this.intro_text = intro_text;
	}
	public String getAddr1_l1() {
		return addr1_l1;
	}
	public void setAddr1_l1(String addr1_l1) {
		this.addr1_l1 = addr1_l1;
	}
	public String getAddr1_l2() {
		return addr1_l2;
	}
	public void setAddr1_l2(String addr1_l2) {
		this.addr1_l2 = addr1_l2;
	}
	public String getAddr1_l3() {
		return addr1_l3;
	}
	public void setAddr1_l3(String addr1_l3) {
		this.addr1_l3 = addr1_l3;
	}
	public String getAddr1_l4() {
		return addr1_l4;
	}
	public void setAddr1_l4(String addr1_l4) {
		this.addr1_l4 = addr1_l4;
	}
	public String getAddr1_l5() {
		return addr1_l5;
	}
	public void setAddr1_l5(String addr1_l5) {
		this.addr1_l5 = addr1_l5;
	}
	public String getAddr1_type() {
		return addr1_type;
	}
	public void setAddr1_type(String addr1_type) {
		this.addr1_type = addr1_type;
	}
	public String getOsea_f1() {
		return osea_f1;
	}
	public void setOsea_f1(String osea_f1) {
		this.osea_f1 = osea_f1;
	}
	public String getState_c1() {
		return state_c1;
	}
	public void setState_c1(String state_c1) {
		this.state_c1 = state_c1;
	}
	public String getPostcode1() {
		return postcode1;
	}
	public void setPostcode1(String postcode1) {
		this.postcode1 = postcode1;
	}
	public String getAddr2_l1() {
		return addr2_l1;
	}
	public void setAddr2_l1(String addr2_l1) {
		this.addr2_l1 = addr2_l1;
	}
	public String getAddr2_l2() {
		return addr2_l2;
	}
	public void setAddr2_l2(String addr2_l2) {
		this.addr2_l2 = addr2_l2;
	}
	public String getAddr2_l3() {
		return addr2_l3;
	}
	public void setAddr2_l3(String addr2_l3) {
		this.addr2_l3 = addr2_l3;
	}
	public String getAddr2_l4() {
		return addr2_l4;
	}
	public void setAddr2_l4(String addr2_l4) {
		this.addr2_l4 = addr2_l4;
	}
	public String getAddr2_l5() {
		return addr2_l5;
	}
	public void setAddr2_l5(String addr2_l5) {
		this.addr2_l5 = addr2_l5;
	}
	public String getAddr2_type() {
		return addr2_type;
	}
	public void setAddr2_type(String addr2_type) {
		this.addr2_type = addr2_type;
	}
	public String getOsea_f2() {
		return osea_f2;
	}
	public void setOsea_f2(String osea_f2) {
		this.osea_f2 = osea_f2;
	}
	public String getState_c2() {
		return state_c2;
	}
	public void setState_c2(String state_c2) {
		this.state_c2 = state_c2;
	}
	public String getPostcode2() {
		return postcode2;
	}
	public void setPostcode2(String postcode2) {
		this.postcode2 = postcode2;
	}
	public String getAddr3_l1() {
		return addr3_l1;
	}
	public void setAddr3_l1(String addr3_l1) {
		this.addr3_l1 = addr3_l1;
	}
	public String getAddr3_l2() {
		return addr3_l2;
	}
	public void setAddr3_l2(String addr3_l2) {
		this.addr3_l2 = addr3_l2;
	}
	public String getAddr3_l3() {
		return addr3_l3;
	}
	public void setAddr3_l3(String addr3_l3) {
		this.addr3_l3 = addr3_l3;
	}
	public String getAddr3_l4() {
		return addr3_l4;
	}
	public void setAddr3_l4(String addr3_l4) {
		this.addr3_l4 = addr3_l4;
	}
	public String getAddr3_l5() {
		return addr3_l5;
	}
	public void setAddr3_l5(String addr3_l5) {
		this.addr3_l5 = addr3_l5;
	}
	public String getAddr3_type() {
		return addr3_type;
	}
	public void setAddr3_type(String addr3_type) {
		this.addr3_type = addr3_type;
	}
	public String getOsea_f3() {
		return osea_f3;
	}
	public void setOsea_f3(String osea_f3) {
		this.osea_f3 = osea_f3;
	}
	public String getState_c3() {
		return state_c3;
	}
	public void setState_c3(String state_c3) {
		this.state_c3 = state_c3;
	}
	public String getPostcode3() {
		return postcode3;
	}
	public void setPostcode3(String postcode3) {
		this.postcode3 = postcode3;
	}
	public String getAddr4_l1() {
		return addr4_l1;
	}
	public void setAddr4_l1(String addr4_l1) {
		this.addr4_l1 = addr4_l1;
	}
	public String getAddr4_l2() {
		return addr4_l2;
	}
	public void setAddr4_l2(String addr4_l2) {
		this.addr4_l2 = addr4_l2;
	}
	public String getAddr4_l3() {
		return addr4_l3;
	}
	public void setAddr4_l3(String addr4_l3) {
		this.addr4_l3 = addr4_l3;
	}
	public String getAddr4_l4() {
		return addr4_l4;
	}
	public void setAddr4_l4(String addr4_l4) {
		this.addr4_l4 = addr4_l4;
	}
	public String getAddr4_l5() {
		return addr4_l5;
	}
	public void setAddr4_l5(String addr4_l5) {
		this.addr4_l5 = addr4_l5;
	}
	public String getAddr4_type() {
		return addr4_type;
	}
	public void setAddr4_type(String addr4_type) {
		this.addr4_type = addr4_type;
	}
	public String getOsea_f4() {
		return osea_f4;
	}
	public void setOsea_f4(String osea_f4) {
		this.osea_f4 = osea_f4;
	}
	public String getState_c4() {
		return state_c4;
	}
	public void setState_c4(String state_c4) {
		this.state_c4 = state_c4;
	}
	public String getPostcode4() {
		return postcode4;
	}
	public void setPostcode4(String postcode4) {
		this.postcode4 = postcode4;
	}
	
	//XM_APPLN_TJINFO
	public XM_APPLN_TJINFO createXM_APPLN_TJINFO(String userId){
		XM_APPLN_TJINFO obj = new XM_APPLN_TJINFO();
		obj.setBrnch_intr(brnch_intr);
		obj.setBrnchcrlmt(brnchcrlmt);
		obj.setCustomer_id(customer_id);
		obj.setIntr_cnbr(intr_cnbr);
		obj.setIntr_name(intr_name);
		obj.setIntr_nbr(intr_nbr);
		obj.setIntr_qc(intr_qc);
		obj.setIntr_recom(intr_recom);
		obj.setIntr_rl(intr_rl);
		obj.setIntro_code(intro_code);
		obj.setIntro_text(intro_text);
		obj.setVrf_cntnt(vrf_cntnt);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_ADDR
	public XM_APPLN_ADDR createXM_APPLN_ADDR(String userId){
		XM_APPLN_ADDR obj = new XM_APPLN_ADDR();
		obj.setAddr1_l1(addr1_l1);
		obj.setAddr1_l2(addr1_l2);
		obj.setAddr1_l3(addr1_l3);
		obj.setAddr1_l4(addr1_l4);
		obj.setAddr1_l5(addr1_l5);
		obj.setAddr1_type(addr1_type);
		obj.setAddr2_l1(addr2_l1);
		obj.setAddr2_l2(addr2_l2);
		obj.setAddr2_l3(addr2_l3);
		obj.setAddr2_l4(addr2_l4);
		obj.setAddr2_l5(addr2_l5);
		obj.setAddr2_type(addr2_type);
		obj.setAddr3_l1(addr3_l1);
		obj.setAddr3_l2(addr3_l2);
		obj.setAddr3_l3(addr3_l3);
		obj.setAddr3_l4(addr3_l4);
		obj.setAddr3_l5(addr3_l5);
		obj.setAddr3_type(addr3_type);
		obj.setAddr4_l1(addr4_l1);
		obj.setAddr4_l2(addr4_l2);
		obj.setAddr4_l3(addr4_l3);
		obj.setAddr4_l3(addr4_l3);
		obj.setAddr4_l4(addr4_l4);
		obj.setAddr4_l5(addr4_l5);
		obj.setAddr4_type(addr4_type);
		obj.setCustomer_id(customer_id);
		obj.setOsea_f1(osea_f1);
		obj.setOsea_f2(osea_f2);
		obj.setOsea_f3(osea_f3);
		obj.setOsea_f4(osea_f4);
		obj.setPostcode1(postcode1);
		obj.setPostcode2(postcode2);
		obj.setPostcode3(postcode3);
		obj.setPostcode4(postcode4);
		obj.setState_c1(state_c1);
		obj.setState_c2(state_c2);
		obj.setState_c3(state_c3);
		obj.setState_c4(state_c4);
		obj.setCreatedBy(userId);
		return obj;
	}
	/**
	 
	 */
	public XM_APPLN_JCZL createXM_APPLN_JCZL(String userId){
		XM_APPLN_JCZL obj = new XM_APPLN_JCZL();
		obj.setHome_phone(home_phone);
		obj.setBusi_phone(busi_phone);
		obj.setExtension(extension);
		obj.setModifiedBy(userId);
	    return obj;
	}
}
