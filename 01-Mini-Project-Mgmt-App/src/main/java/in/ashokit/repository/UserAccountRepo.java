package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount ,Integer> {



}
