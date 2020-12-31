package com.interblocks.iwallet.smb.services.cache;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;
import com.interblocks.iwallet.isodetails.model.ISOCountryDetails;
import com.interblocks.iwallet.isodetails.service.ISODetailsService;
import com.interblocks.iwallet.subcomponents.cache.CacheService;
import com.interblocks.iwallet.subcomponents.cache.domain.UserTrxData;
import com.interblocks.iwallet.subcomponents.organization.db.model.BnkVwCurrRate;
import com.interblocks.iwallet.subcomponents.organization.db.repository.CurrencyRepository;
import com.interblocks.iwallet.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Primary
@Component
public class HazelcastCacheService implements CacheService {

    @Autowired
    HazelcastInstance hazelcastInstance;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ISODetailsService isoDetailsService;

    @Override
    public void updateLastActiveTime(String hash) {
        IMap<String, Date> map = hazelcastInstance.getMap(Const.TOKEN_ACCESS_TIMES);
        map.put(hash, new Date());
    }

    @Override
    public void removeToken(String hash) {
        IMap<String, Date> map = hazelcastInstance.getMap(Const.TOKEN_ACCESS_TIMES);
        map.remove(hash, new Date());
    }

    @Override
    public Date getLastActiveTime(String hash) {
        IMap<String, Date> map = hazelcastInstance.getMap(Const.TOKEN_ACCESS_TIMES);
        return map.get(hash);
    }

    @Override
    public void updateNewActiveLogin(String userId, String token) {
        MultiMap<String, String> multiMap = hazelcastInstance.getMultiMap(Const.ACTIVE_LOGIN_IDS);
        Collection<String> tokenList = multiMap.get(userId.toUpperCase());
        for (String mapToken : tokenList) {
            if (mapToken.equalsIgnoreCase(token)) {
                return;
            }
        }
        multiMap.put(userId.toUpperCase(), token);
    }

    @Override
    public void removeActiveLogin(String userId, String token) {
        MultiMap<String, String> multiMap = hazelcastInstance.getMultiMap(Const.ACTIVE_LOGIN_IDS);
        multiMap.remove(userId, token);
    }

    @Override
    public boolean getIsActiveLogin(String userId) {
        MultiMap<String, String> multiMap = hazelcastInstance.getMultiMap(Const.ACTIVE_LOGIN_IDS);
        Collection<String> tokenList = multiMap.get(userId.toUpperCase());
        return (!tokenList.isEmpty());
    }

    @Override
    public String getCurrencyCode(String currDesc) {
        IMap<String, String> currencyDescMap = hazelcastInstance.getMap(Const.CURRENCY_DESCRIPTIONS);
        String currCode = currencyDescMap.getOrDefault(currDesc, null);
        if (null != currCode) {
            return currCode;
        } else {
            List<BnkVwCurrRate> bnkVwCurrRates = currencyRepository.getCurrCodeByDesc(currDesc);
            if (null != bnkVwCurrRates && bnkVwCurrRates.size() > 0) {
                currCode = bnkVwCurrRates.get(0).getCurrencyCode();
                currencyDescMap.put(currDesc, currCode);
                return currCode;
            } else {
                return "";
            }
        }
    }

    @Override
    public String getCurrencyDesc(String currCode) {
        IMap<String, String> currencyCodeMap = hazelcastInstance.getMap(Const.CURRENCY_CODES);
        String currDesc = currencyCodeMap.getOrDefault(currCode, null);
        if (null != currDesc) {
            return currDesc;
        } else {
            List<BnkVwCurrRate> bnkVwCurrRates = currencyRepository.getCurrDescByCode(currCode);
            if (null != bnkVwCurrRates && bnkVwCurrRates.size() > 0) {
                currDesc = bnkVwCurrRates.get(0).getCurrencyDesc();
                currencyCodeMap.put(currCode, currDesc);
                return currDesc;
            } else {
                return "";
            }
        }
    }

    @Override
    public String getCountryIcon(String currCode) {
        String country = null;
        IMap<String, String> countryIcons = hazelcastInstance.getMap(Const.COUNTRY_ICONS);
        String countryIcon = countryIcons.getOrDefault(currCode, null);
        if (null != countryIcon) {
            return countryIcon;
        } else {

            for (ISOCountryDetails isoCountryDetails : isoDetailsService.getISODetailsAllCountries()) {
                if (isoCountryDetails.getISO3166_1_numeric() != null && isoCountryDetails.getISO3166_1_numeric().equals(currCode)) {
                    countryIcons.put(currCode, isoCountryDetails.getISO3166_1_Alpha_2());
                    country = isoCountryDetails.getISO3166_1_Alpha_2();
                }
                if (countryIcons.getOrDefault(isoCountryDetails.getISO3166_1_numeric(), null) == null) {
                    countryIcons.put(isoCountryDetails.getISO3166_1_numeric(), isoCountryDetails.getISO3166_1_Alpha_2());
                }
            }
            return country;
        }
    }

    @Override
    public UserTrxData getTrxDataForUser(String userId) {
        IMap<String, UserTrxData> userTrxDataIMap = hazelcastInstance.getMap(Const.USR_TRX_DATA);
        UserTrxData data = userTrxDataIMap.get(userId);
        if (data == null) {
            UserTrxData newData = new UserTrxData();
            newData.setViolationCount(0);
            newData.setLastTrxTime(null);
            newData.setLocked(false);
            newData.setTransferHashCodes(new HashMap<>());
            userTrxDataIMap.put(userId, newData);
            return newData;
        }
        return data;
    }

    @Override
    public void setTrxDataForUser(String userId, UserTrxData trxData) {
        IMap<String, UserTrxData> userTrxDataIMap = hazelcastInstance.getMap(Const.USR_TRX_DATA);
        userTrxDataIMap.put(userId, trxData);
    }
}
