package com.yk.bike.dao;

import com.yk.bike.pojo.SiteLocation;

import java.util.List;

public interface SiteLocationDao {
    SiteLocation searchSiteLocationId(String siteId) throws Exception;

    List<SiteLocation> searchAllSiteLocation() throws Exception;

    List<SiteLocation> queryPageSiteLocation(int pageIndex, int pageSize) throws Exception;

    int addSiteLocation(double latitude, double longitude, int radius) throws Exception;

    int updateSiteLocation(SiteLocation siteLocation) throws Exception;

    int deleteSiteLocation(SiteLocation siteLocation) throws Exception;
}
