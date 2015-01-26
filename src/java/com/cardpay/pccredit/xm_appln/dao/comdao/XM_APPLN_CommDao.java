package com.cardpay.pccredit.xm_appln.dao.comdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardpay.pccredit.product.constant.CustomerConstant;
import com.cardpay.pccredit.product.constant.ProductOperateEnum;
import com.cardpay.pccredit.product.dao.ProductDao;
import com.cardpay.pccredit.product.model.FilterDict;
import com.wicresoft.jrad.base.database.dao.common.CommonDao;

/**
 *
 * @author 谭文华
 *
 * 2014-10-24 下午2:54:08
 */

@Service
public class XM_APPLN_CommDao {
	
	@Autowired
	private CommonDao commonDao;
}
