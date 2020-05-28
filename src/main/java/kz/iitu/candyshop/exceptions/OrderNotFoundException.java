package kz.iitu.candyshop.exceptions;

import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class OrderNotFoundException extends PSQLException {

    public OrderNotFoundException() {
        super("Order not found", PSQLState.DATA_ERROR);
    }

    public OrderNotFoundException(String s) {
        super(s, PSQLState.DATA_ERROR);
    }
}
