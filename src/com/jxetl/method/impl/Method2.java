package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method2 implements DealMethod {
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
		if(a.startsWith("12592") || a.startsWith("12593") || a.startsWith("17951") || a.startsWith("17950")){
			a = a.substring(5);
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
		
		//*********�����86��ͷ��  ȥ��86  ���¹���   ������ĵ���δ�ᵽ  ����13λ �ֻ���
		while( a.startsWith("86")){
			a = a.substring(2);
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
		
		
		
		if(a.startsWith("13") || a.startsWith("145") || a.startsWith("147")|| 
				a.startsWith("15") || a.startsWith("18")){
			
			if(a.length() > 11){
				return a.substring(0 , 11);
			} else if(a.length() == 11){
				return a;
			} else {
				return "";
			}
		}
		
		//**********ֱ�������ŵ����� a:00867017072815 b:701  ���أ�7072815
		if(a.startsWith(b)){ 
			if(a.length() == 7  || a.length() == 8){
				
				//��ʲô����
				if(a.endsWith("96202")){
					return "96202";
				}
				
				if(a.endsWith("95559")){
						return "95559";
				}
				
				if(a.endsWith("95533")){
					return "95533";
				}
				
				if(a.endsWith("95566")){
					return "95566";
				}
				
				if(a.endsWith("95588")){
					return "95588";
				}
				
				return  a.substring(b.length() );
			} else {
				return a.substring(b.length() );			
			}
		}
		
		if(a.startsWith("163")){
			return "163";
		}
		
		if(a.startsWith("200")){
			return a.substring(3);
		}
		
		if(a.startsWith("300")){
			return a.substring(3);
		}
		
		//12590  ֱ�ӷ���12590
		if(a.startsWith("12590")){
			return "12590";
		}
		
		
		
		//12599 1259
		if(a.startsWith("1259")){
			if(a.length() > 13){
				return a.substring(4);
			} else {
				return "1259";
			}		
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
		

		
		
		

		return a;
	}

}
