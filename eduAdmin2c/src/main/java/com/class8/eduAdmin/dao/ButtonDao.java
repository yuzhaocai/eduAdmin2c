package com.class8.eduAdmin.dao;

import java.util.List;
import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.model.Button;

public interface ButtonDao {

	public int countOfButton();

	public List<Button> findButtonsPage(Page page);

}
