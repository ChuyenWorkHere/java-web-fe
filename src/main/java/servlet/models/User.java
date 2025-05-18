package servlet.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int userId;
	private String password;
	private String gender;
	private String fullname;
	private String phoneNumber;
	private String email;
	private Date createDate;
	private Date modifiedDate;
	private boolean active;
	private String address;
	private int loginCount;
	private Roles role;
}
