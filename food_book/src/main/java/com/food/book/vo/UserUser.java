package com.food.book.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * <p>Title: </p>
 * <p>Description: 用户bean</p>
 * @author mahuayang
 * @2017年9月14日  下午9:31:03
 */
@Component
public class UserUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9003196076333918032L;
	private long  userId;
	private String  userName;
	private String  realName;
	private String  userPassword;
	private String  userDescription;
	private String  userSex;
	private Date  userBirthday;
	private long  userProvince;
	private long  userCity;
	private long  userProvinceNow;
	private long  userCityNow;
	private long  userProfession;
	private String  userValid;
	private String  userEmail;
	private String  userPhone;
	private Date  registerTime;
	private Date  createTime;
	private Date  updateTime;
	private String  memo;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public long getUserProvince() {
		return userProvince;
	}
	public void setUserProvince(long userProvince) {
		this.userProvince = userProvince;
	}
	public long getUserCity() {
		return userCity;
	}
	public void setUserCity(long userCity) {
		this.userCity = userCity;
	}
	public long getUserProvinceNow() {
		return userProvinceNow;
	}
	public void setUserProvinceNow(long userProvinceNow) {
		this.userProvinceNow = userProvinceNow;
	}
	public long getUserCityNow() {
		return userCityNow;
	}
	public void setUserCityNow(long userCityNow) {
		this.userCityNow = userCityNow;
	}
	public long getUserProfession() {
		return userProfession;
	}
	public void setUserProfession(long userProfession) {
		this.userProfession = userProfession;
	}
	public String getUserValid() {
		return userValid;
	}
	public void setUserValid(String userValid) {
		this.userValid = userValid;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
