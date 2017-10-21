package br.com.newidea.matchproject;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.util.Locale;

@Configuration
@EnableMetrics
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {MatchProjectApiConfig.class})
public class MatchProjectApiConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MetricRegistry metricRegistry;

    @Value("${client.http.connection.timeout}")
    private int connectionTimeout;

    @Value("${client.http.read.timeout}")
    private int readTimeout;

    @PostConstruct
    public void setUpiWithMetrics() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).setMetricRegistry(metricRegistry);
        }
    }

    @Bean
    public LocaleResolver localResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }


    @Bean
    @Primary
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory(false));
        configRestTemplate(restTemplate);
        return restTemplate;
    }

    private void configRestTemplate(final RestTemplate restTemplate) {
        // configuracao para o resttemplate nao lancar excecao quando o servico nao retorna um codigo 200
        restTemplate.setErrorHandler(new CustomErrorHandler());
        // configurando o charset
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.getMessageConverters()
                .add(new MappingJackson2XmlHttpMessageConverter());
    }

    private static class CustomErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(final ClientHttpResponse response) throws IOException {
            return HttpStatus.Series.CLIENT_ERROR.equals(response.getStatusCode().series())
                    || HttpStatus.Series.SERVER_ERROR.equals(response.getStatusCode().series());
        }

        @Override
        public void handleError(final ClientHttpResponse response) throws IOException {
            // ignorar erro!
        }
    }

    private ClientHttpRequestFactory clientHttpRequestFactory(Boolean proxy) {
        final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        factory.setOutputStreaming(false);
        if (proxy) {
            factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("pagseguro.proxy.srv.intranet", 80)));
        }
        return factory;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}

