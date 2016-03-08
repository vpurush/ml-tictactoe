package vpurush.tictactoe.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig(){

		packages("vpurush.tictactoe.resources");
		

        register(RequestContextFilter.class);
	}
}
