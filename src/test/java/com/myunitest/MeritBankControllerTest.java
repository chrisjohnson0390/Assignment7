package com.myunitest;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.meritamerica.main.controllers.AccountHolderController;
import com.meritamerica.main.models.AccountHolder;
import com.meritamerica.main.services.AccountHolderService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountHolderController.class)
class MeritBankControllerTest {

	@MockBean
	private AccountHolderService accHolderService;
	
	private List<AccountHolder> a = new ArrayList<>();
	
	@Test
	public void retrieveDetailsForCourse() throws Exception {
		Mockito.when(accHolderService.getAccountHolders()).thenReturn(a);
	}
}