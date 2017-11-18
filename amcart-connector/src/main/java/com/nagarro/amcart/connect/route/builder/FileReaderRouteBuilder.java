package com.nagarro.amcart.connect.route.builder;

import java.util.Collection;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.nagarro.amcart.connect.ConnectorConstants;
import com.nagarro.amcart.connect.processor.FileMessageProcessor;
import com.nagarro.amcart.connect.route.config.RouteConfiguration;

@Component
@EnableConfigurationProperties(RouteConfiguration.class)
public class FileReaderRouteBuilder extends RouteBuilder {

    @Autowired
    private Collection<FileMessageProcessor> fileMessageProcessors;

    @Autowired
    private RouteConfiguration routeConfiguration;

    @Override
    public void configure() throws Exception {
        for (FileMessageProcessor fileMessageProcessor : fileMessageProcessors) {
            String messageType = fileMessageProcessor.getMessageType().getType();
            String path = String.format(ConnectorConstants.FILE_IMPORT_URL, routeConfiguration.getInputFileDirectory(),
                    messageType);
            String pathAfterSuccessProcess = String.format(ConnectorConstants.FILE_OUTPUT_URL,
                    routeConfiguration.getProcessedFileDirectory(), messageType, ConnectorConstants.SUCCESS);
            String pathAfterFailProcess = String.format(ConnectorConstants.FILE_OUTPUT_URL,
                    routeConfiguration.getProcessedFileDirectory(), messageType, ConnectorConstants.FAIL);
            from(path).onException(Exception.class).handled(true).to(pathAfterFailProcess)
            .end().process(fileMessageProcessor).setHeader(Exchange.FILE_NAME)
                    .simple(ConnectorConstants.FILE_NAME_PROPERTY).to(pathAfterSuccessProcess);
        }
    }

}
