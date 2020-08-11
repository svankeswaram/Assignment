
package APITest;

import java.util.Arrays;
import java.util.List;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BpdtsAPIService extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger( BpdtsAPIService.class );

    private String host;

    public BpdtsAPIService() {
        host = Configuration.get( "bpdts.api.host" );

    }

    public List<User> getAllUsers() {
        String endpoint = host + "/users";
        return Arrays.asList( callGet( endpoint, 200 ).getBody().as( User[].class ) );
    }

    public User getUserById(String id, int statusCode) {
        String endpoint = host + "/user/" + id;
        if (statusCode == 200) {
            return callGet( endpoint, statusCode ).getBody().as( User.class );
        } else {
            LOGGER.info( callGet( endpoint, statusCode ).jsonPath().get( "message" ).toString() );
            return null;
        }
    }

    public List<User> getUsersByLocation(String city) {
        String endpoint = host + "/city/" + city + "/users";
        return Arrays.asList( callGet( endpoint, 200 ).getBody().as( User[].class ) );
    }

}
