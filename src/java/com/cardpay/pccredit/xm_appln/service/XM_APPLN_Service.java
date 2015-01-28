package com.cardpay.pccredit.xm_appln.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.common.Cn2Spell;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_ADDR_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_DBXX_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_DCSC_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_HKSZ_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_JCZL_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_KHED_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_KHFW_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_KHLB_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_KPMX_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_LXRZL_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_POZL_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_QTXYKXX_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_SQED_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_TJINFO_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_YWXX_Dao;
import com.cardpay.pccredit.xm_appln.dao.XM_APPLN_ZXQSZL_Dao;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ADDR;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DBXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_DCSC;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_HKSZ;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_JCZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHFW;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KHLB;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_KPMX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_LXRZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_POZL;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_QTXYKXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_SQED;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_TJINFO;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_YWXX;
import com.cardpay.pccredit.xm_appln.model.XM_APPLN_ZXQSZL;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_ADDR_FORM;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_FORM;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_JBZL_FORM;
import com.cardpay.pccredit.xm_appln.web.XM_APPLN_NEW_CUSTOMER_FORM;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;
import com.wicresoft.jrad.base.database.id.IDGenerator;
import com.wicresoft.jrad.modules.privilege.model.User;

/**
 * 
 * @author 谭文华
 *
 */
@Service
public class XM_APPLN_Service {

	@Autowired
	private CustomerInforService customerInforService;
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private XM_APPLN_ADDR_Dao xM_APPLN_ADDR_Dao;
	@Autowired
	private XM_APPLN_Dao xM_APPLN_Dao;
	@Autowired
	private XM_APPLN_DBXX_Dao xM_APPLN_DBXX_Dao;
	@Autowired
	private XM_APPLN_DCSC_Dao xM_APPLN_DCSC_Dao;
	@Autowired
	private XM_APPLN_HKSZ_Dao xM_APPLN_HKSZ_Dao;
	@Autowired
	private XM_APPLN_JCZL_Dao xM_APPLN_JCZL_Dao;
	@Autowired
	private XM_APPLN_KHED_Dao xM_APPLN_KHED_Dao;
	@Autowired
	private XM_APPLN_KHFW_Dao xM_APPLN_KHFW_Dao;
	@Autowired
	private XM_APPLN_KHLB_Dao xM_APPLN_KHLB_Dao;
	@Autowired
	private XM_APPLN_KPMX_Dao xM_APPLN_KPMX_Dao;
	@Autowired
	private XM_APPLN_LXRZL_Dao xM_APPLN_LXRZL_Dao;
	@Autowired
	private XM_APPLN_POZL_Dao xM_APPLN_POZL_Dao;
	@Autowired
	private XM_APPLN_QTXYKXX_Dao xM_APPLN_QTXYKXX_Dao;
	@Autowired
	private XM_APPLN_TJINFO_Dao xM_APPLN_TJINFO_Dao;
	@Autowired
	private XM_APPLN_YWXX_Dao xM_APPLN_YWXX_Dao;
	@Autowired
	private XM_APPLN_ZXQSZL_Dao xM_APPLN_ZXQSZL_Dao;
	@Autowired
	private XM_APPLN_SQED_Dao xM_APPLN_SQED_Dao;
	
	/**
	 * 插入数据
	 * @param 基本资料page0
	 * @return
	 */
	public String insertXM_APPLN_NEW_CUSTOMER(String customerId,XM_APPLN_NEW_CUSTOMER_FORM xM_APPLN_NEW_CUSTOMER_FORM,User user){
		if(customerId == null){
			CustomerInfor customerinfor = new CustomerInfor();
			customerinfor.setCreatedBy(user.getId());
			customerinfor.setUserId(user.getId());
			customerinfor.setChineseName(xM_APPLN_NEW_CUSTOMER_FORM.getSurname());
			customerinfor.setPinyinenglishName(Cn2Spell.converterToSpell(xM_APPLN_NEW_CUSTOMER_FORM.getSurname()));
			customerinfor.setNationality("NTC00000000156");//中国
			customerinfor.setCardType("CST0000000000A");//身份证
			customerinfor.setCardId(xM_APPLN_NEW_CUSTOMER_FORM.getCard_id());//身份证
			customerinfor.setSex(xM_APPLN_NEW_CUSTOMER_FORM.getGender().equals("1")?"Male":"Female");
			customerId = customerInforService.insertCustomerInfor(customerinfor);
		}
		
		XM_APPLN_JCZL xM_APPLN_JCZL = xM_APPLN_NEW_CUSTOMER_FORM.createXM_APPLN_JCZL(customerId,user.getId());
		XM_APPLN_POZL xM_APPLN_POZL = xM_APPLN_NEW_CUSTOMER_FORM.createXM_APPLN_POZL(customerId,user.getId());
		
		insertOrUpdateXM_APPLN_JCZL(xM_APPLN_JCZL);
		insertOrUpdateXM_APPLN_POZL(xM_APPLN_POZL);
		
		return customerId;
	}
	
