package org.small;

import it.sauronsoftware.jave.Encoder;
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.gimpy.BlockGimpyRenderer;
import nl.captcha.gimpy.FishEyeGimpyRenderer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.ColoredEdgesWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.small.model.CaptchaConstants;
import org.small.model.CaptchaData;
import org.small.model.Result;
import org.small.utils.EasyCharTextProducer;
import org.small.utils.FixedWordRenderer;
import org.small.utils.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * CaptchaService 生成验证码操作
 *
 * @author jiang haiguo
 * @date 2016/8/8
 */
public class CaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaService.class);

    /**
     * @param width
     * @param height
     * @param captchaLength
     * @param catchaStyle
     * @param outputType
     * @return
     */
    public Result createSimpleCode(int width, int height, int captchaLength, String catchaStyle, String outputType) {
        LOGGER.debug("开始创建验证码图片,宽度为：" + width + ",高度：" + height + ",验证码长度" + captchaLength + ",验证码格式类型" + catchaStyle + ",验证码输出类型" + outputType);
        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            // randomCode记录随机产生的验证码
            // 默认图片的长度为120*40
            if (width == 0 || height == 0) {
                width = 120;
                height = 40;
            }

            // 默认随机英文加字幕
            if (StringUtils.isEmpty(catchaStyle)) {
                catchaStyle = CaptchaConstants.ENG;
            }

            // 不符合条件的条件进行过滤，默认为4
            if (captchaLength == 0 || captchaLength > 7 || captchaLength < 0) {
                captchaLength = 4;
            }

            // 默认为图形为 jpg格式
            if (StringUtils.isEmpty(outputType)) {
                outputType = CaptchaConstants.OUTPUT_TYPE;
            }
            Captcha captcha = null;
            AudioCaptcha audioCaptcha = null;
            if (CaptchaConstants.CHS.equals(catchaStyle)) {
                captcha = this.getChsCaptcha(width, height, captchaLength);
            } else {
                if (CaptchaConstants.MP3.equals(catchaStyle) || CaptchaConstants.WAV.equals(catchaStyle)) {
                    audioCaptcha = this.getAudioCaptcha(outputType);
                } else {
                    captcha = this.getDefaultCaptcha(width, height, captchaLength, catchaStyle);
                }
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bts = null;
            String strRand = "";
            if (!CaptchaConstants.MP3.equals(catchaStyle) && !CaptchaConstants.WAV.equals(catchaStyle)) {
                strRand = audioCaptcha.getAnswer();
                AudioSystem.write(audioCaptcha.getChallenge().getAudioInputStream(),
                        AudioFileFormat.Type.WAVE, bos);

            } else {
                //图片处理
                ImageIO.write(captcha.getImage(), outputType, bos);
                strRand = captcha.getAnswer();
                bts = bos.toByteArray();
            }

            BASE64Encoder encoder = new BASE64Encoder();
            LOGGER.debug("创建验证码图片中,计算后的验证码Base64码为：" + encoder.encode(bts));
            returnMap.put("captcha_base64", encoder.encode(bts));
            returnMap.put("answer", strRand);
            returnMap.put("media_type", outputType);
            LOGGER.debug("创建验证码图片中,计算后的随机验证码为：" + strRand);
        } catch (IOException e) {
            returnMap.put("code", "400");
            returnMap.put("name", "生成图片验证码出错");
            LOGGER.error("创建验证码图片出错：" + e.getMessage());
            return Result.error("获取验证码失败", returnMap);
        }
        LOGGER.debug("成功创建验证码图片结束");
        return Result.ok("获取验证码成功", returnMap);
    }

    /**
     * 获取音频验证码数据数据
     *
     * @return
     */
    public AudioCaptcha getAudioCaptcha(String outputType) {
        AudioCaptcha captcha = new AudioCaptcha.Builder().addAnswer().addVoice().addNoise().build(); // Required
//            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\angelo\\Desktop\\123.wav"));
//            fileOutputStream.write(baos.toByteArray());
//            fileOutputStream.flush();
//            fileOutputStream.close();
       return captcha;
    }

    public byte[] convertWavToMp3(byte[] source){
        try{

            FileOutputStream fileOutputStream = new FileOutputStream(new File("123.wav"));
            fileOutputStream.write(source);
            fileOutputStream.flush();
            fileOutputStream.close();
            File target = new File(desFileName);
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(36000)); //音频比率 MP3默认是1280000
            audio.setChannels(new Integer(2));
            audio.setSamplingRate(new Integer(44100));
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ;
    }

    /**
     * 获取默认的汉字验证码
     *
     * @param width
     * @param height
     * @param captchaLength
     * @return
     */
    public Captcha getChsCaptcha(int width, int height, int captchaLength) {
        Captcha.Builder builder = new Captcha.Builder(width, height);
        builder.addBorder();
        builder.addNoise();
        java.util.List<Font> fontList = new ArrayList<Font>();
        fontList.add(new Font("宋体", Font.ITALIC, 25));//可以设置斜体之类的
        java.util.List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.green);
        colorList.add(Color.blue);
        ColoredEdgesWordRenderer cwr = new ColoredEdgesWordRenderer(colorList, fontList);
        WordRenderer wr = cwr;
        builder.addText(new DefaultTextProducer(captchaLength, CaptchaData.baseChsCaptcha.toCharArray()), wr);
        builder.gimp(new BlockGimpyRenderer(1));
        Captcha captcha = builder.build();
        return captcha;
    }

    /**
     * 获取默认的数字字母验证码
     *
     * @param width
     * @param height
     * @param captchaLength
     * @return
     */
    public Captcha getDefaultCaptcha(int width, int height, int captchaLength, String catchaStyle) {
        Captcha captcha = new Captcha.Builder(width, height)
                .addText(new EasyCharTextProducer(captchaLength, catchaStyle),
                        new FixedWordRenderer(Color.white, null))
                .gimp(new FishEyeGimpyRenderer()).addBorder().build();
        return captcha;
    }

    public static void main(String[] args) {
        CaptchaService captchaService = new CaptchaService();
        try {
            captchaService.createSimpleCode(140, 40, 5, "mp3", "png");
        } catch (Exception excption) {
            excption.printStackTrace();
        }
    }
}
