package com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.channels.processors;

import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.data.Package;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.enums.PackageType;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.NetworkNodePluginRoot;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.channels.endpoinsts.FermatWebSocketChannelEndpoint;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.context.NodeContext;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.context.NodeContextItem;
import com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.exceptions.CantInsertRecordDataBaseException;

import org.apache.commons.lang.NotImplementedException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.websocket.Session;

/**
 * The Class <code>com.bitdubai.fermat_p2p_plugin.layer.communications.network.node.developer.bitdubai.version_1.structure.channels.processors.PackageProcessor</code>
 * is the base class for all message processor class <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 06/12/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public abstract class PackageProcessor {

    /**
     * Represent the packageType
     */
    private PackageType packageType;

    /**
     * Represent the networkNodePluginRoot
     */
    private NetworkNodePluginRoot networkNodePluginRoot;

    /**
     * Constructor with parameter
     * @param packageType
     */
    public PackageProcessor(PackageType packageType) {
        this.packageType = packageType;
        this.networkNodePluginRoot = (NetworkNodePluginRoot) NodeContext.get(NodeContextItem.PLUGIN_ROOT);
    }

    /**
     * Gets the value of packageType and returns
     *
     * @return packageType
     */
    public PackageType getPackageType() {
        return packageType;
    }

    /**
     * Get the NetworkNodePluginRoot
     * @return NetworkNodePluginRoot
     */
    public NetworkNodePluginRoot getNetworkNodePluginRoot() {
        return networkNodePluginRoot;
    }

    /**
     * Save the method call history
     *
     * @param parameters
     * @param profileIdentityPublicKey
     */
    protected void methodCallsHistory(String parameters, String profileIdentityPublicKey) throws CantInsertRecordDataBaseException {
/*
        MethodCallsHistory methodCallsHistory = new MethodCallsHistory();
        methodCallsHistory.setMethodName(getPackageType().toString());
        methodCallsHistory.setParameters(parameters);
        methodCallsHistory.setProfileIdentityPublicKey(profileIdentityPublicKey);

        getDaoFactory().getMethodCallsHistoryDao().create(methodCallsHistory);*/
    }

    /**
     * Method that call to process the message
     *
     * @param session that send the package
     * @param packageReceived to process
     */
    public synchronized void processingPackage(final Session session, final Package packageReceived, final FermatWebSocketChannelEndpoint channel){
        throw new NotImplementedException();
    }

    /**
     *
     * @param messageToSend
     * @return boolean
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public final boolean sendMessage(Future<Void> messageToSend) throws ExecutionException, InterruptedException {
        return messageToSend.get() == null;
    }

}
