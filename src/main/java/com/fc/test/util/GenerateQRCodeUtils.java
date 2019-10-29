package com.fc.test.util;

import com.fc.test.model.request.GenerateQRCodeReq;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * java生成二维码图片
 * @author dgl
 */
@Slf4j
public class GenerateQRCodeUtils {

    public static void GenerateQRCode(GenerateQRCodeReq req) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(req.getText(), BarcodeFormat.QR_CODE, req.getWidth(), req.getHeight());

        Path path = FileSystems.getDefault().getPath(req.getFilePath());

        MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);

    }

    public static void main(String[] args) {
        try {
            GenerateQRCode(new GenerateQRCodeReq("媳妇,下班了吗?\n", 350, 350));
        } catch (WriterException e) {
            log.info("二维码生成失败:" + e.getMessage());
        } catch (IOException e) {
            log.info("二维码生成失败:" + e.getMessage());
        }
    }


}
