package com.foysaldev.loginscreen;

public class User {

    private String email;
    private String fullName;
    private String profession;
    private String workplace;
    private String phone;
    private String password;
    private String sex;
    private String facebook;
    private String twitter;

    public User() {}

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public User(String fullName, String email, String profession,
                String workplace, String phone, String facebook, String twitter) {
        this.email = email;
        this.fullName = fullName;
        this.profession = profession;
        this.workplace = workplace;
        this.phone = phone;
        this.facebook = facebook;
        this.twitter = twitter;
    }

/*    public HashMap<String, Object> getAsMap(){
        HashMap<String, Object> userAsMap = new HashMap<>();
        userAsMap.put("username",username);
        userAsMap.put("password",password);
        userAsMap.put("age",age);
        userAsMap.put("name",name);
        //Add or remove more key value pair
        return userAsMap;
    }*/

    public String getSex() { return sex; }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setWorkplace(String wordplace) {
        this.workplace = wordplace;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfession() {
        return profession;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setSex(String sex) { this.sex = sex; }

    public String getpassword() {
        return password;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}