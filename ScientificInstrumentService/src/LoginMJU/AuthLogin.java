package LoginMJU;

public class AuthLogin {
	private String username;
	private String password;
	private String userrole;
	private boolean result;
	public AuthLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean status) {
		this.result = status;
	}
	
	
}
