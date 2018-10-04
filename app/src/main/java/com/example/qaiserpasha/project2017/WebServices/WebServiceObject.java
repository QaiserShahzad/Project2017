package com.example.qaiserpasha.project2017.WebServices;

/**
 * Created by Qaiser Pasha on 10/11/2017.
 */

public class WebServiceObject {

    public static  String FTP_IP="192.168.1.7";
    public static  String onlyIp=FTP_IP+":8040";
    public  static  String IP="http://"+onlyIp+"/Service1.svc/";
    public  static  String ADD_STOCK=IP+"saveStock/";
    public  static  String RATTING=IP+"Rating/";

    public  static  String DONEE_SEND_REQ=IP+"saveDonieRequest/";


    // Donee Getting Request
    public  static  String DONEE_REQ=IP+"DistRequestAdmin/";
    public  static  String DONEE_ACCEPT_REQ=IP+"UpdateDoneeRequest/";
    public  static  String GET_ALL_ACCEPTED_DONEE_REQUESTS_NEW=IP+"DoneeApproveeRequestAdmin";


    public  static  String DONEE_SEARCH=IP+"search/";
    public  static  String GET_ALL_DONATIONS=IP+"getDonations/";
    public  static  String GET_ALL_REQUESTS_NEW=IP+"DonateRequestAdmin";
    public  static  String GET_ALL_ACCEPTED_REQUESTS_NEW=IP+"DonationApproveeRequestAdmin";
    public  static  String APPROVAL_UPDATE=IP+"ADRequest/";
    public  static  String IMAGE_URL="http://"+onlyIp+"/Image/";


}
