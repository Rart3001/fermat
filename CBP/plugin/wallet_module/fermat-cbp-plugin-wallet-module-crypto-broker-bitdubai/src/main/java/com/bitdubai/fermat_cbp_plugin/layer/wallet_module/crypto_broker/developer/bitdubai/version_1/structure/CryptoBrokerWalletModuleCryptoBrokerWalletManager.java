package com.bitdubai.fermat_cbp_plugin.layer.wallet_module.crypto_broker.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_api.layer.all_definition.enums.FiatCurrency;
import com.bitdubai.fermat_api.layer.all_definition.enums.Platforms;
import com.bitdubai.fermat_api.layer.all_definition.enums.interfaces.FermatEnum;
import com.bitdubai.fermat_api.layer.all_definition.exceptions.InvalidParameterException;
import com.bitdubai.fermat_api.layer.all_definition.settings.structure.SettingsManager;
import com.bitdubai.fermat_api.layer.modules.common_classes.ActiveActorIdentityInformation;
import com.bitdubai.fermat_api.layer.modules.exceptions.CantGetSelectedActorIdentityException;
import com.bitdubai.fermat_api.layer.modules.interfaces.FermatSettings;
import com.bitdubai.fermat_api.layer.world.interfaces.Currency;
import com.bitdubai.fermat_bnk_api.all_definition.enums.BankAccountType;
import com.bitdubai.fermat_bnk_api.layer.bnk_wallet.bank_money.exceptions.CantAddNewAccountException;
import com.bitdubai.fermat_bnk_api.layer.bnk_wallet.bank_money.exceptions.CantCalculateBalanceException;
import com.bitdubai.fermat_bnk_api.layer.bnk_wallet.bank_money.exceptions.CantLoadBankMoneyWalletException;
import com.bitdubai.fermat_bnk_api.layer.bnk_wallet.bank_money.interfaces.BankAccountNumber;
import com.bitdubai.fermat_bnk_api.layer.bnk_wallet.bank_money.interfaces.BankMoneyWalletManager;
import com.bitdubai.fermat_cbp_api.all_definition.enums.ClauseStatus;
import com.bitdubai.fermat_cbp_api.all_definition.enums.ClauseType;
import com.bitdubai.fermat_cbp_api.all_definition.enums.ContractStatus;
import com.bitdubai.fermat_cbp_api.all_definition.enums.CurrencyType;
import com.bitdubai.fermat_cbp_api.all_definition.enums.NegotiationStatus;
import com.bitdubai.fermat_cbp_api.all_definition.enums.NegotiationStepStatus;
import com.bitdubai.fermat_cbp_api.all_definition.enums.NegotiationStepType;
import com.bitdubai.fermat_cbp_api.all_definition.enums.NegotiationType;
import com.bitdubai.fermat_cbp_api.all_definition.enums.OriginTransaction;
import com.bitdubai.fermat_cbp_api.all_definition.negotiation.Clause;
import com.bitdubai.fermat_cbp_api.all_definition.negotiation.NegotiationBankAccount;
import com.bitdubai.fermat_cbp_api.all_definition.negotiation.NegotiationLocations;
import com.bitdubai.fermat_cbp_api.layer.contract.customer_broker_sale.exceptions.CantGetListCustomerBrokerContractSaleException;
import com.bitdubai.fermat_cbp_api.layer.contract.customer_broker_sale.interfaces.CustomerBrokerContractSale;
import com.bitdubai.fermat_cbp_api.layer.contract.customer_broker_sale.interfaces.CustomerBrokerContractSaleManager;
import com.bitdubai.fermat_cbp_api.layer.identity.crypto_broker.exceptions.CantCreateCryptoBrokerIdentityException;
import com.bitdubai.fermat_cbp_api.layer.identity.crypto_broker.exceptions.CantListCryptoBrokerIdentitiesException;
import com.bitdubai.fermat_cbp_api.layer.identity.crypto_broker.exceptions.CryptoBrokerIdentityAlreadyExistsException;
import com.bitdubai.fermat_cbp_api.layer.identity.crypto_broker.interfaces.CryptoBrokerIdentity;
import com.bitdubai.fermat_cbp_api.layer.identity.crypto_broker.interfaces.CryptoBrokerIdentityManager;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantCreateBankAccountSaleException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantCreateLocationSaleException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantDeleteBankAccountSaleException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantDeleteLocationSaleException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantGetListLocationsSaleException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantGetListSaleNegotiationsException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.exceptions.CantUpdateLocationSaleException;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.interfaces.CustomerBrokerSaleNegotiation;
import com.bitdubai.fermat_cbp_api.layer.negotiation.customer_broker_sale.interfaces.CustomerBrokerSaleNegotiationManager;
import com.bitdubai.fermat_cbp_api.layer.negotiation.exceptions.CantGetListClauseException;
import com.bitdubai.fermat_cbp_api.layer.negotiation_transaction.customer_broker_update.exceptions.CantCancelNegotiationException;
import com.bitdubai.fermat_cbp_api.layer.negotiation_transaction.customer_broker_update.interfaces.CustomerBrokerUpdateManager;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.bank_money_destock.exceptions.CantCreateBankMoneyDestockException;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.bank_money_destock.interfaces.BankMoneyDestockManager;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.bank_money_restock.exceptions.CantCreateBankMoneyRestockException;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.bank_money_restock.interfaces.BankMoneyRestockManager;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.cash_money_destock.exceptions.CantCreateCashMoneyDestockException;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.cash_money_destock.interfaces.CashMoneyDestockManager;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.cash_money_restock.exceptions.CantCreateCashMoneyRestockException;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.cash_money_restock.interfaces.CashMoneyRestockManager;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.crypto_money_destock.exceptions.CantCreateCryptoMoneyDestockException;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.crypto_money_destock.interfaces.CryptoMoneyDestockManager;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.crypto_money_restock.exceptions.CantCreateCryptoMoneyRestockException;
import com.bitdubai.fermat_cbp_api.layer.stock_transactions.crypto_money_restock.interfaces.CryptoMoneyRestockManager;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CantGetAvailableBalanceCryptoBrokerWalletException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CantGetCryptoBrokerMarketRateException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CantGetCryptoBrokerStockTransactionException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CantGetCryptoBrokerWalletSettingException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CantGetStockCryptoBrokerWalletException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CantSaveCryptoBrokerWalletSettingException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.exceptions.CryptoBrokerWalletNotFoundException;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.CryptoBrokerStockTransaction;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.FiatIndex;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.setting.CryptoBrokerWalletAssociatedSetting;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.setting.CryptoBrokerWalletProviderSetting;
import com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.setting.CryptoBrokerWalletSettingSpread;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantGetContractHistoryException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantGetContractsWaitingForBrokerException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantGetContractsWaitingForCustomerException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantGetNegotiationInformationException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantGetNegotiationsWaitingForBrokerException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantGetNegotiationsWaitingForCustomerException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantNewEmptyBankAccountException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantNewEmptyCryptoBrokerWalletAssociatedSettingException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantNewEmptyCryptoBrokerWalletProviderSettingException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantNewEmptyCryptoBrokerWalletSettingException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CantNewEmptyNegotiationBankAccountException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.exceptions.CouldNotCancelNegotiationException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.AmountToSellStep;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.ClauseInformation;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.ContractBasicInformation;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.CustomerBrokerNegotiationInformation;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.ExchangeRateStep;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.IndexInfoSummary;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.NegotiationStep;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.common.interfaces.SingleValueStep;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.crypto_broker.exceptions.CantGetCryptoBrokerIdentityListException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.crypto_broker.exceptions.CantGetCurrentIndexSummaryForStockCurrenciesException;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.crypto_broker.interfaces.CryptoBrokerWalletManager;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.crypto_broker.interfaces.StockInformation;
import com.bitdubai.fermat_cbp_api.layer.wallet_module.crypto_broker.interfaces.StockStatistics;
import com.bitdubai.fermat_ccp_api.layer.basic_wallet.bitcoin_wallet.interfaces.BitcoinWalletManager;
import com.bitdubai.fermat_ccp_api.layer.basic_wallet.common.enums.BalanceType;
import com.bitdubai.fermat_ccp_api.layer.basic_wallet.common.exceptions.CantLoadWalletException;
import com.bitdubai.fermat_ccp_api.layer.basic_wallet.bitcoin_wallet.interfaces.BitcoinWalletManager;
import com.bitdubai.fermat_ccp_api.layer.basic_wallet.common.enums.BalanceType;
import com.bitdubai.fermat_ccp_api.layer.basic_wallet.common.exceptions.CantLoadWalletException;
import com.bitdubai.fermat_cer_api.all_definition.interfaces.CurrencyPair;
import com.bitdubai.fermat_cer_api.all_definition.interfaces.ExchangeRate;
import com.bitdubai.fermat_cer_api.layer.provider.exceptions.CantGetExchangeRateException;
import com.bitdubai.fermat_cer_api.layer.provider.exceptions.UnsupportedCurrencyPairException;
import com.bitdubai.fermat_cer_api.layer.provider.interfaces.CurrencyExchangeRateProviderManager;
import com.bitdubai.fermat_cer_api.layer.search.exceptions.CantGetProviderException;
import com.bitdubai.fermat_cer_api.layer.search.interfaces.CurrencyExchangeProviderFilterManager;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantCreateCashMoneyWalletException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantGetCashMoneyWalletBalanceException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantGetCashMoneyWalletCurrencyException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.exceptions.CantLoadCashMoneyWalletException;
import com.bitdubai.fermat_csh_api.layer.csh_wallet.interfaces.CashMoneyWalletManager;
import com.bitdubai.fermat_wpd_api.layer.wpd_middleware.wallet_manager.exceptions.CantListWalletsException;
import com.bitdubai.fermat_wpd_api.layer.wpd_middleware.wallet_manager.interfaces.InstalledWallet;
import com.bitdubai.fermat_wpd_api.layer.wpd_middleware.wallet_manager.interfaces.WalletManagerManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Module Manager of Crypto Broker Module Plugin
 *
 * @author Nelson Ramirez
 * @version 1.0
 * @since 05/11/15
 * Modified by Franklin Marcano 23/12/15
 */
