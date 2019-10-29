package com.fc.test.model.request;

import com.fc.test.util.TZDateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * java生成二维码所需要的参数
 */
@Data
@Service
public class GenerateQRCodeReq {

    @ApiModelProperty(value = "二维码图片包含的文本内容")
    private String text;
    @ApiModelProperty(value = "二维码图片的宽")
    private int width;
    @ApiModelProperty(value = "二维码图片的高")
    private int height;
    @ApiModelProperty(value = "二维码图片的输出路径")
    private String filePath = "C:\\Users\\李亚婷\\Desktop\\二维码"+ TZDateUtils.formatTimeToNumber(new Date())+".jpg";

    public GenerateQRCodeReq(String text, int width, int height) {
        try {
            //解决中文乱码问题
            this.text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.width = width;
        this.height = height;
    }

    public GenerateQRCodeReq() {

    }
}
