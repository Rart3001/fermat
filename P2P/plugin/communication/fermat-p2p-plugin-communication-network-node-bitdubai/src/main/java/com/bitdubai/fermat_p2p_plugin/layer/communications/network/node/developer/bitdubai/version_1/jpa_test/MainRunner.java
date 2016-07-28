package com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.jpa_test;

import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.ECCKeyPair;
import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.network_service.enums.NetworkServiceType;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.enums.ProfileStatus;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.profiles.ActorProfile;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.profiles.NetworkServiceProfile;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.enums.JPANamedQuery;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.DatabaseManager;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos.ActorCatalogDao;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos.ActorCheckInDao;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos.ClientCheckInDao;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos.JPADaoFactory;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos.NetworkServiceCheckInDao;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.daos.NodeCatalogDao;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ActorCatalog;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ActorCheckIn;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.Client;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.ClientCheckIn;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.GeoLocation;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.NetworkService;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.NetworkServiceCheckIn;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.database.jpa.entities.NodeCatalog;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantInsertRecordDataBaseException;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantReadRecordDataBaseException;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantUpdateRecordDataBaseException;
import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.TypedQuery;

/**
 * Created by rrequena on 21/07/16.
 */

public class MainRunner {

    private static final int TOTAL_NODES = 3;

    private static final int TOTAL_CLIENTS = 1;

    private static final int TOTAL_NS = 20;

    private static final int TOTAL_ACTOR = 5000;

