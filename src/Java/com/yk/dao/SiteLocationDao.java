package com.yk.dao;

import com.yk.pojo.SiteLocation;

public interface SiteLocationDao extends BaseDao<SiteLocation> {
    String COLUMN_SITE_ID = "site_id";
    String COLUMN_LATITUDE = "latitude";
    String COLUMN_LONGITUDE = "longitude";
    String COLUMN_RADIUS = "radius";
}
