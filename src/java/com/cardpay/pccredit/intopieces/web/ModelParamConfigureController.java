package com.cardpay.pccredit.intopieces.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.intopieces.filter.ModelParamFilter;
import com.cardpay.pccredit.intopieces.model.ModelParamConfigure;
import com.cardpay.pccredit.intopieces.model.ModelParamConfigureForm;
import com.cardpay.pccredit.intopieces.service.XmNewTeService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.constant.JRadConstants;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

/**
 * @author 宋辰
 * @created on 2015-11-12
 * @version $Id:$
 */
@Controller
@RequestMapping("/product/modelparamconfigure/*")
@JRadModule("product.modelparamconfigure")
public class ModelParamConfigureController extends BaseController {

	@Autowired
	private XmNewTeService xmNewTeService;
	
	
	/**
	 * 浏览页面
	 * @param filter
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute ModelParamFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		QueryResult<ModelParamConfigure> result = xmNewTeService.findModelParamFilter(filter);
		JRadPagedQueryResult<ModelParamConfigure> pagedResult = new JRadPagedQueryResult<ModelParamConfigure>(filter, result);
		JRadModelAndView mv = new JRadModelAndView("/product/model_param_configure", request);
		mv.addObject(PAGED_RESULT, pagedResult);
		return mv;
	}

	/**
	 * 跳转到增加页面
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "create.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView create(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/model_param_configure_create", request);

		return mv;
	}
	
	
	/**
	 * 执行属性添加
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insert.json", method = { RequestMethod.POST })
	@JRadOperation(JRadOperation.CREATE)
	public JRadReturnMap insert(@ModelAttribute ModelParamConfigureForm form, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				 IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
				 String loginId = user.getId();
				 ModelParamConfigure model = form.createModel( ModelParamConfigure.class);
				 model.setCreatedBy(loginId);
				 model.setCreatedTime(new Date());
				 String id = xmNewTeService.insertModelParam(model);
				returnMap.put(RECORD_ID, id);
				returnMap.put(MESSAGE, "添加成功");
			} catch (Exception e) {
				e.printStackTrace();
				return WebRequestHelper.processException(e);

			}
		}
		return returnMap;
	}
	
	
	/**
	 * 修改属性页面
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "change.page")
	@JRadOperation(JRadOperation.CHANGE)
	public AbstractModelAndView change(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/product/model_param_configure_update", request);
		String id = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(id)) {
			ModelParamConfigure modelParamConfigure = xmNewTeService.findModelParamConfigureById(id);
			mv.addObject("model", modelParamConfigure);
			mv.addObject("id", id);
		}
		return mv;
	}
	
	
	/**
	 * 执行修改属性
	 * @param productAttributeForm
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update.json", method = { RequestMethod.POST })
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap update(@ModelAttribute ModelParamConfigureForm form, HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		if (returnMap.isSuccess()) {
			try {
				IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
				String loginId = user.getId();
				ModelParamConfigure modelParamConfigure = form.createModel(ModelParamConfigure.class);
				modelParamConfigure.setModifiedBy(loginId);
				modelParamConfigure.setModifiedTime(new Date());
				int i = xmNewTeService.updateModelParamConfigure(modelParamConfigure);
				returnMap.put(MESSAGE, "修改成功");
			} catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}

		return returnMap;
	}
	
	/**
	 * 执行删除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete.json")
	@JRadOperation(JRadOperation.DELETE)
	public JRadReturnMap delete(HttpServletRequest request) {
		JRadReturnMap returnMap = new JRadReturnMap();
		try {
			String id = RequestHelper.getStringValue(request, ID);
			xmNewTeService.deleteById(id);
			returnMap.addGlobalMessage(JRadConstants.DELETE_SUCCESS);
		} catch (Exception e) {
			return WebRequestHelper.processException(e);
		}
		return returnMap;
	}
	


}
