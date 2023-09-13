package tk.scaks.resource.config;

import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Base encryption configuration
 */
@Configuration
public class EncryptionConfig {

    private final JasyptEncryptorConfigurationProperties encryptorProperties;

    public EncryptionConfig(JasyptEncryptorConfigurationProperties encryptorProperties) {
        this.encryptorProperties = encryptorProperties;
    }

    @Bean
    StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptorProperties.getPassword());
        config.setAlgorithm(encryptorProperties.getAlgorithm());
        config.setKeyObtentionIterations(encryptorProperties.getKeyObtentionIterations());
        config.setPoolSize(encryptorProperties.getPoolSize());
        config.setProviderName(encryptorProperties.getProviderName());
        config.setSaltGeneratorClassName(encryptorProperties.getSaltGeneratorClassname());
        config.setIvGeneratorClassName(encryptorProperties.getIvGeneratorClassname());
        config.setStringOutputType(encryptorProperties.getStringOutputType());
        encryptor.setConfig(config);
        return encryptor;
    }
}
