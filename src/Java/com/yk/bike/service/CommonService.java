package com.yk.bike.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    String getServiceTime();

    String uploadAvatar(MultipartFile original, String id) throws Exception;

    ResponseEntity<byte[]> downloadAvatar(String id, String level) throws Exception;

    ResponseEntity<byte[]> exportBikeRecord() throws Exception;

    ResponseEntity<byte[]> exportDepositRecord() throws Exception;

    ResponseEntity<byte[]> exportBalanceRecord() throws Exception;

    ResponseEntity<byte[]> createQRCode(int startNum, int endNum, String bikeType) throws Exception;
}
