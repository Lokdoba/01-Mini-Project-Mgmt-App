package in.ashokit.service;

import java.util.List;

import in.ashokit.entities.UserAccount;

public interface UserAccountService {
	
	public String saveOrUpdateUserAcc(UserAccount userAcc);
	
	
	public List<UserAccount> getAllUserAccount();
	
	public UserAccount getUserAccount(Integer usrId);
	
	public boolean deleteUserAccount(Integer userId);
	 
	public boolean updateUserAccStatus(Integer userId, String status);









}
