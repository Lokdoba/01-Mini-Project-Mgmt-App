package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entities.UserAccount;
import in.ashokit.repository.UserAccountRepo;


@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	private UserAccountRepo userAccRepo;

	@Override
	public String saveOrUpdateUserAcc(UserAccount userAcc) {
		
		Integer userId = userAcc.getUserId();
		//UPSERT (Insert or Update in DB)
		userAccRepo.save(userAcc);
		 
		if(userId == null) {
		    return "User Record Saved";
		} else {
		    return "User Record Updated";
		}

		
		
	}

	@Override
	public List<UserAccount> getAllUserAccount() {
		return userAccRepo.findAll();
	}

	@Override
	public UserAccount getUserAccount(Integer usrId) {
		Optional<UserAccount> findById = userAccRepo.findById(usrId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteUserAccount(Integer userId) {
		boolean existsById = userAccRepo.existsById(userId);
		if(existsById) {
			userAccRepo.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {
	    Optional<UserAccount> optionalUser = userAccRepo.findById(userId);
	    if (optionalUser.isPresent()) {
	        UserAccount user = optionalUser.get();
	        user.setActiveSw(status);
	        userAccRepo.save(user);  // Save updated status
	        return true;
	    }
	    return false;
	}

}
