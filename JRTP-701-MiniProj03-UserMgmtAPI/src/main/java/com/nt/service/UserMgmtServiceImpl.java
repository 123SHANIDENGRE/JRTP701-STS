package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.bindings.ActivateUser;
import com.nt.bindings.LoginCredentials;
import com.nt.bindings.RecoverPassword;
import com.nt.bindings.UserAccount;
import com.nt.entity.UserMaster;
import com.nt.repository.IUserMasterRepository;
import com.nt.utils.EmailsUtils;
	
@Service
public class UserMgmtServiceImpl implements IUserMgmtService {

	@Autowired
	private IUserMasterRepository userMasterRepo;
	
	@Autowired
	private EmailsUtils   emailUtils;
	
	@Autowired
	private Environment env;
	
	private static  Logger  log=LoggerFactory.getLogger(UserMgmtServiceImpl.class);
	

	@Override
	public String registerUser(UserAccount user) throws Exception{
		// convert UserAccount obj data to UserMaster obj (Entity obj) data
		UserMaster master = new UserMaster();
		BeanUtils.copyProperties(user, master);
		
		// set random string of 6 chars as password
		String tempPwd=generateRandomPassword(6);
		master.setPassword(tempPwd);
		master.setActiveSw("InActive");
		// save object
		UserMaster savedMaster = userMasterRepo.save(master);
		//perform send mail operations
		String subject="User Registration Success";
		String body=readEmailMessageBody(env.getProperty("mailbody.registeruser.location"), user.getName(), tempPwd);
		emailUtils.sendEmailMeassage(user.getEmail(), subject, body);
		
		return savedMaster.getUserId() != null ? "User is registered with Id Value=" + savedMaster.getUserId()+
				"check mail for temporary password"	: "Problem is user registration";

	}

	/*
	 * @Override public String activateUserAccount(ActivateUser user) {
	 * 
	 * //COnvert ActivateUser obj to EntityObj(UserMaster obj) data UserMaster
	 * master=new UserMaster(); master.setEmail(user.getEmail());
	 * master.setPassword(user.getTempPassword());
	 * 
	 * //check the record availble by using Email and temp password
	 * Example<UserMaster> example=Example.of(master); //Select * from
	 * JRTP_USER_MASTER where mail=? and password=?; List<UserMaster> list=
	 * userMasterRepo.findAll(example);
	 * 
	 * //if valid email and temp is given then set enduser supplied real password
	 * and active the user if(list.size()!=0) { //get entity object UserMaster
	 * entity=list.get(0); //set confirm password
	 * entity.setPassword(user.getConfirmPassword()); //change the user account
	 * status to active entity.setActive_sw("Active");
	 * 
	 * //update the obj userMasterRepo.save(entity); return
	 * "User is Activated with new Password"; } return
	 * "User is Not Found for Activation"; }
	 */

	@Override
	public String activateUserAccount(ActivateUser user) {

		UserMaster entity = userMasterRepo.findByEmailAndPassword(user.getEmail(), user.getTempPassword());
		if (entity == null) {
			return "User is not found for activation";
		} else {
			// set confirm password
			entity.setPassword(user.getConfirmPassword());
			// set status is active
			entity.setActiveSw("Active");
			// update entity
			userMasterRepo.save(entity);

			return "User is activated with new Password";

		}

	}

	@Override
	public String login(LoginCredentials credentials) {

		// convert LoginCredentials object to UserMaster obj
		UserMaster master = new UserMaster();
		BeanUtils.copyProperties(credentials, master);

		Example<UserMaster> example = Example.of(master);
		List<UserMaster> listUsers = userMasterRepo.findAll(example);

		if (listUsers.size() == 0) {
			return "Invalid Credentials";
		} else {
			// get entity object
			UserMaster entity = listUsers.get(0);
			if (entity.getActiveSw().equalsIgnoreCase("Active")) {
				return "Valid Credentials and Login Successful";

			} else {
				return "User Account is Not Active";

			}

		}

	}