public class CryptoBrokerWalletModuleCryptoBrokerWalletManager implements CryptoBrokerWalletManager {
    private final WalletManagerManager walletManagerManager;
    private final com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.CryptoBrokerWalletManager cryptoBrokerWalletManager;
    private final BankMoneyWalletManager bankMoneyWalletManager;
    private final CustomerBrokerSaleNegotiationManager customerBrokerSaleNegotiationManager;
    private final BankMoneyRestockManager bankMoneyRestockManager;
    private final CashMoneyRestockManager cashMoneyRestockManager;
    private final CryptoMoneyRestockManager cryptoMoneyRestockManager;
    private final CashMoneyWalletManager cashMoneyWalletManager;
    private final BankMoneyDestockManager bankMoneyDestockManager;
    private final CashMoneyDestockManager cashMoneyDestockManager;
    private final CryptoMoneyDestockManager cryptoMoneyDestockManager;
    private final CustomerBrokerContractSaleManager customerBrokerContractSaleManager;
    private final CurrencyExchangeProviderFilterManager currencyExchangeProviderFilterManager;
    private final CryptoBrokerIdentityManager cryptoBrokerIdentityManager;
    private final CustomerBrokerUpdateManager customerBrokerUpdateManager;
    private final BitcoinWalletManager bitcoinWalletManager;

    /*
    *Constructor with Parameters
    */
    public CryptoBrokerWalletModuleCryptoBrokerWalletManager(WalletManagerManager walletManagerManager,
                                                             com.bitdubai.fermat_cbp_api.layer.wallet.crypto_broker.interfaces.CryptoBrokerWalletManager cryptoBrokerWalletManager,
                                                             BankMoneyWalletManager bankMoneyWalletManager,
                                                             CustomerBrokerSaleNegotiationManager customerBrokerSaleNegotiationManager,
                                                             BankMoneyRestockManager bankMoneyRestockManager,
                                                             CashMoneyRestockManager cashMoneyRestockManager,
                                                             CryptoMoneyRestockManager cryptoMoneyRestockManager,
                                                             CashMoneyWalletManager cashMoneyWalletManager,
                                                             BankMoneyDestockManager bankMoneyDestockManager,
                                                             CashMoneyDestockManager cashMoneyDestockManager,
                                                             CryptoMoneyDestockManager cryptoMoneyDestockManager,
                                                             CustomerBrokerContractSaleManager customerBrokerContractSaleManager,
                                                             CurrencyExchangeProviderFilterManager currencyExchangeProviderFilterManager,
                                                             CryptoBrokerIdentityManager cryptoBrokerIdentityManager,
                                                             CustomerBrokerUpdateManager customerBrokerUpdateManager,
                                                             BitcoinWalletManager bitcoinWalletManager) {
        this.walletManagerManager = walletManagerManager;
        this.cryptoBrokerWalletManager = cryptoBrokerWalletManager;
        this.bankMoneyWalletManager = bankMoneyWalletManager;
        this.customerBrokerSaleNegotiationManager = customerBrokerSaleNegotiationManager;
        this.bankMoneyRestockManager = bankMoneyRestockManager;
        this.cashMoneyRestockManager = cashMoneyRestockManager;
        this.cryptoMoneyRestockManager = cryptoMoneyRestockManager;
        this.cashMoneyWalletManager = cashMoneyWalletManager;
        this.bankMoneyDestockManager = bankMoneyDestockManager;
        this.cashMoneyDestockManager = cashMoneyDestockManager;
        this.cryptoMoneyDestockManager = cryptoMoneyDestockManager;
        this.customerBrokerContractSaleManager = customerBrokerContractSaleManager;
        this.currencyExchangeProviderFilterManager = currencyExchangeProviderFilterManager;
        this.cryptoBrokerIdentityManager = cryptoBrokerIdentityManager;
        this.customerBrokerUpdateManager = customerBrokerUpdateManager;
        this.bitcoinWalletManager = bitcoinWalletManager;
    }

    private List<ContractBasicInformation> contractsHistory;
    private List<CustomerBrokerNegotiationInformation> openNegotiations;
    private ArrayList<CryptoBrokerIdentity> listOfIdentities;

    public static final String CASH_IN_HAND = "Cash on Hand";
    public static final String CASH_DELIVERY = "Cash Delivery";
    public static final String BANK_TRANSFER = "Bank Transfer";
    public static final String CRYPTO_TRANSFER = "Crypto Transfer";

    private static final List<String> BROKER_BANK_ACCOUNTS = new ArrayList<>();
    public static final String BROKER_BANK_ACCOUNT_1 = "Banco: BOD\nTipo de cuenta: Corriente\nNro: 0105-2255-2221548739\nCliente: Brokers Asociados";
    public static final String BROKER_BANK_ACCOUNT_2 = "Banco: Provincial\nTipo de cuenta: Ahorro\nNro: 0114-3154-268548735\nCliente: Brokers Asociados";
    public static final String BROKER_BANK_ACCOUNT_3 = "Banco: Banesco\nTipo de cuenta: Corriente\nNro: 1124-0235-9981548701\nCliente: Brokers Asociados";

    public static final String CUSTOMER_BANK_ACCOUNT_1 = "Banco: Venezuela\nTipo de cuenta: Ahorro\nNro: 0001-2051-2221548714\nCliente: Angel Lacret";

    private static final List<String> BROKER_LOCATIONS = new ArrayList<>();
    public static final String BROKER_LOCATION_1 = "C.C. Sambil Maracaibo, Piso 2, Local 5A, al lado de Farmatodo";
    public static final String BROKER_LOCATION_2 = "C.C. Galerias, Piso 3, Local 5A, cerca de la feria de la comida";
    public static final String BROKER_LOCATION_3 = "Av. Padilla, Residencias San Martin, Casa #4-5";

    public static final String CUSTOMER_LOCATION_1 = "Centro Empresarial Totuma, Piso 2, Local 5A";
    public static final String CUSTOMER_LOCATION_2 = "Instituto de Calculo Aplicado, LUZ Facultad de Ingenieria";

    private static final String BROKER_CRYPTO_ADDRESS = "jn384jfnqirfjqn4834232039dj";
    private static final String CUSTOMER_CRYPTO_ADDRESS = "ioajpviq3489f9r8fj208245nds";

    private String merchandise = null, typeOfPayment = null, paymentCurrency = null;

