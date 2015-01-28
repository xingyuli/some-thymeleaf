package org.swordess.toy.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

/**
 * Created by liuuyxin on 15-1-27.
 */
public class Application {

    private static TemplateEngine templateEngine;

    static {
        initializeTemplateEngine();
    }

    private static void initializeTemplateEngine() {
        ClassLoaderTemplateResolver htmlTemplateResolver = new ClassLoaderTemplateResolver();
        // XHTML is the default mode, but we set it anyway for better understanding of code
        htmlTemplateResolver.setTemplateMode("XHTML");
        // This will convert "home" to templates/home.html
        htmlTemplateResolver.setPrefix("templates/");
        htmlTemplateResolver.setSuffix(".html");
        // Template cache TTL=1h. If not set, entries would be cached until expelled by LRU
        htmlTemplateResolver.setCacheTTLMs(3600000L);

        /*
         * The location of externalized text in Thymeleaf is fully configurable, and it will depend on the specific
         * org.thymeleaf.messageresolver.IMessageResolver implementation being used. Normally, an implementation based
         * on .properties files will be used, but we could create our own implementations if we wanted, for example, to
         * obtain messages from a database.
         *
         * However, we have not specified a message resolver to our Template Engine during initialization, and that
         * means that our application is using the Standard Message Resolver, implemented by class
         * org.thymeleaf.messageresolver.StandardMessageResolver.
         *
         * This standard message resolver expects to find messages for /WEB-INF/templates/home.html in .properties
         * files in the same folder and with the same name as the template, like:
         *
         *   /WEB-INF/templates/home_en.properties for English texts.
         *   /WEB-INF/templates/home_es.properties for Spanish language texts.
         *   /WEB-INF/templates/home_pt_BR.properties for Portuguese (Brazil) language texts.
         *   /WEB-INF/templates/home.properties for default texts (if locale is not matched).
         */

        ClassLoaderTemplateResolver xmlTemplateResolver = new ClassLoaderTemplateResolver();
        xmlTemplateResolver.setTemplateMode("XML");
        xmlTemplateResolver.setPrefix("data/");
        xmlTemplateResolver.setSuffix(".xml");
        xmlTemplateResolver.setCacheTTLMs(3600000L);

        templateEngine = new TemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver);
        templateEngine.addTemplateResolver(xmlTemplateResolver);
    }

    public static TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

}
