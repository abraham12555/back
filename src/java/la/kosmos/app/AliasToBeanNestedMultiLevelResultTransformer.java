/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package la.kosmos.app;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.BasicTransformerAdapter;
import org.hibernate.transform.ResultTransformer;

/**
 *
 * @author elizabeth
 */
public class AliasToBeanNestedMultiLevelResultTransformer extends AliasedTupleSubsetResultTransformer {

    private static final long serialVersionUID = -8047276133980128266L;

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    private boolean initialized;
    private Class<?> resultClass;
    private Map<String, Class<?>> clazzMap = new HashMap<>();
    private Map<String, Setter> settersMap = new HashMap<>();

    public AliasToBeanNestedMultiLevelResultTransformer(Class<?> resultClass) {
        this.resultClass = resultClass;
    }

    @Override
    public Object transformTuple(Object[] tuples, String[] aliases) {

        Map<String, Object> nestedObjectsMap = new HashMap<>();

        Object result;
        try {
            result = resultClass.newInstance();

            if (!initialized) {
                initialized = true;
                initialize(aliases);
            }

            for (int a = 0; a < aliases.length; a++) {

                String alias = aliases[a];
                Object tuple = tuples[a];

                Object baseObject = result;

                int index = alias.lastIndexOf(".");
                if (index > 0) {
                    String basePath = alias.substring(0, index);
                    baseObject = nestedObjectsMap.get(basePath);
                    if (baseObject == null) {
                        baseObject = clazzMap.get(basePath).newInstance();
                        nestedObjectsMap.put(basePath, baseObject);
                    }
                }

                settersMap.get(alias).set(baseObject, tuple, null);

            }

            for (Map.Entry<String, Object> entry : nestedObjectsMap.entrySet()) {
                Setter setter = settersMap.get(entry.getKey());
                if (entry.getKey().contains(".")) {

                    int index = entry.getKey().lastIndexOf(".");
                    String basePath = entry.getKey().substring(0, index);
                    Object obj = nestedObjectsMap.get(basePath);

                    setter.set(obj, entry.getValue(), null);
                } else {
                    setter.set(result, entry.getValue(), null);
                }
            }

        } catch (InstantiationException | IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }

        return result;
    }

    private void initialize(String[] aliases) {

        PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
                new PropertyAccessor[]{
                    PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
                    PropertyAccessorFactory.getPropertyAccessor("field")
                }
        );

        for (int a = 0; a < aliases.length; a++) {

            String alias = aliases[a];

            Class<?> baseClass = resultClass;

            if (alias.contains(".")) {

                String[] split = alias.split("\\.");

                StringBuffer res = new StringBuffer();

                for (int i = 0; i < split.length; i++) {

                    if (res.length() > 0) {
                        res.append(".");
                    }

                    String item = split[i];
                    res.append(item);

                    String resString = res.toString();

                    if (i == split.length - 1) {
                        clazzMap.put(resString, baseClass);
                        settersMap.put(resString, propertyAccessor.getSetter(baseClass, item));
                        break;
                    }

                    Class<?> clazz = clazzMap.get(resString);
                    if (clazz == null) {
                        clazz = propertyAccessor.getGetter(baseClass, item).getReturnType();
                        settersMap.put(resString, propertyAccessor.getSetter(baseClass, item));
                        clazzMap.put(resString, clazz);
                    }
                    baseClass = clazz;
                }
            } else {
                clazzMap.put(alias, resultClass);
                settersMap.put(alias, propertyAccessor.getSetter(resultClass, alias));
            }

        }

    }
}

abstract class AliasedTupleSubsetResultTransformer
        extends BasicTransformerAdapter
        implements TupleSubsetResultTransformer {

    @Override
    public boolean[] includeInTransform(String[] aliases, int tupleLength) {
        if (aliases == null) {
            throw new IllegalArgumentException("aliases cannot be null");
        }
        if (aliases.length != tupleLength) {
            throw new IllegalArgumentException(
                    "aliases and tupleLength must have the same length; "
                    + "aliases.length=" + aliases.length + "tupleLength=" + tupleLength);
        }
        boolean[] includeInTransform = new boolean[tupleLength];
        for (int i = 0; i < aliases.length; i++) {
            if (aliases[ i] != null) {
                includeInTransform[ i] = true;
            }
        }
        return includeInTransform;
    }
}

interface TupleSubsetResultTransformer extends ResultTransformer {

    /**
     * When a tuple is transformed, is the result a single element of the tuple?
     *
     * @param aliases - the aliases that correspond to the tuple
     * @param tupleLength - the number of elements in the tuple
     * @return true, if the transformed value is a single element of the tuple;
     * false, otherwise.
     */
    boolean isTransformedValueATupleElement(String[] aliases, int tupleLength);

    /**
     * Returns an array with the i-th element indicating whether the i-th
     * element of the tuple is included in the transformed value.
     *
     * @param aliases - the aliases that correspond to the tuple
     * @param tupleLength - the number of elements in the tuple
     * @return array with the i-th element indicating whether the i-th element
     * of the tuple is included in the transformed value.
     */
    boolean[] includeInTransform(String[] aliases, int tupleLength);
}