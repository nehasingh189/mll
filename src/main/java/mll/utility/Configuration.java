package mll.utility;

public class Configuration {
    public final String USER_NAME_FOR_EMAIL = "medialicensinglab@gmail.com";
     public final String
             PASSWOD_FOR_EMAIL = "mll@team19";
    public final String EMAIL_ADDRESS_FOR_SUPPORT = "medialicensinglab@gmail.com";
    public final String FROM_EMAIL_ADDRESS = "medialicensinglab@gmail.com";
    public final String FROM_NAME = "Media Team";

//    public final String USER_NAME_FOR_EMAIL = "singh.ne@husky.neu.edu";
//    public final String PASSWOD_FOR_EMAIL = "Renu12#";
//    public final String EMAIL_ADDRESS_FOR_SUPPORT = "singh.ne@husky.neu.edu";
//    public final String FROM_EMAIL_ADDRESS = "singh.ne@husky.neu.edu";
//    public final String FROM_NAME = "Media Team";
    public final String FEEDBACK_EMAIL_ADDRESS = "northeastern.mll.feedback@gmail.com";

    //RAZUNA Configuration Details
//    public final String RAZUNA_URL = "http://35.163.135.77:8081/razuna/raz1/dam/index.cfm";
//    public final String RAZUNA_API_URL = "http://35.163.135.77:8081/razuna/global/api2/";
//    public final String RAZUNA_KEY = "67CB2FB1C14A47579A18089214A22FFA";


    //RAZUNA Configuration Details :: NEHA
    public final String RAZUNA_URL = "http://128.31.22.157:8081/razuna/raz1/dam/index.cfm";
    public final String RAZUNA_API_URL = "http://128.31.22.157:8081/razuna/global/api2/";
    public final String RAZUNA_KEY = "E756FF1F1E9649D58DDA309324FAC84D";

    //RAZUNA Configuration Details local {{CHANGE THESE FOR YOUR OWN LOCAL INSTALLATION}}
//    public final String RAZUNA_URL = "http://localhost:8080/razuna/raz1/dam/index.cfm";
//    public final String RAZUNA_API_URL="http://localhost:8080/razuna/global/api2/";
//    public final String RAZUNA_KEY = "AD569C89CDE84FA7AA3376831884A3D8";
//
//    //Custom Fields Asset IDs aws Razuna
    //public final String RAZUNA_TITLE = "01E319D0-48EF-4D05-ACE4B103ED99DF53";
    //public final String RAZUNA_BITS_PER_RATE = "A7C4A55A-527E-4768-9B33A3829C6646E1";
    //public final String RAZUNA_COPY_RIGHT_NUMBER = "CE24DE8B-1FE8-4BA6-91C36F19D09BA82F";
    //public final String RAZUNA_COPY_RIGHT_DATE = "AD0764AC-3021-4E01-994C851F00022892";
    //public final String RAZUNA_PUBLISHING_COMPANY = "85D8F4E8-CCE7-44B6-8490715DAC432E71";
    //public final String RAZUNA_PRO = "4D67D8AC-4008-4071-B034D5F97075D209";
    //public final String RAZUNA_LYRICS = "756DC3F8-3960-4B9E-A596DD2172B0CD7E";
    //public final String RAZUNA_PRIMARY_GENRE = "B0B59899-8E0A-4BAE-AD8BF4BE26742367";
    //public final String RAZUNA_SEC_GENRE = "7E069440-F3A0-4FB5-A032D6CBD935B87E";
    //public final String RAZUNA_ARTISTS = "10E600F2-40CA-4092-86C2752A20E51DA7";


    //Custom Fields Asset IDs aws Razuna::: NEHA
    public final String RAZUNA_TITLE = "85EA105F-8897-4D61-9E4D2DF291F47F28";
    public final String RAZUNA_BITS_PER_RATE = "539C34B6-6DA4-4C12-830B74A8E3929D62";
    public final String RAZUNA_COPY_RIGHT_NUMBER = "E28F8E67-0D95-459A-B68F591ED4674F4C";
    public final String RAZUNA_COPY_RIGHT_DATE = "0C790AD7-5484-4E29-A08986BAA0CEE185";
    public final String RAZUNA_PUBLISHING_COMPANY = "0CEFB7B0-4C6C-49E7-ABEF8143E348DAA3";
    public final String RAZUNA_PRO = "7CE6D15B-3044-4B28-91596847F4E6D790";
    public final String RAZUNA_LYRICS = "69745E4B-615A-4C74-9EB183A97BF7A6AD";
    public final String RAZUNA_PRIMARY_GENRE = "08E2B57E-6715-4C6B-A780CCFF24099589";
    public final String RAZUNA_SEC_GENRE = "813E7FDF-0726-4B26-B0F5125C8BB4E58A";
    public final String RAZUNA_ARTISTS = "A7EFAE57-3251-44C2-860C67232B96E873";

    //custom Fields Asset Ids local razuna {{CHANGE THESE FOR YOUR OWN LOCAL INSTALLATION}}
//    public final String RAZUNA_TITLE="F7DB7E95-F858-436C-956654E70DC3ECEE";
//    public final String RAZUNA_BITS_PER_RATE="CBB978E6-112A-4BA3-8525D4E42D32DDCD";
//    public final String RAZUNA_COPY_RIGHT_NUMBER="C446291B-344F-4425-93733A02CDB7519C";
//    public final String RAZUNA_COPY_RIGHT_DATE="74DEF824-3ED1-4667-B2C4F917E6EC7105";
//    public final String RAZUNA_PUBLISHING_COMPANY="FD1F5573-E4F6-4EAB-95FF6EDFFCFC04C5";
//    public final String RAZUNA_PRO="A900B015-1FAA-4A1E-8E59374A2855E02D";
//    public final String RAZUNA_LYRICS="EE581C86-C4DF-48D0-8B148894AA775294";
//    public final String RAZUNA_PRIMARY_GENRE="BC2C4C2E-D7B9-4AD7-85716CCD40ACA23A";
//    public final String RAZUNA_SEC_GENRE="DEF8D7F9-02E4-47AE-B019EEA701343340";
//    public final String RAZUNA_ARTISTS="48B652AC-9D9C-4C52-9AB48F3B181E12BD";


    //RAZUNA API METHODS
    public final String Razuna_CREATE_FOLDER_METHOD = "folder.cfc?";
    public final String RAZUNA_CUSTOM_FIELD_METHOD = "customfield.cfc";
    public final String RAZUNA_ASSET_METHOD = "asset.cfc";
    public final String RAZUNA_SEARCH_METHOD = "search.cfc";

    //Invite mail url ::: NEHA
    public final String INVITE_URL= "http://128.31.22.157:8080/MLL/index.html#!/";

    //Invite mail url local {{CHANGE THIS FOR YOUR OWN LOCAL INSTALLATION}}
//    public final String INVITE_URL = "http://localhost:8082/MLL/index.html#!/";
}