	@Override
	public List<UserAccount> listUsers() {

		// Load All entittes and convert to UserAccount obj
		List<UserAccount> listUsers = userMasterRepo.findAll().stream().map(entity -> {
			UserAccount user = new UserAccount();
			BeanUtils.copyProperties(entity, user);
			return user;

		}).toList();

		return listUsers;

		/*
		 * List<UserMaster> listEntities = userMasterRepo.findAll(); //convert all
		 * entity into UserAccount obj List<UserAccount> listUsers=new ArrayList<>();
		 * listEntities.forEach( entity-> { UserAccount user=new UserAccount();
		 * BeanUtils.copyProperties(entity, user); listUsers.add(user); } );
		 * 
		 * return listUsers;
		 */

	}

	@Override
	public UserAccount showUserByUserId(Integer id) {

		// load the user by finbyId
		Optional<UserMaster> master = userMasterRepo.findById(id);
		UserAccount account = null;
		if (master.isPresent()) {
			account = new UserAccount();
			BeanUtils.copyProperties(master.get(), account);
		}

		return account;
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {

		// load the user by findByNameAndEmail
		UserMaster master = userMasterRepo.findByNameAndEmail(name, email);
		UserAccount account = null;
		if (master != null) {
			account = new UserAccount();
			BeanUtils.copyProperties(master, account);
		}

		return account;
	}

	@Override
	public String updateUser(UserAccount user) {

		// use custam method
		Optional<UserMaster> opt = userMasterRepo.findById(user.getUserId());
		if (opt.isPresent()) {

			UserMaster usermaster = opt.get();
			BeanUtils.copyProperties(user, usermaster);
			userMasterRepo.save(usermaster);
			return "User Details are   updated";
		} else {
			return "User Not found for updation";
		}

	}

	@Override
	public String deleteUserById(Integer id) {
		// load obj
		Optional<UserMaster> opt = userMasterRepo.findById(id);
		if (opt.isPresent()) {
			userMasterRepo.deleteById(id);
			return "User is Deleted";
		} else {
			return "User is Not Found for Deletion";
		}
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		// load obj
		Optional<UserMaster> opt = userMasterRepo.findById(id);
		if (opt.isPresent()) {
			// get Entity
			UserMaster master = opt.get();
			// change the status
			master.setActiveSw(status);
			// update the object
			userMasterRepo.save(master);

			return "User status changed";
		} else {
			return "User is Not Found for changing the status";
		}
	}

	@Override
	public String recoverPassword(RecoverPassword recover)throws Exception {
		// get userMaster obj by name and email
		UserMaster master = userMasterRepo.findByNameAndEmail(recover.getName(), recover.getEmail());
		if (master != null) {
			String pwd = master.getPassword();
			//send the recovered password to the email account
			String subject="mail for password recovery";
			String mailBody=readEmailMessageBody(env.getProperty("mailbody.recoverpwd.location"), recover.getName(), pwd);
			emailUtils.sendEmailMeassage(recover.getEmail(), subject, mailBody);
			
			
			return pwd + "mail is sent having a recover password";
		} else {
			return " User and Email is not Found";
		}

	}

	// helper method for same class
	private String generateRandomPassword(int length) {
		// a list of characters to choose from in form of string
		String AlphaNumericstr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		// creating a StringBUffer size of AlphaNumericStr
		StringBuilder randomWord = new StringBuilder(length);
		int i;
		for (i = 0; i < length; i++) {
			// generating a random number using math.random() (gives psuedo random number
			// 0.0 to 1.0)
			int ch = (int) (AlphaNumericstr.length() * Math.random());
			// adding Random character one by one at the end of randomword
			randomWord.append(AlphaNumericstr.charAt(ch));
		}

		return randomWord.toString();

	}
	
	// helper Method for same class
	private String readEmailMessageBody(String fileName, String fullName, String pwd) throws Exception {
		String mailBody = null;
		String url = "http://localhost:4044/user-api/activate";
		try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {

			// read fileContent to StringBuffer object line by line
			StringBuffer buffer = new StringBuffer();
			String line = null;
			do {
				line = br.readLine();
				if(line!=null)
				buffer.append(line);

			} while (line != null);

			mailBody = buffer.toString();
			mailBody=	mailBody.replace("{FULL-NAME}", fullName);
			mailBody=	mailBody.replace("{PWD}", pwd);
			mailBody=   mailBody.replace("{URL", url);
		} // try
		catch (Exception e) {
			log.error(e.getMessage());
			throw e;

		}

		return mailBody;
	}
	
	
	
}
