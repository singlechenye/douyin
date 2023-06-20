package com.cy.douyin.util;
import java.awt.image.BufferedImage;
import java.io.*;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;

public class VideoUtil {
    public static byte[] extractCoverImage(byte[] bytes)  {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        // 从 ByteBuffer 中读取视频帧
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream);

        // 将 BufferedImage 转换成字节数组并返回
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            grabber.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage image = converter.getBufferedImage(grabber.grabImage());
            ImageIO.write(image, "jpeg", baos);
            return baos.toByteArray();
        }catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            try {
                grabber.stop();
                grabber.release();
            }catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
//    public static void main(String[] args) throws IOException {
//        InputStream inputStream = VideoUtil.class.getClassLoader().getResourceAsStream("video/test.MP4");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        int len;
//        byte[] buffer = new byte[4096];
//        while ((len = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
//        byte[] bytes = outputStream.toByteArray();
//        byte[] bytes1 = extractCoverImage(bytes);
//        String fileName = "myimage.jpg";
//        Path filePath = Paths.get("src", "main", "resources", "jpg", fileName);
//        try (OutputStream os = Files.newOutputStream(filePath)) {
//            os.write(bytes1);
//        }
//
//
//    }


}
