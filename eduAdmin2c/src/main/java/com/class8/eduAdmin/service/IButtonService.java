package com.class8.eduAdmin.service;

import java.util.List;
import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.model.Button;

public interface IButtonService {

	public int countOfButton();

	public List<Button> findButtonsPage(Page page);

	public List<Button> findAllButtons();
	
}
