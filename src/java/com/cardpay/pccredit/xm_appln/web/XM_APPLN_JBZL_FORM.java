package com.cardpay.pccredit.xm_appln.web;

import java.util.ArrayList;
import java.util.List;

import com.cardpay.pccredit.xm_appln.model.XM_APPLN_JCZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHFW;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHLB;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_LXRZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_POZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ZXQSZL;
import com.wicresoft.jrad.base.web.form.BaseForm;

public class XM_APPLN_JBZL_FORM extends BaseForm {
	public static final long serialVersionUID = 1L;

	//xm_appln_jczl
	public String customer_id;
	public String card_id;
	public String surname;
	public String race_code;
	public String lang_code;
	public String dob_input;
	public String home_phone;
	public String gender;
	public String mar_status;
	public String mo_phone;
	public String title_inp;
	public String mthr_mname;
	public String custr_ref;
	public String emply_nbr;
	public String nation;
	public String nation_cd;
	public String educa;
	public String home_code;
	public String income_src;
	public String dependents;
	public String yr_there;
	public String home_loan;
	public String email_addr;
	public String comp_name;
	public String emply_dept;
	public String ird_number;
	public String occ_catgry;
	public String int_taxcod;
	public String income_ann;
	public String occ_code;
	public String posn_emply;
	public String yr_in_comp;
	public String busi_phone;
	public String cune_cr;
	public String secur_nbr;
	public String belong_bank; 
	/*分机号 added by nihc 20150706 begin*/
	public String extension;
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/*分机号 added by nihc 20150706 end*/
	//xm_appln_khfw
	public String mail_code;
	public String work_calls;
	public String elig_loyal;
	public String id_verify;
	public String idcp_yn;
	public String imp_cuflag;
	
	//xm_appln_khlb
	public String class_code;
	public String risk_levl;
	public String value_levl;
	
	//xm_appln_khed
	public String cred_limit;
	public String credlim_x;
	public String mp_limit;
	
	//xm_appln_pozl
	public String spu_compnm;
	public String spu_custr_nbr;
	public String spu_income_src;
	public String spu_mobile;
	public String spu_yr_in_comp;
	public String spu_income_ann;
	public String spu_name;
	public String spu_telno;
	
	//xm_appln_lxrzl
	public String con_name1;
	public String con_compnm1;
	public String con_telno1;
	public String con_rel1;
	public String con_mobile1;
	public String con_name2;
	public String con_compnm2;
	public String con_telno2;
	public String con_rel2;
	public String con_mobile2;
	
