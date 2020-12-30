#lich工具包


[TOC]


## 主要功能

* jar包冲突解决（conflictResolution）
* 数据封装（dataFormat）
* 基于时间的动态密码生成工具（dynamicPassword)
* 对象工具类（object）
* 加解密工具类（encryptionAndDecryption)

##jar包冲突解决（conflictResolution）
----建设中----
##数据封装（dataFormat）
----建设中----
##基于时间的动态密码生成工具（dynamicPassword)
----建设中----
##对象工具类（object）
----建设中----
##加解密工具类（encryptionAndDecryption)
本工具类是基于bouncycastle的在封装,并利用conflictResolution工具实现了jar包无冲突主要功能
* 支持国密、RSA证书本地 生成、签发、签名\验签、加密\解密
* 支持国密单\双证 CA签发模式
* 支持RSA  CA签发模式
* 支持CRC\MD5\SHA\SM3等散列算法
* 支持AES\DES\SM4等对称加密算法

###Provider描述类
 类Provider为算法叙述文件,可以方便的找到对应算法的参数，示例如下
``` 
Provider.GM.Cipher.SM2WITHSM3;//国密.加解密.加密散列hash算法为SM2WITHSM3
Provider.RSA.Signature.SHA1WithRSA;//RSA.签名.摘要算法为SHA1WithRSA

``` 

###RSA\GM密钥对生成工具类(KeyPairTool)
``` 
KeyPair gmKeyPair=KeyPairTool.generateGMKeyPair();//国密密钥对生成
KeyPair rsaKeyPair=KeyPairTool.generateRSAKeyPair(1024);//RSA密钥对生成 参数为公钥长度
```
###密钥对工具类（KeyStoreTool）
####导出pfx(p12)
```
KeyStoreTool.toPKCS12(keyPair, pki, alias, pwd);
KeyStoreTool.toPKCS12(prk, certs, alias, pwd);
```
####加载pfx(p12)
```
P12Data k=KeyStoreTool.loadPKCS12(p12, "123456");
k.getCert();//公钥证书
k.getPrivateKey();//私钥
k.getCertificateChain();//证书链
```
####生成PKCS10证书申请
```
KeyStoreTool.toPKCS10(k, "C=CN,O=lich", Provider.RSA.Signature.SHA1WithRSA);
KeyStoreTool.toPKCS10(prk,puk, "C=CN,O=lich", Provider.RSA.Signature.SHA1WithRSA);
```
###公钥（publicKey）、公钥证书(x509Certificate)工具类(PublicKeyTool)
####publicKey->x509Certificate
此过程生成的公钥证书为自签证书，不具备法律效应
```
PublicKeyTool.getX509Certificate(pki, pk);
```
####获取公钥值
GM返回格式为 04|x|y RSA返回modulus
```
PublicKeyTool.getPublicKeyByte(publicKey);
```
###私钥（privateKey）工具类(privateKeyTool)
####加载私钥(GM)
```
PrivateKeyTool.toGMPrivateKey(d,P);
PrivateKeyTool.toGMPrivateKeyByEnvelopedKeyBlob(doubleprvkey);
PrivateKeyTool.toGMPrivateKeyByEnvelopedKeyBlob(doubleprvkey,privateKey);
PrivateKeyTool.toGMPrivateKeyBySignedAndEnvelopedData(doubleprvkey,puk);
PrivateKeyTool.toGMPrivateKeyBySignedAndEnvelopedData(doubleprvkey,privateKey,puk);
```
####加载私钥(RSA)
```
PrivateKeyTool.toRSAPrivateKey(pk);

```
###签名\验签、加密\解密（AsymmetricTool）
####签名
```
AsymmetricTool.sign(ori, privateKey, Provider.RSA.Signature.SHA256WithRSA);
AsymmetricTool.sign(ori, privateKey, (X509Certificate)cert);
```
####验签
```
AsymmetricTool.verify(sign, ori, (X509Certificate)cert);
AsymmetricTool.verify(sign, ori, publicKey,Provider.RSA.Signature.SHA256WithRSA);
```
####加密
```
AsymmetricTool.encrypt(ori, publicKey, Provider.RSA.Cipher.RSA);
```
####解密
```
AsymmetricTool.decrypt(enc, privateKey, Provider.RSA.Cipher.RSA);
```
##示例
###RSA证书发证、使用
```
	/******密钥生成*******/
    KeyPair kp=KeyPairTool.generateRSAKeyPair(2048);
    Date notBefore=new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, 1);
    Date notAfter = calendar.getTime();
    String subject="C=CN , CN=lich";
    BigInteger serial=BigInteger.valueOf(System.currentTimeMillis());
    PublicKeyInfo pki=new PublicKeyInfo(serial,notBefore, notAfter, subject);
    Certificate cert=PublicKeyTool.getX509Certificate(pki, kp.getPublic());
    byte [] p12= KeyStoreTool.toPKCS12(kp.getPrivate(), new Certificate[] {cert}, "测试证书", "123456");
    System.out.println("cer:"+Base64.encodeBase64String(cert.getEncoded()));
    System.out.println("pfx:"+Base64.encodeBase64String(p12));
    /******密钥加载*******/
    P12Data k=KeyStoreTool.loadPKCS12(p12, "123456");
    cert=k.getCert();
    PrivateKey prk= k.getPrivateKey();
    /******签名验签*******/
    byte[] ori="测试原文".getBytes("utf-8");
    byte[]  sign= AsymmetricTool.sign(ori, privateKey, (X509Certificate)cert);
    System.out.println("sign:"+Base64.encodeBase64String(sign));
    System.out.println("verify:"+AsymmetricTool.verify(sign, ori, (X509Certificate)cert));
     /******加解密*******/
    ori="加密原文".getBytes("utf-8");
    byte [] enc=AsymmetricTool.encrypt(ori, publicKey, Provider.RSA.Cipher.RSA);
    System.out.println("enc:"+Base64.encodeBase64String(enc));
    System.out.println("dec:"+new String(AsymmetricTool.decrypt(enc, privateKey, Provider.RSA.Cipher.RSA),"utf-8"));

   ```
###GM双证发证流程



