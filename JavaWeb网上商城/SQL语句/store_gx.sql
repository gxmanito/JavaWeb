/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : store_gx

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-06 13:10:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码');
INSERT INTO `category` VALUES ('172934bd636d485c98fd2d3d9cccd409', '运动户外');
INSERT INTO `category` VALUES ('2', '电脑办公');
INSERT INTO `category` VALUES ('3', '家具家居');
INSERT INTO `category` VALUES ('4', '鞋靴箱包');
INSERT INTO `category` VALUES ('5', '图书音像');
INSERT INTO `category` VALUES ('59f56ba6ccb84cb591c66298766b83b5', '军用飞机');
INSERT INTO `category` VALUES ('6', '母婴孕婴');
INSERT INTO `category` VALUES ('afdba41a139b4320a74904485bdb7719', '汽车用品');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` varchar(32) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `fk_0001` (`pid`),
  KEY `fk_0002` (`oid`),
  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('06B335F97EC3493F8109A61A9A45F5EC', '1', '4899', '47', 'A4EF8AB6B75C47B69766D266E6D360AE');
INSERT INTO `orderitem` VALUES ('4210513072434855865786A793EA7DC2', '1', '4899', '47', '21303849EE9C48CCA08799D97D5D1E92');
INSERT INTO `orderitem` VALUES ('4A19C8A14C6F407E83EC55DAAAF57674', '1', '2299', '31', 'B7A52C1534244E64940C2C5C51C9BF53');
INSERT INTO `orderitem` VALUES ('4B61FFE073404177B7837DAB0E04AF62', '1', '2299', '50', 'E9D7EC20B7004543ABDF486CCCA6A448');
INSERT INTO `orderitem` VALUES ('5442C7E481AE4391806374A04097B54D', '1', '2299', '50', '6203C222BBC84BF49F2D96C7680A94B5');
INSERT INTO `orderitem` VALUES ('6C2A302297394A479F16F5C112964B2C', '1', '1999', '4', '7533AD5E7D56480AAD7664AAA081DB66');
INSERT INTO `orderitem` VALUES ('762E5962AA2C4BE3B5E674968279D020', '1', '2299', '50', 'D21B05D80DBD48E892CC913460D4BBCE');
INSERT INTO `orderitem` VALUES ('7FC06F24EC78406F99DD9A0CF4472F6B', '1', '1699', '23', '1F8E9056C9424FE6908B8C6AA2251303');
INSERT INTO `orderitem` VALUES ('83422E4225564991A644DB45CD6CDAF3', '112', '257488', '50', '570E23B940F842528404289CE6356962');
INSERT INTO `orderitem` VALUES ('864D74EA5D4F45CEA30E5D9B3247FD0C', '1', '1398', '7', 'A476EB85904746CD9DE8F464CAEBC24E');
INSERT INTO `orderitem` VALUES ('8730D65E8A8349DC84BB95D50EF2AB8B', '1', '4899', '47', 'EB7F6130C5F040D882B68C56A7D6C2AE');
INSERT INTO `orderitem` VALUES ('888A71BE3B014AB3BA965D781ECD72E3', '1', '4899', '47', 'A476EB85904746CD9DE8F464CAEBC24E');
INSERT INTO `orderitem` VALUES ('8BDA084ECD4B4CF9B82D1C29AD024E98', '1', '1699', '23', 'BA23E69B0B9346CA8E4CC0077475ACC7');
INSERT INTO `orderitem` VALUES ('971EBFF40B5D4949A9DA2AF3B879104A', '1', '1999', '4', '570E23B940F842528404289CE6356962');
INSERT INTO `orderitem` VALUES ('9823B94AD11C4B14A101E33D9F5EE515', '112', '257488', '50', '7533AD5E7D56480AAD7664AAA081DB66');
INSERT INTO `orderitem` VALUES ('98C01A9E7B2646079657ABFB763C1F14', '1', '4899', '47', 'D21B05D80DBD48E892CC913460D4BBCE');
INSERT INTO `orderitem` VALUES ('9F04D611B3884A459EA45FD129697447', '2', '5198', '10', '7533AD5E7D56480AAD7664AAA081DB66');
INSERT INTO `orderitem` VALUES ('AEA4AE2C4AA440C0B91D78714476AD8D', '1', '1398', '7', '0556A27D4DA34AF2A481F3B92F321ABC');
INSERT INTO `orderitem` VALUES ('AEDDBCB473EF43B69BC7F2653E6BECA7', '1', '4899', '47', '72C9739A48A04C8FAF5EDD3D90E36AC7');
INSERT INTO `orderitem` VALUES ('B7BB156A50914683AB1F23C86E70EDB3', '1', '4899', '47', 'DF0D2955C41A423E93EF9853FF02DB1D');
INSERT INTO `orderitem` VALUES ('B92A6ED1CFEA46AC861F14EBE4CC36EC', '1', '4899', '47', 'BB79CD6C22E54DDF90E5196557775807');
INSERT INTO `orderitem` VALUES ('BAEEF50F62154E938844CDF5FDF63A77', '1', '4899', '47', 'BA23E69B0B9346CA8E4CC0077475ACC7');
INSERT INTO `orderitem` VALUES ('BCEC006F5B2C46ABB21E637F8D70ED65', '1', '1398', '7', 'A4EF8AB6B75C47B69766D266E6D360AE');
INSERT INTO `orderitem` VALUES ('C025A76A4396441AB6566C1E8C37187E', '2', '5198', '10', '570E23B940F842528404289CE6356962');
INSERT INTO `orderitem` VALUES ('C2A72DB82F3D44CD8EA194E4727F86CA', '1', '1398', '7', 'BA23E69B0B9346CA8E4CC0077475ACC7');
INSERT INTO `orderitem` VALUES ('C737397546E740258E84C54A399BA595', '1', '1398', '7', 'DEC7A44450D540E383179654F7E8E1E9');
INSERT INTO `orderitem` VALUES ('D188FBB6C7DD4ACFAE5D70F4B836769A', '1', '4899', '47', '97C2E903DEBF47D0A7DC0CE11AB02253');
INSERT INTO `orderitem` VALUES ('EB03754550884D28B2F876EC81DB57D0', '1', '2299', '50', 'BF1B57CF0D0D41CDBE5FFDB16403A3BA');
INSERT INTO `orderitem` VALUES ('FC5446039A82457CA22635CCC6617B60', '1', '1398', '7', 'B7A52C1534244E64940C2C5C51C9BF53');
INSERT INTO `orderitem` VALUES ('FC7BE700A1244085A6954D900DB16BE4', '1', '1699', '23', '8083C5343635452D94AE2EC5F7A6DA14');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0556A27D4DA34AF2A481F3B92F321ABC', '2017-10-03 12:59:06', '1398', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('1F8E9056C9424FE6908B8C6AA2251303', '2017-10-03 13:54:10', '1699', '0', null, null, null, 'F9C949987536453ABFBE4DF45B356A65');
INSERT INTO `orders` VALUES ('21303849EE9C48CCA08799D97D5D1E92', '2017-10-03 00:42:22', '4899', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('30363B789F114FF7A420E87B101ACA70', '2017-10-03 00:40:59', '0', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('570E23B940F842528404289CE6356962', '2017-10-02 23:05:30', '264685', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('6203C222BBC84BF49F2D96C7680A94B5', '2017-10-03 14:26:12', '2299', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('72C9739A48A04C8FAF5EDD3D90E36AC7', '2017-10-03 12:30:35', '4899', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('7533AD5E7D56480AAD7664AAA081DB66', '2017-10-02 23:05:56', '264685', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('8083C5343635452D94AE2EC5F7A6DA14', '2017-10-03 17:55:20', '1699', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('89B25ED5125F420499F7E5B21CB4F3B3', '2017-10-03 00:42:29', '0', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('97C2E903DEBF47D0A7DC0CE11AB02253', '2017-10-03 00:31:36', '4899', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('A476EB85904746CD9DE8F464CAEBC24E', '2017-10-03 12:30:40', '6297', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('A4EF8AB6B75C47B69766D266E6D360AE', '2017-10-03 00:40:15', '6297', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('B6785824AF074829A6C99A5B6FF5718A', '2017-10-03 00:40:34', '0', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('B7A52C1534244E64940C2C5C51C9BF53', '2017-10-03 17:55:48', '3697', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('BA23E69B0B9346CA8E4CC0077475ACC7', '2017-10-03 12:30:56', '7996', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('BB79CD6C22E54DDF90E5196557775807', '2017-10-03 00:31:53', '4899', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('BF1B57CF0D0D41CDBE5FFDB16403A3BA', '2017-10-03 00:43:59', '2299', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('D21B05D80DBD48E892CC913460D4BBCE', '2017-10-02 22:54:14', '7198', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('DEC7A44450D540E383179654F7E8E1E9', '2017-10-03 00:40:57', '1398', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('DF0D2955C41A423E93EF9853FF02DB1D', '2017-10-02 22:45:12', '4899', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('E9D7EC20B7004543ABDF486CCCA6A448', '2017-10-03 12:56:50', '2299', '0', '??', '???', '123123', '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('EB7F6130C5F040D882B68C56A7D6C2AE', '2017-10-03 17:55:06', '4899', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');
INSERT INTO `orders` VALUES ('F8AA1C28C0D14AE48081B1BA0BB2EB62', '2017-10-03 00:40:51', '0', '0', null, null, null, '7AB701F328354D59BC532FE3F6A3E9B5');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `pflag` int(11) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sfk_0001` (`cid`),
  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 4c 标准版', '1399', '1299', 'products/1/c_0001.jpg', '2015-11-02', '1', '小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待', '0', '1');
