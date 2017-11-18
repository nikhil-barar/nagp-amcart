package com.nagarro.amcart.connect.processor.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.amcart.connect.util.CsvUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * The Class AbstractCsvMessageProcessor.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractCsvMessageProcessor<T> extends AbstractFileMessageProcessor {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCsvMessageProcessor.class);

    /** The class of T. */
    private Class<T> classOfT;

    private String[] columns;

    /**
     * Instantiates a new abstract csv message processor.
     *
     * @param classOfT
     *            the class of T
     */
    public AbstractCsvMessageProcessor(Class<T> classOfT) {
        this.classOfT = classOfT;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.nagarro.amcart.connect.processor.impl.AbstractFileMessageProcessor#processStringMessage(java.lang.String)
     */
    @Override
    protected String processStringMessage(String message) throws Exception {
        StringWriter writer = new StringWriter();
        try (CSVReader csvReader = new CSVReader(new StringReader(message))) {
            String[] readValues = csvReader.readNext();
            String[] columns = readValues;
            Collection<String> outputColumnSet = null;
            try (CSVWriter csvWriter = new CSVWriter(writer)) {
                if (ArrayUtils.isNotEmpty(columns)) {
                    while (ArrayUtils.isNotEmpty((readValues = csvReader.readNext()))) {
                        T object = CsvUtils.csvToObject(classOfT, readValues, columns);
                        T processedObject = processObject(object);
                        Map<String, String> csvValues = CsvUtils.ObjectToCsv(processedObject);
                        if (Objects.isNull(outputColumnSet)) {
                            List<String> outputColumns = new ArrayList<>(csvValues.keySet());
                            Collections.sort(outputColumns);
                            outputColumnSet = outputColumns;
                            csvWriter.writeNext(outputColumnSet.toArray(new String[outputColumnSet.size()]));
                        }
                        writeOutput(outputColumnSet, csvWriter, csvValues);
                    }
                }
            }
        }
        return writer.toString();
    }

    private void writeOutput(Collection<String> outputColumnSet, CSVWriter csvWriter, Map<String, String> csvValues) {
        String[] nextLine = new String[outputColumnSet.size()];
        int index = 0;
        for (String key : outputColumnSet) {
            nextLine[index] = csvValues.get(key);
            index++;
        }
        csvWriter.writeNext(nextLine);
    }

    private void writeCsv(String[] columnMapping, List<T> processObjects, StringWriter writer) {
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            for (T object : processObjects) {
                csvWriter.writeNext(getFields(object, columnMapping));
            }
        } catch (IOException exception) {
            LOGGER.error("Fail to write CSV.", exception);
        }
    }

    private String[] getFields(T object, String[] columnMapping) {
        int numOfColumns = columnMapping.length;
        String[] values = new String[numOfColumns];
        for (int i = 0; i < numOfColumns; i++) {
            String fieldName = columnMapping[i];
            Object value;
            try {
                value = Whitebox.getInternalState(object, fieldName);
            } catch (RuntimeException exception) {
                LOGGER.error("Fail to parse {} value for object:{}", fieldName, object);
                value = null;
            }
            values[i] = Objects.toString(value);
        }
        return values;
    }

    /**
     * Process objects.
     *
     * @param objects
     *            the objects
     * @return the list
     */
    private List<T> processObjects(List<T> objects) {
        List<T> processedObjectList = new ArrayList<>(objects.size());
        for (T object : objects) {
            T processedObject = processObject(object);
            processedObjectList.add(processedObject);
        }
        return processedObjectList;
    }

    /**
     * Gets the column mapping.
     *
     * @return the column mapping
     */
    protected String[] getColumnMapping() {
        if (Objects.isNull(columns)) {
            Field[] fields = classOfT.getDeclaredFields();
            List<String> columnList = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
            columns = columnList.toArray(new String[columnList.size()]);
        }
        return columns;
    }

    /**
     * Process object.
     *
     * @param object
     *            the object
     * @return the t
     */
    protected abstract T processObject(T object);
}
