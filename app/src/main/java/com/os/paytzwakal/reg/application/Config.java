package com.os.paytzwakal.reg.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class Config {
    private static final String PREFERENCES = "preferences_paytzwakala";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static String contactNumber;

    public static void init(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void setAndroidVeriosn(String androidVersion) {
        editor.putString("androidVersion", androidVersion).apply();
    }

    public static String getAndroidVerion() {
        return preferences.getString("androidVersion", "");
    }

    public static String getPVV() {
        return preferences.getString("pvv", null);
    }

    public static void setPVV(String userToken) {
        editor.putString("pvv", userToken).apply();
    }

    public static String getAddMoneyToken() {
        return preferences.getString("add_money_token", null);
    }

    public static void setAddMoneyToken(String userToken) {
        editor.putString("add_money_token", userToken).apply();
    }

    public static boolean isCameraOn() {
        return preferences.getBoolean("camera_on", false);
    }

    public static void setCameraOn(boolean camera_on) {
        editor.putBoolean("camera_on", camera_on).apply();
    }

    public static boolean isBiomatric() {
        return preferences.getBoolean("bio_matric", true);
    }

    public static void setBioMatric(boolean bio_matric) {
        editor.putBoolean("bio_matric", bio_matric).apply();
    }

    public static String getUserToken() {
        return preferences.getString("userToken", null);
    }

    public static void setUserToken(String userToken) {
        editor.putString("userToken", userToken).apply();
    }

    public static int getSlideMenuItem() {
        return preferences.getInt("SlideMenuItem", -1);
    }

    public static void setSlideMenuItem(int userToken) {
        editor.putInt("SlideMenuItem", userToken).apply();
    }

    public static String getAndroidUrl() {
        return preferences.getString("androidUrl", "");
    }

    public static void setAndroidUrl(String androidUrl) {
        editor.putString("androidUrl", androidUrl).apply();

    }

    public static String getLongitude() {
        return preferences.getString("Longitude", null);
    }

    public static void setLongitude(String longitude) {
        editor.putString("Longitude", longitude).apply();
    }

    public static String getLatitude() {
        return preferences.getString("Latitude", null);
    }

    public static void setLatitude(String lat) {
        editor.putString("Latitude", lat).apply();
    }

    public static String getUserId() {
        return preferences.getString("user_id", "");
    }

    public static void setUserId(String user_id) {
        editor.putString("user_id", user_id).apply();
    }

    public static String getMD5Encode() {
        return preferences.getString("md_five", "");
    }

    public static void setMD5Encode(String md) {
        editor.putString("md_five", md).apply();
    }

    public static String getIsfirstlogin() {
        return preferences.getString("is_first_login", "");
    }

    public static void setIsfirstlogin(String is_first_login) {
        editor.putString("is_first_login", is_first_login).apply();
    }

    public static String getSocialId() {
        return preferences.getString("SocialId", null);
    }

    public static void setSocialId(String SocialId) {
        editor.putString("SocialId", SocialId).apply();
    }

    public static String getMobileNumber() {
        return preferences.getString("mobile_number", "");
    }

    public static void setMobileNumber(String mobile) {
        editor.putString("mobile_number", mobile).apply();
    }


    public static String getCountryCode() {
        return preferences.getString("CountryCode", "");
    }

    public static void setCountryCode(String CountryCode) {
        editor.putString("CountryCode", CountryCode).apply();
    }


    public static String getIsMobileVerified() {
        return preferences.getString("is_mobile_verified", "");
    }

    public static void setIsMobileVerified(String CountryCode) {
        editor.putString("is_mobile_verified", CountryCode).apply();
    }

    public static String getFirstName() {
        return preferences.getString("first_name", null);
    }

    public static void setFirstName(String first_name) {
        editor.putString("first_name", first_name).apply();
    }

    public static String getRememberToken() {
        return preferences.getString("remember_token", null);
    }

    public static void setRememberToken(String remember_token) {
        editor.putString("remember_token", remember_token).apply();
    }

    public static String getLastName() {
        return preferences.getString("last_name", null);
    }

    public static void setLastName(String last_name) {
        editor.putString("last_name", last_name).apply();
    }

    public static String getLanguage() {
        return preferences.getString("language", "eng");
    }

    public static void setLanguage(String language) {
        editor.putString("language", language).apply();
    }

    public static String getUserName() {
        return preferences.getString("username", "");
    }

    public static void setUserName(String username) {
        editor.putString("username", username).apply();
    }

    public static String getProfileCheck() {
        return preferences.getString("profile_check", "");
    }

    public static void setProfileCheck(String username) {
        editor.putString("profile_check", username).apply();
    }

    public static String getNotificationManager() {
        return preferences.getString("noty_boy", "");
    }

    public static void setNotificationManager(String username) {
        editor.putString("noty_boy", username).apply();
    }

    public static String getEmail() {
        return preferences.getString("email", "");
    }

    public static void setEmail(String email) {
        editor.putString("email", email).apply();
    }

    public static String getLoginType() {
        return preferences.getString("login_type", null);
    }

    public static void setLoginType(String login_type) {
        editor.putString("login_type", login_type).apply();
    }

    public static int getNotificationCount() {
        return preferences.getInt("notification_count", 0);
    }

    public static void setNotificationCount(int login_type) {
        editor.putInt("notification_count", login_type).apply();
    }

    public static int getNotificationRead() {
        return preferences.getInt("notification_read", 0);
    }

    public static void setNotificationRead(int login_type) {
        editor.putInt("notification_read", login_type).apply();
    }

    public static int getMessageRead() {
        return preferences.getInt("message_read", 0);
    }

    public static void setMessageRead(int login_type) {
        editor.putInt("message_read", login_type).apply();
    }

    public static String getCity() {
        return preferences.getString("city", null);
    }

    public static void setCity(String login_type) {
        editor.putString("city", login_type).apply();
    }

    public static boolean getELocate() {
        return preferences.getBoolean("locate", true);
    }

    public static void setELocate(boolean locate) {
        editor.putBoolean("locate", locate).apply();
    }

    public static boolean getGreeting() {
        return preferences.getBoolean("Greeting", true);
    }

    public static void setGreeting(boolean Greeting) {
        editor.putBoolean("Greeting", Greeting).apply();
    }

    public static boolean getNotificationClicks() {
        return preferences.getBoolean("notification_click", true);
    }

    public static void setNotificationClicks(boolean Greeting) {
        editor.putBoolean("notification_click", Greeting).apply();
    }

    public static boolean getNotifyClick() {
        return preferences.getBoolean("click", true);
    }

    public static void setNotifyClick(boolean Greeting) {
        editor.putBoolean("click", Greeting).apply();
    }

    public static long getLastTime() {
        return preferences.getLong("lasttime", 0);
    }

    public static void setLastTime(long time) {
        editor.putLong("lasttime", time).apply();
    }

    public static long getLastTimeTwo() {
        return preferences.getLong("lasttimetwo", 0);
    }

    public static void setLastTimeTwo(long time) {
        editor.putLong("lasttimetwo", time).apply();
    }

    public static long getLogOutTime() {
        return preferences.getLong("logouttime", 60000);
    }

    public static void setLogOutTime(long time) {
        editor.putLong("logouttime", time).apply();
    }

    public static String getAppRunning() {
        return preferences.getString("app_running", "running");
    }

    public static void setAppRunning(String login_type) {
        editor.putString("app_running", login_type).apply();
    }

    public static String getAddress() {
        return preferences.getString("address", null);
    }

    public static void setAddress(String login_type) {
        editor.putString("address", login_type).apply();
    }

    public static String getDefaultImg() {
        return preferences.getString("img", "");
    }

    public static void setDefaultImg(String img) {
        editor.putString("img", img).apply();
    }

    public static String getDocFileImg() {
        return preferences.getString("doc_img", "");
    }

    public static void setDocFileImg(String img) {
        editor.putString("doc_img", img).apply();
    }

    public static boolean getLogoutStatus() {
        return preferences.getBoolean("status", false);
    }

    public static void setLogoutStatus(boolean img) {
        editor.putBoolean("status", img).apply();
    }

    public static String getGender() {
        return preferences.getString("gender", "");
    }

    public static void setGender(String first_name) {
        editor.putString("gender", first_name).apply();
    }

    public static String getDocVerified() {
        return preferences.getString("doc_verified", "");
    }

    public static void setDocVerified(String first_name) {
        editor.putString("doc_verified", first_name).apply();
    }


    public static String getKycVerified() {
        return preferences.getString("kyc_verified", "");
    }

    public static void setKycVerified(String first_name) {
        editor.putString("kyc_verified", first_name).apply();
    }

    public static String getHalotelPrivateKey() {
        return preferences.getString("halotel_private_key", "");
    }

    public static void setHalotelPrivateKey(String first_name) {
        editor.putString("halotel_private_key", first_name).apply();
    }

    public static String getHalotelClientId() {
        return preferences.getString("halotel_client_id", "");
    }

    public static void setHalotelClientId(String first_name) {
        editor.putString("halotel_client_id", first_name).apply();
    }


    public static String getMemberSince() {
        return preferences.getString("member_since", "");
    }

    public static void setMemberSince(String first_name) {
        editor.putString("member_since", first_name).apply();
    }

    public static String getAge() {
        return preferences.getString("age", "");
    }

    public static void setAge(String first_name) {
        editor.putString("age", first_name).apply();
    }

    public static String getDateOfBirth() {
        return preferences.getString("dateofbirth", "");
    }

    public static void setDateOfBirth(String first_name) {
        editor.putString("dateofbirth", first_name).apply();
    }

    public static int getDisable_flag() {
        return preferences.getInt("disable_flag", 0);
    }

    public static void setDisable_flag(int login_type) {
        editor.putInt("disable_flag", login_type).apply();
    }

    public static String getDisable_service_pay_at_store() {
        return preferences.getString("disable_service_pay_at_store", "");
    }

    public static void setDisable_service_pay_at_store(String disable_service_pay_at_store) {
        editor.putString("disable_service_pay_at_store", disable_service_pay_at_store).apply();
    }

    public static String getDisable_service_online_shopping() {
        return preferences.getString("disable_service_online_shopping", "");
    }

    public static void setDisable_service_online_shopping(String disable_service_online_shopping) {
        editor.putString("disable_service_online_shopping", disable_service_online_shopping).apply();
    }

    public static String getDisable_service_wallet_to_wallet() {
        return preferences.getString("disable_service_wallet_to_wallet", "");
    }

    public static void setDisable_service_wallet_to_wallet(String disable_service_wallet_to_wallet) {
        editor.putString("disable_service_wallet_to_wallet", disable_service_wallet_to_wallet).apply();
    }

    public static String getDisable_service_bank_transfer() {
        return preferences.getString("disable_service_bank_transfer", "");
    }

    public static void setDisable_service_bank_transfer(String disable_service_bank_transfer) {
        editor.putString("disable_service_bank_transfer", disable_service_bank_transfer).apply();
    }

    public static String getDisable_service_mobile_recharge() {
        return preferences.getString("disable_service_mobile_recharge", "");
    }

    public static void setDisable_service_mobile_recharge(String disable_service_mobile_recharge) {
        editor.putString("disable_service_mobile_recharge", disable_service_mobile_recharge).apply();
    }

    public static String getDisable_service_electricity() {
        return preferences.getString("disable_service_electricity", "");
    }

    public static void setDisable_service_electricity(String disable_service_electricity) {
        editor.putString("disable_service_electricity", disable_service_electricity).apply();
    }

    public static String getDisable_all_offers() {
        return preferences.getString("disable_all_offers", "");
    }

    public static void setDisable_all_offers(String disable_all_offers) {
        editor.putString("disable_all_offers", disable_all_offers).apply();
    }

    public static String getDisable_offer_promo_code() {
        return preferences.getString("disable_offer_promo_code", "");
    }

    public static void setDisable_offer_promo_code(String disable_offer_promo_code) {
        editor.putString("disable_offer_promo_code", disable_offer_promo_code).apply();
    }

    public static String getDisable_offer_promo_code_general() {
        return preferences.getString("disable_offer_promo_code_general", "");
    }

    public static void setDisable_offer_promo_code_general(String disable_offer_promo_code_general) {
        editor.putString("disable_offer_promo_code_general", disable_offer_promo_code_general).apply();
    }

    public static String getDisable_offer_promo_code_deposit() {
        return preferences.getString("disable_offer_promo_code_deposit", "");
    }

    public static void setDisable_offer_promo_code_deposit(String disable_offer_promo_code_deposit) {
        editor.putString("disable_offer_promo_code_deposit", disable_offer_promo_code_deposit).apply();
    }

    public static String getDisable_offer_promo_code_merchant() {
        return preferences.getString("disable_offer_promo_code_merchant", "");
    }

    public static void setDisable_offer_promo_code_merchant(String disable_offer_promo_code_merchant) {
        editor.putString("disable_offer_promo_code_merchant", disable_offer_promo_code_merchant).apply();
    }

    public static String getDisable_offer_discount() {
        return preferences.getString("disable_offer_discount", "");
    }

    public static void setDisable_offer_discount(String disable_offer_discount) {
        editor.putString("disable_offer_discount", disable_offer_discount).apply();
    }

    public static String getDisable_offer_topup() {
        return preferences.getString("disable_offer_topup", "");
    }

    public static void setDisable_offer_topup(String disable_offer_topup) {
        editor.putString("disable_offer_topup", disable_offer_topup).apply();
    }


    public static String getDisable_service_add_money() {
        return preferences.getString("disable_service_add_money", "");
    }

    public static void setDisable_service_add_money(String disable_service_add_money) {
        editor.putString("disable_service_add_money", disable_service_add_money).apply();
    }


    public static String getPhoneBook() {
        return preferences.getString("phonebook", "");
    }

    public static void setPhoneBook(String first_name) {
        editor.putString("phonebook", first_name).apply();
    }

    public static String getRegisterUser() {
        return preferences.getString("registeruser", "");
    }

    public static void setRegisterUser(String first_name) {
        editor.putString("registeruser", first_name).apply();
    }

    public static List<String> getAirtelList() {
        Set<String> set = preferences.getStringSet("airtelList", null);
        return new ArrayList<String>(set);
    }

    public static void setAirtelList(List<String> airtelList) {
        Set<String> set = new HashSet<String>();
        airtelList.addAll(airtelList);
        editor.putStringSet("airtelList", set).apply();
    }

    public static List<String> getAirtelMoney() {
        Set<String> set = preferences.getStringSet("airtel_money", null);
        return new ArrayList<String>(set);
    }

    public static void setAirtelMoney(List<String> vodacomList) {
        Set<String> set = new HashSet<String>();
        set.addAll(vodacomList);
        editor.putStringSet("airtel_money", set).apply();
    }

    public static List<String> getVodacomList() {
        Set<String> set = preferences.getStringSet("VodacomList", null);
        return new ArrayList<String>(set);
    }

    public static void setVodacomList(List<String> vodacomList) {
        Set<String> set = new HashSet<String>();
        set.addAll(vodacomList);
        editor.putStringSet("VodacomList", set).apply();
    }

    public static List<String> getTigoPesaList() {
        Set<String> set = preferences.getStringSet("TigoPesaList", null);
        return new ArrayList<String>(set);
    }

    public static void setTigoPesaList(List<String> tigoPesaList) {
        Set<String> set = new HashSet<String>();
        set.addAll(tigoPesaList);
        editor.putStringSet("TigoPesaList", set).apply();
    }

    public static List<String> getNMBBranchList() {
        Set<String> set = preferences.getStringSet("NMBBranchList", null);
        return new ArrayList<String>(set);
    }

    public static void setNMBBranchList(List<String> nmbBranchList) {
        Set<String> set = new HashSet<String>();
        set.addAll(nmbBranchList);
        editor.putStringSet("NMBBranchList", set).apply();
    }

    public static List<String> getNMBWakalaList() {
        Set<String> set = preferences.getStringSet("NMBWakalaList", null);
        return new ArrayList<String>(set);
    }

    public static void setNMBWakalaList(List<String> wakalaList) {
        Set<String> set = new HashSet<String>();
        set.addAll(wakalaList);
        editor.putStringSet("NMBWakalaList", set).apply();
    }

    public static List<String> getNMBMobileList() {
        Set<String> set = preferences.getStringSet("NMBMobileList", null);
        return new ArrayList<String>(set);
    }

    public static void setNMBMobileList(List<String> nmbMobileList) {
        Set<String> set = new HashSet<String>();
        set.addAll(nmbMobileList);
        editor.putStringSet("NMBMobileList", set).apply();
    }

    public static List<String> getHaloList() {
        Set<String> set = preferences.getStringSet("HaloList", null);
        return new ArrayList<String>(set);
    }

    public static void setHaloList(List<String> haloList) {
        Set<String> set = new HashSet<String>();
        set.addAll(haloList);
        editor.putStringSet("HaloList", set).apply();
    }

    public static String getDisable_service_vodacom_airtime() {
        return preferences.getString("disable_service_vodacom_airtime", "");
    }

    public static void setDisable_service_vodacom_airtime(String disableServiceVodacomAirtime) {
        editor.putString("disableServiceVodacomAirtime", disableServiceVodacomAirtime).apply();
    }

    public static String getDisable_service_vodacom_bundle() {
        return preferences.getString("disable_service_vodacom_bundle", "");
    }

    public static void setDisable_service_vodacom_bundle(String disable_offer_topup) {
        editor.putString("disable_service_vodacom_bundle", disable_offer_topup).apply();
    }

    public static String getDisable_service_halotel() {
        return preferences.getString("disable_service_halotel", "");
    }

    public static void setDisable_service_halotel(String disable_offer_topup) {
        editor.putString("disable_service_halotel", disable_offer_topup).apply();
    }

    public static String getDisable_service_ttcl() {
        return preferences.getString("disable_service_ttcl", "");
    }

    public static void setDisable_service_ttcl(String disable_offer_topup) {
        editor.putString("disable_service_ttcl", disable_offer_topup).apply();
    }

    public static String getDisable_service_tego() {
        return preferences.getString("disable_service_tego", "");
    }

    public static void setDisable_service_tego(String disable_offer_topup) {
        editor.putString("disable_service_tego", disable_offer_topup).apply();
    }

    public static String getDisable_service_zentel() {
        return preferences.getString("disable_service_zentel", "");
    }

    public static void setDisable_service_zentel(String disable_offer_topup) {
        editor.putString("disable_service_zentel", disable_offer_topup).apply();
    }

    public static void savePreferences() {
        editor.commit();
    }

    public static void clearPreferences() {
        editor.clear();
        savePreferences();
    }

    public static void removeValueForKey(String key) {
        if (!TextUtils.isEmpty(key)) {
            editor.remove(key);
            savePreferences();
        }

    }

    public static void setKycLevel(String kycLevel) {
        editor.putString("kyc_level", kycLevel).apply();
    }

    public static String getkyc_level() {
        return preferences.getString("kyc_level", "");
    }

    public static String getAgentId() {
        return preferences.getString("agentId", "");
    }

    public static void setAgentId(String agentId) {
        editor.putString("agentId", agentId).apply();
    }

    public static String getRefernaceNumer() {
        return preferences.getString("referenceNumber", "");
    }

    public static void setRefernaceNumer(String referenceNumber) {
        editor.putString("referenceNumber", referenceNumber).apply();
    }

    public static String getContactPerson() {
        return preferences.getString("contactPerson", "");
    }

    public static void setContactPerson(String contactPerson) {
        editor.putString("contactPerson", contactPerson).apply();
    }

    public static String getWalletAmount() {
        return preferences.getString("walletAmount", "");
    }

    public static void setWalletAmount(String walletAmount) {
        editor.putString("walletAmount", walletAmount).apply();
    }

    public static String getBankId() {
        return preferences.getString("bankId", "");
    }

    public static void setBankId(String bankId) {
        editor.putString("bankId", bankId).apply();
    }

    public static void setbeneficiaryName(String beneficiaryName) {
        editor.putString("beneficiaryName", beneficiaryName).apply();
    }

    public static String getbeneficiaryName() {
        return preferences.getString("beneficiaryName", "");
    }

    public static String getBankAccountNumber() {
        return preferences.getString("bankAccountNumber", "");
    }

    public static void setBankAccountNumber(String bankAccountNumber) {
        editor.putString("bankAccountNumber", bankAccountNumber).apply();

    }

    public static String getBankName() {
        return preferences.getString("bankName", "");
    }

    public static void setBankName(String bankName) {
        editor.putString("bankName", bankName).apply();

    }

    public static String getBankIfsc() {
        return preferences.getString("bankIfsc", "");
    }

    public static void setBankIfsc(String bankIfsc) {
        editor.putString("bankIfsc", bankIfsc).apply();

    }

    public static String getQrcode() {
        return preferences.getString("qrCode", "");
    }

    public static void setQrcode(String qrCode) {
        editor.putString("qrCode", qrCode).apply();

    }

    public static String getIsNotification() {
        return preferences.getString("isNotification", "");
    }

    public static void setIsNotification(String isNotification) {
        editor.putString("isNotification", isNotification).apply();

    }

    public static String getModified() {
        return preferences.getString("modified", "");
    }

    public static void setModified(String modified) {
        editor.putString("modified", modified).apply();
    }

    public static String getNonKycUserLimit() {
        return preferences.getString("nonKycUserLimit", "");
    }

    public static void setNonKycUserLimit(String nonKycUserLimit) {
        editor.putString("nonKycUserLimit", nonKycUserLimit).apply();

    }

    public static void setkycUserLimit(String kycUserLimit) {
        editor.putString("kycUserLimit", kycUserLimit).apply();
    }

    public static String getkycUserLimit() {
        return preferences.getString("kycUserLimit", "");
    }

    public static String getNextkycLimit() {
        return preferences.getString("nextKycLimit", "");
    }

    public static void setNextkycLimit(String nextKycLimit) {
        editor.putString("nextKycLimit", nextKycLimit).apply();
    }

    public static String getNextkycUserLimit() {
        return preferences.getString("kycUserLimit", "");
    }

    public static void setNextkycUserLimit(String kycUserLimit) {
        editor.putString("kycUserLimit", kycUserLimit).apply();
    }

    public static String getDailyTransactionLimit() {
        return preferences.getString("dailyTransactionLimit", "");
    }

    public static void setDailyTransactionLimit(String dailyTransactionLimit) {
        editor.putString("dailyTransactionLimit", dailyTransactionLimit).apply();
    }

    public static String getWakalaSendMoneyComission() {
        return preferences.getString("wakalaSendMoneyComission", "");
    }

    public static void setWakalaSendMoneyComission(String wakalaSendMoneyComission) {
        editor.putString("wakalaSendMoneyComission", wakalaSendMoneyComission).apply();
    }

    public static String getWakalaAirtel() {
        return preferences.getString("wakalaAirtel", "");
    }

    public static void setWakalaAirtel(String wakalaAirtel) {
        editor.putString("wakalaAirtel", wakalaAirtel).apply();
    }

    public static String getWakalaHalotel() {
        return preferences.getString("wakalaHalotel", "");
    }

    public static void setWakalaHalotel(String wakalaHalotel) {
        editor.putString("wakalaHalotel", wakalaHalotel).apply();
    }

    public static String getWakalaTtcl() {
        return preferences.getString("wakalaTtcl", "");
    }

    public static void setWakalaTtcl(String wakalaTtcl) {
        editor.putString("wakalaTtcl", wakalaTtcl).apply();
    }

    public static void setWakalaVodacom(String wakalaVodacom) {
        editor.putString("wakalaVodacom", wakalaVodacom).apply();
    }

    public static String getWaklaVodacom() {
        return preferences.getString("wakalaVodacom", "");
    }

    public static String getWakalaMobistok() {
        return preferences.getString("wakalaMobistock", "");
    }

    public static void setWakalaMobistok(String wakalaMobistock) {
        editor.putString("wakalaMobistock", wakalaMobistock).apply();
    }

    public static void seWakalaLuku(String wakalaLuku) {
        editor.putString("wakalaLuku", wakalaLuku).apply();
    }

    public static String geWakalaLuku() {
        return preferences.getString("wakalaLuku", "");
    }

    public static String getUnreadNotification() {
        return preferences.getString("unreadNotification", "");
    }

    public static void setUnreadNotification(Integer unreadNotification) {
        editor.putString("unreadNotification", unreadNotification.toString()).apply();
    }

    public static void removeAll() {
        editor.clear();
        editor.apply();
    }

    public static String getContactNumber() {
        return preferences.getString("contactNumber", "");
    }

    public static void setContactNumber(String contactNumber) {
        editor.putString("contactNumber", contactNumber).apply();
    }
}