    public static void main(String[] args) {

        try {

            Stopwatch timer = Stopwatch.createStarted();

            NodeCatalog nodeCatalog = testNodeCatalog();

            ClientCheckIn clientCheckIn = testClientCheckIn(nodeCatalog);

            DatabaseManager.closeDataBase();

            System.out.println("TOTAL TEST TOOK: " + timer.stop());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static NodeCatalog testNodeCatalog() throws CantReadRecordDataBaseException, CantUpdateRecordDataBaseException, CantInsertRecordDataBaseException {

        System.out.println(" ---------------------------------------------------------------------------------- ");
        System.out.println(" Executing method testNodeCatalog()");

        Stopwatch timer = Stopwatch.createStarted();
        List<NodeCatalog> list = new ArrayList<>();
        NodeCatalogDao dao = new NodeCatalogDao();

        ECCKeyPair eccKeyPair = null;

        for (int i = 0; i < TOTAL_NODES; i++) {

            eccKeyPair = new ECCKeyPair();
            NodeCatalog nodeCatalog = new NodeCatalog();
            nodeCatalog.setId(eccKeyPair.getPublicKey());
            nodeCatalog.setDefaultPort(8080);
            nodeCatalog.setName("Node_" + i);
            nodeCatalog.setIp("10.1.1." + i);
            nodeCatalog.setStatus(ProfileStatus.OFFLINE);
            nodeCatalog.setLocation(new GeoLocation((10.1 + i), (8.9 + i)));

            list.add(nodeCatalog);

        }

        for (NodeCatalog item: list) {
            dao.save(item);
        }

        System.out.println("Last id: " + eccKeyPair.getPublicKey());
        System.out.println("Total entities: " + dao.count());

        NodeCatalog entity = dao.findById(eccKeyPair.getPublicKey());
        System.out.println("Load NodeCatalog entity:" +entity);
        System.out.println("Method testNodeCatalog() took: " + timer.stop());
        System.out.println(" ---------------------------------------------------------------------------------- ");

        return entity;

    }


    public static ClientCheckIn testClientCheckIn(NodeCatalog nodeCatalog) throws CantReadRecordDataBaseException, CantUpdateRecordDataBaseException, CantInsertRecordDataBaseException {

        System.out.println(" ---------------------------------------------------------------------------------- ");
        System.out.println(" Executing method testClientCheckIn()");

        Stopwatch timer = Stopwatch.createStarted();
        List<ClientCheckIn> list = new ArrayList<>();
        ClientCheckInDao dao = new ClientCheckInDao();

        ECCKeyPair id = null;
        String sessionId = null;
        ClientCheckIn clientCheckIn = null;

        for (int i = 0; i < TOTAL_CLIENTS; i++) {

            sessionId = UUID.randomUUID().toString();
            id = new ECCKeyPair();
            Client profile = new Client();
            profile.setDeviceType("device " + i);
            profile.setId(id.getPublicKey());
            profile.setLocation(new GeoLocation((10.1 + i), (8.9 + i)));
            profile.setStatus(ProfileStatus.ONLINE);

            clientCheckIn = new ClientCheckIn();
            clientCheckIn.setClient(profile);
            clientCheckIn.setId(sessionId);

            list.add(clientCheckIn);

        }

        NetworkServiceCheckIn networkServiceCheckIn = null;
        ActorCheckIn actorCheckIn = null;
        for (ClientCheckIn item: list) {
            dao.save(item);
            networkServiceCheckIn = testNetworkServiceCheckIn(item);
            actorCheckIn = testActorCheckIn(item, networkServiceCheckIn, nodeCatalog);
        }

        System.out.println("Last id: " + clientCheckIn.getId());
        System.out.println("Total ClientCheckIn entities: " + dao.count());

        ClientCheckIn entity = dao.findById(sessionId);
      //  System.out.println("entity = " + entity);
        System.out.println("Load ClientCheckIn entity:" +clientCheckIn.getId());
        System.out.println("Method testClientCheckIn() took: " + timer.stop());
        System.out.println(" ---------------------------------------------------------------------------------- ");

        speedTest(actorCheckIn);

        return entity;

    }
    public static void speedTest(ActorCheckIn actorCheckIn){
        System.out.println("#######################################################");
        try {
            HashMap<String, Object> filter = new HashMap<>();
            filter.put("type", actorCheckIn.getActor().getActorType());
            Stopwatch timer = Stopwatch.createStarted();
            List<ActorCheckIn> actorCheckIns = JPADaoFactory.getActorCheckInDao().executeNamedQuery(JPANamedQuery.GET_ALL_CHECKED_IN_ACTORS_BY_ACTORTYPE, filter);
           int total = JPADaoFactory.getActorCheckInDao().executeNamedQuery(JPANamedQuery.GET_ALL_CHECKED_IN_ACTORS_BY_ACTORTYPE, filter).size();
//            System.out.println("actorCheckIns = "+actorCheckIns);
            System.out.println("total = " + total);
            System.out.println("Time consumed:" + timer.stop());
            System.out.println("#######################################################");
            filter.clear();
            filter.put("actor.actorType", actorCheckIn.getActor().getActorType());
            timer = Stopwatch.createStarted();
            actorCheckIns = JPADaoFactory.getActorCheckInDao().list(filter);
            total = JPADaoFactory.getActorCheckInDao().count(filter);
//            System.out.println("actorCheckIns = "+actorCheckIns);
            System.out.println("total = " + total);
            System.out.println("Time consumed:"+timer.stop());
            System.out.println("#######################################################");
        } catch (CantReadRecordDataBaseException e) {
            e.printStackTrace();
        }
    }

    public static NetworkServiceCheckIn testNetworkServiceCheckIn(ClientCheckIn clientCheckIn) throws CantReadRecordDataBaseException, CantUpdateRecordDataBaseException, CantInsertRecordDataBaseException {

        System.out.println(" ---------------------------------------------------------------------------------- ");
        System.out.println(" Executing method testNetworkServiceCheckIn()");

        Stopwatch timer = Stopwatch.createStarted();
        List<NetworkServiceCheckIn> list = new ArrayList<>();
        NetworkServiceCheckInDao dao = new NetworkServiceCheckInDao();

        ECCKeyPair id = null;
        NetworkServiceCheckIn networkServiceCheckIn = null;

        for (int i = 0; i < TOTAL_NS; i++) {

            id = new ECCKeyPair();
            NetworkServiceProfile profile = new NetworkServiceProfile();
            profile.setIdentityPublicKey(id.getPublicKey());
            profile.setLocation(new GeoLocation((10.1 + i), (8.9 + i)));
            profile.setStatus(ProfileStatus.ONLINE);
            profile.setClientIdentityPublicKey(clientCheckIn.getClient().getId());
            profile.setNetworkServiceType(NetworkServiceType.NEGOTIATION_TRANSMISSION);

            NetworkService networkService = new NetworkService(profile);

            networkServiceCheckIn = new NetworkServiceCheckIn();
            networkServiceCheckIn.setSessionId(clientCheckIn.getId());
            networkServiceCheckIn.setNetworkService(networkService);

            list.add(networkServiceCheckIn);

        }

        for (NetworkServiceCheckIn item: list) {
            dao.save(item);
        }

        System.out.println("Last id: " + id.getPublicKey());
        System.out.println("Total NetworkServiceCheckIn entities: " + dao.count());

        NetworkServiceCheckIn entity = dao.findById(networkServiceCheckIn.getId());
       // System.out.println("Load NetworkServiceCheckIn entity:" +entity);
        System.out.println("Method testClientCheckIn() took: " + timer.stop());
        System.out.println(" ---------------------------------------------------------------------------------- ");

        return entity;

    }


    public static ActorCheckIn testActorCheckIn(ClientCheckIn clientCheckIn, NetworkServiceCheckIn networkServiceCheckIn, NodeCatalog nodeCatalog) throws CantReadRecordDataBaseException, CantUpdateRecordDataBaseException, CantInsertRecordDataBaseException {

        System.out.println(" ---------------------------------------------------------------------------------- ");
        System.out.println(" Executing method testActorCheckIn()");

        Stopwatch timer = Stopwatch.createStarted();
        ActorCheckInDao dao = new ActorCheckInDao();
        ActorCatalogDao actorCatalogDao = new ActorCatalogDao();

        ECCKeyPair id = null;
        ActorCheckIn actorCheckIn = null;

        for (int i = 0; i < TOTAL_ACTOR; i++) {

            id = new ECCKeyPair();
            ActorProfile profile = new ActorProfile();
            profile.setIdentityPublicKey(id.getPublicKey());
            profile.setLocation(new GeoLocation((10.1 + i), (8.9 + i)));
            profile.setStatus(ProfileStatus.UNKNOWN);
            profile.setClientIdentityPublicKey(clientCheckIn.getClient().getId());
            profile.setNsIdentityPublicKey(networkServiceCheckIn.getNetworkService().getId());
            profile.setAlias("Alias-00" + i);
            profile.setName("Name " + i);
            if(i == TOTAL_ACTOR-1)
                profile.setActorType(Actors.ART_FAN.getCode());
            else
                profile.setActorType(Actors.CBP_CRYPTO_BROKER.getCode());
            profile.setExtraData("content " + i + i);
            profile.setPhoto(("Imagen " + i).getBytes());

            actorCheckIn = new ActorCheckIn();
            actorCheckIn.setSessionId(clientCheckIn.getId());

            ActorCatalog actorCatalog = new ActorCatalog(profile, ("Thumbnail " + i).getBytes(), nodeCatalog, actorCheckIn, "");
            actorCheckIn.setActor(actorCatalog);

            dao.save(actorCheckIn);

        }

        HashMap<String,Object> filters = new HashMap<>();
        String actorType = null;
        List<ActorCheckIn> actorCheckIns;
        long total;
        if(actorType != null && !actorType.isEmpty()) {
            filters.put("type",actorType);
            actorCheckIns = JPADaoFactory.getActorCheckInDao().executeNamedQuery(JPANamedQuery.GET_ALL_CHECKED_IN_ACTORS_BY_ACTORTYPE, filters);
            filters.clear();
            filters.put("type",actorType);
            total = JPADaoFactory.getActorCheckInDao().executeNamedQuery(JPANamedQuery.GET_ALL_CHECKED_IN_ACTORS_BY_ACTORTYPE, filters).size();
        }else {
            actorCheckIns = JPADaoFactory.getActorCheckInDao().executeNamedQuery(JPANamedQuery.GET_ALL_CHECKED_IN_ACTORS, filters);
            filters.clear();
            total = JPADaoFactory.getActorCheckInDao().executeNamedQuery(JPANamedQuery.GET_ALL_CHECKED_IN_ACTORS, filters).size();

        }
        filters.clear();
        filters.put("id",actorCheckIn.getActor().getId());
     //   List<ActorCatalog> actorCatalogs = JPADaoFactory.getActorCatalogDao().executeNamedQuery(JPANamedQuery.GET_ACTOR_CATALOG_BY_ID,filters);
       // System.out.println("actorCatalogs = " + actorCatalogs);
        System.out.println("actors type"+actorCheckIns);
        System.out.println("total"+total);
        System.out.println("##########################################");
        System.out.println("Last id: " + actorCheckIn.getId());
        System.out.println("Total ActorCheckIn entities: " + dao.count());

        ActorCheckIn entity = dao.findById(actorCheckIn.getId());
      //  System.out.println("Load ActorCheckIn entity:" +entity);

        System.out.println("Exist ActorCheckIn entity " + dao.exist(entity.getId()));
      //  System.out.println("ActorProfile " + entity.getActor().getActorProfile());
        System.out.println("Method testActorCheckIn() took: " + timer.stop());
        System.out.println(" ---------------------------------------------------------------------------------- ");

        return entity;

    }
}
