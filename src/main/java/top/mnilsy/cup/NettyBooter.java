package top.mnilsy.cup;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.mnilsy.cup.netty.WSServer;

/**
 * Created by mnilsy on 19-4-22 下午8:05.
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent()==null){
            WSServer.getInstance().start();
        }
    }
}
