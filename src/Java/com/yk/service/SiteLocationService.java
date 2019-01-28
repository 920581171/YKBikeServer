package com.yk.service;

import com.yk.pojo.SiteLocation;

import java.util.List;

public interface SiteLocationService {
    SiteLocation searchSiteLocationId(String siteId) throws Exception;

    List<SiteLocation> searchAllSiteLocation() throws Exception;

    int addSiteLocation(double latitude, double longitude,int radius) throws Exception;

    int updateSiteLocation(SiteLocation siteLocation) throws Exception;

    int deleteSiteLocation(SiteLocation siteLocation) throws Exception;
}
