package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.SiteLocationServiceImpl;
import com.yk.pojo.SiteLocation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "站点相关")
@Controller
@RequestMapping("siteLocation")
public class SiteLocationController {

    @Autowired
    SiteLocationServiceImpl siteLocationService;

    @ResponseBody
    @ApiOperation(value = "查找所有站点", httpMethod = "POST")
    @RequestMapping(value = "/findAllSiteLocation", method = RequestMethod.POST)
    public String findAllSiteLocation() {
        try {
            List<SiteLocation> siteLocations = siteLocationService.searchAllSiteLocation();
            return GsonUtils.responseObjectJson(siteLocations != null, siteLocations);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据Id查询站点", httpMethod = "POST")
    @RequestMapping(value = "/findSiteLocationById", method = RequestMethod.POST)
    public String findSiteLocationById(@RequestParam("siteId") String siteId) {
        try {
            SiteLocation siteLocation = siteLocationService.searchSiteLocationId(siteId);
            return GsonUtils.responseObjectJson(siteLocation != null, siteLocation);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加站点", httpMethod = "POST")
    @RequestMapping(value = "/addSiteLocation", method = RequestMethod.POST)
    public String addSiteLocation(@RequestParam("latitude") double latitude,
                                  @RequestParam("longitude") double longitude,
                                  @RequestParam("radius") int radius) {
        try {
            return GsonUtils.responseSimpleJson(siteLocationService.addSiteLocation(latitude, longitude, radius) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新站点", httpMethod = "POST")
    @RequestMapping(value = "/updateSiteLocation", method = RequestMethod.POST)
    public String updateSiteLocation(@RequestParam("siteId") String siteId,
                                     @RequestParam("latitude") double latitude,
                                     @RequestParam("longitude") double longitude,
                                     @RequestParam("radius") int radius) {

        try {
            SiteLocation siteLocation = siteLocationService.searchSiteLocationId(siteId);
            siteLocation.setLatitude(latitude)
                    .setLongitude(longitude)
                    .setRadius(radius);

            return GsonUtils.responseSimpleJson(siteLocationService.updateSiteLocation(siteLocation) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除站点", httpMethod = "POST")
    @RequestMapping(value = "/deleteSiteLocation", method = RequestMethod.POST)
    public String deleteSiteLocation(@RequestParam("siteId") String siteId) {
        try {
            SiteLocation siteLocation = siteLocationService.searchSiteLocationId(siteId);
            return GsonUtils.responseSimpleJson(siteLocationService.deleteSiteLocation(siteLocation) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
