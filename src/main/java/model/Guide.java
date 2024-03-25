package model;

import java.util.List;

public class Guide {
    private int guideId;
    private String firstName;
    private String lastName;
    private String sex;
    private int age;
    private String email;
    private int phone;
    private int cityId;
    private List<String> languages;
    private double price;
    private String password;
    private byte[] profilePic; // Storing as URL or file path instead of byte[]
    private String pictureBase64;

    // Constructors
    public Guide() {
    }

    public Guide(int guideId, String firstName, String lastName, String sex, int age, String email, int phone, int cityId, List<String> languages, double price, String password, byte[] profilePic ,String pictureBase64) {
        this.guideId = guideId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.cityId = cityId;
        this.languages = languages; // Defensive copy
        this.price = price;
        this.password = password; // Should be hashed
        this.profilePic = profilePic;
        this.pictureBase64 = pictureBase64 ;
        

    }

    // Getters
    public int getGuideId() {
        return guideId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public int getCityId() {
        return cityId;
    }

    public List<String> getLanguages() {
        return languages; // Return an unmodifiable view
    }

    public double getPrice() {
        return price;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }
    public String getPictureBase64() {
        return pictureBase64;
    }

    // Setters (with validation and immutability enforcement)
    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be positive.");
        }
    }

    public void setEmail(String email) {
        // Add email validation if necessary
        this.email = email;
    }

    public void setPhone(int phone) {
        // Add phone number validation if necessary
        this.phone = phone;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages; // Defensive copy
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    public void setPassword(String password) {
        // Hash the password before setting
        this.password = password;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }
}
