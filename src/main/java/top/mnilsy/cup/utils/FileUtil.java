package top.mnilsy.cup.utils;

import net.coobird.thumbnailator.Thumbnails;
import top.mnilsy.cup.enums.UrlEnum;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by mnilsy on 19-5-7 下午5:22.
 */
public class FileUtil {

    public static boolean base64ToFile(String base64Str, String url) {
        if (base64Str == null) return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] data = decoder.decode(base64Str);
            for (int i = 0; i < data.length; ++i) {
                if (data[i] < 0) {//调整异常数据
                    data[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(url);
            out.write(data);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String fileToBase64(String url) {
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(url);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    public static String fileToBase64(File file) {
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }


    public static String thumbnail(String url) {
        String tempName = UUID.randomUUID().toString();
        File temp = new File(UrlEnum.TEMP.vlue + tempName);
        try {
            Thumbnails.of(url).size(300, 300).toFile(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String base64 = fileToBase64(temp);
        temp.delete();
        return base64;
    }
}
