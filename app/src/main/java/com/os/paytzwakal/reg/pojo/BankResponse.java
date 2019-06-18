package com.os.paytzwakal.reg.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BankResponse implements Serializable {


    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public static class Response implements Serializable {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
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

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
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


        public static class Datum implements Serializable {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("bank_display_name")
            @Expose
            private String bankDisplayName;
            @SerializedName("bank_name")
            @Expose
            private String bankName;

            public Datum(String id, String bankDisplayName, String bankName) {
                this.id = id;
                this.bankDisplayName = bankDisplayName;
                this.bankName = bankName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBankDisplayName() {
                return bankDisplayName;
            }

            public void setBankDisplayName(String bankDisplayName) {
                this.bankDisplayName = bankDisplayName;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }
        }
    }

}
