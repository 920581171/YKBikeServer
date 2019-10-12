package com.yk.bike.dao.impl;

import com.yk.bike.mapper.SiteLocationMapper;
import com.yk.bike.pojo.SiteLocation;
import com.yk.bike.dao.SiteLocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class SiteLocationDaoImpl implements SiteLocationDao {

    @Autowired
    SiteLocationMapper siteLocationDao;

    @Override
    public SiteLocation searchSiteLocationId(String siteId) throws Exception {
        return siteLocationDao.selectOne(SiteLocationMapper.COLUMN_SITE_ID, siteId);
    }

    @Override
    public List<SiteLocation> searchAllSiteLocation() throws Exception {
        return siteLocationDao.selectTable();
    }

    @Override
    public List<SiteLocation> queryPageSiteLocation(int pageIndex, int pageSize) throws Exception {
        return siteLocationDao.queryPageTable(pageIndex, pageSize);
    }

    @Override
    public int addSiteLocation(double latitude, double longitude, int radius) throws Exception {

        String siteId = randomId("SITE");

        while (siteLocationDao.selectOne(SiteLocationMapper.COLUMN_SITE_ID, siteId) != null) {
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
