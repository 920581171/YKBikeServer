package com.yk.bike.service;

public interface SiteLocationService {
    String findAllSiteLocation() throws Exception;

    String queryPageSiteLocation(int pageIndex, int pageSize) throws Exception;

    String findSiteLocationById(String siteId) throws Exception;

    String addSiteLocation(double latitude, double longitude, int radius) throws Exception;

    String updateSiteLocation(String siteId, double latitude, double longitude, int radius) throws Exception;

    String deleteSiteLocation(String siteId) throws Exception;

    String deleteMoreSiteLocation(String[] sites) throws Exception;
}
