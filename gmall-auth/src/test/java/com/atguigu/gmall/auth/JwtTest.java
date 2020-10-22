package com.atguigu.gmall.auth;

import com.atguigu.gmall.common.utils.JwtUtils;
import com.atguigu.gmall.common.utils.RsaUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 别忘了创建D:\\project\rsa目录
	private static final String pubKeyPath = "D:\\test\\project\\rsa\\rsa.pub";
    private static final String priKeyPath = "D:\\test\\project\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "SIUUuuii*&*&&*");
    }

    @BeforeEach
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "8");
        map.put("username", "wh3");
        // 生成token
        String token = JwtUtils.generateToken(map, privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6IjgiLCJ1c2VybmFtZSI6IndoMyIsImV4cCI6MTYwMjY1MDczMH0.Er8Z_QWOavGU1umD7Npk_HbQF0ju86VgLbj4P3FObHLwXhACTokW2-uMdLp7DFH6qk2M_DKYwLhibv3H5pwg1xrespwGVlDttHupaB8lg_RJeNIGk1Eba-FMdzmliZSH33yogajFgwYua0ikL5iXlDDwY7Gr5i4uKuJY-kFIj85C3le6hV9Br3tVuquYtNy1lVmTvi2DObZ23vLan6FVHS7Y7MyiRlJD7YmYW--cQOOsw4LtGOsQUTHFqEXDVg2nZoofOdalrs1LHG-7n2NROH7DblNBpRxynIM2ETtjOg2BrGWVrG1xijLxV86yRLz6WN2WjeEHn76t6HTzOiB3Ug";

        // 解析token
        Map<String, Object> map = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + map.get("id"));
        System.out.println("userName: " + map.get("username"));
    }
}