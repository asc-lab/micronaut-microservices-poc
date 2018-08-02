package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.reflect.GenericTypeUtils;
import io.micronaut.inject.BeanDefinition;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.Query;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
@SuppressWarnings("unchecked")
public class Registry {

    private Map<Class<? extends Command>, CommandProvider> commandProviderMap = new HashMap<>();
    private Map<Class<? extends Query>, QueryProvider> queryProviderMap = new HashMap<>();

    public Registry(ApplicationContext applicationContext) {
        Collection<BeanDefinition<CommandHandler>> commandHandlers = applicationContext.getBeanDefinitions(CommandHandler.class);
        commandHandlers.forEach(x -> registerCommand(applicationContext, x));

        Collection<BeanDefinition<QueryHandler>> queryHandlers = applicationContext.getBeanDefinitions(QueryHandler.class);
        queryHandlers.forEach(x -> registerQuery(applicationContext, x));
    }

    private void registerCommand(ApplicationContext applicationContext, BeanDefinition<CommandHandler> bean) {
        Class<CommandHandler> handlerClass = bean.getBeanType();
        Class<?>[] generics = GenericTypeUtils.resolveInterfaceTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
        commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
    }

    private void registerQuery(ApplicationContext applicationContext, BeanDefinition<QueryHandler> bean) {
        Class<QueryHandler> handlerClass = bean.getBeanType();
        Class<?>[] generics = GenericTypeUtils.resolveInterfaceTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends Query> queryType = (Class<? extends Query>) generics[1];
        queryProviderMap.put(queryType, new QueryProvider(applicationContext, handlerClass));
    }

    <R, C extends Command<R>> CommandHandler<R, C> getCmd(Class<C> commandClass) {
        return commandProviderMap.get(commandClass).get();
    }

    <R, C extends Query<R>> QueryHandler<R, C> getQuery(Class<C> commandClass) {
        return queryProviderMap.get(commandClass).get();
    }
}
