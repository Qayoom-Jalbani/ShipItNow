package business.shipitnow.com.module;

import com.google.gson.annotations.SerializedName;

public class login {

    @SerializedName("AccountId")
    private String AccountId;
    @SerializedName("UserName")
    private String UserName;
    @SerializedName("Password")
    private String Password;
    @SerializedName("FullName")
    private String FullName;
    @SerializedName("IsActive")
    private String IsActive;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("CreatedDateTime")
    private String CreatedDateTime;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDateTime() {
        return CreatedDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        CreatedDateTime = createdDateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "login{" +
                "AccountId='" + AccountId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", FullName='" + FullName + '\'' +
                ", IsActive='" + IsActive + '\'' +
                ", CreatedBy='" + CreatedBy + '\'' +
                ", CreatedDateTime='" + CreatedDateTime + '\'' +
                ", Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
