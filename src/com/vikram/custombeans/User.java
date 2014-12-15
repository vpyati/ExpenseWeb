package com.vikram.custombeans;

public interface User {
	
	public boolean isValidUser();
	
	public String getName();
	
	public String getEmailAddress();
	
	public User INVALID_USER = new User(){

		@Override
		public boolean isValidUser() {
			return false;
		}

		@Override
		public String getName() {
			return "";
		}

		@Override
		public String getEmailAddress() {
			return "";
		}
		
	};
	
}