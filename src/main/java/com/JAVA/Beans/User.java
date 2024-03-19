package com.JAVA.Beans;


public class User {
	 private int id;
	    private String name;
	    private String email;
	    private String password;
	    private String sexe;
	    private String age;
	    private String ville; 
	    private String tel;
	    
	    public User() {
	    	
	    }
	    public User(int id, String name, String email, String password, String sexe, String age, String ville, String tel) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.sexe = sexe;
	        this.age = age;
	        this.ville = ville;
	        this.tel = tel;
	    }
	    

		public User(String name, String email, String password, String sexe, String age, String ville, String tel) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.sexe = sexe;
			this.age = age;
			this.ville = ville;
			this.tel = tel;
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getSexe() {
			return sexe;
		}

		public void setSexe(String sexe) {
			this.sexe = sexe;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}    
   
	
	    
	
    
    }

    
    