	/**
	 * 插入数据
	 * @param 基本资料page1
	 * @return
	 */
	public String insertXM_APPLN_JBZL(String customerId,XM_APPLN_JBZL_FORM xM_APPLN_JBZL_FORM,User user){
		/*if(customerId == null){
			CustomerInfor customerinfor = new CustomerInfor();
			customerinfor.setCreatedBy(user.getId());
			customerinfor.setUserId(user.getId());
			customerinfor.setChineseName(xM_APPLN_JBZL_FORM.getSurname());
			customerinfor.setPinyinenglishName(Cn2Spell.converterToSpell(xM_APPLN_JBZL_FORM.getSurname()));
			customerinfor.setNationality("NTC00000000156");//中国
			customerinfor.setCardType(xM_APPLN_JBZL_FORM.getRace_code());//身份证
			customerinfor.setCardId(xM_APPLN_JBZL_FORM.getCard_id());//身份证
			customerinfor.setSex(xM_APPLN_JBZL_FORM.getGender().equals("1")?"Male":"Female");
			customerId = customerInforService.insertCustomerInfor(customerinfor);
		}*/
		
		//CustomerInfor customerinfor = xM_APPLN_JBZL_FORM.createModel(XM_APPLN_JBZL_FORM.class);
		XM_APPLN_JCZL xM_APPLN_JCZL = xM_APPLN_JBZL_FORM.createXM_APPLN_JCZL(customerId,user.getId());
		XM_APPLN_KHFW xM_APPLN_KHFW = xM_APPLN_JBZL_FORM.createXM_APPLN_KHFW(customerId,user.getId());
		XM_APPLN_KHLB xM_APPLN_KHLB = xM_APPLN_JBZL_FORM.createXM_APPLN_KHLB(customerId,user.getId());
		XM_APPLN_KHED xM_APPLN_KHED = xM_APPLN_JBZL_FORM.createXM_APPLN_KHED(customerId,user.getId());
		XM_APPLN_POZL xM_APPLN_POZL = xM_APPLN_JBZL_FORM.createXM_APPLN_POZL(customerId,user.getId());
		List<XM_APPLN_LXRZL> xM_APPLN_LXRZL_ls = xM_APPLN_JBZL_FORM.createXM_APPLN_LXRZL(customerId,user.getId());
		XM_APPLN_ZXQSZL xM_APPLN_ZXQSZL = xM_APPLN_JBZL_FORM.createXM_APPLN_ZXQSZL(customerId,user.getId());
		
		insertOrUpdateXM_APPLN_JCZL(xM_APPLN_JCZL);
		insertOrUpdateXM_APPLN_KHFW(xM_APPLN_KHFW);
		insertOrUpdateXM_APPLN_KHLB(xM_APPLN_KHLB);
		insertOrUpdateXM_APPLN_KHED(xM_APPLN_KHED);
		insertOrUpdateXM_APPLN_POZL(xM_APPLN_POZL);
		insertOrUpdateXM_APPLN_LXRZL(xM_APPLN_LXRZL_ls);
		insertOrUpdateXM_APPLN_ZXQSZL(xM_APPLN_ZXQSZL);
		return customerId;
	}
	
