package com.sporthealth.common.utils;

import org.junit.Test;

import java.lang.annotation.*;

import static org.junit.Assert.*;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-19 23:20
 **/
public class ObjectUtilsTest {

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.SOURCE)
    @Inherited
    public @interface SupProperties {

        /**
         * Treelist的ID	串
         */
        String id() default "";

        /**
         * 主键	串, 下面<col>的列名，复合主键须以逗号分隔
         */
        String key() default "";

        String value() default "default";
    }

    @SupProperties(id = "101", key = "kdc", value = "111")
    public class A{

    }

    public class TestSupProperties{
        private String id;
        private String key;
        private String value;

        @Override
        public String toString() {
            return "TestSupProperties{" +
                    "id='" + id + '\'' +
                    ", key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Test
    public void annotationToObject() {
        TestSupProperties testSupProperties = new TestSupProperties();
        Annotation declaredAnnotation = A.class.getDeclaredAnnotation(SupProperties.class);
        ObjectUtils.annotationToObject(declaredAnnotation, testSupProperties);
        System.out.println(testSupProperties.toString());
    }
}