	//xm_appln_zxqszl
	public String rel_name;
	public String rel_telno;
	public String rel_rel;
	public String rel_mobile;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getRace_code() {
		return race_code;
	}
	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	public String getDob_input() {
		return dob_input;
	}
	public void setDob_input(String dob_input) {
		this.dob_input = dob_input;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMar_status() {
		return mar_status;
	}
	public void setMar_status(String mar_status) {
		this.mar_status = mar_status;
	}
	public String getMo_phone() {
		return mo_phone;
	}
	public void setMo_phone(String mo_phone) {
		this.mo_phone = mo_phone;
	}
	public String getTitle_inp() {
		return title_inp;
	}
	public void setTitle_inp(String title_inp) {
		this.title_inp = title_inp;
	}
	public String getMthr_mname() {
		return mthr_mname;
	}
	public void setMthr_mname(String mthr_mname) {
		this.mthr_mname = mthr_mname;
	}
	public String getCustr_ref() {
		return custr_ref;
	}
	public void setCustr_ref(String custr_ref) {
		this.custr_ref = custr_ref;
	}
	public String getEmply_nbr() {
		return emply_nbr;
	}
	public void setEmply_nbr(String emply_nbr) {
		this.emply_nbr = emply_nbr;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNation_cd() {
		return nation_cd;
	}
	public void setNation_cd(String nation_cd) {
		this.nation_cd = nation_cd;
	}
	public String getEduca() {
		return educa;
	}
	public void setEduca(String educa) {
		this.educa = educa;
	}
	public String getHome_code() {
		return home_code;
	}
	public void setHome_code(String home_code) {
		this.home_code = home_code;
	}
	public String getIncome_src() {
		return income_src;
	}
	public void setIncome_src(String income_src) {
		this.income_src = income_src;
	}
	public String getDependents() {
		return dependents;
	}
	public void setDependents(String dependents) {
		this.dependents = dependents;
	}
	public String getYr_there() {
		return yr_there;
	}
	public void setYr_there(String yr_there) {
		this.yr_there = yr_there;
	}
	public String getHome_loan() {
		return home_loan;
	}
	public void setHome_loan(String home_loan) {
		this.home_loan = home_loan;
	}
	public String getEmail_addr() {
		return email_addr;
	}
	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public String getEmply_dept() {
		return emply_dept;
	}
	public void setEmply_dept(String emply_dept) {
		this.emply_dept = emply_dept;
	}
	public String getIrd_number() {
		return ird_number;
	}
	public void setIrd_number(String ird_number) {
		this.ird_number = ird_number;
	}
	public String getOcc_catgry() {
		return occ_catgry;
	}
	public void setOcc_catgry(String occ_catgry) {
		this.occ_catgry = occ_catgry;
	}
	public String getInt_taxcod() {
		return int_taxcod;
	}
	public void setInt_taxcod(String int_taxcod) {
		this.int_taxcod = int_taxcod;
	}
	public String getIncome_ann() {
		return income_ann;
	}
	public void setIncome_ann(String income_ann) {
		this.income_ann = income_ann;
	}
	public String getOcc_code() {
		return occ_code;
	}
	public void setOcc_code(String occ_code) {
		this.occ_code = occ_code;
	}
	public String getPosn_emply() {
		return posn_emply;
	}
	public void setPosn_emply(String posn_emply) {
		this.posn_emply = posn_emply;
	}
	public String getYr_in_comp() {
		return yr_in_comp;
	}
	public void setYr_in_comp(String yr_in_comp) {
		this.yr_in_comp = yr_in_comp;
	}
	public String getBusi_phone() {
		return busi_phone;
	}
	public void setBusi_phone(String busi_phone) {
		this.busi_phone = busi_phone;
	}
	public String getCune_cr() {
		return cune_cr;
	}
	public void setCune_cr(String cune_cr) {
		this.cune_cr = cune_cr;
	}
	public String getSecur_nbr() {
		return secur_nbr;
	}
	public void setSecur_nbr(String secur_nbr) {
		this.secur_nbr = secur_nbr;
	}
	public String getBelong_bank() {
		return belong_bank;
	}
	public void setBelong_bank(String belong_bank) {
		this.belong_bank = belong_bank;
	}
	public String getMail_code() {
		return mail_code;
	}
	public void setMail_code(String mail_code) {
		this.mail_code = mail_code;
	}
	public String getWork_calls() {
		return work_calls;
	}
	public void setWork_calls(String work_calls) {
		this.work_calls = work_calls;
	}
	public String getElig_loyal() {
		return elig_loyal;
	}
	public void setElig_loyal(String elig_loyal) {
		this.elig_loyal = elig_loyal;
	}
	public String getId_verify() {
		return id_verify;
	}
	public void setId_verify(String id_verify) {
		this.id_verify = id_verify;
	}
	public String getIdcp_yn() {
		return idcp_yn;
	}
	public void setIdcp_yn(String idcp_yn) {
		this.idcp_yn = idcp_yn;
	}
	public String getImp_cuflag() {
		return imp_cuflag;
	}
	public void setImp_cuflag(String imp_cuflag) {
		this.imp_cuflag = imp_cuflag;
	}
	public String getClass_code() {
		return class_code;
	}
	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}
	public String getRisk_levl() {
		return risk_levl;
	}
	public void setRisk_levl(String risk_levl) {
		this.risk_levl = risk_levl;
	}
	public String getValue_levl() {
		return value_levl;
	}
	public void setValue_levl(String value_levl) {
		this.value_levl = value_levl;
	}
	public String getCred_limit() {
		return cred_limit;
	}
	public void setCred_limit(String cred_limit) {
		this.cred_limit = cred_limit;
	}
	public String getCredlim_x() {
		return credlim_x;
	}
	public void setCredlim_x(String credlim_x) {
		this.credlim_x = credlim_x;
	}
	public String getMp_limit() {
		return mp_limit;
	}
	public void setMp_limit(String mp_limit) {
		this.mp_limit = mp_limit;
	}
	public String getSpu_compnm() {
		return spu_compnm;
	}
	public void setSpu_compnm(String spu_compnm) {
		this.spu_compnm = spu_compnm;
	}
	public String getSpu_custr_nbr() {
		return spu_custr_nbr;
	}
	public void setSpu_custr_nbr(String spu_custr_nbr) {
		this.spu_custr_nbr = spu_custr_nbr;
	}
	public String getSpu_income_src() {
		return spu_income_src;
	}
	public void setSpu_income_src(String spu_income_src) {
		this.spu_income_src = spu_income_src;
	}
	public String getSpu_mobile() {
		return spu_mobile;
	}
	public void setSpu_mobile(String spu_mobile) {
		this.spu_mobile = spu_mobile;
	}
	public String getSpu_yr_in_comp() {
		return spu_yr_in_comp;
	}
	public void setSpu_yr_in_comp(String spu_yr_in_comp) {
		this.spu_yr_in_comp = spu_yr_in_comp;
	}
	public String getSpu_income_ann() {
		return spu_income_ann;
	}
	public void setSpu_income_ann(String spu_income_ann) {
		this.spu_income_ann = spu_income_ann;
	}
	public String getSpu_name() {
		return spu_name;
	}
	public void setSpu_name(String spu_name) {
		this.spu_name = spu_name;
	}
	public String getSpu_telno() {
		return spu_telno;
	}
	public void setSpu_telno(String spu_telno) {
		this.spu_telno = spu_telno;
	}
	public String getCon_name1() {
		return con_name1;
	}
	public void setCon_name1(String con_name1) {
		this.con_name1 = con_name1;
	}
	public String getCon_compnm1() {
		return con_compnm1;
	}
	public void setCon_compnm1(String con_compnm1) {
		this.con_compnm1 = con_compnm1;
	}
	public String getCon_telno1() {
		return con_telno1;
	}
	public void setCon_telno1(String con_telno1) {
		this.con_telno1 = con_telno1;
	}
	public String getCon_rel1() {
		return con_rel1;
	}
	public void setCon_rel1(String con_rel1) {
		this.con_rel1 = con_rel1;
	}
	public String getCon_mobile1() {
		return con_mobile1;
	}
	public void setCon_mobile1(String con_mobile1) {
		this.con_mobile1 = con_mobile1;
	}
	public String getCon_name2() {
		return con_name2;
	}
	public void setCon_name2(String con_name2) {
		this.con_name2 = con_name2;
	}
	public String getCon_compnm2() {
		return con_compnm2;
	}
	public void setCon_compnm2(String con_compnm2) {
		this.con_compnm2 = con_compnm2;
	}
	public String getCon_telno2() {
		return con_telno2;
	}
	public void setCon_telno2(String con_telno2) {
		this.con_telno2 = con_telno2;
	}
	public String getCon_rel2() {
		return con_rel2;
	}
	public void setCon_rel2(String con_rel2) {
		this.con_rel2 = con_rel2;
	}
	public String getCon_mobile2() {
		return con_mobile2;
	}
	public void setCon_mobile2(String con_mobile2) {
		this.con_mobile2 = con_mobile2;
	}
	public String getRel_name() {
		return rel_name;
	}
	public void setRel_name(String rel_name) {
		this.rel_name = rel_name;
	}
	public String getRel_telno() {
		return rel_telno;
	}
	public void setRel_telno(String rel_telno) {
		this.rel_telno = rel_telno;
	}
	public String getRel_rel() {
		return rel_rel;
	}
	public void setRel_rel(String rel_rel) {
		this.rel_rel = rel_rel;
	}
	public String getRel_mobile() {
		return rel_mobile;
	}
	public void setRel_mobile(String rel_mobile) {
		this.rel_mobile = rel_mobile;
	}
	
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	//XM_APPLN_JCZL
	public XM_APPLN_JCZL createXM_APPLN_JCZL(String customerId,String userId){
		XM_APPLN_JCZL obj = new XM_APPLN_JCZL();
		obj.setCard_id(card_id);
		obj.setBusi_phone(busi_phone);
		obj.setComp_name(comp_name);
		obj.setCune_cr(cune_cr);
		obj.setCustomer_id(customerId);
		obj.setCustr_ref(custr_ref);
		obj.setDependents(dependents);
		obj.setDob_input(dob_input);
		obj.setEduca(educa);
		obj.setEmail_addr(email_addr);
		obj.setEmply_dept(emply_dept);
		obj.setEmply_nbr(emply_nbr);
		obj.setGender(gender);
		obj.setHome_code(home_code);
		obj.setHome_loan(home_loan);
		obj.setHome_phone(home_phone);
		obj.setIncome_ann(income_ann);
		obj.setIncome_src(income_src);
		obj.setInt_taxcod(int_taxcod);
		obj.setIrd_number(ird_number);
		obj.setLang_code(lang_code);
		obj.setMar_status(mar_status);
		obj.setMo_phone(mo_phone);
		obj.setMthr_mname(mthr_mname);
		obj.setNation(nation);
		obj.setNation_cd(nation_cd);
		obj.setOcc_catgry(occ_catgry);
		obj.setOcc_code(occ_code);
		obj.setPosn_emply(posn_emply);
		obj.setRace_code(race_code);
		obj.setSecur_nbr(secur_nbr);
		obj.setSurname(surname);
		obj.setTitle_inp(title_inp);
		obj.setYr_in_comp(yr_in_comp);
		obj.setYr_there(yr_there);
		obj.setBelong_bank(belong_bank);
		obj.setExtension(extension);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_KHFW
	public XM_APPLN_KHFW createXM_APPLN_KHFW(String customerId,String userId){
		XM_APPLN_KHFW obj = new XM_APPLN_KHFW();
		obj.setCustomer_id(customerId);
		obj.setElig_loyal(elig_loyal);
		obj.setId_verify(id_verify);
		obj.setIdcp_yn(idcp_yn);
		obj.setImp_cuflag(imp_cuflag);
		obj.setMail_code(mail_code);
		obj.setWork_calls(work_calls);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_KHLB
	public XM_APPLN_KHLB createXM_APPLN_KHLB(String customerId,String userId){
		XM_APPLN_KHLB obj = new XM_APPLN_KHLB();
		obj.setClass_code(class_code);
		obj.setCustomer_id(customerId);
		obj.setRisk_levl(risk_levl);
		obj.setValue_levl(value_levl);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_KHED
	public XM_APPLN_KHED createXM_APPLN_KHED(String customerId,String userId){
		XM_APPLN_KHED obj = new XM_APPLN_KHED();
		obj.setCred_limit(cred_limit);
		obj.setCredlim_x(credlim_x);
		obj.setCustomer_id(customerId);
		obj.setMp_limit(mp_limit);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_POZL
	public XM_APPLN_POZL createXM_APPLN_POZL(String customerId,String userId){
		XM_APPLN_POZL obj = new XM_APPLN_POZL();
		obj.setCompnm(spu_compnm);
		obj.setCustomer_id(customerId);
		obj.setCustr_nbr(spu_custr_nbr);
		obj.setIncome_ann(spu_income_ann);
		obj.setIncome_src(spu_income_src);
		obj.setMobile(spu_mobile);
		obj.setName(spu_name);
		obj.setTelno(spu_telno);
		obj.setYr_in_comp(spu_yr_in_comp);
		obj.setCreatedBy(userId);
		return obj;
	}
	
	//XM_APPLN_LXRZL
	public List<XM_APPLN_LXRZL> createXM_APPLN_LXRZL(String customerId,String userId){
		List<XM_APPLN_LXRZL> ls = new ArrayList<XM_APPLN_LXRZL>();
		XM_APPLN_LXRZL obj = new XM_APPLN_LXRZL();
		obj.setLsh(0);
		obj.setCompnm(con_compnm1);
		obj.setCustomer_id(customerId);
		obj.setMobile(con_mobile1);
		obj.setName(con_name1);
		obj.setRel(con_rel1);
		obj.setTelno(con_telno1);
		obj.setCreatedBy(userId);
		ls.add(obj);
		XM_APPLN_LXRZL obj2 = new XM_APPLN_LXRZL();
		obj2.setLsh(1);
		obj2.setCompnm(con_compnm2);
		obj2.setCustomer_id(customerId);
		obj2.setMobile(con_mobile2);
		obj2.setName(con_name2);
		obj2.setRel(con_rel2);
		obj2.setTelno(con_telno2);
		obj2.setCreatedBy(userId);
		ls.add(obj2);
		return ls;
	}
	
	//xm_appln_jczl
	public XM_APPLN_ZXQSZL createXM_APPLN_ZXQSZL(String customerId,String userId){
		XM_APPLN_ZXQSZL obj = new XM_APPLN_ZXQSZL();
		obj.setCustomer_id(customerId);
		obj.setMobile(rel_mobile);
		obj.setName(rel_name);
		obj.setRel(rel_rel);
		obj.setTelno(rel_telno);
		obj.setCreatedBy(userId);
		return obj;
	}
}