	/**
	 * 插入数据
	 * @param 申请信息
	 * @return
	 */
	public void insertXM_APPLN(XM_APPLN_FORM xM_APPLN_FORM,User user){
		XM_APPLN xM_APPLN = xM_APPLN_FORM.createXM_APPLN(user.getId());
		XM_APPLN_SQED xM_APPLN_SQED = xM_APPLN_FORM.createXM_APPLN_SQED(user.getId());
		List<XM_APPLN_KPMX> xM_APPLN_KPMX_ls = xM_APPLN_FORM.createXM_APPLN_KPMX(user.getId());
		XM_APPLN_HKSZ xM_APPLN_HKSZ = xM_APPLN_FORM.createXM_APPLN_HKSZ(user.getId());
		XM_APPLN_DBXX xM_APPLN_DBXX = xM_APPLN_FORM.createXM_APPLN_DBXX(user.getId());
		XM_APPLN_QTXYKXX xM_APPLN_QTXYKXX = xM_APPLN_FORM.createXM_APPLN_QTXYKXX(user.getId());
		XM_APPLN_YWXX xM_APPLN_YWXX = xM_APPLN_FORM.createXM_APPLN_YWXX(user.getId());
		XM_APPLN_DCSC xM_APPLN_DCSC = xM_APPLN_FORM.createXM_APPLN_DCSC(user.getId());
		
		insertOrUpdateXM_APPLN(xM_APPLN);
		insertOrUpdateXM_APPLN_SQED(xM_APPLN_SQED);
		insertOrUpdateXM_APPLN_KPMX(xM_APPLN_KPMX_ls);
		insertOrUpdateXM_APPLN_HKSZ(xM_APPLN_HKSZ);
		insertOrUpdateXM_APPLN_DBXX(xM_APPLN_DBXX);
		insertOrUpdateXM_APPLN_QTXYKXX(xM_APPLN_QTXYKXX);
		insertOrUpdateXM_APPLN_YWXX(xM_APPLN_YWXX);
		insertOrUpdateXM_APPLN_DCSC(xM_APPLN_DCSC);
	}
	
