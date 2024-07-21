package org.develnext.jphp.ext.steam;

import org.develnext.jphp.ext.steam.classes.AuthSteamB;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;



public class AuthenticationSteamExtension extends Extension
{
    public static final String NS = "php\\demonck\\steam\\auth";

    @Override
    public Status getStatus()
    {
        return Status.STABLE;
    }
    @Override
    public void onRegister(CompileScope scope)
    {

        registerClass(scope, AuthSteamB.class);


    }
}
