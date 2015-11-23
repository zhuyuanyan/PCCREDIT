package com.cardpay.pccredit.tools;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 银行卸数
 * @author 宋辰
 *
 */
@Service
public class ParseBankDataFileSchedulesService {
	public Logger log = Logger.getLogger(ParseBankDataFileSchedulesService.class);
	public String curRemotePath = "";//保存本次remotePath
	
	@Autowired
	private SXykStmtCurService sXykStmtCurService;
	
	
	
	@Scheduled(cron = "0 45 10 * * ?")
	private void dosynchMethod() throws IOException{
		try {
			//台帐部分
			//sXykStmtCurService.saveTzbfDataFile("");
			//交易部分
			//sXykStmtCurService.saveJybfDataFile("");
			//台帐全部
			//sXykStmtCurService.saveTzqlDataFile("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 客户调额信息统计表(月)
	 */
	@Scheduled(cron = "0 29 15 * * ?")
	private void dosynchMethod1() throws IOException{ 
		sXykStmtCurService.saveKuTeTj();
	}
	
	/**
	 * 客户贷款信息统计表(日)
	 */
	@Scheduled(cron = "0 29 15 * * ?")
	private void dosynchMethod2() throws IOException{ 
		sXykStmtCurService.saveKuTeTj();
	}
	
}
