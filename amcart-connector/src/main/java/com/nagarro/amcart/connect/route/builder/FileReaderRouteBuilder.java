package com.nagarro.amcart.connect.route.builder;

import java.util.Collection;

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
            String pathAfterProcess = String.format(ConnectorConstants.FILE_IMPORT_URL,
                    routeConfiguration.getProcessedFileDirectory(), messageType);
            from(path).process(fileMessageProcessor).to(pathAfterProcess);
        }
    }

}
