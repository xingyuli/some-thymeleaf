package org.swordess.toy.thymeleaf;

import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by liuuyxin on 15-1-27.
 */
public class Demo {

    private TemplateEngine templateEngine = Application.getTemplateEngine();

    @Test
    public void testI18nAndURLAndLiteral() {
        User user = new User();
        user.setName("Huage");

        Order order = new Order();
        order.setId(3L);

        Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("user", user);
        ctx.setVariable("o", order);

        /*
         * Just before execution, a special variable is set into all context objects (implementations of IContext),
         * including both Context and WebContext, called the execution info (execInfo). This variable contains two
         * pieces of data that can be used from within your templates:
         *
         *   The template name (${execInfo.templateName}), the name specified for engine execution, and corresponding
         *   to the template being executed.
         *
         *   The current date and time (${execInfo.now}), a Calendar object corresponding to the moment the template
         *   engine started its execution for this template.
         */
        PrintWriter writer = stdWriter();
        templateEngine.process("home", ctx, writer);
        writer.flush();

        /*
         * org.thymeleaf.context.IWebContext
         *
         * From the interface definition we can tell that WebContext will offer specialized methods for obtaining the
         * request parameters and request, session and application attributes . But in fact WebContext will do a little
         * bit more than just that:
         *
         *   Add all the request attributes to the context variables map.
         *   Add a context variable called param containing all the request parameters.
         *   Add a context variable called session containing all the session attributes.
         *   Add a context variable called application containing all the ServletContext attributes.
         */
    }

    @Test
    public void testIteration() {
        List<User> users = new ArrayList<>();
        users.add(new User("Harry"));
        users.add(new User("Josh"));
        users.add(new User("Tina"));

        Context ctx = new Context();
        ctx.setVariable("users", users);

        PrintWriter writer = stdWriter();
        templateEngine.process("iteration", ctx, writer);
        writer.flush();
    }

    @Test
    public void testInclude() {
        Context ctx = new Context();
        PrintWriter writer = stdWriter();
        templateEngine.process("testInclude", ctx, writer);
        writer.flush();
    }

    @Test
    public void testReplace() {
        Context ctx = new Context();
        PrintWriter writer = stdWriter();
        templateEngine.process("testReplace", ctx, writer);
        writer.flush();
    }

    @Test
    public void testParameterizableFragment() {
        Context ctx = new Context();
        PrintWriter writer = stdWriter();
        templateEngine.process("testParameterizableFragment", ctx, writer);
        writer.flush();
    }

    private PrintWriter stdWriter() {
        return new PrintWriter(new OutputStreamWriter(System.out));
    }

}