INSERT INTO `product` VALUES ('10', '华为 Ascend Mate7', '2699', '2599', 'products/1/c_0010.jpg', '2015-11-02', '1', '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', '0', '1');
INSERT INTO `product` VALUES ('11', 'vivo X5Pro', '2399', '2298', 'products/1/c_0014.jpg', '2015-11-02', '1', '移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术', '0', '1');
INSERT INTO `product` VALUES ('12', '努比亚（nubia）My 布拉格', '1899', '1799', 'products/1/c_0013.jpg', '2015-11-02', '0', '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', '0', '1');
INSERT INTO `product` VALUES ('13', '华为 麦芒4', '2599', '2499', 'products/1/c_0012.jpg', '2015-11-02', '1', '华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖', '0', '1');
INSERT INTO `product` VALUES ('14', 'vivo X5M', '1899', '1799', 'products/1/c_0011.jpg', '2015-11-02', '0', 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', '0', '1');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', '4399', '4288', 'products/1/c_0015.jpg', '2015-11-02', '1', 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', '0', '1');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', '4200', '4087', 'products/1/c_0016.jpg', '2015-11-03', '0', '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', '0', '1');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', '4099', '3999', 'products/1/c_0017.jpg', '2015-11-02', '0', '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', '0', '1');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', '3599', '3499', 'products/1/c_0018.jpg', '2015-11-02', '0', 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', '0', '1');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', '1599', '1469', 'products/1/c_0020.jpg', '2015-11-02', '1', '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', '0', '1');
INSERT INTO `product` VALUES ('2', '中兴 AXON', '2899', '2699', 'products/1/c_0002.jpg', '2015-11-05', '1', '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', '0', '1');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', '649', '549', 'products/1/c_0019.jpg', '2015-11-02', '0', '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', '0', '1');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', '1099', '999', 'products/1/c_0021.jpg', '2015-11-02', '0', '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', '0', '1');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', '2099', '1999', 'products/1/c_0022.jpg', '2015-11-02', '1', '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', '0', '1');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', '1799', '1699', 'products/1/c_0023.jpg', '2015-11-09', '1', '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', '0', '1');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', '3988', '3888', 'products/1/c_0024.jpg', '2015-11-02', '1', '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', '0', '1');
INSERT INTO `product` VALUES ('25', 'Apple iPhone 6 Plus (A1524) 16GB 金色', '5188', '4988', 'products/1/c_0025.jpg', '2015-11-02', '0', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '0', '1');
INSERT INTO `product` VALUES ('26', 'Apple iPhone 6s (A1700) 64G 玫瑰金色', '6388', '6088', 'products/1/c_0026.jpg', '2015-11-02', '0', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '0', '1');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', '5588', '5388', 'products/1/c_0027.jpg', '2015-11-02', '0', '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', '0', '1');
INSERT INTO `product` VALUES ('28', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', '5999', '5888', 'products/1/c_0028.jpg', '2015-11-02', '0', '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', '0', '1');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', '3018', '2978', 'products/1/c_0029.jpg', '2015-11-02', '0', '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', '0', '1');
INSERT INTO `product` VALUES ('3', '华为荣耀6', '1599', '1499', 'products/1/c_0003.jpg', '2015-11-02', '0', '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', '0', '1');
INSERT INTO `product` VALUES ('30', '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', '1099', '999', 'products/1/c_0030.jpg', '2015-11-02', '0', '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', '0', '1');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', '2399', '2299', 'products/1/c_0031.jpg', '2015-11-02', '0', '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', '0', '2');
INSERT INTO `product` VALUES ('32', 'Apple MacBook Air MJVE2CH/A 13.3英寸', '6788', '6688', 'products/1/c_0032.jpg', '2015-11-02', '0', '宽屏笔记本电脑 128GB 闪存', '0', '2');
INSERT INTO `product` VALUES ('33', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)', '4399', '4199', 'products/1/c_0033.jpg', '2015-11-02', '0', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', '0', '2');
INSERT INTO `product` VALUES ('34', '联想（Lenovo）小新V3000经典版', '4599', '4499', 'products/1/c_0034.jpg', '2015-11-02', '0', '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', '0', '2');
INSERT INTO `product` VALUES ('35', '华硕（ASUS）经典系列R557LI', '3799', '3699', 'products/1/c_0035.jpg', '2015-11-02', '0', '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', '0', '2');
INSERT INTO `product` VALUES ('36', '华硕（ASUS）X450J', '4599', '4399', 'products/1/c_0036.jpg', '2015-11-02', '0', '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', '0', '2');
INSERT INTO `product` VALUES ('37', '戴尔（DELL）灵越 飞匣3000系列', '3399', '3299', 'products/1/c_0037.jpg', '2015-11-03', '0', ' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', '0', '2');
INSERT INTO `product` VALUES ('38', '惠普(HP)WASD 暗影精灵', '5699', '5499', 'products/1/c_0038.jpg', '2015-11-02', '0', '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', '0', '2');
INSERT INTO `product` VALUES ('38232F5E3A2C45D2B6F61D8CAC3D1777', '山泉水', '888', '666', 'products/6/1/E352EDF758DC45BAB0F9D03805FAFD25.jpg', '2017-10-05', '1', '新乡八里沟', '0', '172934bd636d485c98fd2d3d9cccd409');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', '11299', '10288', 'products/1/c_0039.jpg', '2015-11-02', '0', 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', '0', '2');
INSERT INTO `product` VALUES ('4', '联想 P1', '2199', '1999', 'products/1/c_0004.jpg', '2015-11-02', '0', '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', '0', '1');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', '6799', '6599', 'products/1/c_0040.jpg', '2015-11-02', '0', '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', '0', '2');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', '5699', '5499', 'products/1/c_0041.jpg', '2015-11-02', '0', '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', '0', '2');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', '6199', '5999', 'products/1/c_0042.jpg', '2015-11-02', '0', '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', '0', '2');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', '5699', '5499', 'products/1/c_0043.jpg', '2015-11-02', '0', '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', '0', '2');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', '3199', '3099', 'products/1/c_0044.jpg', '2015-11-02', '0', '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', '0', '2');
INSERT INTO `product` VALUES ('44272E6F852F4075AA3F4283CC4392E8', '山泉水', '888', '666', 'products/7/6/4156CE947B054B5AA1F1DA8462452617.jpg', '2017-10-05', '1', '新乡八里沟', null, '172934bd636d485c98fd2d3d9cccd409');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', '10999', '9899', 'products/1/c_0045.jpg', '2015-11-02', '0', '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', '0', '2');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', '3299', '3199', 'products/1/c_0046.jpg', '2015-11-02', '0', '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', '0', '2');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', '5099', '4899', 'products/1/c_0047.jpg', '2015-11-11', '0', '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', '0', '2');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', '2088', '1888', 'products/1/c_0048.jpg', '2015-11-02', '0', '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', '0', '2');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', '1399', '1299', 'products/1/c_0049.jpg', '2015-11-02', '0', 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', '0', '2');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', '1799', '1699', 'products/1/c_0005.jpg', '2015-11-01', '0', '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', '0', '1');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', '2399', '2299', 'products/1/c_0050.jpg', '2015-11-12', '0', '（9.7英寸 16G WLAN 机型 银色）', '0', '2');
INSERT INTO `product` VALUES ('6', '魅族 MX5 16GB 银黑色', '1899', '1799', 'products/1/c_0006.jpg', '2015-11-02', '0', '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', '0', '1');
INSERT INTO `product` VALUES ('7', '三星 Galaxy On7', '1499', '1398', 'products/1/c_0007.jpg', '2015-11-14', '0', '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', '0', '1');
INSERT INTO `product` VALUES ('8', 'NUU NU5', '1288', '1190', 'products/1/c_0008.jpg', '2015-11-02', '0', 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', '0', '1');
INSERT INTO `product` VALUES ('9', '乐视（Letv）乐1pro（X800）', '2399', '2299', 'products/1/c_0009.jpg', '2015-11-02', '0', '乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！', '0', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('05C493ECE73640239F429F5D4980F008', 'ZZ', 'ZZ', 'ZZ', 'ZZ@QQ.com', null, '2017-10-11', '1', '0', '1568D6DD9BF247D3ACD4C819B8FED599');
INSERT INTO `user` VALUES ('2B3A6128B47741DF9239191815297D2C', 'ZZ', 'ZZ', 'ZZ', 'ZZ@QQ.com', null, '2017-10-06', '1', '0', 'B6219D5B2C354C50A160762DD53DAE41');
INSERT INTO `user` VALUES ('69C7F4B70873477591FCB15DBA0CB4FC', 'ZZ', 'ZZ', 'ZZ', 'ZZ@QQ.com', null, '2017-10-12', '1', '0', '72D131D1BB574C34BC665B451C722726');
INSERT INTO `user` VALUES ('7AB701F328354D59BC532FE3F6A3E9B5', '汤姆', '111', 'admin', 'tom@store.com', null, '2017-09-30', '1', '1', null);
INSERT INTO `user` VALUES ('C4DA031B04BA4F41ADD7D3D0013A3A52', 'AA', '000', 'AA', 'AA@qq.com', null, '2017-09-30', '0', '0', '5096618B847C4A5A82FBA841AC41EF20');
INSERT INTO `user` VALUES ('F9C949987536453ABFBE4DF45B356A65', '李晨阳', '000', '李晨阳', 'leihou@lh.com', null, '2017-10-04', '1', '1', '');
INSERT INTO `user` VALUES ('FAF9C449453343BDBB4BC76E25B072AF', 'AAAAAAAAA', 'AA', 'AA', 'AA@qq.com', null, '2017-10-07', '1', '0', 'D5B3E0E037334DD1A8248B3CAC099B1F');
