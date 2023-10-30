package shop.mtcoding.springblogriver._core.util;

import shop.mtcoding.springblogriver._core.error.exception.Exception500;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class MyFileUtil {

    public static String write(String imgBase64) {
        // 1. file folder path
        String folder = "/images/";

        // 1. 파일명 생성
        UUID uuid = UUID.randomUUID();
        String mimeType = Base64Util.getMimeType(imgBase64);
        String imgFilename = folder+uuid+"."+mimeType;

        // 2. base64 -> byte[]
        byte[] imgBytes = Base64Util.decodeAsBytes(imgBase64);
        try {
            // 상대경로
            Path imgFilePath = Paths.get("."+imgFilename);
            Files.write(imgFilePath, imgBytes);
        } catch (Exception e) {
            throw new Exception500("파일 업로드 실패 : " + e.getMessage());
        }
        return imgFilename;
    }


}
