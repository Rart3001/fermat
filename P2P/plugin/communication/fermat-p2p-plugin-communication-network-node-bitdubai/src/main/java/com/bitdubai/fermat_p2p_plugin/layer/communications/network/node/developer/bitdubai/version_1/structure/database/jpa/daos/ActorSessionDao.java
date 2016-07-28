/*
 * @#ActorCheckInDao.java - 2016
 * Copyright Fermat.org, All rights reserved.
 */
package com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos;

import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.enums.ProfileTypes;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.profiles.ActorProfile;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ActorCatalog;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ActorSession;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.Client;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ProfileRegistrationHistory;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.enums.RegistrationResult;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.enums.RegistrationType;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantDeleteRecordDataBaseException;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantInsertRecordDataBaseException;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantReadRecordDataBaseException;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantUpdateRecordDataBaseException;

import org.apache.commons.lang.ClassUtils;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.websocket.Session;

/**
 * The Class <code>com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.ActorSessionDao</code>
 * is the responsible for manage the <code>com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ActorSession</code> entity
 * <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 22/07/16
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class ActorSessionDao extends AbstractBaseDao<ActorSession> {

    /**
     * Represent the LOG
     */
    private final Logger LOG = Logger.getLogger(ClassUtils.getShortClassName(ActorSessionDao.class));

    /**
     * Constructor
     */
    public ActorSessionDao(){
        super(ActorSession.class);
    }


    /**
     * Check in a actor and associate with the session
     *
     * @param session
     * @param actorProfile
     */
    public synchronized void checkIn(Session session, ActorProfile actorProfile, Client client) throws CantReadRecordDataBaseException, CantUpdateRecordDataBaseException, CantInsertRecordDataBaseException {

        LOG.debug("Executing checkIn(" + session.getId() + ", " + actorProfile.getIdentityPublicKey() + ")");

        LOG.info("actorProfile = "+actorProfile);
        LOG.info("client = "+client);

        EntityManager connection = getConnection();
        EntityTransaction transaction = connection.getTransaction();

        try {

            transaction.begin();

            ActorSession actorSession;
            Map<String, Object> filters = new HashMap<>();
            filters.put("sessionId", session.getId());
            filters.put("actor.id", actorProfile.getIdentityPublicKey());
            filters.put("actor.client.id", actorProfile.getClientIdentityPublicKey());
            List<ActorSession> list = list(filters);

            if ((list != null) && (!list.isEmpty())){
                actorSession = list.get(0);
                actorSession.setActor(new ActorCatalog(actorProfile));
                connection.merge(actorSession);
            }else {
                actorSession = new ActorSession(session, new ActorCatalog(actorProfile));
                connection.persist(actorSession);
            }

            ProfileRegistrationHistory profileRegistrationHistory = new ProfileRegistrationHistory(actorProfile.getIdentityPublicKey(), client.getDeviceType(), ProfileTypes.ACTOR, RegistrationType.CHECK_IN, RegistrationResult.SUCCESS, "");
            connection.persist(profileRegistrationHistory);

            transaction.commit();

        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            throw new CantInsertRecordDataBaseException(CantInsertRecordDataBaseException.DEFAULT_MESSAGE, e, "Network Node", "");
        }finally {
            connection.close();
        }

    }

    /**
     * Check out all actor associate with the session
     *
     * @param session
     * @param client
     * @throws CantDeleteRecordDataBaseException
     */
    public void checkOut(Session session, Client client) throws CantDeleteRecordDataBaseException {

        LOG.debug("Executing checkOut("+session.getId()+")");

        EntityManager connection = getConnection();
        EntityTransaction transaction = connection.getTransaction();

        try {

            transaction.begin();

            Map<String, Object> filters = new HashMap<>();
            filters.put("sessionId", session.getId());
            filters.put("actor.client.id", client.getId());
            List<ActorSession> list = list(filters);

            if ((list != null) && (!list.isEmpty())){
                for (ActorSession actorSession : list) {
                    connection.remove(actorSession);
                    connection.remove(connection.contains(actorSession) ? actorSession : connection.merge(actorSession));
                    ProfileRegistrationHistory profileRegistrationHistory = new ProfileRegistrationHistory(actorSession.getActor().getId(), actorSession.getActor().getClient().getDeviceType(), ProfileTypes.ACTOR, RegistrationType.CHECK_OUT, RegistrationResult.SUCCESS, "");
                    connection.persist(profileRegistrationHistory);
                }
            }

            transaction.commit();

        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            throw new CantDeleteRecordDataBaseException(CantDeleteRecordDataBaseException.DEFAULT_MESSAGE, e, "Network Node", "");
        }finally {
            connection.close();
        }

    }

    /**
     * Check out a specific actor associate with the session
     *
     * @param session
     * @param actorProfile
     * @throws CantDeleteRecordDataBaseException
     */
    public void checkOut(Session session, ActorProfile actorProfile) throws CantDeleteRecordDataBaseException {

        LOG.debug("Executing checkOut(" + session.getId() + ")");

        EntityManager connection = getConnection();
        EntityTransaction transaction = connection.getTransaction();

        try {

            transaction.begin();

            Map<String, Object> filters = new HashMap<>();
            filters.put("sessionId", session.getId());
            filters.put("actor.id",actorProfile.getIdentityPublicKey());
            filters.put("actor.client.id", actorProfile.getClientIdentityPublicKey());
            List<ActorSession> list = list(filters);

            if ((list != null) && (!list.isEmpty())){

                connection.remove(connection.contains(list.get(0)) ? list.get(0) : connection.merge(list.get(0)));
                ProfileRegistrationHistory profileRegistrationHistory = new ProfileRegistrationHistory(list.get(0).getActor().getId(), list.get(0).getActor().getClient().getDeviceType(), ProfileTypes.ACTOR, RegistrationType.CHECK_OUT, RegistrationResult.SUCCESS, "");
                connection.persist(profileRegistrationHistory);
            }

            transaction.commit();

        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            throw new CantDeleteRecordDataBaseException(CantDeleteRecordDataBaseException.DEFAULT_MESSAGE, e, "Network Node", "");
        }finally {
            connection.close();
        }

    }
}
