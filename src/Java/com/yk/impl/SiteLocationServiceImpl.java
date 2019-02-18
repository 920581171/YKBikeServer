package com.yk.impl;

import com.yk.dao.SiteLocationDao;
import com.yk.pojo.SiteLocation;
import com.yk.service.SiteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class SiteLocationServiceImpl implements SiteLocationService {

    @Autowired
    SiteLocationDao siteLocationDao;

    @Override
    public SiteLocation searchSiteLocationId(String siteId) throws Exception {
        return siteLocationDao.selectOne(SiteLocationDao.COLUMN_SITE_ID, siteId);
    }

    @Override
    public List<SiteLocation> searchAllSiteLocation() throws Exception {
        return siteLocationDao.selectTable();
    }

    @Override
    public int addSiteLocation(double latitude, double longitude, int radius) throws Exception {

        String siteId = randomId("SITE");

        while (siteLocationDao.selectOne(SiteLocationDao.COLUMN_SITE_ID, siteId) != null) {
            siteId = randomId("SITE");
        }

        SiteLocation siteLocation = new SiteLocation()
                .setSiteId(siteId)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setRadius(radius);

        siteLocationDao.insert(siteLocation);

        return siteLocation.getId();
    }

    @Override
    public int updateSiteLocation(SiteLocation siteLocation) throws Exception {
        return siteLocationDao.update(siteLocation);
    }

    @Override
    public int deleteSiteLocation(SiteLocation siteLocation) throws Exception {
        return siteLocationDao.delete(siteLocation);
    }
}
