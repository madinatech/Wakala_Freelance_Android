package com.os.paytzwakal.reg.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RegistrationResponse implements Serializable {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private List<Data> data = null;
        @SerializedName("under_maintenance")
        @Expose
        private String underMaintenance;
        @SerializedName("is_deactivate")
        @Expose
        private String isDeactivate;
        @SerializedName("sflag")
        @Expose
        private String sflag;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public String getUnderMaintenance() {
            return underMaintenance;
        }

        public void setUnderMaintenance(String underMaintenance) {
            this.underMaintenance = underMaintenance;
        }

        public String getIsDeactivate() {
            return isDeactivate;
        }

        public void setIsDeactivate(String isDeactivate) {
            this.isDeactivate = isDeactivate;
        }

        public String getSflag() {
            return sflag;
        }

        public void setSflag(String sflag) {
            this.sflag = sflag;
        }

        public class Data {

            @SerializedName("buisness_code")
            @Expose
            private String buisnessCode;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("role_id")
            @Expose
            private Integer roleId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("mobile_number")
            @Expose
            private String mobileNumber;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("device_id")
            @Expose
            private String deviceId;
            @SerializedName("device_type")
            @Expose
            private String deviceType;
            @SerializedName("contact_person")
            @Expose
            private String contactPerson;
            @SerializedName("contact_number")
            @Expose
            private String contactNumber;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("bank_id")
            @Expose
            private String bankId;
            @SerializedName("beneficiary_name")
            @Expose
            private String beneficiaryName;
            @SerializedName("bank_account_number")
            @Expose
            private String bankAccountNumber;
            @SerializedName("activation_key")
            @Expose
            private String activationKey;
            @SerializedName("pin")
            @Expose
            private String pin;
            @SerializedName("created")
            @Expose
            private String created;
            @SerializedName("modified")
            @Expose
            private String modified;
            @SerializedName("reference_number")
            @Expose
            private String referenceNumber;
            @SerializedName("bank_name")
            @Expose
            private String bankName;
            @SerializedName("bank_ifsc")
            @Expose
            private String bankIfsc;
            @SerializedName("wakala_id")
            @Expose
            private String wakalaId;
            @SerializedName("image_before_url")
            @Expose
            private String imageBeforeUrl;
            @SerializedName("email")
            @Expose
            private String email;

            public String getBuisnessCode() {
                return buisnessCode;
            }

            public void setBuisnessCode(String buisnessCode) {
                this.buisnessCode = buisnessCode;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Integer getRoleId() {
                return roleId;
            }

            public void setRoleId(Integer roleId) {
                this.roleId = roleId;
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

            public String getMobileNumber() {
                return mobileNumber;
            }

            public void setMobileNumber(String mobileNumber) {
                this.mobileNumber = mobileNumber;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getContactPerson() {
                return contactPerson;
            }

            public void setContactPerson(String contactPerson) {
                this.contactPerson = contactPerson;
            }

            public String getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(String contactNumber) {
                this.contactNumber = contactNumber;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getBankId() {
                return bankId;
            }

            public void setBankId(String bankId) {
                this.bankId = bankId;
            }

            public String getBeneficiaryName() {
                return beneficiaryName;
            }

            public void setBeneficiaryName(String beneficiaryName) {
                this.beneficiaryName = beneficiaryName;
            }

            public String getBankAccountNumber() {
                return bankAccountNumber;
            }

            public void setBankAccountNumber(String bankAccountNumber) {
                this.bankAccountNumber = bankAccountNumber;
            }

            public String getActivationKey() {
                return activationKey;
            }

            public void setActivationKey(String activationKey) {
                this.activationKey = activationKey;
            }

            public String getPin() {
                return pin;
            }

            public void setPin(String pin) {
                this.pin = pin;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public String getReferenceNumber() {
                return referenceNumber;
            }

            public void setReferenceNumber(String referenceNumber) {
                this.referenceNumber = referenceNumber;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBankIfsc() {
                return bankIfsc;
            }

            public void setBankIfsc(String bankIfsc) {
                this.bankIfsc = bankIfsc;
            }

            public String getWakalaId() {
                return wakalaId;
            }

            public void setWakalaId(String wakalaId) {
                this.wakalaId = wakalaId;
            }

            public String getImageBeforeUrl() {
                return imageBeforeUrl;
            }

            public void setImageBeforeUrl(String imageBeforeUrl) {
                this.imageBeforeUrl = imageBeforeUrl;
            }

        }
    }
}