	/**
	 * 插入数据
	 * @param 地址信息
	 * @return
	 */
	public void insertXM_APPLN_ADDR(XM_APPLN_ADDR_FORM xM_APPLN_ADDR_FORM,User user){
		XM_APPLN_TJINFO xM_APPLN_TJINFO = xM_APPLN_ADDR_FORM.createXM_APPLN_TJINFO(user.getId());
		XM_APPLN_ADDR xM_APPLN_ADDR = xM_APPLN_ADDR_FORM.createXM_APPLN_ADDR(user.getId());
		
		insertOrUpdateXM_APPLN_TJINFO(xM_APPLN_TJINFO);
		insertOrUpdateXM_APPLN_ADDR(xM_APPLN_ADDR);
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_JCZL基础资料
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_JCZL(XM_APPLN_JCZL xM_APPLN_JCZL) {
		XM_APPLN_JCZL tmp = this.xM_APPLN_JCZL_Dao.findByCustomerId(xM_APPLN_JCZL.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_JCZL.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_JCZL.setId(id);
		xM_APPLN_JCZL.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_JCZL);
		return xM_APPLN_JCZL.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_KHFW客户服务
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_KHFW(XM_APPLN_KHFW xM_APPLN_KHFW) {
		XM_APPLN_KHFW tmp = this.xM_APPLN_KHFW_Dao.findByCustomerId(xM_APPLN_KHFW.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_KHFW.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_KHFW.setId(id);
		xM_APPLN_KHFW.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_KHFW);
		return xM_APPLN_KHFW.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_KHLB客户类别
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_KHLB(XM_APPLN_KHLB xM_APPLN_KHLB) {
		XM_APPLN_KHLB tmp = this.xM_APPLN_KHLB_Dao.findByCustomerId(xM_APPLN_KHLB.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_KHLB.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_KHLB.setId(id);
		xM_APPLN_KHLB.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_KHLB);
		return xM_APPLN_KHLB.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_KHED客户额度
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_KHED(XM_APPLN_KHED xM_APPLN_KHED) {
		XM_APPLN_KHED tmp = this.xM_APPLN_KHED_Dao.findByCustomerId(xM_APPLN_KHED.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_KHED.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_KHED.setId(id);
		xM_APPLN_KHED.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_KHED);
		return xM_APPLN_KHED.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_POZL配偶资料
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_POZL(XM_APPLN_POZL xM_APPLN_POZL) {
		XM_APPLN_POZL tmp = this.xM_APPLN_POZL_Dao.findByCustomerId(xM_APPLN_POZL.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_POZL.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_POZL.setId(id);
		xM_APPLN_POZL.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_POZL);
		return xM_APPLN_POZL.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_LXRZL联系人资料
	 * @return
	 */
	public void insertOrUpdateXM_APPLN_LXRZL(List<XM_APPLN_LXRZL> xM_APPLN_LXRZL_ls) {
		List<XM_APPLN_LXRZL> tmp = this.xM_APPLN_LXRZL_Dao.findByCustomerId(xM_APPLN_LXRZL_ls.get(0).getCustomer_id());
		if(tmp != null){
			for(XM_APPLN_LXRZL obj:tmp){
				commonDao.deleteObject(XM_APPLN_LXRZL.class, obj.getId());
			}
		}
		for(XM_APPLN_LXRZL obj:xM_APPLN_LXRZL_ls){
			String id = IDGenerator.generateID();
			obj.setId(id);
			obj.setCreatedTime(new Date());
			commonDao.insertObject(obj);
		}
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_ZXQSZL直系亲属资料
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_ZXQSZL(XM_APPLN_ZXQSZL xM_APPLN_ZXQSZL) {
		XM_APPLN_ZXQSZL tmp = this.xM_APPLN_ZXQSZL_Dao.findByCustomerId(xM_APPLN_ZXQSZL.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_ZXQSZL.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_ZXQSZL.setId(id);
		xM_APPLN_ZXQSZL.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_ZXQSZL);
		return xM_APPLN_ZXQSZL.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN申请基础资料
	 * @return
	 */
	public String insertOrUpdateXM_APPLN(XM_APPLN xM_APPLN) {
		XM_APPLN tmp = this.xM_APPLN_Dao.findByCustomerId(xM_APPLN.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN.setId(id);
		xM_APPLN.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN);
		return xM_APPLN.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_SQED申请额度
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_SQED(XM_APPLN_SQED xM_APPLN_SQED) {
		XM_APPLN_SQED tmp = this.xM_APPLN_SQED_Dao.findByCustomerId(xM_APPLN_SQED.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_SQED.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_SQED.setId(id);
		xM_APPLN_SQED.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_SQED);
		return xM_APPLN_SQED.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_KPMX卡片明细
	 * @return
	 */
	public void insertOrUpdateXM_APPLN_KPMX(List<XM_APPLN_KPMX> xM_APPLN_KPMX_ls) {
		List<XM_APPLN_KPMX> tmp = this.xM_APPLN_KPMX_Dao.findByCustomerId(xM_APPLN_KPMX_ls.get(0).getCustomer_id());
		if(tmp != null){
			for(XM_APPLN_KPMX obj:tmp){
				commonDao.deleteObject(XM_APPLN_KPMX.class, obj.getId());
			}
		}
		for(XM_APPLN_KPMX obj:xM_APPLN_KPMX_ls){
			String id = IDGenerator.generateID();
			obj.setId(id);
			obj.setCreatedTime(new Date());
			commonDao.insertObject(obj);
		}
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_HKSZ还款设置
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_HKSZ(XM_APPLN_HKSZ xM_APPLN_HKSZ) {
		XM_APPLN_HKSZ tmp = this.xM_APPLN_HKSZ_Dao.findByCustomerId(xM_APPLN_HKSZ.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_HKSZ.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_HKSZ.setId(id);
		xM_APPLN_HKSZ.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_HKSZ);
		return xM_APPLN_HKSZ.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_DBXX担保信息
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_DBXX(XM_APPLN_DBXX xM_APPLN_DBXX) {
		XM_APPLN_DBXX tmp = this.xM_APPLN_DBXX_Dao.findByCustomerId(xM_APPLN_DBXX.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_DBXX.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_DBXX.setId(id);
		xM_APPLN_DBXX.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_DBXX);
		return xM_APPLN_DBXX.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_QTXYKXX其他信用卡信息
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_QTXYKXX(XM_APPLN_QTXYKXX xM_APPLN_QTXYKXX) {
		XM_APPLN_QTXYKXX tmp = this.xM_APPLN_QTXYKXX_Dao.findByCustomerId(xM_APPLN_QTXYKXX.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_QTXYKXX.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_QTXYKXX.setId(id);
		xM_APPLN_QTXYKXX.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_QTXYKXX);
		return xM_APPLN_QTXYKXX.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_YWXX业务信息
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_YWXX(XM_APPLN_YWXX xM_APPLN_YWXX) {
		XM_APPLN_YWXX tmp = this.xM_APPLN_YWXX_Dao.findByCustomerId(xM_APPLN_YWXX.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_YWXX.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_YWXX.setId(id);
		xM_APPLN_YWXX.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_YWXX);
		return xM_APPLN_YWXX.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_DCSC调查审查
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_DCSC(XM_APPLN_DCSC xM_APPLN_DCSC) {
		XM_APPLN_DCSC tmp = this.xM_APPLN_DCSC_Dao.findByCustomerId(xM_APPLN_DCSC.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_DCSC.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_DCSC.setId(id);
		xM_APPLN_DCSC.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_DCSC);
		return xM_APPLN_DCSC.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_TJINFO推荐人信息
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_TJINFO(XM_APPLN_TJINFO xM_APPLN_TJINFO) {
		XM_APPLN_TJINFO tmp = this.xM_APPLN_TJINFO_Dao.findByCustomerId(xM_APPLN_TJINFO.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_TJINFO.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_TJINFO.setId(id);
		xM_APPLN_TJINFO.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_TJINFO);
		return xM_APPLN_TJINFO.getId();
	}
	
	/**
	 * 插入数据
	 * @param XM_APPLN_ADDR地址信息
	 * @return
	 */
	public String insertOrUpdateXM_APPLN_ADDR(XM_APPLN_ADDR xM_APPLN_ADDR) {
		XM_APPLN_ADDR tmp = this.xM_APPLN_ADDR_Dao.findByCustomerId(xM_APPLN_ADDR.getCustomer_id());
		if(tmp != null){
			commonDao.deleteObject(XM_APPLN_ADDR.class, tmp.getId());
		}
		String id = IDGenerator.generateID();
		xM_APPLN_ADDR.setId(id);
		xM_APPLN_ADDR.setCreatedTime(new Date());
		commonDao.insertObject(xM_APPLN_ADDR);
		return xM_APPLN_ADDR.getId();
	}
	
	public XM_APPLN findXM_APPLNByCustomerId(String customer_Id){
		return this.xM_APPLN_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_SQED findXM_APPLN_SQEDByCustomerId(String customer_Id){
		return this.xM_APPLN_SQED_Dao.findByCustomerId(customer_Id);
	}
	public List<XM_APPLN_KPMX> findXM_APPLN_KPMXByCustomerId(String customer_Id){
		return this.xM_APPLN_KPMX_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_HKSZ findXM_APPLN_HKSZByCustomerId(String customer_Id){
		return this.xM_APPLN_HKSZ_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_DBXX findXM_APPLN_DBXXByCustomerId(String customer_Id){
		return this.xM_APPLN_DBXX_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_QTXYKXX findXM_APPLN_QTXYKXXByCustomerId(String customer_Id){
		return this.xM_APPLN_QTXYKXX_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_DCSC findXM_APPLN_DCSCByCustomerId(String customer_Id){
		return this.xM_APPLN_DCSC_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_TJINFO findXM_APPLN_TJINFOByCustomerId(String customer_Id){
		return this.xM_APPLN_TJINFO_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_ADDR findXM_APPLN_ADDRByCustomerId(String customer_Id){
		return this.xM_APPLN_ADDR_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_JCZL findXM_APPLN_JCZLByCustomerId(String customer_Id){
		return this.xM_APPLN_JCZL_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_KHFW findXM_APPLN_KHFWByCustomerId(String customer_Id){
		return this.xM_APPLN_KHFW_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_KHLB findXM_APPLN_KHLBByCustomerId(String customer_Id){
		return this.xM_APPLN_KHLB_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_KHED findXM_APPLN_KHEDByCustomerId(String customer_Id){
		return this.xM_APPLN_KHED_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_POZL findXM_APPLN_POZLByCustomerId(String customer_Id){
		return this.xM_APPLN_POZL_Dao.findByCustomerId(customer_Id);
	}
	public List<XM_APPLN_LXRZL> findXM_APPLN_LXRZLByCustomerId(String customer_Id){
		return this.xM_APPLN_LXRZL_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_ZXQSZL findXM_APPLN_ZXQSZLByCustomerId(String customer_Id){
		return this.xM_APPLN_ZXQSZL_Dao.findByCustomerId(customer_Id);
	}
	public XM_APPLN_YWXX findXM_APPLN_YWXXByCustomerId(String customer_Id){
		return this.xM_APPLN_YWXX_Dao.findByCustomerId(customer_Id);
	}
}
