package com.yk.bike.controller;

import com.yk.bike.service.SiteLocationService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "站点相关")
@Controller
@RequestMapping("siteLocation")
public class SiteLocationController {

    @Autowired
    private SiteLocationService siteLocationService;

    @ResponseBody
    @ApiOperation(value = "查找所有站点", httpMethod = "POST")
    @RequestMapping(value = "/findAllSiteLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllSiteLocation() {
        try {
            return siteLocationService.findAllSiteLocation();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找所有自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/queryPageSiteLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryPageSiteLocation(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        try {
            return siteLocationService.queryPageSiteLocation(pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据Id查询站点", httpMethod = "POST")
    @RequestMapping(value = "/findSiteLocationById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findSiteLocationById(@RequestParam("siteId") String siteId) {
        try {
            return siteLocationService.findSiteLocationById(siteId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加站点", httpMethod = "POST")
    @RequestMapping(value = "/addSiteLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addSiteLocation(@RequestParam("latitude") double latitude,
                                  @RequestParam("longitude") double longitude,
                                  @RequestParam("radius") int radius) {
        try {
            return siteLocationService.addSiteLocation(latitude, longitude, radius);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新站点", httpMethod = "POST")
    @RequestMapping(value = "/updateSiteLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateSiteLocation(@RequestParam("siteId") String siteId,
                                     @RequestParam("latitude") double latitude,
                                     @RequestParam("longitude") double longitude,
                                     @RequestParam("radius") int radius) {

        try {
            return siteLocationService.updateSiteLocation(siteId, latitude, longitude, radius);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除站点", httpMethod = "POST")
    @RequestMapping(value = "/deleteSiteLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteSiteLocation(@RequestParam("siteId") String siteId) {
        try {
            return siteLocationService.deleteSiteLocation(siteId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "批量删除站点", httpMethod = "POST")
    @RequestMapping(value = "/deleteMoreSiteLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteMoreSiteLocation(@RequestParam(value = "sites") String[] sites) {
        try {
            return siteLocationService.deleteMoreSiteLocation(sites);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
