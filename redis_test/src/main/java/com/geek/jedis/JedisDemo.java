package com.geek.jedis;

import redis.clients.jedis.Jedis;

public class JedisDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.142.135", 6379);

        String ping = jedis.ping();

        System.out.println("ping = " + ping);

        String set = jedis.set("name", "geek");

        System.out.println("set = " + set);

        String name = jedis.get("name");

        System.out.println("name = " + name);
    }
}

// Exception in thread "main" redis.clients.jedis.exceptions.JedisDataException: DENIED Redis is running in protected mode because protected mode is enabled, no bind address was specified, no authentication password is requested to clients. In this mode connections are only accepted from the loopback interface. If you want to connect from external computers to Redis you may adopt one of the following solutions: 1) Just disable protected mode sending the command 'CONFIG SET protected-mode no' from the loopback interface by connecting to Redis from the same host the server is running, however MAKE SURE Redis is not publicly accessible from internet if you do so. Use CONFIG REWRITE to make this change permanent. 2) Alternatively you can just disable the protected mode by editing the Redis configuration file, and setting the protected mode option to 'no', and then restarting the server. 3) If you started the server manually just for testing, restart it with the '--protected-mode no' option. 4) Setup a bind address or an authentication password. NOTE: You only need to do one of the above things in order for the server to start accepting connections from the outside.
