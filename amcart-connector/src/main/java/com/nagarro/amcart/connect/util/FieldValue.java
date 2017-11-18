package com.nagarro.amcart.connect.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.powermock.reflect.Whitebox;

public class FieldValue {
    private Map<String, Object> objectMap;
    private Class<?> objectClass;
    private Class<?> targetCollectionClass;
    private Object object;
    private Field field;

    public FieldValue(Field field, Object object) {
        this.field = field;
        this.object = object;
        this.targetCollectionClass = field.getType();
        init(field);
    }

    private void init(Field field) {
        objectMap = new LinkedHashMap<>();
        if (targetCollectionClass.isArray()) {
            objectClass = targetCollectionClass.getComponentType();
        } else if (Collection.class.isAssignableFrom(targetCollectionClass)) {
            Type genericType = field.getGenericType();
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            objectClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
    }

    public Object getTargetObject() {
        return getTargetObject(objectClass);
    }

    @SuppressWarnings("unchecked")
    <T> Object getTargetObject(Class<T> clazz) {
        Object newObject;
        if (Collection.class.isAssignableFrom(targetCollectionClass)) {
            Collection<T> newCollection = createCollection(clazz);
            for (Object object : objectMap.values()) {
                newCollection.add((T) object);
            }
            newObject = newCollection;
        } else {
            newObject = Array.newInstance(objectClass, objectMap.size());
            T[] array = (T[]) newObject;
            int index = 0;
            for (Object object : objectMap.values()) {
                array[index] = (T) object;
                index++;
            }
        }
        return newObject;
    }

    @SuppressWarnings("unchecked")
    private <T> Collection<T> createCollection(Class<T> clazz) {
        Collection<T> collectionOfT;
        if (targetCollectionClass.isInterface() || Modifier.isAbstract(targetCollectionClass.getModifiers())) {
            if (targetCollectionClass.isAssignableFrom(List.class)) {
                collectionOfT = new ArrayList<>();
            } else if (targetCollectionClass.isAssignableFrom(HashSet.class)) {
                collectionOfT = new HashSet<>();
            } else if (targetCollectionClass.isAssignableFrom(ArrayDeque.class)) {
                collectionOfT = new ArrayDeque<>();
            } else {
                collectionOfT = (Collection<T>) Whitebox.newInstance(targetCollectionClass);
            }

        } else {
            collectionOfT = (Collection<T>) Whitebox.newInstance(targetCollectionClass);
        }
        return collectionOfT;
    }

    public Object getObject(String indexString) {
        Object object = objectMap.get(indexString);
        if (Objects.isNull(object)) {
            object = Whitebox.newInstance(objectClass);
            objectMap.put(indexString, object);
        }
        return object;
    }

    public void addObject(String indexString, Object object) {
        objectMap.put(indexString, object);
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public void assignValues() {
        Whitebox.setInternalState(object, field.getName(), getTargetObject());
    }
}
