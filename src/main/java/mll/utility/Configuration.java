package mll.utility;

public class Configuration {
    public final String USER_NAME_FOR_EMAIL = "medialicensinglab@gmail.com";
    public final String PASSWOD_FOR_EMAIL = "mll@team19";
    public final String EMAIL_ADDRESS_FOR_SUPPORT = "medialicensinglab@gmail.com";

    public final String FROM_EMAIL_ADDRESS = "medialicensinglab@gmail.com";
    public final String FROM_NAME = "Media Team";

    public final String FEEDBACK_EMAIL_ADDRESS = "northeastern.mll.feedback@gmail.com";

    //RAZUNA Configuration Details
    public final String RAZUNA_URL = "http://35.163.135.77:8081/razuna/raz1/dam/index.cfm";
    public final String RAZUNA_API_URL = "http://35.163.135.77:8081/razuna/global/api2/";
    public final String RAZUNA_KEY = "67CB2FB1C14A47579A18089214A22FFA";

    //RAZUNA Configuration Details local {{CHANGE THESE FOR YOUR OWN LOCAL INSTALLATION}}
    /*public final String RAZUNA_URL = "http://localhost:8080/razuna/raz1/dam/index.cfm";
    public final String RAZUNA_API_URL="http://localhost:8080/razuna/global/api2/";
    public final String RAZUNA_KEY = "1DA1384E62B246B89772B66B30113907";*/

    //Custom Fields Asset IDs aws Razuna
    public final String RAZUNA_TITLE = "01E319D0-48EF-4D05-ACE4B103ED99DF53";
    public final String RAZUNA_BITS_PER_RATE = "A7C4A55A-527E-4768-9B33A3829C6646E1";
    public final String RAZUNA_COPY_RIGHT_NUMBER = "CE24DE8B-1FE8-4BA6-91C36F19D09BA82F";
    public final String RAZUNA_COPY_RIGHT_DATE = "AD0764AC-3021-4E01-994C851F00022892";
    public final String RAZUNA_PUBLISHING_COMPANY = "85D8F4E8-CCE7-44B6-8490715DAC432E71";
    public final String RAZUNA_PRO = "4D67D8AC-4008-4071-B034D5F97075D209";
    public final String RAZUNA_LYRICS = "756DC3F8-3960-4B9E-A596DD2172B0CD7E";
    public final String RAZUNA_PRIMARY_GENRE = "B0B59899-8E0A-4BAE-AD8BF4BE26742367";
    public final String RAZUNA_SEC_GENRE = "7E069440-F3A0-4FB5-A032D6CBD935B87E";
    public final String RAZUNA_ARTISTS = "10E600F2-40CA-4092-86C2752A20E51DA7";

    //custom Fields Asset Ids local razuna {{CHANGE THESE FOR YOUR OWN LOCAL INSTALLATION}}
    /*public final String RAZUNA_TITLE="D7EA5ACF-815E-4505-B604E58AAC12C12B";
    public final String RAZUNA_BITS_PER_RATE="291617DA-9AF7-4FB2-82FD6DF59F4780D3";
    public final String RAZUNA_COPY_RIGHT_NUMBER="CD5B7990-E6B6-491F-BCA8E2CE1839BBD8";
    public final String RAZUNA_COPY_RIGHT_DATE="CADDA8BB-68DC-4480-B4D39EC58D6C0C31";
    public final String RAZUNA_PUBLISHING_COMPNAY="9F08F9E0-ADB8-45DC-B6C68E54B2A0EF6E";
    public final String RAZUNA_PRO="EBD6478A-1902-4B4E-A3739328B329A682";
    public final String RAZUNA_LYRICS="9F64DB66-F55F-48DE-9BAF490DCA8E3406";
    public final String RAZUNA_PRIMARY_GENRE="9DB68243-DA76-439F-B69B6C3353598F6C";
    public final String RAZUNA_SEC_GENRE="F254233C-D8E4-4ED2-B9CE14EF3707B87B";
    public final String RAZUNA_ARTISTS="1D855CD0-A98A-4ECE-BC797390ECBBD478";*/


    //RAZUNA API METHODS
    public final String Razuna_CREATE_FOLDER_METHOD = "folder.cfc?";
    public final String RAZUNA_CUSTOM_FIELD_METHOD = "customfield.cfc";
    public final String RAZUNA_ASSET_METHOD = "asset.cfc";
    public final String RAZUNA_SEARCH_METHOD = "search.cfc";

    //Invite mail url
    public final String INVITE_URL= "http://35.163.135.77:8080/MLL/index.html#!/";

    //Invite mail url local {{CHANGE THIS FOR YOUR OWN LOCAL INSTALLATION}}
    //public final String INVITE_URL = "http://localhost:9000/MLL/index.html#!/";
}
