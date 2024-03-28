package com.JAVA.Beans;

import java.util.List;

public class User {
	 private int id;
	    private String name;
	    private String email;
	    private String password;
	    private String sexe;
	    private String age;
	    private String ville; 
	    private String tel;
	    private byte[] picture;
	    private String pictureBase64;
	    private int tarif;
	    private String langues;
	    public User() {
	    	
	    }
	    public User(int id, String name, String email, String password, String sexe, String age, String ville, String tel,byte[] picture, String langues,int tarif) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.sexe = sexe;
	        this.age = age;
	        this.ville = ville;
	        this.tel = tel;
	        this.picture=picture;
	        this.langues=langues;
	        this.tarif=tarif;
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
		public byte[] getPicture() {
			return picture;
		}
		public void setPicture(byte[] picture) {
			this.picture = picture;
		}
		
		public String getLangue() {
			return langues;
		}
		public void setLangue(String langues) {
			this.langues = langues;
		}
		public String getPictureBase64() {
			return pictureBase64;
		}
		public void setPictureBase64(String pictureBase64) {
			this.pictureBase64 = pictureBase64;
		}
		public int getTarif() {
			return tarif;
		}
		public void setTarif(int tarif) {
			this.tarif = tarif;
		}
		
	
	    
	
    
    }

    
    
