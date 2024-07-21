<?php
namespace php\demonck\steam\auth;
use Closure;

class AuthSteamOpenID
{

    /**
     * Конструктор для AuthSteamOpenID.
     * @param int port Порт
     * @param Closure closure замыкание
     */
    public function __construct(int $port, Closure $closure)
    {

    }

    /**
     * Закрываем javalin
     */
    public function stop(){}

}
?>
