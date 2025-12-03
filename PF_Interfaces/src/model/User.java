package model;

public class User {

	private int idUser;
	private String nickName;
	private String pass;
	private int vecesLoaded;
	private String mail;
	
	public User(int idUser, String nickName, String pass, int vecesLoaded, String mail) {
		this.idUser = idUser;
		this.nickName = nickName;
		this.pass = pass;
		this.vecesLoaded = vecesLoaded;
		this.mail = mail;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getVecesLoaded() {
		return vecesLoaded;
	}
	public void setVecesLoaded(int vecesLoaded) {
		this.vecesLoaded = vecesLoaded;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nickName=" + nickName + ", pass=" + pass + ", vecesLoaded=" + vecesLoaded
				+ ", mail=" + mail + "]";
	}
}
