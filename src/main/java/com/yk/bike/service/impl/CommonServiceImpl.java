package com.yk.bike.service.impl;

import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.yk.bike.constant.Consts;
import com.yk.bike.dao.impl.BalanceRecordDaoImpl;
import com.yk.bike.dao.impl.BikeRecordDaoImpl;
import com.yk.bike.dao.impl.DepositRecordDaoImpl;
import com.yk.bike.pojo.BalanceRecord;
import com.yk.bike.pojo.BikeRecord;
import com.yk.bike.pojo.DepositRecord;
import com.yk.bike.service.CommonService;
import com.yk.bike.utils.ExcelUtil;
import com.yk.bike.utils.GsonUtils;
import com.yk.bike.utils.ImageUtils;
import com.yk.bike.utils.MatrixToImageWriter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private BikeRecordDaoImpl bikeRecordService;
    @Autowired
    private DepositRecordDaoImpl depositRecordService;
    @Autowired
    private BalanceRecordDaoImpl balanceRecordService;

    @Override
    public String getServiceTime() {
        return GsonUtils.responseSuccessObjJson(String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public String uploadAvatar(MultipartFile original, String id) throws Exception {
        String[] paths = {Consts.AVATAR_PATH + File.separator + "Source" + File.separator + id + ".jpg",
                Consts.AVATAR_PATH + File.separator + "High" + File.separator + id + ".jpg",
                Consts.AVATAR_PATH + File.separator + "Middle" + File.separator + id + ".jpg",
                Consts.AVATAR_PATH + File.separator + "Low" + File.separator + id + ".jpg"};
        int[] size = {0, 512, 256, 128};

        for (int i = 0; i < paths.length; i++) {
            File file = new File(paths[i]);
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    return GsonUtils.responseErrorJson();
                }
            }

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return GsonUtils.responseErrorJson();
                }
            }

            if (i == 0) {
                original.transferTo(file);
            } else {
                ImageUtils.scale2(paths[0], paths[i], size[i], size[i], true);
            }
        }
        return GsonUtils.responseSuccessJson();
    }

    @Override
    public ResponseEntity<byte[]> downloadAvatar(String id, String level) throws Exception {
        File file;
        if (level == null || "".equals(level)) {
            file = new File(Consts.AVATAR_PATH + File.separator + "Source" + File.separator + id + ".jpg");
        } else {
            file = new File(Consts.AVATAR_PATH + File.separator + level + File.separator + id + ".jpg");
        }
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return responseStream(bytes, file.getName());
    }

    @Override
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
        return responseStream(bytes, fileName);
    }

    @Override
    public ResponseEntity<byte[]> exportDepositRecord() throws Exception {
        //获取数据
        List<DepositRecord> list = depositRecordService.searchAllDepositRecord();

        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
        //excel文件名
        String fileName = "押金出入" + dateFormat.format(System.currentTimeMillis()) + ".xlsx";
        //excel标题
        String[] title = {"序号", "记录ID", "用户ID", "押金出入", "时间"};
        //sheet名
        String sheetName = "押金出入记录表";

        String[][] content = new String[list.size() + 1][];

        float total = 0;

        for (int i = 0; i < list.size(); i++) {
            String deposit = (list.get(i).getDeposit() > 0 ? "充值" : "退还") + Math.abs(list.get(i).getDeposit()) + "元";
            String createTime = dateFormat.format(list.get(i).getCreateTime().getTime());

            content[i] = new String[title.length];
            content[i][0] = String.valueOf(i);
            content[i][1] = list.get(i).getRecordId();
            content[i][2] = list.get(i).getUserId();
            content[i][3] = deposit;
            content[i][4] = createTime;

            total += list.get(i).getDeposit();
        }

        content[list.size()] = new String[title.length];
        content[list.size()][0] = "总计";
        content[list.size()][1] = "";
        content[list.size()][2] = "";
        content[list.size()][3] = total + "元";
        content[list.size()][4] = list.size() + "条记录";

        //创建XSSFWorkbook
        XSSFWorkbook wb = ExcelUtil.getXSSFWorkbook(sheetName, title, content);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
        wb.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return responseStream(bytes, fileName);
    }

    @Override
    public ResponseEntity<byte[]> exportBalanceRecord() throws Exception {
        //获取数据
        List<BalanceRecord> list = balanceRecordService.searchAllBalanceRecord();

        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
        //excel文件名
        String fileName = "余额统计" + dateFormat.format(System.currentTimeMillis()) + ".xlsx";
        //excel标题
        String[] title = {"序号", "记录ID", "用户ID", "充值/消费", "时间"};
        //sheet名
        String sheetName = "余额统计表";

        String[][] content = new String[list.size() + 1][];

        float totalRecharge = 0;
        float totalCharge = 0;

        for (int i = 0; i < list.size(); i++) {
            String balance = (list.get(i).getBalance() > 0 ? "充值" : "退还") + Math.abs(list.get(i).getBalance()) + "元";
            String createTime = dateFormat.format(list.get(i).getCreateTime().getTime());

            content[i] = new String[title.length];
            content[i][0] = String.valueOf(i);
            content[i][1] = list.get(i).getRecordId();
            content[i][2] = list.get(i).getUserId();
            content[i][3] = balance;
            content[i][4] = createTime;

            if (list.get(i).getBalance() > 0) {
                totalRecharge += list.get(i).getBalance();
            } else {
                totalCharge += list.get(i).getBalance();
            }
        }

        totalCharge = Math.round(Math.abs(totalCharge) * 100f) / 100f;

        content[list.size()] = new String[title.length];
        content[list.size()][0] = "总计";
        content[list.size()][1] = "";
        content[list.size()][2] = "用户充值：" + totalRecharge + "元";
        content[list.size()][3] = "用户消费：" + totalCharge + "元";
        content[list.size()][4] = list.size() + "条记录";

        //创建XSSFWorkbook
        XSSFWorkbook wb = ExcelUtil.getXSSFWorkbook(sheetName, title, content);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
        wb.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return responseStream(bytes, fileName);
    }

    @Override
    public ResponseEntity<byte[]> createQRCode(int startNum, int endNum, String bikeType) throws Exception {

        File[] files = new File[endNum - startNum];

        for (int i = 0; i < endNum - startNum; i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bikeId", "BIKE" + (startNum + i));
            jsonObject.addProperty("bikeType", bikeType);
            String content = Base64Utils.encodeToString(jsonObject.toString().getBytes());
            String path = Consts.AVATAR_PATH + File.separator + (startNum + i) + ".jpg";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
            files[i] = new File(path);
            if (!files[i].getParentFile().exists()) {
                if (!files[i].getParentFile().mkdirs()) {
                    return null;
                }
            }

            if (!files[i].exists()) {
                if (!files[i].createNewFile()) {
                    return null;
                }
            }
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", files[i]);
        }

        String fileName = "二维码-" + startNum + "-" + endNum + ".zip";

        byte[] buffer = new byte[1024];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ZipOutputStream out = new ZipOutputStream(byteArrayOutputStream);
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len;
            //读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
            file.delete();
        }
        out.close();

        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return responseStream(bytes, fileName);
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
