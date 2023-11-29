package com.up.iotbackend.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;

import static cn.hutool.core.codec.Base64.*;

/**
 * @Author dw
 * @ClassName WeChatUtil
 * @Description
 * @Date 2020/8/28 10:56
 * @Version 1.0
 */
public class WeChatUtil {

    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap<String, Object> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", "wxb04d22795ede2cc0");
        //小程序secret
        requestUrlParam.put("secret", "34d283647c9dc5ca2481021315228b80");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        String result = HttpUtil.post(requestUrl, requestUrlParam);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.println(code);
        return jsonObject;
    }

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = decode(sessionKey);
        // 偏移量
        byte[] ivByte = decode(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONUtil.parseObj(result);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static JSONObject getAccessToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        HashMap<String, Object> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", "wxb04d22795ede2cc0");
        //小程序secret
        requestUrlParam.put("secret", "34d283647c9dc5ca2481021315228b80");
        //默认参数
        requestUrlParam.put("grant_type", "client_credential");
        //发送post请求读取调用微信接口获取openid用户唯一标识
//        {
//            "access_token": "64_ycEqo-cyoxRfusYQkO37oN7YQ8oHQhV56Pil74U64Pa81Z7uefpfi5Y-DQvwiPxdWQE0BmraVsxhTNL7Ykr7pxvKTtmZ5klKaO_mmtgsmVMCBegs2Cgj8-L5pCcGYDaABAWOT",
//            "expires_in": 7200
//        }
        String result = HttpUtil.get(requestUrl, requestUrlParam);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject;
    }

    public static JSONObject getPhone(String access_token,String code) {
        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+access_token;
        HashMap<String, Object> requestUrlParam = new HashMap<>();
        //获取凭证
        requestUrlParam.put("code", code);
        //发送post请求读取调用微信接口获取openid用户唯一标识
        String result = HttpUtil.post(requestUrl, com.alibaba.fastjson.JSONObject.toJSONString(requestUrlParam));
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject;
    }

    //消息推送身份认证
    //20230429 这里只要一加AES密钥就会出问题先不放可以绑定加密解密没整明白
    public static final String TOKEN = "Uptech2022"; //开发者自行定义Tooken要与公众号平台输入的token保持一致
    public static final String ENCODINGAESKEY = "i9uGp0tCFNy4kcmLpX7RKZZmuJ595xckHPqP8wjsq9n"; //开发者自行定义AES与公众号平台输入的token保持一致
    public static Boolean authNotification(String signature,String timestamp,String nonce){
        String encryptString="";
        try {
            // 这里是对三个参数进行加密
            encryptString = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        } catch (AesException e) {
            e.printStackTrace();
        }
//        System.out.println("encryptString   "+encryptString);
//        System.out.println("signature   "+signature);
//        System.out.println("timestamp   "+timestamp);
//        System.out.println("nonce   "+nonce);
        if(encryptString.equals(signature)){
            return true;
        }else{
            return false;
        }
    }

    //消息推送


}