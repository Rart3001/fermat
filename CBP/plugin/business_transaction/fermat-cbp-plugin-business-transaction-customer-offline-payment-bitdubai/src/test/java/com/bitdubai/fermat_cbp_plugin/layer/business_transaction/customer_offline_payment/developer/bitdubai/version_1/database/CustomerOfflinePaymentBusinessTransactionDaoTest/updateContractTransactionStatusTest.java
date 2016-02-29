package com.bitdubai.fermat_cbp_plugin.layer.business_transaction.customer_offline_payment.developer.bitdubai.version_1.database.CustomerOfflinePaymentBusinessTransactionDaoTest;

import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_cbp_api.all_definition.enums.ContractTransactionStatus;
import com.bitdubai.fermat_cbp_api.all_definition.exceptions.UnexpectedResultReturnedFromDatabaseException;
import com.bitdubai.fermat_cbp_plugin.layer.business_transaction.customer_offline_payment.developer.bitdubai.version_1.database.CustomerOfflinePaymentBusinessTransactionDao;
import com.bitdubai.fermat_cbp_plugin.layer.business_transaction.customer_offline_payment.developer.bitdubai.version_1.database.CustomerOfflinePaymentBusinessTransactionDatabaseConstants;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.interfaces.ErrorManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * Created by alexander jimenez (alex_jimenez76@hotmail.com) on 25/02/16.
 */
public class updateContractTransactionStatusTest {
    CustomerOfflinePaymentBusinessTransactionDao customerOfflinePaymentBusinessTransactionDao;
    @Mock
    PluginDatabaseSystem pluginDatabaseSystem;
    @Mock
    Database database;
    @Mock
    ErrorManager errorManager;
    @Mock
    DatabaseTable databaseTable;
    @Mock
    List< DatabaseTableRecord> databaseTableRecordList;
    @Mock
    DatabaseTableRecord databaseTableRecord;
    private UUID testId;
    @Before
    public void setup(){
        testId = UUID.randomUUID();
        MockitoAnnotations.initMocks(this);
        customerOfflinePaymentBusinessTransactionDao = new CustomerOfflinePaymentBusinessTransactionDao(
                pluginDatabaseSystem,testId,database,errorManager);
        when(databaseTable.getRecords()).thenReturn(databaseTableRecordList);
        when(databaseTableRecordList.get(0)).thenReturn(databaseTableRecord);
    }
    @Test
    public void updateContractTransactionStatusTest()throws Exception{
        when(database.getTable(
                        CustomerOfflinePaymentBusinessTransactionDatabaseConstants.OFFLINE_PAYMENT_TABLE_NAME)
        ).thenReturn(databaseTable);
        customerOfflinePaymentBusinessTransactionDao.updateContractTransactionStatus(
                "contractHash", ContractTransactionStatus.PENDING_ONLINE_PAYMENT_CONFIRMATION);
    }
    @Test(expected = UnexpectedResultReturnedFromDatabaseException.class)
    public void updateContractTransactionStatusTest_Should_Throw_Exception()throws Exception{
        customerOfflinePaymentBusinessTransactionDao.updateContractTransactionStatus(
                "contractHash", ContractTransactionStatus.PENDING_ONLINE_PAYMENT_CONFIRMATION);
    }
}
