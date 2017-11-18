package com.nagarro.amcart.connect.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvUtils {
    private static final Logger LOGGER=LoggerFactory.getLogger(CsvUtils.class);

    protected static final String COLUMN_SEPARATOR = ".";
    protected static final String COLUMN_SEPARATOR_REGEX = "\\" + COLUMN_SEPARATOR;
    private static final String COLLECTION_INDECATOR = "[";
    private static final String COLLECTION_INDECATOR_CLOSE = "]";

    public static <T> T csvToObject(Class<T> clazz, String[] value, String... colums) throws IOException {
        T object;
        try {
            object = Whitebox.newInstance(clazz);
            setValues(object, value, colums);
        } catch (InstantiationException | IllegalAccessException | RuntimeException exception) {
            LOGGER.error("Fail to parse CSV.",exception);
            throw new IOException(exception);
        }
        return object;
    }

    protected static <T> void setValues(T object, String[] columnsValues, String... colums)
            throws InstantiationException, IllegalAccessException {
        int columLen = colums.length;
        int valueLen = columnsValues.length;
        Map<String, FieldValue> columnValueMap = new LinkedHashMap<>();
        for (int index = 0; index < columLen; index++) {
            if (index < valueLen) {
                String columnsValue = columnsValues[index];
                if (StringUtils.isNotEmpty(columnsValue)) {
                    String column = colums[index];
                    if (StringUtils.contains(column, COLLECTION_INDECATOR)) {
                        setCollectonColumnValue(columnValueMap, object, columnsValue, column, StringUtils.EMPTY);
                    } else {
                        setColumnValue(object, columnsValue, column);
                    }
                }
            }
        }
        setFieldValue(columnValueMap);
    }

    private static void setFieldValue(Map<String, FieldValue> columnValueMap) {
        columnValueMap.entrySet().forEach(entry -> entry.getValue().assignValues());
    }

    private static <T> void setCollectonColumnValue(Map<String, FieldValue> columnValueMap, T object,
            String columnsValue, String column, String processColumn)
            throws InstantiationException, IllegalAccessException {
        String[] columns = column.split(COLUMN_SEPARATOR_REGEX, 2);
        String currentColumn = columns[0];
        if (columns.length == 1) {
            if (StringUtils.contains(currentColumn, COLLECTION_INDECATOR)) {
                Entry<String, String> columnNameWithIndex = getColumnNameWithIndex(currentColumn);
                String collectionColumn = columnNameWithIndex.getKey();
                String index = columnNameWithIndex.getValue();
                Field field = Whitebox.getField(object.getClass(), collectionColumn);
                FieldValue fieldValue = getFieldValue(columnValueMap, processColumn + collectionColumn, field, object);
                Object targetTypeValue = getTargetObject(columnsValue, fieldValue.getObjectClass());
                fieldValue.addObject(index, targetTypeValue);
            } else {
                setColumnValue(object, columnsValue, column);
            }
        } else {
            if (StringUtils.contains(currentColumn, COLLECTION_INDECATOR)) {
                Entry<String, String> columnNameWithIndex = getColumnNameWithIndex(currentColumn);
                String collectionColumn = columnNameWithIndex.getKey();
                String index = columnNameWithIndex.getValue();
                Field field = Whitebox.getField(object.getClass(), collectionColumn);
                FieldValue fieldValue = getFieldValue(columnValueMap, processColumn + collectionColumn, field, object);
                Object targetTypeValue = fieldValue.getObject(index);
                setCollectonColumnValue(columnValueMap, targetTypeValue, columnsValue, columns[1],
                        processColumn + currentColumn + COLUMN_SEPARATOR);
            } else {
                Field field = Whitebox.getField(object.getClass(), currentColumn);
                Object targetTypeValue = Whitebox.getInternalState(object, field.getName());
                if (null == targetTypeValue) {
                    targetTypeValue = ConvertUtils.convert(columnsValue, field.getType());
                    Whitebox.setInternalState(object, currentColumn, targetTypeValue);
                }
                setCollectonColumnValue(columnValueMap, targetTypeValue, columnsValue, columns[1],
                        processColumn + currentColumn + COLUMN_SEPARATOR);
            }
        }
    }

    private static FieldValue getFieldValue(Map<String, FieldValue> columnValueMap, String currentColumn, Field field,
            Object object) {
        FieldValue fieldValue = columnValueMap.get(currentColumn);
        if (null == fieldValue) {
            fieldValue = new FieldValue(field, object);
            columnValueMap.put(currentColumn, fieldValue);
        }
        return fieldValue;
    }

    private static Entry<String, String> getColumnNameWithIndex(String currentColumn) {
        int startIndex = currentColumn.indexOf(COLLECTION_INDECATOR);
        String column = currentColumn.substring(0, startIndex);
        int length = COLLECTION_INDECATOR.length();
        String collectionIndex = currentColumn.substring(startIndex + length, currentColumn.length() - length);
        return new ImmutablePair<>(column, collectionIndex);
    }

    private static <T> void setColumnValue(T object, String columnsValue, String column)
            throws InstantiationException, IllegalAccessException {
        String[] subColums = column.split(COLUMN_SEPARATOR_REGEX);
        int subColumsLen = subColums.length;
        Object columnObject = object;
        for (int columnIndex = 0; columnIndex < subColumsLen; columnIndex++) {
            String subColum = subColums[columnIndex];
            if (columnIndex + 1 == subColumsLen) {
                Field field = Whitebox.getField(columnObject.getClass(), subColum);
                Class<?> coulmnType = field.getType();
                Object targetTypeValue = getTargetObject(columnsValue, coulmnType);
                Whitebox.setInternalState(columnObject, subColum, targetTypeValue);
            } else {
                Object nextColumnObject = Whitebox.getInternalState(columnObject, subColum);
                if (null == nextColumnObject) {
                    Field field = Whitebox.getField(columnObject.getClass(), subColum);
                    Class<?> coulmnType = field.getType();
                    nextColumnObject = Whitebox.newInstance(coulmnType);
                    Whitebox.setInternalState(columnObject, subColum, nextColumnObject);
                }
                columnObject = nextColumnObject;
            }
        }
    }

    private static Object getTargetObject(String columnsValue, Class<?> coulmnType) {
        Object targetTypeValue = null;
        if (coulmnType.isEnum()) {
            Enum<?>[] enumConstants = (Enum<?>[]) coulmnType.getEnumConstants();
            for (Enum<?> enumConstant : enumConstants) {
                if (StringUtils.equalsIgnoreCase(columnsValue, enumConstant.name())) {
                    targetTypeValue = enumConstant;
                    break;
                }
            }
        } else {
            targetTypeValue = ConvertUtils.convert(columnsValue, coulmnType);
        }
        return targetTypeValue;
    }

    public static <T> Map<String, String> ObjectToCsv(T object) throws IOException {
        Map<String, String> valueMap = new HashMap<>();
        try {
            if (Objects.nonNull(object)) {
                convertToCsv(object, StringUtils.EMPTY, valueMap);
            }
        } catch (IllegalArgumentException | IllegalAccessException exception) {
            throw new IOException(exception);
        }
        return valueMap;
    }

    private static <T> void convertToCsv(T object, String prePix, Map<String, String> valueMap)
            throws IllegalArgumentException, IllegalAccessException {
        Set<Field> allInstanceFields = Whitebox.getAllInstanceFields(object);
        for (Field field : allInstanceFields) {
            Object fieldObject = field.get(object);
            if (null != fieldObject) {
                Class<?> fieldType = field.getType();
                if (fieldType.isArray() || Iterable.class.isAssignableFrom(fieldType)) {
                    int size = CollectionUtils.size(fieldObject);
                    for (int index = 0; index < size; index++) {
                        Object objectOfCollection = CollectionUtils.get(fieldObject, index);
                        Converter converter = ConvertUtils.lookup(objectOfCollection.getClass());
                        String columnName = prePix + field.getName() + COLLECTION_INDECATOR + index
                                + COLLECTION_INDECATOR_CLOSE;
                        if (Objects.isNull(converter) && !fieldType.isEnum()) {
                            convertToCsv(objectOfCollection, columnName + COLUMN_SEPARATOR, valueMap);
                        } else {
                            valueMap.put(columnName, Objects.toString(fieldObject));
                        }
                    }
                } else {
                    Converter converter = ConvertUtils.lookup(fieldType);
                    if (Objects.isNull(converter) && !fieldType.isEnum()) {
                        convertToCsv(fieldObject, prePix + field.getName() + COLUMN_SEPARATOR, valueMap);
                    } else {
                        valueMap.put(prePix + field.getName(), Objects.toString(fieldObject));
                    }
                }
            }
        }
    }
}
