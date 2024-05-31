package net.lab1024.smartadmin;

import net.lab1024.smartadmin.module.business.param.dao.ParInitialDataAutomationDao;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataAutomationService;
import net.lab1024.smartadmin.module.business.param.service.ParInitialDataLoanDepositService;
import net.lab1024.smartadmin.module.business.param.service.ParPersonnelCostService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmartAdminApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartAdminApplicationTests {
    @Autowired
    private ParInitialDataLoanDepositService parInitialDataLoanDepositService;

    @Autowired
    private ParInitialDataAutomationService parInitialDataAutomationService;

    @Autowired
    private ParInitialDataAutomationDao parInitialDataAutomationDao;

    @Autowired
    private ParPersonnelCostService parPersonnelCostService;
    @Test
    public void test() {
        System.out.println(parPersonnelCostService.queryAll());
    }

}