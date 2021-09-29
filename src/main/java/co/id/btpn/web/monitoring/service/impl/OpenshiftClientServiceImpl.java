package co.id.btpn.web.monitoring.service.impl;


import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;
import io.fabric8.openshift.client.OpenShiftClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.id.btpn.web.monitoring.service.OpenshiftClientService;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.openshift.api.model.Project;
import io.fabric8.openshift.api.model.ProjectList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service("openshiftClientService")
public class OpenshiftClientServiceImpl implements OpenshiftClientService{

	    private static final Logger logger = LoggerFactory.getLogger(OpenshiftClientServiceImpl.class);

        private KubernetesClient client =null;

        
        @Value("${kubernetes.keystore.file}")
        private String kubernetesKeystoreFile;

        @Value("${kubernetes.keystore.passphrase}")
        private String kubernetesKeystorePassphrase;

        @Value("${kubernetes.truststore.file}")
        private String kubernetesTruststoreFile;

        @Value("${kubernetes.truststore.passphrase}")
        private String kubernetesTruststorePassphrase;


        @Value("${kubernetes.force.basicAuth}")
        private boolean kubernetesForceBasicAuth;

        @Value("${openshift.url}")
        private String openshiftUrl;

        @Value("${kubernetes.auth.basic.username}")
        private String kubernetesAuthBasicUsername;

        @Value("${kubernetes.auth.basic.password}")
        private String kubernetesAuthBasicPassword;

        @Value("${kubernetes.auth.tryServiceAccount}")
        private String kubernetesAuthTryServiceAccount;

        // @Value("${kubernetes.certs.client.file}")
        // private String kubernetesCertsClientFile;

        // @Value("${kubernetes.auth.token}")
        // private String kubernetesAuthToken;

        @Value("${kubernetes.namespace}")
        private String kubernetesNamespace;

        @Override
        public OpenShiftClient getConnection() {

		String basedir = System.getenv("PROJECT_DIR");
        String keyStore = basedir + kubernetesKeystoreFile;
        String trustStore = basedir + kubernetesTruststoreFile;
		
        System.setProperty("kubernetes.trust.certificates", "true");
            if(!kubernetesForceBasicAuth){
                System.setProperty("kubernetes.auth.tryServiceAccount", "true");
                try (KubernetesClient client =  new DefaultKubernetesClient(new ConfigBuilder()
                    .withKeyStoreFile(keyStore)
                    .withKeyStorePassphrase(kubernetesKeystorePassphrase)
                    .withTrustStoreFile(trustStore)
                    .withTrustStorePassphrase(kubernetesTruststorePassphrase)
                    .withNamespace(kubernetesNamespace)
                    .build())) {

                    if (Boolean.FALSE.equals(client.isAdaptable(OpenShiftClient.class))) {
                        logger.warn("Target cluster is not OpenShift compatible");
                        return null;
                    }else{
                        OpenShiftClient oClient = client.adapt(OpenShiftClient.class);
                        logger.info("Login Successful to : {} ",oClient.getMasterUrl());
                        return oClient;
                    } 
                }
            }else{
                try (KubernetesClient client =  new DefaultKubernetesClient(new ConfigBuilder()
                    .withMasterUrl(openshiftUrl)
                    .withUsername(kubernetesAuthBasicUsername)
                    .withPassword(kubernetesAuthBasicPassword)
                    .withKeyStoreFile(keyStore)
                    .withKeyStorePassphrase(kubernetesKeystorePassphrase)
                    .withTrustStoreFile(trustStore)
                    .withTrustStorePassphrase(kubernetesTruststorePassphrase)
                    .withNamespace(kubernetesNamespace)
                    .build())) {

                    if (Boolean.FALSE.equals(client.isAdaptable(OpenShiftClient.class))) {
                        logger.warn("Target cluster is not OpenShift compatible");
                        return null;
                    }else{
                        OpenShiftClient oClient = client.adapt(OpenShiftClient.class);
                        logger.info("Login Successful to : {} ",oClient.getMasterUrl());
                        return oClient;
                    } 
                }
            }
        }
    
}
