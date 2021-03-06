package com.bitdubai.fermat_cbp_api.all_definition.constants;

/**
 * Created by nelson on 03/03/16.
 */
public interface CBPBroadcasterConstants {
    //Broker Wallet Constants
    String CBW_NEW_NEGOTIATION_NOTIFICATION = "CBW_NEW_NEGOTIATION_NOTIFICATION";
    String CBW_WAITING_FOR_BROKER_NOTIFICATION = "CBW_WAITING_FOR_BROKER_NOTIFICATION";
    String CBW_CONTRACT_EXPIRATION_NOTIFICATION = "CBW_CONTRACT_EXPIRATION_NOTIFICATION";
    String CBW_NEW_CONTRACT_UPDATE_VIEW = "CBW_NEW_CONTRACT_UPDATE_VIEW";
    String CBW_NEW_CONTRACT_NOTIFICATION = "CBW_NEW_CONTRACT_NOTIFICATION";
    String CBW_CANCEL_NEGOTIATION_NOTIFICATION = "CBW_CANCEL_NEGOTIATION_NOTIFICATION";
    String CBW_NEGOTIATION_UPDATE_VIEW = "CBW_NEGOTIATION_UPDATE_VIEW";

    //Customer Wallet Constants
    String CCW_WAITING_FOR_CUSTOMER_NOTIFICATION = "CCW_WAITING_FOR_CUSTOMER_NOTIFICATION";
    String CCW_CONTRACT_EXPIRATION_NOTIFICATION = "CCW_CONTRACT_EXPIRATION_NOTIFICATION";
    String CCW_CANCEL_NEGOTIATION_NOTIFICATION="CCW_CANCEL_NEGOTIATION_NOTIFICATION";
    String CCW_NEW_CONTRACT_UPDATE_VIEW = "CCW_NEW_CONTRACT_UPDATE_VIEW";
    String CCW_NEW_CONTRACT_NOTIFICATION = "CCW_NEW_CONTRACT_NOTIFICATION";
    String CCW_NEGOTIATION_UPDATE_VIEW = "CCW_NEGOTIATION_UPDATE_VIEW";
}