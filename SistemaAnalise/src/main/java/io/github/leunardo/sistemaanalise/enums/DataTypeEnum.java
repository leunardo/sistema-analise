/*
More in github.com/leunardo
 */
package io.github.leunardo.sistemaanalise.enums;

/**
 *
 * @author leonardo
 */
public enum DataTypeEnum {
    SalesMan {
        @Override
        public String toString() {
            return "001";
        }
    },
    Client {
        @Override
        public String toString() {
            return "002";
        }
    },
    Sales {
        @Override
        public String toString() {
            return "003";
        }
    }
            

}
