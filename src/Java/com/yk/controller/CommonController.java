package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.Utils.ImageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Api(value = "common")
@Controller
@RequestMapping(value = "common")
public class CommonController {
    @ApiOperation(value = "获得服务器时间", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/getServiceTime", method = RequestMethod.POST)
    public String getServiceTime() {
        return GsonUtils.responseSuccessObjJson(String.valueOf(System.currentTimeMillis()));
    }

    @ResponseBody
    @ApiOperation(value = "上传头像", httpMethod = "POST")
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("file") MultipartFile original, @RequestParam("id") String id) {
        String[] paths = {"D:\\Avatar\\Source\\" + id + ".jpg",
                "D:\\Avatar\\High\\" + id + ".jpg",
                "D:\\Avatar\\Middle\\" + id + ".jpg",
                "D:\\Avatar\\Low\\" + id + ".jpg"};
        int[] size = {0, 512, 256, 128};

        try {
            for (int i = 0; i < paths.length; i++) {
                File file = new File(paths[i]);
                if (!file.getParentFile().exists())
                    if (!file.getParentFile().mkdirs())
                        return GsonUtils.responseErrorJson();

                if (!file.exists()) {
                    if (!file.createNewFile())
                        return GsonUtils.responseErrorJson();
                }

                if (i == 0) {
                    original.transferTo(file);
                } else {
                    ImageUtils.scale2(paths[0], paths[i], size[i], size[i], true);
                }
            }
            return GsonUtils.responseSuccessJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "下载头像", httpMethod = "GET")
    @RequestMapping(value = "/downloadAvatar", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadAvatar(@RequestParam("id") String id, @RequestParam(value = "level", required = false) String level) {
        try {
            File file;
            if (level == null || "".equals(level))
                file = new File("D:\\Avatar\\Source\\" + id + ".jpg");
            else
                file = new File("D:\\Avatar\\"+level+"\\" + id + ".jpg");
            InputStream in = new FileInputStream(file);
            byte[] b = new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            String filename = new String(file.getName().getBytes("gbk"), "iso8859-1");
            headers.add("Content-Disposition", "attachment;filename=" + filename);
            HttpStatus statusCode = HttpStatus.OK;
            in.close();
            return new ResponseEntity<byte[]>(b, headers, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
