package net.runnerdave.api;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ResourceBundle;

/**
 * Created by runnerdave on 13/02/17.
 */
public class SwaggerDocumentSetup extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle( "Runnerdave general API's" );
        beanConfig.setDescription( "Core API's that can be used to copy the use of Swagger and Jersey." );

        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:".concat(bundle.getString("tomcat.port")));
        beanConfig.setBasePath("/jersey_rest/services");
        beanConfig.setResourcePackage( "net.runnerdave.api.services" );

        beanConfig.setScan(true);

    }

}
