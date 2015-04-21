package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cardpay.pccredit.report.service.StatisticalTableScheduleService;
import com.cardpay.pccredit.system.service.ImportUserData;


/**
 * 
 * @author 季东晓
 *
 * 2014-11-13 下午5:14:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-junit.xml")
public class ImportUserDataTest {
	
	@Autowired
	private ImportUserData importUserData;

	@Test
	public void doTest() {
		try {
			importUserData.insertUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
