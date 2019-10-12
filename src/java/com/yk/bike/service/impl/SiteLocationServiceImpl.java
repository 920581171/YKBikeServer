package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.SiteLocationDaoImpl;
import com.yk.bike.pojo.SiteLocation;
import com.yk.bike.service.SiteLocationService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteLocationServiceImpl implements SiteLocationService {

    @Autowired
    private SiteLocationDaoImpl siteLocationDao;

    @Override
    public String findAllSiteLocation() throws Exception {
        List<SiteLocation> siteLocations = siteLocationDao.searchAllSiteLocation();
        return GsonUtils.responseObjectJson(siteLocations != null, siteLocations);
    }

    @Override
    public String queryPageSiteLocation(int pageIndex, int pageSize) throws Exception {
        List<SiteLocation> siteLocations = siteLocationDao.queryPageSiteLocation(pageIndex, pageSize);
        return GsonUtils.responseObjectJson(siteLocations != null, siteLocations);
    }

    @Override
    public String findSiteLocationById(String siteId) throws Exception {
        SiteLocation siteLocation = siteLocationDao.searchSiteLocationId(siteId);
        return GsonUtils.responseObjectJson(siteLocation != null, siteLocation);
    }

    @Override
    public String addSiteLocation(double latitude, double longitude, int radius) throws Exception {
        return GsonUtils.responseSimpleJson(siteLocationDao.addSiteLocation(latitude, longitude, radius) > 0);
    }

    @Override
    public String updateSiteLocation(String siteId, double latitude, double longitude, int radius) throws Exception {
        SiteLocation siteLocation = siteLocationDao.searchSiteLocationId(siteId);
        siteLocation.setLatitude(latitude)
                .setLongitude(longitude)
                .setRadius(radius);

        return GsonUtils.responseSimpleJson(siteLocationDao.updateSiteLocation(siteLocation) > 0);
    }

    @Override
    public String deleteSiteLocation(String siteId) throws Exception {
        SiteLocation siteLocation = siteLocationDao.searchSiteLocationId(siteId);
        return GsonUtils.responseSimpleJson(siteLocationDao.deleteSiteLocation(siteLocation) > 0);
    }

    @Override
    public String deleteMoreSiteLocation(String[] sites) throws Exception {
        boolean b = true;
        for (String id : sites) {
            SiteLocation siteLocation = siteLocationDao.searchSiteLocationId(id);
            boolean b1 = siteLocationDao.deleteSiteLocation(siteLocation) > 0;
            if (!b1) {
                b = false;
            }
        }
        return GsonUtils.responseSimpleJson(b);
    }
}