    @Override
    public Collection<ContractBasicInformation> getContractsHistory(ContractStatus status, int max, int offset) throws CantGetContractHistoryException {
        List<ContractBasicInformation> filteredList = new ArrayList<>();
        try {

            List<ContractBasicInformation> contractsHistory = new ArrayList<>();

            CryptoBrokerWalletModuleContractBasicInformation contract = null;

            if (status != null) {

                //TODO: Preguntar de donde saco la demas informacion del contract "merchandise Moneda que recibe como mercancia el customer", "typeOfPayment Forma en la que paga el customer", "paymentCurrency Moneda que recibe como pago el broker"
                for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(status)) {
                    CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));
                    if (customerBrokerContractSale.getStatus().equals(status))
                        merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                    typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                    paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                    contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, status, customerBrokerContractSale, saleNegotiation);
                    filteredList.add(contract);
                }
                contractsHistory = filteredList;
            } else {
                for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(ContractStatus.CANCELLED)) {
                    CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));
                    if (customerBrokerContractSale.getStatus().equals(status))
                        merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                    typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                    paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                    contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, ContractStatus.CANCELLED, customerBrokerContractSale, saleNegotiation);
                    filteredList.add(contract);
                }
                for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(ContractStatus.COMPLETED)) {
                    CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));
                    if (customerBrokerContractSale.getStatus().equals(status))
                        merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                    typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                    paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                    contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, ContractStatus.COMPLETED, customerBrokerContractSale, saleNegotiation);
                    filteredList.add(contract);
                }
            }

            //TODO:Eliminar solo para que se terminen las pantallas
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.COMPLETED, null, null);
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.COMPLETED, null, null);
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.CANCELLED, null, null);
            filteredList.add(contract);
            return contractsHistory;

        } catch (Exception ex) {
            throw new CantGetContractHistoryException(ex);
        }
    }

    @Override
    public Collection<ContractBasicInformation> getContractsWaitingForBroker(int max, int offset) throws CantGetContractsWaitingForBrokerException {
        try {
            ContractBasicInformation contract;
            Collection<ContractBasicInformation> waitingForBroker = new ArrayList<>();

            for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(ContractStatus.PENDING_MERCHANDISE)) {
                CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));

                merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, ContractStatus.PENDING_MERCHANDISE, customerBrokerContractSale, saleNegotiation);
                waitingForBroker.add(contract);
            }
            for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(ContractStatus.MERCHANDISE_SUBMIT)) {
                CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));

                merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, ContractStatus.PAYMENT_SUBMIT, customerBrokerContractSale, saleNegotiation);
                waitingForBroker.add(contract);
            }
            //TODO:Eliminar solo para que se terminen las pantallas
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.PAYMENT_SUBMIT, null, null);
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.PAYMENT_SUBMIT, null, null);
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.PAYMENT_SUBMIT, null, null);
            waitingForBroker.add(contract);
            return waitingForBroker;

        } catch (Exception ex) {
            throw new CantGetContractsWaitingForBrokerException("Cant get contracts waiting for the broker", ex);
        }
    }

    private String getClauseType(CustomerBrokerSaleNegotiation saleNegotiation, ClauseType clauseType) {
        String value = null;
        try {
            for (Clause clause : saleNegotiation.getClauses()) {
                if (clause.getType().getCode() == clauseType.getCode()) {
                    value = clause.getValue();
                }
                if (clause.getType().getCode() == clauseType.getCode()) {
                    value = clause.getValue();
                }
                if (clause.getType().getCode() == clauseType.getCode()) {
                    value = clause.getValue();
                }
            }
        } catch (CantGetListClauseException e) {
            e.printStackTrace();
        }

        return value;
    }

    @Override
    public Collection<ContractBasicInformation> getContractsWaitingForCustomer(int max, int offset) throws CantGetContractsWaitingForCustomerException {
        try {
            ContractBasicInformation contract;
            Collection<ContractBasicInformation> waitingForCustomer = new ArrayList<>();

            for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(ContractStatus.MERCHANDISE_SUBMIT)) {
                CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));
