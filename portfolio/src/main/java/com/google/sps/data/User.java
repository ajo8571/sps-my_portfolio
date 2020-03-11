public class User{
    public String eMail;
    public String nickName;
    public Boolean admin; 

    public User(String eMail,String nickName){
        this.eMail = eMail;
        this.nickName = nickName;
        this.admin = false;
    }

    public User(String eMail,String nickName, Boolean admin){
        this.eMail = eMail;
        this.nickName = nickName;
        this.admin = admin;
    }
}