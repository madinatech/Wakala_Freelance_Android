package com.os.paytzwakal.reg.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response implements Serializable {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("is_deactivate")
        @Expose
        private String isDeactivate;
        @SerializedName("under_maintenance")
        @Expose
        private String underMaintenance;
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

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getIsDeactivate() {
            return isDeactivate;
        }

        public void setIsDeactivate(String isDeactivate) {
            this.isDeactivate = isDeactivate;
        }

        public String getUnderMaintenance() {
            return underMaintenance;
        }

        public void setUnderMaintenance(String underMaintenance) {
            this.underMaintenance = underMaintenance;
        }

        public String getSflag() {
            return sflag;
        }

        public void setSflag(String sflag) {
            this.sflag = sflag;
        }

        public class Datum implements Serializable {

            @SerializedName("freelancer_id")
            @Expose
            private String freelancerId;
            @SerializedName("full_name")
            @Expose
            private String fullName;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("mobile_number")
            @Expose
            private String mobileNumber;
            @SerializedName("is_notification")
            @Expose
            private String isNotification;
            @SerializedName("modified")
            @Expose
            private String modified;
            @SerializedName("remember_token")
            @Expose
            private String rememberToken;
            @SerializedName("img")
            @Expose
            private String img;

            public String getFreelancerId() {
                return freelancerId;
            }

            public void setFreelancerId(String freelancerId) {
                this.freelancerId = freelancerId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
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

            public String getIsNotification() {
                return isNotification;
            }

            public void setIsNotification(String isNotification) {
                this.isNotification = isNotification;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public String getRememberToken() {
                return rememberToken;
            }

            public void setRememberToken(String rememberToken) {
                this.rememberToken = rememberToken;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

        }
    }
}
