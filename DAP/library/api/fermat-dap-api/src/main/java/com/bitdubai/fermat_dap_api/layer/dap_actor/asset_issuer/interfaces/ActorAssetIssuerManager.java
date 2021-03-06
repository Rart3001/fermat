package com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces;

import com.bitdubai.fermat_api.layer.all_definition.common.system.interfaces.FermatManager;
import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_dap_api.layer.all_definition.enums.DAPConnectionState;
import com.bitdubai.fermat_dap_api.layer.dap_actor.DAPActor;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.exceptions.CantAssetIssuerActorNotFoundException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.exceptions.CantCreateActorAssetIssuerException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.exceptions.CantGetAssetIssuerActorsException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.exceptions.CantUpdateActorAssetIssuerException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.exceptions.CantConnectToActorAssetException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.redeem_point.exceptions.CantConnectToActorAssetRedeemPointException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.redeem_point.interfaces.ActorAssetRedeemPoint;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.asset_issuer.exceptions.CantRegisterActorAssetIssuerException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantAskConnectionActorAssetException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantCancelConnectionActorAssetException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantDenyConnectionActorAssetException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantAcceptActorAssetUserException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantCreateActorAssetReceiveException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantGetActorAssetNotificationException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantGetActorAssetWaitingException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.exceptions.CantRequestAlreadySendActorAssetException;

import java.util.List;

/**
 * Created by Nerio on 07/09/15.
 */
public interface ActorAssetIssuerManager extends FermatManager {

    /**
     * The method <code>getActorRegisteredByPublicKey</code> shows the information associated with the actorPublicKey
     *
     * @param actorPublicKey                     The public key of the Asset Actor Issuer
     * @return                                   THe information associated with the actorPublicKey.
     * @throws CantGetAssetIssuerActorsException
     * @throws CantAssetIssuerActorNotFoundException
     */
    ActorAssetIssuer getActorByPublicKey(String actorPublicKey) throws CantGetAssetIssuerActorsException, CantAssetIssuerActorNotFoundException;

    /**
     * The method <code>createActorAssetIssuerFactory</code> create Actor by a Identity
     *
     * @param assetIssuerActorPublicKey                 Referred to the Identity publicKey
     * @param assetIssuerActorName                      Referred to the Identity Alias
     * @param assetIssuerImage              Referred to the Identity profileImage
     * @throws CantCreateActorAssetIssuerException
     */
    void createActorAssetIssuerFactory(String assetIssuerActorPublicKey, String assetIssuerActorName, byte[] assetIssuerImage) throws CantCreateActorAssetIssuerException;

    /**
     * The method <code>registerActorInActorNetworkService</code> Register Actor in Actor Network Service
     */
    void registerActorInActorNetworkService() throws CantRegisterActorAssetIssuerException;

    /**
     * The method <code>createActorAssetIssuerRegisterInNetworkService</code> create Actor Registered
     *
     * @param actorAssetIssuers                       Referred to the Identity publicKey
     * @throws CantCreateActorAssetIssuerException
     */
    void createActorAssetIssuerRegisterInNetworkService(List<ActorAssetIssuer> actorAssetIssuers) throws CantCreateActorAssetIssuerException;

    void createActorAssetIssuerRegisterInNetworkService(ActorAssetIssuer actorAssetIssuer) throws CantCreateActorAssetIssuerException;

    /**
     * The method <code>getActorPublicKey</code> get All Information about Actor
     *
     * @throws CantGetAssetIssuerActorsException
     */
    ActorAssetIssuer getActorAssetIssuer() throws CantGetAssetIssuerActorsException;

    DAPConnectionState getActorIssuerRegisteredDAPConnectionState(String actorAssetPublicKey) throws CantGetAssetIssuerActorsException;

    /**
     * The method <code>getAllAssetIssuerActorInTableRegistered</code> get All Actors Registered in Actor Network Service
     * and used in Sub App Community
     *
     * @throws CantGetAssetIssuerActorsException
     */
    List<ActorAssetIssuer> getAllAssetIssuerActorInTableRegistered() throws CantGetAssetIssuerActorsException;

    /**
     * The method <code>getAllAssetIssuerActorConnected</code> receives All Actors with have CryptoAddress in BD
     *
     * @throws CantGetAssetIssuerActorsException
     */
    List<ActorAssetIssuer> getAllAssetIssuerActorConnected() throws CantGetAssetIssuerActorsException;


    List<ActorAssetIssuer> getAllAssetIssuerActorConnectedWithExtendedPublicKey() throws CantGetAssetIssuerActorsException;

    /**
     * The method <code>sendMessage</code> Stablish Connection
     * with Requester and Lists Redeem Point associate
     *
     * @throws CantConnectToActorAssetRedeemPointException
     */
    void sendMessage(ActorAssetIssuer requester, List<ActorAssetRedeemPoint> actorAssetRedeemPoints) throws CantConnectToActorAssetRedeemPointException;

//    void sendMessage(ActorAssetRedeemPoint requester, ActorAssetIssuer actorAssetIssuer) throws CantConnectToActorAssetException;

