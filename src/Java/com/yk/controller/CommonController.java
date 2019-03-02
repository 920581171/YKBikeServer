package com.yk.controller;

import com.yk.Utils.ExcelUtil;
import com.yk.Utils.GsonUtils;
import com.yk.Utils.ImageUtils;
import com.yk.impl.BikeRecordServiceImpl;
import com.yk.pojo.BikeRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Api(description = "其他")
@Controller
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    BikeRecordServiceImpl bikeRecordService;

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
        String[] paths = {"C:\\Avatar\\Source\\" + id + ".jpg",
                "C:\\Avatar\\High\\" + id + ".jpg",
                "C:\\Avatar\\Middle\\" + id + ".jpg",
                "C:\\Avatar\\Low\\" + id + ".jpg"};
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
                file = new File("C:\\Avatar\\Source\\" + id + ".jpg");
            else
                file = new File("C:\\Avatar\\" + level + "\\" + id + ".jpg");
            InputStream in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            return responseStream(bytes,file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出报表
     *
     * @return
     */
    @ApiOperation(value = "导出骑行记录EXCEL表", httpMethod = "GET")
    @RequestMapping(value = "/exportBikeRecord")
    @ResponseBody
    public ResponseEntity<byte[]> exportBikeRecord() throws Exception {
        //获取数据
        List<BikeRecord> list = bikeRecordService.searchAllBikeRecord();

        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
        //excel文件名
        String fileName = "骑行记录" + dateFormat.format(System.currentTimeMillis()) + ".xlsx";
        //excel标题
        String[] title = {"序号", "订单编号", "用户ID", "车辆ID", "计费（元）", "里程（米）", "时长", "创建时间", "结束时间", "状态"};
        //sheet名
        String sheetName = "骑行记录表";

        String[][] content = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            long duration = list.get(i).getEndTime().getTime() - list.get(i).getCreateTime().getTime();
            String durationText = duration / 1000 / 60 + "分钟";

            String createTime = dateFormat.format(list.get(i).getCreateTime().getTime());
            String endTime = list.get(i).getCreateTime().getTime() == list.get(i).getEndTime().getTime() ?
                    "NULL" :
                    dateFormat.format(list.get(i).getEndTime().getTime());

            String status = list.get(i).getOrderStatus().equals("1") ?
                    "已结算" :
                    "未结算";

            content[i] = new String[title.length];
            BikeRecord bikeRecord = list.get(i);
            content[i][0] = String.valueOf(i);
            content[i][1] = bikeRecord.getOrderId();
            content[i][2] = bikeRecord.getUserId();
            content[i][3] = bikeRecord.getBikeId();
            content[i][4] = String.valueOf(bikeRecord.getCharge());
            content[i][5] = String.valueOf(bikeRecord.getMileage());
            content[i][6] = durationText;
            content[i][7] = createTime;
            content[i][8] = endTime;
            content[i][9] = status;
        }

        //创建XSSFWorkbook
        XSSFWorkbook wb = ExcelUtil.getXSSFWorkbook(sheetName, title, content);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
        wb.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return responseStream(bytes,fileName);
    }

    private ResponseEntity<byte[]> responseStream(byte[] bytes, String fileName) {
        try {
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpStatus statusCode = HttpStatus.OK;
            return new ResponseEntity<byte[]>(bytes, headers, statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
