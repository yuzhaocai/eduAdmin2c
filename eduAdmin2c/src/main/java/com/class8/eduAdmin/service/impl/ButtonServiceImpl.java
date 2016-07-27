package com.class8.eduAdmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.dao.ButtonDao;
import com.class8.eduAdmin.model.Button;
import com.class8.eduAdmin.service.IButtonService;

@Service
@Transactional
public class ButtonServiceImpl implements IButtonService {
	
	@Autowired
	private ButtonDao buttonDao;
	
	public int countOfButton() {
		return buttonDao.countOfButton();
	}

	public List<Button> findButtonsPage(Page page) {
		return buttonDao.findButtonsPage(page);
	}
	
	public List<Button> findAllButtons(){
		return null;
	}


}
