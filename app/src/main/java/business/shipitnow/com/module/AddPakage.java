package business.shipitnow.com.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddPakage implements Serializable {

    @SerializedName("PackageID")
    private String PackageID;
    @SerializedName("PackageImage")
    private String PackageImage;
    @SerializedName("PackageName")
    private String PackageName;
    @SerializedName("Description")
    private String Description;
    @SerializedName("PickUpLocation")
    private String PickUpLocation;
    @SerializedName("PickupLat")
    private String PickupLat;
    @SerializedName("PickupLong")
    private String PickupLong;
    @SerializedName("DropOffLocation")
    private String DropOffLocation;
    @SerializedName("DropoffLat")
    private String DropoffLat;
    @SerializedName("DropoffLong")
    private String DropoffLong;
    @SerializedName("PackageType")
    private String PackageType;
    @SerializedName("QTY")
    private String QTY;
    @SerializedName("Weight")
    private String Weight;
    @SerializedName("UOM")
    private String UOM;
    @SerializedName("PickUpDate")
    private String PickUpDate;
    @SerializedName("PickUpTime")
    private String PickUpTime;
    @SerializedName("DropOffDate")
    private String DropOffDate;
    @SerializedName("DropOffTime")
    private String DropOffTime;
    @SerializedName("InProcess")
    private String InProcess;
    @SerializedName("IsDelivered")
    private String IsDelivered;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;
    @SerializedName("CourierService")
    private String CourierService;
    @SerializedName("CourierPayment")
    private String CourierPayment;

    public String getPackageImage() {
        return PackageImage;
    }

    public String getPackageName() {
        return PackageName;
    }

    public String getDescription() {
        return Description;
    }

    public String getPickUpLocation() {
        return PickUpLocation;
    }

    public String getPickupLat() {
        return PickupLat;
    }

    public String getPickupLong() {
        return PickupLong;
    }

    public String getDropOffLocation() {
        return DropOffLocation;
    }

    public String getDropoffLat() {
        return DropoffLat;
    }

    public String getDropoffLong() {
        return DropoffLong;
    }

    public String getPackageType() {
        return PackageType;
    }

    public String getQTY() {
        return QTY;
    }

    public String getWeight() {
        return Weight;
    }

    public String getUOM() {
        return UOM;
    }

    public String getPickUpDate() {
        return PickUpDate;
    }

    public String getPickUpTime() {
        return PickUpTime;
    }

    public String getDropOffDate() {
        return DropOffDate;
    }

    public String getDropOffTime() {
        return DropOffTime;
    }

    public String getInProcess() {
        return InProcess;
    }

    public String getIsDelivered() {
        return IsDelivered;
    }

    public String getStatus() {
        return Status;
    }

    public String getMessage() {
        return Message;
    }

    public String getCourierService() {
        return CourierService;
    }

    public String getCourierPayment() {
        return CourierPayment;
    }

    public void setPackageImage(String packageImage) {
        PackageImage = packageImage;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPickUpLocation(String pickUpLocation) {
        PickUpLocation = pickUpLocation;
    }

    public void setPickupLat(String pickupLat) {
        PickupLat = pickupLat;
    }

    public void setPickupLong(String pickupLong) {
        PickupLong = pickupLong;
    }

    public void setDropOffLocation(String dropOffLocation) {
        DropOffLocation = dropOffLocation;
    }

    public void setDropoffLat(String dropoffLat) {
        DropoffLat = dropoffLat;
    }

    public void setDropoffLong(String dropoffLong) {
        DropoffLong = dropoffLong;
    }

    public void setPackageType(String packageType) {
        PackageType = packageType;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public void setPickUpDate(String pickUpDate) {
        PickUpDate = pickUpDate;
    }

    public void setPickUpTime(String pickUpTime) {
        PickUpTime = pickUpTime;
    }

    public void setDropOffDate(String dropOffDate) {
        DropOffDate = dropOffDate;
    }

    public void setDropOffTime(String dropOffTime) {
        DropOffTime = dropOffTime;
    }

    public void setInProcess(String inProcess) {
        InProcess = inProcess;
    }

    public void setIsDelivered(String isDelivered) {
        IsDelivered = isDelivered;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setCourierService(String courierService) {
        CourierService = courierService;
    }

    public void setCourierPayment(String courierPayment) {
        CourierPayment = courierPayment;
    }

    public String getPackageID() {
        return PackageID;
    }

    public void setPackageID(String packageID) {
        PackageID = packageID;
    }

    @Override
    public String toString() {
        return "AddPakage{" +
                "PackageID='" + PackageID + '\'' +
                ", PackageImage='" + PackageImage + '\'' +
                ", PackageName='" + PackageName + '\'' +
                ", Description='" + Description + '\'' +
                ", PickUpLocation='" + PickUpLocation + '\'' +
                ", PickupLat='" + PickupLat + '\'' +
                ", PickupLong='" + PickupLong + '\'' +
                ", DropOffLocation='" + DropOffLocation + '\'' +
                ", DropoffLat='" + DropoffLat + '\'' +
                ", DropoffLong='" + DropoffLong + '\'' +
                ", PackageType='" + PackageType + '\'' +
                ", QTY='" + QTY + '\'' +
                ", Weight='" + Weight + '\'' +
                ", UOM='" + UOM + '\'' +
                ", PickUpDate='" + PickUpDate + '\'' +
                ", PickUpTime='" + PickUpTime + '\'' +
                ", DropOffDate='" + DropOffDate + '\'' +
                ", DropOffTime='" + DropOffTime + '\'' +
                ", InProcess='" + InProcess + '\'' +
                ", IsDelivered='" + IsDelivered + '\'' +
                ", Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                ", CourierService='" + CourierService + '\'' +
                ", CourierPayment='" + CourierPayment + '\'' +
                '}';
    }
}
