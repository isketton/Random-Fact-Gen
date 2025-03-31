package org.randomfacts;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.SetArgs;
import io.quarkus.redis.datasource.value.ValueCommands;
import org.randomfacts.model.Fact;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.function.Supplier;

@ApplicationScoped
public class MyRedisCache {

    private final ValueCommands<String, Fact> commands;

    public MyRedisCache(RedisDataSource ds) {
        this.commands = ds.value(Fact.class);
    }

    public Fact get(String key) {
        return commands.get(key);
    }

    public void set(String key, Fact result) {
        commands.set(key, result, new SetArgs().ex(Duration.ofSeconds(10)));
    }

    public void evict(String key) {
        commands.getdel(key);
    }

    public Fact getOrSetIfAbsent(String key,
           Supplier<Fact> computation) {
        var cached = get(key);
        if (cached != null) {
            return cached;
        } else {
            var result = computation.get();
            set(key, result);
            return result;
        }
    }
}