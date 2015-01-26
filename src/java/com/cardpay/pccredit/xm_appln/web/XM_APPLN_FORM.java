package com.cardpay.pccredit.xm_appln.web;

import java.util.ArrayList;
import java.util.List;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DBXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DCSC;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_HKSZ;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KPMX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_QTXYKXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_YWXX;
import com.wicresoft.jrad.base.web.form.BaseForm;

public class XM_APPLN_FORM extends BaseForm {
	public static final long serialVersionUID = 1L;

	//xm_appln
	private String customer_id;
	private String product;
	private String addl_card;
	private String rush_card;
	private String app_source;
	private String int_code;
	private String rush_fee;
	private String business;
	private String emboss_cpy;
	private String busn_name;
	private String base_card;
	private String branch;
	private String brch_name;
	private String stm_code;
	private String app_batch;
	private String ab_branch;
	private String acc_type;
	private String gtoc;
	private String downprod;
	private String cp_no;
	private String cp_nbrmth;
	private String debit_brn;
	private String app_deccd;
	private String app_decreas;
	private String app_decday;
	private String cycle_nbr;
	private String fee_group;
	private String hdwr_sn;
	private String mail_to;
	
	//xm_appln_sqed
	private String crdlmt_req;
	private String crlm_x_req;
	private String mplmt_req;
	private String climit;
	private String cred_lmt;
	
	//xm_appln_kpmx
	private String cardCount;
	private String custr_nbr1;
	private String embname_d1;
	private String emboss_cd1;
	private String pin_reqd1;
	private String sms_yn1;
	private String pin_chk1;
	private String cdesploc1;
	private String cdespmtd1;
	private String courierf1;
	private String cdfrm1;
	private String ppno1;
	private String cred_lmt1;
	private String spec_inst1;
	private String atm1;
	private String tele1;
	private String net1;
	private String phone1;
	
	private String custr_nbr2;
	private String embname_d2;
	private String emboss_cd2;
	private String pin_reqd2;
	private String sms_yn2;
	private String pin_chk2;
	private String cdesploc2;
	private String cdespmtd2;
	private String courierf2;
	private String cdfrm2;
	private String ppno2;
	private String cred_lmt2;
	private String spec_inst2;
	private String atm2;
	private String tele2;
	private String net2;
	private String phone2;
	
	private String custr_nbr3;
	private String embname_d3;
	private String emboss_cd3;
	private String pin_reqd3;
	private String sms_yn3;
	private String pin_chk3;
	private String cdesploc3;
	private String cdespmtd3;
	private String courierf3;
	private String cdfrm3;
	private String ppno3;
	private String cred_lmt3;
	private String spec_inst3;
	private String atm3;
	private String tele3;
	private String net3;
	private String phone3;
	
	private String custr_nbr4;
	private String embname_d4;
	private String emboss_cd4;
	private String pin_reqd4;
	private String sms_yn4;
	private String pin_chk4;
	private String cdesploc4;
	private String cdespmtd4;
	private String courierf4;
	private String cdfrm4;
	private String ppno4;
	private String cred_lmt4;
	private String spec_inst4;
	private String atm4;
	private String tele4;
	private String net4;
	private String phone4;
	
	private String custr_nbr5;
	private String embname_d5;
	private String emboss_cd5;
	private String pin_reqd5;
	private String sms_yn5;
	private String pin_chk5;
	private String cdesploc5;
	private String cdespmtd5;
	private String courierf5;
	private String cdfrm5;
	private String ppno5;
	private String cred_lmt5;
	private String spec_inst5;
	private String atm5;
	private String tele5;
	private String net5;
	private String phone5;
	
	private String custr_nbr6;
	private String embname_d6;
	private String emboss_cd6;
	private String pin_reqd6;
	private String sms_yn6;
	private String pin_chk6;
	private String cdesploc6;
	private String cdespmtd6;
	private String courierf6;
	private String cdfrm6;
	private String ppno6;
	private String cred_lmt6;
	private String spec_inst6;
	private String atm6;
	private String tele6;
	private String net6;
	private String phone6;
	
	private String custr_nbr7;
	private String embname_d7;
	private String emboss_cd7;
	private String pin_reqd7;
	private String sms_yn7;
	private String pin_chk7;
	private String cdesploc7;
	private String cdespmtd7;
	private String courierf7;
	private String cdfrm7;
	private String ppno7;
	private String cred_lmt7;
	private String spec_inst7;
	private String atm7;
	private String tele7;
	private String net7;
	private String phone7;
	
	private String custr_nbr8;
	private String embname_d8;
	private String emboss_cd8;
	private String pin_reqd8;
	private String sms_yn8;
	private String pin_chk8;
	private String cdesploc8;
	private String cdespmtd8;
	private String courierf8;
	private String cdfrm8;
	private String ppno8;
	private String cred_lmt8;
	private String spec_inst8;
	private String atm8;
	private String tele8;
	private String net8;
	private String phone8;
	
	private String custr_nbr9;
	private String embname_d9;
	private String emboss_cd9;
	private String pin_reqd9;
	private String sms_yn9;
	private String pin_chk9;
	private String cdesploc9;
	private String cdespmtd9;
	private String courierf9;
	private String cdfrm9;
	private String ppno9;
	private String cred_lmt9;
	private String spec_inst9;
	private String atm9;
	private String tele9;
	private String net9;
	private String phone9;
	
	//xm_appln_hksz
	private String repay_code;
	private String bankacct1;
	private String repay_amt;
	private String repay_pct;
	private String repay_day;
	private String repay_codx;
	private String bankacct2;
	private String repay_amtx;
	private String repay_pctx;
	private String exch_flag;
	private String bankacct3;
	private String exch_code;
	
	//xm_appln_dbxx
	private String guarn_id;
	private String guarn_bank;
	private String guarn_ref;
	private String guarn_code;
	private String guarn_ref2;
	
	//xm_appln_qtxykxx
	private String xrefinac1;
	private String xrefcode1;
	private String xrefcrlmt1;
	private String xrefinac2;
	private String xrefcode2;
	
	//xm_appln_ywxx
	private String app_giftno;
	private String app_cross;
	
