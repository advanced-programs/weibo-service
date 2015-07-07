package zx.soft.weibo.sina.example;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 获取用户基本信息
 *
 * @author wanggang
 *
 */
public class UsersShow {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		String cookie = "SINAGLOBAL=1967857952695.3398.1430183667535; _s_tentry=login.sina.com.cn; Apache=6243587974458.933.1433726825631; ULV=1433726825660:3:1:1:6243587974458.933.1433726825631:1430816662844; login_sid_t=001965a1c28c55a224e738dda5783e23; un=wgxzy_1015@163.com; UOR=,,ent.ifeng.com; SUS=SID-1862087393-1436238674-GZ-0whpq-03ce46c5672abef5da25811248d5e4bd; SUE=es%3D671697a1ce8c8ad7ef60d07055dc7616%26ev%3Dv1%26es2%3D331cd66d1fcc0355bf19c60d6b91e47d%26rs0%3DaghTVO4Ej445DRI5nES%252BxWyKQHtytQDMXMkCJUL4HssQE3XNhws01QHrJmgZAm5UECfPOyzTrfECWCK5Et5lFJ7zNFXP7mIH3ir6AtNo2OMCzRxokAJ82gKlBlq%252BUDyScFj1%252BjsUcBK1AqVLe1TXFREOQ3zLUenrnt5gH9yG1gM%253D%26rv%3D0; SUP=cv%3D1%26bt%3D1436238674%26et%3D1436325074%26d%3Dc909%26i%3De4bd%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D2%26st%3D0%26uid%3D1862087393%26name%3Dwgxzy_1015%2540sina.com%26nick%3D%25E6%25B0%25B8%25E4%25B8%258D%25E6%25AD%25A2%25E6%25AD%25A5%26fmp%3D%26lcp%3D2012-03-12%252000%253A03%253A03; SUB=_2A254nzMCDeTxGedG7VAR-CnPwj-IHXVb7SPKrDV8PUNbvtBeLXL9kW8-SPdIX1GZubGQKtb1SVsk0ByRNA..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhOMc47RCc5Rfeu-vvUZEJ45JpX5KMt; SUHB=05iLVP-ro1wVou; ALF=1467774670; SSOLoginState=1436238674; JSESSIONID=35410988D39244A4CCE2D7ACB7BCE352";
		SinaDomain sinaDomain = api.usersShow("1586302422", cookie);
		System.out.println(JsonUtils.toJson(sinaDomain));

	}

}
