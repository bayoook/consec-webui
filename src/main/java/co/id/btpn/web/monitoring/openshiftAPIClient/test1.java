package co.id.btpn.web.monitoring.openshiftAPIClient;

import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;

public class test1 {
    
    public static void main(String[] args) {

        try (OpenShiftClient client = new DefaultOpenShiftClient()) {
            System.out.println("Client opened is: " + client);
            client.pods().list().getItems().stream().forEach(
              p -> System.out.println("pod: " + p));
        }

    }
   
}
