package com.interblocks.iwallet.smb.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NicValidateRes {

    @JsonProperty("SuccessOrFailure")
    private String SuccessOrFailure;
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("cifId")
    private String cifId;
    @JsonProperty("custfullname")
    private String custfullname;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("custdob")
    private String custdob;
    @JsonProperty("custtitle")
    private String custtitle;
    @JsonProperty("isstaff")
    private String isstaff;
    @JsonProperty("custstaffid")
    private String custstaffid;
    @JsonProperty("segment")
    private String segment;
    @JsonProperty("subSegment")
    private String subSegment;
    @JsonProperty("iskyc")
    private String iskyc;
    @JsonProperty("kycrevdate")
    private String kycrevdate;
    @JsonProperty("riskrtng")
    private String riskrtng;
    @JsonProperty("custoccp")
    private String custoccp;
    @JsonProperty("isfrgntax")
    private String isfrgntax;
    @JsonProperty("frgntaxcntry")
    private String frgntaxcntry;
    @JsonProperty("frgnrptstatus")
    private String frgnrptstatus;
    @JsonProperty("emprname")
    private String emprname;
    @JsonProperty("custdesign")
    private String custdesign;
    @JsonProperty("empraddln1")
    private String empraddln1;
    @JsonProperty("empraddln2")
    private String empraddln2;
    @JsonProperty("emprcity")
    private String emprcity;
    @JsonProperty("emprdistr")
    private String emprdistr;
    @JsonProperty("emprcntry")
    private String emprcntry;
    @JsonProperty("Nationality")
    private String Nationality;
    @JsonProperty("rescntry")
    private String rescntry;
    @JsonProperty("gaurdiancif")
    private String gaurdiancif;
    @JsonProperty("childcif")
    private String childcif;
    @JsonProperty("gaurdiantype")
    private String gaurdiantype;
    @JsonProperty("gaurdianname")
    private String gaurdianname;
    @JsonProperty("commaddline1")
    private String commaddline1;
     @JsonProperty("commaddline2")
     private String commaddline2;
     @JsonProperty("commcity")
     private String commcity;
     @JsonProperty("commdistr")
     private String commdistr;
     @JsonProperty("commcntry")
     private String commcntry;
     @JsonProperty("permaddrln1")
     private String permaddrln1;
     @JsonProperty("permaddrlin2")
     private String permaddrlin2;
     @JsonProperty("permcity")
     private String permcity;
     @JsonProperty("permdistr")
     private String permdistr;
     @JsonProperty("permcntry")
     private String permcntry;
     @JsonProperty("frgnaddln1")
     private String frgnaddln1;
     @JsonProperty("frgnaddln2")
     private String frgnaddln2;
     @JsonProperty("frgncity")
     private String frgncity;
     @JsonProperty("frgndstr")
     private String frgndstr;
     @JsonProperty("frgncntry")
     private String frgncntry;
     @JsonProperty("mblPhoneNo")
     private String mblPhoneNo;
     @JsonProperty("commlandphone")
     private String commlandphone;
     @JsonProperty("email")
     private String email;
     @JsonProperty("incomeLvl")
     private String incomeLvl;
     @JsonProperty("isnonres")
     private String isnonres;
     @JsonProperty("isdiff")
     private String isdiff;
     @JsonProperty("custnicdoc")
     private String custnicdoc;
     @JsonProperty("custnicissuedate")
     private String custnicissuedate;
     @JsonProperty("custnicno")
     private String custnicno;
     @JsonProperty("custnicexpdate")
     private String custnicexpdate;
     @JsonProperty("identndoccode")
     private String identndoccode;
     @JsonProperty("identnissudate")
     private String identnissudate;
     @JsonProperty("identnuniqueno")
     private String identnuniqueno;
     @JsonProperty("identnexpdate")
     private String identnexpdate;
     @JsonProperty("immprptyval")
     private String immprptyval;
     @JsonProperty("immprpty")
     private String immprpty;
     @JsonProperty("movprptyval")
     private String movprptyval;
     @JsonProperty("movprpty")
     private String movprpty;
     @JsonProperty("citizen")
     private String citizen;
     @JsonProperty("permresidhol")
     private String permresidhol;
     @JsonProperty("custnatbuss")
     private String custnatbuss;
     @JsonProperty("isrent")
     private String isrent;
     @JsonProperty("corpId")
     private String corpId;
     @JsonProperty("istaxpay")
     private String istaxpay;
     @JsonProperty("taxfileno")
     private String taxfileno;
     @JsonProperty("oconnbuss")
     private String oconnbuss;
     @JsonProperty("calling")
     private String calling;
     @JsonProperty("pep")
     private String pep;
     @JsonProperty("sharepcnt")
     private String sharepcnt;
     @JsonProperty("taxidntno")
     private String taxidntno;
     @JsonProperty("faxnumber")
     private String faxnumber;
     @JsonProperty("whtPerc")
     private String whtPerc;
     @JsonProperty("whtFloor")
     private String whtFloor;
     @JsonProperty("gaurdiancif_name")
     private String gaurdiancif_name;
     @JsonProperty("custoccp_desc")
     private String custoccp_desc;
     @JsonProperty("immprpty_desc")
     private String immprpty_desc;
     @JsonProperty("movprpty_desc")
     private String movprpty_desc;
     @JsonProperty("custdesign_desc")
     private String custdesign_desc;
     @JsonProperty("segment_desc")
     private String segment_desc;
     @JsonProperty("subsegment_desc")
     private String subsegment_desc;
     @JsonProperty("foreigntaxreportingcountry_desc")
     private String foreigntaxreportingcountry_desc;
     @JsonProperty("emprcity_desc")
     private String emprcity_desc;
     @JsonProperty("emprdistr_desc")
     private String emprdistr_desc;
     @JsonProperty("emprcntry_desc")
     private String emprcntry_desc;
     @JsonProperty("nationality_desc")
     private String nationality_desc;
     @JsonProperty("residcntry_desc")
     private String residcntry_desc;
     @JsonProperty("citizen_desc")
     private String citizen_desc;
     @JsonProperty("permresidhol_desc")
     private String permresidhol_desc;
     @JsonProperty("commcity_desc")
     private String commcity_desc;
     @JsonProperty("commdistr_desc")
     private String commdistr_desc;
     @JsonProperty("commcntry_desc")
     private String commcntry_desc;
     @JsonProperty("permcity_desc")
     private String permcity_desc;
     @JsonProperty("permdistr_desc")
     private String permdistr_desc;
     @JsonProperty("permcntry_desc")
     private String permcntry_desc;
     @JsonProperty("frgncity_desc")
     private String frgncity_desc;
     @JsonProperty("frgndstr_desc")
     private String frgndstr_desc;
     @JsonProperty("frgncntry_desc")
     private String frgncntry_desc;
     @JsonProperty("custnatbuss_desc")
     private String custnatbuss_desc;
     @JsonProperty("vishwa")
     private String vishwa;
     @JsonProperty("smsalertz")
     private String smsalertz;
     @JsonProperty("smsNumber")
     private String smsNumber;
     @JsonProperty("schmCode")
     private String schmCode;
     @JsonProperty("crncyCode")
     private String crncyCode;
     @JsonProperty("foracid")
     private String foracid;
     @JsonProperty("acctname")
     private String acctname;
     @JsonProperty("modeofopr")
     private String modeofopr;
     @JsonProperty("sectrcode")
     private String sectrcode;
     @JsonProperty("subsctrcode")
     private String subsctrcode;
     @JsonProperty("canvasby")
     private String canvasby;
     @JsonProperty("depamt")
     private String depamt;
     @JsonProperty("periodmm")
     private String periodmm;
     @JsonProperty("perioddd")
     private String perioddd;
     @JsonProperty("prefint")
     private String prefint;
     @JsonProperty("fundacct")
     private String fundacct;
     @JsonProperty("intcracct")
     private String intcracct;
     @JsonProperty("valdate")
     private String valdate;
     @JsonProperty("pbrecino")
     private String pbrecino;
     @JsonProperty("dispmod")
     private String dispmod;
     @JsonProperty("chqbkname")
     private String chqbkname;
     @JsonProperty("purpofacct")
     private String purpofacct;
     @JsonProperty("opurpofacct")
     private String opurpofacct;
     @JsonProperty("sourceoffund")
     private String sourceoffund;
     @JsonProperty("osourceoffund")
     private String osourceoffund;
     @JsonProperty("typeofvisa")
     private String typeofvisa;
     @JsonProperty("visaexpdate")
     private String visaexpdate;
     @JsonProperty("plantrantype")
     private String plantrantype;
     @JsonProperty("typeofcntr")
     private String typeofcntr;
     @JsonProperty("isjnt")
     private String isjnt;
     @JsonProperty("maincustcode")
     private String maincustcode;
     @JsonProperty("purpofadv")
     private String purpofadv;
     @JsonProperty("debitcardreq")
     private String debitcardreq;

}
