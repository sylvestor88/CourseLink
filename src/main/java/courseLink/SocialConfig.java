package courseLink;

import org.springframework.context.annotation.Bean;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.stereotype.Controller;

@Controller
public class SocialConfig {
	
	@Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
       // registry.addConnectionFactory(new LinkedInConnectionFactory(
         //       .getProperty("linkedin.consumerKey"),
         //       environment.getProperty("linkedin.consumerSecret")));
        return registry;
    }
	
}