	//xm_appln_dcsc
	private String aval_nbr;
	private String aval_name;
	private String achk_nbr;
	private String achk_name;
	private String adec_nbr;
	private String adec_name;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getAddl_card() {
		return addl_card;
	}
	public void setAddl_card(String addl_card) {
		this.addl_card = addl_card;
	}
	public String getRush_card() {
		return rush_card;
	}
	public void setRush_card(String rush_card) {
		this.rush_card = rush_card;
	}
	public String getApp_source() {
		return app_source;
	}
	public void setApp_source(String app_source) {
		this.app_source = app_source;
	}
	public String getInt_code() {
		return int_code;
	}
	public void setInt_code(String int_code) {
		this.int_code = int_code;
	}
	public String getRush_fee() {
		return rush_fee;
	}
	public void setRush_fee(String rush_fee) {
		this.rush_fee = rush_fee;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getEmboss_cpy() {
		return emboss_cpy;
	}
	public void setEmboss_cpy(String emboss_cpy) {
		this.emboss_cpy = emboss_cpy;
	}
	public String getBusn_name() {
		return busn_name;
	}
	public void setBusn_name(String busn_name) {
		this.busn_name = busn_name;
	}
	public String getBase_card() {
		return base_card;
	}
	public void setBase_card(String base_card) {
		this.base_card = base_card;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBrch_name() {
		return brch_name;
	}
	public void setBrch_name(String brch_name) {
		this.brch_name = brch_name;
	}
	public String getStm_code() {
		return stm_code;
	}
	public void setStm_code(String stm_code) {
		this.stm_code = stm_code;
	}
	public String getApp_batch() {
		return app_batch;
	}
	public void setApp_batch(String app_batch) {
		this.app_batch = app_batch;
	}
	public String getAb_branch() {
		return ab_branch;
	}
	public void setAb_branch(String ab_branch) {
		this.ab_branch = ab_branch;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getGtoc() {
		return gtoc;
	}
	public void setGtoc(String gtoc) {
		this.gtoc = gtoc;
	}
	public String getDownprod() {
		return downprod;
	}
	public void setDownprod(String downprod) {
		this.downprod = downprod;
	}
	public String getCp_no() {
		return cp_no;
	}
	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}
	public String getCp_nbrmth() {
		return cp_nbrmth;
	}
	public void setCp_nbrmth(String cp_nbrmth) {
		this.cp_nbrmth = cp_nbrmth;
	}
	public String getDebit_brn() {
		return debit_brn;
	}
	public void setDebit_brn(String debit_brn) {
		this.debit_brn = debit_brn;
	}
	public String getApp_deccd() {
		return app_deccd;
	}
	public void setApp_deccd(String app_deccd) {
		this.app_deccd = app_deccd;
	}
	public String getApp_decreas() {
		return app_decreas;
	}
	public void setApp_decreas(String app_decreas) {
		this.app_decreas = app_decreas;
	}
	public String getApp_decday() {
		return app_decday;
	}
	public void setApp_decday(String app_decday) {
		this.app_decday = app_decday;
	}
	public String getCycle_nbr() {
		return cycle_nbr;
	}
	public void setCycle_nbr(String cycle_nbr) {
		this.cycle_nbr = cycle_nbr;
	}
	public String getFee_group() {
		return fee_group;
	}
	public void setFee_group(String fee_group) {
		this.fee_group = fee_group;
	}
	public String getHdwr_sn() {
		return hdwr_sn;
	}
	public void setHdwr_sn(String hdwr_sn) {
		this.hdwr_sn = hdwr_sn;
	}
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public String getCrdlmt_req() {
		return crdlmt_req;
	}
	public void setCrdlmt_req(String crdlmt_req) {
		this.crdlmt_req = crdlmt_req;
	}
	public String getCrlm_x_req() {
		return crlm_x_req;
	}
	public void setCrlm_x_req(String crlm_x_req) {
		this.crlm_x_req = crlm_x_req;
	}
	public String getMplmt_req() {
		return mplmt_req;
	}
	public void setMplmt_req(String mplmt_req) {
		this.mplmt_req = mplmt_req;
	}
	public String getClimit() {
		return climit;
	}
	public void setClimit(String climit) {
		this.climit = climit;
	}
	public String getCred_lmt() {
		return cred_lmt;
	}
	public void setCred_lmt(String cred_lmt) {
		this.cred_lmt = cred_lmt;
	}
	public String getCardCount() {
		return cardCount;
	}
	public void setCardCount(String cardCount) {
		this.cardCount = cardCount;
	}
	public String getCustr_nbr1() {
		return custr_nbr1;
	}
	public void setCustr_nbr1(String custr_nbr1) {
		this.custr_nbr1 = custr_nbr1;
	}
	public String getEmbname_d1() {
		return embname_d1;
	}
	public void setEmbname_d1(String embname_d1) {
		this.embname_d1 = embname_d1;
	}
	public String getEmboss_cd1() {
		return emboss_cd1;
	}
	public void setEmboss_cd1(String emboss_cd1) {
		this.emboss_cd1 = emboss_cd1;
	}
	public String getPin_reqd1() {
		return pin_reqd1;
	}
	public void setPin_reqd1(String pin_reqd1) {
		this.pin_reqd1 = pin_reqd1;
	}
	public String getSms_yn1() {
		return sms_yn1;
	}
	public void setSms_yn1(String sms_yn1) {
		this.sms_yn1 = sms_yn1;
	}
	public String getPin_chk1() {
		return pin_chk1;
	}
	public void setPin_chk1(String pin_chk1) {
		this.pin_chk1 = pin_chk1;
	}
	public String getCdesploc1() {
		return cdesploc1;
	}
	public void setCdesploc1(String cdesploc1) {
		this.cdesploc1 = cdesploc1;
	}
	public String getCdespmtd1() {
		return cdespmtd1;
	}
	public void setCdespmtd1(String cdespmtd1) {
		this.cdespmtd1 = cdespmtd1;
	}
	public String getCourierf1() {
		return courierf1;
	}
	public void setCourierf1(String courierf1) {
		this.courierf1 = courierf1;
	}
	public String getCdfrm1() {
		return cdfrm1;
	}
	public void setCdfrm1(String cdfrm1) {
		this.cdfrm1 = cdfrm1;
	}
	public String getPpno1() {
		return ppno1;
	}
	public void setPpno1(String ppno1) {
		this.ppno1 = ppno1;
	}
	public String getCred_lmt1() {
		return cred_lmt1;
	}
	public void setCred_lmt1(String cred_lmt1) {
		this.cred_lmt1 = cred_lmt1;
	}
	public String getSpec_inst1() {
		return spec_inst1;
	}
	public void setSpec_inst1(String spec_inst1) {
		this.spec_inst1 = spec_inst1;
	}
	public String getAtm1() {
		return atm1;
	}
	public void setAtm1(String atm1) {
		this.atm1 = atm1;
	}
	public String getTele1() {
		return tele1;
	}
	public void setTele1(String tele1) {
		this.tele1 = tele1;
	}
	public String getNet1() {
		return net1;
	}
	public void setNet1(String net1) {
		this.net1 = net1;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getCustr_nbr2() {
		return custr_nbr2;
	}
	public void setCustr_nbr2(String custr_nbr2) {
		this.custr_nbr2 = custr_nbr2;
	}
	public String getEmbname_d2() {
		return embname_d2;
	}
	public void setEmbname_d2(String embname_d2) {
		this.embname_d2 = embname_d2;
	}
	public String getEmboss_cd2() {
		return emboss_cd2;
	}
	public void setEmboss_cd2(String emboss_cd2) {
		this.emboss_cd2 = emboss_cd2;
	}
	public String getPin_reqd2() {
		return pin_reqd2;
	}
	public void setPin_reqd2(String pin_reqd2) {
		this.pin_reqd2 = pin_reqd2;
	}
	public String getSms_yn2() {
		return sms_yn2;
	}
	public void setSms_yn2(String sms_yn2) {
		this.sms_yn2 = sms_yn2;
	}
	public String getPin_chk2() {
		return pin_chk2;
	}
	public void setPin_chk2(String pin_chk2) {
		this.pin_chk2 = pin_chk2;
	}
	public String getCdesploc2() {
		return cdesploc2;
	}
	public void setCdesploc2(String cdesploc2) {
		this.cdesploc2 = cdesploc2;
	}
	public String getCdespmtd2() {
		return cdespmtd2;
	}
	public void setCdespmtd2(String cdespmtd2) {
		this.cdespmtd2 = cdespmtd2;
	}
	public String getCourierf2() {
		return courierf2;
	}
	public void setCourierf2(String courierf2) {
		this.courierf2 = courierf2;
	}
	public String getCdfrm2() {
		return cdfrm2;
	}
	public void setCdfrm2(String cdfrm2) {
		this.cdfrm2 = cdfrm2;
	}
	public String getPpno2() {
		return ppno2;
	}
	public void setPpno2(String ppno2) {
		this.ppno2 = ppno2;
	}
	public String getCred_lmt2() {
		return cred_lmt2;
	}
	public void setCred_lmt2(String cred_lmt2) {
		this.cred_lmt2 = cred_lmt2;
	}
	public String getSpec_inst2() {
		return spec_inst2;
	}
	public void setSpec_inst2(String spec_inst2) {
		this.spec_inst2 = spec_inst2;
	}
	public String getAtm2() {
		return atm2;
	}
	public void setAtm2(String atm2) {
		this.atm2 = atm2;
	}
	public String getTele2() {
		return tele2;
	}
	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}
	public String getNet2() {
		return net2;
	}
	public void setNet2(String net2) {
		this.net2 = net2;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getCustr_nbr3() {
		return custr_nbr3;
	}
	public void setCustr_nbr3(String custr_nbr3) {
		this.custr_nbr3 = custr_nbr3;
	}
	public String getEmbname_d3() {
		return embname_d3;
	}
	public void setEmbname_d3(String embname_d3) {
		this.embname_d3 = embname_d3;
	}
	public String getEmboss_cd3() {
		return emboss_cd3;
	}
	public void setEmboss_cd3(String emboss_cd3) {
		this.emboss_cd3 = emboss_cd3;
	}
	public String getPin_reqd3() {
		return pin_reqd3;
	}
	public void setPin_reqd3(String pin_reqd3) {
		this.pin_reqd3 = pin_reqd3;
	}
	public String getSms_yn3() {
		return sms_yn3;
	}
	public void setSms_yn3(String sms_yn3) {
		this.sms_yn3 = sms_yn3;
	}
	public String getPin_chk3() {
		return pin_chk3;
	}
	public void setPin_chk3(String pin_chk3) {
		this.pin_chk3 = pin_chk3;
	}
	public String getCdesploc3() {
		return cdesploc3;
	}
	public void setCdesploc3(String cdesploc3) {
		this.cdesploc3 = cdesploc3;
	}
	public String getCdespmtd3() {
		return cdespmtd3;
	}
	public void setCdespmtd3(String cdespmtd3) {
		this.cdespmtd3 = cdespmtd3;
	}
	public String getCourierf3() {
		return courierf3;
	}
	public void setCourierf3(String courierf3) {
		this.courierf3 = courierf3;
	}
	public String getCdfrm3() {
		return cdfrm3;
	}
	public void setCdfrm3(String cdfrm3) {
		this.cdfrm3 = cdfrm3;
	}
	public String getPpno3() {
		return ppno3;
	}
	public void setPpno3(String ppno3) {
		this.ppno3 = ppno3;
	}
	public String getCred_lmt3() {
		return cred_lmt3;
	}
	public void setCred_lmt3(String cred_lmt3) {
		this.cred_lmt3 = cred_lmt3;
	}
	public String getSpec_inst3() {
		return spec_inst3;
	}
	public void setSpec_inst3(String spec_inst3) {
		this.spec_inst3 = spec_inst3;
	}
	public String getAtm3() {
		return atm3;
	}
	public void setAtm3(String atm3) {
		this.atm3 = atm3;
	}
	public String getTele3() {
		return tele3;
	}
	public void setTele3(String tele3) {
		this.tele3 = tele3;
	}
	public String getNet3() {
		return net3;
	}
	public void setNet3(String net3) {
		this.net3 = net3;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getCustr_nbr4() {
		return custr_nbr4;
	}
	public void setCustr_nbr4(String custr_nbr4) {
		this.custr_nbr4 = custr_nbr4;
	}
	public String getEmbname_d4() {
		return embname_d4;
	}
	public void setEmbname_d4(String embname_d4) {
		this.embname_d4 = embname_d4;
	}
	public String getEmboss_cd4() {
		return emboss_cd4;
	}
	public void setEmboss_cd4(String emboss_cd4) {
		this.emboss_cd4 = emboss_cd4;
	}
	public String getPin_reqd4() {
		return pin_reqd4;
	}
	public void setPin_reqd4(String pin_reqd4) {
		this.pin_reqd4 = pin_reqd4;
	}
	public String getSms_yn4() {
		return sms_yn4;
	}
	public void setSms_yn4(String sms_yn4) {
		this.sms_yn4 = sms_yn4;
	}
	public String getPin_chk4() {
		return pin_chk4;
	}
	public void setPin_chk4(String pin_chk4) {
		this.pin_chk4 = pin_chk4;
	}
	public String getCdesploc4() {
		return cdesploc4;
	}
	public void setCdesploc4(String cdesploc4) {
		this.cdesploc4 = cdesploc4;
	}
	public String getCdespmtd4() {
		return cdespmtd4;
	}
	public void setCdespmtd4(String cdespmtd4) {
		this.cdespmtd4 = cdespmtd4;
	}
	public String getCourierf4() {
		return courierf4;
	}
	public void setCourierf4(String courierf4) {
		this.courierf4 = courierf4;
	}
	public String getCdfrm4() {
		return cdfrm4;
	}
	public void setCdfrm4(String cdfrm4) {
		this.cdfrm4 = cdfrm4;
	}
	public String getPpno4() {
		return ppno4;
	}
	public void setPpno4(String ppno4) {
		this.ppno4 = ppno4;
	}
	public String getCred_lmt4() {
		return cred_lmt4;
	}
	public void setCred_lmt4(String cred_lmt4) {
		this.cred_lmt4 = cred_lmt4;
	}
	public String getSpec_inst4() {
		return spec_inst4;
	}
	public void setSpec_inst4(String spec_inst4) {
		this.spec_inst4 = spec_inst4;
	}
	public String getAtm4() {
		return atm4;
	}
	public void setAtm4(String atm4) {
		this.atm4 = atm4;
	}
	public String getTele4() {
		return tele4;
	}
	public void setTele4(String tele4) {
		this.tele4 = tele4;
	}
	public String getNet4() {
		return net4;
	}
	public void setNet4(String net4) {
		this.net4 = net4;
	}
	public String getPhone4() {
		return phone4;
	}
	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}
	public String getCustr_nbr5() {
		return custr_nbr5;
	}
	public void setCustr_nbr5(String custr_nbr5) {
		this.custr_nbr5 = custr_nbr5;
	}
	public String getEmbname_d5() {
		return embname_d5;
	}
	public void setEmbname_d5(String embname_d5) {
		this.embname_d5 = embname_d5;
	}
	public String getEmboss_cd5() {
		return emboss_cd5;
	}
	public void setEmboss_cd5(String emboss_cd5) {
		this.emboss_cd5 = emboss_cd5;
	}
	public String getPin_reqd5() {
		return pin_reqd5;
	}
	public void setPin_reqd5(String pin_reqd5) {
		this.pin_reqd5 = pin_reqd5;
	}
	public String getSms_yn5() {
		return sms_yn5;
	}
	public void setSms_yn5(String sms_yn5) {
		this.sms_yn5 = sms_yn5;
	}
	public String getPin_chk5() {
		return pin_chk5;
	}
	public void setPin_chk5(String pin_chk5) {
		this.pin_chk5 = pin_chk5;
	}
	public String getCdesploc5() {
		return cdesploc5;
	}
	public void setCdesploc5(String cdesploc5) {
		this.cdesploc5 = cdesploc5;
	}
	public String getCdespmtd5() {
		return cdespmtd5;
	}
	public void setCdespmtd5(String cdespmtd5) {
		this.cdespmtd5 = cdespmtd5;
	}
	public String getCourierf5() {
		return courierf5;
	}
	public void setCourierf5(String courierf5) {
		this.courierf5 = courierf5;
	}
	public String getCdfrm5() {
		return cdfrm5;
	}
	public void setCdfrm5(String cdfrm5) {
		this.cdfrm5 = cdfrm5;
	}
	public String getPpno5() {
		return ppno5;
	}
	public void setPpno5(String ppno5) {
		this.ppno5 = ppno5;
	}
	public String getCred_lmt5() {
		return cred_lmt5;
	}
	public void setCred_lmt5(String cred_lmt5) {
		this.cred_lmt5 = cred_lmt5;
	}
	public String getSpec_inst5() {
		return spec_inst5;
	}
	public void setSpec_inst5(String spec_inst5) {
		this.spec_inst5 = spec_inst5;
	}
	public String getAtm5() {
		return atm5;
	}
	public void setAtm5(String atm5) {
		this.atm5 = atm5;
	}
	public String getTele5() {
		return tele5;
	}
	public void setTele5(String tele5) {
		this.tele5 = tele5;
	}
	public String getNet5() {
		return net5;
	}
	public void setNet5(String net5) {
		this.net5 = net5;
	}
	public String getPhone5() {
		return phone5;
	}
	public void setPhone5(String phone5) {
		this.phone5 = phone5;
	}
	public String getCustr_nbr6() {
		return custr_nbr6;
	}
	public void setCustr_nbr6(String custr_nbr6) {
		this.custr_nbr6 = custr_nbr6;
	}
	public String getEmbname_d6() {
		return embname_d6;
	}
	public void setEmbname_d6(String embname_d6) {
		this.embname_d6 = embname_d6;
	}
	public String getEmboss_cd6() {
		return emboss_cd6;
	}
	public void setEmboss_cd6(String emboss_cd6) {
		this.emboss_cd6 = emboss_cd6;
	}
	public String getPin_reqd6() {
		return pin_reqd6;
	}
	public void setPin_reqd6(String pin_reqd6) {
		this.pin_reqd6 = pin_reqd6;
	}
	public String getSms_yn6() {
		return sms_yn6;
	}
	public void setSms_yn6(String sms_yn6) {
		this.sms_yn6 = sms_yn6;
	}
	public String getPin_chk6() {
		return pin_chk6;
	}
	public void setPin_chk6(String pin_chk6) {
		this.pin_chk6 = pin_chk6;
	}
	public String getCdesploc6() {
		return cdesploc6;
	}
	public void setCdesploc6(String cdesploc6) {
		this.cdesploc6 = cdesploc6;
	}
	public String getCdespmtd6() {
		return cdespmtd6;
	}
	public void setCdespmtd6(String cdespmtd6) {
		this.cdespmtd6 = cdespmtd6;
	}
	public String getCourierf6() {
		return courierf6;
	}
	public void setCourierf6(String courierf6) {
		this.courierf6 = courierf6;
	}
	public String getCdfrm6() {
		return cdfrm6;
	}
	public void setCdfrm6(String cdfrm6) {
		this.cdfrm6 = cdfrm6;
	}
	public String getPpno6() {
		return ppno6;
	}
	public void setPpno6(String ppno6) {
		this.ppno6 = ppno6;
	}
	public String getCred_lmt6() {
		return cred_lmt6;
	}
	public void setCred_lmt6(String cred_lmt6) {
		this.cred_lmt6 = cred_lmt6;
	}
	public String getSpec_inst6() {
		return spec_inst6;
	}
	public void setSpec_inst6(String spec_inst6) {
		this.spec_inst6 = spec_inst6;
	}
	public String getAtm6() {
		return atm6;
	}
	public void setAtm6(String atm6) {
		this.atm6 = atm6;
	}
	public String getTele6() {
		return tele6;
	}
	public void setTele6(String tele6) {
		this.tele6 = tele6;
	}
	public String getNet6() {
		return net6;
	}
	public void setNet6(String net6) {
		this.net6 = net6;
	}
	public String getPhone6() {
		return phone6;
	}
	public void setPhone6(String phone6) {
		this.phone6 = phone6;
	}
	public String getCustr_nbr7() {
		return custr_nbr7;
	}
	public void setCustr_nbr7(String custr_nbr7) {
		this.custr_nbr7 = custr_nbr7;
	}
	public String getEmbname_d7() {
		return embname_d7;
	}
	public void setEmbname_d7(String embname_d7) {
		this.embname_d7 = embname_d7;
	}
	public String getEmboss_cd7() {
		return emboss_cd7;
	}
	public void setEmboss_cd7(String emboss_cd7) {
		this.emboss_cd7 = emboss_cd7;
	}
	public String getPin_reqd7() {
		return pin_reqd7;
	}
	public void setPin_reqd7(String pin_reqd7) {
		this.pin_reqd7 = pin_reqd7;
	}
	public String getSms_yn7() {
		return sms_yn7;
	}
	public void setSms_yn7(String sms_yn7) {
		this.sms_yn7 = sms_yn7;
	}
	public String getPin_chk7() {
		return pin_chk7;
	}
	public void setPin_chk7(String pin_chk7) {
		this.pin_chk7 = pin_chk7;
	}
	public String getCdesploc7() {
		return cdesploc7;
	}
	public void setCdesploc7(String cdesploc7) {
		this.cdesploc7 = cdesploc7;
	}
	public String getCdespmtd7() {
		return cdespmtd7;
	}
	public void setCdespmtd7(String cdespmtd7) {
		this.cdespmtd7 = cdespmtd7;
	}
	public String getCourierf7() {
		return courierf7;
	}
	public void setCourierf7(String courierf7) {
		this.courierf7 = courierf7;
	}
	public String getCdfrm7() {
		return cdfrm7;
	}
	public void setCdfrm7(String cdfrm7) {
		this.cdfrm7 = cdfrm7;
	}
	public String getPpno7() {
		return ppno7;
	}
	public void setPpno7(String ppno7) {
		this.ppno7 = ppno7;
	}
	public String getCred_lmt7() {
		return cred_lmt7;
	}
	public void setCred_lmt7(String cred_lmt7) {
		this.cred_lmt7 = cred_lmt7;
	}
	public String getSpec_inst7() {
		return spec_inst7;
	}
	public void setSpec_inst7(String spec_inst7) {
		this.spec_inst7 = spec_inst7;
	}
	public String getAtm7() {
		return atm7;
	}
	public void setAtm7(String atm7) {
		this.atm7 = atm7;
	}
	public String getTele7() {
		return tele7;
	}
	public void setTele7(String tele7) {
		this.tele7 = tele7;
	}
	public String getNet7() {
		return net7;
	}
	public void setNet7(String net7) {
		this.net7 = net7;
	}
	public String getPhone7() {
		return phone7;
	}
	public void setPhone7(String phone7) {
		this.phone7 = phone7;
	}
	public String getCustr_nbr8() {
		return custr_nbr8;
	}
	public void setCustr_nbr8(String custr_nbr8) {
		this.custr_nbr8 = custr_nbr8;
	}
	public String getEmbname_d8() {
		return embname_d8;
	}
	public void setEmbname_d8(String embname_d8) {
		this.embname_d8 = embname_d8;
	}
	public String getEmboss_cd8() {
		return emboss_cd8;
	}
	public void setEmboss_cd8(String emboss_cd8) {
		this.emboss_cd8 = emboss_cd8;
	}
	public String getPin_reqd8() {
		return pin_reqd8;
	}
	public void setPin_reqd8(String pin_reqd8) {
		this.pin_reqd8 = pin_reqd8;
	}
	public String getSms_yn8() {
		return sms_yn8;
	}
	public void setSms_yn8(String sms_yn8) {
		this.sms_yn8 = sms_yn8;
	}
	public String getPin_chk8() {
		return pin_chk8;
	}
	public void setPin_chk8(String pin_chk8) {
		this.pin_chk8 = pin_chk8;
	}
	public String getCdesploc8() {
		return cdesploc8;
	}
	public void setCdesploc8(String cdesploc8) {
		this.cdesploc8 = cdesploc8;
	}
	public String getCdespmtd8() {
		return cdespmtd8;
	}
	public void setCdespmtd8(String cdespmtd8) {
		this.cdespmtd8 = cdespmtd8;
	}
	public String getCourierf8() {
		return courierf8;
	}
	public void setCourierf8(String courierf8) {
		this.courierf8 = courierf8;
	}
	public String getCdfrm8() {
		return cdfrm8;
	}
	public void setCdfrm8(String cdfrm8) {
		this.cdfrm8 = cdfrm8;
	}
	public String getPpno8() {
		return ppno8;
	}
	public void setPpno8(String ppno8) {
		this.ppno8 = ppno8;
	}
	public String getCred_lmt8() {
		return cred_lmt8;
	}
	public void setCred_lmt8(String cred_lmt8) {
		this.cred_lmt8 = cred_lmt8;
	}
	public String getSpec_inst8() {
		return spec_inst8;
	}
	public void setSpec_inst8(String spec_inst8) {
		this.spec_inst8 = spec_inst8;
	}
	public String getAtm8() {
		return atm8;
	}
	public void setAtm8(String atm8) {
		this.atm8 = atm8;
	}
	public String getTele8() {
		return tele8;
	}
	public void setTele8(String tele8) {
		this.tele8 = tele8;
	}
	public String getNet8() {
		return net8;
	}
	public void setNet8(String net8) {
		this.net8 = net8;
	}
	public String getPhone8() {
		return phone8;
	}
	public void setPhone8(String phone8) {
		this.phone8 = phone8;
	}
	public String getCustr_nbr9() {
		return custr_nbr9;
	}
	public void setCustr_nbr9(String custr_nbr9) {
		this.custr_nbr9 = custr_nbr9;
	}
	public String getEmbname_d9() {
		return embname_d9;
	}
	public void setEmbname_d9(String embname_d9) {
		this.embname_d9 = embname_d9;
	}
	public String getEmboss_cd9() {
		return emboss_cd9;
	}
	public void setEmboss_cd9(String emboss_cd9) {
		this.emboss_cd9 = emboss_cd9;
	}
	public String getPin_reqd9() {
		return pin_reqd9;
	}
	public void setPin_reqd9(String pin_reqd9) {
		this.pin_reqd9 = pin_reqd9;
	}
	public String getSms_yn9() {
		return sms_yn9;
	}
	public void setSms_yn9(String sms_yn9) {
		this.sms_yn9 = sms_yn9;
	}
	public String getPin_chk9() {
		return pin_chk9;
	}
	public void setPin_chk9(String pin_chk9) {
		this.pin_chk9 = pin_chk9;
	}
	public String getCdesploc9() {
		return cdesploc9;
	}
	public void setCdesploc9(String cdesploc9) {
		this.cdesploc9 = cdesploc9;
	}
	public String getCdespmtd9() {
		return cdespmtd9;
	}
	public void setCdespmtd9(String cdespmtd9) {
		this.cdespmtd9 = cdespmtd9;
	}
	public String getCourierf9() {
		return courierf9;
	}
	public void setCourierf9(String courierf9) {
		this.courierf9 = courierf9;
	}
	public String getCdfrm9() {
		return cdfrm9;
	}
	public void setCdfrm9(String cdfrm9) {
		this.cdfrm9 = cdfrm9;
	}
	public String getPpno9() {
		return ppno9;
	}
	public void setPpno9(String ppno9) {
		this.ppno9 = ppno9;
	}
	public String getCred_lmt9() {
		return cred_lmt9;
	}
	public void setCred_lmt9(String cred_lmt9) {
		this.cred_lmt9 = cred_lmt9;
	}
	public String getSpec_inst9() {
		return spec_inst9;
	}
	public void setSpec_inst9(String spec_inst9) {
		this.spec_inst9 = spec_inst9;
	}
	public String getAtm9() {
		return atm9;
	}
	public void setAtm9(String atm9) {
		this.atm9 = atm9;
	}
	public String getTele9() {
		return tele9;
	}
	public void setTele9(String tele9) {
		this.tele9 = tele9;
	}
	public String getNet9() {
		return net9;
	}
	public void setNet9(String net9) {
		this.net9 = net9;
	}
	public String getPhone9() {
		return phone9;
	}
	public void setPhone9(String phone9) {
		this.phone9 = phone9;
	}
	public String getRepay_code() {
		return repay_code;
	}
	public void setRepay_code(String repay_code) {
		this.repay_code = repay_code;
	}
	public String getBankacct1() {
		return bankacct1;
	}
	public void setBankacct1(String bankacct1) {
		this.bankacct1 = bankacct1;
	}
	public String getRepay_amt() {
		return repay_amt;
	}
	public void setRepay_amt(String repay_amt) {
		this.repay_amt = repay_amt;
	}
	public String getRepay_pct() {
		return repay_pct;
	}
	public void setRepay_pct(String repay_pct) {
		this.repay_pct = repay_pct;
	}
	public String getRepay_day() {
		return repay_day;
	}
	public void setRepay_day(String repay_day) {
		this.repay_day = repay_day;
	}
	public String getRepay_codx() {
		return repay_codx;
	}
	public void setRepay_codx(String repay_codx) {
		this.repay_codx = repay_codx;
	}
	public String getBankacct2() {
		return bankacct2;
	}
	public void setBankacct2(String bankacct2) {
		this.bankacct2 = bankacct2;
	}
	public String getRepay_amtx() {
		return repay_amtx;
	}
	public void setRepay_amtx(String repay_amtx) {
		this.repay_amtx = repay_amtx;
	}
	public String getRepay_pctx() {
		return repay_pctx;
	}
	public void setRepay_pctx(String repay_pctx) {
		this.repay_pctx = repay_pctx;
	}
	public String getExch_flag() {
		return exch_flag;
	}
	public void setExch_flag(String exch_flag) {
		this.exch_flag = exch_flag;
	}
	public String getBankacct3() {
		return bankacct3;
	}
	public void setBankacct3(String bankacct3) {
		this.bankacct3 = bankacct3;
	}
	public String getExch_code() {
		return exch_code;
	}
	public void setExch_code(String exch_code) {
		this.exch_code = exch_code;
	}
	public String getGuarn_id() {
		return guarn_id;
	}
	public void setGuarn_id(String guarn_id) {
		this.guarn_id = guarn_id;
	}
	public String getGuarn_bank() {
		return guarn_bank;
	}
	public void setGuarn_bank(String guarn_bank) {
		this.guarn_bank = guarn_bank;
	}
	public String getGuarn_ref() {
		return guarn_ref;
	}
	public void setGuarn_ref(String guarn_ref) {
		this.guarn_ref = guarn_ref;
	}
	public String getGuarn_code() {
		return guarn_code;
	}
	public void setGuarn_code(String guarn_code) {
		this.guarn_code = guarn_code;
	}
	public String getGuarn_ref2() {
		return guarn_ref2;
	}
	public void setGuarn_ref2(String guarn_ref2) {
		this.guarn_ref2 = guarn_ref2;
	}
	public String getXrefinac1() {
		return xrefinac1;
	}
	public void setXrefinac1(String xrefinac1) {
		this.xrefinac1 = xrefinac1;
	}
	public String getXrefcode1() {
		return xrefcode1;
	}
	public void setXrefcode1(String xrefcode1) {
		this.xrefcode1 = xrefcode1;
	}
	public String getXrefcrlmt1() {
		return xrefcrlmt1;
	}
	public void setXrefcrlmt1(String xrefcrlmt1) {
		this.xrefcrlmt1 = xrefcrlmt1;
	}
	public String getXrefinac2() {
		return xrefinac2;
	}
	public void setXrefinac2(String xrefinac2) {
		this.xrefinac2 = xrefinac2;
	}
	public String getXrefcode2() {
		return xrefcode2;
	}
	public void setXrefcode2(String xrefcode2) {
		this.xrefcode2 = xrefcode2;
	}
	public String getApp_giftno() {
		return app_giftno;
	}
	public void setApp_giftno(String app_giftno) {
		this.app_giftno = app_giftno;
	}
	public String getApp_cross() {
		return app_cross;
	}
	public void setApp_cross(String app_cross) {
		this.app_cross = app_cross;
	}
	public String getAval_nbr() {
		return aval_nbr;
	}
	public void setAval_nbr(String aval_nbr) {
		this.aval_nbr = aval_nbr;
	}
	public String getAval_name() {
		return aval_name;
	}
	public void setAval_name(String aval_name) {
		this.aval_name = aval_name;
	}
	public String getAchk_nbr() {
		return achk_nbr;
	}
	public void setAchk_nbr(String achk_nbr) {
		this.achk_nbr = achk_nbr;
	}
	public String getAchk_name() {
		return achk_name;
	}
	public void setAchk_name(String achk_name) {
		this.achk_name = achk_name;
	}
	public String getAdec_nbr() {
		return adec_nbr;
	}
	public void setAdec_nbr(String adec_nbr) {
		this.adec_nbr = adec_nbr;
	}
	public String getAdec_name() {
		return adec_name;
	}
	public void setAdec_name(String adec_name) {
		this.adec_name = adec_name;
	}
	
	//XM_APPLN
	public XM_APPLN createXM_APPLN(String userId){
		XM_APPLN obj = new XM_APPLN();
		obj.setAb_branch(ab_branch);
		obj.setAcc_type(acc_type);
		obj.setAddl_card(addl_card);
		obj.setApp_batch(app_batch);
		obj.setApp_deccd(app_deccd);
		obj.setApp_decday(app_decday);
		obj.setApp_decreas(app_decreas);
		obj.setApp_source(app_source);
		obj.setBase_card(base_card);
		obj.setBranch(branch);
		obj.setBrch_name(brch_name);
		obj.setBusiness(business);
		obj.setBusn_name(busn_name);
		obj.setCp_nbrmth(cp_nbrmth);
		obj.setCp_no(cp_no);
		obj.setCustomer_id(customer_id);
		obj.setCycle_nbr(cycle_nbr);
		obj.setDebit_brn(debit_brn);
		obj.setDownprod(downprod);
		obj.setEmboss_cpy(emboss_cpy);
		obj.setFee_group(fee_group);
		obj.setGtoc(gtoc);
		obj.setHdwr_sn(hdwr_sn);
		obj.setInt_code(int_code);
		obj.setMail_to(mail_to);
		obj.setProduct(product);
		obj.setRush_card(rush_card);
		obj.setRush_fee(rush_fee);
		obj.setStm_code(stm_code);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_SQED
	public XM_APPLN_SQED createXM_APPLN_SQED(String userId){
		XM_APPLN_SQED obj = new XM_APPLN_SQED();
		obj.setClimit(climit);
		obj.setCrdlmt_req(crdlmt_req);
		obj.setCred_lmt(cred_lmt);
		obj.setCrlm_x_req(crlm_x_req);
		obj.setCustomer_id(customer_id);
		obj.setMplmt_req(mplmt_req);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_KPMX
	public List<XM_APPLN_KPMX> createXM_APPLN_KPMX(String userId){
		List<XM_APPLN_KPMX> ls = new ArrayList<XM_APPLN_KPMX>();
		XM_APPLN_KPMX obj = new XM_APPLN_KPMX();
		obj.setAtm(atm1);
		obj.setCdesploc(cdesploc1);
		obj.setCdespmtd(cdespmtd1);
		obj.setCdfrm(cdfrm1);
		obj.setCourierf(courierf1);
		obj.setCred_lmt(cred_lmt1);
		obj.setCustomer_id(customer_id);
		obj.setCustr_nbr(custr_nbr1);
		obj.setEmbname_d(embname_d1);
		obj.setEmboss_cd(emboss_cd1);
		obj.setLsh(0);
		obj.setNet(net1);
		obj.setPhone(phone1);
		obj.setPin_chk(pin_chk1);
		obj.setPin_reqd(pin_reqd1);
		obj.setPpno(ppno1);
		obj.setSms_yn(sms_yn1);
		obj.setSpec_inst(spec_inst1);
		obj.setTele(tele1);
		obj.setCreatedBy(userId);
		ls.add(obj);
		
		XM_APPLN_KPMX obj2 = new XM_APPLN_KPMX();
		obj2.setAtm(atm2);
		obj2.setCdesploc(cdesploc2);
		obj2.setCdespmtd(cdespmtd2);
		obj2.setCdfrm(cdfrm2);
		obj2.setCourierf(courierf2);
		obj2.setCred_lmt(cred_lmt2);
		obj2.setCustomer_id(customer_id);
		obj2.setCustr_nbr(custr_nbr2);
		obj2.setEmbname_d(embname_d2);
		obj2.setEmboss_cd(emboss_cd2);
		obj2.setLsh(1);
		obj2.setNet(net2);
		obj2.setPhone(phone2);
		obj2.setPin_chk(pin_chk2);
		obj2.setPin_reqd(pin_reqd2);
		obj2.setPpno(ppno2);
		obj2.setSms_yn(sms_yn2);
		obj2.setSpec_inst(spec_inst2);
		obj2.setTele(tele2);
		obj2.setCreatedBy(userId);
		ls.add(obj2);
		
		if(Integer.parseInt(cardCount)>2){
			XM_APPLN_KPMX obj3 = new XM_APPLN_KPMX();
			obj3.setAtm(atm3);
			obj3.setCdesploc(cdesploc3);
			obj3.setCdespmtd(cdespmtd3);
			obj3.setCdfrm(cdfrm3);
			obj3.setCourierf(courierf3);
			obj3.setCred_lmt(cred_lmt3);
			obj3.setCustomer_id(customer_id);
			obj3.setCustr_nbr(custr_nbr3);
			obj3.setEmbname_d(embname_d3);
			obj3.setEmboss_cd(emboss_cd3);
			obj3.setLsh(2);
			obj3.setNet(net3);
			obj3.setPhone(phone3);
			obj3.setPin_chk(pin_chk3);
			obj3.setPin_reqd(pin_reqd3);
			obj3.setPpno(ppno3);
			obj3.setSms_yn(sms_yn3);
			obj3.setSpec_inst(spec_inst3);
			obj3.setTele(tele3);
			obj3.setCreatedBy(userId);
			ls.add(obj3);
		}
		if(Integer.parseInt(cardCount)>3){
			XM_APPLN_KPMX obj4 = new XM_APPLN_KPMX();
			obj4.setAtm(atm4);
			obj4.setCdesploc(cdesploc4);
			obj4.setCdespmtd(cdespmtd4);
			obj4.setCdfrm(cdfrm4);
			obj4.setCourierf(courierf4);
			obj4.setCred_lmt(cred_lmt4);
			obj4.setCustomer_id(customer_id);
			obj4.setCustr_nbr(custr_nbr4);
			obj4.setEmbname_d(embname_d4);
			obj4.setEmboss_cd(emboss_cd4);
			obj4.setLsh(3);
			obj4.setNet(net4);
			obj4.setPhone(phone4);
			obj4.setPin_chk(pin_chk4);
			obj4.setPin_reqd(pin_reqd4);
			obj4.setPpno(ppno4);
			obj4.setSms_yn(sms_yn4);
			obj4.setSpec_inst(spec_inst4);
			obj4.setTele(tele4);
			obj4.setCreatedBy(userId);
			ls.add(obj4);
		}
		if(Integer.parseInt(cardCount)>4){
			XM_APPLN_KPMX obj5 = new XM_APPLN_KPMX();
			obj5.setAtm(atm5);
			obj5.setCdesploc(cdesploc5);
			obj5.setCdespmtd(cdespmtd5);
			obj5.setCdfrm(cdfrm5);
			obj5.setCourierf(courierf5);
			obj5.setCred_lmt(cred_lmt5);
			obj5.setCustomer_id(customer_id);
			obj5.setCustr_nbr(custr_nbr5);
			obj5.setEmbname_d(embname_d5);
			obj5.setEmboss_cd(emboss_cd5);
			obj5.setLsh(4);
			obj5.setNet(net5);
			obj5.setPhone(phone5);
			obj5.setPin_chk(pin_chk5);
			obj5.setPin_reqd(pin_reqd5);
			obj5.setPpno(ppno5);
			obj5.setSms_yn(sms_yn5);
			obj5.setSpec_inst(spec_inst5);
			obj5.setTele(tele5);
			obj5.setCreatedBy(userId);
			ls.add(obj5);
		}
		if(Integer.parseInt(cardCount)>5){
			XM_APPLN_KPMX obj6 = new XM_APPLN_KPMX();
			obj6.setAtm(atm6);
			obj6.setCdesploc(cdesploc6);
			obj6.setCdespmtd(cdespmtd6);
			obj6.setCdfrm(cdfrm6);
			obj6.setCourierf(courierf6);
			obj6.setCred_lmt(cred_lmt6);
			obj6.setCustomer_id(customer_id);
			obj6.setCustr_nbr(custr_nbr6);
			obj6.setEmbname_d(embname_d6);
			obj6.setEmboss_cd(emboss_cd6);
			obj6.setLsh(5);
			obj6.setNet(net6);
			obj6.setPhone(phone6);
			obj6.setPin_chk(pin_chk6);
			obj6.setPin_reqd(pin_reqd6);
			obj6.setPpno(ppno6);
			obj6.setSms_yn(sms_yn6);
			obj6.setSpec_inst(spec_inst6);
			obj6.setTele(tele6);
			obj6.setCreatedBy(userId);
			ls.add(obj6);
		}
		if(Integer.parseInt(cardCount)>6){
			XM_APPLN_KPMX obj7 = new XM_APPLN_KPMX();
			obj7.setAtm(atm7);
			obj7.setCdesploc(cdesploc7);
			obj7.setCdespmtd(cdespmtd7);
			obj7.setCdfrm(cdfrm7);
			obj7.setCourierf(courierf7);
			obj7.setCred_lmt(cred_lmt7);
			obj7.setCustomer_id(customer_id);
			obj7.setCustr_nbr(custr_nbr7);
			obj7.setEmbname_d(embname_d7);
			obj7.setEmboss_cd(emboss_cd7);
			obj7.setLsh(6);
			obj7.setNet(net7);
			obj7.setPhone(phone7);
			obj7.setPin_chk(pin_chk7);
			obj7.setPin_reqd(pin_reqd7);
			obj7.setPpno(ppno7);
			obj7.setSms_yn(sms_yn7);
			obj7.setSpec_inst(spec_inst7);
			obj7.setTele(tele7);
			obj7.setCreatedBy(userId);
			ls.add(obj7);
		}
		if(Integer.parseInt(cardCount)>7){
			XM_APPLN_KPMX obj8 = new XM_APPLN_KPMX();
			obj8.setAtm(atm8);
			obj8.setCdesploc(cdesploc8);
			obj8.setCdespmtd(cdespmtd8);
			obj8.setCdfrm(cdfrm8);
			obj8.setCourierf(courierf8);
			obj8.setCred_lmt(cred_lmt8);
			obj8.setCustomer_id(customer_id);
			obj8.setCustr_nbr(custr_nbr8);
			obj8.setEmbname_d(embname_d8);
			obj8.setEmboss_cd(emboss_cd8);
			obj8.setLsh(7);
			obj8.setNet(net8);
			obj8.setPhone(phone8);
			obj8.setPin_chk(pin_chk8);
			obj8.setPin_reqd(pin_reqd8);
			obj8.setPpno(ppno8);
			obj8.setSms_yn(sms_yn8);
			obj8.setSpec_inst(spec_inst8);
			obj8.setTele(tele8);
			obj8.setCreatedBy(userId);
			ls.add(obj8);
		}
		if(Integer.parseInt(cardCount)>8){
			XM_APPLN_KPMX obj9 = new XM_APPLN_KPMX();
			obj9.setAtm(atm9);
			obj9.setCdesploc(cdesploc9);
			obj9.setCdespmtd(cdespmtd9);
			obj9.setCdfrm(cdfrm9);
			obj9.setCourierf(courierf9);
			obj9.setCred_lmt(cred_lmt9);
			obj9.setCustomer_id(customer_id);
			obj9.setCustr_nbr(custr_nbr9);
			obj9.setEmbname_d(embname_d9);
			obj9.setEmboss_cd(emboss_cd9);
			obj9.setLsh(8);
			obj9.setNet(net9);
			obj9.setPhone(phone9);
			obj9.setPin_chk(pin_chk9);
			obj9.setPin_reqd(pin_reqd9);
			obj9.setPpno(ppno9);
			obj9.setSms_yn(sms_yn9);
			obj9.setSpec_inst(spec_inst9);
			obj9.setTele(tele9);
			obj9.setCreatedBy(userId);
			ls.add(obj9);
		}
		return ls;
	}
	
	//XM_APPLN_HKSZ
	public XM_APPLN_HKSZ createXM_APPLN_HKSZ(String userId){
		XM_APPLN_HKSZ obj = new XM_APPLN_HKSZ();
		obj.setBankacct1(bankacct1);
		obj.setBankacct2(bankacct2);
		obj.setBankacct3(bankacct3);
		obj.setCustomer_id(customer_id);
		obj.setExch_code(exch_code);
		obj.setExch_flag(exch_flag);
		obj.setRepay_amt(repay_amt);
		obj.setRepay_amtx(repay_amtx);
		obj.setRepay_code(repay_code);
		obj.setRepay_codx(repay_codx);
		obj.setRepay_day(repay_day);
		obj.setRepay_pct(repay_pct);
		obj.setRepay_pctx(repay_pctx);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_DBXX
	public XM_APPLN_DBXX createXM_APPLN_DBXX(String userId){
		XM_APPLN_DBXX obj = new XM_APPLN_DBXX();
		obj.setCustomer_id(customer_id);
		obj.setGuarn_bank(guarn_bank);
		obj.setGuarn_code(guarn_code);
		obj.setGuarn_id(guarn_id);
		obj.setGuarn_ref(guarn_ref);
		obj.setGuarn_ref2(guarn_ref2);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_QTXYKXX
	public XM_APPLN_QTXYKXX createXM_APPLN_QTXYKXX(String userId){
		XM_APPLN_QTXYKXX obj = new XM_APPLN_QTXYKXX();
		obj.setCustomer_id(customer_id);
		obj.setXrefcode1(xrefcode1);
		obj.setXrefcode2(xrefcode2);
		obj.setXrefcrlmt1(xrefcrlmt1);
		obj.setXrefinac1(xrefinac1);
		obj.setXrefinac2(xrefinac2);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_YWXX
	public XM_APPLN_YWXX createXM_APPLN_YWXX(String userId){
		XM_APPLN_YWXX obj = new XM_APPLN_YWXX();
		obj.setApp_cross(app_cross);
		obj.setApp_giftno(app_giftno);
		obj.setCustomer_id(customer_id);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_DCSC
	public XM_APPLN_DCSC createXM_APPLN_DCSC(String userId){
		XM_APPLN_DCSC obj = new XM_APPLN_DCSC();
		obj.setAchk_name(achk_name);
		obj.setAchk_nbr(achk_nbr);
		obj.setAdec_name(adec_name);
		obj.setAdec_nbr(adec_nbr);
		obj.setAval_name(aval_name);
		obj.setAval_nbr(aval_nbr);
		obj.setCustomer_id(customer_id);
		obj.setCreatedBy(userId);
		return obj;
	}
}
