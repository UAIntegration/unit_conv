package unit_conv;

import com.pb.crm.conveyorapiutils.entity.*;
import com.pb.crm.conveyorapiutils.utils.HttpManager;
import java.util.*;


public class Unit_conv {

    public static void main(String[] args) throws Exception {
        // URL конвейера
        String url = "https://cp.privatbank.ua:443/api/1/json";
        // логин авторизации к API
        String apiLogin = "4989";
        // ключ доступа по логину
        String key = "uMX1z4u8prV9XdAqg5vRzARlco0GmXBBsRsxmeboK8NZxgEDEj";

        // ID конвейера в который будем загружать заявку
        String conv_id = "18631";
        //String ref = "7777777";
        String ref = "NetBeans" + System.currentTimeMillis() + new Random(System.currentTimeMillis()).nextInt(1000);

        // Данные которые будут загружены в конвейер 1234
        Map<String, Object> data = new HashMap<String, Object>();
        data.put( "phone", "1" );
        data.put( "card", "2" );

        ConveyorMessage mes = ConveyorMessage.request(
            key,
            Arrays.asList(
                RequestOperation.create( conv_id, ref, data )/*modify*/
            )
        );
        System.out.println( "testGetConvQueryMessage" );
        System.out.println( "key \t" + mes.key );
        System.out.println( "ref \t" + ref );
        System.out.println( "time \t" + mes.time );
        System.out.println( "signCode \t" + mes.signCode );
        //System.out.println( "body \t" + mes.body );

        ConveyorRequest request = ConveyorRequest.getRequest(
            url,
            apiLogin,
            mes
        );
        String answer = new HttpManager().send(request);
        System.out.println( answer.substring(17, 19).equalsIgnoreCase("ok")? "true":"false" );
    }
    
}
