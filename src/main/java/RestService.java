import controller.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;


@ApplicationPath("")
public class RestService extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(PlayerController.class);
        resources.add(ClubController.class);
        resources.add(UpgradeController.class);
        resources.add(PackController.class);
        resources.add(MatchSetupController.class);
        resources.add(MatchController.class);
    }
}
