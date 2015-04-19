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
	 *  ����Դ��a,b�������롣����Դ��aΪ���������룬b�ú�����������š���������Ĺ������£�
	 *	1.	�ȴ�����ʵ绰��׺Ϊ'00'�������ǰ׺Ϊ00����ô�жϽ����ŵĺ����Ƿ�Ϊ'86'�����Ϊ�棬��ʾ�����绰Ϊ���ڵ绰��'86'��ĵ绰������Ϊ���������Ų��䣬���µ��ñ��������������'86'����ʾ�õ绰Ϊ����绰��ԭ�����أ�������������ǰ��λ��Ϊ'00'��ִ�е�2���� 	
	 *	2.	�ж�ǰ׺�Ƿ�Ϊ'0'�����Ϊ�棬��ô�ж����ĺ����Ƿ�Ϊ�ֻ��Ų��ҳ����㹻��������ֻ��ţ������Ȳ�������������ؿշ���ȡ���ֻ����룬�������ء����ǰ׺��Ϊ'0'����ǰ׺��'0'�����������벻���ֻ�ǰ׺����������ִ�С� 
     *  3.	�жϺ���ǰ׺�Ƿ�Ϊ���ţ�����ǣ���Ҫ�жϺ��볤���Ƿ�Ϊ7λ��8λ����Щ�л������ǰ׺��������ͬ����Щ�����е�ǰ׺����ȥ���������Ϊ�棬��ú��벻�������ԭ�����ء����С��7λ�����8λ����Ҫȥ��ǰ�ߵ����ţ��Ѻ�ߵĵ绰���벿����Ϊ�������ٵ��ñ����� 
	 *	4.	�жϺ����ǰ׺�Ƿ�ΪIP�绰ǰ׺����������Ӫ�̵Ĺ̶�ǰ׺������ǣ����ȥ����ǰ׺�ĺ�����Ϊ�������ٴε��øú��� 
	 *	5.	������϶����ǣ�������޷��������ѹ�����ϣ�ԭ�����ء�
	 * @throws MethodDealException 
	 * 
	 * 
	 *     �й��ƶ��ŶΣ�134��135��136��137��138��139��150��151��152��157��158��159��147��182��183��184[1]��187��188
     *     �й���ͨ�ŶΣ�130��131��132��145��155��156��185��186 ��145������ͨ�����������ŶΣ���176
     *     �й����źŶΣ�133 ��153 ��180 ��181 ��189
     *     ����  13 14 15 18 ��ͷ�ж��ǲ��� �ƶ��绰    �Ƿ�������⣿
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException {
		String[] strArr = str.split(",");
		String a = "";
		String b = "";
		
		//��ʱ����
		//180123456778��  ״�� ȥ����ֱ�ӷ���
		if(strArr.length == 2){
			a = strArr[0];
			b = strArr[1];
		} else {
			a =strArr[0];
			b = "";
		}
		
		//14   ����14
		if(a.length() < 3){
			return a;
		}
		
		//�жϺ����ǰ׺�Ƿ�ΪIP�绰ǰ׺����������Ӫ�̵Ĺ̶�ǰ׺��
		//����ǣ����ȥ����ǰ׺�ĺ�����Ϊ�������ٴε��øú��� 
		//if(����ǰ׺Ϊip�绰������������Ӫ�̶̹�ǰ׺)
				//return new Metho2().invoke(ȥ��ǰ׺��defaultValue, param);
		//ȥ��179��ͷ��ip���� ����Ĭ��179��ͷ����
		//�й�����IP����� ���� 17900 17901
		//��������IP����� ���� 17908 17909
		//�й���ͨIP����� ���� 17910 17911
		//�й��ƶ�IP����� ���� 17950 17951
		//�й���ͨIP����� ���� 17960 17961
		//������ͨIP����� ���� 17968 17969
		//�й���ͨIP����� ���� 17990 17991
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
				
				
		//�ȴ�����ʵ绰��׺Ϊ'00'�������ǰ׺Ϊ00����ô�жϽ����ŵĺ����Ƿ�Ϊ'86'��
		//���Ϊ�棬��ʾ�����绰Ϊ���ڵ绰��'86'��ĵ绰������Ϊ���������Ų��䣬���µ��ñ�������
		//�������'86'����ʾ�õ绰Ϊ����绰��ԭ�����أ�������������ǰ��λ��Ϊ'00'��ִ�е�2���� 	
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
		//*********�����86��ͷ��  ȥ��86  ���¹���   ������ĵ���δ�ᵽ  ����13λ �ֻ���
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
			
		//�ж�ǰ׺�Ƿ�Ϊ'0'�����Ϊ�棬��ô�ж����ĺ����Ƿ�Ϊ�ֻ��Ų��ҳ����㹻��
		//������ֻ��ţ������Ȳ�������������ؿ�,����ȡ���ֻ����룬�������ء�
		//����ȡ���ֻ�����   ȥ��0��������
		//���ǰ׺��Ϊ'0'����ǰ׺��'0'�����������벻���ֻ�ǰ׺����������ִ�С� 
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
				//�жϺ���ǰ׺�Ƿ�Ϊ���ţ�����ǣ���Ҫ�жϺ��볤���Ƿ�Ϊ7λ��8λ
				//����Щ�л������ǰ׺��������ͬ����Щ�����е�ǰ׺����ȥ������
				//���Ϊ�棬��ú��벻�������ԭ�����ء�
				//���С��7λ�����8λ����Ҫȥ��ǰ�ߵ����ţ��Ѻ�ߵĵ绰���벿����Ϊ�������ٵ��ñ����� 
				//***************���� ��� 07938309226|8309226 Դ�� Ϊ  07938309226 793   ���ĵ�����
				if(a.startsWith("0" + b)){					
						//method2Ӧ���ù�������
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
		
		
		
		//**********ֱ�������ŵ����� a:00867017072815 b:701  ���أ�7072815
		if( a.startsWith(b)){ 
			if(a.length() == 7  || a.length() == 8){
				return  a.substring(b.length() );
			} else {
				return a.substring(b.length() );			
			}
		}
		
		//��ʲô����
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
		//86300860 791   ���860���� ����û��
		//3|300860|0
		//3|300200|200
		//3||00  8630000
		if(a.startsWith("300860")){
			return "0";
		}
		
		if(a.startsWith("300")){
			return a.substring(3);
		}
		
		//12590  ֱ�ӷ���12590
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
		
		//172 ҵ�� 17208|172 a��8617208 b��792
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
