package mll.utility;

public class Configuration {
    public final String USER_NAME_FOR_EMAIL = "medialicensinglab@gmail.com";
    public final String PASSWOD_FOR_EMAIL = "mll@team19";
    public final String EMAIL_ADDRESS_FOR_SUPPORT = "medialicensinglab@gmail.com";
    
    public final String FROM_EMAIL_ADDRESS = "medialicensinglab@gmail.com";
    public final String FROM_NAME = "Media Team";
    
    
    //RAZUNA Configuration Details
    public final String RAZUNA_URL = "http://35.163.173.243:8080/razuna/raz1/dam/index.cfm";
    public final String Razuna_API_URL="http://35.163.173.243:8080/razuna/global/api2/";
    public final String RAZUNA_KEY = "9F697883197D45EE987BEEB71510EC36";

    //RAZUNA Configuration Details local
    /*public final String RAZUNA_URL = "http://localhost:8080/razuna/raz1/dam/index.cfm";
    public final String Razuna_API_URL="http://localhost:8080/razuna/global/api2/";
    public final String RAZUNA_KEY = "0488EB2CCA804FCCB1ECE1BBED7F91F4";*/
    
    //Custom Fields Asset IDs aws Razuna
    public final String RAZUNA_TITLE="A10E8DDA-7F16-4980-A9877BA0C3F544E0";
    public final String RAZUNA_BITS_PER_RATE="6AA86E27-8297-4C4C-9C9E0565C9E26DC9";
    public final String RAZUNA_COPY_RIGHT_NUMBER="6152EEC1-06B6-4E77-A44C46F3E1B5F77F";
    public final String RAZUNA_COPY_RIGHT_DATE="79286C52-33C8-43E9-97BC3E53B1C51E68";
    public final String RAZUNA_PUBLISHING_COMPNAY="061EA843-8301-4235-9CED0E8A94A59FA2";
    public final String RAZUNA_PRO="D0302D91-E25E-498A-B8B89107CB16F34B";
    public final String RAZUNA_LYRICS="1F9005CA-6094-460C-A5E5C05F7C378FA5";
    public final String RAZUNA_PRIMARY_GENRE="7BBD3E02-D408-4D9D-9129D443394E057A";
    public final String RAZUNA_SEC_GENRE="6C96E45B-18C1-44DA-8A7C46E3CE025569";
    public final String RAZUNA_ARTISTS="F3441433-9F0A-4A8D-963E8892FA6D378E";
    
    //custom Fields Asset Ids local razuna 
    /*public final String RAZUNA_TITLE="B8285E19-AEEC-44E9-9A77BC5A342DFEC0";
    public final String RAZUNA_BITS_PER_RATE="CEE60877-0483-4CB5-BD42B3C3CD669FAD";
    public final String RAZUNA_COPY_RIGHT_NUMBER="763B2AA4-0A45-4F6F-B204305265F24FA0";
    public final String RAZUNA_COPY_RIGHT_DATE="AA0A51E3-1F43-4999-9BEB97FB1D87DB0A";
    public final String RAZUNA_PUBLISHING_COMPNAY="B18DD8C2-28D3-443B-893B831B36069C9A";
    public final String RAZUNA_PRO="A850B9B1-9009-4881-A273F3BC6F58BDAD";
    public final String RAZUNA_LYRICS="00ADDE47-FC23-4852-91E5543CB8F182F1";
    public final String RAZUNA_PRIMARY_GENRE="E56AFF86-20FC-4A54-B1D3FAFBCB6BB7BA";
    public final String RAZUNA_SEC_GENRE="5C24E0AC-96FF-48CF-97BB37F502EEC365";
    public final String RAZUNA_ARTISTS="A5367524-F0C1-4A96-BADDC9E623CA787C";*/
    
    //RAZUNA API METHODS
    public final String Razuna_CREATE_FOLDER_METHOD="folder.cfc?";
    public final String RAZUNA_CUSTOM_FIELD_METHOD="customfield.cfc";
    public final String RAZUNA_ASSET_METHOD="asset.cfc";
    public final String RAZUNA_SEARCH_METHOD="search.cfc";
    
    //Invite mail url
    public final String INVITE_URL= "http://35.163.135.77:8080/MLL/index.html#!/";
    
    //Invite mail url local {{CHANGE THIS FOR YOUR OWN LOCAL INSTALLATION}}
    //public final String INVITE_URL= "http://localhost:8080/MLL/index.html#!/";
}
