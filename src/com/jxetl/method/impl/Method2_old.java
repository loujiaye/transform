package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method2_old implements DealMethod {
	public static final int ID = 2;
	@Override
	public int getID() {

		return ID;
	}

	/**
	 *  根据源列a,b规整号码。其中源列a为待规整号码，b该号码归属地区号。规整号码的规则如下：
	 *	1.	先处理国际电话（缀为'00'），如果前缀为00，那么判断紧接着的号码是否为'86'，如果为真，表示该条电话为国内电话把'86'后的电话号码做为参数，区号不变，重新调用本函数，如果不是'86'，表示该电话为国外电话，原样返回（不作分析）。前两位不为'00'，执行第2条。 	
	 *	2.	判断前缀是否为'0'，如果为真，那么判断其后的号码是否为手机号并且长度足够，如果是手机号，但长度不够，则出错，返回空否则取出手机号码，正常返回。如果前缀不为'0'，或前缀是'0'，但后续号码不是手机前缀，接着向下执行。 
     *  3.	判断号码前缀是否为区号，如果是，还要判断号码长度是否为7位或8位（有些市话号码的前缀与区号相同，这些号码中的前缀不能去掉），如果为真，则该号码不需规整，原样返回。如果小于7位或大于8位，则要去掉前边的区号，把后边的电话号码部分作为参数，再调用本函数 
	 *	4.	判断号码的前缀是否为IP电话前缀、或其它运营商的固定前缀，如果是，则把去掉该前缀的号码作为参数，再次调用该函数 
	 *	5.	如果以上都不是，则该码无法规整或已规整完毕，原样返回。
	 * @throws MethodDealException 
	 * 
	 * 
	 *     中国移动号段：134、135、136、137、138、139、150、151、152、157、158、159、147、182、183、184[1]、187、188
     *     中国联通号段：130、131、132、145、155、156、185、186 （145属于联通无线上网卡号段）、176
     *     中国电信号段：133 、153 、180 、181 、189
     *     用以  13 14 15 18 开头判断是不是 移动电话    是否存在问题？
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException {
		String[] strArr = str.split(",");
		String a = "";
		String b = "";
		
		//暂时处理
		//180123456778，  状况 去掉，直接返回
		if(strArr.length == 2){
			a = strArr[0];
			b = strArr[1];
		} else {
			a =strArr[0];
			b = "";
		}
		
		//14   返回14
		if(a.length() < 3){
			return a;
		}
		
		//判断号码的前缀是否为IP电话前缀、或其它运营商的固定前缀，
		//如果是，则把去掉该前缀的号码作为参数，再次调用该函数 
		//if(号码前缀为ip电话，或者其他运营商固定前缀)
				//return new Metho2().invoke(去掉前缀，defaultValue, param);
		//去掉179开头的ip拨号 这里默认179开头都是
		//中国电信IP接入号 …… 17900 17901
		//广西电信IP接入号 …… 17908 17909
		//中国联通IP接入号 …… 17910 17911
		//中国移动IP接入号 …… 17950 17951
		//中国网通IP接入号 …… 17960 17961
		//广西网通IP接入号 …… 17968 17969
		//中国铁通IP接入号 …… 17990 17991
		//26|115949562468|15949562468 a:1259115949562468
		//26|1795013870435947|13870435947  a:1795013870435947 
		//26|1799600886928825894|886928825894 a:1799600886928825894
		if(a.startsWith("12591") || a.startsWith("12592") || a.startsWith("12593") || a.startsWith("17951")  
				|| a.startsWith("17950")  ||a.startsWith("17900")  || a.startsWith("17901")  || a.startsWith("17908")  ||   a.startsWith("17931")  ||
				a.startsWith("17991")  || a.startsWith("17996")  || a.startsWith("17990")){
			if(a.length() == 5){
				return a;
			} else {
				a = a.substring(5);
			}
		}
				
				
		//先处理国际电话（缀为'00'），如果前缀为00，那么判断紧接着的号码是否为'86'，
		//如果为真，表示该条电话为国内电话把'86'后的电话号码做为参数，区号不变，重新调用本函数，
		//如果不是'86'，表示该电话为国外电话，原样返回（不作分析）。前两位不为'00'，执行第2条。 	
		if(a.startsWith("00")){	
			if( a.startsWith("0086")){
				a = a.substring(4);
			} else {
				return a.substring(2);
			}			
		} 
		
		//26|00000|0000  a:86000000
		//26|0000|000       a:8600000
		if(a.startsWith("8600")){
			return a.substring(4);
		}
		//*********如果以86开头则  去掉86  重新规整   这个在文档中未提到  大于13位 手机号
		while( a.startsWith("86")){
			a = a.substring(2);
		}
		
		if(a.startsWith("12591") || a.startsWith("12592") || a.startsWith("12593") || a.startsWith("17951")  
				|| a.startsWith("17950") || a.startsWith("17900")  || a.startsWith("17901")  || a.startsWith("17908")  ||   a.startsWith("17931")  ||
				a.startsWith("17991")  || a.startsWith("17996")  || a.startsWith("17990")){
			if(a.length() == 5){
				return a;
			} else {
				a = a.substring(5);
			}
		}
		
		
		/**
		 * 26|190852213|852213 a:190852213 b:754
		 */
		if(a.startsWith("190")){
			return a.substring(3);
		}
		
		//a:8619666 b:796   66
		if(a.startsWith("196")){
			return a.substring(3);
		}
			
		//判断前缀是否为'0'，如果为真，那么判断其后的号码是否为手机号并且长度足够，
		//如果是手机号，但长度不够，则出错，返回空,否则取出手机号码，正常返回。
		//否则取出手机号码   去掉0？？？？
		//如果前缀不为'0'，或前缀是'0'，但后续号码不是手机前缀，接着向下执行。 
		if(a.startsWith("0")){				
			if(a.startsWith("013") || a.startsWith("0145") || a.startsWith("0147")|| 
					a.startsWith("015") || a.startsWith("018")){
				if(a.length() > 12){
					return a.substring(1 , 12);
				} else if(a.length() == 12){
					return a.substring(1);
				} else {
					return "";
				}
			} else {
				//判断号码前缀是否为区号，如果是，还要判断号码长度是否为7位或8位
				//（有些市话号码的前缀与区号相同，这些号码中的前缀不能去掉），
				//如果为真，则该号码不需规整，原样返回。
				//如果小于7位或大于8位，则要去掉前边的区号，把后边的电话号码部分作为参数，再调用本函数 
				//***************出现 情况 07938309226|8309226 源列 为  07938309226 793   与文档不符
				if(a.startsWith("0" + b)){					
						//method2应该用工厂调用
						return a.substring(b.length() + 1);
					
				}
				
			}
		}
		
		
		//3||14761 a:8614761  b:792
		//3|15507912|
		if(a.startsWith("13") || a.startsWith("145") || a.startsWith("147")|| 
				a.startsWith("15") || a.startsWith("18")){
			
			if(a.length() > 11){
				return a.substring(0 , 11);
			} else if(a.length() == 11){
				return a;
			} else {
				//3||14775 a:8614775
				if(a.startsWith("147")){
					return a;
				} 
				
				//3||14575  8614575
				if(a.startsWith("145")){
					return a;
				} 
				//3||186   8686186
				//3|18622| :8618622
				if(a.equals("186")){
					return a;
				}
					return "";
				
			}
		}
		
		
		
		//**********直接是区号的情形 a:00867017072815 b:701  返回：7072815
		if( a.startsWith(b)){ 
			if(a.length() == 7  || a.length() == 8){
				return  a.substring(b.length() );
			} else {
				return a.substring(b.length() );			
			}
		}
		
		//以什么结束
				if(a.endsWith("96202")){
					return "96202";
				}
				
				if(a.endsWith("95559")){
						return "95559";
				}
				
				//26|95533|6095533  a:6095533 b:
				if(a.endsWith("95533")){
					return "95533";
				}
				
				if(a.endsWith("95566")){
					return "95566";
				}
				
				if(a.endsWith("95588")){
					return "95588";
				}
		
		
		
		//3|06846232|46232 a:06846232 b:799
		if(a.startsWith("068")){
			a=a.substring(3);
		}
		//3|19788|88 a:8619788 b:792
		if(a.startsWith("193") || a.startsWith("195") || a.startsWith("197")){
			a=a.substring(3);
		}
		
		if(a.startsWith("163")){
			return "163";
		}
		
		if(a.startsWith("169")){
			return "169";
		}
		
		
		
		
		
		//3|005|5   a:86200005
		//3|007|7   a:86300007
		//3||00       a:8630000
		if(a.startsWith("20000")  || a.startsWith("30000")){
			if(a.length() == 5){
				return "00";
			} else{
				return a.substring(5);
			}
		}
		
		if(a.startsWith("200")){
			return a.substring(3);
		}
		//86300860 791   变成860不对 疑似没变
		//3|300860|0
		//3|300200|200
		//3||00  8630000
		if(a.startsWith("300860")){
			return "0";
		}
		
		if(a.startsWith("300")){
			return a.substring(3);
		}
		
		//12590  直接返回12590
		if(a.startsWith("12590")){
			return "12590";
		}
		
		
		
		//12599 1259
		//26|9313979754860|1259  a:12599313979754860
		if(a.startsWith("1259")){
			/*if(a.length() > 13){
				return a.substring(4);
			} else {*/
				return "1259";
			//}		
		}
		
		//172 业务 17208|172 a：8617208 b：792
		if(a.startsWith("172")){
			return "172";	
		}
		
		
		/**
		 * 26|12580995|12580 a:12580995 b:793
		 */
		if(a.startsWith("12580")){
			return "12580";
		}
		
		
	
	    //26|12588|1258 a:12588 b:793	
		//12582    1258
		if(a.startsWith("1258")){
			return "1258";
		}
		

		
		
		if(a.startsWith("00")){
			a=a.substring(2);
		}
		
		if(a.equals("02002")){
			return "02";
		}

		return a;
	}

}
