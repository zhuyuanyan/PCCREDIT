package com.cardpay.pccredit.pbccrcReport.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.pbccrcReport.model.ZX_QUERY_RECORD;
import com.cardpay.pccredit.pbccrcReport.service.RhzxService;
import com.cardpay.pccredit.pbccrcReport.util.PbccrcReport;
import com.cardpay.pccredit.pbccrcReport.util.PbocFileReader;
import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.model.CustomerMainManager;
import com.cardpay.pccredit.customer.model.XmZxLogin;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.customer.service.CustomerMainManagerService;
import com.cardpay.pccredit.product.service.ProductService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.jrad.modules.privilege.service.UserService;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;

/**
 * CustomerZXController类的描述
 * 
 * @author 谭文华
 * @created on 2015-01-13
 * 
 * @version $Id:$
 */
@Controller
@RequestMapping("/customer/customerzx/*")
@JRadModule("customer.customerzx")
public class CustomerZXController extends BaseController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerInforService customerInforService;

	@Autowired
	private CustomerMainManagerService customerMainManagerService;

	@Autowired
	private RhzxService rhzxService;
	
	/**
	 * 设置征信查询条件
	 */
	@ResponseBody
	@RequestMapping(value = "conditionRhzx.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.RHZX)
	public AbstractModelAndView conditionRhzx(HttpServletRequest request) {
		String customerId = request.getParameter("customerId");
		CustomerInfor customerInfor = customerInforService.findCustomerInforById(customerId);
		JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/customerzx_condition",request);
		mv.addObject("customerInfor", customerInfor);
		return mv;
	}
	
	/**
	 * 查询人行征信
	 * 
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkRhzx.page", method = { RequestMethod.POST })
	@JRadOperation(JRadOperation.RHZX)
	public AbstractModelAndView checkRhzx(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf8");
			String customerId = request.getParameter("customerId");
			String QueryReason = request.getParameter("QueryReason");
			String QueryType = request.getParameter("QueryType");
			String Vertype = request.getParameter("Vertype");
			String orgId = request.getParameter("orgId");
			
			CustomerInfor customer = customerInforService.findCustomerInforById(customerId);
			IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
			
			//通过机构号获取查询帐号和密码，修改为查询所有的查询账户和密码，随机获取一组
			List<XmZxLogin> list = customerInforService.getLoginByOrg("1");
			//生成list之内的一个随机数
			int k = (int)(Math.random()*(list.size()));
			System.out.println(k);
			String userid = list.get(k).getUserName();
			String passwd = list.get(k).getPassWord();
			
			//新增征信查询记录
			ZX_QUERY_RECORD zxqr = new ZX_QUERY_RECORD();
			zxqr.setCustomer_id(customer.getId());
			zxqr.setOrg_id(user.getOrganization().getId());
			zxqr.setUser_id(user.getLogin());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date now = new Date();
			zxqr.setCreate_datetime(sdf.format(now));
			this.rhzxService.insertZX_QUERY_RECORD(zxqr);
			
			logger.info("人行征信查询---查询用户:"+user.getId()+",客户:"+customer.getChineseName());
			PbccrcReport pbccrcReport = new PbccrcReport();
			String fileFullPath = pbccrcReport.manuProcessPbocCreditInfo(customer.getChineseName(), customer.getCardType(), customer.getCardId(),
					QueryReason,QueryType,Vertype,userid,passwd);
			if(fileFullPath != null){
				//解析htm文件抓取内容并存入数据库
				this.rhzxService.insertOrUpdateZX(customerId, fileFullPath);
				
				JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/rhzx/"+customer+"_"+customer.getCardId(), request);
				mv.addObject("customer", customer);
				return mv;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JRadModelAndView mv = new JRadModelAndView("/customer/customerzx/rhzx/error", request);
		return mv;
	}
}