//                for(Clause clause : saleNegotiation.getClauses())
//                {
//                    if (clause.getType().getCode() == ClauseType.CUSTOMER_CURRENCY.getCode())
//                    {
//                        merchandise = clause.getValue();
//                    }
//                    if (clause.getType().getCode() == ClauseType.CUSTOMER_PAYMENT_METHOD.getCode())
//                    {
//                        typeOfPayment = clause.getValue();
//                    }
//                    if (clause.getType().getCode() == ClauseType.BROKER_CURRENCY.getCode())
//                    {
//                        paymentCurrency = clause.getValue();
//                    }
//                }
                merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, ContractStatus.MERCHANDISE_SUBMIT, customerBrokerContractSale, saleNegotiation);
                waitingForCustomer.add(contract);
            }
            for (CustomerBrokerContractSale customerBrokerContractSale : customerBrokerContractSaleManager.getCustomerBrokerContractSaleForStatus(ContractStatus.PENDING_PAYMENT)) {
                CustomerBrokerSaleNegotiation saleNegotiation = customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(UUID.fromString(customerBrokerContractSale.getNegotiatiotId()));

                merchandise = getClauseType(saleNegotiation, ClauseType.CUSTOMER_CURRENCY);
                typeOfPayment = getClauseType(saleNegotiation, ClauseType.CUSTOMER_PAYMENT_METHOD);
                paymentCurrency = getClauseType(saleNegotiation, ClauseType.BROKER_CURRENCY);

                contract = new CryptoBrokerWalletModuleContractBasicInformation(customerBrokerContractSale.getPublicKeyCustomer(), merchandise, typeOfPayment, paymentCurrency, ContractStatus.PENDING_PAYMENT, customerBrokerContractSale, saleNegotiation);
                waitingForCustomer.add(contract);
            }
            //TODO:Eliminar solo para que se terminen las pantallas
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.MERCHANDISE_SUBMIT, null, null);
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.MERCHANDISE_SUBMIT, null, null);
            contract = new CryptoBrokerWalletModuleContractBasicInformation("publicKeyCustomer", "merchandise", "typeOfPayment", "paymentCurrency", ContractStatus.MERCHANDISE_SUBMIT, null, null);
            waitingForCustomer.add(contract);
            return waitingForCustomer;

        } catch (Exception ex) {
            throw new CantGetContractsWaitingForCustomerException("Cant get contracts waiting for the customers", ex);
        }
    }

    @Override
    public Collection<CustomerBrokerNegotiationInformation> getNegotiationsWaitingForBroker(int max, int offset) throws CantGetNegotiationsWaitingForBrokerException {
        try {
            CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation customerBrokerNegotiationInformation = null;

            Collection<CustomerBrokerNegotiationInformation> waitingForBroker = new ArrayList<>();
            for (CustomerBrokerSaleNegotiation customerBrokerSaleNegotiation : customerBrokerSaleNegotiationManager.getNegotiationsByStatus(NegotiationStatus.WAITING_FOR_BROKER)) {
                customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation(customerBrokerSaleNegotiation.getCustomerPublicKey(), NegotiationStatus.WAITING_FOR_BROKER);
                waitingForBroker.add(customerBrokerNegotiationInformation);
            }
            //TODO:Eliminar solo para que se terminen las pantallas
            customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("publicKeyCustomer", NegotiationStatus.WAITING_FOR_BROKER);
            customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("publicKeyCustomer", NegotiationStatus.WAITING_FOR_BROKER);
            customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("publicKeyCustomer", NegotiationStatus.WAITING_FOR_BROKER);
            waitingForBroker.add(customerBrokerNegotiationInformation);
            return waitingForBroker;

        } catch (Exception ex) {
            throw new CantGetNegotiationsWaitingForBrokerException("Cant get negotiations waiting for the broker", ex, "", "");
        }
    }

    @Override
    //Sale Negotiation Collection<CustomerBrokerSaleNegotiation> getNegotiationsByStatus pasando el Waiting for Customer
    public Collection<CustomerBrokerNegotiationInformation> getNegotiationsWaitingForCustomer(int max, int offset) throws CantGetNegotiationsWaitingForCustomerException {
        try {
            CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation customerBrokerNegotiationInformation = null;

            Collection<CustomerBrokerNegotiationInformation> waitingForCustomer = new ArrayList<>();
            for (CustomerBrokerSaleNegotiation customerBrokerSaleNegotiation : customerBrokerSaleNegotiationManager.getNegotiationsByStatus(NegotiationStatus.WAITING_FOR_CUSTOMER)) {
                customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation(customerBrokerSaleNegotiation.getCustomerPublicKey(), NegotiationStatus.WAITING_FOR_CUSTOMER);
                waitingForCustomer.add(customerBrokerNegotiationInformation);
            }
            //TODO:Eliminar solo para que se terminen las pantallas
            customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("publicKeyCustomer", NegotiationStatus.WAITING_FOR_CUSTOMER);
            customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("publicKeyCustomer", NegotiationStatus.WAITING_FOR_CUSTOMER);
            customerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("publicKeyCustomer", NegotiationStatus.WAITING_FOR_CUSTOMER);
            waitingForCustomer.add(customerBrokerNegotiationInformation);
            return waitingForCustomer;

        } catch (Exception ex) {
            throw new CantGetNegotiationsWaitingForCustomerException("Cant get negotiations waiting for the customers", ex, "", "");
        }
    }

    @Override
    public CustomerBrokerNegotiationInformation getNegotiationInformation(UUID negotiationID) throws CantGetNegotiationInformationException, CantGetListSaleNegotiationsException {
        CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation cryptoBrokerWalletModuleCustomerBrokerNegotiationInformation = null;
        cryptoBrokerWalletModuleCustomerBrokerNegotiationInformation = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation(customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(negotiationID).getCustomerPublicKey(), customerBrokerSaleNegotiationManager.getNegotiationsByNegotiationId(negotiationID).getStatus());
        return cryptoBrokerWalletModuleCustomerBrokerNegotiationInformation;
    }

    /**
     * @param negotiationType
     * @return Collection<NegotiationLocations>
     */
    @Override
    public Collection<NegotiationLocations> getAllLocations(NegotiationType negotiationType) throws CantGetListLocationsSaleException {
        Collection<NegotiationLocations> negotiationLocations = null;
        if (negotiationType.getCode() == NegotiationType.SALE.getCode()) {
            negotiationLocations = customerBrokerSaleNegotiationManager.getAllLocations();
        }
        return negotiationLocations;
    }

    @Override //TODO: Implementar falta walletPublicKey
    public boolean associateIdentity(String brokerPublicKey) {
        return false;
    }

    @Override //TODO: Implementar CustomerBrokerUpdateManager Negotiation Transaction
    public CustomerBrokerNegotiationInformation cancelNegotiation(CustomerBrokerNegotiationInformation negotiation, String reason) throws CouldNotCancelNegotiationException, CantCancelNegotiationException {
        CustomerBrokerSaleNegotiationImpl customerBrokerSaleNegotiation = new CustomerBrokerSaleNegotiationImpl(negotiation.getNegotiationId());
        customerBrokerSaleNegotiation.setCancelReason(reason);
        customerBrokerUpdateManager.cancelNegotiation(customerBrokerSaleNegotiation);
        return negotiation;
    }

    @Override //TODO: Implementar CER provider seleccionado en la wallet
    public Collection<IndexInfoSummary> getCurrentIndexSummaryForStockCurrencies() throws CantGetCurrentIndexSummaryForStockCurrenciesException {
        try {
            IndexInfoSummary indexInfoSummary;
            Collection<IndexInfoSummary> summaryList = new ArrayList<>();

            indexInfoSummary = new CryptoBrokerWalletModuleIndexInfoSummary(CryptoCurrency.BITCOIN, FiatCurrency.US_DOLLAR, 240.62, 235.87);
            summaryList.add(indexInfoSummary);
            indexInfoSummary = new CryptoBrokerWalletModuleIndexInfoSummary(FiatCurrency.VENEZUELAN_BOLIVAR, CryptoCurrency.BITCOIN, 245000, 240000);
            summaryList.add(indexInfoSummary);
            indexInfoSummary = new CryptoBrokerWalletModuleIndexInfoSummary(FiatCurrency.VENEZUELAN_BOLIVAR, FiatCurrency.US_DOLLAR, 840, 800);
            summaryList.add(indexInfoSummary);
            indexInfoSummary = new CryptoBrokerWalletModuleIndexInfoSummary(FiatCurrency.US_DOLLAR, FiatCurrency.EURO, 1.2, 1.1);
            summaryList.add(indexInfoSummary);

            return summaryList;

        } catch (Exception ex) {
            throw new CantGetCurrentIndexSummaryForStockCurrenciesException(ex);
        }
    }

    @Override
    public List<CryptoBrokerIdentity> getListOfIdentities() throws CantGetCryptoBrokerIdentityListException, CantListCryptoBrokerIdentitiesException {
        List<CryptoBrokerIdentity> cryptoBrokerIdentities = cryptoBrokerIdentityManager.listIdentitiesFromCurrentDeviceUser();
        return cryptoBrokerIdentities;//getListOfIdentitiesTestData();
    }

    @Override //TODO: Implementar
    public List<String> getPaymentMethods(String currencyToSell, String brokerWalletPublicKey) throws CryptoBrokerWalletNotFoundException, CantGetCryptoBrokerWalletSettingException {
        List<String> paymentMethod = new ArrayList<>();

        List<CryptoBrokerWalletAssociatedSetting> associatedWallets = getCryptoBrokerWalletAssociatedSettings(brokerWalletPublicKey);

        for (CryptoBrokerWalletAssociatedSetting associatedWallet : associatedWallets) {
            FermatEnum merchandise = associatedWallet.getMerchandise();

            if (merchandise.getCode().equals(currencyToSell)) {
                CurrencyType currencyType = associatedWallet.getCurrencyType();
                paymentMethod.add(currencyType.getFriendlyname());
            }
        }

        return paymentMethod;
    }

    @Override
    public List<NegotiationStep> getSteps(CustomerBrokerNegotiationInformation negotiationInfo) {
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
        List<NegotiationStep> data = new ArrayList<>();
        int stepNumber = 0;

        // exchange rate step
        Map<ClauseType, ClauseInformation> clauses = negotiationInfo.getClauses();
        String currencyToSell = clauses.get(ClauseType.CUSTOMER_CURRENCY).getValue();
        String currencyToReceive = clauses.get(ClauseType.BROKER_CURRENCY).getValue();
        String exchangeRate = clauses.get(ClauseType.EXCHANGE_RATE).getValue();
        String suggestedExchangeRate = decimalFormat.format(215.25); // TODO este valor me lo da la wallet
        data.add(new ExchangeRateStepImp(++stepNumber, currencyToSell, currencyToReceive, suggestedExchangeRate, exchangeRate));

        // amount to sell step
        String amountToSell = clauses.get(ClauseType.CUSTOMER_CURRENCY_QUANTITY).getValue();
        String amountToReceive = clauses.get(ClauseType.BROKER_CURRENCY_QUANTITY).getValue();
        data.add(new AmountToSellStepImp(++stepNumber, currencyToSell, currencyToReceive, amountToSell, amountToReceive, exchangeRate));

        // Payment Method
        String paymentMethod = clauses.get(ClauseType.CUSTOMER_PAYMENT_METHOD).getValue();
        data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.PAYMENT_METHOD, paymentMethod));

        // Broker Bank Account
        ClauseInformation clauseInformation = clauses.get(ClauseType.BROKER_BANK_ACCOUNT);
        if (clauseInformation != null) {
            String brokerBankAccount = clauseInformation.getValue();
            data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.BROKER_BANK_ACCOUNT, brokerBankAccount));
        }

        // Broker Locations
        clauseInformation = clauses.get(ClauseType.BROKER_PLACE_TO_DELIVER);
        if (clauseInformation != null) {
            String brokerBankAccount = clauseInformation.getValue();
            data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.BROKER_LOCATION, brokerBankAccount));
        }

        // Customer Bank Account
        clauseInformation = clauses.get(ClauseType.CUSTOMER_BANK_ACCOUNT);
        if (clauseInformation != null) {
            String brokerBankAccount = clauseInformation.getValue();
            data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.CUSTOMER_BANK_ACCOUNT, brokerBankAccount));
        }

        // Customer Location
        clauseInformation = clauses.get(ClauseType.CUSTOMER_PLACE_TO_DELIVER);
        if (clauseInformation != null) {
            String brokerBankAccount = clauseInformation.getValue();
            data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.CUSTOMER_LOCATION, brokerBankAccount));
        }

        // Datetime to Pay
        String datetimeToPay = clauses.get(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER).getValue();
        data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.DATE_TIME_TO_PAY, datetimeToPay));

        // Datetime to Deliver
        String datetimeToDeliver = clauses.get(ClauseType.BROKER_DATE_TIME_TO_DELIVER).getValue();
        data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.DATE_TIME_TO_DELIVER, datetimeToDeliver));

        // Datetime to Deliver
        String expirationDatetime = String.valueOf(negotiationInfo.getNegotiationExpirationDate());
        data.add(new SingleValueStepImp(++stepNumber, NegotiationStepType.EXPIRATION_DATE_TIME, expirationDatetime));

        return data;
    }

    @Override
    public void modifyNegotiationStepValues(NegotiationStep step, NegotiationStepStatus status, String... newValues) {
        NegotiationStepType negotiationStepType = step.getType();

        switch (negotiationStepType) {
            case EXCHANGE_RATE:
                ExchangeRateStepImp exchangeRateStep = (ExchangeRateStepImp) step;
                exchangeRateStep.setExchangeRate(newValues[0]);
                exchangeRateStep.setStatus(status);
                break;
            case AMOUNT_TO_SALE:
                AmountToSellStepImp amountToSellStep = (AmountToSellStepImp) step;
                amountToSellStep.setAmountToSell(newValues[0]);
                amountToSellStep.setAmountToReceive(newValues[1]);
                amountToSellStep.setExchangeRateValue(newValues[2]);
                amountToSellStep.setStatus(status);
                break;
            default:
                SingleValueStepImp singleValueStep = (SingleValueStepImp) step;
                singleValueStep.setValue(newValues[0]);
                singleValueStep.setStatus(status);
                break;
        }
    }

    @Override
    public boolean isNothingLeftToConfirm(List<NegotiationStep> dataSet) {
        for (NegotiationStep step : dataSet) {
            if (step.getStatus() == NegotiationStepStatus.CONFIRM) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CustomerBrokerNegotiationInformation sendNegotiationSteps(CustomerBrokerNegotiationInformation data, List<NegotiationStep> dataSet) {

        CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation wrapper = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation(data);
        CryptoBrokerWalletModuleClauseInformation clause;
        ClauseInformation clauseInfo;
        Map<ClauseType, ClauseInformation> clauses = wrapper.getClauses();

        wrapper.setStatus(NegotiationStatus.WAITING_FOR_CUSTOMER);
        wrapper.setLastNegotiationUpdateDate(Calendar.getInstance(Locale.getDefault()).getTimeInMillis());

        for (NegotiationStep step : dataSet) {
            NegotiationStepType type = step.getType();
            switch (type) {
                case AMOUNT_TO_SALE:
                    AmountToSellStep amountToSellStep = (AmountToSellStep) step;

                    clauseInfo = clauses.get(ClauseType.BROKER_CURRENCY_QUANTITY);
                    clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), amountToSellStep.getAmountToReceive(), clauseInfo.getStatus());
                    clauses.put(ClauseType.BROKER_CURRENCY_QUANTITY, clause);

                    clauseInfo = clauses.get(ClauseType.CUSTOMER_CURRENCY_QUANTITY);
                    clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), amountToSellStep.getAmountToSell(), clauseInfo.getStatus());
                    clauses.put(ClauseType.CUSTOMER_CURRENCY_QUANTITY, clause);
                    break;

                case BROKER_BANK_ACCOUNT:
                    SingleValueStep singleValueStep = (SingleValueStep) step;
                    clauseInfo = clauses.get(ClauseType.BROKER_BANK_ACCOUNT);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.BROKER_BANK_ACCOUNT, clause);
                    }
                    break;

                case BROKER_LOCATION:
                    singleValueStep = (SingleValueStep) step;
                    clauseInfo = clauses.get(ClauseType.BROKER_PLACE_TO_DELIVER);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.BROKER_PLACE_TO_DELIVER, clause);
                    }
                    break;

                case CUSTOMER_BANK_ACCOUNT:
                    singleValueStep = (SingleValueStep) step;
                    clauseInfo = clauses.get(ClauseType.CUSTOMER_BANK_ACCOUNT);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.CUSTOMER_BANK_ACCOUNT, clause);
                    }
                    break;

                case CUSTOMER_LOCATION:
                    singleValueStep = (SingleValueStep) step;
                    clauseInfo = clauses.get(ClauseType.CUSTOMER_PLACE_TO_DELIVER);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.CUSTOMER_PLACE_TO_DELIVER, clause);
                    }
                    break;

                case DATE_TIME_TO_DELIVER:
                    singleValueStep = (SingleValueStep) step;
                    clauseInfo = clauses.get(ClauseType.BROKER_DATE_TIME_TO_DELIVER);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.BROKER_DATE_TIME_TO_DELIVER, clause);
                    }
                    break;

                case DATE_TIME_TO_PAY:
                    singleValueStep = (SingleValueStep) step;

                    clauseInfo = clauses.get(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, clause);
                    }
                    break;

                case EXCHANGE_RATE:
                    ExchangeRateStep exchangeRateStep = (ExchangeRateStep) step;
                    clauseInfo = clauses.get(ClauseType.EXCHANGE_RATE);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), exchangeRateStep.getExchangeRate(), clauseInfo.getStatus());
                        clauses.put(ClauseType.EXCHANGE_RATE, clause);
                    }
                    break;

                case PAYMENT_METHOD:
                    singleValueStep = (SingleValueStep) step;
                    clauseInfo = clauses.get(ClauseType.CUSTOMER_PAYMENT_METHOD);
                    if (clauseInfo != null) {
                        clause = new CryptoBrokerWalletModuleClauseInformation(clauseInfo.getType(), singleValueStep.getValue(), clauseInfo.getStatus());
                        clauses.put(ClauseType.CUSTOMER_PAYMENT_METHOD, clause);
                    }
                    break;

                case EXPIRATION_DATE_TIME:
                    singleValueStep = (SingleValueStep) step;
                    wrapper.setExpirationDatetime(Long.valueOf(singleValueStep.getValue()));
                    break;
            }
        }

        // TODO el wrapper se le va a pasar al plugin de Yordin "Customer Broker Update Negotiation Transaction"
        //Negotiation Transaccion Update createCustomerBrokerUpdateSaleNegotiationTranasction
        for (int i = 0; i < openNegotiations.size(); i++) {
            CustomerBrokerNegotiationInformation item = openNegotiations.get(i);
            if (item.getNegotiationId().equals(wrapper.getNegotiationId())) {
                openNegotiations.set(i, wrapper);
                break;
            }
        }

        return wrapper;
    }

    /**
     * This method list all wallet installed in device, start the transaction
     */
    @Override
    public List<InstalledWallet> getInstallWallets() throws CantListWalletsException {
        return walletManagerManager.getInstalledWallets();
    }

    @Override
    public CryptoBrokerWalletSettingSpread newEmptyCryptoBrokerWalletSetting() throws CantNewEmptyCryptoBrokerWalletSettingException {
        CryptoBrokerWalletSettingSpreadImpl cryptoBrokerWalletSettingSpread = new CryptoBrokerWalletSettingSpreadImpl();
        return cryptoBrokerWalletSettingSpread;
    }

    @Override
    public CryptoBrokerWalletAssociatedSetting newEmptyCryptoBrokerWalletAssociatedSetting() throws CantNewEmptyCryptoBrokerWalletAssociatedSettingException {
        CryptoBrokerWalletAssociatedSettingImpl cryptoBrokerWalletAssociatedSetting = new CryptoBrokerWalletAssociatedSettingImpl();
        return cryptoBrokerWalletAssociatedSetting;
    }

    @Override
    public CryptoBrokerWalletProviderSetting newEmptyCryptoBrokerWalletProviderSetting() throws CantNewEmptyCryptoBrokerWalletProviderSettingException {
        CryptoBrokerWalletProviderSettingImpl cryptoBrokerWalletProviderSettingImpl = new CryptoBrokerWalletProviderSettingImpl();
        return cryptoBrokerWalletProviderSettingImpl;
    }

    @Override
    public NegotiationBankAccount newEmptyNegotiationBankAccount(UUID bankAccountId, String bankAccount, FiatCurrency fiatCurrency) throws CantNewEmptyNegotiationBankAccountException {
        return new NegotiationBankAccountImpl(bankAccountId, bankAccount, fiatCurrency);
    }

    @Override
    public BankAccountNumber newEmptyBankAccountNumber(String bankName, BankAccountType bankAccountType, String alias, String account, FiatCurrency currencyType) throws CantNewEmptyBankAccountException {
        return new BankAccountNumberImpl(bankName, bankAccountType, alias, account, currencyType);
    }

    @Override
    public void addNewAccount(BankAccountNumber bankAccountNumber, String walletPublicKey) throws CantAddNewAccountException, CantLoadBankMoneyWalletException {
        bankMoneyWalletManager.loadBankMoneyWallet(walletPublicKey).createBankName(bankAccountNumber.getBankName());
        bankMoneyWalletManager.loadBankMoneyWallet(walletPublicKey).addNewAccount(bankAccountNumber);
    }

    /**
     * Returns an object which allows getting and modifying (credit/debit) the Book Balance
     *
     * @param walletPublicKey
     * @param fiatCurrency
     * @return A CashMoneyWalletBalance object
     */
    @Override
    public void createCashMoneyWallet(String walletPublicKey, FiatCurrency fiatCurrency) throws CantCreateCashMoneyWalletException {
        cashMoneyWalletManager.createCashMoneyWallet(walletPublicKey, fiatCurrency);
    }

    /**
     * Through the method <code>createCryptoBrokerIdentity</code> you can create a new crypto broker identity.
     *
     * @param alias the alias of the crypto broker that we want to create.
     * @param image an image that represents the crypto broker. it will be shown to other actors when they try to connect.
     * @return an instance of the recent created crypto broker identity.
     * @throws CantCreateCryptoBrokerIdentityException if something goes wrong.
     */
    @Override
    public CryptoBrokerIdentity createCryptoBrokerIdentity(String alias, byte[] image) throws CantCreateCryptoBrokerIdentityException, CryptoBrokerIdentityAlreadyExistsException {
        return cryptoBrokerIdentityManager.createCryptoBrokerIdentity(alias, image);
    }

    @Override
    public void saveWalletSetting(CryptoBrokerWalletSettingSpread cryptoBrokerWalletSettingSpread, String publicKeyWalletCryptoBrokerInstall) throws CantSaveCryptoBrokerWalletSettingException, CryptoBrokerWalletNotFoundException, CantGetCryptoBrokerWalletSettingException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        publicKeyWalletCryptoBrokerInstall = "walletPublicKeyTest";
        cryptoBrokerWalletManager.loadCryptoBrokerWallet(publicKeyWalletCryptoBrokerInstall).getCryptoWalletSetting().saveCryptoBrokerWalletSpreadSetting(cryptoBrokerWalletSettingSpread);
    }

    @Override
    public void saveWalletSettingAssociated(CryptoBrokerWalletAssociatedSetting cryptoBrokerWalletAssociatedSetting, String publicKeyWalletCryptoBrokerInstall) throws CantGetCryptoBrokerWalletSettingException, CryptoBrokerWalletNotFoundException, CantSaveCryptoBrokerWalletSettingException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        publicKeyWalletCryptoBrokerInstall = "walletPublicKeyTest";
        cryptoBrokerWalletManager.loadCryptoBrokerWallet(publicKeyWalletCryptoBrokerInstall).getCryptoWalletSetting().saveCryptoBrokerWalletAssociatedSetting(cryptoBrokerWalletAssociatedSetting);
    }

    @Override
    public boolean isWalletConfigured(String publicKeyWalletCryptoBrokerInstall) throws CryptoBrokerWalletNotFoundException, CantGetCryptoBrokerWalletSettingException {
        //TODO: Faltar validar los otros de los demas plugins
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        publicKeyWalletCryptoBrokerInstall = "walletPublicKeyTest";
        boolean isConfigured = true;
        CryptoBrokerWalletSettingSpread cryptoBrokerWalletSettingSpread = cryptoBrokerWalletManager.loadCryptoBrokerWallet(publicKeyWalletCryptoBrokerInstall).getCryptoWalletSetting().getCryptoBrokerWalletSpreadSetting();
        List<CryptoBrokerWalletAssociatedSetting> cryptoBrokerWalletAssociatedSettings = cryptoBrokerWalletManager.loadCryptoBrokerWallet(publicKeyWalletCryptoBrokerInstall).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings();
        List<CryptoBrokerWalletProviderSetting> cryptoBrokerWalletProviderSettings = cryptoBrokerWalletManager.loadCryptoBrokerWallet(publicKeyWalletCryptoBrokerInstall).getCryptoWalletSetting().getCryptoBrokerWalletProviderSettings();
        if (cryptoBrokerWalletSettingSpread == null || cryptoBrokerWalletAssociatedSettings.isEmpty() || cryptoBrokerWalletProviderSettings.isEmpty()) {
            isConfigured = false;
        }
        return isConfigured;
    }

    /**
     * @param location
     * @param uri
     * @throws CantCreateLocationSaleException
     */
    @Override
    public void createNewLocation(String location, String uri) throws CantCreateLocationSaleException {
        customerBrokerSaleNegotiationManager.createNewLocation(location, uri);
    }

    /**
     * @param location
     * @throws CantUpdateLocationSaleException
     */
    @Override
    public void updateLocation(NegotiationLocations location) throws CantUpdateLocationSaleException {
        customerBrokerSaleNegotiationManager.updateLocation(location);
    }

    /**
     * @param location
     * @throws CantDeleteLocationSaleException
     */
    @Override
    public void deleteLocation(NegotiationLocations location) throws CantDeleteLocationSaleException {
        customerBrokerSaleNegotiationManager.deleteLocation(location);
    }

    @Override
    public List<BankAccountNumber> getAccounts(String walletPublicKey) throws CantLoadBankMoneyWalletException {
        return bankMoneyWalletManager.loadBankMoneyWallet(walletPublicKey).getAccounts();
    }

    /**
     * Returns an object which allows getting and modifying (credit/debit) the Book Balance
     *
     * @param walletPublicKey
     * @return A FiatCurrency object
     */
    @Override
    public FiatCurrency getCashCurrency(String walletPublicKey) throws CantGetCashMoneyWalletCurrencyException, CantLoadCashMoneyWalletException {
        return cashMoneyWalletManager.loadCashMoneyWallet(walletPublicKey).getCurrency();
    }

    /**
     * Method that create the transaction Restock
     *
     * @param publicKeyActor
     * @param fiatCurrency
     * @param cbpWalletPublicKey
     * @param bankWalletPublicKey
     * @param bankAccount
     * @param amount
     * @param memo
     * @param priceReference
     * @param originTransaction
     * @throws CantCreateBankMoneyRestockException
     */
    @Override
    public void createTransactionRestockBank(String publicKeyActor, FiatCurrency fiatCurrency, String cbpWalletPublicKey, String bankWalletPublicKey, String bankAccount, BigDecimal amount, String memo, BigDecimal priceReference, OriginTransaction originTransaction) throws CantCreateBankMoneyRestockException {
        bankMoneyRestockManager.createTransactionRestock(publicKeyActor, fiatCurrency, cbpWalletPublicKey, bankWalletPublicKey, bankAccount, amount, memo, priceReference, originTransaction);
    }

    /**
     * Method that create the transaction Destock
     *
     * @param publicKeyActor
     * @param fiatCurrency
     * @param cbpWalletPublicKey
     * @param bankWalletPublicKey
     * @param bankAccount
     * @param amount
     * @param memo
     * @param priceReference
     * @param originTransaction
     * @throws CantCreateBankMoneyDestockException
     */
    @Override
    public void createTransactionDestockBank(String publicKeyActor, FiatCurrency fiatCurrency, String cbpWalletPublicKey, String bankWalletPublicKey, String bankAccount, BigDecimal amount, String memo, BigDecimal priceReference, OriginTransaction originTransaction) throws CantCreateBankMoneyDestockException {
        bankMoneyDestockManager.createTransactionDestock(publicKeyActor, fiatCurrency, cbpWalletPublicKey, bankWalletPublicKey, bankAccount, amount, memo, priceReference, originTransaction);
    }

    /**
     * Method that create the transaction Destock
     *
     * @param publicKeyActor
     * @param fiatCurrency
     * @param cbpWalletPublicKey
     * @param cshWalletPublicKey
     * @param cashReference
     * @param amount
     * @param memo
     * @param priceReference
     * @param originTransaction
     * @throws CantCreateCashMoneyRestockException
     */
    @Override
    public void createTransactionRestockCash(String publicKeyActor, FiatCurrency fiatCurrency, String cbpWalletPublicKey, String cshWalletPublicKey, String cashReference, BigDecimal amount, String memo, BigDecimal priceReference, OriginTransaction originTransaction) throws CantCreateCashMoneyRestockException {
        cashMoneyRestockManager.createTransactionRestock(publicKeyActor, fiatCurrency, cbpWalletPublicKey, cshWalletPublicKey, cashReference, amount, memo, priceReference, originTransaction);
    }

    /**
     * Method that create the transaction Destock
     *
     * @param publicKeyActor
     * @param fiatCurrency
     * @param cbpWalletPublicKey
     * @param cshWalletPublicKey
     * @param cashReference
     * @param amount
     * @param memo
     * @param priceReference
     * @param originTransaction
     * @throws CantCreateCashMoneyDestockException
     */
    @Override
    public void createTransactionDestockCash(String publicKeyActor, FiatCurrency fiatCurrency, String cbpWalletPublicKey, String cshWalletPublicKey, String cashReference, BigDecimal amount, String memo, BigDecimal priceReference, OriginTransaction originTransaction) throws CantCreateCashMoneyDestockException {
        cashMoneyDestockManager.createTransactionDestock(publicKeyActor, fiatCurrency, cbpWalletPublicKey, cshWalletPublicKey, cashReference, amount, memo, priceReference, originTransaction);
    }

    /**
     * Method that create the transaction Destock
     *
     * @param publicKeyActor
     * @param cryptoCurrency
     * @param cbpWalletPublicKey
     * @param cryWalletPublicKey
     * @param amount
     * @param memo
     * @param priceReference
     * @param originTransaction
     * @throws CantCreateCashMoneyRestockException
     */
    @Override
    public void createTransactionRestockCrypto(String publicKeyActor, CryptoCurrency cryptoCurrency, String cbpWalletPublicKey, String cryWalletPublicKey, BigDecimal amount, String memo, BigDecimal priceReference, OriginTransaction originTransaction) throws CantCreateCryptoMoneyRestockException {
        cryptoMoneyRestockManager.createTransactionRestock(publicKeyActor, cryptoCurrency, cbpWalletPublicKey, cryWalletPublicKey, amount, memo, priceReference, originTransaction);
    }

    /**
     * Method that create the transaction Destock
     *
     * @param publicKeyActor
     * @param cryptoCurrency
     * @param cbpWalletPublicKey
     * @param cryWalletPublicKey
     * @param amount
     * @param memo
     * @param priceReference
     * @param originTransaction
     * @throws CantCreateCryptoMoneyDestockException
     */
    @Override
    public void createTransactionDestockCrypto(String publicKeyActor, CryptoCurrency cryptoCurrency, String cbpWalletPublicKey, String cryWalletPublicKey, BigDecimal amount, String memo, BigDecimal priceReference, OriginTransaction originTransaction) throws CantCreateCryptoMoneyDestockException {
        cryptoMoneyDestockManager.createTransactionDestock(publicKeyActor, cryptoCurrency, cbpWalletPublicKey, cryWalletPublicKey, amount, memo, priceReference, originTransaction);
    }

    /**
     * This method load the list CryptoBrokerStockTransaction
     *
     * @param merchandise
     * @param fiatCurrency
     * @param currencyType
     * @return FiatIndex
     * @throws CantGetCryptoBrokerMarketRateException
     */
    @Override
    public FiatIndex getMarketRate(FermatEnum merchandise, FiatCurrency fiatCurrency, CurrencyType currencyType, String walletPublicKey) throws CantGetCryptoBrokerMarketRateException, CryptoBrokerWalletNotFoundException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        return cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getMarketRate(merchandise, fiatCurrency, currencyType);
    }

    @Override
    public CustomerBrokerNegotiationInformation setMemo(String memo, CustomerBrokerNegotiationInformation data) {
        CustomerBrokerNegotiationInformation customerBrokerNegotiationInformation = data;
        customerBrokerNegotiationInformation.setMemo(memo);
        return customerBrokerNegotiationInformation;
    }

    /**
     * @param ContractId
     * @return a CustomerBrokerContractSale with information of contract with ContractId
     * @throws CantGetListCustomerBrokerContractSaleException
     */
    @Override
    public CustomerBrokerContractSale getCustomerBrokerContractSaleForContractId(String ContractId) throws CantGetListCustomerBrokerContractSaleException {
        return customerBrokerContractSaleManager.getCustomerBrokerContractSaleForContractId(ContractId);
    }

    /**
     * This method load the list CryptoBrokerWalletProviderSetting
     *
     * @return List<CryptoBrokerWalletProviderSetting>
     * @throws CantGetCryptoBrokerWalletSettingException
     */
    @Override
    public List<CryptoBrokerWalletProviderSetting> getCryptoBrokerWalletProviderSettings(String walletPublicKey) throws CantGetCryptoBrokerWalletSettingException, CryptoBrokerWalletNotFoundException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        return cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletProviderSettings();
    }

    /**
     * Returns a list of Providers able to obtain the  CurrencyPair for providers wallet associated
     *
     * @param currencyPair
     * @param walletPublicKey
     * @return a map containing both the ProviderID and the CurrencyPair for providers wallet associated
     */
    @Override
    public Map<String, CurrencyPair> getWalletProviderAssociatedCurrencyPairs(CurrencyPair currencyPair, String walletPublicKey) throws CryptoBrokerWalletNotFoundException, CantGetCryptoBrokerWalletSettingException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        CurrencyPairImpl currencyPair1 = null;
        CurrencyPairImpl currencyPair2 = null;
        CurrencyPairImpl currencyPair3 = null;
        CurrencyPairImpl currencyPair4 = null;
        CurrencyPairImpl currencyPair5 = null;
        CurrencyPairImpl currencyPair6 = null;

        Map<String, CurrencyPair> map = new HashMap();

        if (!cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().isEmpty()) {
            if (cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().size() == 2) {
                currencyPair1 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(0).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(1).getMerchandise());

                currencyPair2 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(1).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(0).getMerchandise());

                map.put("par1", currencyPair1);
                map.put("par2", currencyPair2);
            }
            if (cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().size() == 3) {
                currencyPair1 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(0).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(1).getMerchandise());

                currencyPair2 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(0).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(2).getMerchandise());

                currencyPair3 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(1).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(0).getMerchandise());

                currencyPair4 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(1).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(2).getMerchandise());

                currencyPair5 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(2).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(0).getMerchandise());

                currencyPair6 = new CurrencyPairImpl(
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(2).getMerchandise(),
                        (Currency) cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings().get(1).getMerchandise());

                map.put("par1", currencyPair1);
                map.put("par2", currencyPair2);
                map.put("par3", currencyPair3);
                map.put("par4", currencyPair4);
                map.put("par5", currencyPair5);
                map.put("par6", currencyPair6);

            }
        }

        return map;
    }

    /**
     * This method load the list CryptoBrokerWalletProviderSetting
     *
     * @return List<CryptoBrokerWalletAssociatedSetting>
     * @throws CantGetCryptoBrokerWalletSettingException
     */
    @Override
    public List<CryptoBrokerWalletAssociatedSetting> getCryptoBrokerWalletAssociatedSettings(String walletPublicKey) throws CantGetCryptoBrokerWalletSettingException, CryptoBrokerWalletNotFoundException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        return cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().getCryptoBrokerWalletAssociatedSettings();
    }

    /**
     * Returns an exchange rate of a given date, for a specific currencyPair
     *
     * @param currencyFrom
     * @param currencyTo
     * @param timestamp
     * @return an exchangeRate object
     */
    @Override
    public ExchangeRate getExchangeRateFromDate(final Currency currencyFrom, final Currency currencyTo, long timestamp, UUID providerId) throws UnsupportedCurrencyPairException, CantGetExchangeRateException, CantGetProviderException {
        CurrencyPair currencyPair = new CurrencyPair() {
            @Override
            public Currency getFrom() {
                return currencyFrom;
            }

            @Override
            public Currency getTo() {
                return currencyTo;
            }
        };
        return currencyExchangeProviderFilterManager.getProviderReference(providerId).getExchangeRateFromDate(currencyPair, timestamp);
    }

    /**
     * Given a start and end timestamp and a currencyPair, returns a list of DAILY ExchangeRates
     *
     * @param currencyFrom
     * @param currencyTo
     * @param startTimestamp
     * @param endTimestamp
     * @param providerId
     * @return a list of exchangeRate objects
     */
    @Override
    public Collection<ExchangeRate> getDailyExchangeRatesForPeriod(final Currency currencyFrom, final Currency currencyTo, long startTimestamp, long endTimestamp, UUID providerId) throws UnsupportedCurrencyPairException, CantGetExchangeRateException, CantGetProviderException {
        CurrencyPair currencyPair = new CurrencyPair() {
            @Override
            public Currency getFrom() {
                return currencyFrom;
            }

            @Override
            public Currency getTo() {
                return currencyTo;
            }
        };
        return currencyExchangeProviderFilterManager.getProviderReference(providerId).getDailyExchangeRatesForPeriod(currencyPair, startTimestamp, endTimestamp);
    }


    /**
     * This method load the list CryptoBrokerStockTransaction
     *
     * @param merchandise
     * @param currencyType
     * @param offset
     * @param timeStamp
     * @param walletPublicKey
     * @return List<CryptoBrokerStockTransaction>
     * @throws CantGetCryptoBrokerStockTransactionException
     */
    @Override
    public List<CryptoBrokerStockTransaction> getStockHistory(FermatEnum merchandise, CurrencyType currencyType, int offset, long timeStamp, String walletPublicKey) throws CantGetCryptoBrokerStockTransactionException {
        //TODO: Implementar en la wallet la mejor forma de hacer esta consulta
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        return null;
    }

    @Override
    public float getAvailableBalance(FermatEnum merchandise, String walletPublicKey) throws CantGetAvailableBalanceCryptoBrokerWalletException, CryptoBrokerWalletNotFoundException, CantGetStockCryptoBrokerWalletException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        return cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getStockBalance().getAvailableBalance(merchandise);
    }

    /**
     * Returns a list of provider references which can obtain the ExchangeRate of the given CurrencyPair
     *
     * @param currencyFrom
     * @param currencyTo
     * @return a Collection of provider reference pairs
     */
    @Override
    public Collection<CurrencyExchangeRateProviderManager> getProviderReferencesFromCurrencyPair(final Currency currencyFrom, final Currency currencyTo) throws CantGetProviderException {
        CurrencyPair currencyPair = new CurrencyPair() {
            @Override
            public Currency getFrom() {
                return currencyFrom;
            }

            @Override
            public Currency getTo() {
                return currencyTo;
            }
        };
        return currencyExchangeProviderFilterManager.getProviderReferencesFromCurrencyPair(currencyPair);
    }


    /**
     * This method save the instance CryptoBrokerWalletProviderSetting
     *
     * @param cryptoBrokerWalletProviderSetting
     * @return
     * @throws CantSaveCryptoBrokerWalletSettingException
     */
    @Override
    public void saveCryptoBrokerWalletProviderSetting(CryptoBrokerWalletProviderSetting cryptoBrokerWalletProviderSetting, String walletPublicKey) throws CantSaveCryptoBrokerWalletSettingException, CryptoBrokerWalletNotFoundException, CantGetCryptoBrokerWalletSettingException {
        //TODO: Quitar este hardcore luego que se implemente la instalacion de la wallet
        walletPublicKey = "walletPublicKeyTest";
        cryptoBrokerWalletManager.loadCryptoBrokerWallet(walletPublicKey).getCryptoWalletSetting().saveCryptoBrokerWalletProviderSetting(cryptoBrokerWalletProviderSetting);
    }

    /**
     * @param bankAccount
     * @throws CantCreateBankAccountSaleException
     */
    @Override
    public void createNewBankAccount(NegotiationBankAccount bankAccount) throws CantCreateBankAccountSaleException {
        customerBrokerSaleNegotiationManager.createNewBankAccount(bankAccount);
    }

    /**
     * @param bankAccount
     * @throws CantDeleteBankAccountSaleException
     */
    @Override
    public void deleteBankAccount(NegotiationBankAccount bankAccount) throws CantDeleteBankAccountSaleException {
        customerBrokerSaleNegotiationManager.deleteBankAccount(bankAccount);
    }

    /**
     * Returns the Balance this BankMoneyWalletBalance belongs to. (Can be available or book)
     *
     * @param walletPublicKey
     * @param accountNumber
     * @return A double, containing the balance.
     */
    @Override
    public double getBalanceBankWallet(String walletPublicKey, String accountNumber) throws CantCalculateBalanceException, CantLoadBankMoneyWalletException {
        return bankMoneyWalletManager.loadBankMoneyWallet(walletPublicKey).getAvailableBalance().getBalance(accountNumber);
    }

    /**
     * Returns the Balance this CashMoneyWalletBalance belongs to. (Can be available or book)
     *
     * @param walletPublicKey
     * @return A BigDecimal, containing the balance.
     */
    @Override
    public BigDecimal getBalanceCashWallet(String walletPublicKey) throws CantGetCashMoneyWalletBalanceException, CantLoadCashMoneyWalletException {
        return cashMoneyWalletManager.loadCashMoneyWallet(walletPublicKey).getAvailableBalance().getBalance();
    }

    /**
     * Returns the Balance this BitcoinWalletBalance belongs to. (Can be available or book)
     *
     * @return A BigDecimal, containing the balance.
     */
    @Override
    public long getBalanceBitcoinWallet(String walletPublicKey) throws com.bitdubai.fermat_ccp_api.layer.basic_wallet.common.exceptions.CantCalculateBalanceException, CantLoadWalletException {
        return bitcoinWalletManager.loadWallet(walletPublicKey).getBalance(BalanceType.AVAILABLE).getBalance();
    }


    private List<ContractBasicInformation> getContractHistoryTestData() {
        if (contractsHistory == null) {
            List<CustomerBrokerNegotiationInformation> openNegotiations = getOpenNegotiationsTestData();

            CryptoBrokerWalletModuleContractBasicInformation contract;
            contractsHistory = new ArrayList<>();

        }

        return contractsHistory;
    }

    private List<CustomerBrokerNegotiationInformation> getOpenNegotiationsTestData() {
        if (openNegotiations != null) {
            return openNegotiations;
        }

        Random random = new Random(321515131);
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();

        float currencyQtyVal = random.nextFloat() * 100;
        float exchangeRateVal = random.nextFloat();
        String customerCurrencyQty = decimalFormat.format(currencyQtyVal);
        String exchangeRate = decimalFormat.format(exchangeRateVal);
        String brokerCurrencyQty = decimalFormat.format(currencyQtyVal * exchangeRateVal);
        long timeInMillisVal = System.currentTimeMillis();
        String timeInMillisStr = String.valueOf(timeInMillisVal);

        openNegotiations = new ArrayList<>();
        CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation item;

        item = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("nelsonalfo", NegotiationStatus.WAITING_FOR_BROKER);
        item.setLastNegotiationUpdateDate(timeInMillisVal);
        item.setExpirationDatetime(timeInMillisVal);
        item.setNote("Le dices al portero que vas a nombre del señor Bastidas");
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY, FiatCurrency.VENEZUELAN_BOLIVAR.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY_QUANTITY, brokerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY, CryptoCurrency.BITCOIN.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY_QUANTITY, customerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.EXCHANGE_RATE, exchangeRate, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PAYMENT_METHOD, CASH_DELIVERY, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PLACE_TO_DELIVER, BROKER_LOCATION_1, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PAYMENT_METHOD, CRYPTO_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CRYPTO_ADDRESS, CUSTOMER_CRYPTO_ADDRESS, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        openNegotiations.add(item);

        currencyQtyVal = random.nextFloat() * 100;
        exchangeRateVal = random.nextFloat();
        customerCurrencyQty = decimalFormat.format(currencyQtyVal);
        exchangeRate = decimalFormat.format(exchangeRateVal);
        brokerCurrencyQty = decimalFormat.format(currencyQtyVal * exchangeRateVal);
        timeInMillisVal = System.currentTimeMillis();
        timeInMillisStr = String.valueOf(timeInMillisVal);

        item = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("jorgeegonzalez", NegotiationStatus.WAITING_FOR_BROKER);
        item.setLastNegotiationUpdateDate(timeInMillisVal);
        item.setExpirationDatetime(timeInMillisVal);
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY, FiatCurrency.VENEZUELAN_BOLIVAR.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY_QUANTITY, brokerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY, FiatCurrency.US_DOLLAR.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY_QUANTITY, customerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.EXCHANGE_RATE, exchangeRate, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PAYMENT_METHOD, CASH_IN_HAND, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PLACE_TO_DELIVER, BROKER_LOCATION_1, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PAYMENT_METHOD, BANK_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_BANK_ACCOUNT, CUSTOMER_BANK_ACCOUNT_1, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        openNegotiations.add(item);

        currencyQtyVal = random.nextFloat() * 100;
        exchangeRateVal = random.nextFloat();
        customerCurrencyQty = decimalFormat.format(currencyQtyVal);
        exchangeRate = decimalFormat.format(exchangeRateVal);
        brokerCurrencyQty = decimalFormat.format(currencyQtyVal * exchangeRateVal);
        timeInMillisVal = System.currentTimeMillis();
        timeInMillisStr = String.valueOf(timeInMillisVal);

        item = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("Matias Furzyfer", NegotiationStatus.WAITING_FOR_BROKER);
        item.setLastNegotiationUpdateDate(timeInMillisVal);
        item.setExpirationDatetime(timeInMillisVal);
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY, FiatCurrency.ARGENTINE_PESO.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY_QUANTITY, brokerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY, FiatCurrency.US_DOLLAR.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY_QUANTITY, customerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.EXCHANGE_RATE, exchangeRate, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PAYMENT_METHOD, BANK_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_BANK_ACCOUNT, BROKER_BANK_ACCOUNT_1, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PAYMENT_METHOD, CASH_DELIVERY, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PLACE_TO_DELIVER, CUSTOMER_LOCATION_2, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        openNegotiations.add(item);


        currencyQtyVal = random.nextFloat() * 100;
        exchangeRateVal = random.nextFloat();
        customerCurrencyQty = decimalFormat.format(currencyQtyVal);
        exchangeRate = decimalFormat.format(exchangeRateVal);
        brokerCurrencyQty = decimalFormat.format(currencyQtyVal * exchangeRateVal);
        timeInMillisVal = System.currentTimeMillis();
        timeInMillisStr = String.valueOf(timeInMillisVal);

        item = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("neoperol", NegotiationStatus.WAITING_FOR_BROKER);
        item.setLastNegotiationUpdateDate(timeInMillisVal);
        item.setExpirationDatetime(timeInMillisVal);
        item.setNote("Nos vemos cerca de la entrada principal. Voy vestido de franela amarílla y pantalón de Jean");
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY, CryptoCurrency.BITCOIN.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY_QUANTITY, brokerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY, FiatCurrency.US_DOLLAR.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY_QUANTITY, customerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.EXCHANGE_RATE, exchangeRate, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PAYMENT_METHOD, CRYPTO_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CRYPTO_ADDRESS, BROKER_CRYPTO_ADDRESS, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PAYMENT_METHOD, BANK_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_BANK_ACCOUNT, CUSTOMER_BANK_ACCOUNT_1, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        openNegotiations.add(item);


        currencyQtyVal = random.nextFloat() * 100;
        exchangeRateVal = random.nextFloat();
        customerCurrencyQty = decimalFormat.format(currencyQtyVal);
        exchangeRate = decimalFormat.format(exchangeRateVal);
        brokerCurrencyQty = decimalFormat.format(currencyQtyVal * exchangeRateVal);
        timeInMillisVal = System.currentTimeMillis();
        timeInMillisStr = String.valueOf(timeInMillisVal);

        item = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("Nelson Orlando", NegotiationStatus.WAITING_FOR_CUSTOMER);
        item.setLastNegotiationUpdateDate(timeInMillisVal);
        item.setExpirationDatetime(timeInMillisVal);
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY, CryptoCurrency.BITCOIN.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY_QUANTITY, brokerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY, FiatCurrency.VENEZUELAN_BOLIVAR.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY_QUANTITY, customerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.EXCHANGE_RATE, exchangeRate, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PAYMENT_METHOD, CRYPTO_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CRYPTO_ADDRESS, BROKER_CRYPTO_ADDRESS, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PAYMENT_METHOD, CASH_DELIVERY, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_BANK_ACCOUNT, CUSTOMER_LOCATION_1, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        openNegotiations.add(item);

        currencyQtyVal = random.nextFloat() * 100;
        exchangeRateVal = random.nextFloat();
        customerCurrencyQty = decimalFormat.format(currencyQtyVal);
        exchangeRate = decimalFormat.format(exchangeRateVal);
        brokerCurrencyQty = decimalFormat.format(currencyQtyVal * exchangeRateVal);
        timeInMillisVal = System.currentTimeMillis();
        timeInMillisStr = String.valueOf(timeInMillisVal);

        item = new CryptoBrokerWalletModuleCustomerBrokerNegotiationInformation("Customer 5", NegotiationStatus.WAITING_FOR_CUSTOMER);
        item.setLastNegotiationUpdateDate(timeInMillisVal);
        item.setExpirationDatetime(timeInMillisVal);
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY, CryptoCurrency.LITECOIN.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CURRENCY_QUANTITY, brokerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY, CryptoCurrency.BITCOIN.getCode(), ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CURRENCY_QUANTITY, customerCurrencyQty, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.EXCHANGE_RATE, exchangeRate, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_PAYMENT_METHOD, CRYPTO_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_CRYPTO_ADDRESS, BROKER_CRYPTO_ADDRESS, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_PAYMENT_METHOD, CRYPTO_TRANSFER, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_CRYPTO_ADDRESS, CUSTOMER_CRYPTO_ADDRESS, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.BROKER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        item.addClause(new CryptoBrokerWalletModuleClauseInformation(ClauseType.CUSTOMER_DATE_TIME_TO_DELIVER, timeInMillisStr, ClauseStatus.DRAFT));
        openNegotiations.add(item);

        return openNegotiations;
    }

    @Override
    public SettingsManager<FermatSettings> getSettingsManager() {
        return null;
    }

    @Override
    public ActiveActorIdentityInformation getSelectedActorIdentity() throws CantGetSelectedActorIdentityException {
        return null;
    }

    @Override
    public void createIdentity(String name, String phrase, byte[] profile_img) throws Exception {

    }

    @Override
    public void setAppPublicKey(String publicKey) {

    }

    @Override
    public int[] getMenuNotifications() {
        return new int[0];
    }
}
