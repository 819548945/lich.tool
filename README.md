
# lich工具包

**version 0.1.0**

## 主要功能

#### jar包冲突解决（conflictResolution）

* 利用独立classloader解决jar包冲突问题
* 独立的反射封装，简化了代码

#### 数据封装（dataFormat）

* 将多段数据封装成byte数组快速传输

#### 基于时间的动态密码生成工具（dynamicPassword)

* 将军令的简化实现

#### 对象工具类（object）

* 对象Copy MAP\Object 、List 相互转换
* 优化批量添加的 ArrayList、HashMap
* Object数据替换工具

#### 加解密工具类（encryptionAndDecryption)

* encryptionAndDecryption.core 无jar包冲突封装

#### 加解密核心工具类（encryptionAndDecryption.core）

* 基于bouncycastle的在封装
* 支持国密、RSA证书本地 生成、签发、签名\验签、加密\解密
* 支持国密单\双证 CA签发模式
* 支持RSA  CA签发模式
* 支持MD5\SHA\SM3等散列算法
* 支持AES\DES\SM4等对称加密算法

git：  https://github.com/819548945/tool.encryptionAndDecryption.core

文档： http://doc.lich.tk/0.1.0/encryptionAndDecryption.core.html

bug、意见反馈: liuchao_@outlook.com

### 安装

maven 

````xml
<dependency>
  <groupId>com.github.819548945</groupId>
  <artifactId>tool</artifactId>
  <version>0.1.0</version>
</dependency>
````