    void updateExtendedPublicKey(String issuerPublicKey, String extendedPublicKey) throws CantUpdateActorAssetIssuerException;

    void updateIssuerRegisteredDAPConnectionState(String issuerPublicKey, DAPConnectionState connectionState) throws CantUpdateActorAssetIssuerException;

    /**
     * The method <code>askActorAssetUserForConnection</code> registers a new actor asset user in the list
     * managed by this plugin with ContactState PENDING_REMOTELY_ACCEPTANCE until the other intra user
     * accepts the connection request sent also by this method.
     *
     * @param actorAssetIssuerToLinkPublicKey The public key of the actor asset user sending the connection request.
     * @param actorAssetIssuerToAddName               The name of the actor asset user to add
     * @param actorAssetIssuerToAddPublicKey          The public key of the actor asset  user to add
     * @param profileImage                           The profile image that the actor asset user has
     *
     * @throws CantAskConnectionActorAssetException if something goes wrong.
     */
    void askActorAssetIssuerForConnection(String actorAssetIssuerToLinkPublicKey,
                                        String actorAssetIssuerToAddName              ,
                                        String actorAssetIssuerToAddPublicKey         ,
                                        byte[] profileImage) throws CantAskConnectionActorAssetException, CantRequestAlreadySendActorAssetException;


    /**
     * The method <code>acceptIntraWalletUser</code> takes the information of a connection request, accepts
     * the request and adds the intra user to the list managed by this plugin with ContactState CONTACT.
     *
     * @param actorAssetIssuerInPublicKey The public key of the intra user sending the connection request.
     * @param actorAssetIssuerToAddPublicKey    The public key of the intra user to add
     * @throws CantAcceptActorAssetUserException
     */
    void acceptActorAssetIssuer(String actorAssetIssuerInPublicKey, String actorAssetIssuerToAddPublicKey) throws CantAcceptActorAssetUserException;


    /**
     * The method <code>denyConnection</code> rejects a connection request from another intra user
     *
     * @param actorAssetIssuerLoggedInPublicKey The public key of the intra user identity that is the receptor of the request
     * @param actorAssetIssuerToRejectPublicKey The public key of the intra user that sent the request
     * @throws CantDenyConnectionActorAssetException
     */
    void denyConnectionActorAssetIssuer(String actorAssetIssuerLoggedInPublicKey, String actorAssetIssuerToRejectPublicKey) throws CantDenyConnectionActorAssetException;

//    /**
//     * The method <code>disconnectIntraWalletUser</code> disconnect an intra user from the connections registry
//     * @param intraUserLoggedInPublicKey The public key of the intra user identity that is the receptor of the request
//     * @param intraUserToDisconnectPublicKey The public key of the intra user to disconnect as connection
//     * @throws CantDisconnectAssetUserActorException
//     */
//    void disconnectActorAssetUser(String intraUserLoggedInPublicKey, String intraUserToDisconnectPublicKey) throws CantDisconnectAssetUserActorException;


    void receivingActorAssetIssuerRequestConnection(String actorAssetIssuerLoggedInPublicKey,
                                                  String actorAssetIssuerToAddName,
                                                  String actorAssetIssuerToAddPublicKey,
                                                  byte[] profileImage,
                                                  Actors actorsType) throws CantCreateActorAssetReceiveException;

    /**
     * The method <code>cancelIntraWalletUser</code> cancels an intra user from the connections registry
     * @param actorAssetUserToCancelPublicKey The public key of the intra user to cancel as connection
     * @throws CantCancelConnectionActorAssetException
     */
    void cancelActorAssetIssuer(String actorAssetUserToCancelPublicKey) throws CantCancelConnectionActorAssetException;

    /**
     * The method <code>getWaitingYourAcceptanceIntraWalletUsers</code> shows the list of all intra users
     * that sent a connection request and are waiting for the acceptance of the logged in one.
     *
     * @param actorAssetIssuerLoggedPublicKey the public key of the intra user logged in
     * @return the list of intra users the logged in intra user has as connections.
     * @throws CantGetActorAssetWaitingException
     */
    List<DAPActor> getWaitingYourConnectionActorAssetIssuer(String actorAssetIssuerLoggedPublicKey, int max, int offset) throws CantGetActorAssetWaitingException;

    /**
     * The method <code>getWaitingTheirConnectionActorAssetUser</code> shows the list of all actor asset users
     * that the logged in one has sent connections request to and have not been answered yet..
     *
     * @param actorAssetIssuerLoggedPublicKey the public key of the intra user logged in
     * @return the list of intra users the logged in intra user has as connections.
     * @throws CantGetActorAssetWaitingException
     */
    List<DAPActor> getWaitingTheirConnectionActorAssetIssuer(String actorAssetIssuerLoggedPublicKey, int max, int offset) throws CantGetActorAssetWaitingException;

    ActorAssetIssuer getLastNotificationActorAssetIssuer(String actorAssetIssuerPublicKey) throws CantGetActorAssetNotificationException;

    void updateOfflineIssuersRegisterInNetworkService(List<ActorAssetIssuer> actorAssetIssuers) throws CantGetAssetIssuerActorsException;

}
