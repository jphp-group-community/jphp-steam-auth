package org.develnext.jphp.ext.steam.classes;


import io.javalin.Javalin;
import org.develnext.jphp.ext.steam.AuthenticationSteamExtension;
import org.develnext.jphp.ext.steam.support.SteamOpenIDLogin;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.invoke.Invoker;
import php.runtime.lang.BaseObject;
import php.runtime.memory.StringMemory;
import php.runtime.reflection.ClassEntity;

import java.util.List;
import java.util.Map;


@Reflection.Name("AuthSteamOpenID")
@Reflection.Namespace(AuthenticationSteamExtension.NS)
public class AuthSteamB extends BaseObject {

    public AuthSteamB(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    protected AuthSteamB(ClassEntity entity) {
        super(entity);
    }

    protected Javalin app;

    @Reflection.Signature
    public void __construct(int port, Invoker invoker) {
        app = Javalin.create();
        SteamOpenIDLogin openidLogin = new SteamOpenIDLogin();

        app.get("/authsteam-api-v1-demonck", ctx -> {
            String redirectUrl = openidLogin.login("http://localhost:"+ port +"/authsteamcall");
            if (redirectUrl != null) {
                ctx.redirect(redirectUrl);
            } else {
                new Exception("error 403");
            }
        });

        app.get("/authsteamcall", ctx -> {
            Map<String, List<String>> params = ctx.queryParamMap();
            String steamIdUser = openidLogin.verify(ctx.url(), params);

            if (steamIdUser != null) {
                try {
                    invoker.call(new StringMemory(steamIdUser));
                    // to-do ctx.html("<html><body><script>window.close();</script></body></html>");
                } catch (Throwable e) {
                    throw new Exception(e);
                }

            } else {
                new Exception("Failed to verify OpenID login");
            }

        });
        app.start(port);
    }

    @Reflection.Signature
    public void stop(){
        app.stop();

    }

}
